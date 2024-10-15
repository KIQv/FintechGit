package model;

import java.util.Date;

public class Emprestimo {
    private int idEmprestimo;
    private double valor;
    private double taxaJuros;
    private int numeroParcelas;
    private Date dataInicio;

    public Emprestimo(int idEmprestimo, double valor, int numeroParcelas, Date dataInicio) {
        this.idEmprestimo = idEmprestimo;
        this.valor = valor;
        this.numeroParcelas = numeroParcelas;
        this.dataInicio = dataInicio;
    }

    public void detalhesEmprestimo() {
        System.out.println("Detalhes do empréstimo");
        System.out.println("Id: " + idEmprestimo);
        System.out.println("Valor: " + valor);
        System.out.println("Número de Parcelas: " + numeroParcelas);
        System.out.println("Data: " + dataInicio);
    }

    // Métodos de cálculo omitidos para simplicidade
}
