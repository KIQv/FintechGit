package consultas;

import database.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class TesteSelects {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\nEscolha uma das opções abaixo:");
            System.out.println("1. Informações do Usuário e Contas");
            System.out.println("2. Informações do Usuário, Contas e Movimentações");
            System.out.println("3. Informações do Usuário, Contas e Receitas");
            System.out.println("4. Informações do Usuário, Contas e Despesas");
            System.out.println("5. Sair");
            System.out.print("\nOpção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    exibirUsuarioEContas();
                    break;
                case 2:
                    exibirUsuarioContasMovimentacoes();
                    break;
                case 3:
                    exibirUsuarioContasReceitas();
                    break;
                case 4:
                    exibirUsuarioContasDespesas();
                    break;
                case 5:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    public static void exibirUsuarioEContas() {
        String sql = "SELECT u.cd_usuario, u.nome_usuario, u.email, c.cd_conta, c.tipo_conta, c.saldo " +
                "FROM USUARIO u " +
                "JOIN CONTA c ON u.cd_usuario = c.cd_usuario";

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n---------------------------------------------");
            System.out.println("Usuário | Conta");
            System.out.println("---------------------------------------------");

            while (rs.next()) {
                System.out.printf("Usuário ID: %d | Nome: %s | Email: %s\n",
                        rs.getInt("cd_usuario"), rs.getString("nome_usuario"), rs.getString("email"));
                System.out.printf("Conta ID: %d | Tipo: %s | Saldo: %.2f\n\n",
                        rs.getInt("cd_conta"), rs.getString("tipo_conta"), rs.getDouble("saldo"));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao executar consulta: " + e.getMessage());
        }
    }

    public static void exibirUsuarioContasMovimentacoes() {
        String sql = "SELECT u.cd_usuario, u.nome_usuario, c.cd_conta, c.tipo_conta, c.saldo, " +
                "d.valor AS despesa_valor, d.descricao AS despesa_desc, " +
                "r.valor AS receita_valor, r.descricao AS receita_desc " +
                "FROM USUARIO u " +
                "JOIN CONTA c ON u.cd_usuario = c.cd_usuario " +
                "LEFT JOIN DESPESA d ON c.cd_conta = d.cd_conta " +
                "LEFT JOIN RECEITA r ON c.cd_conta = r.cd_conta";

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n---------------------------------------------");
            System.out.println("Usuário | Conta | Movimentações");
            System.out.println("---------------------------------------------");

            while (rs.next()) {
                System.out.printf("Usuário ID: %d | Nome: %s\n", rs.getInt("cd_usuario"), rs.getString("nome_usuario"));
                System.out.printf("Conta ID: %d | Tipo: %s | Saldo: %.2f\n", rs.getInt("cd_conta"), rs.getString("tipo_conta"), rs.getDouble("saldo"));
                System.out.printf("Despesa: %.2f | Descrição: %s\n", rs.getDouble("despesa_valor"), rs.getString("despesa_desc"));
                System.out.printf("Receita: %.2f | Descrição: %s\n\n", rs.getDouble("receita_valor"), rs.getString("receita_desc"));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao executar consulta: " + e.getMessage());
        }
    }

    public static void exibirUsuarioContasReceitas() {
        String sql = "SELECT u.cd_usuario, u.nome_usuario, u.email, c.cd_conta, c.tipo_conta, c.saldo, " +
                "r.valor AS receita_valor, r.descricao AS receita_desc " +
                "FROM USUARIO u " +
                "JOIN CONTA c ON u.cd_usuario = c.cd_usuario " +
                "JOIN RECEITA r ON c.cd_conta = r.cd_conta";

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n---------------------------------------------");
            System.out.println("Usuário | Conta | Receitas");
            System.out.println("---------------------------------------------");

            while (rs.next()) {
                System.out.printf("Usuário ID: %d | Nome: %s | Email: %s\n",
                        rs.getInt("cd_usuario"), rs.getString("nome_usuario"), rs.getString("email"));
                System.out.printf("Conta ID: %d | Tipo: %s | Saldo: %.2f\n",
                        rs.getInt("cd_conta"), rs.getString("tipo_conta"), rs.getDouble("saldo"));
                System.out.printf("Receita: %.2f | Descrição: %s\n\n",
                        rs.getDouble("receita_valor"), rs.getString("receita_desc"));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao executar consulta: " + e.getMessage());
        }
    }

    public static void exibirUsuarioContasDespesas() {
        String sql = "SELECT u.cd_usuario, u.nome_usuario, u.email, c.cd_conta, c.tipo_conta, c.saldo, " +
                "d.valor AS despesa_valor, d.descricao AS despesa_desc " +
                "FROM USUARIO u " +
                "JOIN CONTA c ON u.cd_usuario = c.cd_usuario " +
                "JOIN DESPESA d ON c.cd_conta = d.cd_conta";

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n---------------------------------------------");
            System.out.println("Usuário | Conta | Despesas");
            System.out.println("---------------------------------------------");

            while (rs.next()) {
                System.out.printf("Usuário ID: %d | Nome: %s | Email: %s\n",
                        rs.getInt("cd_usuario"), rs.getString("nome_usuario"), rs.getString("email"));
                System.out.printf("Conta ID: %d | Tipo: %s | Saldo: %.2f\n",
                        rs.getInt("cd_conta"), rs.getString("tipo_conta"), rs.getDouble("saldo"));
                System.out.printf("Despesa: %.2f | Descrição: %s\n\n",
                        rs.getDouble("despesa_valor"), rs.getString("despesa_desc"));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao executar consulta: " + e.getMessage());
        }
    }
}
