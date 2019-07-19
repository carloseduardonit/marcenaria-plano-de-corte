/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marcenaria.pessoa1;

/**
 * 16/06/2019
 *
 * @author Carlos Eduardo dos Santos Figueiredo
 */
public class Fornecedor extends Pessoa {

    private static int idFornecedor;
    private static String CNPJ, tipoPessoa, docum;
    private static final String TABELA = Fornecedor.class.getSimpleName();

    /**
     * Este metodo inserer informação na tabela Fornecedor utilizado um metodo
     * auxiliar da classe <b>Pessoa</b> no metodo <b>adicionarPessoa(String
     * Tabela, String logPessoa, String senPessoa, String conSenPessoa, String
     * tipoPessoa, String nomePessoa, String documPessoa)</b>
     *
     * @param logFornecedor Setar uma informação do tipo String da Tabela
     * Fornecedor no Login Fornecedor
     * @param senFornecedor Setar uma informação do tipo String da Tabela
     * Fornecedor no Senha Fornecedor
     * @param consenFornecedor Setar uma informação do tipo String da Tabela
     * Fornecedor na Confirmação da senha Fornecedor
     * @param tipoPessoa etar uma informação do tipo String da Tabela Fornecedor
     * no tipo de Fornecedor, sendo que so podera utilizar <b>PF</b> ou
     * <b>PJ</b>
     * @param nomeFornecedor Setar uma informação do tipo String da Tabela
     * Fornecedor no Nome Fornecedor
     * @param documFornecedor Setar uma informação do tipo String da Tabela
     * Fornecedor no documento do Fornecedor,sendo quanto o tipoPessoa
     * se<b>PF</b> so poderá anexa a infornação for de 11 digito, senão
     * <b>PJ</b> so poderá anexa a infornação for de 14 digito
     */
    public static void adicionarFornecedor(String logFornecedor, String senFornecedor, String consenFornecedor,
            String tipoPessoa, String nomeFornecedor, String documFornecedor) {
        Pessoa.adicionarPessoa(getTABELA(), logFornecedor, senFornecedor, consenFornecedor, tipoPessoa, nomeFornecedor,
                documFornecedor);
    }

    /**
     *
     */
    public static void criarFornecedor() {
        Pessoa.criarPessoa(getTABELA());
    }

    /**
     * Este metodo faz a exclução da informação na Tabela
     */
    public static void deletarFornecedor() {
        Pessoa.deletarPessoa(getTABELA());
    }

    /**
     * TA MONTANDO FALTA TESTA
     *
     * @param nlogFornecedor Setar uma informação do tipo String da Tabela
     * Fornecedor no novo Login Fornecedor
     * @param logFornecedor Setar uma informação do tipo String da Tabela
     * Fornecedor no Login Fornecedor
     * @param senFornecedor Setar uma informação do tipo String da Tabela
     * Fornecedor no Senha Fornecedor
     * @param consenFornecedor Setar uma informação do tipo String da Tabela
     * Fornecedor na Confirmação da senha Fornecedor
     * @param tipoPessoa etar uma informação do tipo String da Tabela Fornecedor
     * no tipo de Fornecedor, sendo que so podera utilizar <b>PF</b> ou
     * <b>PJ</b>
     * @param nomeFornecedor Setar uma informação do tipo String da Tabela
     * Fornecedor no Nome Fornecedor
     * @param documFornecedor Setar uma informação do tipo String da Tabela
     * Fornecedor no documento do Fornecedor,sendo quanto o tipoPessoa
     * se<b>PF</b> so poderá anexa a infornação for de 11 digito, senão
     * <b>PJ</b> so poderá anexa a infornação for de 14 digito
     */
    public static void editarFornecedor(String nlogFornecedor, String logFornecedor, String senFornecedor,
            String consenFornecedor, String tipoPessoa, String nomeFornecedor, String documFornecedor) {
        Pessoa.editarPessoa(getTABELA(), nlogFornecedor, logFornecedor, senFornecedor, consenFornecedor, tipoPessoa,
                nomeFornecedor, documFornecedor);
    }

    /**
     * TA MONTANDO FALTA TESTA
     *
     * @param logFornecedor Setar uma informação do tipo String da Tabela
     * Fornecedor no Login Fornecedor
     */
    public static void excluirFornecedor(String logFornecedor) {
        Pessoa.excluirPessoa(Fornecedor.getTABELA(), logFornecedor);
    }

    /**
     * TA MONTANDO FALTA TESTA
     *
     * @param logFornecedor Setar uma informação do tipo String da Tabela
     * Fornecedor no Login Fornecedor
     */
    public static void pesquisarFornecedor(String logFornecedor) {
        Pessoa.pesquisarPessoa(Fornecedor.getTABELA(), logFornecedor);
    }

    /**
     * @param logFornecedor Setar uma informação do tipo String da Tabela
     * Fornecedor no Login Fornecedor
     * @return Retornar o id da Tabela Fornecedor atraves do Login.
     */
    public static int obterIdFornecedor(String logFornecedor) {
        return Pessoa.obterIdPessoa(logFornecedor, Fornecedor.getTABELA());
    }

    // Sets e Gets
    /**
     * @return Retornar uma informação do tipo inteiro da Tabela Fornecedor no
     * ID Fornecedor
     */
    public static int getIdFornecedor() {
        return idFornecedor;
    }

    /**
     * Setar Informação de valor inteiro do ID do fornecedor
     *
     * @param idFornecedor Setar Informação de valor inteiro do ID do fornecedor
     */
    public static void setIdFornecedor(int idFornecedor) {
        Fornecedor.idFornecedor = idFornecedor;
    }

    /**
     * Este Metodo Retornar uma informação do tipo inteiro da Tabela Fornecedor
     * no Documento Fornecedor
     *
     * @return Retornar uma informação do tipo inteiro da Tabela Fornecedor no
     * Documento Fornecedor
     */
    public static String getCNPJ() {
        return CNPJ;
    }

    /**
     * Este Metodo Setar uma informação do tipo inteiro da Tabela Fornecedor no
     * Documento Fornecedor
     *
     * @param CNPJ Setar uma informação do tipo inteiro da Tabela Fornecedor no
     * Documento Fornecedor
     */
    public static void setCNPJ(String CNPJ) {
        Fornecedor.CNPJ = CNPJ;
    }

    /**
     * Este Metodo Retornar uma informação do tipo String da Tabela Fornecedor
     * no Tipo de Fornecedor
     *
     * @return Retornar uma informação do tipo String da Tabela Fornecedor no
     * Tipo de Fornecedor
     */
    public static String getTipoPessoa() {
        return tipoPessoa;
    }

    /**
     * Este Metodo Setar uma informação do tipo String da Tabela Fornecedor no
     * Tipo de Fornecedor
     *
     * @param tipoPessoa Setar uma informação do tipo String da Tabela
     * Fornecedor no Tipo de Fornecedor
     */
    public static void setTipoPessoa(String tipoPessoa) {
        Fornecedor.tipoPessoa = tipoPessoa;
    }

    /**
     *
     * @return Retornar uma informação do tipo String da Tabela Fornecedor com
     * nome da tabela
     */
    public static String getTABELA() {
        return TABELA;
    }

    /**
     * Este Metodo Retornar uma informação de valor String do documento do
     * Fornecedor.
     *
     * @return Este Metodo Retornar uma informação de valor String do documento
     * do Fornecedor.
     */
    public static String getDocum() {
        return docum;
    }

    /**
     * Este Metodo Setar uma informação de valor String do documento do
     * Fornecedor.
     *
     * @param docum Setar uma informação de valor String do documento do
     * Fornecedor.
     */
    public static void setDocum(String docum) {
        Fornecedor.docum = docum;
    }

}
