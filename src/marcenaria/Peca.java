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
     * @param args
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
     * 
     */
    public static void criadaPeca() {
        Material.criarMaterial(TABELA);
    }

    /**
     * 
     */
    public static void deletadaPeca() {
        Material.deletarMaterial(TABELA);
    }

    /**
     * 
     */
    public static void inserirPeca() {
        setTipoMateria();
        Material.adicionarMaterial(getTABELA(), getTipoMateria(0), getQuantPeca(), getComprimento(), getLargura(), getEspessura(), getPreco());
    }

    /**
     * 
     */
    public static void editarPeca() {
        setTipoMateria();
        Material.editarMaterial(getTABELA(), getTipoMateria(1), getQuantPeca(), getComprimento(), getLargura(), getEspessura(), getPreco());
    }

    /**
     * 
     */
    public static void excluirPeca() {
        Material.excluirMaterial(TABELA, TABELA, quantPeca, 0, 0, 0, 0);
    }

    /**
     * 
     */
    public static void pesquisarPeca() {

    }
/**soma as peça
    Se 109,5X79,5*/
    public static void SomarPeca(){
        double somar;
        if(getLargura()==0){
            
        }                
    }
    public static void QuantPeca(){
        
    }
    
    //Sets e Gets
    /**
     * @return
     */
    public static Double getLargura() {
        return largura;
    }

    /**
     * @param largura
     */
    public static void setLargura(Double largura) {
        Peca.largura = largura;
    }

    /**
     * @return
     */
    public static Double getComprimento() {
        return comprimento;
    }

    /**
     * @param comprimento
     */
    public static void setComprimento(Double comprimento) {
        Peca.comprimento = comprimento;
    }

    /**
     * @return
     */
    public static Double getEspessura() {
        return espessura;
    }

    /**
     * @param espessura
     */
    public static void setEspessura(Double espessura) {
        Peca.espessura = espessura;
    }

    /**
     * @return
     */
    public static Double getPreco() {
        return preco;
    }

    /**
     * @param preco
     */
    public static void setPreco(Double preco) {
        Peca.preco = preco;
    }

    /**
     * @return
     */
    public static String getTABELA() {
        return TABELA;
    }

    /**
     * @return
     */
    public static int getQuantPeca() {
        return quantPeca;
    }

    /**
     * @param quantPeca
     */
    public static void setQuantPeca(int quantPeca) {
        Peca.quantPeca = quantPeca;
    }

    /**
     * @return
     */
    public static int getIdPeca() {
        return idPeca;
    }

    /**
     * @param idPeca
     */
    public static void setIdPeca(int idPeca) {
        Peca.idPeca = idPeca;
    }

    /**
     * @return
     */
    public static String[] getTipoMateria() {
        return tipoMateria;
    }

    /**
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
        Peca.tipoMateria[0] = "Compensado";
        Peca.tipoMateria[1] = "MDF";
    }

    /**
     * @param tipoMateria
     */
    public static void setTipoMateria(String[] tipoMateria) {
        Peca.tipoMateria = tipoMateria;
    }

    /**
     * @param tipoMateria
     * @param pos
     */
    public static void setTipoMateria(String tipoMateria, int pos) {
        Peca.tipoMateria[pos] = tipoMateria;
    }

}
