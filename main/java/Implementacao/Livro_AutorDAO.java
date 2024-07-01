package Implementacao;

import entidades.Autor;
import entidades.Livro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Livro_AutorDAO {
    private Connection conexao;

    public Livro_AutorDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void salvarRelacaoLivroAutor(Livro livro, Autor autor) throws SQLException {
        String sql = "INSERT INTO Livro_Autor (ISBN, ID_Autor) VALUES (?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, livro.getIsbn());
            stmt.setInt(2, autor.getIdAutor());
            stmt.executeUpdate();
        }
    }

    public void removerRelacaoLivroAutor(Livro livro, Autor autor) throws SQLException {
        String sql = "DELETE FROM Livro_Autor WHERE ISBN = ? AND ID_Autor = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, livro.getIsbn());
            stmt.setInt(2, autor.getIdAutor());
            stmt.executeUpdate();
        }
    }

    public List<Autor> listarAutoresPorLivro(Livro livro) throws SQLException {
        List<Autor> autores = new ArrayList<>();
        String sql = "SELECT a.ID_Autor, a.Nome FROM Autor a " +
                     "JOIN Livro_Autor la ON a.ID_Autor = la.ID_Autor " +
                     "WHERE la.ISBN = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, livro.getIsbn());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Autor autor = new Autor(
                        rs.getInt("ID_Autor"),
                        rs.getString("Nome")
                    );
                    autores.add(autor);
                }
            }
        }
        return autores;
    }

    public Autor buscarAutorPorLivro(int idAutor, String isbnLivro) throws SQLException {
        String sql = "SELECT a.ID_Autor, a.Nome FROM Autor a " +
                     "JOIN Livro_Autor la ON a.ID_Autor = la.ID_Autor " +
                     "WHERE la.ISBN = ? AND a.ID_Autor = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, isbnLivro);
            stmt.setInt(2, idAutor);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Autor autor = new Autor(
                        rs.getInt("ID_Autor"),
                        rs.getString("Nome")
                    );
                    return autor;
                }
            }
        }
        return null;
    }
}