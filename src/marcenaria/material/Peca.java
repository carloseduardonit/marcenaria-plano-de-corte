/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marcenaria.material;

import java.sql.*;
import marcenaria.Const.Messagem;

/**
 * 16/05/2019
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
    private static int idPeca, quantPeca;
    private static final String TABELA = Peca.class.getSimpleName();
    private static String[] tipoMateria = new String[2];
    private static double[][] sobra = new double[10][2];
    static Connection conexao;
    static PreparedStatement pst;
    static ResultSet rs;
    static Statement stmt;

    /** <b>Este metodo e para utilização para teste.</b>
     *
     * @param args Informar um valor String para tabela de Material
     */
    public static void main(String[] args) {
       
        adicionarPeca(5, 220, 160, 0.18, 280.50, "MDF");
    }

    /**
     * <b>Este metodo faz Criação da Tabela Peca.</b>
     * <p>
     * Utilizar um metodo da classe Material como Metodo auxiliar o metodo
     * criarMaterial(String Tabela).</p>
     *
     */
    public static void criadaPeca() {
        Material.criarMaterial(TABELA);
    }

    /**
     * <b>Este metodo faz Deletação da Tabela Peca.</b>
     * <p>
     * Este metodo utilizar um metodo da classe Material como Metodo auxiliar o
     * metodo deletarMaterial(String Tabela).</p>
     *
     */
    public static void deletadaPeca() {
        Material.deletarMaterial(TABELA);
    }

    /** <b>Este metodo faz adição na tabela Peça.</b>
     * <p>
     * Utilizar um metodo da classe Material como Metodo auxiliar o metodo
     * adicionarMaterial((String Tabelaterial, int quantidade, double
     * comprimento, double largura, double espessura, double preco, String
     * tipoMa).</p>
     *
     */
    public static void adicionarPeca() {
        setTipoMateria();
        Material.adicionarMaterial(getTABELA(), getQuantPeca(), getComprimento(), getLargura(), getEspessura(), getPreco(), getTipoMateria(0));
    }

    /** <b>Este metodo faz adição na tabela Peça.</b>
     * <p>
     * Utilizar um metodo da classe Material como Metodo auxiliar o metodo
     * adicionarMaterial((String Tabela, int quantidade, double comprimento,
     * double largura, double espessura, double preco, String tipoMaterial).</p>
     *
     * @param Tabela
     * @param quanPeca
     * @param compPeca
     * @param largPeca
     * @param espePeca
     * @param precPeca
     * @param tipoMaterial
     */
    public static void adicionarPeca(int quanPeca, double compPeca, double largPeca, double espePeca, double precPeca, String tipoMaterial) {
        Material.adicionarMaterial(getTABELA(), quanPeca, compPeca, largPeca, espePeca, precPeca, tipoMaterial);
    }

    /**
     * <b>Este metodo faz Edição na tabela Peça.</b>
     * <p>
     * Utilizar um metodo da classe Material como Metodo auxiliar o metodo
     * editarMaterial(String Tabela, String tipoMaterial, int quantidade, double
     * comprimento, double largura, double espessura, double preco).</p>
     *
     */
    public static void editarPeca() {
        setTipoMateria();
        Material.editarMaterial(getTABELA(), getTipoMateria(1), getQuantPeca(), getComprimento(), getLargura(), getEspessura(), getPreco());
    }

    /**
     * <b>Este metodo faz a Exclução na tabela Peça.</b><p>
     * Utilizar um metodo da classe Material como Metodo auxiliar o metodo
     * excluirMaterial(String Tabela, String tipoMaterial, int quantidade,
     * double comprimento, double largura, double espessura, double preco).</p>
     */
    public static void excluirPeca() {
        Material.excluirMaterial(TABELA, TABELA, quantPeca, 0, 0, 0, 0);
    }

    /** <b>Este metodo faz Pesquisar na tabela Peça.</b>
     * <p>
     * Utilizar um metodo da classe Material como Metodo auxiliar o metodo</p>
     */
    public static void pesquisarPeca() {

    }

    public static int obterIdPeca(String tipoMaterial, double espessura) {
        return Material.obterIdMaterial(Peca.getTABELA(), tipoMaterial, espessura);
    }

    /**
     * Estou preparando soma as peça Se 109,5X79,5
     *
     * @param compChapa
     * @param largChapa
     * @param compPeca
     * @param largPeca
     * @param serra
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
     * @param compChapa
     * @param largChapa
     * @param compPeca
     * @param largPeca
     * @param serra
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
     * @param compChapa
     * @param largChapa
     * @param compPeca
     * @param largPeca
     * @param serra
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
     * pos1 =coluna.</p
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
