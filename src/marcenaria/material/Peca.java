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
import marcenaria.pessoa.Fornecedor;
import marcenaria.pessoa.cliente.Projeto;

/**
 * @since 16/05/2019
 *
 * @version 1.0
 * @author Carlos Eduardo dos Santos Figueiredo
 */
public class Peca {
    

    /**
     *
     */
    int[][][][] peca = new int[quantPeca][quantPeca][quantPeca][quantPeca];
    private static Double largura, comprimento, espessura, preco;
    private static int idPeca, quantPeca, index;
    private static final String TABELA = Peca.class.getSimpleName();
    private static String[] tipoMateria = new String[2];
    private static double[][] sobra = new double[10][2];
    private static Connection conexao;
    private static PreparedStatement pst;
    private static ResultSet rs;
    private static ResultSetMetaData rsmd;
    private static Statement stmt;

    /**
     * Este metodo realiza a conexao com o banco de dado
     *
     * @since 16/05/2019
     * @version 1.0
     */
    private static void peca() {
        conexao = ModuloConector.getConecction();
    }

    /**
     * <b>Este metodo faz Criação da Tabela Peca.</b>
     * <p>
     * Utilizar um metodo da classe Material como Metodo auxiliar o metodo
     * criarMaterial(String Tabela).</p>
     *
     */
    public static void criadaPeca() {
        if (Table.VerificarNaoExistirTabela(Chapa.getTABELA())) {
            Chapa.criadaChapa();
        }
        if (Table.VerificarNaoExistirTabela(Projeto.getTABELA())) {
            Projeto.criarProjeto();
        }
        String sql = "create table if not exists " + Peca.getTABELA()
                + "(id" + Peca.getTABELA() + " int primary key auto_increment,"
                + "quantidade int not null default 0,"
                + "comprimento double(7,2) not null default 0,"
                + "largura double(7,2) not null default 0,"
                + "espessura double(4,2) not null default 0,"
                + "preco double(10,2) not null default 0, "
                + "tipoMaterial varchar(30) not null,"
                + "id" + Chapa.getTABELA() + " int not null default 0,"
                + "id" + Projeto.getTABELA() + " int not null default 0, "
                + "foreign key (id" + Projeto.getTABELA() + ") references " + Projeto.getTABELA() + "(id" + Projeto.getTABELA()
                + "), foreign key(id" + Chapa.getTABELA() + ") references " + Chapa.getTABELA() + "(id" + Chapa.getTABELA() + "))";
        Table.criarTabela(sql, Peca.getTABELA());
    }

    /**
     * <b>Este metodo faz Deletação da Tabela Peca.</b>
     * <p>
     * Este metodo utilizar um metodo da classe Material como Metodo auxiliar o
     * metodo deletarMaterial(String Tabela).</p>
     *
     */
    public static void deletadaPeca() {
        if (!Table.VerificarNaoExistirTabela(Pedaco.getTABELA())) {
            Pedaco.deletaPedaco();
        }
        Table.deletarTabela(Peca.getTABELA());
    }

    /** <b>Este metodo faz adição na tabela Peça.</b>
     * <p>
     * Utilizar um metodo da classe Material como Metodo auxiliar o metodo
     * adicionarMaterial((String Tabela, int quantidade, double comprimento,
     * double largura, double espessura, double preco, String tipoMaterial).</p>
     *
     * @param quanPeca Setar informação de valor inteiro da quantidade de Peça
     * @param compPeca Setar informação de valor double do Comprimento da Peça
     * @param largPeca Setar informação de valor double da largura da Peça
     * @param espePeca Setar informação de valor double de espessura da Peça
     * @param precPeca Setar informação de valor double de preço da Peça
     * @param fornecedor Setar Informação de valor String do nome do fornecedor
     * @param tipoMaterial Setar Informação de valor String tipo de Material da
     * Peça
     */
    public static void adicionarPeca(int quanPeca, double compPeca, double largPeca, double espePeca, double precPeca, String tipoMaterial, String fornecedor) {
        if (!Peca.HaCampoVazio(quanPeca, compPeca, largPeca, espePeca, precPeca, tipoMaterial, fornecedor)) {
            int idChapa = 0;
            try {
                String sql = "insert into " + Peca.getTABELA() + "(quantidade,comprimento,largura,espessura,preco, tipoMaterial,id"
                        + Chapa.getTABELA() + ") values (?,?,?,?,?,?,?)";
                peca();
                pst = conexao.prepareStatement(sql);
                pst.setInt(1, quanPeca);
                pst.setDouble(2, compPeca);
                pst.setDouble(3, largPeca);
                pst.setDouble(4, espePeca);
                pst.setDouble(4, precPeca);
                pst.setInt(5, idChapa);
                int adicionar = pst.executeUpdate();
                if (adicionar > 0) {
                    Messagem.chamarTela(Messagem.ADICIONADO(sql));
                }
            } catch (SQLException e) {
                Messagem.chamarTela(e);
            } finally {
                ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
            }
        } else {
            Messagem.chamarTela(Messagem.VAZIO(Peca.campoVazio(quanPeca, compPeca, largPeca, espePeca, precPeca, tipoMaterial, fornecedor)));
        }

    }

    /**
     * com erro
     * <b>Este metodo faz Edição na tabela Peça.</b>
     * <p>
     * Utilizar um metodo da classe Material como Metodo auxiliar o metodo
     * editarMaterial(String Tabela, String tipoMaterial, int quantidade, double
     * comprimento, double largura, double espessura, double preco).</p>
     *
     * @param quanPeca Setar informação de valor inteiro da quantidade de Peça
     * @param compPeca Setar informação de valor double do Comprimento da Peça
     * @param largPeca Setar informação de valor double da largura da Peça
     * @param espePeca Setar informação de valor double de espessura da Peça
     * @param precPeca Setar informação de valor double de preço da Peça
     * @param fornecedor Setar Informação de valor String do nome do fornecedor
     * @param tipoMaterial Setar Informação de valor String tipo de Material da
     * Peça
     */
    public static void editarPeca(int quanPeca, double compPeca, double largPeca, double espePeca, double precPeca, String tipoMaterial, String fornecedor) {
        if (!HaCampoVazio(quanPeca, compPeca, largPeca, espePeca, precPeca, tipoMaterial, fornecedor)) {
            if (Fornecedor.existeroFornecedor(fornecedor)) {
                int idFornecedor = Fornecedor.obterIdPessoa(fornecedor);
                try {
                    String sql = "update from " + Peca.getTABELA() + " set quantidade = ?,comprimento = ? ,largura = ?,espessura =?, preco = ?, tipoMaterial = ? where id" + Fornecedor.getTABELA() + " = ?";
                    peca();
                    pst = conexao.prepareStatement(sql);
                    pst.setInt(1, quanPeca);
                    pst.setDouble(2, compPeca);
                    pst.setDouble(3, largPeca);
                    pst.setDouble(4, espePeca);
                    pst.setDouble(5, precPeca);
                    pst.setString(6, tipoMaterial);
                    pst.setInt(7, idFornecedor);
                    int editado = pst.executeUpdate();
                    if (editado > 0) {
                        Messagem.chamarTela(Messagem.EDITADO(sql));
                    }
                } catch (SQLException e) {
                    Messagem.chamarTela(e);
                } finally {
                    ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
                }
            } else {
                Messagem.chamarTela("");
            }
        } else {
            Messagem.chamarTela("");
        }
        //setTipoMateria();
        // Material.editarMaterial(getTABELA(), getTipoMateria(1), getQuantPeca(), getComprimento(), getLargura(), getEspessura(), getPreco(), fornecedor);
    }

    /**
     * <b>Este metodo faz a Exclução na tabela Peça.</b><p>
     * Utilizar um metodo da classe Material como Metodo auxiliar o metodo
     * excluirMaterial(String Tabela, String tipoMaterial, int quantidade,
     * double comprimento, double largura, double espessura, double preco,String
     * fornecedo).</p>
     *
     * @param quanPeca Setar informação de valor inteiro da quantidade de Peça
     * @param compPeca Setar informação de valor double do Comprimento da Peça
     * @param largPeca Setar informação de valor double da largura da Peça
     * @param espePeca Setar informação de valor double de espessura da Peça
     * @param precPeca Setar informação de valor double de preço da Peça
     * @param fornecedor Setar Informação de valor String do nome do fornecedor
     * @param tipoMaterial Setar Informação de valor String tipo de Material da
     * Peça
     */
    public static void excluirPeca(int quanPeca, double compPeca, double largPeca, double espePeca, double precPeca, String tipoMaterial, String fornecedor) {
        if (!Peca.HaCampoVazio(quanPeca, compPeca, largPeca, espePeca, precPeca, tipoMaterial, fornecedor)) {
            try {
                String sql = "";
                peca();
                pst = conexao.prepareStatement(sql);
                pst.setString(1, fornecedor);
                int excluir = pst.executeUpdate();
                if (excluir > 0) {
                    Messagem.chamarTela(Messagem.EXCLUIDO(sql));
                }
            } catch (SQLException e) {
                Messagem.chamarTela(e);
            } finally {
                ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
            }
        } else {
            Messagem.chamarTela(Peca.campoVazio(quanPeca, compPeca, largPeca, espePeca, precPeca, tipoMaterial, fornecedor));
        }
        //Material.excluirMaterial(TABELA, TABELA, quantPeca, 0, 0, 0, 0, fornecedor);
    }

    /** <b>Este metodo faz Pesquisar na tabela Peça.</b>
     * <p>
     * Utilizar um metodo da classe Material como Metodo auxiliar o metodo</p>
     *
     * @param quanPeca Setar informação de valor inteiro da quantidade de Peça
     * @param compPeca Setar informação de valor double do Comprimento da Peça
     * @param largPeca Setar informação de valor double da largura da Peça
     * @param espePeca Setar informação de valor double de espessura da Peça
     * @param precPeca Setar informação de valor double de preço da Peça
     * @param fornecedor Setar Informação de valor String do nome do fornecedor
     * @param tipoMaterial Setar Informação de valor String tipo de Material da
     * Peça
     */
    public static void pesquisarPeca(int quanPeca, double compPeca, double largPeca, double espePeca, double precPeca, String tipoMaterial, String fornecedor) {
        if (!Peca.HaCampoVazio(quanPeca, compPeca, largPeca, espePeca, precPeca, tipoMaterial, fornecedor)) {
            try {
                String sql = "";
                peca();
                pst = conexao.prepareStatement(sql);
                pst.setString(1, fornecedor);
                rs = pst.executeQuery();
                if (rs.next()) {
                    setQuantPeca(rs.getInt(1));
                    setComprimento(rs.getDouble(1));
                    setLargura(rs.getDouble(2));
                    setEspessura(rs.getDouble(3));
                    setPreco(rs.getDouble(4));
                    setTipoMateria(rs.getString(5));
                }
            } catch (SQLException e) {
                Messagem.chamarTela(e);
            } finally {
                ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
            }
        } else {
            Messagem.chamarTela(Peca.campoVazio(quanPeca, compPeca, largPeca, espePeca, precPeca, tipoMaterial, fornecedor));
        }
    }

    public static int obterIdPeca(String tipoMaterial, double espessura) {
        return Material.obterIdMaterial(Peca.getTABELA(), tipoMaterial, espessura);
    }

    /** <b>Este metodo .</b>
     * <p>
     * </p>
     *
     * @param quanPeca Setar informação de valor inteiro da quantidade de Peça
     * @param compPeca Setar informação de valor double do Comprimento da Peça
     * @param largPeca Setar informação de valor double da largura da Peça
     * @param espePeca Setar informação de valor double de espessura da Peça
     * @param precPeca Setar informação de valor double de preço da Peça
     * @param fornecedor Setar Informação de valor String do nome do fornecedor
     * @param tipoMaterial Setar Informação de valor String tipo de Material da
     * Peça
     */
    private static String[] campoVazio(int quanPeca, double compPeca, double largPeca, double espePeca, double precPeca, String tipoMaterial, String fornecedor) {
        String[] vazio = new String[7];
        boolean qp = false, cp = false, lp = false, ep = false, pp = false, tm = false, f = false;
        for (int i = 0; i < vazio.length; i++) {
            if (String.valueOf(quanPeca).isEmpty() && !qp) {
                vazio[i] = "\nquantidade de peça";
                qp = true;
            } else if (String.valueOf(compPeca).isEmpty() && !cp) {
                vazio[i] = "\ncomprimento de peça";
                cp = true;
            } else if (String.valueOf(largPeca).isEmpty() && !lp) {
                vazio[i] = "\nlargura de peça";
                lp = true;
            } else if (String.valueOf(espePeca).isEmpty() && !ep) {
                vazio[i] = "\nespessura de peça";
                ep = true;
            } else if (String.valueOf(precPeca).isEmpty() && !pp) {
                vazio[i] = "\nPreço da Peça";
                pp = true;
            } else if (tipoMaterial.isEmpty() && !tm) {
                vazio[i] = "\ntipo de materia da Peça";
                tm = true;
            } else if (fornecedor.isEmpty() && !f) {
                vazio[i] = "\nFornecedor da peça";
                f = true;
            }
        }
        return vazio;
    }

    /** <b>Este metodo .</b>
     * 
     *
     * @param quanPeca Setar informação de valor inteiro da quantidade de Peça
     * @param compPeca Setar informação de valor double do Comprimento da Peça
     * @param largPeca Setar informação de valor double da largura da Peça
     * @param espePeca Setar informação de valor double de espessura da Peça
     * @param precPeca Setar informação de valor double de preço da Peça
     * @param fornecedor Setar Informação de valor String do nome do fornecedor
     * @param tipoMaterial Setar Informação de valor String tipo de Material da
     * Peça
     * @return
     */
    public static boolean HaCampoVazio(int quanPeca, double compPeca, double largPeca, double espePeca, double precPeca, String tipoMaterial, String fornecedor) {
        return String.valueOf(quanPeca).isEmpty() && String.valueOf(compPeca).isEmpty() && String.valueOf(largPeca).isEmpty() && String.valueOf(espePeca).isEmpty() && String.valueOf(precPeca).isEmpty() && tipoMaterial.isEmpty() && fornecedor.isEmpty();
    }

    /**
     * Estou preparando soma as peça Se 109,5X79,5
     *
     * @param compChapa Setar uma informaÇão de valor double do comprimento da
     * Chapa.
     * @param largChapa Setar uma informação de valor double da largura da
     * Chapa.
     * @param compPeca Setar uma informaÇão de valor double do comprimento da
     * Peça.
     * @param largPeca Setar uma informação de valor double da largura da Peça.
     * @param serra Setar uma informação de valor double da Espessura da Serra.
     */
    public static void SomarVerticalPeca(double compChapa, double largChapa, double compPeca, double largPeca, double serra) {
        double somar;
        String erro = "";
        if ((largPeca <= 0 || compPeca <= 0)) {
            if ((largPeca <= 0 && compPeca > 0)) {
                erro = "largura menor ou Igual a Zero(0).";
            } else if ((largPeca > 0 && compPeca <= 0)) {
                erro = "comprimento menor ou Igual a Zero(0).";
            } else {
                erro = "comprimento e largura menor ou Igual a Zero(0).";
            }
            Messagem.chamarTela(erro);
        } else {
            if (compChapa <= getComprimento() + compPeca + serra && largChapa <= getLargura()) {
                setComprimento(getComprimento() + compPeca + serra);

            } else {
                setComprimento(getComprimento() + compPeca + serra);
                //Sobra(compChapa, largChapa, 0, 0, 0);
            }
        }
    }

    /**
     * PREPARADO
     *
     * @param compChapa Setar uma informaÇão de valor double do comprimento da
     * Chapa.
     * @param largChapa Setar uma informação de valor double da largura da
     * Chapa.
     * @param compPeca Setar uma informaÇão de valor double do comprimento da
     * Peça.
     * @param largPeca Setar uma informação de valor double da largura da Peça.
     * @param serra Setar uma informação de valor double da Espessura da Serra.
     */
    public static void SomarhorizontalPeca(double compChapa, double largChapa, double compPeca, double largPeca, double serra) {
        double somar;
        String erro = "";
        if ((largPeca <= 0 || compPeca <= 0)) {
            if ((largPeca <= 0 && compPeca > 0)) {
                erro = "largura menor ou Igual a Zero(0).";
            } else if ((largPeca > 0 && compPeca <= 0)) {
                erro = "comprimento menor ou Igual a Zero(0).";
            } else {
                erro = "comprimento e largura menor ou Igual a Zero(0).";
            }
            Messagem.chamarTela(erro);
        } else {
            if (getSobra(0, 0) <= compPeca + serra && getSobra(0, 0) <= largPeca + serra) {
            } else if (compChapa <= getComprimento() && largChapa <= getLargura() + largPeca + serra) {
                setLargura(getLargura() + largPeca + serra);
            } else {
                setLargura(getLargura() + largPeca + serra);
            }
        }
    }

    /**
     * @param compChapa Setar uma informaÇão de valor double do comprimento da
     * Chapa.
     * @param largChapa Setar uma informação de valor double da largura da
     * Chapa.
     * @param compPeca Setar uma informaÇão de valor double do comprimento da
     * Peça.
     * @param largPeca Setar uma informação de valor double da largura da Peça.
     * @param serra Setar uma informação de valor double da Espessura da Serra.
     */
    public static void Sobra(double compChapa, double largChapa, double compPeca, double largPeca, double serra) {

    }

    public static void QuantPeca() {

    }

    //Sets e Gets
    /** <b>Este Metodo Retornar Informação com valor double no largura da
     * Peça.</b>
     *
     * @return Retornar um valor Double da largura da Peça.
     */
    public static Double getLargura() {
        return largura;
    }

    /** <b>Este Metodo setar Informação com valor double no largura da Peça.</b>
     *
     * @param largura Informar um valor Double da largura.
     */
    public static void setLargura(Double largura) {
        Peca.largura = largura;
    }

    /** <b>Este Metodo Retornar Informação com valor double no comprimento da
     * Peça.</b>
     *
     * @return Retornar um valor Double do comprimento.
     */
    public static Double getComprimento() {
        return comprimento;
    }

    /** <b>Este Metodo setar Informação com valor double no comprimento da
     * Peça.</b>
     *
     * @param comprimento Informar um valor Double do comprimento.
     */
    public static void setComprimento(Double comprimento) {
        Peca.comprimento = comprimento;
    }

    /** <b>Este Metodo Retornar Informação com valor double no Espessura da
     * Peça.</b>
     *
     * @return Retornar um valor Double da Espessura.
     */
    public static Double getEspessura() {
        return espessura;
    }

    /** <b>Este Metodo setar Informação com valor double no Espessura da
     * Peça.</b>
     *
     * @param espessura Informar um valor Double da Espessura.
     */
    public static void setEspessura(Double espessura) {
        Peca.espessura = espessura;
    }

    /** <b>Este Metodo Retornar Informação com valor double no preço da
     * Peça.</b>
     *
     * @return Retornar um valor Double do Preço.
     */
    public static Double getPreco() {
        return preco;
    }

    /** <b>Este Metodo setar Informação com valor double no preço da Peça.</b>
     *
     * @param preco Informar um valor Double do Preço.
     */
    public static void setPreco(Double preco) {
        Peca.preco = preco;
    }

    /** <b>Este Metodo Retornar Informação com valor String do nome da Classe
     * Peça.</b>
     *
     * @return Retorna uma String com nome da tabela.
     */
    public static String getTABELA() {
        return TABELA;
    }

    /** <b>Este Metodo Retornar Informação com valor inteiro na quantidade da
     * Peça.</b>
     *
     * @return Retornar um valor inteiro na quantidade de Peça.
     */
    public static int getQuantPeca() {
        return quantPeca;
    }

    /** <b>Este Metodo setar Informação com valor inteiro na quantidade da
     * Peça.</b>
     *
     * @param quantPeca Informar um valor inteiro na quantidade de Peça.<b>Este
     * Metodo setar Informação com valor inteiro na quantidade da Peça</b>
     */
    public static void setQuantPeca(int quantPeca) {
        Peca.quantPeca = quantPeca;
    }

    /** <b>Este metodo Retornar Informação inteira no ID peça.</b>
     *
     * @return Retornar um valor inteiro do id da Peça.
     */
    public static int getIdPeca() {
        return idPeca;
    }

    /** <b>Este metodo setar Informação inteira no ID peça.</b>
     *
     * @param idPeca Informar um valor inteiro do id da Peça.
     */
    public static void setIdPeca(int idPeca) {
        Peca.idPeca = idPeca;
    }

    /** <b>Este metodo Retornar um Array de String do Tipo de Materia.</b>
     *
     * @return Retornar uma Array de String do Tipo de Materia.
     */
    public static String[] getTipoMateria() {
        return tipoMateria;
    }

    /** <b>Este metodo Retornar um valor string da posição de um Array do Tipo
     * de Materia.</b>
     *
     * @param pos Informar um valor inteiro do index do Array e deve começa em
     * <b>ZERO(0)</b>;
     * @return Retornar um valor String do Array do Tipo de Materia.
     */
    public static String getTipoMateria(int pos) {
        return tipoMateria[pos];
    }

    /**
     * <b>Este Metodo Setar Informação um array de String sendo:</b>
     * <p>
     * <b>0</b> iqual a <b>Compensado</b>;</p>
     * <p>
     * <b>1</b> iqual a <b>MDF</b>.</p>
     */
    public static void setTipoMateria() {
        Peca.tipoMateria[0] = "Compensado";
        Peca.tipoMateria[1] = "MDF";
    }

    /**
     * <b>Este Metodo Setar Informação um array de String sendo:</b>
     * <p>
     * <b>0</b> iqual a <b>Compensado</b>;</p>
     * <p>
     * <b>1</b> iqual a <b>MDF</b>.</p>
     *
     * @param tipoMateria
     */
    public static void setTipoMateria(String tipoMateria) {
        if (index < Peca.tipoMateria.length) {
            Peca.tipoMateria[index] = tipoMateria;
            index++;
        } else {
            index = 0;
        }
    }

    /** <b>Este Metodo setar Informação em uma Array de String.</b>
     *
     * @param tipoMateria Informar um Array de String para tipo de Material.
     */
    public static void setTipoMateria(String[] tipoMateria) {
        Peca.tipoMateria = tipoMateria;
    }

    /** <b>Este Metodo setar Informação em um do index do Array de String no
     * tipo de materia.</b>
     *
     * @param tipoMateria Informar um valor do tipo String para setar no campo
     * do Array;
     * @param pos Informar um valor inteiro do index do Array e deve começa em
     * <b>ZERO(0)</b>.
     */
    public static void setTipoMateria(String tipoMateria, int pos) {
        if (tipoMateria.isEmpty()) {
            Messagem.chamarTela(Messagem.VAZIO("Tipo de Materia"));
        } else {
            Peca.tipoMateria[pos] = tipoMateria;
        }
    }

    /**
     * <b>Este metodo Retornar um Array bidirecional com valor double</b>
     *
     * @return Retornar um Array bidirecional com valor double da Sobra
     */
    public static double[][] getSobra() {
        return sobra;
    }

    /**
     * <b> Este metodo Retornar um com valor double da sobra do peça.</b>
     * <p>
     * double[pos][pos1],</p>
     * <p>
     * pos = linha,</p>
     * <p>
     * pos1 =coluna.</p>
     *
     * @param pos Informar um valor inteiro do index do Array da posição linha e
     * deve começa em <b>ZERO(0)</b>.
     * @param pos1 Informar um valor inteiro do index do Array da posição coluna
     * e deve começa em <b>ZERO(0)</b>.
     * @return Retornar um valor double da sobra.
     */
    public static double getSobra(int pos, int pos1) {
        return sobra[pos][pos1];
    }

    /**
     * <b> Este metodo Retornar um com valor double da sobra do peça referente a
     * comprimento.</b>
     * <p>
     * double[pos][0],</p>
     * <p>
     * pos = linha.</p>
     *
     * @param pos Informar um valor inteiro do index do Array da posição linha e
     * deve começa em <b>ZERO(0)</b>.
     *
     * @return Retornar um valor double da sobra.
     */
    public static double getSobraC(int pos) {
        return sobra[pos][0];
    }

    /**
     * <b> Este metodo Retornar um com valor double da sobra do peça referente a
     * largura.</b>
     * <p>
     * double[pos][1],</p>
     * <p>
     * pos = linha,</p>
     *
     * @param pos Informar um valor inteiro do index do Array da posição linha e
     * deve começa em <b>ZERO(0)</b>.
     *
     * @return Retornar um valor double da sobra.
     */
    public static double getSobraL(int pos) {
        return sobra[pos][1];
    }

    /** <b>Este metodo Setar informar um Array double na Sobra</b>.
     *
     * @param sobra informar um Array double na Sobra.
     */
    public static void setSobra(double[][] sobra) {
        Peca.sobra = sobra;
    }

    /** <b> Este metodo Setar informaÇão um com valor double da sobra do
     * peça.</b>
     * <p>
     * double[pos][pos1],</p>
     * <p>
     * pos = linha,</p>
     * <p>
     * pos1 =coluna.</p>
     *
     * @param pos Informar um valor inteiro do index do Array da posição linha e
     * deve começa em <b>ZERO(0)</b>.
     * @param pos1 Informar um valor inteiro do index do Array da posição coluna
     * e deve começa em <b>ZERO(0)</b>.
     * @param sobra Informar um valor double para anexar no Array Sobra
     */
    public static void setSobra(int pos, int pos1, double sobra) {
        Peca.sobra[pos][pos1] = sobra;
    }

    /** <b> Este metodo Setar informaÇão um com valor double da sobra do
     * peça.</b>
     * <p>
     * double[pos][pos1],</p>
     * <p>
     * pos = linha,</p>
     *
     * @param pos Informar um valor inteiro do index do Array da posição linha e
     * deve começa em <b>ZERO(0)</b>.
     * @param co Informar um valor double sendo anexado utilizado como metodo
     * auxiliar setSobra(int pos, int pos1, double sobra) e usando um for
     * interno, aonde parametro pos1 = 0.
     * @param la Informar um valor double sendo anexado utilizado como metodo
     * auxiliar setSobra(int pos, int pos1, double sobra) e usando um for
     * interno, aonde parametro pos1 = 1.
     */
    public static void setSobra(int pos, double co, double la) {
        if (co <= 0 || la <= 0) {

        } else {
            for (int pos1 = 0; pos1 < 2; pos1++) {
                if (pos1 == 0) {
                    Peca.sobra[pos][pos1] = co;
                } else {
                    Peca.sobra[pos][pos1] = la;
                }
            }
        }
    }

}
