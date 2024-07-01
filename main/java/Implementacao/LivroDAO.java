package Implementacao;

import entidades.Autor;
import entidades.Livro;
import entidades.Editora;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {
    private Connection conexao;

    public LivroDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void salvar(Livro livro) throws SQLException {
        String sqlLivro = "INSERT INTO Livro (ISBN, Título, Preço, Ano, Categoria) VALUES (?, ?, ?, ?, ?)";
        String sqlLivroAutor = "INSERT INTO Livro_Autor (ISBN, ID_Autor) VALUES (?, ?)";
        String sqlLivroEditora = "INSERT INTO Livro_Editora (ISBN, ID_Editora) VALUES (?, ?)";

        try {
            conexao.setAutoCommit(false);

            try (PreparedStatement stmtLivro = conexao.prepareStatement(sqlLivro)) {
                stmtLivro.setString(1, livro.getIsbn());
                stmtLivro.setString(2, livro.getTitulo());
                stmtLivro.setDouble(3, livro.getPreco());
                stmtLivro.setInt(4, livro.getAno());
                stmtLivro.setString(5, livro.getCategoria());
                stmtLivro.executeUpdate();
            }

            salvarAutores(livro, sqlLivroAutor);
            salvarEditora(livro, sqlLivroEditora);

            conexao.commit();
        } catch (SQLException e) {
            conexao.rollback();
            throw e;
        } finally {
            conexao.setAutoCommit(true);
        }
    }

    private void salvarAutores(Livro livro, String sql) throws SQLException {
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            for (Autor autor : livro.getAutores()) {
                stmt.setString(1, livro.getIsbn());
                stmt.setInt(2, autor.getIdAutor());
                stmt.executeUpdate();
            }
        }
    }

    private void salvarEditora(Livro livro, String sql) throws SQLException {
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, livro.getIsbn());
            stmt.setInt(2, livro.getEditora().getIdEditora());
            stmt.executeUpdate();
        }
    }

    public Livro buscarPorIsbn(String isbn) throws SQLException {
        String sqlLivro = "SELECT * FROM Livro WHERE ISBN = ?";
        String sqlAutores = "SELECT a.* FROM Livro_Autor la INNER JOIN Autor a ON la.ID_Autor = a.ID_Autor WHERE la.ISBN = ?";
        String sqlEditora = "SELECT e.* FROM Livro_Editora le INNER JOIN Editora e ON le.ID_Editora = e.ID_Editora WHERE le.ISBN = ?";

        try (PreparedStatement stmtLivro = conexao.prepareStatement(sqlLivro)) {
            stmtLivro.setString(1, isbn);
            try (ResultSet resultSet = stmtLivro.executeQuery()) {
                if (resultSet.next()) {
                    Livro livro = new Livro(
                            resultSet.getString("ISBN"),
                            resultSet.getString("Título"),
                            resultSet.getDouble("Preço"),
                            resultSet.getInt("Ano"),
                            resultSet.getString("Categoria"),
                            buscarAutores(isbn, sqlAutores),
                            buscarEditora(isbn, sqlEditora)
                    );
                    return livro;
                }
            }
        }
        return null;
    }

    private List<Autor> buscarAutores(String isbn, String sql) throws SQLException {
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, isbn);
            try (ResultSet resultSet = stmt.executeQuery()) {
                List<Autor> autores = new ArrayList<>();
                while (resultSet.next()) {
                    autores.add(new Autor(
                            resultSet.getInt("ID_Autor"),
                            resultSet.getString("Nome")
                    ));
                }
                return autores;
            }
        }
    }

    private Editora buscarEditora(String isbn, String sql) throws SQLException {
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, isbn);
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    return new Editora(
                            resultSet.getInt("ID_Editora"),
                            resultSet.getString("Nome"),
                            resultSet.getString("Endereco")
                    );
                }
            }
        }
        return null;
    }

    public void atualizar(Livro livro) throws SQLException {
        String sqlLivro = "UPDATE Livro SET Título = ?, Preço = ?, Ano = ?, Categoria = ? WHERE ISBN = ?";
        String sqlRemoverAutores = "DELETE FROM Livro_Autor WHERE ISBN = ?";
        String sqlAdicionarAutores = "INSERT INTO Livro_Autor (ISBN, ID_Autor) VALUES (?, ?)";
        String sqlAtualizarEditora = "UPDATE Livro_Editora SET ID_Editora = ? WHERE ISBN = ?";

        try {
            conexao.setAutoCommit(false);

            try (PreparedStatement stmtLivro = conexao.prepareStatement(sqlLivro)) {
                stmtLivro.setString(1, livro.getTitulo());
                stmtLivro.setDouble(2, livro.getPreco());
                stmtLivro.setInt(3, livro.getAno());
                stmtLivro.setString(4, livro.getCategoria());
                stmtLivro.setString(5, livro.getIsbn());
                stmtLivro.executeUpdate();
            }

            try (PreparedStatement stmtRemover = conexao.prepareStatement(sqlRemoverAutores)) {
                stmtRemover.setString(1, livro.getIsbn());
                stmtRemover.executeUpdate();
            }

            try (PreparedStatement stmtAdicionar = conexao.prepareStatement(sqlAdicionarAutores)) {
                for (Autor autor : livro.getAutores()) {
                    stmtAdicionar.setString(1, livro.getIsbn());
                    stmtAdicionar.setInt(2, autor.getIdAutor());
                    stmtAdicionar.executeUpdate();
                }
            }

            try (PreparedStatement stmtEditora = conexao.prepareStatement(sqlAtualizarEditora)) {
                stmtEditora.setInt(1, livro.getEditora().getIdEditora());
                stmtEditora.setString(2, livro.getIsbn());
                stmtEditora.executeUpdate();
            }

            conexao.commit();
        } catch (SQLException e) {
            conexao.rollback();
            throw e;
        } finally {
            conexao.setAutoCommit(true);
        }
    }

    public void excluir(Livro livro) throws SQLException {
        String sqlLivro = "DELETE FROM Livro WHERE ISBN = ?";
        String sqlAutores = "DELETE FROM Livro_Autor WHERE ISBN = ?";
        String sqlEditora = "DELETE FROM Livro_Editora WHERE ISBN = ?";

        try {
            conexao.setAutoCommit(false);

            try (PreparedStatement stmtLivro = conexao.prepareStatement(sqlLivro)) {
                stmtLivro.setString(1, livro.getIsbn());
                stmtLivro.executeUpdate();
            }

            try (PreparedStatement stmtAutores = conexao.prepareStatement(sqlAutores)) {
                stmtAutores.setString(1, livro.getIsbn());
                stmtAutores.executeUpdate();
            }

            try (PreparedStatement stmtEditora = conexao.prepareStatement(sqlEditora)) {
                stmtEditora.setString(1, livro.getIsbn());
                stmtEditora.executeUpdate();
            }

            conexao.commit();
        } catch (SQLException e) {
            conexao.rollback();
            throw e;
        } finally {
            conexao.setAutoCommit(true);
        }
    }
}
