/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marcenaria.Pessoa;

/**
 * 16/06/2019
 *
 * @author Carlos
 */
public class Fornecedor extends Pessoa {

    private static int idFornecedor;
    private static String CNPJ, tipoPessoa;
    private static final String TABELA = Fornecedor.class.getSimpleName();

    /**
     * TA MONTANDO FALTA TESTA
     *
     * @param logFornecedor
     * @param senFornecedor
     * @param consenFornecedor
     * @param tipoPessoa
     * @param nomeFornecedor
     * @param documFornecedor
     */
    public static void adicionarFornecedor(String logFornecedor, String senFornecedor, String consenFornecedor, String tipoPessoa, String nomeFornecedor, String documFornecedor) {
        Pessoa.adicionarPessoa(getTABELA(), logFornecedor, senFornecedor, consenFornecedor, tipoPessoa, nomeFornecedor, documFornecedor);
    }

    /**
     *
     */
    public static void criarFornecedor() {
        Pessoa.criarPessoa(getTABELA());
    }

    /**
     *
     */
    public static void deletarFornecedor() {
        Pessoa.deletarPessoa(getTABELA());
    }

    /**
     * TA MONTANDO FALTA TESTA
     *
     * @param nlogFornecedor
     * @param logFornecedor
     * @param senFornecedor
     * @param consenFornecedor
     * @param tipoPessoa
     * @param nomeFornecedor
     * @param documFornecedor
     */
    public static void editarFornecedor(String nlogFornecedor, String logFornecedor, String senFornecedor, String consenFornecedor, String tipoPessoa, String nomeFornecedor, String documFornecedor) {
        Pessoa.editarPessoa(getTABELA(), nlogFornecedor, logFornecedor, senFornecedor, consenFornecedor, tipoPessoa, nomeFornecedor, documFornecedor);
    }

    /**
     * TA MONTANDO FALTA TESTA
     *
     * @param logFornecedor
     * @param senFornecedor
     * @param consenFornecedor
     * @param tipoPessoa
     * @param nomeFornecedor
     * @param documFornecedor
     */
    public static void excluirFornecedor(String logFornecedor, String senFornecedor, String consenFornecedor, String tipoPessoa, String nomeFornecedor, String documFornecedor) {
        Pessoa.excluirPessoa(getTABELA(), logFornecedor, senFornecedor, consenFornecedor, tipoPessoa, nomeFornecedor, documFornecedor);
    }

    /**
     * TA MONTANDO FALTA TESTA
     *
     * @param logFornecedor
     * @param senFornecedor
     * @param consenFornecedor
     * @param tipoPessoa
     * @param nomeFornecedor
     * @param documFornecedor
     */
    public static void pesquisarFornecedor(String logFornecedor, String senFornecedor, String consenFornecedor, String tipoPessoa, String nomeFornecedor, String documFornecedor) {
        Pessoa.pesquisarPessoa(getTABELA(), logFornecedor, senFornecedor, consenFornecedor, tipoPessoa, nomeFornecedor, documFornecedor);
    }

    /**
     * @param logFornecedor
     * @return
     */
    public static int obterIdFornecedor(String logFornecedor) {
        return Pessoa.obterIdPessoa(logFornecedor, Fornecedor.getTABELA());
    }

    //Sets e Gets
    /**
     * @return
     */
    public static int getIdFornecedor() {
        return idFornecedor;
    }

    /**
     *
     * @param idFornecedor
     */
    public static void setIdFornecedor(int idFornecedor) {
        Fornecedor.idFornecedor = idFornecedor;
    }

    /**
     *
     * @return
     */
    public static String getCNPJ() {
        return CNPJ;
    }

    /**
     *
     * @param CNPJ
     */
    public static void setCNPJ(String CNPJ) {
        Fornecedor.CNPJ = CNPJ;
    }

    /**
     * @return
     */
    public static String getTipoPessoa() {
        return tipoPessoa;
    }

    /**
     * @param tipoPessoa
     */
    public static void setTipoPessoa(String tipoPessoa) {
        Fornecedor.tipoPessoa = tipoPessoa;
    }

    /**
     *
     * @return
     */
    public static String getTABELA() {
        return TABELA;
    }

}
