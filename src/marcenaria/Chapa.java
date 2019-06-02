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
     * @param args
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
     * @return
     */
    public static Double getLargChapa() {
        return largChapa;
    }

    /**
     *
     * @param largChapa
     */
    public static void setLargChapa(Double largChapa) {
        Chapa.largChapa = largChapa;
    }

    /**
     *
     * @return
     */
    public static Double getComprChapa() {
        return comprChapa;
    }

    /**
     *
     * @param comprChapa
     */
    public static void setComprChapa(Double comprChapa) {
        Chapa.comprChapa = comprChapa;
    }

    /**
     *
     * @return
     */
    public static Double getEspesChapa() {
        return espesChapa;
    }

    /**
     *
     * @param espesChapa
     */
    public static void setEspesChapa(Double espesChapa) {
        Chapa.espesChapa = espesChapa;
    }

    /**
     *
     * @return
     */
    public static Double getPrecoChapa() {
        return precoChapa;
    }

    /**
     *
     * @param precoChapa
     */
    public static void setPrecoChapa(Double precoChapa) {
        Chapa.precoChapa = precoChapa;
    }

    /**
     *
     * @return
     */
    public static int getIdChapa() {
        return idChapa;
    }

    /**
     *
     * @param idChapa
     */
    public static void setIdChapa(int idChapa) {
        Chapa.idChapa = idChapa;
    }

    /**
     *
     * @return
     */
    public static int getQuantChapa() {
        return quantChapa;
    }

    /**
     *
     * @param quantChapa
     */
    public static void setQuantChapa(int quantChapa) {
        Chapa.quantChapa = quantChapa;
    }

    /**
     *
     * @return
     */
    public static String getTABELA() {
        return TABELA;
    }

    /**
     *
     * @return
     */
    public static String[] getTipoMateria() {
        return tipoMateria;
    }

    /**
     *
     * @param pos
     * @return
     */
    public static String getTipoMateria(int pos) {
        return tipoMateria[pos];
    }

    /**
     *
     */
    public static void setTipoMateria() {
        Chapa.tipoMateria[0] = "Compensado";
        Chapa.tipoMateria[1] = "MDF";
    }

    /**
     *
     * @param tipoMateria
     */
    public static void setTipoMateria(String[] tipoMateria) {
        Chapa.tipoMateria = tipoMateria;
    }

    /**
     *
     * @param tipoMateria
     * @param pos
     */
    public static void setTipoMateria(String tipoMateria, int pos) {
        Chapa.tipoMateria[pos] = tipoMateria;
    }

}
