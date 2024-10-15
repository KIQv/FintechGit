package database;

import dao.ContaDAO;
import dao.DespesaDAO;
import dao.ReceitaDAO;
import model.Conta;
import transaction.Despesa;
import transaction.Receita;

import java.util.List;
import java.util.Scanner;

public class TesteConexaoCriarContaComMovimento {
    public static void main(String[] args) {
        // Criar instâncias dos DAOs
        ContaDAO contaDAO = new ContaDAO();
        DespesaDAO despesaDAO = new DespesaDAO();
        ReceitaDAO receitaDAO = new ReceitaDAO();

        // Scanner para entrada de dados do usuário
        Scanner scanner = new Scanner(System.in);

        // Solicitar cd_usuario para buscar a conta
        System.out.print("Digite o cd_usuario para mexer na conta: ");
        int cdUsuario = scanner.nextInt();

        // Obter a conta correspondente ao usuário
        List<Conta> contas = contaDAO.getAll();
        Conta contaParaUso = null;

        for (Conta conta : contas) {
            if (conta.getCdUsuario() == cdUsuario) {
                contaParaUso = conta;
                break;
            }
        }

        if (contaParaUso == null) {
            System.out.println("Conta não encontrada para o cd_usuario: " + cdUsuario);
            return; // Sai do programa se não encontrar a conta
        }

        // Criar e inserir uma despesa
        System.out.print("Digite o valor da despesa: ");
        double valorDespesa = scanner.nextDouble();
        System.out.print("Digite a descrição da despesa: ");
        scanner.nextLine(); // Consumir a nova linha
        String descricaoDespesa = scanner.nextLine();

        Despesa novaDespesa = new Despesa(3, valorDespesa, descricaoDespesa, contaParaUso.getCdConta()); // ID da despesa e ID da conta
        despesaDAO.insert(novaDespesa); // Chama o método insert apenas com o objeto Despesa

        // Criar e inserir uma receita
        System.out.print("Digite o valor da receita: ");
        double valorReceita = scanner.nextDouble();
        System.out.print("Digite a descrição da receita: ");
        scanner.nextLine(); // Consumir a nova linha
        String descricaoReceita = scanner.nextLine();

        Receita novaReceita = new Receita(3, valorReceita, descricaoReceita, contaParaUso.getCdConta()); // ID da receita e ID da conta
        receitaDAO.insert(novaReceita); // Chama o método insert apenas com o objeto Receita

        // Validar as inserções
        contas = contaDAO.getAll();
        List<Despesa> despesas = despesaDAO.getAll();
        List<Receita> receitas = receitaDAO.getAll();

        System.out.println("\nContas cadastradas:");
        for (Conta conta : contas) {
            System.out.println("ID: " + conta.getCdConta() + ", Banco: " + conta.getTipoConta() + ", Saldo: " + conta.getSaldo());
        }

        System.out.println("\nDespesas cadastradas:");
        for (Despesa despesa : despesas) {
            System.out.println("ID: " + despesa.getCdDespesa() + ", Valor: " + despesa.getValor() + ", Descrição: " + despesa.getDescricao());
        }

        System.out.println("\nReceitas cadastradas:");
        for (Receita receita : receitas) {
            System.out.println("ID: " + receita.getCdReceita() + ", Valor: " + receita.getValor() + ", Descrição: " + receita.getDescricao());
        }

        // Fechar o scanner
        scanner.close();
    }
}
