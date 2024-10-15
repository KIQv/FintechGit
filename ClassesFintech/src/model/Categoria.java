package model;

public class Categoria {
    private String nome;
    private String descricao;

    public Categoria() {}

    public Categoria(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public void adicionarCategoria() {
        System.out.println("Adicionando categoria: " + nome);
    }

    public void exibirInfoCategoria() {
        System.out.println("Exibindo informações da categoria: " + nome);
    }
}
