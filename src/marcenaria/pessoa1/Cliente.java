/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marcenaria.pessoa1;

/**
 *
 * @author Carlos
 */
public class Cliente extends Pessoa {

    private static final String TABELA = Cliente.class.getSimpleName();
    private static int cpf, idCliente;
    private static String tipoPessoa,docum;

    /**
     * Este metodo inserer informação na tabela Cliente utilizado um metodo
     * auxiliar da classe <b>Pessoa</b> no metodo <b>adicionarPessoa(String
     * Tabela, String logPessoa, String senPessoa, String conSenPessoa, String
     * tipoPessoa, String nomePessoa, String documPessoa)</b>
     *
     * @param logCliente Setar uma informação do tipo String da Tabela Cliente
     * no Login Cliente
     * @param senCliente Setar uma informação do tipo String da Tabela Cliente
     * no Senha Cliente
     * @param conSenCliente Setar uma informação do tipo String da Tabela
     * Cliente na Confirmação da senha Cliente
     * @param tipoPessoa Setar uma informação do tipo String da Tabela Cliente
     * no tipo de Cliente, sendo que so podera utilizar <b>PF</b> ou <b>PJ</b>
     * @param nomeCliente Setar uma informação do tipo String da Tabela Cliente
     * no Nome Cliente
     * @param documCliente Setar uma informação do tipo String da Tabela Cliente
     * no documento do Cliente,sendo quanto o tipoPessoa se<b>PF</b> so poderá anexa a
     * infornação for de 11 digito, senão <b>PJ</b> so poderá anexa a infornação
     * for de 14 digito
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
     * @param nlogCliente Setar uma informação do tipo String da Tabela Cliente
     * no  novo Login Cliente
     * @param logCliente Setar uma informação do tipo String da Tabela Cliente
     * no Login Cliente
     * @param senCliente Setar uma informação do tipo String da Tabela Cliente
     * no Senha Cliente
     * @param conSenCliente Setar uma informação do tipo String da Tabela
     * Cliente na Confirmação da senha Cliente
     * @param tipoPessoa Setar uma informação do tipo String da Tabela Cliente
     * no tipo de Cliente, sendo que so podera utilizar <b>PF</b> ou <b>PJ</b>
     * @param nomeCliente Setar uma informação do tipo String da Tabela Cliente
     * no Nome Cliente
     * @param documCliente Setar uma informação do tipo String da Tabela Cliente
     * no documento do Cliente,sendo quanto o tipoPessoa se<b>PF</b> so poderá anexa a
     * infornação for de 11 digito, senão <b>PJ</b> so poderá anexa a infornação
     * for de 14 digito
     */
    public static void editarCliente(String nlogCliente, String logCliente, String senCliente, String conSenCliente, String tipoPessoa, String nomeCliente, String documCliente) {
        Pessoa.editarPessoa(getTABELA(), nlogCliente, logCliente, senCliente, conSenCliente, tipoPessoa, nomeCliente, documCliente);
    }

    /**
     * TA MONTANDO FALTA TESTA
     *
     * @param logCliente Setar uma informação do tipo String da Tabela Cliente
     * no Login Cliente
     * @param senCliente Setar uma informação do tipo String da Tabela Cliente
     * no Senha Cliente
     * @param conSenCliente Setar uma informação do tipo String da Tabela
     * Cliente na Confirmação da senha Cliente
     * @param tipoPessoa Setar uma informação do tipo String da Tabela Cliente
     * no tipo de Cliente, sendo que so podera utilizar <b>PF</b> ou <b>PJ</b>
     * @param nomeCliente Setar uma informação do tipo String da Tabela Cliente
     * no Nome Cliente
     * @param documCliente Setar uma informação do tipo String da Tabela Cliente
     * no documento do Cliente,sendo quanto o tipoPessoa se<b>PF</b> so poderá anexa a
     * infornação for de 11 digito, senão <b>PJ</b> so poderá anexa a infornação
     * for de 14 digito
     */
    public static void excluirCliente(String logCliente) {
        Pessoa.excluirPessoa(Cliente.getTABELA(), logCliente);
    }

    /**
     * TA MONTANDO FALTA TESTA
     *
     * @param logCliente Setar uma informação do tipo String da Tabela Cliente
     * no Login Cliente
     * @param senCliente Setar uma informação do tipo String da Tabela Cliente
     * no Senha Cliente
     * @param conSenCliente Setar uma informação do tipo String da Tabela
     * Cliente na Confirmação da senha Cliente
     * @param tipoPessoa Setar uma informação do tipo String da Tabela Cliente
     * no tipo de Cliente, sendo que so podera utilizar <b>PF</b> ou <b>PJ</b>
     * @param nomeCliente Setar uma informação do tipo String da Tabela Cliente
     * no Nome Cliente
     * @param documCliente Setar uma informação do tipo String da Tabela Cliente
     * no documento do Cliente,sendo quanto o tipoPessoa se<b>PF</b> so poderá anexa a
     * infornação for de 11 digito, senão <b>PJ</b> so poderá anexa a infornação
     * for de 14 digito
     */
    public static void pesquisarCliente(String logCliente) {
        Pessoa.pesquisarPessoa(getTABELA(), logCliente);
    }

    /**
     *
     * @param logCliente Setar uma informação do tipo String da Tabela Cliente
     * no Login Cliente
     * @return Retornar o id da Tabela Cliente atraves do Login.
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
     * @return Retornar uma informação do tipo inteiro da Tabela Cliente no ID
     * Cliente
     */
    public static int getIdCliente() {
        return idCliente;
    }

    /**
     *
     * @param idCliente Setar uma informação do tipo inteiro da Tabela Cliente
     * no ID Cliente
     */
    public static void setIdCliente(int idCliente) {
        Cliente.idCliente = idCliente;
    }

    /**
     * @return Retornar uma informação do tipo String da Tabela Cliente no tipo
     * de pessoa
     */
    public static String getTipoPessoa() {
        return tipoPessoa;
    }

    /**
     * @param tipoPessoa setar uma informação do tipo String da Tabela Cliente
     * no tipo de pessoa
     */
    public static void setTipoPessoa(String tipoPessoa) {
        Cliente.tipoPessoa = tipoPessoa;
    }

    /**
     *
     * @return um valor de String da Tabela Cliente
     */
    public static String getTABELA() {
        return TABELA;
    }

    public static String getDocum() {
        return docum;
    }

    public static void setDocum(String docum) {
        Cliente.docum = docum;
    }

}
