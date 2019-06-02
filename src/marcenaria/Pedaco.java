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

    public static void main(String[] args) {
        criadoPedaco();
        deletaPedaco();
    }

    private static void Pedaco() {
        conexao = ModuloConector.getConecction();
    }

    /**
     * 
     */
    public static void criadoPedaco() {
        try {
            Pedaco();
            String sql = "create table if not exists " + TABELA + "("
                    + "id int auto_increment primary key,"
                    + "idchapa int default '0',"
                    + "idpeca int default 0,"
                    + "comp double not null,"
                    + "larg double not null,"
                    + "espe double not null, "
                    + "foreign key (idchapa) references Chapa(id), "
                    + "foreign key(idpeca) references peca(id))";
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
            Messagem.chamarTela(TABELA);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * 
     */
    public static void deletaPedaco() {
        try {
            Pedaco();
            String sql = "drop if exists " + TABELA;
            stmt = conexao.createStatement();
            Messagem.deletadaTabela(TABELA);
            int deleta = Messagem.getDeleta();
            if (deleta == JOptionPane.OK_OPTION) {
                int deletado = stmt.executeUpdate(sql);
                if (deletado == 0) {
                    ModuloConector.fecharConexao(conexao, rs, pst, stmt);
                    Messagem.chamarTela(Messagem.tabelaDeletada(TABELA));
                }
            }
        } catch (NullPointerException e) {
            Messagem.chamarTela(e);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * @param comp
     * @param larg
     * @param espe
     */
    public static void adicionarPedaco(double comp, double larg, double espe) {
        try {
            String sql = "insert into from " + TABELA + " (comp,larg,espe) values (?,?,?)";
            pst = conexao.prepareStatement(sql);
            pst.setDouble(1, comp);
            pst.setDouble(2, larg);
            pst.setDouble(3, espe);
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
     * @param comp
     * @param larg
     * @param espe
     */
    public static void editarPedaco(double comp, double larg, double espe) {
        try {
            String sql = "";
            pst = conexao.prepareStatement(sql);
            pst.setDouble(1, comp);
            pst.setDouble(2, larg);
            pst.setDouble(3, espe);
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
     * @param comp
     * @param larg
     * @param espe
     */
    public static void excluirPedaco(double comp, double larg, double espe) {
        try {
            String sql = "delete from " + TABELA + " where espe = ?";
            pst = conexao.prepareStatement(sql);
            pst.setDouble(1, espe);
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
     * @param comp
     * @param larg
     * @param espe
     */
    public static void pesquisarPedaco(double comp, double larg, double espe) {
        try {
            String sql = "select * from " + TABELA + " where espe = ?";
            pst = conexao.prepareStatement(sql);
            pst.setDouble(1, espe);
            rs = pst.executeQuery();
            if (rs.next()) {

            }
        } catch (NullPointerException e) {
            Messagem.chamarTela(e);
        } catch (Exception e) {
            Messagem.chamarTela(e);
        }
    }
 // Gets e Sets
    /**
     * @return
     */
    public static int getIdChapa() {
        return idChapa;
    }

    /**
     * @param idChapa
     */
    public static void setIdChapa(int idChapa) {
        Pedaco.idChapa = idChapa;
    }

    /**
     * @return
     */
    public static int getIdPeca() {
        return idPeca;
    }

    /**
     * @param idPeca
     */
    public static void setIdPeca(int idPeca) {
        Pedaco.idPeca = idPeca;
    }

    /**
     * @return
     */
    public static double getComp() {
        return comp;
    }

    /**
     * @param comp
     */
    public static void setComp(double comp) {
        Pedaco.comp = comp;
    }

    /**
     * @return
     */
    public static double getLarg() {
        return larg;
    }

    /**
     * @param larg
     */
    public static void setLarg(double larg) {
        Pedaco.larg = larg;
    }

    /**
     * @return
     */
    public static double getEspe() {
        return espe;
    }

    /**
     * @param espe
     */
    public static void setEspe(double espe) {
        Pedaco.espe = espe;
    }

    /**
     * @return
     */
    public static double getPrec() {
        return prec;
    }

    /**
     * @param prec
     */
    public static void setPrec(double prec) {
        Pedaco.prec = prec;
    }

    /**
     * @return
     */
    public static Date getIncData() {
        return incData;
    }

    /**
     * @param incData
     */
    public static void setIncData(Date incData) {
        Pedaco.incData = incData;
    }

}
