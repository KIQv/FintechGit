package model;

public class Usuario {
    private int cdUsuario;            // Código do usuário
    private String nomeUsuario;       // Nome do usuário
    private String sobrenomeUsuario;  // Sobrenome do usuário
    private String email;             // Email do usuário
    private String senha;             // Senha do usuário

    // Construtor
    public Usuario(int cdUsuario, String nomeUsuario, String sobrenomeUsuario, String email, String senha) {
        this.cdUsuario = cdUsuario;
        this.nomeUsuario = nomeUsuario;
        this.sobrenomeUsuario = sobrenomeUsuario;
        this.email = email;
        this.senha = senha;
    }

    // Métodos getters
    public int getCdUsuario() {
        return cdUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public String getSobrenomeUsuario() {
        return sobrenomeUsuario;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
