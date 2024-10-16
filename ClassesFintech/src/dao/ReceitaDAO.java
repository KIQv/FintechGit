package dao;

import transaction.Receita;
import database.ConexaoBanco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReceitaDAO {
    public void insert(Receita receita) {
        String sql = "INSERT INTO receita (cd_receita, valor, descricao, cd_conta) VALUES (seq_cd_receita.NEXTVAL, ?, ?, ?)";
        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, receita.getValor());
            stmt.setString(2, receita.getDescricao());
            stmt.setInt(3, receita.getCdConta());
            stmt.executeUpdate();
            System.out.println("Receita inserida com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao inserir receita: " + e.getMessage());
        }
    }

    public List<Receita> getAll() {
        List<Receita> receitas = new ArrayList<>();
        String sql = "SELECT * FROM RECEITA";
        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Receita receita = new Receita(
                        rs.getInt("cd_receita"),
                        rs.getDouble("valor"),
                        rs.getString("descricao"),
                        rs.getInt("cd_conta")
                );
                receitas.add(receita);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao consultar receitas: " + e.getMessage());
        }
        return receitas;
    }
}
