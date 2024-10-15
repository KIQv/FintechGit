package dao;

import transaction.Receita;
import database.ConexaoBanco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReceitaDAO {
    // Método para inserir uma nova receita
    public void insert(Receita receita) {
        String sql = "INSERT INTO RECEITA (cd_receita, valor, descricao, cd_conta) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, receita.getCdReceita());
            pstmt.setDouble(2, receita.getValor());
            pstmt.setString(3, receita.getDescricao());
            pstmt.setInt(4, receita.getCdConta());

            pstmt.executeUpdate();
            System.out.println("Receita inserida com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir receita: " + e.getMessage());
        }
    }

    // Método para obter todas as receitas
    public List<Receita> getAll() {
        List<Receita> receitas = new ArrayList<>();
        String sql = "SELECT * FROM RECEITA";

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Receita receita = new Receita(
                        rs.getInt("cd_receita"),
                        rs.getDouble("valor"),
                        rs.getString("descricao"),
                        rs.getInt("cd_conta") // ID da conta associada
                );
                receitas.add(receita);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar receitas: " + e.getMessage());
        }
        return receitas;
    }
}
