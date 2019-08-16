/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marcenaria.material;

import java.sql.*;
import marcenaria.dado.ModuloConector;


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
    private static Double largChapa, comprChapa, espesChapa, precoChapa;
    /**
     * Variaveis do tipo inteiro para id e quantidade da chapa;
     */
    private static int idChapa, quantChapa;
    /**
     * Variaveis do tipo String para nome da Tabela Chapa;
     */
    private static final String TABELA = Chapa.class.getSimpleName();
    /**
     * Array do tipo String para tipo de materia da Chapa;
     */
    private static String[] tipoMateria = new String[2];
    private static Connection conexao;
    private static ResultSet rs;
    private static ResultSetMetaData rsmd;
    private static PreparedStatement pst;
    private static Statement stmt;

    /**Este metodo realiza a conexao com o banco de dado
     *    @since 16/05/2019
     * @version 1.0
     */
    public static void Chapa() {
        conexao = ModuloConector.getConecction();
    }

    /**
     * <b>Este metodo e para utilização para teste.</b>
     *
     * @param args para teste
     */
    public static void main(String[] args) {
        //deletadaChapa();
        //criadaChapa();
        //Peca.criadaPeca();
        // setComprChapa(220.0);
        ///setLargChapa(110.0);
        //setEspesChapa(1.8);
        //setPrecoChapa(150.0);
        //inserirChapa();

       adicionarChapa(5, 220, 160, 0.18, 280, "MDF", "Carlos");
    }

    /** <b>Este Metodo faz Criação da Tabela Chapa utilizando uma classe  e metodo auxiliar</b>
     *
     * @since 16/05/2019
     * @version 1.0
     */
    public static void criadaChapa() {
        Material.criarMaterial(TABELA);
    }

    /**
     * <b>Este Metodo faz Deletação da Tabela Chapa</b>
     *
     * @since 16/05/2019
     * @version 1.0
     */
    public static void deletadaChapa() {
        Material.deletarMaterial(TABELA);
    }

    /**
     * Tem que faze-lo
     *
     * @param fornecedor
     * @since 01/05/2019
     * @version 1.0Testa
     *
     * @param tipoMateria ti
     * @param pos po
     */
    public static void adicionarChapa(String tipoMateria, int pos, String fornecedor) {
        Material.adicionarMaterial(getTABELA(), getQuantChapa(), getComprChapa(), getLargChapa(), getEspesChapa(), getPrecoChapa(), getTipoMateria(pos), fornecedor );
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
        Material.adicionarMaterial(getTABELA(), quantChapa, compChapa, largChapa, espeChapa, precChapa, tipoMaterial, fornecedor);
    }

    /**
     * Tem que faze-lo
     *
     * @param fornecedor
     * @since 01/05/2019
     * @version 1.0
     */
    public static void editarChapa(String fornecedor) {
        setTipoMateria();
        Material.editarMaterial(getTABELA(), getTipoMateria(0), getQuantChapa(), getComprChapa(), getLargChapa(), getEspesChapa(), getPrecoChapa(), fornecedor);
    }

    /**
     * Tem que faze-lo
     *
     * @since 01/05/2019
     * @version 1.0
     *
     */
    public static void excluirChapa() {
        // Material.excluirMaterial(TABELA, TABELA, quantChapa, 0, 0, 0, 0);
    }

    /**
     * Tem que faze-lo
     *
     * @since 01/05/2019
     * @version 1.0
     */
    public static void pesquisarChapa() {

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
// Gets e Sets

    /**
     * Este Metodo Retornar uma valor double da largura da Chapa
     *
     ** @since 16/05/2019
     * @version 1.0
     * @return Retornar uma valor double da largura da Chapa
     */
    public static Double getLargChapa() {
        return largChapa;
    }

    /**
     * Este Metodo Setar Informar uma valor double da largura da Chapa
     *
     ** @since 16/05/2019
     * @version 1.0
     * @param largChapa Setar Informar uma valor double da largura da Chapa
     */
    public static void setLargChapa(Double largChapa) {
        Chapa.largChapa = largChapa;
    }

    /**
     * Este Metodo Retornar um valor double do comprimento da Chapa
     *
     ** @since 16/05/2019
     * @version 1.0
     * @return Retornar um valor double do comprimento da Chapa
     */
    public static Double getComprChapa() {
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
    public static void setComprChapa(Double comprChapa) {
        Chapa.comprChapa = comprChapa;
    }

    /**
     * Este Metodo Retornar um valor double da espessura da Chapa
     *
     ** @since 16/05/2019
     * @version 1.0
     * @return Retornar um valor double da espessura da Chapa
     */
    public static Double getEspesChapa() {
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
    public static void setEspesChapa(Double espesChapa) {
        Chapa.espesChapa = espesChapa;
    }

    /**
     * Este Metodo Retorna um valor double do preco da Chapa
     *
     ** @since 16/05/2019
     * @version 1.0
     * @return Retorna um valor double do preco da Chapa
     */
    public static Double getPrecoChapa() {
        return precoChapa;
    }

    /**
     * Este Metodo Setar Informar um valor double do preco da Chapa
     *
     ** @since 16/05/2019
     * @version 1.0
     * @param precoChapa Setar Informar um valor double do preco da Chapa
     */
    public static void setPrecoChapa(Double precoChapa) {
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
    public static String[] getTipoMateria() {
        return tipoMateria;
    }

    /**
     * Este Metodo Retornar uma informação de valor String do tipo de Pessoa
     * conforme a posição de Index.
     *
     ** @since 16/05/2019
     * @version 1.0
     * @param pos Informar um valor inteiro do index do Array e deve começa em
     * ZERO(0)
     * @return Retornar uma informação de valor String do tipo de Pessoa
     */
    public static String getTipoMateria(int pos) {
        return tipoMateria[pos];
    }

    /**
     * Este Metodo Setar a informação um valor do tipo String em um array do
     * tipo de Materia da Chapa. String sendo:
     * <p>
     * <b>0</b> iqual a Compensado</p>
     * <p>
     * <b>1</b> iqual a MDF</p>
     *
     * @since 01/05/2019
     * @version 1.0
     */
    public static void setTipoMateria() {
        Chapa.tipoMateria[0] = "Compensado";
        Chapa.tipoMateria[1] = "MDF";
    }

    /**
     * Este Metodo Setar a informação um valor do tipo String de Array
     *
     * @since 01/05/2019
     * @version 1.0
     * @param tipoMateria Setar Informar um valor do tipo String de Array
     */
    public static void setTipoMateria(String[] tipoMateria) {
        Chapa.tipoMateria = tipoMateria;
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
    public static void setTipoMateria(String tipoMateria, int pos) {
        Chapa.tipoMateria[pos] = tipoMateria;
    }

}
