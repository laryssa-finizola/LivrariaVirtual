package entidades;

import java.util.List;

public class Livro {
    private String isbn;
    private String titulo;
    private double preco;
    private int ano;
    private String categoria;
    private List<Autor> autores; // Lista de autores do livro
    private Editora editora;

 // Construtor com parâmetros
    public Livro(String isbn, String titulo, double preco, int ano, String categoria, List<Autor> autores, Editora editora) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.preco = preco;
        this.ano = ano;
        this.categoria = categoria;
        this.autores = autores;
        this.editora = editora;
    }
    
    // Getter para isbn
    public String getIsbn() {
        return isbn;
    }

    // Setter para isbn
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    // Getter para titulo
    public String getTitulo() {
        return titulo;
    }

    // Setter para titulo
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    // Getter para preco
    public double getPreco() {
        return preco;
    }

    // Setter para preco
    public void setPreco(double preco) {
        this.preco = preco;
    }

    // Getter para ano
    public int getAno() {
        return ano;
    }

    // Setter para ano
    public void setAno(int ano) {
        this.ano = ano;
    }

    // Getter para categoria
    public String getCategoria() {
        return categoria;
    }

    // Setter para categoria
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    // Getter para autores
    public List<Autor> getAutores() {
        return autores;
    }

    // Setter para autores
    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    // Getter para editora
    public Editora getEditora() {
        return editora;
    }

    // Setter para editora
    public void setEditora(Editora editora) {
        this.editora = editora;
    }
}
