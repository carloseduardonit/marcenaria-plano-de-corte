/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marcenaria.material;

import java.awt.HeadlessException;
import java.sql.*;
import javax.swing.JOptionPane;
import marcenaria.Const.Messagem;
import marcenaria.dado.ModuloConector;
import marcenaria.pessoa1.Fornecedor;
import marcenaria.pessoa1.Pessoa;

/**
 * 18/05/2019
 *
 * @author Carlos Eduardo dos Santos Figueiredo
 */
public class Material {

    static Connection conexao;
    static ResultSet rs;
    static PreparedStatement pst;
    static Statement stmt;
    private static final String TABELA = Material.class.getSimpleName();
    private static String tipoMaterial;
    private static int quantMaterial, idMaterial;
    private static double comprMaterial, largMarterial, espesMaterial, precMaterial;

    /**
     * Este Metodo faz a conexão do banco de dados.
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
     * @param Fornecedo Informar um valor String do Fornecedor do Material.
     *
     */
    public static void adicionarMaterial(String Tabela, int quantidade, double comprimento, double largura,
            double espessura, double preco, String tipoMaterial, String Fornecedo) {
        try {
            Material();
            String sql = "";
            if (Tabela.equalsIgnoreCase(Chapa.getTABELA())) {
                // Verificar
                sql = "insert into " + Tabela + "(quantidade, comprimento, largura, espessura, preco, tipoMaterial, id"
                        + Fornecedor.getTABELA() + ") values (?,?,?,?,?,?,?)";
            } else if (Tabela.equalsIgnoreCase(Peca.getTABELA())) {
                //
                sql = "insert into " + Tabela + "(quantidade,comprimento,largura,espessura,preco, tipoMaterial,id"
                        + Chapa.getTABELA() + ") values (?,?,?,?,?,?,?)";
            } else if (Tabela.equalsIgnoreCase(Pedaco.getTABELA())) {
                sql = "insert into " + Tabela + "(quantidade,comprimento,largura,espessura,preco, tipoMaterial,id"
                        + Chapa.getTABELA() + "id" + Peca.getTABELA() + ") values (?,?,?,?,?,?,?)";
            }
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, quantidade);
            pst.setDouble(2, comprimento);
            pst.setDouble(3, largura);
            pst.setDouble(4, espessura);
            pst.setDouble(5, preco);
            pst.setString(6, tipoMaterial);
            if (Tabela.equalsIgnoreCase(Chapa.getTABELA())) {
                pst.setInt(7, Fornecedor.obterIdFornecedor(Fornecedo));// verificar
            } else if (Tabela.equalsIgnoreCase(Peca.getTABELA())) {
                pst.setInt(7, Chapa.obterIdChapa(tipoMaterial, espessura));
            } else if (Tabela.equalsIgnoreCase(Pedaco.getTABELA())) {
                pst.setInt(7, Chapa.obterIdChapa(tipoMaterial, espessura));
                pst.setInt(8, Peca.obterIdPeca(tipoMaterial, espessura));
            }
            if ((Tabela.equalsIgnoreCase(Chapa.getTABELA()))
                    || (Tabela.equalsIgnoreCase(Peca.getTABELA()) && Chapa.obterIdChapa(tipoMaterial, espessura) > 0)
                    || (Tabela.equalsIgnoreCase(Pedaco.getTABELA()) && (Chapa.obterIdChapa(tipoMaterial, espessura) > 0
                    || Peca.obterIdPeca(tipoMaterial, espessura) > 0))) {
                int inserido = pst.executeUpdate();
                if (inserido > 0) {
                    Messagem.chamarTela("Foi inserido na Tabela " + Tabela + " a quantidade de " + quantidade
                            + " como as Medidas :" + comprimento + "X" + largura + "X" + espessura + " CM.");
                    ModuloConector.fecharConexao(conexao, rs, pst, stmt);
                }
            } else {
                adicionarMaterial(Tabela, quantidade, comprimento, largura, espessura, preco, tipoMaterial, Fornecedo);
            }
        } catch (NullPointerException npe) {
        } catch (HeadlessException | SQLException e) {
            Messagem.chamarTela(e);
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
            double largura, double espessura, double preco,String fornecedo) {
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
     *
     * @param Tabela
     * @param idPessoa
     * @param row
     * @param colu
     * @return
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
Messagem.chamarTela(Tabela+" TabeladeMaterial: "+se);
        }
        return tab;
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
     * @param ou
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
     * TESTADO E OK
     *
     * @param Tabela Informar um valor String para tabela de Material
     */
    public static void criarMaterial(String Tabela) {
        try {
            Material();
            String sql = "create table if not exists " + Tabela + "(" + "id" + Tabela
                    + " int primary key auto_increment," + "quantidade int default 0," + "comprimento double,"
                    + "largura double," + "espessura double," + "preco double, " + "tipoMaterial varchar(30)";
            if (Tabela.equalsIgnoreCase(Chapa.getTABELA())) {
                sql += ", id" + Fornecedor.getTABELA() + " int not null, foreign key (id" + Fornecedor.getTABELA()
                        + ") references " + Fornecedor.getTABELA() + " (id" + Fornecedor.getTABELA() + "))";
            } else if (Tabela.equalsIgnoreCase(Peca.getTABELA())) {
                sql += "," + "id" + Chapa.getTABELA() + " int not null," + "foreign key(id" + Chapa.getTABELA()
                        + ") references chapa(id" + Chapa.getTABELA() + "))";
            } else if (Tabela.equalsIgnoreCase(Pedaco.getTABELA())) {
                sql += ",id" + Chapa.getTABELA() + " int default '0'," + "id" + Peca.getTABELA() + " int default 0,"
                        + "incData Timestamp," + "foreign key (id" + Chapa.getTABELA() + ") references "
                        + Chapa.getTABELA() + " (id" + Chapa.getTABELA() + "), " + "foreign key (id" + Peca.getTABELA()
                        + ") references " + Peca.getTABELA() + " (id" + Peca.getTABELA() + "))";
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
        } catch (SQLIntegrityConstraintViolationException sicve) {
            Messagem.chamarTela(sicve);
        } catch (NullPointerException ne) {
            ModuloConector.fecharConexao(conexao, rs, pst, stmt);
        } catch (HeadlessException he) {
            Messagem.chamarTela(he);
        } catch (SQLException e) {
            if (Tabela.equalsIgnoreCase(Chapa.getTABELA())) {
                Fornecedor.criarFornecedor();
            } else if (Tabela.equalsIgnoreCase(Pedaco.getTABELA())) {
                // primeiro tem que criar a Tabela Chapa para depois a Tabela Peça para depois
                // Tabela Pedaco
                criarMaterial(Chapa.getTABELA());
                criarMaterial(Peca.getTABELA());
                criarMaterial(Tabela);
            } else if (Tabela.equalsIgnoreCase(Peca.getTABELA())) {
                // primeiro tem que criar a Tabela Chapa para depois a Tabela Peça
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
     */
    public static void deletarMaterial() {
        Material.deletarMaterial(Material.getTABELA());
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
            int deleta = JOptionPane.showConfirmDialog(null, "Desejar excluir a  tabela " + Tabela, Tabela,
                    JOptionPane.OK_CANCEL_OPTION);
            // System.out.println(deleta + "X" + JOptionPane.OK_OPTION);
            if (deleta == JOptionPane.OK_OPTION) {
                int deletado = stmt.executeUpdate(sql);
                // System.out.println("" + deletado);
                if (deletado == 0) {
                    JOptionPane.showMessageDialog(null, "Foi Exclui com sucesso  a tabela " + Tabela);
                    ModuloConector.fecharConexao(conexao, rs, pst, stmt);
                }
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            if (Tabela.equalsIgnoreCase(Material.getTABELA())) {
                Pedaco.deletaPedaco();
                Peca.deletadaPeca();
                Chapa.deletadaChapa();
            } else if (Tabela.equalsIgnoreCase(Chapa.getTABELA())) {
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
        } catch (HeadlessException | SQLException he) {
            Messagem.chamarTela(he);
        } catch (NullPointerException npe) {
            ModuloConector.fecharConexao(conexao, rs, pst, stmt);
        }
    }

    /**
     *
     * @param Tabela
     * @param tipoMaterial
     * @param espessura
     * @return
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
                    ModuloConector.fecharConexao(conexao, rs, pst, stmt);
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
        }
        return id;
    }

    // Gets e Sets
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

    public static String getTABELA() {
        return TABELA;
    }

}
