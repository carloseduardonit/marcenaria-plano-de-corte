/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marcenaria.material;

import java.sql.*;
import marcenaria.Const.Messagem;
import marcenaria.dado.ModuloConector;

/**
 *
 * @author Carlos Eduardo dos Santos Figueiredo
 *
 */
public class Pedaco {

    private static int idChapa, idPeca;
    private static double comp, larg, espe, prec;
    private static Date incData;
    private static final String TABELA = Pedaco.class.getSimpleName();
    static Connection conexao;
    static PreparedStatement pst;
    static ResultSet rs;
    static Statement stmt;

    /**/
    /**
     * <b>Este metodo e para utilização para teste.</b>
     *
     * @param args Metodo de teste
     */
    public static void main(String[] args) {
        criadoPedaco();
        Chapa.deletadaChapa();
    }

    /** <b>Este Metodo abre a conexão com o bano de dados.</b>
     *
     */
    private static void Pedaco() {
        conexao = ModuloConector.getConecction();
    }

    /**
     * OK
     * <b> Este Metodo faz a criação da tabela Pedaço no banco.</b>
     */
    public static void criadoPedaco() {
        Material.criarMaterial(getTABELA());
    }

    /**
     * OK
     * <b>Este metodo faz a deletação a tabela Pedaço no banco.</b>
     * <p>
     * utilizando um metodo da Classe Material do Metodo deletarMaterial(String
     * Tabela).
     * </p>
     */
    public static void deletaPedaco() {
        Material.deletarMaterial(TABELA);
    }

    /**
     * <b>Este Metodo adicionar informação na tabela Pedaço no banco.</b>
     *
     * @param quantPeca
     * @param idChapa Informar um valor inteiro do Id da Chapa.
     * @param idPeca Informar um valor inteiro do Id da Peca.
     * @param compPedaco Informar um valor double da Comprimento da Pedaço.
     * @param precPedaco
     * @param tipoMaterial
     * @param fornecedor
     * @param largPedaco Informar um valor double da largura da Pedaço.
     * @param espePedaco Informar um valor double da espessura do Pedaço.
     * @param incData Informar um valor Date do dia da Inclusão.
     */
    public static void adicionarPedaco(int quantPeca, double compPedaco, double largPedaco, double espePedaco, double precPedaco, String tipoMaterial) {
        Material.adicionarMaterial(getTABELA(), quantPeca, compPedaco, largPedaco, espePedaco, precPedaco, tipoMaterial,null);
    }

    /**
     * <b>Este Metodo Editar informação na tabela pedaço no banco.</b>
     *
     * @param idChapa Informar um valor inteiro do Id da Chapa.
     * @param idPeca Informar um valor inteiro do Id da Peca.
     * @param compPedaco Informar um valor double da Comprimento da Pedaço.
     * @param largPedaco Informar um valor double da largura da Pedaço.
     * @param espePedaco Informar um valor double da espessura do Pedaço.
     * @param incData Informar um valor Date do dia da Inclusão do Pedaço.
     */
    public static void editarPedaco(int idChapa, int idPeca, double compPedaco, double largPedaco, double espePedaco, Date incData) {
        try {
            Pedaco();
            String sql = "";

            pst = conexao.prepareStatement(sql);
            pst.setDouble(1, compPedaco);
            pst.setDouble(2, largPedaco);
            pst.setDouble(3, espePedaco);
            int editar = pst.executeUpdate();
            if (editar == 0) {
                ModuloConector.fecharConexao(conexao, rs, pst, stmt);
                Messagem.chamarTela(Messagem.EDITADO(sql));
            }
        } catch (NullPointerException e) {
            Messagem.chamarTela(e);
        } catch (Exception e) {
            Messagem.chamarTela(e);
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
            Pedaco();
            String sql = "delete from " + TABELA + " where espe = ?";
            pst = conexao.prepareStatement(sql);
            pst.setDouble(1, espePedaco);
            int excluir = pst.executeUpdate();
            if (excluir == 0) {
                ModuloConector.fecharConexao(conexao, rs, pst, stmt);
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
     * @param incData Informar um valor Date do dia da Inclusão do Pedaço.
     * @param ou Informar um valor boolean:
     * <p>
     * Se <b>ou</b> for <b>true</b>: adicionar na String interna a expresão
     * ="or";</p>
     * <p>
     * senão adicionar na String interna a expresão ="and".</p>
     */
    public static void pesquisarPedaco(int idChapa, int idPeca, double compPedaco, double largPedaco, double espePedaco, Date incData, boolean ou) {
        try {
            Pedaco();
            int qt = 0, cqt, lqt, eqt;
            boolean c, l, e;
            String a;
            if (ou == false) {
                a = "and";
            } else {
                a = "or";
            }
            String sql = "select idchapa from " + TABELA + " where ";
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
                ModuloConector.fecharConexao(conexao, rs, pst, stmt);
            }
        } catch (NullPointerException e) {
            Messagem.chamarTela("Nulo");
        } catch (SQLException e) {
            Messagem.chamarTela(e);
        }
    }

    public static int obterIdPedaco(String tipoMaterial, double espessura) {
        return Material.obterIdMaterial(getTABELA(), tipoMaterial, espessura);
    }
// Gets e Sets

    /** <b>Este Metodo retornar o ID da Chapa.</b>
     *
     * @return Retonar um valor inteiro do Id da Chapa.
     */
    public static int getIdChapa() {
        return idChapa;
    }

    /** <b>Este Metodo setar a informação mo ID da Chapa.</b>
     *
     * @param idChapa Informar um valor inteiro do Id da Chapa.
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
        return comp;
    }

    /** <b>Este Metodo setar a informação do Comprimento da Pedaço.</b>
     *
     * @param compPedaco Informar um valor double da Comprimento da Pedaço.
     */
    public static void setComp(double compPedaco) {
        Pedaco.comp = comp;
    }

    /** <b>Este Metodo Retornar a informação do largura do pedaço.</b>
     *
     * @return Retornar um valor double da largura da Pedaço.
     */
    public static double getLarg() {
        return larg;
    }

    /** <b>Este Metodo setar a informação da largura do Pedaço.</b>
     *
     * @param largPedaco Informar um valor double da largura do Pedaço.
     */
    public static void setLarg(double largPedaco) {
        Pedaco.larg = largPedaco;
    }

    /** <b>Este Metodo Retornar a informação da Espessura do Pedaço.</b>
     *
     * @return Retornar um valor double da espessura do Pedaço.
     */
    public static double getEspe() {
        return espe;
    }

    /** <b>Este Metodo setar a informação da Espessura do Pedaço.</b>
     *
     * @param espePedaco Informar um valor double da Espessura do Pedaço.
     */
    public static void setEspe(double espePedaco) {
        Pedaco.espe = espePedaco;
    }

    /** <b>Este Metodo Retornar a informação da Preço do Pedaço.</b>
     *
     * @return Retornar um valor double da Preco do Pedaço.
     */
    public static double getPrec() {
        return prec;
    }

    /** <b>Este Metodo setar a informação da Preço do Pedaço.</b>
     *
     * @param precPedaco Informar um valor double da Preco do Pedaço.
     */
    public static void setPrec(double precPedaco) {
        Pedaco.prec = precPedaco;
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
