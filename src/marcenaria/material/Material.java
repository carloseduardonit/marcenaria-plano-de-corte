/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marcenaria.material;

import java.sql.*;
import javax.swing.JOptionPane;
import marcenaria.Const.Messagem;
import marcenaria.dado.ModuloConector;
import marcenaria.pessoa.Fornecedor;
import marcenaria.pessoa.Pessoa;

/**
 * @since 18/05/2019
 * @version 1.0
 * @author Carlos Eduardo dos Santos Figueiredo
 */
public class Material {

    static Connection conexao;
    static ResultSet rs;
    static ResultSetMetaData rsmd;
    static PreparedStatement pst;
    static Statement stmt;

    private static final String TABELA = Material.class.getSimpleName();
    private static String tipoMaterial;
    private static int quantMaterial, idMaterial;
    private static double comprMaterial, largMarterial, espesMaterial, precMaterial;

    /**
     * Este Metodo faz a conexão do banco de dados.
     *
     * @since 18/05/2019
     * @version 1.0
     */
    public static void Material() {
        conexao = ModuloConector.getConecction();
    }

    /**
     * Este metodo Adicionar informações em uma determinada Tabela conforme o
     * paramentro Tabela
     *
     * @since 18/05/2019
     * @version 1.0
     * @param Tabela Informar um valor String para tabela de Material
     * @param tipoMaterial Informar um valor inteiro do Tipo do Material.
     * @param quantidade Informar um valor inteiro da quantidade do Material.
     * @param comprimento Informar um valor double do comprimento do Material.
     * @param largura Informar um valor double da largura do Material.
     * @param espessura Informar um valor double da espessura do Material.
     * @param preco Informar um valor double do preço do Material.
     * @param Fornecedo Informar um valor String do Fornecedor do Material.
     *
     */
    public static void adicionarMaterial(String Tabela, int quantidade, double comprimento, double largura,
            double espessura, double preco, String tipoMaterial, String Fornecedo) {
       
    }

    /**
     * FAZENDO- Este metodo Editar informações em uma determinada Tabela
     * conforme o paramentro Tabela
     *
     * @since 18/05/2019
     * @version 1.0
     * @param Tabela Informar um valor String para tabela de Material
     * @param tipoMaterial Informar um valor inteiro do Tipo do Material.
     * @param quantidade Informar um valor inteiro da quantidade do Material.
     * @param comprimento Informar um valor double do comprimento do Material.
     * @param largura Informar um valor double da largura do Material.
     * @param espessura Informar um valor double da espessura do Material.
     * @param preco Informar um valor double do preço do Material.
     * @param Fornecedo Informar um valor String do Fornecedor do Material.
     */
    public static void editarMaterial(String Tabela, String tipoMaterial, int quantidade, double comprimento,
            double largura, double espessura, double preco, String Fornecedo) {
        try {
            Material();
            String sql = "";
            if (Tabela.equalsIgnoreCase(Chapa.getTABELA())) {
                sql = "insert into " + Tabela
                        + "(tipoMaterial,quantidade,comprimento,largura,espessura,preco,Id" + Fornecedor.getTABELA() + ") values (?,?,?,?,?)";
            } else if (Tabela.equalsIgnoreCase(Peca.getTABELA())) {
                sql = "insert into " + Tabela + "(tipoMaterial,quantidade,comprimento,largura,espessura,preco,id"
                        + Chapa.getTABELA() + ") values (?,?,?,?,?,?)";
            } else if (Tabela.equalsIgnoreCase(Pedaco.getTABELA())) {
                sql = "inset into " + Tabela + "(tipoMaterial,quantidade,comprimento,largura,espessura,preco,id"
                        + Chapa.getTABELA() + ") values (?,?,?,?,?,?)";
            } else {
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
            } else if (Tabela.equalsIgnoreCase(Pedaco.getTABELA())) {
                pst.setDouble(6, preco);
            } else {
                Messagem.chamarTela("erro !!!");
            }
            int inserido = pst.executeUpdate();
            if (inserido == 0) {
                JOptionPane.showMessageDialog(null, "Foi inserido n Tabela " + Tabela + "" + quantidade + ""
                        + comprimento + "X" + largura + "X" + espessura);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * FAZENDO
     *
     **@since 18/05/2019
     * @version 1.0
     *
     * @param Tabela Informar um valor String para tabela de Material
     * @param tipoMaterial Informar um valor inteiro do Tipo do Material.
     * @param quantidade Informar um valor inteiro da quantidade do Material.
     * @param comprimento Informar um valor double do comprimento do Material.
     * @param largura Informar um valor double da largura do Material.
     * @param espessura Informar um valor double da espessura do Material.
     * @param preco Informar um valor double do preço do Material.
     * @param fornecedo Informar um valor String do Fornecedor do Material.
     */
    public static void excluirMaterial(String Tabela, String tipoMaterial, int quantidade, double comprimento,
            double largura, double espessura, double preco, String fornecedo) {
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
                sql = "drop table " + Tabela + "(quantidade,comprimento,largura,espessura,preco,id" + Chapa.getTABELA()
                        + ") values (?,?,?,?,?,?,?)";
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
                JOptionPane.showMessageDialog(null, "Foi excluido da Tabela " + Tabela + "" + quantidade + ""
                        + comprimento + "X" + largura + "X" + espessura);
            }
        } catch (Exception e) {
            Messagem.chamarTela(Tabela + " Excluir: " + e);
        }
    }

    /**
     **@since 18/05/2019
     * @version 1.0
     * @param Tabela Setar uma Informação de valor String para tabela de
     * Material
     * @param idPessoa Setar uma Informação de valor Inteiro do ID pessoa
     * @param row Setar uma Informação de valor Inteiro da quantidade de linha
     * @param colu Setar uma Informação de valor Inteiro da quantidade de coluna
     * @return Retornar um array de Informaçãode valor String da tabela de
     * Produto
     */
    public static String[][] TabeladeMaterial(String Tabela, int idPessoa, int row, int colu) {
        Material();
        String[][] tab = new String[row][colu];
        try {
            String sql = "select from " + Tabela + " from id" + Pessoa.getTABELA() + " = ?";

            pst = conexao.prepareStatement(sql);
            pst.setInt(1, idPessoa);
            rs = pst.executeQuery();

            for (int lin = 0; lin < tab.length; lin++) {
                if (rs.next()) {
                    for (int col = 0; col < tab[lin].length; col++) {
                        tab[lin][col] = rs.getString(col);
                        /*
                     * if (co == 0) { tab[lin][col] = rs.getString(co); } else if (col == 1) {
                     * tab[lin][col] = rs.getString(co); } else if (col == 2) { tab[lin][col] = 0; }
                     * else if (col == 3) { tab[lin][col] = 0; } else if (col == 4) { tab[lin][col]
                     * = 0; } else if (col == 5) { tab[lin][col] = 0; }
                         */
                    }
                }
            }

        } catch (SQLException se) {
            Messagem.chamarTela(Tabela + " TabeladeMaterial: " + se);
        }
        return tab;
    }

    /**
     * FAZENDO
     *
     **@since 18/05/2019
     * @version 1.0
     * @param Tabela Informar um valor String para tabela de Material
     * @param tipoMaterial Informar um valor String do Tipo do Material do
     * materia.
     * @param quantidade Informar um valor inteiro da quantidade do Material.
     * @param comprimento Informar um valor double do comprimento do Material.
     * @param largura Informar um valor double da largura do Material.
     * @param espessura Informar um valor double da espessura do Material.
     * @param preco Informar um valor double do preço do Material.
     * @param ou Setar uma informação de valor boolean
     */
    public static void pesquisarMaterial(String Tabela, String tipoMaterial, int quantidade, double comprimento, double largura, double espessura, double preco, boolean ou) {
        try {
            Material();
            String sql = "";
            if (Tabela.equalsIgnoreCase(Chapa.getTABELA())) {
                if (!tipoMaterial.isEmpty()) {
                    sql = "select  from " + Tabela + " where id" + Tabela + " =?";
                } else {

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
                Messagem.chamarTela("a");
            }
        } catch (Exception e) {
            Messagem.chamarTela(e);
        }
    }

    /**
     * Este Metodo obtem o numero de id do materia da tabela de Material.
     *
     * @since 18/05/2019
     * @version 1.0
     * @param Tabela setar uma Informação de valor String para tabela de
     * Material
     * @param tipoMaterial Setar uma Informação de valor String do Tipo do
     * Material do materia.
     * @param espessura Setar uma Informação de valor double da espessura do
     * Material.
     * @return Retornar uma informação de valor inteiro de id de material.
     */
    public static int obterIdMaterial(String Tabela, String tipoMaterial, double espessura) {
        int id = 0;
        try {
            Material();
            if (!Tabela.isEmpty() && !tipoMaterial.isEmpty() && espessura > 0) {
                String sql = "select id" + Tabela + " from " + Tabela + " where tipoMaterial = ? and espessura = ? ";
                pst = conexao.prepareStatement(sql);
                pst.setString(1, tipoMaterial);
                pst.setDouble(2, espessura);
                rs = pst.executeQuery();
                if (rs.next()) {
                    id = rs.getInt(1);
                    ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
                }
            } else {
                String erro = "";
                int count = 0;
                if (Tabela.isEmpty()) {
                    erro = "Tabela não Informada";
                    count++;
                }
                if (tipoMaterial.isEmpty()) {
                    if (count > 0) {
                        erro += ",\nTipo de Material não informado";
                    } else {
                        erro = "Tipo de Material não informado";
                    }
                }
                if (espessura <= 0) {
                    if (count > 0) {
                        erro += ",\nEspessura menor ou iqual a 0";
                    } else {
                        erro = "Espessura menor ou iqual a 0";
                    }
                }
                erro += ".";
                Messagem.chamarTela(erro);
            }
        } catch (Exception e) {
            Messagem.chamarTela(e);
            ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
        }
        return id;
    }

    // Gets e Sets
    /**
     * Este Metodo Retornar uma Informação de valor String do Tipo do Material
     * do material.
     *
     **@since 18/05/2019
     * @version 1.0
     * @return Retornar uma Informação de valor String do Tipo do Material do
     * material..
     */
    public static String getTipoMaterial() {
        return tipoMaterial;
    }

    /**
     * Este Metodo setar uma Informação de valor String do Tipo do Material do
     * material.
     *
     * *@since 18/05/2019
     * @version 1.0
     * @param tipoMaterial setar uma Informação de valor String do Tipo do
     * Material do material.
     */
    public static void setTipoMaterial(String tipoMaterial) {
        Material.tipoMaterial = tipoMaterial;
    }

    /**
     * Este metodo Retornar uma informação de valor inteiro da quantidade do
     * Material.
     *
     * *@since 18/05/2019
     * @version 1.0
     * @return Retornar uma informação de valor inteiro da quantidade do
     * Material.
     */
    public static int getQuantMaterial() {
        return quantMaterial;
    }

    /**
     * Este metodo Setar uma Informação de valor inteiro da quantidade do
     * Material.
     *
     * *@since 18/05/2019
     * @version 1.0
     * @param quantMaterial Setar uma Informação de valor inteiro da quantidade
     * do Material.
     */
    public static void setQuantMaterial(int quantMaterial) {
        Material.quantMaterial = quantMaterial;
    }

    /**
     * Este Metodo Retornar uma informação de valor inteiro do id do Material.
     *
     * *@since 18/05/2019
     * @version 1.0
     * @return Retornar uma informação de valor inteiro do id do Material.
     */
    public static int getIdMaterial() {
        return idMaterial;
    }

    /**
     * Este Metodo Setar uma Informação de valor inteiro do id do Material.
     *
     * *@since 18/05/2019
     * @version 1.0
     * @param idMaterial Setar uma Informação de valor inteiro do id do
     * Material.
     */
    public static void setIdMaterial(int idMaterial) {
        Material.idMaterial = idMaterial;
    }

    /**
     * Este Metodo Retornar uma informação de valor double do comprimento do
     * Material.
     *
     * *@since 18/05/2019
     * @version 1.0
     * @return Retornar uma informação de valor double do comprimento do
     * Material.
     */
    public static double getComprMaterial() {
        return comprMaterial;
    }

    /**
     * Este Metodo Setar uma Informação de valor double do comprimento do
     * Material.
     *
     * *@since 18/05/2019
     * @version 1.0
     * @param comprMaterial Setar uma Informação de valor double do comprimento
     * do Material.
     */
    public static void setComprMaterial(double comprMaterial) {
        Material.comprMaterial = comprMaterial;
    }

    /**
     * Este Metodo Retornar uma informação de valor double da largura do
     * Material.
     *
     * *@since 18/05/2019
     * @version 1.0
     * @return Retornar uma informação de valor double da largura do Material.
     */
    public static double getLargMarterial() {
        return largMarterial;
    }

    /**
     * Este Metodo Setar Informação um valor double da largura do Material.
     *
     * *@since 18/05/2019
     * @version 1.0
     * @param largMarterial Setar Informação um valor double da largura do
     * Material.
     */
    public static void setLargMarterial(double largMarterial) {
        Material.largMarterial = largMarterial;
    }

    /**
     * Este Metodo Retornar uma informação de valor double da espessura do
     * Material.
     *
     * *@since 18/05/2019
     * @version 1.0
     * @return Retornar uma informação de valor double da espessura do Material.
     */
    public static double getEspesMaterial() {
        return espesMaterial;
    }

    /**
     * Este Metodo setar Informação um valor double da espessura do Material.
     *
     * *@since 18/05/2019
     * @version 1.0
     * @param espesMaterial setar Informação um valor double da espessura do
     * Material.
     */
    public static void setEspesMaterial(double espesMaterial) {
        Material.espesMaterial = espesMaterial;
    }

    /**
     * Este Metodo Retornar uma informação de valor double do preço do Material.
     *
     * @return Retornar uma informação de valor double do preço do Material.
     */
    public static double getPrecMaterial() {
        return precMaterial;
    }

    /**
     * Este Metodo Setar a Informação um valor double do preço do Material.
     *
     * *@since 18/05/2019
     * @version 1.0
     * @param precMaterial Setar a Informação um valor double do preço do
     * Material.
     */
    public static void setPrecMaterial(double precMaterial) {
        Material.precMaterial = precMaterial;
    }

    /**
     * Este Metodo Retornar uma informação de valor String com nome da tabela
     * Material
     *
     * *@since 18/05/2019
     * @version 1.0
     * @return Retornar uma informação de valor String com nome da tabela
     * Material
     */
    public static String getTABELA() {
        return TABELA;
    }

}
