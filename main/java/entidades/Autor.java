package entidades;

public class Autor {
    private int idAutor;
    private String nome;
 // Construtor com parâmetros
    public Autor(int idAutor, String nome) {
        this.idAutor = idAutor;
        this.nome = nome;
    }
    // Getter para idAutor
    public int getIdAutor() {
        return idAutor;
    }

    // Setter para idAutor
    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    // Getter para nome
    public String getNome() {
        return nome;
    }

    // Setter para nome
    public void setNome(String nome) {
        this.nome = nome;
    }
}
