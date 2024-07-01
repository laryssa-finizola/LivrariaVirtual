package entidades;

import java.util.Date;
import java.util.List;

public class Pedido {
    private int idPedido;
    private Date data;
    private Cliente cliente;
    private List<Livro> livros; // Lista de livros no pedido
    
 // Construtor com parâmetros
    public Pedido(int idPedido, Date data, Cliente cliente, List<Livro> livros) {
        this.idPedido = idPedido;
        this.data = data;
        this.cliente = cliente;
        this.livros = livros;
    }
    // Getter para idPedido
    public int getIdPedido() {
        return idPedido;
    }

    // Setter para idPedido
    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    // Getter para data
    public Date getData() {
        return data;
    }

    // Setter para data
    public void setData(Date data) {
        this.data = data;
    }

    // Getter para cliente
    public Cliente getCliente() {
        return cliente;
    }

    // Setter para cliente
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    // Getter para livros
    public List<Livro> getLivros() {
        return livros;
    }

    // Setter para livros
    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }
}
