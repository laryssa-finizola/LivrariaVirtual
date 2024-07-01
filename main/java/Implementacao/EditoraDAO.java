package Implementacao;

import entidades.Editora;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EditoraDAO {
    private Connection conexao;

    public EditoraDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void salvar(Editora editora) throws SQLException {
        String sql = "INSERT INTO Editora (Nome, Endereco) VALUES (?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, editora.getNome());
            stmt.setString(2, editora.getEndereco());
            stmt.executeUpdate();
        }
    }

    public void atualizar(Editora editora) throws SQLException {
        String sql = "UPDATE Editora SET Nome = ?, Endereco = ? WHERE ID_Editora = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, editora.getNome());
            stmt.setString(2, editora.getEndereco());
            stmt.setInt(3, editora.getIdEditora());
            stmt.executeUpdate();
        }
    }

    public List<Editora> listar() throws SQLException {
        List<Editora> editoras = new ArrayList<>();
        String sql = "SELECT * FROM Editora";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Editora editora = new Editora(
                    rs.getInt("ID_Editora"),
                    rs.getString("Nome"),
                    rs.getString("Endereco")
                );
                editoras.add(editora);
            }
        }
        return editoras;
    }

    public Editora buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Editora WHERE ID_Editora = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
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

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM Editora WHERE ID_Editora = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}