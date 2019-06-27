/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marcenaria.dado;

import com.mysql.jdbc.CommunicationsException;
import java.sql.*;
import marcenaria.Const.Messagem;

/**
 *
 * @author Carlos
 */
public class ModuloConector {

    public static final String DATABASE = "teste";
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/";
    public static final String URLD = URL + DATABASE;
    public static final String USER = "root";
    public static final String PASS = "";
    private static Connection conexao = null;
    private static PreparedStatement pst = null;
    private static ResultSet rs = null;
    private static Statement stmt = null;

    private static void conector() {
        conexao = getConecction();

    }

    /**
     * Este metodo faz a conexao com o banco de dados MYSQL utilizado as
     * variaveis finais
     *
     * @return a conexao conexao com o banco de dado
     */
    public static java.sql.Connection getConecction() {
        Connection conexao = null;
        try {
            Class.forName(DRIVER);
            return conexao = DriverManager.getConnection(URLD, USER, PASS);
        } //catch{        }
        catch (ClassNotFoundException cnfe) {
            Messagem.chamarTela("O Servidor ultra-passou o limite de Conexão");
            fecharConexao(conexao);
        } catch (CommunicationsException e) {
            Messagem.chamarTela("O banco de dados deve esta desligado");
            fecharConexao(conexao);
        } catch (Exception e) {
            Messagem.chamarTela("O banco de dados deve esta desligado");
            fecharConexao(conexao);
        }
        return conexao;
    }

    /**
     * TESTE
     *
     * tem ver
     */
    public static void criarDataBase() {

        try {
            Connection conexao = null;
            Class.forName(DRIVER);
            conexao = DriverManager.getConnection(URL, USER, PASS);
            String sql = "create database if not exist " + DATABASE;

        } catch (Exception e) {
            Messagem.chamarTela(e);
        }
    }

    /**
     * Este Metodo faz o fechamento da conexao
     *
     * @param con - Fecha a conexao do banco
     */
    public static void fecharConexao(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            Messagem.chamarTela(e);
        }
    }

    /**
     * Este Metodo faz o fechamento da conexao e Resultado
     *
     * @param con - Fecha a conexao do banco
     * @param rs - Fecha o resultado do Banco
     */
    public static void fecharConexao(Connection con, ResultSet rs) {
        try {
            if (rs != null) {
                fecharConexao(con);
                rs.close();
            } else {
                fecharConexao(con);
            }
        } catch (SQLException e) {
            Messagem.chamarTela(e);
        }
    }

    /**
     * Este Metodo faz o fechamento da conexao, Resultado e Editação
     *
     * @param con - Fecha a conexao do banco
     * @param rs - Fecha o resultado do Banco
     * @param pst -Fecha a
     */
    public static void fecharConexao(Connection con, ResultSet rs, PreparedStatement pst) {
        try {
            if (pst != null) {
                fecharConexao(con, rs);
                pst.close();
            } else {
                fecharConexao(con, rs);
            }
        } catch (SQLException e) {
            Messagem.chamarTela(e);
        }
    }

    /**
     * Este Metodo faz o fechamento da conexao, Resultado e Editação
     *
     * @param con Fecha a conexao do banco
     * @param rs Fecha o resultado do Banco
     * @param pst Fecha a
     * @param stmt Fecha a
     */
    public static void fecharConexao(Connection con, ResultSet rs, PreparedStatement pst, Statement stmt) {
        try {
            if (pst != null) {
                fecharConexao(con, rs, pst);
                stmt.close();
            } else {
                fecharConexao(con, rs, pst);
            }
        } catch (SQLException e) {
            Messagem.chamarTela(e);
        }
    }

    /**
     * Fazer
     */
    public static void criarBackupdataBase() {
        Connection conexao = getConecction();
        String sql = "BACKUP DATABASE teste\n"
                + "TO DISK = 'D:\\backups\\testDB.bak'";
        try {
            Statement stmt = conexao.createStatement();
            int adicionar = stmt.executeUpdate(sql);
            if (adicionar > 0) {

            }
        } catch (SQLException e) {
            Messagem.chamarTela(e);
        }
    }

    /**
     * Este Metodo Verifica se determinada Tabela nao existe return um valor Boolean
     *
     * @param Tabela Setar uma Informação de tipo String com o nome da Tabela
     * @return Retornar um valor Boolean
     */
    public static Boolean VerificarNaoExistirTabela(String Tabela) {
        try {
            if (!Tabela.isEmpty()) {
                conector();
                String sql = "show tables in " + DATABASE + " like ?";
                pst = conexao.prepareStatement(sql);
                pst.setString(1, Tabela + "%");
                rs = pst.executeQuery();
                if (rs.next()) {
                    return false;
                }
            } else {
                Messagem.chamarTela("O campo tabela esta Vazio !!!");
            }
        } catch (SQLException e) {
            Messagem.chamarTela(e);
        }
        return true;
    }
}
