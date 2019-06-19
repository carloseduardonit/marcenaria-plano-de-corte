/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marcenaria.Pessoa;

/**
 *
 * @author Carlos
 */
public class Cliente extends Pessoa {

    private static final String TABELA = Cliente.class.getSimpleName();
    private static int cpf, idCliente;
    private static String tipoPessoa;

    /**
     * TA MONTANDO FALTA TESTA
     *
     * @param logCliente
     * @param senCliente
     * @param conSenCliente
     * @param tipoPessoa
     * @param nomeCliente
     * @param documCliente
     */
    public static void adicionarCliente(String logCliente, String senCliente, String conSenCliente, String tipoPessoa, String nomeCliente, String documCliente) {
        Pessoa.adicionarPessoa(getTABELA(), logCliente, senCliente, conSenCliente, tipoPessoa, nomeCliente, documCliente);
    }

    /**
     * TA MONTANDO FALTA TESTA
     */
    public static void criarCliente() {
        Pessoa.criarPessoa(getTABELA());
    }

    /**
     * TA MONTANDO FALTA TESTA
     */
    public static void deletarCliente() {
        Pessoa.deletarPessoa(getTABELA());
    }

    /**
     * TA MONTANDO FALTA TESTA
     *
     * @param nlogCliente
     * @param logCliente
     * @param senCliente
     * @param conSenCliente
     * @param tipoPessoa
     * @param nomeCliente
     * @param documCliente
     */
    public static void editarCliente(String nlogCliente, String logCliente, String senCliente, String conSenCliente, String tipoPessoa, String nomeCliente, String documCliente) {
        Pessoa.editarPessoa(getTABELA(), nlogCliente, logCliente, senCliente, conSenCliente, tipoPessoa, nomeCliente, documCliente);
    }

    /**
     * TA MONTANDO FALTA TESTA
     *
     * @param logCliente
     * @param senCliente
     * @param conSenCliente
     * @param tipoPessoa
     * @param nomeCliente
     * @param documCliente
     */
    public static void excluirCliente(String logCliente, String senCliente, String conSenCliente, String tipoPessoa, String nomeCliente, String documCliente) {
        Pessoa.excluirPessoa(getTABELA(), logCliente, senCliente, conSenCliente, tipoPessoa, nomeCliente, documCliente);
    }

    /**
     * TA MONTANDO FALTA TESTA
     *
     * @param logCliente
     * @param senCliente
     * @param conSenCliente
     * @param tipoPessoa
     * @param nomeCliente
     * @param documCliente
     */
    public static void pesquisarCliente(String logCliente, String senCliente, String conSenCliente, String tipoPessoa, String nomeCliente, String documCliente) {
        Pessoa.pesquisarPessoa(getTABELA(), logCliente, senCliente, conSenCliente, tipoPessoa, nomeCliente, documCliente);
    }

    /**
     *
     * @param logCliente
     * @return
     */
    public static int obterIdCliente(String logCliente) {
        return Pessoa.obterIdPessoa(logCliente, Cliente.getTABELA());
    }
// Sets e Gets

    /**
     *
     * @return
     */
    public static int getCpf() {
        return cpf;
    }

    /**
     *
     * @param cpf
     */
    public static void setCpf(int cpf) {
        Cliente.cpf = cpf;
    }

    /**
     *
     * @return
     */
    public static int getIdCliente() {
        return idCliente;
    }

    /**
     *
     * @param idCliente
     */
    public static void setIdCliente(int idCliente) {
        Cliente.idCliente = idCliente;
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
        Cliente.tipoPessoa = tipoPessoa;
    }

    /**
     *
     * @return
     */
    public static String getTABELA() {
        return TABELA;
    }

}
