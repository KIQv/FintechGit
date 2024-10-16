package dao;

import model.Usuario;
import database.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    public void insert(Usuario usuario) {
        String sql = "INSERT INTO USUARIO (cd_usuario, nome_usuario, sobrenome_usuario, email, senha) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, usuario.getCdUsuario());
            stmt.setString(2, usuario.getNomeUsuario());
            stmt.setString(3, usuario.getSobrenomeUsuario()); // A linha está correta
            stmt.setString(4, usuario.getEmail());
            stmt.setString(5, usuario.getSenha());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir usuário: " + e.getMessage());
        }
    }


    public List<Usuario> getAll() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM USUARIO";
        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int cdUsuario = rs.getInt("cd_usuario");
                String nomeUsuario = rs.getString("nome_usuario");
                String sobrenomeUsuario = rs.getString("sobrenome_usuario"); // A linha está correta
                String email = rs.getString("email");
                String senha = rs.getString("senha");

                Usuario usuario = new Usuario(cdUsuario, nomeUsuario, sobrenomeUsuario, email, senha);
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao recuperar usuários: " + e.getMessage());
        }
        return usuarios;
    }

    public Usuario getByEmailAndSenha(String email, String senha) {
        String sql = "SELECT * FROM USUARIO WHERE email = ? AND senha = ?";
        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Usuario(
                        rs.getInt("cd_usuario"),
                        rs.getString("nome_usuario"),
                        rs.getString("sobrenome_usuario"),
                        rs.getString("email"),
                        rs.getString("senha")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao recuperar usuário: " + e.getMessage());
        }
        return null; // Retorna null se não encontrar o usuário
    }

}
