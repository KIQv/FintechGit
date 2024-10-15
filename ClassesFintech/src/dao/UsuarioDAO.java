package dao;

import model.Usuario;
import database.ConexaoBanco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    // Método para inserir um novo usuário
    public void insert(Usuario usuario) {
        String sql = "INSERT INTO USUARIO (cd_usuario, nome_usuario, sobrenome_usuario, email, senha) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, usuario.getCdUsuario());
            pstmt.setString(2, usuario.getNomeUsuario());
            pstmt.setString(3, usuario.getSobrenomeUsuario());
            pstmt.setString(4, usuario.getEmail());
            pstmt.setString(5, usuario.getSenha());

            pstmt.executeUpdate();
            System.out.println("Usuário inserido com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir usuário: " + e.getMessage());
        }
    }

    // Método para obter todos os usuários
    public List<Usuario> getAll() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM USUARIO";

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("cd_usuario"),
                        rs.getString("nome_usuario"),
                        rs.getString("sobrenome_usuario"),
                        rs.getString("email"),
                        rs.getString("senha")
                );
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar usuários: " + e.getMessage());
        }
        return usuarios;
    }
}
