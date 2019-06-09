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
public class Peca {

    /**
     *
     */
    int[][][][] peca = new int[quantPeca][quantPeca][quantPeca][quantPeca];
    private static Double largura, comprimento, espessura, preco;
    private static int idPeca, quantPeca;
    private static final String TABELA = Peca.class.getSimpleName();
    private static String[] tipoMateria = new String[2];

    /**
     * @param args Informar um valor String para tabela de Material
     */
    public static void main(String[] args) {
        /* Scanner ler  = new Scanner(System.in);
        Boolean validaComprimento = false;
        do {
            setComprimento(0.0);
            if (getComprimento()<=0) {
                System.out.println("erro");
            }else{
                
                validaComprimento =true;
            }
        } while (!validaComprimento);*/
        criadaPeca();
        deletadaPeca();

    }

    /**
     * Este metodo utilizar um metodo da classe Material como Metodo auxiliar o
     * metodo criarMaterial(String Tabela).
     *
     */
    public static void criadaPeca() {
        Material.criarMaterial(TABELA);
    }

    /**
     * Este metodo utilizar um metodo da classe Material como Metodo auxiliar o
     * metodo deletarMaterial(String Tabela).
     *
     */
    public static void deletadaPeca() {
        Material.deletarMaterial(TABELA);
    }

    /**
     * Este metodo utilizar um metodo da classe Material como Metodo auxiliar o
     * metodo inserirMaterial((String Tabela, String tipoMaterial, int
     * quantidade, double comprimento, double largura, double espessura, double
     * preco).
     *
     */
    public static void inserirPeca() {
        setTipoMateria();
        Material.adicionarMaterial(getTABELA(), getTipoMateria(0), getQuantPeca(), getComprimento(), getLargura(), getEspessura(), getPreco());
    }

    /**
     * Este metodo utilizar um metodo da classe Material como Metodo auxiliar o
     * metodo editarMaterial(String Tabela, String tipoMaterial, int quantidade,
     * double comprimento, double largura, double espessura, double preco)
     *
     */
    public static void editarPeca() {
        setTipoMateria();
        Material.editarMaterial(getTABELA(), getTipoMateria(1), getQuantPeca(), getComprimento(), getLargura(), getEspessura(), getPreco());
    }

    /**
     * Este metodo utilizar um metodo da classe Material como Metodo auxiliar o
     * metodo excluirMaterial(String Tabela, String tipoMaterial, int
     * quantidade, double comprimento, double largura, double espessura, double
     * preco)
     */
    public static void excluirPeca() {
        Material.excluirMaterial(TABELA, TABELA, quantPeca, 0, 0, 0, 0);
    }

    /**
     *Este metodo utilizar um metodo da classe Material como Metodo auxiliar o
     * metodo
     */
    public static void pesquisarPeca() {

    }

    /**
     * soma as peça Se 109,5X79,5
     */
    public static void SomarPeca() {
        double somar;
        if (getLargura() == 0) {

        }
    }

    public static void QuantPeca() {

    }

    //Sets e Gets
    /**
     * @return Retornar um valor Double da largura.
     */
    public static Double getLargura() {
        return largura;
    }

    /**
     * @param largura Informar um valor Double da largura.
     */
    public static void setLargura(Double largura) {
        Peca.largura = largura;
    }

    /**
     * @return Retornar um valor Double do comprimento.
     */
    public static Double getComprimento() {
        return comprimento;
    }

    /**
     * @param comprimento Informar um valor Double do comprimento.
     */
    public static void setComprimento(Double comprimento) {
        Peca.comprimento = comprimento;
    }

    /**
     * @return Retornar um valor Double da Espessura.
     */
    public static Double getEspessura() {
        return espessura;
    }

    /**
     * @param espessura Informar um valor Double da Espessura.
     */
    public static void setEspessura(Double espessura) {
        Peca.espessura = espessura;
    }

    /**
     * @return Retornar um valor Double do Preço.
     */
    public static Double getPreco() {
        return preco;
    }

    /**
     * @param preco Informar um valor Double do Preço.
     */
    public static void setPreco(Double preco) {
        Peca.preco = preco;
    }

    /**
     * @return Retorna uma String com nome da tabela
     */
    public static String getTABELA() {
        return TABELA;
    }

    /**
     * @return Retornar um valor inteiro na quantidade de Peça
     */
    public static int getQuantPeca() {
        return quantPeca;
    }

    /**
     * @param quantPeca Informar um valor inteiro na quantidade de Peça
     */
    public static void setQuantPeca(int quantPeca) {
        Peca.quantPeca = quantPeca;
    }

    /**
     * @return Retornar um valor inteiro do id da Peça
     */
    public static int getIdPeca() {
        return idPeca;
    }

    /**
     * @param idPeca Informar um valor inteiro do id da Peça
     */
    public static void setIdPeca(int idPeca) {
        Peca.idPeca = idPeca;
    }

    /**
     * @return Retornar uma Array de String do Tipo de Materia
     */
    public static String[] getTipoMateria() {
        return tipoMateria;
    }

    /**
     * @param pos Informar um valor inteiro do index do Array e deve começa em
     * ZERO(0)
     * @return Retornar um valor String do Array do Tipo de Materia
     */
    public static String getTipoMateria(int pos) {
        return tipoMateria[pos];
    }

    /**
     * Setar um array de String sendo:\n0 iqual a Compensado\n1 iqual a MDF
     */
    public static void setTipoMateria() {
        Peca.tipoMateria[0] = "Compensado";
        Peca.tipoMateria[1] = "MDF";
    }

    /**
     * @param tipoMateria Informar um Array de String para tipo de Material
     */
    public static void setTipoMateria(String[] tipoMateria) {
        Peca.tipoMateria = tipoMateria;
    }

    /**
     * @param tipoMateria Informar um valor do tipo String para setar no campo
     * do Array
     * @param pos Informar um valor inteiro do index do Array e deve começa em
     * ZERO(0)
     */
    public static void setTipoMateria(String tipoMateria, int pos) {
        Peca.tipoMateria[pos] = tipoMateria;
    }

}
