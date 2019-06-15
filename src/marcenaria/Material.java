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
     * Este metodo Adicionar informações em uma determinada Tabela conforme o
     * paramentro Tabela
     *
     * @param Tabela Informar um valor String para tabela de Material
     * @param tipoMaterial Informar um valor inteiro do Tipo do Material.
     * @param quantidade Informar um valor inteiro da quantidade do Material.
     * @param comprimento Informar um valor double do comprimento do Material.
     * @param largura Informar um valor double da largura do Material.
     * @param espessura Informar um valor double da espessura do Material.
     * @param preco Informar um valor double do preço do Material.
     *
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
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * FAZENDO- Este metodo Editar informações em uma determinada Tabela
     * conforme o paramentro Tabela
     *
     * @param Tabela Informar um valor String para tabela de Material
     * @param tipoMaterial Informar um valor inteiro do Tipo do Material.
     * @param quantidade Informar um valor inteiro da quantidade do Material.
     * @param comprimento Informar um valor double do comprimento do Material.
     * @param largura Informar um valor double da largura do Material.
     * @param espessura Informar um valor double da espessura do Material.
     * @param preco Informar um valor double do preço do Material.
     */
    public static void editarMaterial(String Tabela, String tipoMaterial, int quantidade, double comprimento, double largura, double espessura, double preco) {
        try {
            Material();
            String sql = "";
            if (Tabela.equalsIgnoreCase(Chapa.getTABELA())) {
                sql = "insert into " + Tabela + "(tipoMaterial,quantidade,comprimento,largura,espessura,preco) values (?,?,?,?,?)";
            } else if (Tabela.equalsIgnoreCase(Peca.getTABELA())) {
                sql = "insert into " + Tabela + "(tipoMaterial,quantidade,comprimento,largura,espessura,preco,id" + Chapa.getTABELA() + ") values (?,?,?,?,?,?)";
            } else if(Tabela.equalsIgnoreCase(Pedaco.getTABELA())){
                sql = "";
            }else{
                Messagem.chamarTela("Classe não criada");
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
            }else if(Tabela.equalsIgnoreCase(Pedaco.getTABELA())){
            
        }else{
                Messagem.chamarTela("erro !!!");
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
     * @param Tabela Informar um valor String para tabela de Material
     * @param tipoMaterial Informar um valor inteiro do Tipo do Material.
     * @param quantidade Informar um valor inteiro da quantidade do Material.
     * @param comprimento Informar um valor double do comprimento do Material.
     * @param largura Informar um valor double da largura do Material.
     * @param espessura Informar um valor double da espessura do Material.
     * @param preco Informar um valor double do preço do Material.
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
            int editado = pst.executeUpdate();
            if (editado == 0) {
                JOptionPane.showMessageDialog(null, "Foi excluido da Tabela " + Tabela + "" + quantidade + "" + comprimento + "X" + largura + "X" + espessura);
            }
        } catch (Exception e) {
        }
    }

    /**
     * FAZENDO
     *
     * @param Tabela Informar um valor String para tabela de Material
     * @param tipoMaterial Informar um valor inteiro do Tipo do Material.
     * @param quantidade Informar um valor inteiro da quantidade do Material.
     * @param comprimento Informar um valor double do comprimento do Material.
     * @param largura Informar um valor double da largura do Material.
     * @param espessura Informar um valor double da espessura do Material.
     * @param preco Informar um valor double do preço do Material.
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
     * @param Tabela Informar um valor String para tabela de Material
     */
    public static void criarMaterial(String Tabela) {
        try {
            Material();
            String sql = "create table if not exists " + Tabela + "("
                    + "id" + Tabela + " int primary key auto_increment,"
                    + "quantidade int default 0,"
                    + "comprimento double,"
                    + "largura double,"
                    + "espessura double,"
                    + "preco double, "
                    + "tipoMaterial varchar(30)";
            if (Tabela.equalsIgnoreCase(Chapa.getTABELA())) {
                sql += ")";
            } else if (Tabela.equalsIgnoreCase(Peca.getTABELA())) {
                sql += ","
                        + "id" + Chapa.getTABELA() + " int not null,"
                        + "foreign key(id" + Chapa.getTABELA()
                        + ") references chapa(id" + Chapa.getTABELA() + "))";
            } else if (Tabela.equalsIgnoreCase(Pedaco.getTABELA())) {
                sql += ",id" + Chapa.getTABELA() + " int default '0',"
                        + "id" + Peca.getTABELA() + " int default 0,"
                        + "incData Timestamp,"
                        + "foreign key (id" + Chapa.getTABELA() 
                        + ") references " + Chapa.getTABELA() 
                        + " (id" + Chapa.getTABELA() + "), "
                        + "foreign key (id" + Peca.getTABELA() 
                        + ") references " + Peca.getTABELA() 
                        + " (id" + Peca.getTABELA() + "))";
            }
            stmt = conexao.createStatement();
            Messagem.criadoTabela(Tabela);
            if (Messagem.getCriada() == JOptionPane.OK_OPTION) {
                int adicionada = stmt.executeUpdate(sql);
                if (adicionada == 0) {
                    Messagem.chamarTela(Messagem.tabelaCriada(Tabela));
                    ModuloConector.fecharConexao(conexao, rs, pst, stmt);
                }
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(null, e);
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, e);
        } catch (SQLException e) {
            if (Tabela.equalsIgnoreCase(Pedaco.getTABELA())) {
                // primeiro tem que criar  a Tabela Chapa para depois a Tabela Peça para depois Tabela Pedaco
                criarMaterial(Chapa.getTABELA());
                criarMaterial(Peca.getTABELA());
                criarMaterial(Tabela);
            } else if (Tabela.equalsIgnoreCase(Peca.getTABELA())) {
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
     * @param Tabela Informar um valor String para tabela de Material
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
                deletarMaterial(Pedaco.getTABELA());
                deletarMaterial(Peca.getTABELA());
                deletarMaterial(Tabela);
            } else if (Tabela.equalsIgnoreCase(Peca.getTABELA())) {
                deletarMaterial(Pedaco.getTABELA());
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
     * @return Retornar um valor inteiro do Tipo do Material.
     */
    public static String getTipoMaterial() {
        return tipoMaterial;
    }

    /**
     * @param tipoMaterial Informar um valor inteiro do Tipo do Material.
     */
    public static void setTipoMaterial(String tipoMaterial) {
        Material.tipoMaterial = tipoMaterial;
    }

    /**
     * @return Retornar um valor inteiro da quantidade do Material.
     */
    public static int getQuantMaterial() {
        return quantMaterial;
    }

    /**
     * @param quantMaterial Informar um valor inteiro da quantidade do Material.
     */
    public static void setQuantMaterial(int quantMaterial) {
        Material.quantMaterial = quantMaterial;
    }

    /**
     * @return Retornar um valor inteiro do id do Material.
     */
    public static int getIdMaterial() {
        return idMaterial;
    }

    /**
     * @param idMaterial Informar um valor inteiro do id do Material.
     */
    public static void setIdMaterial(int idMaterial) {
        Material.idMaterial = idMaterial;
    }

    /**
     * @return Retornar um valor double do comprimento do Material.
     */
    public static double getComprMaterial() {
        return comprMaterial;
    }

    /**
     * @param comprMaterial Informar um valor double do comprimento do Material.
     */
    public static void setComprMaterial(double comprMaterial) {
        Material.comprMaterial = comprMaterial;
    }

    /**
     * @return Retornar um valor double da largura do Material.
     */
    public static double getLargMarterial() {
        return largMarterial;
    }

    /**
     * @param largMarterial Informar um valor double da largura do Material.
     */
    public static void setLargMarterial(double largMarterial) {
        Material.largMarterial = largMarterial;
    }

    /**
     * @return Retornar um valor double da espessura do Material.
     */
    public static double getEspesMaterial() {
        return espesMaterial;
    }

    /**
     * @param espesMaterial Informar um valor double da espessura do Material.
     */
    public static void setEspesMaterial(double espesMaterial) {
        Material.espesMaterial = espesMaterial;
    }

    /**
     * @return Retornar um valor double do preço do Material.
     */
    public static double getPrecMaterial() {
        return precMaterial;
    }

    /**
     * @param precMaterial Informar um valor double do preço do Material.
     */
    public static void setPrecMaterial(double precMaterial) {
        Material.precMaterial = precMaterial;
    }

}
