// Arquivo: Implementacao/Livro_EditoraDAO.java
package Implementacao;

import entidades.Editora;
import entidades.Livro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Livro_EditoraDAO {
    private Connection conexao;

    public Livro_EditoraDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void salvarRelacaoLivroEditora(Livro livro, Editora editora) throws SQLException {
        String sql = "INSERT INTO Livro_Editora (ISBN, ID_Editora) VALUES (?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, livro.getIsbn());
            stmt.setInt(2, editora.getIdEditora());
            stmt.executeUpdate();
        }
    }

    public void removerRelacaoLivroEditora(Livro livro, Editora editora) throws SQLException {
        String sql = "DELETE FROM Livro_Editora WHERE ISBN = ? AND ID_Editora = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, livro.getIsbn());
            stmt.setInt(2, editora.getIdEditora());
            stmt.executeUpdate();
        }
    }

    public List<Editora> listarEditorasPorLivro(Livro livro) throws SQLException {
        List<Editora> editoras = new ArrayList<>();
        String sql = "SELECT e.ID_Editora, e.Nome, e.Endereco FROM Editora e " +
                     "JOIN Livro_Editora le ON e.ID_Editora = le.ID_Editora " +
                     "WHERE le.ISBN = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, livro.getIsbn());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Editora editora = new Editora(
                        rs.getInt("ID_Editora"),
                        rs.getString("Nome"),
                        rs.getString("Endereco")
                    );
                    editoras.add(editora);
                }
            }
        }
        return editoras;
    }

    public Editora buscarEditoraPorLivro(int idEditora, String isbnLivro) throws SQLException {
        String sql = "SELECT e.ID_Editora, e.Nome, e.Endereco FROM Editora e " +
                     "JOIN Livro_Editora le ON e.ID_Editora = le.ID_Editora " +
                     "WHERE le.ISBN = ? AND e.ID_Editora = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, isbnLivro);
            stmt.setInt(2, idEditora);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Editora(
                        rs.getInt("ID_Editora"),
                        rs.getString("Nome"),
                        rs.getString("Endereco")
                    );
                }
            }
        }
        return null;
    }
}
