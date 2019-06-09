/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marcenaria;

import java.sql.*;
import javax.swing.JOptionPane;
import marcenaria.Const.Messagem;
import marcenaria.dado.ModuloConector;

/**
 *
 * @author Carlos
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
     *
     * @param args Metodo de teste
     */

    public static void main(String[] args) {
        criadoPedaco();
        deletaPedaco();
    }

    /**
     * Abre a conexão com o banco de dado
     */
    private static void Pedaco() {
        conexao = ModuloConector.getConecction();
    }

    /**
     *
     */
    public static void criadoPedaco() {
        String sql = "";
        try {
            Pedaco();
            sql = "create table if not exists " + TABELA + "("
                    + "id int auto_increment primary key,"
                    + "id" + Chapa.getTABELA() + " int default '0',"
                    + "id" + Peca.getTABELA() + " int default 0,"
                    + "comp double not null,"
                    + "larg double not null,"
                    + "espe double not null, "
                    + "incData Timestamp,"
                    + "foreign key (id" + Chapa.getTABELA() + ") references " + Chapa.getTABELA() + " (id" + Chapa.getTABELA() + "), "
                    + "foreign key (id" + Peca.getTABELA() + ") references " + Peca.getTABELA() + " (id" + Peca.getTABELA() + "))";
            stmt = conexao.createStatement();
            Messagem.criadoTabela(TABELA);
            int criada = Messagem.getCriada();
            if (criada == JOptionPane.OK_OPTION) {
                int criado = stmt.executeUpdate(sql);
                if (criado == 0) {
                    ModuloConector.fecharConexao(conexao, rs, pst, stmt);
                    Messagem.chamarTela(Messagem.tabelaCriada(TABELA));
                }
            }
        } catch (NullPointerException e) {
            Messagem.chamarTela("variavel nular");
        } catch (SQLSyntaxErrorException ssee) {
            Messagem.chamarTela(ssee);
            Messagem.chamarTela(sql);
            Chapa.criadaChapa();
            Peca.criadaPeca();
            Pedaco.criadoPedaco();
        } catch (SQLException se) {
            Messagem.chamarTela(se);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     *
     */
    public static void deletaPedaco() {
        Material.deletarMaterial(TABELA);
    }

    /**
     * @param idChapa Informar um valor inteiro do Id da Chapa
     * @param idPeca Informar um valor inteiro do Id da Peca
     * @param compPedaco Informar um valor double da Comprimento da Pedaço
     * @param largPedaco Informar um valor double da largura da Pedaço
     * @param espePedaco Informar um valor double da espessura do Pedaço
     * @param incData Informar um valor Date do dia da Inclusão
     */
    public static void adicionarPedaco(int idChapa, int idPeca, double compPedaco, double largPedaco, double espePedaco, Date incData) {
        try {
            Pedaco();
            String sql = "insert into from " + TABELA + " (comp,larg,espe) values (?,?,?)";
            pst = conexao.prepareStatement(sql);
            pst.setDouble(1, compPedaco);
            pst.setDouble(2, largPedaco);
            pst.setDouble(3, espePedaco);
            int adicionar = pst.executeUpdate();
            if (adicionar == 0) {
                Messagem.chamarTela(Messagem.ADICIONADO(sql));
            }
        } catch (NullPointerException e) {
            Messagem.chamarTela(e);
        } catch (Exception e) {
            Messagem.chamarTela(e);
        }
    }

    /**
     * @param idChapa Informar um valor inteiro do Id da Chapa
     * @param idPeca Informar um valor inteiro do Id da Peca
     * @param compPedaco Informar um valor double da Comprimento da Pedaço
     * @param largPedaco Informar um valor double da largura da Pedaço
     * @param espePedaco Informar um valor double da espessura do Pedaço
     * @param incData Informar um valor Date do dia da Inclusão
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
     * @param idChapa Informar um valor inteiro do Id da Chapa
     * @param idPeca Informar um valor inteiro do Id da Peca
     * @param compPedaco Informar um valor double da Comprimento da Pedaço
     * @param largPedaco Informar um valor double da largura da Pedaço
     * @param espePedaco Informar um valor double da espessura do Pedaço
     * @param incData Informar um valor Date do dia da Inclusão
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
     * @param idChapa Informar um valor inteiro do Id da Chapa
     * @param idPeca Informar um valor inteiro do Id da Peca
     * @param compPedaco Informar um valor double da Comprimento da Pedaço
     * @param largPedaco Informar um valor double da largura da Pedaço
     * @param espePedaco Informar um valor double da espessura do Pedaço
     * @param incData Informar um valor Date do dia da Inclusão
     * @param ou
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
            Messagem.chamarTela(e);
        } catch (Exception e) {
            Messagem.chamarTela(e);
        }
    }
    // Gets e Sets

    /**
     * @return Retonar um valor inteiro do Id da Chapa
     */
    public static int getIdChapa() {
        return idChapa;
    }

    /**
     * @param idChapa Informar um valor inteiro do Id da Chapa
     */
    public static void setIdChapa(int idChapa) {
        Pedaco.idChapa = idChapa;
    }

    /**
     * @return Retornar um valor inteiro do Id da Peca
     */
    public static int getIdPeca() {
        return idPeca;
    }

    /**
     * @param idPeca Informar um valor inteiro do Id da Peca
     */
    public static void setIdPeca(int idPeca) {
        Pedaco.idPeca = idPeca;
    }

    /**
     * @return Retornar um valor double da Comprimento da Pedaço
     */
    public static double getComp() {
        return comp;
    }

    /**
     * @param compPedaco Informar um valor double da Comprimento da Pedaço
     */
    public static void setComp(double compPedaco) {
        Pedaco.comp = comp;
    }

    /**
     * @return Retornar um valor double da largura da Pedaço
     */
    public static double getLarg() {
        return larg;
    }

    /**
     * @param largPedaco Informar um valor double da largura da Pedaço
     */
    public static void setLarg(double largPedaco) {
        Pedaco.larg = largPedaco;
    }

    /**
     * @return Retornar um valor double da espessura do Pedaço
     */
    public static double getEspe() {
        return espe;
    }

    /**
     * @param espePedaco Informar um valor double da espessura do Pedaço
     */
    public static void setEspe(double espePedaco) {
        Pedaco.espe = espePedaco;
    }

    /**
     * @return Retornar um valor double da Preco do Pedaço
     */
    public static double getPrec() {
        return prec;
    }

    /**
     * @param precPedaco Informar um valor double da Preco do Pedaço
     */
    public static void setPrec(double precPedaco) {
        Pedaco.prec = precPedaco;
    }

    /**
     * @return Retornar um valor Date do dia da Inclusão.
     */
    public static Date getIncData() {
        return incData;
    }

    /**
     * @param incData Informar um valor Date do dia da Inclusão.
     */
    public static void setIncData(Date incData) {
        Pedaco.incData = incData;
    }

    /**
     * @return Retornar uma String com nome da tabela
     */
    public static String getTABELA() {
        return TABELA;
    }

}
