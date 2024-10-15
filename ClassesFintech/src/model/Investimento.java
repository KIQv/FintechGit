package model;

public abstract class Investimento {
    private String idInvestimento;
    private String nomeInvestimento;
    private double valorInvestido;
    private double valorAtual;
    private double taxaRetornoEsperada;

    public Investimento(String idInvestimento, String nomeInvestimento, double valorInvestido, double taxaRetornoEsperada) {
        this.idInvestimento = idInvestimento;
        this.nomeInvestimento = nomeInvestimento;
        this.valorInvestido = valorInvestido;
        this.taxaRetornoEsperada = taxaRetornoEsperada;
        this.valorAtual = valorInvestido;
    }

    public abstract void calcularValorAtual();

    @Override
    public String toString() {
        return "Investimento{" +
                "idInvestimento='" + idInvestimento + '\'' +
                ", nomeInvestimento='" + nomeInvestimento + '\'' +
                ", valorInvestido=" + valorInvestido +
                ", valorAtual=" + valorAtual +
                ", taxaRetornoEsperada=" + taxaRetornoEsperada +
                '}';
    }
}
