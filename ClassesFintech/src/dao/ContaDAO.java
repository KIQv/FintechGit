package dao;

import model.Conta;
import database.ConexaoBanco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContaDAO {
    // Método para inserir uma nova conta
    public void insert(Conta conta) {
        String sql = "INSERT INTO CONTA (cd_conta, tipo_conta, saldo, cd_usuario) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, conta.getCdConta());
            pstmt.setString(2, conta.getTipoConta());
            pstmt.setDouble(3, conta.getSaldo());
            pstmt.setInt(4, conta.getCdUsuario());

            pstmt.executeUpdate();
            System.out.println("Conta inserida com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir conta: " + e.getMessage());
        }
    }

    public void update(Conta conta) {
        String sql = "UPDATE CONTA SET saldo = ? WHERE cd_conta = ?";
        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, conta.getSaldo());
            stmt.setInt(2, conta.getCdConta());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Tratar o erro adequadamente
        }
    }

    // Método para obter uma conta pelo ID
    public Conta getById(int id) {
        Conta conta = null;
        String sql = "SELECT * FROM CONTA WHERE cd_conta = ?";

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                conta = new Conta(
                        rs.getInt("cd_conta"),
                        rs.getString("tipo_conta"),
                        rs.getDouble("saldo"),
                        rs.getInt("cd_usuario")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar conta: " + e.getMessage());
        }

        return conta;
    }

    // Método para obter todas as contas
    public List<Conta> getAll() {
        List<Conta> contas = new ArrayList<>();
        String sql = "SELECT * FROM CONTA";

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Conta conta = new Conta(
                        rs.getInt("cd_conta"),
                        rs.getString("tipo_conta"),
                        rs.getDouble("saldo"),
                        rs.getInt("cd_usuario") // ID do usuário associado
                );
                contas.add(conta);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar contas: " + e.getMessage());
        }
        return contas;
    }
}
