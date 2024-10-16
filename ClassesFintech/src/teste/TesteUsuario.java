package teste;

import dao.UsuarioDAO;
import model.Usuario;

import java.util.List;
import java.util.Scanner;

public class TesteUsuario {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        // Obter todos os usuários cadastrados para determinar o próximo ID
        List<Usuario> usuariosExistentes = usuarioDAO.getAll();
        int nextId = usuariosExistentes.isEmpty() ? 1 : usuariosExistentes.size() + 1;

        boolean continuar = true;
        while (continuar) {
            System.out.println("Cadastro do Usuário " + nextId);
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Sobrenome: ");
            String sobrenome = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Senha: ");
            String senha = scanner.nextLine();

            Usuario usuario = new Usuario(nextId, nome, sobrenome, email, senha);
            usuarioDAO.insert(usuario);
            System.out.println("Usuário cadastrado: " + usuario.getNomeUsuario() + " " + usuario.getSobrenomeUsuario());

            // Perguntar se deseja cadastrar mais usuários
            System.out.print("Deseja cadastrar outro usuário? (s/n): ");
            String resposta = scanner.nextLine();
            continuar = resposta.equalsIgnoreCase("s");

            // Atualizar o próximo ID
            if (continuar) {
                nextId++;
            }
        }

        System.out.println("Todos os usuários cadastrados no sistema:");
        usuarioDAO.getAll().forEach(usuario ->
                System.out.println(usuario.getNomeUsuario() + " " + usuario.getSobrenomeUsuario()));

        scanner.close();
    }
}
