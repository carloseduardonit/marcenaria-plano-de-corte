/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marcenaria.material;

import java.sql.*;
import marcenaria.Const.Messagem;
import marcenaria.dado.ModuloConector;
import marcenaria.dado.Table;

/**
 *
 * @author Carlos Eduardo dos Santos Figueiredo
 *
 */
public class Pedaco {

    /**
     * Variavel inteiro
     */
    private static int idChapa = 0, idPeca = 0, id;
    /**
     * Variavel double
     */
    private static double compPedaco = 0, largPedaco = 0, espePedaco = 0, precPedaco;
    /**
     * Variavel data
     */
    private static Date incData;
    private static String sys = "System";
    private static final String TABELA = Pedaco.class.getSimpleName();
    private static Connection conexao;
    private static ResultSet rs;
    private static ResultSetMetaData rsmd;
    private static PreparedStatement pst;
    private static Statement stmt;

    /** <b>Este Metodo abre a conexão com o bano de dados.</b>
     *
     */
    private static void pedaco() {
        conexao = ModuloConector.getConecction();
    }

    /**
     * <b> Este Metodo faz a criação da tabela Pedaço no banco.</b>
     */
    public static void criadoPedaco() {
        if (Table.VerificarNaoExistirTabela(Chapa.getTABELA())) {
            Chapa.criadaChapa();
        }
       
        String sql = "create table if not exists " + Pedaco.getTABELA()
                + "(id" + Pedaco.getTABELA() + " int primary key auto_increment,"
                + "quantidade int default 0,"
                + "comprimento double(7,2) not null,"
                + "largura double(7,2) not null,"
                + "espessura double(4,2) not null,"
                + "preco double(10,2) not null, "
                + "tipoMaterial varchar(30) not null,"
                + "id" + Chapa.getTABELA() + " int default '0',"
                + "incData Timestamp auto_increment,"
                + "foreign key (id" + Chapa.getTABELA() + ") references " + Chapa.getTABELA() + " (id" + Chapa.getTABELA() + "), "
                + "foreign key (id" + Peca.getTABELA() + ") references " + Peca.getTABELA() + " (id" + Peca.getTABELA() + "))";
        Table.criarTabela(sql, Pedaco.getTABELA());

    }

    /**
     *
     * <b>Este metodo faz a deletação a tabela Pedaço no banco.</b>
     * <p>
     * utilizando um metodo da Classe Material do Metodo deletarMaterial(String
     * Tabela).
     * </p>
     */
    public static void deletaPedaco() {
        Table.deletarTabela(Pedaco.getTABELA());
    }

    /**
     * <b>Este Metodo adicionar informação na tabela Pedaço no banco.</b>
     *
     * @param quantPedaco
     * @param quantPeca Informar um valor double da Comprimento da Pedaço.
     * @param compPedaco Informar um valor double da Comprimento da Pedaço.
     * @param precPedaco Informar um valor double da preço da Pedaço.
     * @param tipoMaterial Informar um valor double da largura da Pedaço.
     * @param largPedaco Informar um valor double da largura da Pedaço.
     * @param espePedaco Informar um valor double da espessura do Pedaço.
     * @param idChapa
     * @param idPeca
     */
    public static void adicionarPedaco(int quantPedaco, double compPedaco, double largPedaco, double espePedaco, double precPedaco, String tipoMaterial) {
        if (Pedaco.HaCampoVazio(quantPedaco, compPedaco, largPedaco, espePedaco, precPedaco, tipoMaterial, 0, idPeca)) {
            
            int idChapa = Chapa.obterIdChapa(compPedaco, largPedaco, espePedaco, tipoMaterial, sys, false);
            if (idChapa > 0) {
                try {
                    String sql = "insert into " + Pedaco.getTABELA()
                            + "(quantidade, comprimento, largura, espessura, preco, tipoMaterial, id"
                            + Chapa.getTABELA() + ") values (?,?,?,?,?,?,?)";
                    pedaco();
                    pst = conexao.prepareStatement(sql);
                    pst.setInt(1, quantPedaco);
                    pst.setDouble(2, compPedaco);
                    pst.setDouble(3, largPedaco);
                    pst.setDouble(4, espePedaco);
                    pst.setDouble(5, precPedaco);
                    pst.setString(6, tipoMaterial);
                    pst.setInt(7, idChapa);
                    int adicionar = pst.executeUpdate();
                    if (adicionar > 0) {
                        Messagem.chamarTela(Messagem.ADICIONADO(sql));
                    }
                } catch (SQLException e) {
                    Messagem.chamarTela(e);
                } finally {
                    ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
                }
            }
        }
    }

    /**
     * <b>Este Metodo Editar informação na tabela pedaço no banco.</b>
     *
     * @param quantPecado
     * @param idChapa Informar um valor inteiro do Id da Chapa.
     * @param idPeca Informar um valor inteiro do Id da Peca.
     * @param compPedaco Informar um valor double da Comprimento da Pedaço.
     * @param largPedaco Informar um valor double da largura da Pedaço.
     * @param espePedaco Informar um valor double da espessura do Pedaço.
     * @param incData Informar um valor Date do dia da Inclusão do Pedaço.
     */
    public static void editarPedaco(int quantPecado, double compPedaco, double largPedaco, double espePedaco, Date incData) {
        try {
            pedaco();
            String sql = "";
            pst = conexao.prepareStatement(sql);
            pst.setDouble(1, compPedaco);
            pst.setDouble(2, largPedaco);
            pst.setDouble(3, espePedaco);
            pst.setDouble(4, 0);
            int editar = pst.executeUpdate();
            if (editar == 0) {
                Messagem.chamarTela(Messagem.EDITADO(sql));
            }
        } catch (SQLException e) {
            Messagem.chamarTela(e);
        } finally {
            ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
        }
    }

    /**
     * <b>Este metodo excluir informaçao na tabela pedaço no banco.</b>
     *
     * @param idChapa Informar um valor inteiro do Id da Chapa.
     * @param idPeca Informar um valor inteiro do Id da Peca.
     * @param compPedaco Informar um valor double da Comprimento da Pedaço.
     * @param largPedaco Informar um valor double da largura da Pedaço.
     * @param espePedaco Informar um valor double da espessura do Pedaço.
     * @param incData Informar um valor Date do dia da Inclusão do Pedaço.
     */
    public static void excluirPedaco(int idChapa, int idPeca, double compPedaco, double largPedaco, double espePedaco, Date incData) {
        try {
            pedaco();
            String sql = "delete from " + TABELA + " where espe = ?";
            pst = conexao.prepareStatement(sql);
            pst.setDouble(1, espePedaco);
            int excluir = pst.executeUpdate();
            if (excluir == 0) {
                ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
                Messagem.chamarTela(Messagem.EXCLUIDO(sql));
            }
        } catch (NullPointerException e) {
            Messagem.chamarTela(e);
        } catch (Exception e) {
            Messagem.chamarTela(e);
        }
    }

    /**
     * <b>Este metodo pesquisar informação na tabela pedaço no banco.</b>
     * <p>
     * Se os paramentos forem diferentes de null ou vazio.</p>
     *
     * @param idChapa Informar um valor inteiro do Id da Chapa.
     * @param idPeca Informar um valor inteiro do Id da Peca.
     * @param compPedaco Informar um valor double da Comprimento da Pedaço.
     * @param largPedaco Informar um valor double da largura da Pedaço.
     * @param espePedaco Informar um valor double da espessura do Pedaço.
     * @param tipoMaterial
     * @param incData Informar um valor Date do dia da Inclusão do Pedaço.
     * @param ou Informar um valor boolean:
     * <p>
     * Se <b>ou</b> for <b>true</b>: adicionar na String interna a expresão
     * ="or";</p>
     * <p>
     * senão adicionar na String interna a expresão ="and".</p>
     */
    public static void pesquisarPedaco(int idChapa, int idPeca, double compPedaco, double largPedaco, double espePedaco, String tipoMaterial, Date incData, boolean ou) {
        try {
            pedaco();
            int qt = 0, cqt, lqt, eqt;
            boolean c, l, e;
            String a;
            if (ou == false) {
                a = "and";
            } else {
                a = "or";
            }
            String sql = "select * from " + TABELA + " where ";
            if (String.valueOf(compPedaco).equals(null) == false && !String.valueOf(espePedaco).isEmpty()) {
                if (qt == 0) {
                    sql += "comp = ?";

                } else {
                    sql += a + " comp = ?";
                }
                qt++;
                c = false;
            } else {
                c = false;
            }
            if (String.valueOf(largPedaco).equals(null) == false && !String.valueOf(largPedaco).isEmpty()) {
                if (qt == 0) {
                    sql += "larg = ?";
                } else {
                    sql += a + " larg = ?";
                }
                qt++;
                l = false;
            } else {
                l = false;
            }
            if (String.valueOf(espePedaco).equals(null) == false && !String.valueOf(espePedaco).isEmpty()) {
                if (qt == 0) {
                    sql += "espe = ?";
                } else {
                    sql += a + " espe = ?";
                }
                qt++;
                e = false;
            } else {
                e = false;
            }

            pst = conexao.prepareStatement(sql);
            for (int i = 1; i <= qt; i++) {
                if (!String.valueOf(compPedaco).isEmpty() && c == false) {
                    cqt = i;
                    pst.setDouble(cqt, compPedaco);
                    c = true;
                } else if (!String.valueOf(largPedaco).isEmpty() && l == false) {
                    lqt = i;
                    pst.setDouble(lqt, largPedaco);
                    l = true;
                } else if (!String.valueOf(espePedaco).isEmpty() && e == false) {
                    eqt = i;
                    pst.setDouble(eqt, espePedaco);
                    e = true;
                }
            }
            rs = pst.executeQuery();
            if (rs.next()) {
                setIdChapa(rs.getInt(1));
                setIdPeca(rs.getInt(2));
                setComp(rs.getDouble(3));
                setLarg(rs.getDouble(4));
                setEspe(rs.getDouble(5));
                setIncData(rs.getDate(6));

            }
        } catch (NullPointerException e) {
            Messagem.chamarTela("Nulo");
        } catch (SQLException e) {
            Messagem.chamarTela(e);
        } finally {
            ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
        }
    }

    /**
     *
     */
    private static boolean HaCampoVazio(int quantPeca, double compPedaco, double largPedaco, double espePedaco, double precPedaco, String tipoMaterial, int idChapa, int idPeca) {
        return String.valueOf(quantPeca).isEmpty() && String.valueOf(compPedaco).isEmpty() && String.valueOf(largPedaco).isEmpty() && String.valueOf(espePedaco).isEmpty() && String.valueOf(precPedaco).isEmpty() && tipoMaterial.isEmpty() && String.valueOf(idChapa).isEmpty() && String.valueOf(idPeca).isEmpty();
    }

    /**
     *
     */
    private static String[] CampoVazio(int quantPeca, double compPedaco, double largPedaco, double espePedaco, double precPedaco, String tipoMaterial, int idChapa, int idPeca) {
        String[] vazio = new String[8];
        boolean qp = false, cp = false, lp = false, ep = false, pp = false, tm = false, ic = false, ip = false;
        for (int i = 0; i < vazio.length; i++) {
            if (String.valueOf(quantPeca).isEmpty() && !qp) {
                vazio[i] = "";
                qp = true;
            } else if (String.valueOf(compPedaco).isEmpty() && !cp) {
                vazio[i] = "";
                cp = true;
            } else if (String.valueOf(largPedaco).isEmpty() && !lp) {
                vazio[i] = "";
                lp = true;
            } else if (String.valueOf(espePedaco).isEmpty() && !ep) {
                vazio[i] = "";
                ep = true;
            } else if (String.valueOf(precPedaco).isEmpty() && !pp) {
                vazio[i] = "";
                pp = true;
            } else if (tipoMaterial.isEmpty() && !tm) {
                vazio[i] = "";
                tm = true;
            } else if (String.valueOf(idChapa).isEmpty() && !ic) {
                vazio[i] = "";
                ic = true;
            } else if (String.valueOf(idPeca).isEmpty() && !ip) {
                vazio[i] = "";
                ip = true;
            }
        }
        return vazio;
    }

    /**
     *
     */
    private static void exibirPedaco(int idChapa, int idPeca, double compPedaco, double largPedaco, double espePedaco, String tipoMaterial, Date incData, boolean ou) {
        Messagem.chamarTela(exibirPedacotoString(idChapa, idPeca, compPedaco, largPedaco, espePedaco, tipoMaterial, incData, ou));

    }

    /**
     *
     */
    private static String exibirPedacotoString(int idChapa, int idPeca, double compPedaco, double largPedaco, double espePedaco, String tipoMaterial, Date incData, boolean ou) {
        String exibe = "";
        pesquisarPedaco(idChapa, idPeca, compPedaco, largPedaco, espePedaco, tipoMaterial, incData, ou);
        if (Peca.getIdPeca() > 0 && Peca.getComprimento() > 0.0 && Peca.getLargura() > 0.0 && Peca.getEspessura() > 0.0 && Peca.getPreco() > 0.0) {

        } else {

        }
        return exibe;
    }

    /**
     *
     * @param tipoMaterial
     * @param espessura
     * @return
     */
    public static int obterIdPedaco(String tipoMaterial, double espessura) {
        return Material.obterIdMaterial(getTABELA(), tipoMaterial, espessura);
    }

    /**
     * @param compPedaco
     * @param largPedaco
     * @param espePedaco
     * @param tipoMaterial
     * @return
     */
    public static boolean temPedaco(double compPedaco, double largPedaco, double espePedaco, String tipoMaterial) {
        pesquisarPedaco(0, 0, 0, 0, 0, tipoMaterial, null, true);
        boolean a = getComp() >= compPedaco && getLarg() >= largPedaco && getEspe() >= espePedaco;
        if (a == false) {
            Messagem.chamarTela("Não há Pedaço !!!");
        }
        return a;
    }
// Gets e Sets

    /** <b>Este Metodo retornar o ID da Chapa.</b>
     *
     * @return Retonar um valor inteiro do Id da Chapa.
     */
    public static int getIdChapa() {
        return idChapa;
    }

    /** <b>Este Metodo setar a informação um valor inteiro na varivel ID da
     * Chapa.</b>
     *
     * @param idChapa Setar a Informação um valor inteiro do Id da Chapa.
     */
    public static void setIdChapa(int idChapa) {
        Pedaco.idChapa = idChapa;
    }

    /** <b>Este Metodo Retornar a informação O ID da Peça.</b>
     *
     * @return Retornar um valor inteiro do Id da Peça.
     */
    public static int getIdPeca() {
        return idPeca;
    }

    /** <b>Este Metodo setar a informação do ID da Peça.</b>
     *
     * @param idPeca Informar um valor inteiro do Id da Peca.
     */
    public static void setIdPeca(int idPeca) {
        Pedaco.idPeca = idPeca;
    }

    /** <b>Este Metodo Retornar a informação do comprimento do Pedaço.</b>
     *
     * @return Retornar um valor double da Comprimento da Pedaço.
     */
    public static double getComp() {
        return compPedaco;
    }

    /** <b>Este Metodo setar a informação do Comprimento da Pedaço.</b>
     *
     * @param compPedaco Informar um valor double da Comprimento da Pedaço.
     */
    public static void setComp(double compPedaco) {
        Pedaco.compPedaco = compPedaco;
    }

    /** <b>Este Metodo Retornar a informação do largura do pedaço.</b>
     *
     * @return Retornar um valor double da largura da Pedaço.
     */
    public static double getLarg() {
        return largPedaco;
    }

    /** <b>Este Metodo setar a informação da largura do Pedaço.</b>
     *
     * @param largPedaco Informar um valor double da largura do Pedaço.
     */
    public static void setLarg(double largPedaco) {
        Pedaco.largPedaco = largPedaco;
    }

    /** <b>Este Metodo Retornar a informação da Espessura do Pedaço.</b>
     *
     * @return Retornar um valor double da espessura do Pedaço.
     */
    public static double getEspe() {
        return espePedaco;
    }

    /** <b>Este Metodo setar a informação da Espessura do Pedaço.</b>
     *
     * @param espePedaco Informar um valor double da Espessura do Pedaço.
     */
    public static void setEspe(double espePedaco) {
        Pedaco.espePedaco = espePedaco;
    }

    /** <b>Este Metodo Retornar a informação da Preço do Pedaço.</b>
     *
     * @return Retornar um valor double da Preco do Pedaço.
     */
    public static double getPrec() {
        return precPedaco;
    }

    /** <b>Este Metodo setar a informação da Preço do Pedaço.</b>
     *
     * @param precPedaco Informar um valor double da Preco do Pedaço.
     */
    public static void setPrec(double precPedaco) {
        Pedaco.precPedaco = precPedaco;
    }

    /** <b>Este Metodo Retornar a informação do dia da Inclusão do Pedaço.</b>
     *
     * @return Retornar um valor Date do dia da Inclusão do Pedaço.
     */
    public static Date getIncData() {
        return incData;
    }

    /** <b>Este Metodo setar a informação da dia da inclusão do Pedaço.</b>
     *
     * @param incData Informar um valor Date do dia da Inclusão do Pedaço.
     */
    public static void setIncData(Date incData) {
        Pedaco.incData = incData;
    }

    /** <b>Este Metodo Retornar o nome da classe Pedaço.</b>
     *
     * @return Retornar uma String com nome da tabela.
     */
    public static String getTABELA() {
        return TABELA;
    }

}
