/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marcenaria.dado;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import marcenaria.Const.Messagem;

/**
 *
 * @author Carlos
 */
public class DataBase extends ModuloConector {

    static String linha = new String();

    public static void main(String[] args) {
        criarDataBase("teste");
        //deletarDataBase("teste");
    }
    private static Connection conexao;
    private static Statement stmt;
    private static ResultSet rs;
    private static ResultSetMetaData rsmd;
    private static PreparedStatement pst;
    static File arg;

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
     * Testando
     * Este Metodo faz a importação de arquivo  do tipo sql, com a localizaçao o arquivo atravez  do parametro
     *
     * @param caminhoArquivo
     * @since 01/05/2019
     */
    public static void importarBackupdataBaseSQL(String caminhoArquivo) {
        arg = new File(caminhoArquivo);
        String Conteudo = "";
        if (arg.exists()) {
            try {
                // pega a localizaçao do arquivo
                FileReader leitorArquivo = new FileReader(caminhoArquivo);
                //ler a linha do arquivo 
                BufferedReader bufferArquivo = new BufferedReader(leitorArquivo);
                while (true) {
                    linha = bufferArquivo.readLine();
                    if (linha == null) {
                        break;
                    } else {
                        if (!linha.startsWith("--")) {
                            // este e o incio do Bloco separa o comentario  da instrução sql
                            System.out.println(linha);
                            Conteudo += linha;
                            if (Conteudo.endsWith(";")) {
                                // este Bloco executar instrução sql
                                try {
                                    conexao = ModuloConector.getConecction();
                                    Statement stmt = conexao.createStatement();
                                    int adicionar = stmt.executeUpdate(Conteudo);
                                    if (adicionar > 0) {
                                        // Messagem.chamarTela(Conteudo);
                                    }
                                    System.err.println(Conteudo);
                                } catch (SQLException e) {
                                     System.err.println(e);
                                    Messagem.chamarTela(e);
                                } finally {
                                    Conteudo = "";
                                    ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
                                }
                                // este Bloco executar instrução sql
                            }else{
                                 //  else  do  comentario MYSqul
                            }
                            // este e o FIM do Bloco separa o comentario  da instrução sql
                        } else{
                            //  else  do  comentario MYSqul
                        }
                    }
                }
            } catch (FileNotFoundException FNFE) {
                Messagem.chamarTela(FNFE);
            } catch (IOException IOE) {
                Messagem.chamarTela(IOE);
            } finally { 
                arg.deleteOnExit();
            }
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
