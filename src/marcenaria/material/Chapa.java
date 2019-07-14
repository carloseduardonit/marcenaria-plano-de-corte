/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marcenaria.material;

import java.sql.*;
import marcenaria.dado.ModuloConector;
import marcenaria.pessoa1.Fornecedor;

/**
 * @since 16/05/2019
 * @version 1.0
 * @author Carlos Eduardo dos Santos Figueiredo
 */
public class Chapa {

    /**
     *
     */
    private static Double largChapa, comprChapa, espesChapa, precoChapa;
    /**
     *
     */
    private static int idChapa, quantChapa;
    /**
     *
     */
    private static final String TABELA = Chapa.class.getSimpleName();
    /**
     *
     */
    static String[] tipoMateria = new String[2];
    static Connection conexao;
    static ResultSet rs;
    static PreparedStatement pst;
    static Statement stmt;

    public static void Chapa() {
        conexao = ModuloConector.getConecction();
    }

    /**
     * <b>Este metodo e para utilização para teste.</b>
     *
     * @param args para teste
     */
    public static void main(String[] args) {
        deletadaChapa();
        criadaChapa();
        //Peca.criadaPeca();
        // setComprChapa(220.0);
        ///setLargChapa(110.0);
        //setEspesChapa(1.8);
        //setPrecoChapa(150.0);
        //inserirChapa();
        //
        adicionarChapa(5, 220, 160, 0.18, 280, "MDF", "Carlos");
    }

    /** <b>Este Metodo faz Criação da Tabela Chapa</b>
     *
     */
    public static void criadaChapa() {
        Material.criarMaterial(TABELA);
    }

    /**
     * OK
     *
     */
    public static void deletadaChapa() {
        Material.deletarMaterial(TABELA);
    }

    /**
     * Testa
     *
     * @param tipoMateria
     * @param pos
     */
    public static void adicionarChapa(String tipoMateria, int pos) {
        Material.adicionarMaterial(getTABELA(), getQuantChapa(), getComprChapa(), getLargChapa(), getEspesChapa(), getPrecoChapa(), getTipoMateria(pos), Fornecedor.getLogin());
    }

    /**
     * Testa
     *
     * @param quantChapa
     * @param compChapa
     * @param largChapa
     * @param espeChapa
     * @param precChapa
     * @param tipoMaterial
     * @param fornecedor
     */
    public static void adicionarChapa(int quantChapa, double compChapa, double largChapa, double espeChapa, double precChapa, String tipoMaterial, String fornecedor) {
        Material.adicionarMaterial(getTABELA(), quantChapa, compChapa, largChapa, espeChapa, precChapa, tipoMaterial, fornecedor);
    }

    /**
     * testa
     *
     */
    public static void editarChapa() {
        setTipoMateria();
        Material.editarMaterial(getTABELA(), getTipoMateria(0), getQuantChapa(), getComprChapa(), getLargChapa(), getEspesChapa(), getPrecoChapa(), Fornecedor.getLogin());
    }

    /**
     * testa
     *
     */
    public static void excluirChapa() {
        // Material.excluirMaterial(TABELA, TABELA, quantChapa, 0, 0, 0, 0);
    }

    /**
     * testa
     *
     */
    public static void pesquisarChapa() {

    }

    /**
     * Este Metodo Retornar uma informação de valor inteiro do Id de Chapa
     * conforme o tipo de Materia e espessura da Chapa.
     *@since 01/05/2019
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
     * Este Metodo Retornar uma valor double da largura da Chapa
     *
     * @return Retornar uma valor double da largura da Chapa
     */
    public static Double getLargChapa() {
        return largChapa;
    }

    /**
     * Este Metodo Setar Informar uma valor double da largura da Chapa
     *
     * @param largChapa Setar Informar uma valor double da largura da Chapa
     */
    public static void setLargChapa(Double largChapa) {
        Chapa.largChapa = largChapa;
    }

    /**
     * Este Metodo Retornar um valor double do comprimento da Chapa
     *
     * @return Retornar um valor double do comprimento da Chapa
     */
    public static Double getComprChapa() {
        return comprChapa;
    }

    /**
     * Este Metodo setar Informação um valor double do Comprimento da Chapa
     *
     * @param comprChapa setar Informação um valor double do Comprimento da
     * Chapa
     */
    public static void setComprChapa(Double comprChapa) {
        Chapa.comprChapa = comprChapa;
    }

    /**
     * Este Metodo Retornar um valor double da espessura da Chapa
     *
     * @return Retornar um valor double da espessura da Chapa
     */
    public static Double getEspesChapa() {
        return espesChapa;
    }

    /**
     * Este Metodo setar uma Informação de valor double da espessura da Chapa
     *
     * @param espesChapa setar uma Informação de valor double da espessura da
     * Chapa
     */
    public static void setEspesChapa(Double espesChapa) {
        Chapa.espesChapa = espesChapa;
    }

    /**
     * Este Metodo Retorna um valor double do preco da Chapa
     *
     * @return Retorna um valor double do preco da Chapa
     */
    public static Double getPrecoChapa() {
        return precoChapa;
    }

    /**
     * Este Metodo Setar Informar um valor double do preco da Chapa
     *
     * @param precoChapa Setar Informar um valor double do preco da Chapa
     */
    public static void setPrecoChapa(Double precoChapa) {
        Chapa.precoChapa = precoChapa;
    }

    /**
     * Este Metodo Retornar um valor inteiro do id da Chapa
     *
     * @return Retornar um valor inteiro do id da Chapa
     */
    public static int getIdChapa() {
        return idChapa;
    }

    /**
     * Este Metodo setar Informar um valor inteiro do id da Chapa
     *
     * @param idChapa setar Informar um valor inteiro do id da Chapa
     */
    public static void setIdChapa(int idChapa) {
        Chapa.idChapa = idChapa;
    }

    /**
     * Este Metodo Retornar um valor inteiro referente a quantidade
     *
     * @return Retornar um valor inteiro referente a quantidade
     */
    public static int getQuantChapa() {
        return quantChapa;
    }

    /**
     * Este Metodo Setar uma Informar de valor inteiro na quantidade da Chapa
     *
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
     * @return Retorna uma String com nome da tabela
     */
    public static String getTABELA() {
        return TABELA;
    }

    /**
     * Este Metodo Retornar um array de informação de valor String do tipo de
     * Pessoa
     *
     * @return Retornar um Array de String
     */
    public static String[] getTipoMateria() {
        return tipoMateria;
    }

    /**
     * Este Metodo Retornar uma informação de valor String do tipo de Pessoa
     * conforme a posição de Index.
     *
     * @param pos Informar um valor inteiro do index do Array e deve começa em
     * ZERO(0)
     * @return Retornar uma informação de valor String do tipo de Pessoa
     */
    public static String getTipoMateria(int pos) {
        return tipoMateria[pos];
    }

    /**
     * Este Metodo Setar a informação um valor do tipo String em um array de
     * String sendo:
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
