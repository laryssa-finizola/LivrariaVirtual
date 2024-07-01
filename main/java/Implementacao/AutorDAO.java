package Implementacao;

import entidades.Autor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutorDAO {
    private Connection conexao;

    public AutorDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void salvar(Autor autor) throws SQLException {
        String sql = "INSERT INTO Autor (Nome) VALUES (?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, autor.getNome());
            stmt.executeUpdate();
        }
    }

    public void atualizar(Autor autor) throws SQLException {
        String sql = "UPDATE Autor SET Nome = ? WHERE ID_Autor = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, autor.getNome());
            stmt.setInt(2, autor.getIdAutor());
            stmt.executeUpdate();
        }
    }

    public Autor buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Autor WHERE ID_Autor = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Autor(
                        rs.getInt("ID_Autor"),
                        rs.getString("Nome")
                    );
                }
            }
        }
        return null;
    }

    public List<Autor> listar() throws SQLException {
        List<Autor> autores = new ArrayList<>();
        String sql = "SELECT * FROM Autor";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Autor autor = new Autor(
                    rs.getInt("ID_Autor"),
                    rs.getString("Nome")
                );
                autores.add(autor);
            }
        }
        return autores;
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM Autor WHERE ID_Autor = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}