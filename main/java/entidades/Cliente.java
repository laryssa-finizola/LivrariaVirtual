package entidades;

public class Cliente {
    private int idCliente;
    private String nome;
    private String endereco;

    // Construtor com parâmetros
    public Cliente(int idCliente, String nome, String endereco) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.endereco = endereco;
    }

    // Getter para idCliente
    public int getIdCliente() {
        return idCliente;
    }

    // Setter para idCliente
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    // Getter para nome
    public String getNome() {
        return nome;
    }

    // Setter para nome
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter para endereco
    public String getEndereco() {
        return endereco;
    }

    // Setter para endereco
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
