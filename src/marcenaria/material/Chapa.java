/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marcenaria.material;
// importação 

import java.sql.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JOptionPane;
import marcenaria.Const.Messagem;
import marcenaria.dado.ModuloConector;
import marcenaria.pessoa.Fornecedor;

/**
 * este Classe foi desevolvida para resolver as necessidade de ser criar,
 * deletar e manipular a tabela Chapa
 *
 * @since 16/05/2019
 * @version 1.0
 * @author Carlos Eduardo dos Santos Figueiredo
 */
public class Chapa {

    /**
     * Variaveis do tipo double para largura , comprimento, espessura, preço da
     * chapa
     */
    private static double largChapa = 0.0, comprChapa = 0.0, espesChapa = 0.0, precoChapa = 0.0;
    /**
     * Variaveis do tipo inteiro para id e quantidade da chapa;
     */
    private static int idChapa = 0, quantChapa = 0;
    /**
     * Variaveis do tipo String para nome da Tabela Chapa;
     */
    private static final String TABELA = Chapa.class.getSimpleName();
    /**
     * Varivel do tipo String para tipo de materia da Chapa;
     */
    private static String tipoMateria;
    /**
     * Variaveis de conexao com banco de dados
     */

    private static Connection conexao;
    private static ResultSet rs;
    private static ResultSetMetaData rsmd;
    private static PreparedStatement pst;
    private static Statement stmt;
    /**
     * Variaveis de Manipulação de valores double
     */
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
     * <b>Este Metodo faz a verificação se a tabela Fornecedor existe, se não
     * existi e criar a tabela Fornecedor, logo apos faz a Criação da Tabela
     * Chapa utilizando o metodo auxiliar ModuloConector.criarTabela</b>
     *
     * @since 16/05/2019
     * @version 1.0 - utilizava o metodo auxiliar Material.criarMaterial
     * @since 13/09/2019
     * @version 1.1 - utilizava o metodo auxiliar ModuloConector.criarTabela
     */
    public static void criadaChapa() {
        if (ModuloConector.VerificarNaoExistirTabela(Fornecedor.getTABELA())) {
            Fornecedor.criarFornecedor();
            // faz a criação da tabela Fornecedor por causa foreign key(Chave Estrageira) idFornecedor
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
     * <b>Este Metodo faz a Verificação se as Tabelas Peça e Pedaço se sim faz a
     * deletação das tabela Peça e Pedaço e logo apos Deletação da Tabela
     * Chapa</b>
     *
     * @since 16/05/2019
     * @version 1.0
     */
    public static void deletadaChapa() {
        if (!ModuloConector.VerificarNaoExistirTabela(Peca.getTABELA())) {
            Peca.deletadaPeca();
            //este metodo precisa se excluido devido precisa da Foreign key(chave Estrageira) idChapa da Tabela Chapa
        }
        if (!ModuloConector.VerificarNaoExistirTabela(Pedaco.getTABELA())) {
            Pedaco.deletaPedaco();
            //este metodo precisa se excluido devido precisa da Foreign key(chave Estrageira) idChapa da Tabela Chapa
        }
        ModuloConector.deletarTabela(Chapa.getTABELA());
    }

    /**
     * Este metodo faz a verificação se não ha campos vazio e se o fornecedor
     * existe se sim faz a inserção de uma informação no banco de dado na tabela
     * Chapa conforme os parametros
     *
     * @since 01/05/2019
     * @version 1.0 - criaçao do metodo sem metodo de verificação e utilizava
     * metodo auxiliar da Material.adicionarMaterial.
     * @since 13/09/2019
     * @version 1.1 - reformulaçao e desenvolvimento no corpo do metodo e adição
     * de metodos de verificação Chapa.NaoHaCampoVazio e
     * Fornecedor.existeroFornecedor.
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
                        Messagem.chamarTela(Messagem.ADICIONADO(Chapa.exibirChapatoString(compChapa, largChapa, espeChapa, precChapa, tipoMaterial, fornecedor)));
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
     * Tem que faze-lo Este metodo faz a utilização do metodo Chapa.editarChapa
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
     * @since 01/05/2019
     * @version 1.0
     */
    public static void editarChapa(int quantChapa, double compChapa, double largChapa, double espeChapa, double precChapa, String tipoMaterial, String fornecedor) {
        Chapa.editarChapa(quantChapa, compChapa, largChapa, espeChapa, precChapa, tipoMaterial, fornecedor, false);
    }

    /**
     * Este Metodo faz a verificação se não ha campo vazio e se exister o
     * fornecedor se sim faz a editação da informação na tabela Chapa conforme
     * os parametros
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
     * @param ou Setar uma informação de valor boolean na expressão OU da
     * Chapa<p>
     * se for <b>TRUE</b> sera inserido <b>OR</b></p><p>
     * se for <b>FALSE</b> sera inserido <b>AND</b></p>
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

    /**
     * Este metodo faz a utilização do metodo Chapa.excluirChapa()
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
     *
     * @since 01/05/2019
     * @version 1.0
     */
    public static void excluirChapa(int quantChapa, double compChapa, double largChapa, double espeChapa, double precChapa, String tipoMaterial, String fornecedor) {
        excluirChapa(quantChapa, compChapa, largChapa, espeChapa, precChapa, tipoMaterial, fornecedor, true, true);
    }

    /**
     * Este metodo faz a utilização do metodo Chapa.excluirChapa()
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
    public static void excluirChapa(int quantChapa, double compChapa, double largChapa, double espeChapa, double precChapa, String tipoMaterial, String fornecedor, boolean ou) {
        excluirChapa(quantChapa, compChapa, largChapa, espeChapa, precChapa, tipoMaterial, fornecedor, ou, true);
    }

    /**
     * Este Metodo faz a verificação se ha campos vazios e se fornecedor existe
     * e logo apos exclução das informaçoes na tabela chapa atraves dos
     * paramentos
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
     * @param ou Setar uma informação de valor boolean na expressão OU da
     * Chapa<p>
     * se for <b>TRUE</b> sera inserido <b>OR</b></p><p>
     * se for <b>FALSE</b> sera inserido <b>AND</b></p>
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
        if (Chapa.NaoHaCampoVazio(quantChapa, compChapa, largChapa, espeChapa, precChapa, tipoMaterial, fornecedor)) {
            if (Fornecedor.existeroFornecedor(fornecedor)) {
                try {
                    int i = 0, excluindo = 3, idFornecedor = Fornecedor.obterIdFornecedortoFornecedor(fornecedor);
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
                        excluindo = JOptionPane.showConfirmDialog(null, exclui, Chapa.getTABELA(), JOptionPane.OK_CANCEL_OPTION);
                    } else {
                        excluindo = JOptionPane.OK_OPTION;
                    }
                    if (excluindo == 0) {
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
    }

    /**
     * este Metodo faz a Inserção ou atualização na tabela Chapa conforme os
     * parametros
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
    public static void inserirChapa(int quantChapa, double compChapa, double largChapa, double espeChapa, double precChapa, String tipoMaterial, String fornecedor) {
        if (TemChapa(compChapa, largChapa, espeChapa, precChapa, tipoMaterial, fornecedor)) {
            editarChapa(getQuantChapa() + quantChapa, compChapa, largChapa, espeChapa, precChapa, tipoMaterial, fornecedor, true);
        } else {
            adicionarChapa(quantChapa, compChapa, largChapa, espeChapa, 0, tipoMaterial, fornecedor);
        }
    }
 /**
     * Este Metodo realiza uma pesquisa na tabela Chapa do banco de dados
     * conforme os parametros
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
     * @param ou Setar uma informação de valor boolean na expressão OU da
     * Chapa<p>
     * se for <b>TRUE</b> sera inserido <b>OR</b></p><p>
     * se for <b>FALSE</b> sera inserido <b>AND</b></p>
     *
     * @since 01/05/2019
     * @version 1.0
     */
    public static void pesquisarChapa(int quantChapa, double compChapa, double largChapa, double espeChapa, double precChapa, String tipoMaterial, String fornecedor){
        pesquisarChapa(quantChapa, compChapa, largChapa, espeChapa, precChapa, tipoMaterial, fornecedor, true);
    }
    /**
     * Este Metodo realiza uma pesquisa na tabela Chapa do banco de dados
     * conforme os parametros
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
     * @param ou Setar uma informação de valor boolean na expressão OU da
     * Chapa<p>
     * se for <b>TRUE</b> sera inserido <b>OR</b></p><p>
     * se for <b>FALSE</b> sera inserido <b>AND</b></p>
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
     * Este metodo retornar um array de String dos parametros que se encontra
     * VAZIO
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
     * @return Retornar um array de informaçao de valor String dos campos vazio
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
     * Este Metodo retornar uma informação de valor String de uma pesquisar
     *
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
     * @return retornar uma informação de valor String da pesquisar
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
     * Este Metodo faz a verificação dos parametros e localiza se a parametro
     * sem informação.
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
    public static boolean NaoHaCampoVazio(int quantChapa, double compChapa, double largChapa, double espeChapa, double precChapa, String tipoMaterial, String fornecedor) {
        return !Chapa.HaCampoVazio(quantChapa, compChapa, largChapa, espeChapa, precChapa, tipoMaterial, fornecedor, true);
    }

    /**
     * Este Metodo faz a verificação dos parametros e localiza se a parametro
     * sem informação.
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
    public static boolean HaCampoVazio(int quantChapa, double compChapa, double largChapa, double espeChapa, double precChapa, String tipoMaterial, String fornecedor) {
        return Chapa.HaCampoVazio(quantChapa, compChapa, largChapa, espeChapa, precChapa, tipoMaterial, fornecedor, true);
    }

    /**
     * Este Metodo faz a verificação dos parametros e localiza se a parametro
     * sem informação.
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
        if (a && messagem) {
            System.out.println("1" + Messagem.VAZIO(Chapa.campoVazio(quantChapa, compChapa, largChapa, espeChapa, precChapa, tipoMaterial, fornecedor)));
            Messagem.chamarTela(Messagem.VAZIO(Chapa.campoVazio(quantChapa, compChapa, largChapa, espeChapa, precChapa, tipoMaterial, fornecedor)));
        }
        return a;
    }

    /**
     * Este Metodo faz a verificação se existe Chapa disponivel para utilização
     * no banco de dados da tabela chapa conforme os parametros.
     *
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

    /**
     * Este metodo obtem o id chapa conforme os paramentros
     *
     * @param compChapa Setar uma informação de valor double do comprimento de
     * Chapa
     * @param largChapa Setar uma informação de valor double do largura de Chapa
     * @param espeChapa Setar uma informação de valor double do espessura de
     * Chapa
     * @param tipoMaterial Setar uma informação de valor String do tipo de
     * materia da Chapa
     * @param fornecedor Setar uma informação de valor String do fornecedor da
     * Chapa
     * @param ou
     * @return
     */
    public static int obterIdChapa(double compChapa, double largChapa, double espeChapa, String tipoMaterial, String fornecedor, boolean ou) {
        String a, sql;
        int h = 0, i = 1, id = 0;
        if (ou) {
            a = " or ";
        } else {
            a = "and";
        }
        sql = "Select id" + Chapa.getTABELA() + " from " + Chapa.getTABELA() + " where ";
        if (!String.valueOf(compChapa).isEmpty() && compChapa > 0) {
            sql += "comprimento >= ?";
            h++;
        }
        if (!String.valueOf(largChapa).isEmpty() && largChapa > 0) {
            if (h > 0) {
                sql += a;
            }
            sql += "largura >= ?";
            h++;
        }
        if (!String.valueOf(espeChapa).isEmpty() && espeChapa > 0) {
            if (h > 0) {
                sql += a;
            }
            sql += "espessura = ?";
            h++;
        }
        if (!tipoMaterial.isEmpty()
                && !tipoMaterial.equals(null)) {
            if (h > 0) {
                sql += a;
            }
            sql += "tipoMaterial = ?";
            h++;
        }
        if (!fornecedor.isEmpty() && !fornecedor.equals(null)) {
            if (h > 0) {
                sql += a;
            }
            sql += "id" + Fornecedor.getTABELA() + " = ?";
        }
        try {
            chapa();
            pst = conexao.prepareStatement(sql);
            if (!String.valueOf(compChapa).isEmpty() && compChapa > 0) {
                pst.setDouble(i++, compChapa);
            }
            if (!String.valueOf(largChapa).isEmpty() && largChapa > 0) {
                pst.setDouble(i++, largChapa);
            }
            if (!String.valueOf(espeChapa).isEmpty() && espeChapa > 0) {
                pst.setDouble(i++, espeChapa);
            }
            if (!tipoMaterial.isEmpty()) {
                pst.setString(i++, tipoMaterial);
            }
            if (!fornecedor.isEmpty()) {
                pst.setInt(i++, Fornecedor.obterIdFornecedortoFornecedor(fornecedor));
            }
            rs = pst.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            Messagem.chamarTela("Metodo obterIDChapa e: " + e + "\n" + sql);
        } finally {
            ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
        }
        return id;
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
     */
    public static void setTipoMateria(String tipoMateria) {
        Chapa.tipoMateria = tipoMateria;
    }

}
