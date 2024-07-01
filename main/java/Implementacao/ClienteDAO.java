package Implementacao; 
import entidades.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private Connection conexao;

    public ClienteDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void salvar(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO Cliente (Nome, Endereco) VALUES (?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEndereco());
            stmt.executeUpdate();
        }
    }

    public void atualizar(Cliente cliente) throws SQLException {
        String sql = "UPDATE Cliente SET Nome = ?, Endereco = ? WHERE ID_Cliente = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEndereco());
            stmt.setInt(3, cliente.getIdCliente());
            stmt.executeUpdate();
        }
    }

    public Cliente buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Cliente WHERE ID_Cliente = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Cliente(
                        rs.getInt("ID_Cliente"),
                        rs.getString("Nome"),
                        rs.getString("Endereco")
                    );
                }
            }
        }
        return null;
    }

    public List<Cliente> listar() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM Cliente";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Cliente cliente = new Cliente(
                    rs.getInt("ID_Cliente"),
                    rs.getString("Nome"),
                    rs.getString("Endereco")
                );
                clientes.add(cliente);
            }
        }
        return clientes;
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM Cliente WHERE ID_Cliente = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
