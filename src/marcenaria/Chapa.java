/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marcenaria;

/**
 * 16/05/2019
 *
 * @author Carlos
 */
public class Chapa {

    private static Double largChapa, comprChapa, espesChapa, precoChapa;
    private static int idChapa, quantChapa;
    private static final String TABELA = Chapa.class.getSimpleName();
    static String[] tipoMateria = new String[2];

    /**
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
    }

    /**OK
     *
     */
    public static void criadaChapa() {
        Material.criarMaterial(TABELA);
    }

    /**OK
     *
     */
    public static void deletadaChapa() {
        Material.deletarMaterial(TABELA);
    }

    /**Testa
     *
     */
    public static void inserirChapa() {
        setTipoMateria();
        Material.adicionarMaterial(getTABELA(), getTipoMateria(1), getQuantChapa(), getComprChapa(), getLargChapa(), getEspesChapa(), getPrecoChapa());

    }

    /**testa
     *
     */
    public static void editarChapa() {
        setTipoMateria();
        Material.editarMaterial(getTABELA(), getTipoMateria(0), getQuantChapa(), getComprChapa(), getLargChapa(), getEspesChapa(), getPrecoChapa());
    }

    /**testa
     *
     */
    public static void excluirChapa() {
        Material.excluirMaterial(TABELA, TABELA, quantChapa, 0, 0, 0, 0);
    }

    /** testa
     *
     */
    public static void pesquisarChapa() {

    }

    /**
     *
     * @return Retornar uma valor double  da largura da Chapa
     */
    public static Double getLargChapa() {
        return largChapa;
    }

    /**
     *
     * @param largChapa Informar uma valor double  da largura da Chapa
     */
    public static void setLargChapa(Double largChapa) {
        Chapa.largChapa = largChapa;
    }

    /**
     *
     * @return Retornar um valor double do comprimento da Chapa
     */
    public static Double getComprChapa() {
        return comprChapa;
    }

    /**
     *
     * @param comprChapa Informar um valor double  do Comprimento da Chapa
     */
    public static void setComprChapa(Double comprChapa) {
        Chapa.comprChapa = comprChapa;
    }

    /**
     *
     * @return Retornar um valor double da espessura da Chapa
     */
    public static Double getEspesChapa() {
        return espesChapa;
    }

    /**
     *
     * @param espesChapa Infromar um valor double da espessura da Chapa
     */
    public static void setEspesChapa(Double espesChapa) {
        Chapa.espesChapa = espesChapa;
    }

    /**
     *
     * @return Retorna um valor double  do preco da Chapa
     */
    public static Double getPrecoChapa() {
        return precoChapa;
    }

    /**
     *
     * @param precoChapa  Informar um valor double  do preco da Chapa
     */
    public static void setPrecoChapa(Double precoChapa) {
        Chapa.precoChapa = precoChapa;
    }

    /**
     *
     * @return Retornar um valor inteiro  do id da Chapa
     */
    public static int getIdChapa() {
        return idChapa;
    }

    /**
     *
     * @param idChapa Informar um valor inteiro  do id da Chapa
     */
    public static void setIdChapa(int idChapa) {
        Chapa.idChapa = idChapa;
    }

    /**
     *
     * @return Retornar um valor inteiro referente a quantidade
     */
    public static int getQuantChapa() {
        return quantChapa;
    }

    /**
     *
     * @param quantChapa Informar um valor inteiro na quantidade da Chapa
     */
    public static void setQuantChapa(int quantChapa) {
        Chapa.quantChapa = quantChapa;
    }

    /**
     *
     * @return Retorna uma String com nome da tabela
     */
    public static String getTABELA() {
        return TABELA;
    }

    /**
     *
     * @return Retornar um Array de String
     */
    public static String[] getTipoMateria() {
        return tipoMateria;
    }

    /**
     *
     * @param pos Informar um valor inteiro do index  do Array e deve começa em ZERO(0)
     * @return Retornar uma String 
     */
    public static String getTipoMateria(int pos) {
        return tipoMateria[pos];
    }

    /**
     * Setar um array de String sendo:
     * 0 iqual a Compensado
     * 1 iqual a MDF
     */
    public static void setTipoMateria() {
        Chapa.tipoMateria[0] = "Compensado";
        Chapa.tipoMateria[1] = "MDF";
    }

    /**
     *
     * @param tipoMateria Informar uma String de Array 
     */
    public static void setTipoMateria(String[] tipoMateria) {
        Chapa.tipoMateria = tipoMateria;
    }

    /**
     *
     * @param tipoMateria Informar um valor do tipo String para setar no campo do Array
     * @param pos Informar um valor inteiro do index  do Array e deve começa em ZERO(0)
     */
    public static void setTipoMateria(String tipoMateria, int pos) {
        Chapa.tipoMateria[pos] = tipoMateria;
    }

}
