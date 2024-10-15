package controller;

import java.util.Scanner;

public class Login {
    private String cpf;
    private String senhaDeSeisDigitos;
    private String nomeDoUsuario;

    public Login(String cpf, String nomeDoUsuario) {
        this.cpf = cpf;
        this.nomeDoUsuario = nomeDoUsuario;
    }

    public void loginSimples() {
        System.out.println("Bem-vindo à nossa Fintech, " + nomeDoUsuario + ".");
    }

    public boolean autenticarUsuario(String senha) {
        return senha.equals(senhaDeSeisDigitos);
    }

    public static boolean verificaContaNoSistema(String cpf) {
        return true; // Implementar lógica de verificação
    }

    public static void processarLogin() {
        System.out.println("Bem-vindo! Faça login ou cadastro inserindo seu CPF");
        Scanner scanner = new Scanner(System.in);

        String cpf = scanner.nextLine();

        if (verificaContaNoSistema(cpf)) {
            System.out.println("Verificamos que já possui uma conta conosco. Siga para o login.");
            System.out.println("Digite sua senha:");
            String senha = scanner.nextLine();

            Login login = new Login(cpf, "Usuário Teste");

            if (login.autenticarUsuario(senha)) {
                login.loginSimples();
                System.out.println("Login efetuado com sucesso!");
            } else {
                System.out.println("Senha incorreta!");
            }
        } else {
            System.out.println("CPF não encontrado no sistema.");
        }

        scanner.close();
    }
}
