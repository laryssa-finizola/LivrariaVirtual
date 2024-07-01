package Implementacao;

import entidades.Autor;
import entidades.Cliente;
import entidades.Editora;
import entidades.Livro;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import ConexaoBD.Conexao;

public class Main {
    public static void main(String[] args) {
        try (Connection conexao = Conexao.getConnection()) {
            System.out.println("Conexão estabelecida com sucesso!");

            AutorDAO autorDAO = new AutorDAO(conexao);
            ClienteDAO clienteDAO = new ClienteDAO(conexao);
            EditoraDAO editoraDAO = new EditoraDAO(conexao);
            LivroDAO livroDAO = new LivroDAO(conexao);

            // Exemplo de uso do AutorDAO
            Autor autorNovo = new Autor(0, "João da Silva");
            autorDAO.salvar(autorNovo);

            Autor autorRecuperado = autorDAO.buscarPorId(1);
            if (autorRecuperado != null) {
                System.out.println("Autor encontrado: " + autorRecuperado.getNome());
            } else {
                System.out.println("Autor não encontrado.");
            }

            // Exemplo de uso do ClienteDAO
            Cliente clienteNovo = new Cliente(0, "Maria Oliveira", "Rua A, 123");
            clienteDAO.salvar(clienteNovo);

            List<Cliente> clientes = clienteDAO.listar();
            System.out.println("Clientes cadastrados:");
            for (Cliente c : clientes) {
                System.out.println(c.getNome() + " - " + c.getEndereco());
            }

            // Exemplo de uso do EditoraDAO
            Editora editoraNova = new Editora(0, "Editora XYZ", "Av. B, 456");
            editoraDAO.salvar(editoraNova);

            // Exemplo de uso do LivroDAO
            List<Autor> autoresDoLivro = autorDAO.listar();
            Editora editoraDoLivro = editoraDAO.buscarPorId(1);
            Livro livroNovo = new Livro("123456789", "Java para Iniciantes", 49.99, 2023, "Programação", autoresDoLivro, editoraDoLivro);
            livroDAO.salvar(livroNovo);

            Livro livroRecuperado = livroDAO.buscarPorIsbn("123456789");
            if (livroRecuperado != null) {
                System.out.println("Livro encontrado: " + livroRecuperado.getTitulo());
                System.out.println("Autores:");
                for (Autor autor : livroRecuperado.getAutores()) {
                    System.out.println("- " + autor.getNome());
                }
                System.out.println("Editora: " + livroRecuperado.getEditora().getNome());
            } else {
                System.out.println("Livro não encontrado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro de conexão com o banco de dados: " + e.getMessage());
        }
    }
}
