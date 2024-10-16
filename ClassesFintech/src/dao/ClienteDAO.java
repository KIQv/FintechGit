package dao;

import model.Cliente;
import database.ConexaoBanco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    // Método para inserir um novo cliente
    public void insert(Cliente cliente) {
        String sql = "INSERT INTO CLIENTE (cd_cliente, cd_usuario, tipo_conta, saldo) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, cliente.getCdCliente());
            pstmt.setInt(2, cliente.getCdUsuario());
            pstmt.setString(3, cliente.getTipoConta());
            pstmt.setDouble(4, cliente.getSaldo());

            pstmt.executeUpdate();
            System.out.println("Cliente inserido com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir cliente: " + e.getMessage());
        }
    }

    // Método para obter todos os clientes
    public List<Cliente> getAll() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM CLIENTE";

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("cd_cliente"),
                        rs.getInt("cd_usuario"),
                        rs.getString("tipo_conta"),
                        rs.getDouble("saldo")
                );
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar clientes: " + e.getMessage());
        }
        return clientes;
    }

    public Cliente getByUsuarioId(int cdUsuario) {
        String sql = "SELECT * FROM CLIENTE WHERE cd_usuario = ?";
        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, cdUsuario);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Cliente(
                        rs.getInt("cd_cliente"),
                        rs.getInt("cd_usuario"),
                        rs.getString("tipo_conta"),
                        rs.getDouble("saldo")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao recuperar cliente: " + e.getMessage());
        }
        return null; // Retorna null se não encontrar o cliente
    }

    public void atualizaSaldo(int cdCliente, double valor) {
        String sql = "UPDATE CLIENTE SET saldo = saldo + ? WHERE cd_cliente = ?";
        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, valor);
            pstmt.setInt(2, cdCliente);
            pstmt.executeUpdate();
            System.out.println("Saldo atualizado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar saldo: " + e.getMessage());
        }
    }

}
