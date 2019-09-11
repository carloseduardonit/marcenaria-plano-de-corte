/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marcenaria.material;

import java.sql.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JOptionPane;
import marcenaria.Const.Messagem;
import marcenaria.dado.ModuloConector;
import marcenaria.pessoa.Fornecedor;

/**
 * @since 16/05/2019
 * @version 1.0
 * @author Carlos Eduardo dos Santos Figueiredo
 */
public class Chapa {

    /**
     * Variaveis do tipo Double para largura , comprimento, espessura, preço da
     * chapa
     */
    private static double largChapa = 0.0, comprChapa = 0.0, espesChapa = 0.0, precoChapa = 0.0;
    /**
     * Variaveis do tipo inteiro para id e quantidade da chapa;
     */
    private static int idChapa = 0, quantChapa = 0, index = 1;
    /**
     * Variaveis do tipo String para nome da Tabela Chapa;
     */
    private static final String TABELA = Chapa.class.getSimpleName();
    /**
     * Array do tipo String para tipo de materia da Chapa;
     */
    private static String tipoMateria;
    private static Connection conexao;
    private static ResultSet rs;
    private static ResultSetMetaData rsmd;
    private static PreparedStatement pst;
    private static Statement stmt;
    private static DecimalFormat df = new DecimalFormat("###,##.00");
    private static Locale ptBr = new Locale("pt", "br");
    private static NumberFormat moeda = NumberFormat.getInstance(ptBr);

    /**
     * Este metodo realiza a conexao com o banco de dado
     *
     * @since 16/05/2019
     * @version 1.0
     */
    private static void chapa() {
        conexao = ModuloConector.getConecction();
    }

    /**
     * <b>Este Metodo faz Criação da Tabela Chapa utilizando uma classe e metodo
     * auxiliar</b>
     *
     * @since 16/05/2019
     * @version 1.0
     */
    public static void criadaChapa() {
        if (ModuloConector.VerificarNaoExistirTabela(Fornecedor.getTABELA())) {
            Fornecedor.criarFornecedor();
        }
        String sql = "create table if not exists " + Chapa.getTABELA()
                + "( id" + Chapa.getTABELA() + " int primary key auto_increment,"
                + "quantidade int default 0,"
                + "comprimento double (7,2),"
                + "largura double (7,2),"
                + "espessura double(4,2),"
                + "preco double (10,2), "
                + "tipoMaterial varchar(30), "
                + "id" + Fornecedor.getTABELA() + " int not null default 0,"
                + "foreign key(id" + Fornecedor.getTABELA() + ") references " + Fornecedor.getTABELA() + " (id" + Fornecedor.getTABELA() + "))";
        ModuloConector.criarTabela(sql, Chapa.getTABELA());
    }

    /**
     * <b>Este Metodo faz Deletação da Tabela Chapa</b>
     *
     * @since 16/05/2019
     * @version 1.0
     */
    public static void deletadaChapa() {
        if (!ModuloConector.VerificarNaoExistirTabela(Peca.getTABELA())) {
            Peca.deletadaPeca();
        }
        if (!ModuloConector.VerificarNaoExistirTabela(Pedaco.getTABELA())) {
            Pedaco.deletaPedaco();
        }
        ModuloConector.deletarTabela(Chapa.getTABELA());
    }

    /**
     * Tem que faze-lo Este metodo faz a inserção de uma informação no banco de
     * dado na tabela Chapa utilizado uma classe e Metodo Auxiliar
     * Material.adicionarMaterial() conforme os pa
     *
     * @since 01/05/2019
     * @version 1.0
     *
     * @param quantChapa Setar uma informação de valor inteiro da quantidade de
     * Chapa
     * @param compChapa Setar uma informação de valor double do comprimento de
     * Chapa
     * @param largChapa Setar uma informação de valor double do largura de Chapa
     * @param espeChapa Setar uma informação de valor double do espessura de
     * Chapa
     * @param precChapa Setar uma informação de valor double do Preço da Chapa
     * @param tipoMaterial Setar uma informação de valor String do tipo de
     * materia da Chapa
     * @param fornecedor Setar uma informação de valor String do fornecedor da
     * Chapa
     */
    public static void adicionarChapa(int quantChapa, double compChapa, double largChapa, double espeChapa, double precChapa, String tipoMaterial, String fornecedor) {
        if (Chapa.NaoHaCampoVazio(quantChapa, compChapa, largChapa, espeChapa, precChapa, tipoMaterial, fornecedor)) {
            if (Fornecedor.existeroFornecedor(fornecedor)) {
                int idFornecedor = Fornecedor.obterIdFornecedortoFornecedor(fornecedor);
                try {
                    String sql = "insert into " + Chapa.getTABELA()
                            + "(quantidade, comprimento, largura, espessura, preco, tipoMaterial, id"
                            + Fornecedor.getTABELA() + ") values (?,?,?,?,?,?,?)";
                    chapa();
                    pst = conexao.prepareStatement(sql);
                    pst.setInt(1, quantChapa);
                    pst.setDouble(2, compChapa);
                    pst.setDouble(3, largChapa);
                    pst.setDouble(4, espeChapa);
                    pst.setDouble(5, precChapa);
                    pst.setString(6, tipoMaterial);
                    pst.setInt(7, idFornecedor);
                    int adicionar = pst.executeUpdate();
                    if (adicionar > 0) {
                        Messagem.chamarTela(Messagem.ADICIONADO(sql));
                    }
                } catch (SQLException e) {
                    Messagem.chamarTela(Chapa.getTABELA() + " Adicionar: " + e);
                } finally {
                    ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
                }
            } else {
                Messagem.chamarTela("Fornecedor Nao existe");
            }
        } else {
            Messagem.chamarTela(Messagem.VAZIO(Chapa.campoVazio(quantChapa, compChapa, largChapa, espeChapa, precChapa, tipoMaterial, fornecedor)));
        }
    }

    /**
     * Tem que faze-lo
     *
     * @param quantChapa Setar uma informação de valor inteiro da quantidade de
     * Chapa
     * @param compChapa Setar uma informação de valor double do comprimento de
     * Chapa
     * @param largChapa Setar uma informação de valor double do largura de Chapa
     * @param espeChapa Setar uma informação de valor double do espessura de
     * Chapa
     * @param precChapa Setar uma informação de valor double do Preço da Chapa
     * @param tipoMaterial Setar uma informação de valor String do tipo de
     * materia da Chapa
     * @param fornecedor Setar uma informação de valor String do fornecedor da
     * Chapa
     * @param ou
     * @since 01/05/2019
     * @version 1.0
     */
    public static void editarChapa(int quantChapa, double compChapa, double largChapa, double espeChapa, double precChapa, String tipoMaterial, String fornecedor) {
        System.out.println("a1");
        Chapa.editarChapa(quantChapa, compChapa, largChapa, espeChapa, precChapa, tipoMaterial, fornecedor, false);
    }

    /**
     * Tem que faze-lo
     *
     * @param quantChapa Setar uma informação de valor inteiro da quantidade de
     * Chapa
     * @param compChapa Setar uma informação de valor double do comprimento de
     * Chapa
     * @param largChapa Setar uma informação de valor double do largura de Chapa
     * @param espeChapa Setar uma informação de valor double do espessura de
     * Chapa
     * @param precChapa Setar uma informação de valor double do Preço da Chapa
     * @param tipoMaterial Setar uma informação de valor String do tipo de
     * materia da Chapa
     * @param fornecedor Setar uma informação de valor String do fornecedor da
     * Chapa
     * @param ou
     * @since 01/05/2019
     * @version 1.0
     */
    public static void editarChapa(int quantChapa, double compChapa, double largChapa, double espeChapa, double precChapa, String tipoMaterial, String fornecedor, boolean ou) {
        String a;
        if (ou) {
            a = " or ";
        } else {
            a = " and ";
        }
        if (Chapa.NaoHaCampoVazio(quantChapa, compChapa, largChapa, espeChapa, precChapa, tipoMaterial, fornecedor)) {
            if (Fornecedor.existeroFornecedor(fornecedor)) {
                int idFornecedor = Fornecedor.obterIdFornecedortoFornecedor(fornecedor);
                try {
                    String sql = "update " + Chapa.getTABELA() + " set quantidade = ?, comprimento = ?, largura = ?, espessura = ?, preco = ?, tipoMaterial = ? where ", antes = Chapa.exibirChapatoString(compChapa, largChapa, espeChapa, precChapa, tipoMaterial, fornecedor), depois;
                    if (!tipoMaterial.isEmpty() && !fornecedor.isEmpty()) {
                        sql += "tipoMaterial = ?" + a + "id" + Fornecedor.getTABELA() + " = ?";
                    }
                    chapa();
                    int i = 1;
                    pst = conexao.prepareStatement(sql);
                    pst.setInt(i++, quantChapa);
                    pst.setDouble(i++, compChapa);
                    pst.setDouble(i++, largChapa);
                    pst.setDouble(i++, espeChapa);
                    pst.setDouble(i++, precChapa);
                    pst.setString(i++, tipoMaterial);

                    if (!tipoMaterial.isEmpty() && !fornecedor.isEmpty()) {
                        pst.setString(i++, tipoMaterial);
                        pst.setInt(i++, idFornecedor);
                    }
                    int editado = pst.executeUpdate();
                    if (editado > 0) {
                        depois = Chapa.exibirChapatoString(compChapa, largChapa, espeChapa, precChapa, tipoMaterial, fornecedor);
                        Messagem.chamarTela(Messagem.EDITADO(antes + "\n\n" + depois));
                    }
                } catch (SQLException e) {
                    Messagem.chamarTela(Chapa.getTABELA() + " Editar: " + e);
                } finally {
                    ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
                }
            }
        }
    }

    public static void excluirChapa(int quantChapa, double compChapa, double largChapa, double espeChapa, double precChapa, String tipoMaterial, String fornecedor, boolean ou) {
        excluirChapa(quantChapa, compChapa, largChapa, espeChapa, precChapa, tipoMaterial, fornecedor, ou, true);
    }

    /**
     * Tem que faze-lo
     *
     * @param quantChapa Setar uma informação de valor inteiro da quantidade de
     * Chapa
     * @param compChapa Setar uma informação de valor double do comprimento de
     * Chapa
     * @param largChapa Setar uma informação de valor double do largura de Chapa
     * @param espeChapa Setar uma informação de valor double do espessura de
     * Chapa
     * @param precChapa Setar uma informação de valor double do Preço da Chapa
     * @param tipoMaterial Setar uma informação de valor String do tipo de
     * materia da Chapa
     * @param fornecedor Setar uma informação de valor String do fornecedor da
     * Chapa
     * @param ou
     * @param messagem
     * @since 01/05/2019
     * @version 1.0
     *
     */
    public static void excluirChapa(int quantChapa, double compChapa, double largChapa, double espeChapa, double precChapa, String tipoMaterial, String fornecedor, boolean ou, boolean messagem) {
        String a;
        if (ou) {
            a = " or ";
        } else {
            a = " and ";
        }
        if (Chapa.HaCampoVazio(quantChapa, compChapa, largChapa, espeChapa, precChapa, tipoMaterial, fornecedor)) {
            try {
                int i = 0, idFornecedor = Fornecedor.obterIdFornecedortoFornecedor(fornecedor);
                String sql = "delete from " + Chapa.getTABELA() + " where ", exclui = Chapa.exibirChapatoString(compChapa, largChapa, espeChapa, precChapa, tipoMaterial, fornecedor);
                if (quantChapa >= 1) {
                    sql += "quantidade = ?";
                    i++;
                }
                if (compChapa > 0.0 && compChapa == getComprChapa()) {
                    if (i > 0) {
                        sql += a;
                    }
                    sql += "comprimento = ?";
                    i++;
                }
                if (largChapa > 0.0 && largChapa == getLargChapa()) {
                    if (i > 0) {
                        sql += a;
                    }
                    sql += "largura = ?";
                    i++;
                }
                if (espeChapa > 0.0 && espeChapa == getEspesChapa()) {
                    if (i > 0) {
                        sql += a;
                    }
                    sql += "espessura = ?";
                    i++;
                }
                if (precChapa > 0.0 && precChapa == getPrecoChapa()) {
                    if (i > 0) {
                        sql += a;
                    }
                    sql += "preco = ?";
                    i++;
                }
                if (!tipoMaterial.isEmpty() && tipoMaterial.equalsIgnoreCase(getTipoMateria())) {
                    if (i > 0) {
                        sql += a;
                    }
                    sql += "tipoMaterial = ?";
                    i++;
                }
                if (!fornecedor.isEmpty() && idFornecedor > 0) {
                    if (i > 0) {
                        sql += a;
                    }
                    sql += "id" + Fornecedor.getTABELA() + " = ?";
                    i++;
                }
                if (messagem) {
                    Messagem.chamarTelaConfirma(exclui, Chapa.getTABELA(), "deletar");
                } else {
                    Messagem.setDeleta(JOptionPane.OK_OPTION);
                }
                if (Messagem.getDeleta() == 0) {
                    int j = 1;
                    chapa();
                    pst = conexao.prepareStatement(sql);
                    if (quantChapa >= 1) {
                        pst.setInt(j++, quantChapa);
                    }
                    if (compChapa > 0.0 && compChapa == getComprChapa()) {
                        pst.setDouble(j++, compChapa);
                    }
                    if (largChapa > 0.0 && largChapa == getLargChapa()) {
                        pst.setDouble(j++, largChapa);
                    }
                    if (espeChapa > 0.0 && espeChapa == getEspesChapa()) {
                        pst.setDouble(j++, espeChapa);
                    }
                    if (precChapa > 0.0 && precChapa == getPrecoChapa()) {
                        pst.setDouble(j++, precChapa);
                    }
                    if (!tipoMaterial.isEmpty() && tipoMaterial.equalsIgnoreCase(getTipoMateria())) {
                        pst.setString(j++, tipoMaterial);
                    }
                    if (!fornecedor.isEmpty() && idFornecedor > 0) {
                        pst.setInt(j++, idFornecedor);
                    }
                    int excluir = pst.executeUpdate();
                    if (excluir > 0) {
                        Messagem.chamarTela(Messagem.EXCLUIDO(exclui));
                    }
                }
            } catch (SQLException e) {
                Messagem.chamarTela(Chapa.getTABELA() + " Excluir: " + e);
            } finally {
                ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
            }
        }
    }

    /**
     * @param quantChapa Setar uma informação de valor inteiro da quantidade de
     * Chapa
     * @param compChapa Setar uma informação de valor double do comprimento de
     * Chapa
     * @param largChapa Setar uma informação de valor double do largura de Chapa
     * @param espeChapa Setar uma informação de valor double do espessura de
     * Chapa
     * @param precChapa Setar uma informação de valor double do Preço da Chapa
     * @param tipoMaterial Setar uma informação de valor String do tipo de
     * materia da Chapa
     * @param fornecedor Setar uma informação de valor String do fornecedor da
     * Chapa
     */
    public static void inserirChapa(int quantChapa, double compChapa, double largChapa, double espeChapa, double precChapa, String tipoMaterial, String fornecedor) {
        if (TemChapa(compChapa, largChapa, espeChapa, precChapa, tipoMaterial, fornecedor)) {
            editarChapa(getQuantChapa() + quantChapa, compChapa, largChapa, espeChapa, precChapa, tipoMaterial, fornecedor, true);
        } else {
            adicionarChapa(quantChapa, compChapa, largChapa, espeChapa, 0, tipoMaterial, fornecedor);
        }
    }

    /**
     * Tem que faze-lo
     *
     * @param quantChapa Setar uma informação de valor inteiro da quantidade de
     * Chapa
     * @param compChapa Setar uma informação de valor double do comprimento de
     * Chapa
     * @param largChapa Setar uma informação de valor double do largura de Chapa
     * @param espeChapa Setar uma informação de valor double do espessura de
     * Chapa
     * @param precChapa Setar uma informação de valor double do Preço da Chapa
     * @param tipoMaterial Setar uma informação de valor String do tipo de
     * materia da Chapa
     * @param fornecedor Setar uma informação de valor String do fornecedor da
     * Chapa
     * @param ou
     *
     * @since 01/05/2019
     * @version 1.0
     */
    public static void pesquisarChapa(int quantChapa, double compChapa, double largChapa, double espeChapa, double precChapa, String tipoMaterial, String fornecedor, boolean ou) {
        if (!Chapa.HaCampoVazio(quantChapa, compChapa, largChapa, espeChapa, precChapa, tipoMaterial, fornecedor)) {
            try {
                String a;
                int i = 0;
                if (ou) {
                    a = " or ";
                } else {
                    a = " and ";
                }
                String sql = "select id" + Chapa.getTABELA() + ", quantidade, comprimento, largura, espessura, preco, tipoMaterial, id" + Fornecedor.getTABELA() + " from " + Chapa.getTABELA() + " where ";
                if (quantChapa > 0) {
                    sql += "quantidade = ?";
                    i++;
                }
                if (compChapa > 0.0) {
                    if (i >= 1) {
                        sql += a;
                    }
                    sql += "comprimento = ?";
                    i++;
                }
                if (largChapa > 0.0) {
                    if (i >= 1) {
                        sql += a;
                    }
                    sql += "largura = ?";
                    i++;
                }
                if (espeChapa > 0.0) {
                    if (i >= 1) {
                        sql += a;
                    }
                    sql += "espessura = ?";
                    i++;
                }
                if (precChapa > 0.0) {
                    if (i >= 1) {
                        sql += a;
                    }
                    sql += "preco = ?";
                    i++;
                }
                if (!tipoMaterial.isEmpty()) {
                    if (i >= 1) {
                        sql += a;
                    }
                    sql += "tipoMaterial = ?";
                    i++;
                }
                if (!fornecedor.isEmpty()) {
                    if (i >= 1) {
                        sql += a;
                    }
                    sql += "id" + Fornecedor.getTABELA() + " = ?";
                    i++;
                }
                chapa();
                pst = conexao.prepareStatement(sql);
                int j = 1;
                if (quantChapa > 0) {
                    pst.setInt(j, quantChapa);
                    j++;
                }
                if (compChapa > 0.0) {
                    pst.setDouble(j, compChapa);
                    j++;
                }
                if (largChapa > 0.0) {
                    pst.setDouble(j, largChapa);
                    j++;
                }
                if (espeChapa > 0.0) {
                    pst.setDouble(j, espeChapa);
                    j++;
                }
                if (precChapa > 0.0) {
                    pst.setDouble(j, precChapa);
                    j++;
                }
                if (!tipoMaterial.isEmpty()) {
                    pst.setString(j, tipoMaterial);
                    j++;
                }
                if (!fornecedor.isEmpty()) {
                    int forn = Fornecedor.obterIdFornecedortoFornecedor(fornecedor);
                    pst.setInt(j, forn);
                }
                rs = pst.executeQuery();
                if (rs.next()) {
                    setIdChapa(rs.getInt(1));
                    setQuantChapa(rs.getInt(2));
                    setComprChapa(rs.getDouble(3));
                    setLargChapa(rs.getDouble(4));
                    setEspesChapa(rs.getDouble(5));
                    setPrecoChapa(rs.getDouble(6));
                    setTipoMateria(rs.getString(7));
                    Fornecedor.setIdFornecedor(rs.getInt(8));
                }
            } catch (SQLException e) {
                Messagem.chamarTela(Chapa.getTABELA() + " Pesquisar: " + e);
            } finally {
                ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
            }
        }
    }

    /**
     * Este Metodo Retornar uma informação de valor inteiro do Id de Chapa
     * conforme o tipo de Materia e espessura da Chapa.
     *
     * @since 01/05/2019
     * @version 1.0
     * @param tipoMaterial Setar uma informação de valor String do Tipo de
     * Materia da Chapa.
     * @param espessura Setar uma informação de valor double da Espessura da
     * Chapa.
     * @return Retornar uma informação de valor inteiro do Id de Chapa.
     */
    public static int obterIdChapa(String tipoMaterial, double espessura) {
        return Material.obterIdMaterial(getTABELA(), tipoMaterial, espessura);
    }

    /**
     * Tem que faze-lo
     *
     * @param quantChapa Setar uma informação de valor inteiro da quantidade de
     * Chapa
     * @param compChapa Setar uma informação de valor double do comprimento de
     * Chapa
     * @param largChapa Setar uma informação de valor double do largura de Chapa
     * @param espeChapa Setar uma informação de valor double do espessura de
     * Chapa
     * @param precChapa Setar uma informação de valor double do Preço da Chapa
     * @param tipoMaterial Setar uma informação de valor String do tipo de
     * materia da Chapa
     * @param fornecedor Setar uma informação de valor String do fornecedor da
     * Chapa
     * @return
     * @since 01/05/2019
     * @version 1.0
     */
    public static String[] campoVazio(int quantChapa, double compChapa, double largChapa, double espeChapa, double precChapa, String tipoMaterial, String fornecedor) {
        String[] vazio = new String[7];
        boolean qc = false, cc = false, lc = false, ec = false, pc = false, tm = false, f = false;
        for (int i = 0; i < vazio.length; i++) {
            if (String.valueOf(quantChapa).isEmpty() && !qc) {
                vazio[i] = "Quantidade de Chapa\n";
                qc = true;
            } else if (String.valueOf(compChapa).isEmpty() && !cc) {
                vazio[i] = "Comprimento da Chapa\n";
                cc = true;
            } else if (String.valueOf(largChapa).isEmpty() && !lc) {
                vazio[i] = "Largura de Chapa\n";
                lc = true;
            } else if (String.valueOf(espeChapa).isEmpty() && !ec) {
                vazio[i] = "Espessura de Chapa\n";
                ec = true;
            } else if (String.valueOf(precChapa).isEmpty() && !pc) {
                vazio[i] = "Preço de Chapa\n";
                pc = true;
            } else if (tipoMaterial.isEmpty() && !tm) {
                vazio[i] = "Tipo de Chapa\n";
                tm = true;
            } else if (fornecedor.isEmpty() && !f) {
                vazio[i] = "Fornecedor de Chapa\n";
                f = true;
            }
        }
        return vazio;
    }

    /**
     * @param compChapa Setar uma informação de valor double do comprimento de
     * Chapa
     * @param largChapa Setar uma informação de valor double do largura de Chapa
     * @param espeChapa Setar uma informação de valor double do espessura de
     * Chapa
     * @param precChapa Setar uma informação de valor double do Preço da Chapa
     * @param tipoMaterial Setar uma informação de valor String do tipo de
     * materia da Chapa
     * @param fornecedor Setar uma informação de valor String do fornecedor da
     * Chapa
     * @return
     */
    public static String exibirChapatoString(double compChapa, double largChapa, double espeChapa,
            double precChapa, String tipoMaterial, String fornecedor) {
        String a = "";

        //if (TemChapa(compChapa, largChapa, espeChapa, precChapa, tipoMaterial, fornecedor)) {
        pesquisarChapa(0, 0, 0, 0, 0, tipoMaterial, fornecedor, false);
        a = "Cadastro da Chapa: \n";
        if (getQuantChapa() >= 1) {
            a += "\nQuantidade de Chapa: " + getQuantChapa();
        }
        if (getComprChapa() > 0) {
            a += "\nComprimento: " + df.format(getComprChapa());
        }
        if (getLargChapa() > 0) {
            a += "\nLargura: " + df.format(getLargChapa());
        }
        if (getEspesChapa() > 0) {
            a += "\nEspessura: " + df.format(getEspesChapa());
        }
        if (getPrecoChapa() > 0) {
            a += "\nPreço Unitario: " + moeda.format(getPrecoChapa());
        }
        if (!getTipoMateria().isEmpty()) {
            a += "\nTipo de Material: " + getTipoMateria();
        }
        if (Fornecedor.getIdFornecedor() > 0) {
            a += "\n\n" + Fornecedor.exibirFornecedortoString(fornecedor);
        }
        // } else {

        //}
        return a;
    }

    /**
     * @param quantChapa Setar uma informação de valor inteiro da quantidade de
     * Chapa
     * @param compChapa Setar uma informação de valor double do comprimento de
     * Chapa
     * @param largChapa Setar uma informação de valor double do largura de Chapa
     * @param espeChapa Setar uma informação de valor double do espessura de
     * Chapa
     * @param precChapa Setar uma informação de valor double do Preço da Chapa
     * @param tipoMaterial Setar uma informação de valor String do tipo de
     * materia da Chapa
     * @param fornecedor Setar uma informação de valor String do fornecedor da
     * Chapa
     * @return
     * @since 01/05/2019
     * @version 1.0
     */
    public static boolean NaoHaCampoVazio(int quantChapa, double compChapa, double largChapa, double espeChapa, double precChapa, String tipoMaterial, String fornecedor) {
        return !Chapa.HaCampoVazio(quantChapa, compChapa, largChapa, espeChapa, precChapa, tipoMaterial, fornecedor, true);
    }

    /**
     * @param quantChapa Setar uma informação de valor inteiro da quantidade de
     * Chapa
     * @param compChapa Setar uma informação de valor double do comprimento de
     * Chapa
     * @param largChapa Setar uma informação de valor double do largura de Chapa
     * @param espeChapa Setar uma informação de valor double do espessura de
     * Chapa
     * @param precChapa Setar uma informação de valor double do Preço da Chapa
     * @param tipoMaterial Setar uma informação de valor String do tipo de
     * materia da Chapa
     * @param fornecedor Setar uma informação de valor String do fornecedor da
     * Chapa
     * @return
     * @since 01/05/2019
     * @version 1.0
     */
    public static boolean HaCampoVazio(int quantChapa, double compChapa, double largChapa, double espeChapa, double precChapa, String tipoMaterial, String fornecedor) {
        return Chapa.HaCampoVazio(quantChapa, compChapa, largChapa, espeChapa, precChapa, tipoMaterial, fornecedor, true);
    }

    /**
     * Tem que faze-lo
     *
     * @param quantChapa Setar uma informação de valor inteiro da quantidade de
     * Chapa
     * @param compChapa Setar uma informação de valor double do comprimento de
     * Chapa
     * @param largChapa Setar uma informação de valor double do largura de Chapa
     * @param espeChapa Setar uma informação de valor double do espessura de
     * Chapa
     * @param precChapa Setar uma informação de valor double do Preço da Chapa
     * @param tipoMaterial Setar uma informação de valor String do tipo de
     * materia da Chapa
     * @param fornecedor Setar uma informação de valor String do fornecedor da
     * Chapa
     * @param messagem
     * @return
     * @since 01/05/2019
     * @version 1.0
     */
    public static boolean HaCampoVazio(int quantChapa, double compChapa, double largChapa, double espeChapa, double precChapa, String tipoMaterial, String fornecedor, boolean messagem) {
        boolean a = String.valueOf(quantChapa).isEmpty() && String.valueOf(compChapa).isEmpty()
                && String.valueOf(largChapa).isEmpty() && String.valueOf(espeChapa).isEmpty()
                && String.valueOf(precChapa).isEmpty() && tipoMaterial.isEmpty() && fornecedor.isEmpty();
        System.out.println("" + a);
        if (a && messagem) {
            System.out.println("" + !a);
            System.out.println("1" + Messagem.VAZIO(Chapa.campoVazio(quantChapa, compChapa, largChapa, espeChapa, precChapa, tipoMaterial, fornecedor)));
            Messagem.chamarTela(Messagem.VAZIO(Chapa.campoVazio(quantChapa, compChapa, largChapa, espeChapa, precChapa, tipoMaterial, fornecedor)));
        }
        return a;
    }

    /**
     * @param compChapa Setar uma informação de valor double do comprimento de
     * Chapa
     * @param largChapa Setar uma informação de valor double do largura de Chapa
     * @param espeChapa Setar uma informação de valor double do espessura de
     * Chapa
     * @param precChapa Setar uma informação de valor double do Preço da Chapa
     * @param tipoMaterial Setar uma informação de valor String do tipo de
     * materia da Chapa
     * @param fornecedor Setar uma informação de valor String do fornecedor da
     * Chapa
     * @return
     */
    public static boolean TemChapa(double compChapa, double largChapa, double espeChapa, double precChapa, String tipoMaterial, String fornecedor) {
        // System.err.println("5e");
        pesquisarChapa(0, 0, 0, 0, 0, tipoMaterial, fornecedor, false);
        boolean a = getQuantChapa() >= 1 && getComprChapa() >= compChapa && getLargChapa() >= largChapa && getEspesChapa() >= espeChapa && getPrecoChapa() >= precChapa && getTipoMateria().equalsIgnoreCase(tipoMaterial) && Fornecedor.getIdFornecedor() >= 1;
        if (!a) {
            Messagem.chamarTela("Não há Chapa Disponivel!!!");
        }
        return a;
    }

    // Gets e Sets
    /**
     * Este Metodo Retornar uma valor double da largura da Chapa
     *
     ** @since 16/05/2019
     * @version 1.0
     * @return Retornar uma valor double da largura da Chapa
     */
    public static double getLargChapa() {
        return largChapa;
    }

    /**
     * Este Metodo Setar Informar uma valor double da largura da Chapa
     *
     ** @since 16/05/2019
     * @version 1.0
     * @param largChapa Setar Informar uma valor double da largura da Chapa
     */
    public static void setLargChapa(double largChapa) {
        Chapa.largChapa = largChapa;
    }

    /**
     * Este Metodo Retornar um valor double do comprimento da Chapa
     *
     ** @since 16/05/2019
     * @version 1.0
     * @return Retornar um valor double do comprimento da Chapa
     */
    public static double getComprChapa() {
        return comprChapa;
    }

    /**
     * Este Metodo setar Informação um valor double do Comprimento da Chapa
     *
     ** @since 16/05/2019
     * @version 1.0
     * @param comprChapa setar Informação um valor double do Comprimento da
     * Chapa
     */
    public static void setComprChapa(double comprChapa) {
        Chapa.comprChapa = comprChapa;
    }

    /**
     * Este Metodo Retornar um valor double da espessura da Chapa
     *
     ** @since 16/05/2019
     * @version 1.0
     * @return Retornar um valor double da espessura da Chapa
     */
    public static double getEspesChapa() {
        return espesChapa;
    }

    /**
     * Este Metodo setar uma Informação de valor double da espessura da Chapa
     *
     ** @since 16/05/2019
     * @version 1.0
     * @param espesChapa setar uma Informação de valor double da espessura da
     * Chapa
     */
    public static void setEspesChapa(double espesChapa) {
        Chapa.espesChapa = espesChapa;
    }

    /**
     * Este Metodo Retorna um valor double do preco da Chapa
     *
     ** @since 16/05/2019
     * @version 1.0
     * @return Retorna um valor double do preco da Chapa
     */
    public static double getPrecoChapa() {
        return precoChapa;
    }

    /**
     * Este Metodo Setar Informar um valor double do preco da Chapa
     *
     ** @since 16/05/2019
     * @version 1.0
     * @param precoChapa Setar Informar um valor double do preco da Chapa
     */
    public static void setPrecoChapa(double precoChapa) {
        Chapa.precoChapa = precoChapa;
    }

    /**
     * Este Metodo Retornar um valor inteiro do id da Chapa
     *
     ** @since 16/05/2019
     * @version 1.0
     * @return Retornar um valor inteiro do id da Chapa
     */
    public static int getIdChapa() {
        return idChapa;
    }

    /**
     * Este Metodo setar Informar um valor inteiro do id da Chapa
     *
     ** @since 16/05/2019
     * @version 1.0
     * @param idChapa setar Informar um valor inteiro do id da Chapa
     */
    public static void setIdChapa(int idChapa) {
        Chapa.idChapa = idChapa;
    }

    /**
     * Este Metodo Retornar um valor inteiro referente a quantidade
     *
     ** @since 16/05/2019
     * @version 1.0
     * @return Retornar um valor inteiro referente a quantidade
     */
    public static int getQuantChapa() {
        return quantChapa;
    }

    /**
     * Este Metodo Setar uma Informar de valor inteiro na quantidade da Chapa
     *
     ** @since 16/05/2019
     * @version 1.0
     * @param quantChapa Setar uma Informar de valor inteiro na quantidade da
     * Chapa
     */
    public static void setQuantChapa(int quantChapa) {
        Chapa.quantChapa = quantChapa;
    }

    /**
     * Este Metodo Retornar uma informação de valor String do nome da Tabela
     * Chapa
     *
     ** @since 16/05/2019
     * @version 1.0
     * @return Retorna uma String com nome da tabela
     */
    public static String getTABELA() {
        return TABELA;
    }

    /**
     * Este Metodo Retornar um array de informação de valor String do tipo de
     * Pessoa
     *
     ** @since 16/05/2019
     * @version 1.0
     * @return Retornar um Array de String
     */
    public static String getTipoMateria() {
        return tipoMateria;
    }

    /**
     * Este Metodo Setar a informação um valor do tipo String no array na
     * posição escolhida
     *
     * @since 01/05/2019
     * @version 1.0
     * @param tipoMateria Setar Informar um valor do tipo String para setar no
     * campo do Array
     * @param pos Setar Informar um valor inteiro do index do Array e deve
     * começa em ZERO(0)
     */
    public static void setTipoMateria(String tipoMateria) {
        Chapa.tipoMateria = tipoMateria;
    }

}
