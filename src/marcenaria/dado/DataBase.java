/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marcenaria.dado;

import java.sql.*;
import marcenaria.Const.Messagem;

/**
 *
 * @author Carlos
 */
public class DataBase extends ModuloConector {

    public static void main(String[] args) {
        criarDataBase("");
        deletarDataBase("teste");
    }
    private static Connection conexao;
    private static Statement stmt;
    private static ResultSet rs;
    private static ResultSetMetaData rsmd;
    private static PreparedStatement pst;

    /**
     * TESTE
     *
     * tem ver
     *
     * @param dataBase
     */
    public static void criarDataBase(String dataBase) {
        if (NaoHaCampoVazio(dataBase)) {
            try {
                conexao = ModuloConector.getConecction1();
                String sql = "create database if not exists " + dataBase;
                stmt = conexao.createStatement();
                int criada = stmt.executeUpdate(sql);
                if (criada > 0) {
                    Messagem.chamarTela(Messagem.CRIADO("Banco " + dataBase));
                }
            } catch (SQLException e) {
                Messagem.chamarTela(e);
            } finally {
                ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
            }
        }
    }

    /**
     * @param dataBase
     */
    public static void deletarDataBase(String dataBase) {
        if (NaoHaCampoVazio(dataBase)) {
            try {
                conexao = ModuloConector.getConecction1();
                String sql = "drop database if exists " + dataBase;
                stmt = conexao.createStatement();
                int deletada = stmt.executeUpdate(sql);
                System.out.println(deletada);
                if (deletada > 0) {
                    Messagem.chamarTela(Messagem.EXCLUIDO("Banco " + dataBase));
                }
                stmt = conexao.createStatement();
            } catch (SQLException e) {
                Messagem.chamarTela(e);
            } finally {
                ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
            }
        }
    }

    private static boolean HaCampoVazio(String dataBase) {
        boolean campo = dataBase.isEmpty();
        if (campo) {
            Messagem.chamarTela(Messagem.VAZIO("nome do banco de dados nao informado"));
        }
        return campo;
    }

    private static boolean NaoHaCampoVazio(String dataBase) {

        return !HaCampoVazio(dataBase);
    }

    /**
     * Fazer
     *
     * @since 01/05/2019
     */
    public static void importarBackupdataBase() {
        Connection conexao = ModuloConector.getConecction();
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
     * Fazer
     *
     * @since 01/05/2019
     */
    public static void exportarBackupdataBase() {

    }
}
