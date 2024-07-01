package entidades;

public class Pagamento {
    private int idPagamento;
    private String tipo; // Pix, Cartão de Crédito, Boleto Bancário
    private Pedido pedido;

 // Construtor com parâmetros
    public Pagamento(int idPagamento, String tipo, Pedido pedido) {
        this.idPagamento = idPagamento;
        this.tipo = tipo;
        this.pedido = pedido;
    }
    
    // Getter para idPagamento
    public int getIdPagamento() {
        return idPagamento;
    }

    // Setter para idPagamento
    public void setIdPagamento(int idPagamento) {
        this.idPagamento = idPagamento;
    }

    // Getter para tipo
    public String getTipo() {
        return tipo;
    }

    // Setter para tipo
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    // Getter para pedido
    public Pedido getPedido() {
        return pedido;
    }

    // Setter para pedido
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
