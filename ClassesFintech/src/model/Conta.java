package model;

public class Conta {
    private int cdConta;              // Código da conta
    private String tipoConta;         // Tipo de conta (corrente, poupança, etc.)
    private double saldo;             // Saldo da conta
    private int cdUsuario;            // Código do usuário associado

    // Construtor
    public Conta(int cdConta, String tipoConta, double saldo, int cdUsuario) {
        this.cdConta = cdConta;
        this.tipoConta = tipoConta;
        this.saldo = saldo;
        this.cdUsuario = cdUsuario;
    }

    // Métodos getters
    public int getCdConta() {
        return cdConta;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public int getCdUsuario() {
        return cdUsuario;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "Conta{" +
                "cdConta=" + cdConta +
                ", tipoConta='" + tipoConta + '\'' +
                ", saldo=" + saldo +
                ", cdUsuario=" + cdUsuario +
                '}';
    }
}
