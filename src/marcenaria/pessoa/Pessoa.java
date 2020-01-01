package marcenaria.pessoa;

import java.awt.HeadlessException;
import java.sql.*;
import javax.swing.JOptionPane;
import marcenaria.Const.Messagem;
import marcenaria.dado.*;

/**
 * 16/06/2019
 *
 * @author Carlos Eduardo dos Santos Figueiredo
 */
public class Pessoa {

    private static int idpessoa;
    private static final String TABELA = Pessoa.class.getSimpleName();
    private static String login, senha, confSenha, tipoPessoa, nome;
    private static final String PERSONS = "persons";
    private static Connection conexao;
    private static PreparedStatement pst;
    private static ResultSet rs;
    private static ResultSetMetaData rsmd;
    private static Statement stmt;
    public static String banco = PERSONS;

    public static void main(String[] args) {
        pessoaZero();
    }

    /**
     * Este Metodo faz a conexao com o banco de dado
     */
    public static void pessoa() {
        conexao = ModuloConector.getConecction(PERSONS);
    }

    /**
     * OK este metodo faz a 
     * => Criação do Banco de dados Pesons
     * => Criação das tabelas Pessoa, Cliente, Fornecedor, 
     * => Inserção dos usuarios zero na tabela  Pessoa,
     * => Inserção dos usuarios zero na tabela Cliente,
     * => Inserção dos usuarios zero na tabela Fornecedor,
     * atravez do metodo auxiliar
     */
    public static void pessoaZero() {
        DataBase.importarBackupdataBaseSQL("C:\\Users\\Carlos\\Documents\\NetBeansProjects\\Marcenaria\\src\\marcenaria\\pessoa\\Persona.sql", "Persons");
    }

    /**
     * ok Este Metodo faz a criação da Tabela Pessoa e de Tabela Filhas
     *
     */
    public static void criarPessoa() {
        String sql = "create table if not exists " + Pessoa.getTABELA() + "(id" + Pessoa.getTABELA() + " int primary key auto_increment, "
                + "login varchar(15) not  null unique, " + "senha varchar(9) not null, "
                + "tipoPessoa varchar(2) not null, " + "nome varchar(100) not null)";
        Table.criarTabela(sql, Pessoa.getTABELA());
    }

    /**
     * ok Este Metodo deleta a tabela desejada
     *
     */
    public static void deletarPessoa() {
        if (!Table.VerificarNaoExistirTabela(Fornecedor.getTABELA())) {
            Fornecedor.deletarFornecedor();
        }
        if (!Table.VerificarNaoExistirTabela(Cliente.getTABELA())) {
            Cliente.deletarCliente();
        }
        Table.deletarTabela(Pessoa.getTABELA());
    }

    /**
     * Fazendo as correções TESTA Este metodo inserer informação na tabela
     * Pessoa
     *
     * @param logPessoa Setar uma informação do tipo String da Tabela Pessoa no
     * novo Login Pessoa
     * @param senPessoa Setar uma informação do tipo String da Tabela Pessoa no
     * Senha Pessoa
     * @param conSenPessoa Setar uma informação do tipo String da Tabela Pessoa
     * na Confirmação da senha Pessoa
     * @param tipoPessoa Setar uma informação do tipo String da Tabela Pessoa no
     * tipo de Pessoa, sendo que so podera utilizar <b>PF</b> ou
     * <b>PJ</b>
     * @param nomePessoa Setar uma informação do tipo String da Tabela Pessoa no
     * Nome Pessoa
     */
    public static void adicionarPessoa(String logPessoa, String senPessoa, String conSenPessoa, String tipoPessoa, String nomePessoa) {
        Pessoa.adicionarPessoa(logPessoa, senPessoa, conSenPessoa, tipoPessoa, nomePessoa, false);
    }

    /**
     * Fazendo as correções TESTA Este metodo inserer informação na tabela
     * Pessoa
     *
     * @param logPessoa Setar uma informação do tipo String da Tabela Pessoa no
     * novo Login Pessoa
     * @param senPessoa Setar uma informação do tipo String da Tabela Pessoa no
     * Senha Pessoa
     * @param conSenPessoa Setar uma informação do tipo String da Tabela Pessoa
     * na Confirmação da senha Pessoa
     * @param tipoPessoa Setar uma informação do tipo String da Tabela Pessoa no
     * tipo de Pessoa, sendo que so podera utilizar <b>PF</b> ou
     * <b>PJ</b>
     * @param mensagem
     * @param nomePessoa Setar uma informação do tipo String da Tabela Pessoa no
     * Nome Pessoa
     */
    public static void adicionarPessoa(String logPessoa, String senPessoa, String conSenPessoa, String tipoPessoa, String nomePessoa, boolean mensagem) {
        if (!existeraPessoa(logPessoa)) {
            try {
                pessoa();
                String sql = "insert into " + Pessoa.getTABELA() + " (login, senha, tipoPessoa, nome) values (?,?,?,?)";
                pst = conexao.prepareStatement(sql);
                pst.setString(1, logPessoa);
                pst.setString(2, senPessoa);
                pst.setString(3, tipoPessoa);
                pst.setString(4, nomePessoa);
                int adicionado = pst.executeUpdate();
                if (adicionado > 0 && mensagem) {
                    Messagem.chamarTela(Messagem.ADICIONADO(Pessoa.exibirPessoatoString(logPessoa)));
                }
            } catch (SQLException e) {
                Messagem.chamarTela(Pessoa.getTABELA() + " Adicionar: " + e);
            } finally {
                ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
            }
        } else {
            if (mensagem) {
                Messagem.chamarTela(logPessoa + "e uma pessoa cadastra no sistema !!");
            }
        }
    }

    /**
     * Fazendo as correções Este Metodo editar a informaçao de determinado
     * Tabela da conforme o paramentro da Tabela, anexado nlogPessoa no login do
     * usario
     *
     *
     * @param nlogPessoa Setar uma informação do tipo String da Tabela Pessoa no
     * novo Login Pessoa
     * @param logPessoa Setar uma informação do tipo String da Tabela Pessoa no
     * Login Pessoa
     * @param senPessoa Setar uma informação do tipo String da Tabela Pessoa no
     * Senha Pessoa
     * @param conSenPessoa Setar uma informação do tipo String da Tabela Pessoa
     * na Confirmação da senha Pessoa
     * @param tipoPessoa Setar uma informação do tipo String da Tabela Pessoa no
     * tipo de Pessoa, sendo que so podera utilizar <b>PF</b> ou
     * <b>PJ</b>
     * @param nomePessoa Setar uma informação do tipo String da Tabela Pessoa no
     * Nome Pessoa
     */
    public static void editarPessoa(String logPessoa, String senPessoa, String conSenPessoa, String tipoPessoa, String nomePessoa) {
        Pessoa.editarPessoa(logPessoa, senPessoa, conSenPessoa, tipoPessoa, nomePessoa, false);
    }

    /**
     * Fazendo as correções Este Metodo editar a informaçao de determinado
     * Tabela da conforme o paramentro da Tabela, anexado nlogPessoa no login do
     * usario
     *
     *
     * @param nlogPessoa Setar uma informação do tipo String da Tabela Pessoa no
     * novo Login Pessoa
     * @param logPessoa Setar uma informação do tipo String da Tabela Pessoa no
     * Login Pessoa
     * @param senPessoa Setar uma informação do tipo String da Tabela Pessoa no
     * Senha Pessoa
     * @param conSenPessoa Setar uma informação do tipo String da Tabela Pessoa
     * na Confirmação da senha Pessoa
     * @param tipoPessoa Setar uma informação do tipo String da Tabela Pessoa no
     * tipo de Pessoa, sendo que so podera utilizar <b>PF</b> ou
     * <b>PJ</b>
     * @param nomePessoa Setar uma informação do tipo String da Tabela Pessoa no
     * Nome Pessoa
     * @param mensagem
     *
     */
    public static void editarPessoa(String logPessoa, String senPessoa, String conSenPessoa, String tipoPessoa, String nomePessoa, boolean mensagem) {
        if (existeraPessoa(logPessoa)) {
            try {
                Pessoa.setIdpessoa(Pessoa.obterIdPessoa(logPessoa));
                String sql = "update " + Pessoa.getTABELA().toLowerCase() + " set senha = ?, tipoPessoa = ?, nome = ? where id" + Pessoa.getTABELA() + " = ?", mens = Pessoa.exibirPessoatoString(logPessoa), mens1;
                pessoa();
                pst = conexao.prepareStatement(sql);
                pst.setString(1, senPessoa);
                pst.setString(2, tipoPessoa);
                pst.setString(3, nomePessoa);
                pst.setInt(4, Pessoa.getIdpessoa());
                int editada = pst.executeUpdate();
                if (editada > 0 && mensagem) {
                    mens1 = Pessoa.exibirPessoatoString(logPessoa);
                    Messagem.chamarTela(Messagem.EDITADO("Antigo " + mens + "\n Novo " + mens1));
                }
            } catch (SQLException e) {
                Messagem.chamarTela(Pessoa.getTABELA() + " Editar: " + e);
            } finally {
                ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
            }
        } else {
            if (mensagem) {
                Messagem.chamarTela(Pessoa.getTABELA() + " Não existe !!!");
            }
        }
    }

    /**
     * Fazendo as correções FAZER Este metodo faz a exclução da informação na
     * Tabela
     *
     * @param logPessoa Setar uma informação do tipo String da Tabela Pessoa no
     * novo Login Pessoa
     */
    public static void excluirPessoa(String logPessoa) {
        Pessoa.excluirPessoa(logPessoa, true);
    }

    /**
     * Fazendo as correções FAZER Este metodo faz a exclução da informação na
     * Tabela
     *
     * @param logPessoa Setar uma informação do tipo String da Tabela Pessoa no
     * novo Login Pessoa
     * @param mensagem
     */
    public static void excluirPessoa(String logPessoa, boolean mensagem) {
        if (existeraPessoa(logPessoa)) {
            if (Cliente.existeraCliente(logPessoa)) {
                Cliente.excluirCliente(logPessoa);
            }
            if (Fornecedor.existeroFornecedor(logPessoa)) {
                Fornecedor.excluirFornecedor(logPessoa);
            }
            try {
                Pessoa.setIdpessoa(Pessoa.obterIdPessoa(logPessoa));
                String sql = "delete from " + Pessoa.getTABELA() + " where id" + Pessoa.getTABELA() + " = ? ", s = Pessoa.exibirPessoatoString(logPessoa);

                int excluir = JOptionPane.showConfirmDialog(null, s, Pessoa.getTABELA(), JOptionPane.OK_CANCEL_OPTION);
                if (excluir == JOptionPane.OK_OPTION) {
                    pessoa();
                    pst = conexao.prepareStatement(sql);
                    pst.setInt(1, Pessoa.getIdpessoa());
                    int excluido = pst.executeUpdate();
                    System.out.println("" + excluido);
                    if (excluido > 0 && mensagem) {
                        Messagem.chamarTela(Messagem.EXCLUIDO(s));
                    }
                }
            } catch (HeadlessException | SQLException e) {
                Messagem.chamarTela(Pessoa.getTABELA() + " Excluir: " + e);
            } finally {
                ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);

            }
        } else {
            if (mensagem) {
                Messagem.chamarTela(Pessoa.getTABELA() + " Não existe !!!");
            }
        }
    }

    /**
     * ok Este Metodo Exibir uma tela como a informação do banco de dados
     * obtendo o resultado mediante a uma pesquisa na Tabela conforme a
     * informação obtida no parametro <b>Tabela</b> e do parametro
     * <b>logPessoa</b>. Apos verificar qual tabela sera afetada
     *
     * @param logPessoa Setar uma informação do tipo String da Tabela Pessoa no
     * Login Pessoa
     */
    public static void exibirPessoa(String logPessoa) {
        Messagem.chamarTela(Pessoa.exibirPessoatoString(logPessoa) + "\n");
    }

    /**
     * OK Este Metodo Exibir uma tela como a informação do banco de dados
     * obtendo o resultado mediante a uma pesquisa na Tabela conforme a
     * informação obtida no parametro <b>Tabela</b> e do parametro
     * <b>logPessoa</b>. Apos verificar qual tabela sera afetada
     *
     * @param Tabela Setar uma informação do tipo String no nome da Tabela
     * @param logPessoa Setar uma informação do tipo String da Tabela Pessoa no
     * Login Pessoa
     * @return Retornar umma informação de valor String
     */
    static String exibirPessoatoString(String logPessoa) {
        String sql = "";
        Pessoa.pesquisarPessoa(logPessoa);
        if (Pessoa.getIdpessoa() == 0 && Pessoa.getLogin().isEmpty() && Pessoa.getNome().isEmpty() && Pessoa.getSenha().isEmpty()) {
            sql = "pessoa Não  cadastrada!!!";
        } else {
            sql = "Do Cadastro Pessoa\n";
            if (Pessoa.getIdpessoa() > 0) {
                sql += "\nId da Pessoa: " + Pessoa.getIdpessoa();
            }
            if (!Pessoa.getLogin().isEmpty()) {
                sql += "\nLogin da Pessoa: " + Pessoa.getLogin();
            }
            if (!Pessoa.getNome().isEmpty()) {
                sql += "\nNome da Pessoa: " + Pessoa.getNome();
            }
            if (!Pessoa.getSenha().isEmpty()) {
                sql += "\nSenha da Pessoa: " + Pessoa.getSenha();
            }
            if (!Pessoa.getTipoPessoa().isEmpty()) {
                sql += "\nTipo de Pessoa: " + Pessoa.getTipoPessoa();
            }
        }
        return sql;
    }

    /**
     * ok Este metodo Pesquisa na tabela Pessoa do banco de dados
     *
     * @param logPessoa Setar uma informação do tipo String da Tabela Pessoa no
     * Login Pessoa
     *
     */
    static void pesquisarPessoa(String logPessoa) {
        try {
            Pessoa.setIdpessoa(Pessoa.obterIdPessoa(logPessoa));
            String sql = "select P.login, P.senha, P.tipoPessoa, P.nome from " + Pessoa.getTABELA() + " as P where P.id" + Pessoa.getTABELA() + " = ?";
            if (!logPessoa.isEmpty()) {
                pessoa();
                pst = conexao.prepareStatement(sql);
                pst.setInt(1, Pessoa.getIdpessoa());
                rs = pst.executeQuery();
                if (rs.next()) {
                    Pessoa.setLogin(rs.getString(1));
                    Pessoa.setSenha(rs.getString(2));
                    Pessoa.setTipoPessoa(rs.getString(3));
                    Pessoa.setNome(rs.getString(4));
                }
            } else {
                Messagem.chamarTela(Messagem.VAZIO("login "));
            }
        } catch (SQLException e) {
            Messagem.chamarTela(Pessoa.getTABELA() + " Pesquisar: " + e);
        } finally {
            ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
        }
    }

    /**
     * ok Este metodo pesquisa na Tabela atraves informação do parametro
     * <b>Tabela</b> e do parametro
     * <b>logPessoa</b> retornando uma informação do do banco de dado tipo
     * inteiro do ID da pessoa
     *
     * @param logPessoa Setar uma informação do tipo String da Tabela Pessoa no
     * Login Pessoa
     * @param Tabela Setar uma informação do tipo String no nome da Tabela
     * @return Retornar uma informação do banco de dado do tipo inteiro do ID da
     * pessoa
     */
    public static int obterIdPessoa(String Tabela, String logPessoa) {
        int id = 0;
        try {

            String sql = "";
            if (Tabela.equalsIgnoreCase(Pessoa.getTABELA())) {
                sql = "select id" + Pessoa.getTABELA() + " from " + Pessoa.getTABELA() + " where login = ?";
            } else if (Tabela.equalsIgnoreCase(Cliente.getTABELA())) {
                sql = "select id" + Pessoa.getTABELA() + " from " + Cliente.getTABELA() + " where login = ?";
            } else if (Tabela.equalsIgnoreCase(Fornecedor.getTABELA())) {
                sql = "select id" + Pessoa.getTABELA() + " from " + Fornecedor.getTABELA() + " where login = ? ";
            } else {
                Messagem.chamarTela("Problema com tabela");
                logPessoa = "";
            }
            if (!logPessoa.isEmpty()) {
                pessoa();
                pst = conexao.prepareStatement(sql);
                pst.setString(1, logPessoa);
                rs = pst.executeQuery();
                if (rs.next()) {
                    id = rs.getInt(1);
                }
                return id;
            }
        } catch (SQLException e) {
            Messagem.chamarTela(Tabela + " Obter id Pessoa: " + e);
        } finally {
            ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
        }
        return id;
    }

    /**
     * ok Este metodo pesquisa na Tabela pessoa atraves no logim Pessoa
     * Retornando uma informação do do banco de dado tipo inteiro do ID da
     * pessoa
     *
     * @param logPessoa Setar uma informação do tipo String da Tabela Pessoa no
     * Login Pessoa
     * @return Retornar uma informação do do banco de dado tipo inteiro do ID da
     * pessoa
     */
    public static int obterIdPessoa(String logPessoa) {
        return obterIdPessoa(Pessoa.getTABELA(), logPessoa);
    }

    // Incio dos metodos de controle
    /**
     *
     * @return
     */
    public static String[] obterlogPessoa() {
        String sql = "select login from " + Pessoa.getTABELA();
        return obterlogPessoa(Pessoa.getTABELA(), sql);
    }

    /**
     *
     * @param Tabela
     * @param sql
     * @return
     */
    public static String[] obterlogPessoa(String Tabela, String sql) {
        int quantidadePessoa = Table.quantLinha(TABELA, sql);
        String[] login = new String[quantidadePessoa];
        try {
            pessoa();
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            for (int i = 0; i < quantidadePessoa; i++) {
                if (rs.next()) {
                    login[i] = rs.getString(1);
                }
            }
            return login;
        } catch (SQLException e) {
            Messagem.chamarTela(Tabela + " obterlogPessoa: " + e);
        }
        return login;

    }

    /**
     * ok Este Metodo Retornar boolean após Verifica o documento se tem a
     * quantidade de digito referente ao tipo de pessoa.
     *
     * @param docPessoa Setar uma informação de valor String do documento da
     * Pessoa.
     * @param TipoPessoa Setar uma informação de valor String do Tipo de Pessoa.
     * @return Retornar Uma informação de valor boolean da verificação de
     * Documento mediante:
     * <ul>
     * <li> <b>Valor True</b></li>
     * <ol>
     * <li>docPessoa for iqual a 11 digito E TipoPessoa for iqual "PF"</li>
     * <li>ou docPessoa for iqual a 14 digito E TipoPessoa for iqual "PJ"</li>
     * </ol>
     * <li><b>Valor False</b></li>
     * <ol>
     * <li></li>
     * </ol>
     * </ul>
     */
    public static Boolean VerificaDocumento(String docPessoa, String TipoPessoa) {
        if ((docPessoa.length() == 11 && TipoPessoa.equalsIgnoreCase("pf"))
                || (docPessoa.length() == 14 && TipoPessoa.equalsIgnoreCase("pj"))) {
            return true;
        }
        return false;
    }

    /**
     * ok Este Metodo Retornar String após Verifica o documento se tem a
     * quantidade de digito referente ao tipo de pessoa.
     *
     * @param docPessoa Setar uma informação de valor String do documento da
     * Pessoa.
     * @param TipoPessoa Setar uma informação de valor String do Tipo de Pessoa.
     * @return Retornar uma informçaõ de valor String do erro do documento
     * <ul>
     * <li> <b>Valor True</b></li>
     * <ol>
     * <li>docPessoa for iqual a 11 digito E TipoPessoa for iqual "PF"</li>
     * <li>ou docPessoa for iqual a 14 digito E TipoPessoa for iqual "PJ"</li>
     * </ol>
     * <li><b>Valor False</b></li>
     * <ol>
     * <li></li>
     * </ol>
     * </ul>
     */
    public static String txtVerificaDocumento(String docPessoa, String TipoPessoa) {
        String Mess = "";
        if (docPessoa.length() < 11 && tipoPessoa.equalsIgnoreCase("pf")) {
            Mess = "CPF falta " + (11 - docPessoa.length()) + "digitos";
        } else if (docPessoa.length() > 11 && TipoPessoa.equalsIgnoreCase("pf")) {
            Mess = "CPF tem mais " + (docPessoa.length() - 11) + "digitos";
        } else if (docPessoa.length() < 14 && TipoPessoa.equalsIgnoreCase("pj")) {
            Mess = "CNPJ falta " + (14 - docPessoa.length()) + "digitos";
        } else if (docPessoa.length() > 14 && TipoPessoa.equalsIgnoreCase("pj")) {
            Mess = "CNPJ tem mais " + (docPessoa.length() - 14) + "digitos";
        }
        return Mess;
    }

    /**
     * ok Este metodo verificar ser Todos paramentros estão vazio e que tiver
     * ser anexado no array interno, retornado assim um Array de String dos
     * Campos Vazios
     *
     * @param logPessoa Setar uma informação do tipo String da Tabela Pessoa no
     * Login Pessoa
     * @param senPessoa Setar uma informação do tipo String da Tabela Pessoa no
     * Senha Pessoa
     * @param conSenPessoa Setar uma informação do tipo String da Tabela Pessoa
     * na Confirmação da senha Pessoa
     * @param tipoPessoa Setar uma informação do tipo String da Tabela Pessoa no
     * tipo de Pessoa, sendo que so podera utilizar <b>PF</b> ou
     * <b>PJ</b>
     * @param nomePessoa Setar uma informação do tipo String da Tabela Pessoa no
     * Nome Pessoa
     * @param documPessoa Setar uma informação do tipo String da Tabela Pessoa
     * no documento do Pessoa,sendo quanto o tipoPessoa se<b>PF</b>
     * so poderá anexa a infornação for de 11 digito, senão
     * <b>PJ</b> so poderá anexa a infornação for de 14 digito
     * @return Retornar um array de informação de String dos Campos vazios
     */
    public static String[] CampoVazio(String logPessoa, String senPessoa, String conSenPessoa,
            String tipoPessoa, String nomePessoa, String documPessoa) {
        String[] campo = new String[7];
        boolean lp = false, sp = false, csp = false, tp = false, np = false, dp = false;
        for (int i = 0; i < campo.length;) {
            if (logPessoa.isEmpty()
                    && !lp) {
                lp = true;
                campo[i] = "login";
                i++;
            } else if (senPessoa.isEmpty() && !sp) {
                sp = true;
                campo[i] = "Senha";
                i++;
            } else if (conSenPessoa.isEmpty() && !csp) {
                csp = true;
                campo[i] = "Confimação de Senha";
                i++;
            } else if (tipoPessoa.isEmpty() && !tp) {
                tp = true;
                campo[i] = "Tipo de Pessoa";
                i++;
            } else if (nomePessoa.isEmpty() && !np) {
                if (tipoPessoa.equalsIgnoreCase("pf")) {
                    campo[i] = "Nome completo";
                    i++;
                } else if (tipoPessoa.equalsIgnoreCase("pj")) {
                    campo[i] = "Razao social";
                    i++;
                } else {
                    campo[i] = "Nome";
                    i++;
                }
                np = true;
            } else if (documPessoa.isEmpty() && !dp) {
                if (tipoPessoa.equalsIgnoreCase("pf")) {
                    campo[i] = "CPF";
                    i++;
                } else if (tipoPessoa.equalsIgnoreCase("pj")) {
                    campo[i] = "CNPJ";
                    i++;
                } else {
                    campo[i] = "CPF ou CNPJ";
                    i++;
                }
                dp = true;
            } else {
                i++;
            }
        }
        return campo;
    }

    /**
     * ok Este Metodo verifica os parametros senPessoa e conSenPessoa sao iquais
     * e a quantidade de caracteres retornado uma String
     *
     * @param senPessoa Setar uma informação do tipo String da Tabela Pessoa no
     * Senha Pessoa
     * @param conSenPessoa Setar uma informação do tipo String da Tabela Pessoa
     * na Confirmação da senha Pessoa
     * @param quant Setar uma informação do tipo inteiro para informar o numero
     * de Cartecter
     * @return Retorna Uma informação no valor de String dos campos diferentes
     * ou com caracter menor a <b>quant</b>
     */
    public static String CampoDiferente(String senPessoa, String conSenPessoa, int quant) {
        String s = "";
        if (!senPessoa.equalsIgnoreCase(conSenPessoa)) {
            s += "Senha diferente da confimação \n";
        }
        if (senPessoa.length() < quant) {
            s += "Senha menor que " + quant + " !!!\n";
        }
        if (conSenPessoa.length() < quant) {
            s += "Confimação menor que " + quant + " !!!\n";
        }
        return s;
    }

    /**
     * ok Este Metodo verifica os parametros senPessoa e conSenPessoa sao iquais
     * e a quantidade de caracteres retornado um valor Boolean
     *
     * @param senPessoa Setar uma informação do tipo String da Tabela Pessoa no
     * Senha Pessoa
     * @param conSenPessoa Setar uma informação do tipo String da Tabela Pessoa
     * na Confirmação da senha Pessoa
     * @param quant Setar uma informação do tipo inteiro para informar o numero
     * de Cartecter
     * @return Retorna Uma informação no valor de boolean dos campos diferentes
     * ou com caracter maior ou iqual a <b>quant</b>
     * <p>
     * se sim o valor será TRUE
     * </p>
     * <p>
     * se não o valor será FALSE
     * </p>
     */
    public static Boolean campoDiferente(String senPessoa, String conSenPessoa, int quant) {
        if (senPessoa.equalsIgnoreCase(conSenPessoa) && senPessoa.length() >= quant && conSenPessoa.length() >= quant) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param logPessoa
     * @return
     */
    public static Boolean existeraPessoa(String logPessoa) {
        return obterIdPessoa(logPessoa) > 0;
    }

    // Fim dos metodos de controle
    //  Inicio dos Sets e Gets
    /**
     * Este Metodo Retornar uma informação do tipo inteiro do id pessoa
     *
     * @return Retornar uma informação do tipo inteiro do id pessoa
     */
    public static int getIdpessoa() {
        return idpessoa;
    }

    /**
     * Este Metodo Setar uma informação do tipo inteiro do id pessoa
     *
     * @param idpessoa Setar uma informação do tipo inteiro do id pessoa
     */
    public static void setIdpessoa(int idpessoa) {
        Pessoa.idpessoa = idpessoa;
    }

    /**
     * Este Metodo Retornar uma informação do tipo String da Tabela Pessoa no
     * Login Pessoa
     *
     * @return Retornar uma informação do tipo String da Tabela Pessoa no Login
     * Pessoa
     */
    public static String getLogin() {
        return login;
    }

    /**
     * Este Metodo Setar uma informação do tipo String da Tabela Pessoa no Login
     * Pessoa
     *
     * @param login Setar uma informação do tipo String da Tabela Pessoa no
     * Login Pessoa
     */
    public static void setLogin(String login) {
        Pessoa.login = login;
    }

    /**
     * Este Metodo Retornar uma informação do tipo String da Tabela Pessoa no
     * Senha Pessoa
     *
     * @return Retornar uma informação do tipo String da Tabela Pessoa no Senha
     * Pessoa
     */
    public static String getSenha() {
        return senha;
    }

    /**
     * Este Metodo Setar uma informação do tipo String da Tabela Pessoa no Senha
     * Pessoa
     *
     * @param senha Setar uma informação do tipo String da Tabela Pessoa no
     * Senha Pessoa
     */
    public static void setSenha(String senha) {
        Pessoa.senha = senha;
    }

    /**
     * Este Metodo Retornar uma informação do tipo String da Tabela Pessoa na
     * Confirmação da senha Pessoa
     *
     * @return Retornar uma informação do tipo String da Tabela Pessoa na
     * Confirmação da senha Pessoa
     */
    public static String getConfSenha() {
        return confSenha;
    }

    /**
     * Este Metodo Setar uma informação do tipo String da Tabela Pessoa na
     * Confirmação da senha Pessoa
     *
     * @param confSenha Setar uma informação do tipo String da Tabela Pessoa na
     * Confirmação da senha Pessoa
     */
    public static void setConfSenha(String confSenha) {
        Pessoa.confSenha = confSenha;
    }

    /**
     * Este metodo Retornar uma informação do tipo String da Tabela Pessoa no
     * tipo de Pessoa, sendo que so podera utilizar <b>PF</b> ou <b>PJ</b>
     *
     * @return Retornar uma informação do tipo String da Tabela Pessoa no tipo
     * de Pessoa, sendo que so podera utilizar <b>PF</b> ou <b>PJ</b>
     */
    public static String getTipoPessoa() {
        return tipoPessoa;
    }

    /**
     * Este metodo Setar uma informação do tipo String da Tabela Pessoa no tipo
     * de Pessoa, sendo que so podera utilizar <b>PF</b> ou <b>PJ</b>
     *
     * @param tipoPessoa Setar uma informação do tipo String da Tabela Pessoa no
     * tipo de Pessoa, sendo que so podera utilizar <b>PF</b> ou
     * <b>PJ</b>
     */
    public static void setTipoPessoa(String tipoPessoa) {
        Pessoa.tipoPessoa = tipoPessoa;
    }

    /**
     * Este Metodo Retornar uma informação do tipo String da Tabela Pessoa no
     * Nome Pessoa
     *
     * @return Retornar uma informação do tipo String da Tabela Pessoa no Nome
     * Pessoa
     */
    public static String getNome() {
        return nome;
    }

    /**
     * Este metodo Setar uma informação do tipo String da Tabela Pessoa no Nome
     * Pessoa
     *
     * @param nome Setar uma informação do tipo String da Tabela Pessoa no Nome
     * Pessoa
     */
    public static void setNome(String nome) {
        Pessoa.nome = nome;
    }

    /**
     * Este Metodo Retornar uma informação do tipo String no nome da Tabela
     *
     * @return Retornar uma informação do tipo String no nome da Tabela
     */
    public static String getTABELA() {
        return TABELA;
    }
//  Fim dos Sets e Gets

}
