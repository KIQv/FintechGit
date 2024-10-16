package teste;

import dao.ReceitaDAO;
import dao.UsuarioDAO;
import dao.ContaDAO; // Importa a classe ContaDAO
import transaction.Receita;
import model.Usuario;
import model.Conta; // Importa a classe Conta

import java.util.List;
import java.util.Scanner;

public class TesteReceita {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ReceitaDAO receitaDAO = new ReceitaDAO();
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
            System.out.println("Cadastro da Receita");
            System.out.print("Valor: ");
            double valor = scanner.nextDouble();
            scanner.nextLine(); // Consumir a nova linha
            System.out.print("Descrição: ");
            String descricao = scanner.nextLine();

            // Calcular o próximo ID para a receita
            int nextIdReceita = receitaDAO.getAll().size() + 1;

            // Criar a Receita sem definir CD_RECEITA
            Receita receita = new Receita(0, valor, descricao, contaSelecionada.getCdConta()); // Passando 0 ou um valor padrão para CD_RECEITA
            receitaDAO.insert(receita);
            System.out.println("Receita inserida com sucesso!");

            // Atualizar o saldo da conta
            double novoSaldo = contaSelecionada.getSaldo() + valor;
            contaSelecionada.setSaldo(novoSaldo);
            contaDAO.atualizarSaldo(contaSelecionada.getCdConta(), valor); // Atualiza o saldo da conta

            // Pergunta se deseja cadastrar outra receita
            System.out.print("Deseja cadastrar outra receita? (s/n): ");
            String resposta = scanner.nextLine();
            continuar = resposta.equalsIgnoreCase("s");
        }

        scanner.close();
    }
}
