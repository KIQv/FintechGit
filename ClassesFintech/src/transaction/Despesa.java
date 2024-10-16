package transaction;

public class Despesa {
    private int cdDespesa;
    private double valor;
    private String descricao;
    private int cdConta; // ID da conta associada

    // Construtor
    public Despesa(int cdDespesa, double valor, String descricao, int cdConta) {
        this.cdDespesa = cdDespesa;
        this.valor = valor;
        this.descricao = descricao;
        this.cdConta = cdConta;
    }

    // Getters e Setters
    public int getCdDespesa() {
        return cdDespesa;
    }

    public void setCdDespesa(int cdDespesa) {
        this.cdDespesa = cdDespesa;
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

    @Override
    public String toString() {
        return "Despesa{" +
                "cdDespesa=" + cdDespesa +
                ", valor=" + valor +
                ", descricao='" + descricao + '\'' +
                ", cdConta=" + cdConta +
                '}';
    }
}
