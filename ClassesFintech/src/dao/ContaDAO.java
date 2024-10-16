package dao;

import model.Conta;
import database.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContaDAO {
    public void insert(Conta conta) {
        String sql = "INSERT INTO conta (cd_conta, tipo_conta, saldo, cd_usuario) VALUES (?, ?, ?, ?)"; // Usar nomes em minúsculas
        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, conta.getCdConta());
            stmt.setString(2, conta.getTipoConta());
            stmt.setDouble(3, conta.getSaldo());
            stmt.setInt(4, conta.getCdUsuario());
            stmt.executeUpdate();
            System.out.println("Conta inserida com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao inserir conta: " + e.getMessage());
        }
    }

    public void atualizarSaldo(int cdConta, double valor) {
        String sql = "UPDATE conta SET saldo = saldo + ? WHERE cd_conta = ?";
        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, valor);
            stmt.setInt(2, cdConta);
            stmt.executeUpdate();
            System.out.println("Saldo atualizado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar saldo: " + e.getMessage());
        }
    }


    public List<Conta> getAll() {
        List<Conta> contas = new ArrayList<>();
        String sql = "SELECT * FROM conta"; // Usar nomes em minúsculas
        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Conta conta = new Conta(
                        rs.getInt("cd_conta"), // Usar nomes em minúsculas
                        rs.getString("tipo_conta"), // Usar nomes em minúsculas
                        rs.getDouble("saldo"), // Usar nomes em minúsculas
                        rs.getInt("cd_usuario") // Usar nomes em minúsculas
                );
                contas.add(conta);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao consultar contas: " + e.getMessage());
        }
        return contas;
    }
}
