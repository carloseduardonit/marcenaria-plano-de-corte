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
     * OK
     *
     * Este metodo faz a criação do banco de dados conforme o parametro dataBase
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
     * OK Este metodo faz a exclução do banco de dados e de todos os dados
     * conforme o parametro dataBase
     *
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
     * Testando Este Metodo faz a importação de arquivo do tipo sql, com a
     * localizaçao o arquivo atravez do parametro
     *
     * @param caminhoArquivo
     * @param Banco
     * @since 23/12/2019
     */
    public static void importarBackupdataBaseSQL(String caminhoArquivo, String Banco) {
        arg = new File(caminhoArquivo);
        String Conteudo = "";
        int indexLinha = 0;
        if (arg.exists()) {
            try {
                // pega a localizaçao do arquivo
                FileReader leitorArquivo = new FileReader(caminhoArquivo);
                //ler a linha do arquivo 
                BufferedReader bufferArquivo = new BufferedReader(leitorArquivo);
                while (true) {
                    linha = bufferArquivo.readLine();
                    if (linha == null) {
                        System.out.println("1");
                        if (indexLinha == 0) {
                            Messagem.chamarTela("O arquivo se encontra Vazio  ou não existe");
                        }
                        break;
                    } else {
                        System.out.println("2");
                        if (!linha.startsWith("*/") || !linha.startsWith("--") || !linha.isEmpty() || !linha.startsWith("*") || !linha.startsWith("/*")) {
                            System.out.println("21");
                            // este e o incio do Bloco separa o comentario  da instrução sql
                            System.out.println(linha);
                            Conteudo += linha;
                            if (Conteudo.endsWith(";")) {
                                // este Bloco executar instrução sql
                                try {
                                    if (indexLinha == 0 || Banco == null) {
                                        conexao = ModuloConector.getConecction1();
                                    } else {
                                        conexao = ModuloConector.getConecction(Banco);
                                    }
                                    stmt = conexao.createStatement();
                                    int adicionar = stmt.executeUpdate(Conteudo);
                                    if (adicionar > 0) {
                                        // Messagem.chamarTela(Conteudo);
                                    }
                                    System.err.println(Conteudo);
                                } catch (SQLException e) {
                                    //e.toString().
                                    System.err.println(e);
                                    Messagem.chamarTela(e);
                                } finally {
                                    Conteudo = "";
                                    ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
                                    indexLinha++;
                                }
                                // este Bloco executar instrução sql
                            } else {
                                //  else  do  comentario MYSqul
                            }
                            // este e o FIM do Bloco separa o comentario  da instrução sql
                        } else {
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
                linha = null;
            }
        } else {
            Messagem.chamarTela("O arquivo se encontra Vazio  ou não existe");
        }
    }

    /**
     * Fazer
     *
     * @param caminhoArquivo
     * @param Banco
     * @since 01/05/2019
     */
    public static void exportarBackupdataBaseSQL(String caminhoArquivo, String Banco) {

    }
}
