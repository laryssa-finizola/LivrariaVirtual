package entidades;

public class Editora {
    private int idEditora;
    private String nome;
    private String endereco;
    
 // Construtor com parâmetros
    public Editora(int idEditora, String nome, String endereco) {
        this.idEditora = idEditora;
        this.nome = nome;
        this.endereco = endereco;
    }

    
	// Getter para idEditora
    public int getIdEditora() {
        return idEditora;
    }

    // Setter para idEditora
    public void setIdEditora(int idEditora) {
        this.idEditora = idEditora;
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
