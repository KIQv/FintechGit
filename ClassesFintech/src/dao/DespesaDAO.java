package dao;

import transaction.Despesa;
import database.ConexaoBanco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DespesaDAO {
    // Método para inserir uma nova despesa
    public void insert(Despesa despesa) {
        String sql = "INSERT INTO DESPESA (cd_despesa, valor, descricao, cd_conta) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, despesa.getCdDespesa());
            pstmt.setDouble(2, despesa.getValor());
            pstmt.setString(3, despesa.getDescricao());
            pstmt.setInt(4, despesa.getCdConta());

            pstmt.executeUpdate();
            System.out.println("Despesa inserida com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir despesa: " + e.getMessage());
        }
    }

    // Método para obter todas as despesas
    public List<Despesa> getAll() {
        List<Despesa> despesas = new ArrayList<>();
        String sql = "SELECT * FROM DESPESA";

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Despesa despesa = new Despesa(
                        rs.getInt("cd_despesa"),
                        rs.getDouble("valor"),
                        rs.getString("descricao"),
                        rs.getInt("cd_conta") // ID da conta associada
                );
                despesas.add(despesa);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar despesas: " + e.getMessage());
        }
        return despesas;
    }
}
