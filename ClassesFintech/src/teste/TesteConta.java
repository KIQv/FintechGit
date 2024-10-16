package teste;

import dao.ContaDAO;
import dao.UsuarioDAO; // Importando o UsuarioDAO
import model.Conta;
import model.Usuario;

import java.util.List;
import java.util.Scanner;

public class TesteConta {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ContaDAO contaDAO = new ContaDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        // Listar usuários existentes
        List<Usuario> usuarios = usuarioDAO.getAll();
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado. Cadastre um usuário antes de criar uma conta.");
            return;
        }

        System.out.println("Usuários cadastrados:");
        usuarios.forEach(usuario ->
                System.out.println("ID: " + usuario.getCdUsuario() + " - " + usuario.getNomeUsuario() + " " + usuario.getSobrenomeUsuario())
        );

        // Calcular o próximo ID para a conta
        int nextId = contaDAO.getAll().size() + 1;

        boolean continuar = true;
        while (continuar) {
            System.out.println("Cadastro da Conta " + nextId);
            System.out.print("Tipo da Conta: ");
            String tipo = scanner.nextLine();
            System.out.print("Saldo: ");
            double saldo = scanner.nextDouble();
            System.out.print("ID do Usuário: ");
            int cdUsuario = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            // Verifica se o ID do usuário existe
            boolean usuarioExistente = usuarios.stream().anyMatch(u -> u.getCdUsuario() == cdUsuario);
            if (!usuarioExistente) {
                System.out.println("Usuário com ID " + cdUsuario + " não encontrado. Tente novamente.");
                continue;
            }

            Conta conta = new Conta(nextId, tipo, saldo, cdUsuario);
            contaDAO.insert(conta);
            System.out.println("Conta cadastrada: " + conta.getTipoConta() + " - Saldo: " + conta.getSaldo());

            // Perguntar se deseja cadastrar mais contas
            System.out.print("Deseja cadastrar outra conta? (s/n): ");
            String resposta = scanner.nextLine();
            continuar = resposta.equalsIgnoreCase("s");

            // Atualizar o próximo ID
            if (continuar) {
                nextId++;
            }
        }

        System.out.println("Todas as contas cadastradas:");
        contaDAO.getAll().forEach(conta ->
                System.out.println("ID: " + conta.getCdConta() + " - " + conta.getTipoConta() + " - Saldo: " + conta.getSaldo())
        );

        scanner.close();
    }
}
