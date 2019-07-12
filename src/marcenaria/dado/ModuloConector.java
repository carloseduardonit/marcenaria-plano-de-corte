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
 * @version 1.2
 * @author Carlos Eduardo dos Santos Figueiredo
 * @see ModuloConector
 * @since 01/05/2019
 */
public class ModuloConector {

    /**
     * Nome do banco de dados
     */
    public static final String DATABASE = "teste";
    /**
     * Driver do banco de dados
     */
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    /**
     * A URL do banco de dados
     */
    public static final String URL = "jdbc:mysql://localhost:3306/";
    /**
     * A URL + Nome do banco de dados
     */
    public static final String URLD = URL + DATABASE;
    /**
     * Usuario do banco de dados
     */
    public static final String USER = "root";
    /**
     * Senha do banco de dados
     */
    public static final String PASS = "";
    //Variaveis de gerencimento do bano de dados
    /**
     * 
     */
    private static Connection conexao = null;
    /**
     * 
     */
    private static PreparedStatement pst = null;
    /**
     * 
     */
    private static ResultSet rs = null;
    /**
     * 
     */
    private static ResultSetMetaData rsmd = null;
    /**
     * 
     */
    private static Statement stmt = null;

    /**
     * Este metodo faz a conexão com o banco de dados
     */
    private static void conector() {
        conexao = getConecction();

    }

    /**
     * Este metodo faz a conexao com o banco de dados MYSQL utilizado as
     * variaveis finais
     *
     * @version 1.0
     * @since 01/05/2019
     * @return a conexao conexao com o banco de dado
     */
    public static java.sql.Connection getConecction() {
        Connection conexao = null;
        try {
            Class.forName(DRIVER);
            return conexao = DriverManager.getConnection(URLD, USER, PASS);
        } // catch{ }
        catch (ClassNotFoundException cnfe) {
            Messagem.chamarTela("O Servidor ultra-passou o limite de Conexão " + cnfe);
            fecharConexao(conexao, rs, pst, stmt);
        } catch (CommunicationsException ce) {
            Messagem.chamarTela("O banco de dados deve esta desligado " + ce);
            fecharConexao(conexao, rs, pst, stmt);
        } catch (Exception e) {
            Messagem.chamarTela("O banco de dados deve esta desligado " + e);
            fecharConexao(conexao, rs, pst, stmt);
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
     * @version 1.0 Este Metodo faz o fechamento da conexao
     * @since 01/05/2019
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
     *
     * Este Metodo faz o fechamento da conexao e Resultado
     *
     * @since 01/05/2019
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
     * @since 01/05/2019
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
     * @since 01/05/2019
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
     *
     * @since 01/05/2019
     */
    public static void criarBackupdataBase() {
        Connection conexao = getConecction();
        String sql = "BACKUP DATABASE teste\n" + "TO DISK = 'D:\\backups\\testDB.bak'";
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
     * Este Metodo Verifica se determinada Tabela nao existe return um valor
     * Boolean
     *
     * @since 01/05/2019
     * @param Tabela Setar uma Informação de tipo String com o nome da Tabela
     * @return Retornar um valor Boolean
     *
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

    /**
     * Este Metodo Obtem o numero de Coluna de uma determinada tabela no banco.
     *
     * @since 10/07/2019
     * @version 1.4
     * @param tabela Setar uma informação devalor String com o none da tabela .
     * @return Retornar uma infornação de valor inteiro com a quantidade de
     * coluna.
     */
    public static int quantColuna(String tabela) {
        String sql = "selec * from ?";
        return quantColuna(tabela, sql);
    }

    /**
     * Este Metodo Obtem o numero de Coluna de uma determinada tabela no banco.
     *
     * @since 11/07/2019
     * @version 1.5
     * @param tabela Setar uma informação devalor String com o none da tabela .
     * @param sql Setar uma Informação de Valor String com a instrução MYSQL.
     * @return Retornar uma informação de valor inteiro com a quantidade de
     * coluna.
     */
    public static int quantColuna(String tabela, String sql) {
        conector();
        try {
            if (VerificarNaoExistirTabela(tabela)) {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, tabela);
                rs = pst.executeQuery();
                if (rs.next()) {
                    rsmd = rs.getMetaData();
                    return rsmd.getColumnCount();
                }else{

                }
            }else   {
                
            }
        } catch (Exception e) {
            Messagem.chamarTela("Metodo QuantColuna da Classe ModuloConector: " + e);
        }

        return 0;
    }

    /**
     * Este Metodo Obtem o numero de Linha de uma determinada tabela no banco
     *
     * @since 10/07/2019
     * @param tabela Setar uma informação devalor String com o none da tabela .
     * @return Retornar uma informação de valor inteiro com a quantidade de
     * linha.
     */
    public static int quantLinha(String tabela) {
        String sql = "selec * from " + tabela;
        return quantLinha(tabela, sql);
    }

    /**
     * Este Metodo Obtem o numero de Linha de uma determinada tabela no banco
     *
     * @since 11/07/2019
     * @version 1.5
     * @param tabela Setar uma informação devalor String com o none da tabela .
     * @param sql Setar uma Informação de Valor String com a instrução MYSQL.
     * @return Retornar uma informação de valor inteiro com a quantidade de
     * linha.
     */
    // para obter o numero de linha eu tenho que sabe se  Tabela existe; Depois saber ser a instrução mysql obter algum resultado
    public static int quantLinha(String tabela, String sql) {
        try {
            conector();
            if (VerificarNaoExistirTabela(tabela)) {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, tabela);
                rs = pst.executeQuery();
                stmt = rs.getStatement();
                if (rs.next()) {
                    return stmt.getMaxRows();
                }else{
                    Messagem.chamarTela("");
                }
            } else {
                Messagem.chamarTela("");
            }
        } catch (Exception e) {
            Messagem.chamarTela("Metodo QuantLinha da Classe ModuloConector: " + e);
        }
        return 0;
    }

}
