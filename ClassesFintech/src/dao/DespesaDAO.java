package dao;

import transaction.Despesa;
import database.ConexaoBanco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DespesaDAO {
    public void insert(Despesa despesa) {
        String sql = "INSERT INTO despesa (cd_despesa, valor, descricao, cd_conta) VALUES (seq_cd_despesa.NEXTVAL, ?, ?, ?)";
        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, despesa.getValor());
            stmt.setString(2, despesa.getDescricao());
            stmt.setInt(3, despesa.getCdConta());
            stmt.executeUpdate();
            System.out.println("Despesa inserida com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao inserir despesa: " + e.getMessage());
        }
    }

    public List<Despesa> getAll() {
        List<Despesa> despesas = new ArrayList<>();
        String sql = "SELECT * FROM DESPESA";
        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Despesa despesa = new Despesa(
                        rs.getInt("cd_despesa"),
                        rs.getDouble("valor"),
                        rs.getString("descricao"),
                        rs.getInt("cd_conta")
                );
                despesas.add(despesa);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao consultar despesas: " + e.getMessage());
        }
        return despesas;
    }
}
