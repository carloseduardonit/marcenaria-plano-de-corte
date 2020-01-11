/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marcenaria.dado;

import java.io.*;
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
    static BufferedReader bufferArquivo;
    static File arg;
    static FileReader leitorArquivo;
    static FileWriter escrita;
    static PrintWriter gravarArquivo;

    /**
     * OK
     *
     * Este metodo faz a criação do banco de dados conforme o parametro dataBase
     *
     * @param dataBase Setar uma informação de valor String do nome banco de
     * dados
     */
    public static void criarDataBase(String dataBase) {
        if (NaoHaCampoVazio(null, dataBase)) {
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
     * @param dataBase Setar uma informação de valor String do nome banco de
     * dados
     */
    public static void deletarDataBase(String dataBase) {
        if (NaoHaCampoVazio(null, dataBase)) {
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

    /**
     * Este metodo Retornar uma informação de valor booleanda se for TRUE: ha
     * campo vazio ; ou se for FALSE: Não ha campo vazio
     *
     * @param dataBase Setar uma informação de valor String do nome banco de
     * dados
     * @return Retornar uma informação de valor booleanda se ha campo vazio
     */
    private static boolean HaCampoVazio(String CaminhoArquivo, String dataBase) {
        boolean campo = true;
        if (!CaminhoArquivo.contentEquals(null) && !dataBase.contentEquals(null)) {
            campo = CaminhoArquivo.isEmpty() || dataBase.isEmpty();
        } else if (!CaminhoArquivo.contentEquals(null)) {
            campo = CaminhoArquivo.isEmpty();
        } else if (!dataBase.contentEquals(null)) {
            campo = dataBase.isEmpty();
        } else {
            campo = !campo;
        }
        if (campo) {
            Messagem.chamarTela(Messagem.VAZIO(CampoVazio(CaminhoArquivo, dataBase)));
        }
        return campo;
    }

    /**
     * Este metodo Retornar uma informação de valor booleanda se for TRUE: nao
     * ha campo vazio ; ou se for FALSE: ha campo vazio
     *
     * @param dataBase Setar uma informação de valor String do nome banco de
     * dados
     * @return Retornar uma informação de valor booleanda se não ha campo vazio
     */
    private static boolean NaoHaCampoVazio(String CaminhoArquivo, String dataBase) {
        return !HaCampoVazio(CaminhoArquivo, dataBase);
    }

    private static String[] CampoVazio(String CaminhoArquivo, String dataBase) {
        String[] vazio = new String[2];
        int i = 0;
        if (!CaminhoArquivo.contentEquals(null) && CaminhoArquivo.isEmpty()) {
            vazio[i++] = " Carminho  do Arquivo";
        }
        if (!dataBase.contentEquals(null) && dataBase.isEmpty()) {
            vazio[i++] = "Banco de Dados";
        }
        return vazio;
    }

    /**
     * Testando Este Metodo faz a importação de arquivo do tipo sql, com a
     * localizaçao o arquivo atravez do parametro
     *
     * @param caminhoArquivo Setar uma informação de valor String do Caminho do
     * arquivo que deseja ser executado
     * @param Banco Setar uma informação de valor String do nome banco de dados
     * @since 23/12/2019
     */
    public static void importarBackupdataBaseSQL(String caminhoArquivo, String Banco) {
        if (NaoHaCampoVazio(caminhoArquivo, Banco)) {
            arg = new File(caminhoArquivo);
            String Conteudo = "";
            int indexLinha = 0;
            if (arg.exists()) {
                try {
                    // pega a localizaçao do arquivo
                    leitorArquivo = new FileReader(caminhoArquivo);
                    //ler a linha do arquivo 
                    bufferArquivo = new BufferedReader(leitorArquivo);
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
    }

    /**
     * Este metodo faz a exportação de arquivo do tipo sql, com a localização o
     * arquivo atravez do paramentro 1.faz uma verificação dos parametros.
     *
     * @param caminhoArquivo Setar uma informação de valor String do Caminho do
     * arquivo que deseja ser executado
     * @param Banco Setar uma informação de valor String do nome banco de dados
     * @since 01/05/2019
     */
    public static void exportarBackupdataBaseSQL(String caminhoArquivo, String Banco) {
        if (NaoHaCampoVazio(caminhoArquivo, Banco)) {
            try {
                // A linha abaixo procurar o arquivo.
                escrita = new FileWriter(caminhoArquivo);
                //https://www.youtube.com/watch?v=PS44nHjvtdo
                gravarArquivo = new PrintWriter(escrita);
                //Escreve no arquivo
                gravarArquivo.println("");
            } catch (IOException ex) {
                Messagem.chamarTela(ex);
            } finally {
                gravarArquivo.close();
            }
        }
    }
}
