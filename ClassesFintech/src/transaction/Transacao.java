package transaction;

import model.Conta;

public abstract class Transacao {
    private double valor;
    private String descricao;

    public Transacao(double valor, String descricao) {
        this.valor = valor;
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public abstract void aplicarTransacao(Conta conta);
}
