package transaction;

public class Receita {
    private int cdReceita;
    private double valor;
    private String descricao;
    private int cdConta; // ID da conta associada

    // Construtor
    public Receita(int cdReceita, double valor, String descricao, int cdConta) {
        this.cdReceita = cdReceita;
        this.valor = valor;
        this.descricao = descricao;
        this.cdConta = cdConta;
    }

    // Getters e Setters
    public int getCdReceita() {
        return cdReceita;
    }

    public void setCdReceita(int cdReceita) {
        this.cdReceita = cdReceita;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCdConta() {
        return cdConta;
    }

    public void setCdConta(int cdConta) {
        this.cdConta = cdConta;
    }
}
