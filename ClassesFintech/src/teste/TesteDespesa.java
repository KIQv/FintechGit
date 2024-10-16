package teste;

import dao.DespesaDAO;
import dao.UsuarioDAO;
import dao.ContaDAO; // Importa a classe ContaDAO
import transaction.Despesa;
import model.Usuario;
import model.Conta; // Importa a classe Conta

import java.util.List;
import java.util.Scanner;

public class TesteDespesa {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        DespesaDAO despesaDAO = new DespesaDAO();
        ContaDAO contaDAO = new ContaDAO(); // Instância da classe ContaDAO

        // Listar usuários existentes
        System.out.print("Digite seu email: ");
        String email = scanner.nextLine();
        System.out.print("Digite sua senha: ");
        String senha = scanner.nextLine();

        // Verifica se o usuário existe
        List<Usuario> usuarios = usuarioDAO.getAll();
        Usuario usuarioLogado = null;
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
                usuarioLogado = usuario;
                break;
            }
        }

        if (usuarioLogado == null) {
            System.out.println("Usuário não encontrado ou senha incorreta.");
            return;
        }

        // Obter contas do usuário logado
        List<Conta> contas = contaDAO.getAll(); // Usar a instância da ContaDAO
        int cdUsuarioLogado = usuarioLogado.getCdUsuario(); // Variável temporária para evitar o erro

        List<Conta> contasUsuario = contas.stream()
                .filter(conta -> conta.getCdUsuario() == cdUsuarioLogado)
                .toList();

        if (contasUsuario.isEmpty()) {
            System.out.println("Nenhuma conta disponível para o usuário.");
            return;
        }

        // Seleciona automaticamente a primeira conta
        Conta contaSelecionada = contasUsuario.get(0);
        System.out.println("Conta selecionada automaticamente: ID " + contaSelecionada.getCdConta());

        boolean continuar = true;
        while (continuar) {
            System.out.println("Cadastro da Despesa");
            System.out.print("Valor: ");
            double valor = scanner.nextDouble();
            scanner.nextLine(); // Consumir a nova linha
            System.out.print("Descrição: ");
            String descricao = scanner.nextLine();

            // Criar a Despesa sem definir CD_DESPESA
            Despesa despesa = new Despesa(0, valor, descricao, contaSelecionada.getCdConta()); // Passando 0 ou um valor padrão para CD_DESPESA
            despesaDAO.insert(despesa);
            System.out.println("Despesa inserida com sucesso!");

            // Atualizar o saldo da conta
            double novoSaldo = contaSelecionada.getSaldo() - valor;
            contaSelecionada.setSaldo(novoSaldo);
            contaDAO.atualizarSaldo(contaSelecionada.getCdConta(), -valor); // Atualiza o saldo da conta

            // Pergunta se deseja cadastrar outra despesa
            System.out.print("Deseja cadastrar outra despesa? (s/n): ");
            String resposta = scanner.nextLine();
            continuar = resposta.equalsIgnoreCase("s");
        }

        scanner.close();
    }
}
