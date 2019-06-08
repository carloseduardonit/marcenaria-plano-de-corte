/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marcenaria;

import java.awt.HeadlessException;
import java.sql.*;
import javax.swing.JOptionPane;
import marcenaria.Const.Messagem;
import marcenaria.dado.ModuloConector;

/**
 * 18/05/2019
 *
 * @author Carlos
 */
public class Material {

    static Connection conexao;
    static ResultSet rs;
    static PreparedStatement pst;
    static Statement stmt;
    private static String tipoMaterial;
    private static int quantMaterial, idMaterial;
    private static double comprMaterial, largMarterial, espesMaterial, precMaterial;

    /**
     * OK
     */
    public static void Material() {
        conexao = ModuloConector.getConecction();
    }

    /**
     * FAZENDO
     *
     * @param Tabela
     * @param tipoMaterial
     * @param quantidade
     * @param comprimento
     * @param largura
     * @param espessura
     * @param preco
     */
    public static void adicionarMaterial(String Tabela, String tipoMaterial, int quantidade, double comprimento, double largura, double espessura, double preco) {
        try {
            Material();
            String sql = "";
            if (Tabela.equalsIgnoreCase(Chapa.getTABELA())) {
                sql = "insert into " + Tabela + "(tipoMaterial, quantidade,comprimento,largura,espessura,preco) values (?,?,?,?,?,?)";
            } else if (Tabela.equalsIgnoreCase(Peca.getTABELA())) {
                sql = "insert into " + Tabela + "(tipoMaterial, quantidade,comprimento,largura,espessura,preco,id" + Chapa.getTABELA() + ") values (?,?,?,?,?,?)";
            }
            pst = conexao.prepareStatement(sql);
            pst.setString(1, tipoMaterial);
            pst.setInt(1, quantidade);
            pst.setDouble(2, comprimento);
            pst.setDouble(3, largura);
            pst.setDouble(4, espessura);
            pst.setDouble(5, preco);
            if (Tabela.equalsIgnoreCase(Peca.getTABELA())) {
                pst.setDouble(6, preco);
            }
            int inserido = pst.executeUpdate();
            if (inserido == 0) {
                JOptionPane.showMessageDialog(null, "Foi inserido n Tabela " + Tabela + "" + quantidade + "" + comprimento + "X" + largura + "X" + espessura);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * FAZENDO
     *
     * @param Tabela
     * @param tipoMaterial
     * @param quantidade
     * @param comprimento
     * @param largura
     * @param espessura
     * @param preco
     */
    public static void editarMaterial(String Tabela, String tipoMaterial, int quantidade, double comprimento, double largura, double espessura, double preco) {
        try {
            Material();
            String sql = "";
            if (Tabela.equalsIgnoreCase(Chapa.getTABELA())) {
                sql = "insert into " + Tabela + "(tipoMaterial,quantidade,comprimento,largura,espessura,preco) values (?,?,?,?,?)";
            } else if (Tabela.equalsIgnoreCase(Peca.getTABELA())) {
                sql = "insert into " + Tabela + "(tipoMaterial,quantidade,comprimento,largura,espessura,preco,id" + Chapa.getTABELA() + ") values (?,?,?,?,?,?)";
            }
            pst = conexao.prepareStatement(sql);
            pst.setString(1, tipoMaterial);
            pst.setInt(1, quantidade);
            pst.setDouble(2, comprimento);
            pst.setDouble(3, largura);
            pst.setDouble(4, espessura);
            pst.setDouble(5, preco);
            if (Tabela.equalsIgnoreCase(Peca.getTABELA())) {
                pst.setDouble(6, preco);
            }
            int inserido = pst.executeUpdate();
            if (inserido == 0) {
                JOptionPane.showMessageDialog(null, "Foi inserido n Tabela " + Tabela + "" + quantidade + "" + comprimento + "X" + largura + "X" + espessura);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * FAZENDO
     *
     * @param Tabela
     * @param tipoMaterial
     *
     * @param quantidade
     * @param comprimento
     * @param largura
     * @param espessura
     * @param preco
     */
    public static void excluirMaterial(String Tabela, String tipoMaterial, int quantidade, double comprimento, double largura, double espessura, double preco) {
        try {
            Material();
            String sql = "";
            if (Tabela.equalsIgnoreCase(Chapa.getTABELA())) {
                if (!tipoMaterial.isEmpty() && !String.valueOf(espessura).isEmpty()) {
                    sql = "drop table " + Tabela + "where tipoMaterial =? and espessura =?";
                } else if (!tipoMaterial.isEmpty()) {
                    sql = "drop table " + Tabela + "where ";
                }
            } else if (Tabela.equalsIgnoreCase(Peca.getTABELA())) {
                sql = "drop table " + Tabela + "(quantidade,comprimento,largura,espessura,preco,id" + Chapa.getTABELA() + ") values (?,?,?,?,?,?,?)";
            }
            pst = conexao.prepareStatement(sql);
            pst.setString(1, tipoMaterial);
            pst.setInt(2, quantidade);
            pst.setDouble(3, comprimento);
            pst.setDouble(3, largura);
            pst.setDouble(4, espessura);
            pst.setDouble(5, preco);
            if (Tabela.equalsIgnoreCase(Peca.getTABELA())) {
                pst.setDouble(6, preco);
            }
            int inserido = pst.executeUpdate();
            if (inserido == 0) {
                JOptionPane.showMessageDialog(null, "Foi excluido da Tabela " + Tabela + "" + quantidade + "" + comprimento + "X" + largura + "X" + espessura);
            }
        } catch (Exception e) {
        }
    }

    /**
     * FAZENDO
     *
     * @param Tabela
     * @param quantidade
     * @param comprimento
     * @param largura
     * @param espessura
     * @param preco
     */
    public static void pesquisarMaterial(String Tabela, String tipoMaterial, int quantidade, double comprimento, double largura, double espessura, double preco) {
        try {
            Material();
            String sql = "";
            if (Tabela.equalsIgnoreCase(Chapa.getTABELA())) {
                if (!tipoMaterial.isEmpty()) {
                    sql = "select * from " + Tabela + " where id" + Tabela + " =?";
                }

            } else if (Tabela.equalsIgnoreCase(Peca.getTABELA())) {
                sql = "select * from " + Tabela + " where id" + Tabela + " =?";
            }
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, quantidade);
            rs = pst.executeQuery();
            if (rs.next()) {
                setIdMaterial(rs.getInt(1));
                setTipoMaterial(rs.getString(2));
                setComprMaterial(rs.getDouble(3));
                setLargMarterial(rs.getDouble(4));
                setEspesMaterial(rs.getDouble(5));
                setPrecMaterial(rs.getDouble(6));
                JOptionPane.showMessageDialog(null, "a");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * TESTADO E OK
     *
     * @param Tabela
     */
    public static void criarMaterial(String Tabela) {
        try {
            Material();
            String sql = "create table if not exists " + Tabela + "("
                    + "id" + Tabela + " int primary key auto_increment,"
                    + "tipoMaterial varchar(30),"
                    + "quantidade int default 0,"
                    + "comprimento double,"
                    + "largura double,"
                    + "espessura double,"
                    + "preco double";
            if (Tabela.equalsIgnoreCase(Chapa.getTABELA())) {
                sql += ")";
            } else if (Tabela.equalsIgnoreCase(Peca.getTABELA())) {
                sql += ","
                        + "id" + Chapa.getTABELA() + " int not null,"
                        + "foreign key(id" + Chapa.getTABELA()
                        + ") references chapa(id" + Chapa.getTABELA() + "))";
            }
            stmt = conexao.createStatement();
            int adiciona = JOptionPane.showConfirmDialog(null, "Deseja criar a tabela " + Tabela, Tabela, JOptionPane.OK_CANCEL_OPTION);
            if (adiciona == JOptionPane.OK_OPTION) {
                int adicionada = stmt.executeUpdate(sql);
                if (adicionada == 0) {
                    JOptionPane.showMessageDialog(null, "Foi adicionada com sucesso a tabela " + Tabela);
                    ModuloConector.fecharConexao(conexao, rs, pst, stmt);
                }
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(null, e);
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, e);
        } catch (SQLException e) {
            if (Tabela.equalsIgnoreCase(Peca.getTABELA())) {
                // primeiro tem que criar  a Tabela Chapa para depois a Tabela Peça
                criarMaterial(Chapa.getTABELA());
                criarMaterial(Tabela);
            } else {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    /**
     * TESTADO E OK
     *
     * @param Tabela
     */
    public static void deletarMaterial(String Tabela) {
        try {
            Material();
            String sql = "drop table if exists " + Tabela;
            stmt = conexao.createStatement();
            int deleta = JOptionPane.showConfirmDialog(null, "Desejar excluir a  tabela " + Tabela, Tabela, JOptionPane.OK_CANCEL_OPTION);
            //System.out.println(deleta + "X" + JOptionPane.OK_OPTION);
            if (deleta == JOptionPane.OK_OPTION) {
                int deletado = stmt.executeUpdate(sql);
                //System.out.println("" + deletado);
                if (deletado == 0) {
                    JOptionPane.showMessageDialog(null, "Foi Exclui com sucesso  a tabela " + Tabela);
                    ModuloConector.fecharConexao(conexao, rs, pst, stmt);
                }
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            if (Tabela.equalsIgnoreCase(Chapa.getTABELA())) {
                // Primeiro deleta a Tabela Peca depois a Tabela Chapa
                Pedaco.deletaPedaco();
                deletarMaterial(Peca.getTABELA());
                deletarMaterial(Tabela);
            } else if (Tabela.equalsIgnoreCase(Peca.getTABELA())) {
                Pedaco.deletaPedaco();
                deletarMaterial(Tabela);
            } else if (Tabela.equalsIgnoreCase(Pedaco.getTABELA())) {
                deletarMaterial(Tabela);
            } else {
                Messagem.chamarTela(Tabela + " esta  não foi criada o elseif");
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * @return
     */
    public static String getTipoMaterial() {
        return tipoMaterial;
    }

    /**
     * @param tipoMaterial
     */
    public static void setTipoMaterial(String tipoMaterial) {
        Material.tipoMaterial = tipoMaterial;
    }

    /**
     * @return
     */
    public static int getQuantMaterial() {
        return quantMaterial;
    }

    /**
     * @param quantMaterial
     */
    public static void setQuantMaterial(int quantMaterial) {
        Material.quantMaterial = quantMaterial;
    }

    /**
     * @return
     */
    public static int getIdMaterial() {
        return idMaterial;
    }

    /**
     * @param idMaterial
     */
    public static void setIdMaterial(int idMaterial) {
        Material.idMaterial = idMaterial;
    }

    /**
     * @return
     */
    public static double getComprMaterial() {
        return comprMaterial;
    }

    /**
     * @param comprMaterial
     */
    public static void setComprMaterial(double comprMaterial) {
        Material.comprMaterial = comprMaterial;
    }

    /**
     * @return
     */
    public static double getLargMarterial() {
        return largMarterial;
    }

    /**
     * @param largMarterial
     */
    public static void setLargMarterial(double largMarterial) {
        Material.largMarterial = largMarterial;
    }

    /**
     * @return
     */
    public static double getEspesMaterial() {
        return espesMaterial;
    }

    /**
     * @param espesMaterial
     */
    public static void setEspesMaterial(double espesMaterial) {
        Material.espesMaterial = espesMaterial;
    }

    /**
     * @return
     */
    public static double getPrecMaterial() {
        return precMaterial;
    }

    /**
     * @param precMaterial
     */
    public static void setPrecMaterial(double precMaterial) {
        Material.precMaterial = precMaterial;
    }

}
