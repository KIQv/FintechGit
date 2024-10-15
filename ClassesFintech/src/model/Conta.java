package model;

public class Conta {
    private int cdConta;
    private String tipoConta; // Tipo da conta, ex: "Nubank"
    private double saldo;
    private int cdUsuario; // ID do usuário associado

    // Construtor
    public Conta(int cdConta, String tipoConta, double saldo, int cdUsuario) {
        this.cdConta = cdConta;
        this.tipoConta = tipoConta;
        this.saldo = saldo;
        this.cdUsuario = cdUsuario;
    }

    // Getters e Setters
    public int getCdConta() {
        return cdConta;
    }

    public void setCdConta(int cdConta) {
        this.cdConta = cdConta;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getCdUsuario() {
        return cdUsuario;
    }

    public void setCdUsuario(int cdUsuario) {
        this.cdUsuario = cdUsuario;
    }
}
