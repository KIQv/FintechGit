package model;

public class Cliente {
    private int cdCliente;
    private int cdUsuario; // Chave estrangeira para USUARIO
    private String tipoConta;
    private double saldo;

    public Cliente(int cdCliente, int cdUsuario, String tipoConta, double saldo) {
        this.cdCliente = cdCliente;
        this.cdUsuario = cdUsuario;
        this.tipoConta = tipoConta;
        this.saldo = saldo;
    }

    // Getters e Setters
    public int getCdCliente() {
        return cdCliente;
    }

    public int getCdUsuario() {
        return cdUsuario;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public double getSaldo() {
        return saldo;
    }
}
