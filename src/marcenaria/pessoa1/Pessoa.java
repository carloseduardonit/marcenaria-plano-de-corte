/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marcenaria.pessoa1;

import java.sql.*;
import javax.swing.JOptionPane;
import marcenaria.Const.Messagem;
import marcenaria.dado.ModuloConector;

/**
 * 16/06/2019
 *
 * @author Carlos Eduardo dos Santos Figueiredo
 */
public class Pessoa {

    private static int idpessoa;
    private static final String TABELA = Pessoa.class.getSimpleName();
    private static String login, senha, confSenha, tipoPessoa, nome;
    private static Connection conexao;
    private static PreparedStatement pst;
    private static ResultSet rs;
    private static Statement stmt;

    /**
     * Este Metodo faz a conexao com o banco de dado
     */
    public static void Pessoa() {
        conexao = ModuloConector.getConecction();
    }

    /**
     * TA MONTANDO FALTA TESTA Este metodo inserer informação na tabela Pessoa
     *
     * @param Tabela Setar uma informação do tipo String no nome da Tabela
     * @param logPessoa Setar uma informação do tipo String da Tabela Pessoa no
     * novo Login Pessoa
     * @param senPessoa Setar uma informação do tipo String da Tabela Pessoa no
     * Senha Pessoa
     * @param conSenPessoa Setar uma informação do tipo String da Tabela Pessoa
     * na Confirmação da senha Pessoa
     * @param tipoPessoa Setar uma informação do tipo String da Tabela Pessoa no
     * tipo de Pessoa, sendo que so podera utilizar <b>PF</b> ou <b>PJ</b>
     * @param nomePessoa Setar uma informação do tipo String da Tabela Pessoa no
     * Nome Pessoa
     * @param documPessoa Setar uma informação do tipo String da Tabela Pessoa
     * no documento do Pessoa,sendo quanto o tipoPessoa se<b>PF</b> so poderá
     * anexa a infornação for de 11 digito, senão <b>PJ</b> so poderá anexa a
     * infornação for de 14 digito
     */
    public static void adicionarPessoa(String Tabela, String logPessoa, String senPessoa, String conSenPessoa, String tipoPessoa, String nomePessoa, String documPessoa) {
        try {
            Pessoa();
            int idPessoa = 0;
            String sql = "";
            if (Tabela.equalsIgnoreCase(Pessoa.getTABELA())) {
                if (Pessoa.obterIdPessoa(logPessoa) == 0) {
                    sql = "insert into " + Pessoa.getTABELA().toLowerCase() + "(login,senha,tipoPessoa,nome)  values (?,?,?,?)";
                }
            } else if (Tabela.equalsIgnoreCase(Cliente.getTABELA())) {
                Pessoa.adicionarPessoa(Pessoa.getTABELA(), logPessoa, senPessoa, conSenPessoa, tipoPessoa, nomePessoa, documPessoa);
                idPessoa = Pessoa.obterIdPessoa(logPessoa);
                sql = "insert into " + Cliente.getTABELA().toLowerCase() + " (id" + Pessoa.getTABELA() + ",login, docum) values (?,?,?)";
            } else if (Tabela.equalsIgnoreCase(Fornecedor.getTABELA())) {
                Pessoa.adicionarPessoa(Pessoa.getTABELA(), logPessoa, senPessoa, conSenPessoa, tipoPessoa, nomePessoa, documPessoa);
                sql = "insert into from " + Fornecedor.getTABELA().toLowerCase() + "(id" + Pessoa.getTABELA() + ", login, docum)  values (?,?,?)";
            }
            if (!logPessoa.isEmpty() && !senPessoa.isEmpty() && !conSenPessoa.isEmpty() && !tipoPessoa.isEmpty()
                    && !nomePessoa.isEmpty() && !documPessoa.isEmpty()) {
                pst = conexao.prepareStatement(sql);
                if (senPessoa.equals(conSenPessoa) && Tabela.equalsIgnoreCase(Pessoa.getTABELA())) {
                    pst.setString(1, logPessoa);
                    pst.setString(2, senPessoa);
                    pst.setString(3, tipoPessoa);
                    pst.setString(4, nomePessoa);
                    int adicionado = pst.executeUpdate();
                    if (adicionado == 0) {
                        Messagem.chamarTela(Messagem.ADICIONADO(Tabela));
                        ModuloConector.fecharConexao(conexao, rs, pst, pst);
                    }
                } else if (Tabela.equalsIgnoreCase(Cliente.getTABELA())) {
                    if ((documPessoa.length() == 11 && tipoPessoa.equalsIgnoreCase("pf"))
                            || (documPessoa.length() == 14 && tipoPessoa.equalsIgnoreCase("pj"))) {
                        pst.setInt(1, idPessoa);
                        pst.setString(2, logPessoa);
                        pst.setString(3, documPessoa);
                        int adicionado = pst.executeUpdate();
                        if (adicionado == 0) {
                            Messagem.chamarTela(Messagem.ADICIONADO(Tabela));
                            ModuloConector.fecharConexao(conexao, rs, pst, pst);
                        }
                    } else {
                        if (documPessoa.length() < 11 && tipoPessoa.equalsIgnoreCase("pf")) {
                            Messagem.chamarTela("CPJ falta " + (11 - documPessoa.length()) + "digitos");
                        } else if (documPessoa.length() > 11 && tipoPessoa.equalsIgnoreCase("pf")) {
                            Messagem.chamarTela("CNPJ tem mais " + (documPessoa.length() - 11) + "digitos");
                        } else if (documPessoa.length() < 14 && tipoPessoa.equalsIgnoreCase("pj")) {
                            Messagem.chamarTela("CNPJ falta " + (14 - documPessoa.length()) + "digitos");
                        } else if (documPessoa.length() > 14 && tipoPessoa.equalsIgnoreCase("pj")) {
                            Messagem.chamarTela("CNPJ tem mais " + (documPessoa.length() - 14) + "digitos");
                        }
                    }
                } else if (Tabela.equalsIgnoreCase(Fornecedor.getTABELA())) {
                    if ((documPessoa.length() == 11 && tipoPessoa.equalsIgnoreCase("pf"))
                            || (documPessoa.length() == 14 && tipoPessoa.equalsIgnoreCase("pj"))) {
                        pst.setInt(1, idPessoa);
                        pst.setString(2, logPessoa);
                        pst.setString(3, documPessoa);
                        int adicionado = pst.executeUpdate();
                        if (adicionado == 0) {
                            Messagem.chamarTela(Messagem.ADICIONADO(Tabela));
                            ModuloConector.fecharConexao(conexao, rs, pst, pst);
                        }
                    } else {
                        if (documPessoa.length() < 11 && tipoPessoa.equalsIgnoreCase("pf")) {
                            Messagem.chamarTela("CPF falta " + (11 - documPessoa.length()) + "digitos");
                        } else if (documPessoa.length() > 11 && tipoPessoa.equalsIgnoreCase("pf")) {
                            Messagem.chamarTela("CPF tem mais " + (documPessoa.length() - 11) + "digitos");
                        } else if (documPessoa.length() < 14 && tipoPessoa.equalsIgnoreCase("pj")) {
                            Messagem.chamarTela("CNPJ falta " + (14 - documPessoa.length()) + "digitos");
                        } else if (documPessoa.length() > 14 && tipoPessoa.equalsIgnoreCase("pj")) {
                            Messagem.chamarTela("CNPJ tem mais " + (documPessoa.length() - 14) + "digitos");
                        }
                    }
                }
            } else {
                Messagem.chamarTela(Messagem.VAZIO(
                        CampoVazio(Tabela, logPessoa, senPessoa, conSenPessoa, tipoPessoa, nomePessoa, documPessoa)));
            }
        } catch (Exception e) {
            Messagem.chamarTela(e);
        }
    }

    /**
     * TA MONTANDO FALTA INCLUI AS CHAVES ESTRAGEIRA E TESTA Este Metodo faz a
     * criação da Tabela
     *
     * @param Tabela Setar uma informação do tipo String no nome da Tabela
     */
    public static void criarPessoa(String Tabela) {
        try {
            Pessoa();
            String sql = "create table if not exists " + Tabela + "(";
            if (Tabela.equalsIgnoreCase(Pessoa.getTABELA())) {
                sql += " id" + Pessoa.getTABELA() + " int primary key auto_increment, "
                        + "login varchar(15) not  null unique, "
                        + "senha varchar(9) not null, "
                        + "tipoPessoa varchar(2) not null, "
                        + "nome varchar(100) not null)";
            } else if (Tabela.equalsIgnoreCase(Cliente.getTABELA())) {
                Pessoa.criarPessoa(Pessoa.getTABELA());
                sql += " id" + Pessoa.getTABELA() + " int not null unique, "
                        + "login varchar(15) not  null unique, "
                        + "id" + Cliente.getTABELA() + " int  auto_increment primary key, "
                        + "docum varchar(14) not null unique, "
                        + "foreign key (login) references " + Pessoa.getTABELA().toLowerCase() + "(login),"
                        + "foreign key (id" + Pessoa.getTABELA() + ") references " + Pessoa.getTABELA().toLowerCase() + "(id" + Pessoa.getTABELA() + "))";
            } else if (Tabela.equalsIgnoreCase(Fornecedor.getTABELA())) {
                Pessoa.criarPessoa(Pessoa.getTABELA());
                sql += " id" + Pessoa.getTABELA() + " int not null unique, "
                        + "login varchar(15) not  null unique, "
                        + "id" + Fornecedor.getTABELA() + " int primary key auto_increment, "
                        + "docum varchar(14) not null unique, "
                        + "foreign key (login) references " + Pessoa.getTABELA().toLowerCase() + "(login),"
                        + "foreign key (id" + Pessoa.getTABELA() + ") references " + Pessoa.getTABELA().toLowerCase() + "(id" + Pessoa.getTABELA() + "))";
            } else {
            }
            if (Tabela.equalsIgnoreCase(Cliente.getTABELA())
                    || Tabela.equalsIgnoreCase(Fornecedor.getTABELA())) {
                Messagem.criadoTabela(Tabela);
                if (Messagem.getCriada() == 0) {
                    stmt = conexao.createStatement();
                    int criada = stmt.executeUpdate(sql);
                    if (criada == 0) {
                        Messagem.tabelaCriada(Tabela);
                    }
                }
            }
        } catch (SQLException e) {
            Messagem.chamarTela(e);
        }
    }

    /**
     * TA MONTANDO FALTA TESTA Este Metodo deleta a tabela desejada
     *
     * @param Tabela Setar uma informação do tipo String no nome da Tabela
     */
    public static void deletarPessoa(String Tabela) {
        try {
            Pessoa();
            String sql = "drop table if exists " + Tabela;
            Messagem.deletadaTabela(Tabela);
            if (Messagem.getDeleta() == 0) {
                stmt = conexao.createStatement();
                int deleta = stmt.executeUpdate(sql);
                if (deleta == 0) {
                    Messagem.tabelaDeletada(Tabela);
                }
            }
        } catch (Exception e) {
            Messagem.chamarTela(e);
        }
    }

    /**
     * FAZER Este Metodo editar a informaçao de determinado Tabela da conforme o
     * paramentro da Tabela, anexado nlogPessoa no login do usario
     *
     * @param Tabela Setar uma informação do tipo String no nome da Tabela
     * @param nlogPessoa Setar uma informação do tipo String da Tabela Pessoa no
     * novo Login Pessoa
     * @param logPessoa Setar uma informação do tipo String da Tabela Pessoa no
     * Login Pessoa
     * @param senPessoa Setar uma informação do tipo String da Tabela Pessoa no
     * Senha Pessoa
     * @param conSenPessoa Setar uma informação do tipo String da Tabela Pessoa
     * na Confirmação da senha Pessoa
     * @param tipoPessoa Setar uma informação do tipo String da Tabela Pessoa no
     * tipo de Pessoa, sendo que so podera utilizar <b>PF</b> ou <b>PJ</b>
     * @param nomePessoa Setar uma informação do tipo String da Tabela Pessoa no
     * Nome Pessoa
     * @param documPessoa Setar uma informação do tipo String da Tabela Pessoa
     * no documento do Pessoa,sendo quanto o tipoPessoa se<b>PF</b> so poderá
     * anexa a infornação for de 11 digito, senão <b>PJ</b> so poderá anexa a
     * infornação for de 14 digito
     */
    public static void editarPessoa(String Tabela, String nlogPessoa, String logPessoa, String senPessoa, String conSenPessoa, String tipoPessoa, String nomePessoa, String documPessoa) {
        try {
            Pessoa();
            String sql = "";
            if (!Tabela.isEmpty() && !nlogPessoa.isEmpty() && !logPessoa.isEmpty() && !senPessoa.isEmpty()
                    && !conSenPessoa.isEmpty() && !tipoPessoa.isEmpty() && !nomePessoa.isEmpty()
                    && !documPessoa.isEmpty()) {
                if (senPessoa.equalsIgnoreCase(conSenPessoa)) {
                    if (Tabela.equalsIgnoreCase(Pessoa.getTABELA())) {
                        sql = "uptade " + Pessoa.getTABELA() + " set login =?, senha=?, tipoPessoa=?, nome=? where id" + Pessoa.getTABELA() + " = ?";
                    } else if (Tabela.equalsIgnoreCase(Cliente.getTABELA())) {
                        Pessoa.setIdpessoa(Pessoa.obterIdPessoa(logPessoa));
                        Cliente.setIdCliente(Cliente.obterIdCliente(logPessoa));
                        Pessoa.editarPessoa(Pessoa.getTABELA(), nlogPessoa, logPessoa, senPessoa, conSenPessoa, tipoPessoa, nomePessoa, documPessoa);
                        sql = "uptade " + Cliente.getTABELA() + " set login = ?, docum = ? where id" + Pessoa.getTABELA() + " = ? or id" + Cliente.getTABELA() + " = ?";
                    } else if (Tabela.equalsIgnoreCase(Fornecedor.getTABELA())) {
                        Pessoa.setIdpessoa(Pessoa.obterIdPessoa(logPessoa));
                        Fornecedor.setIdFornecedor(Fornecedor.obterIdFornecedor(logPessoa));
                        Pessoa.editarPessoa(Pessoa.getTABELA(), nlogPessoa, logPessoa, senPessoa, conSenPessoa, tipoPessoa, nomePessoa, documPessoa);
                        sql = "uptade " + Fornecedor.getTABELA() + " set login = ?, docum = ? where id" + Pessoa.getTABELA() + " = ? or id" + Fornecedor.getTABELA() + " = ?";
                    }

                    pst = conexao.prepareStatement(sql);
                    if (Tabela.equalsIgnoreCase(Pessoa.getTABELA())) {
                        pst.setString(1, nlogPessoa);
                        pst.setString(2, senPessoa);
                        pst.setString(3, tipoPessoa);
                        pst.setString(4, nomePessoa);
                        pst.setInt(5, Pessoa.getIdpessoa());
                    } else if (Tabela.equalsIgnoreCase(Cliente.getTABELA())) {
                        pst.setString(1, nlogPessoa);
                        pst.setString(2, documPessoa);
                        pst.setInt(3, Pessoa.getIdpessoa());
                        pst.setInt(4, Cliente.getIdCliente());
                    } else if (Tabela.equalsIgnoreCase(Fornecedor.getTABELA())) {
                        pst.setString(1, nlogPessoa);
                        pst.setString(2, documPessoa);
                        pst.setInt(3, Pessoa.getIdpessoa());
                        pst.setInt(4, Fornecedor.getIdFornecedor());
                    }
                    int editada = pst.executeUpdate();
                    if (editada == 0) {
                        String mens = "";
                        if (Tabela.equalsIgnoreCase(Pessoa.getTABELA())) {
                            mens = "Login: " + nlogPessoa + "\nsenha: " + senPessoa + "\ntipo de Pessoa: " + tipoPessoa + "\nNome: " + nomePessoa;
                        } else if (Tabela.equalsIgnoreCase(Cliente.getTABELA())) {
                            if (tipoPessoa.equalsIgnoreCase("pf")) {
                                mens = "Login: " + nlogPessoa + "\nCPF: " + documPessoa;
                            } else if (tipoPessoa.equalsIgnoreCase("pj")) {
                                mens = "Login: " + nlogPessoa + "\nCNPJ: " + documPessoa;
                            }
                        } else if (Tabela.equalsIgnoreCase(Fornecedor.getTABELA())) {
                            if (tipoPessoa.equalsIgnoreCase("pf")) {
                                mens = "Login: " + nlogPessoa + "\nCPF: " + documPessoa;
                            } else if (tipoPessoa.equalsIgnoreCase("pj")) {
                                mens = "Login: " + nlogPessoa + "\nCNPJ: " + documPessoa;
                            }
                        }
                        Messagem.chamarTela(Messagem.EDITADO(mens));
                    }
                } else {
                    Messagem.chamarTela("A senha não confere !!!");
                }
            } else {
                Messagem.chamarTela(Messagem.VAZIO(
                        CampoVazio(Tabela, logPessoa, senPessoa, conSenPessoa, tipoPessoa, nomePessoa, documPessoa)));
            }
        } catch (SQLException e) {
            Messagem.chamarTela(e);
        }
    }

    /**
     * FAZER Este metodo faz a exclução da informação na Tabela
     *
     * @param Tabela Setar uma informação do tipo String no nome da Tabela
     * @param logPessoa Setar uma informação do tipo String da Tabela Pessoa no
     * novo Login Pessoa
     * @param senPessoa Setar uma informação do tipo String da Tabela Pessoa no
     * Senha Pessoa
     * @param conSenPessoa Setar uma informação do tipo String da Tabela Pessoa
     * na Confirmação da senha Pessoa
     * @param tipoPessoa Setar uma informação do tipo String da Tabela Pessoa no
     * tipo de Pessoa, sendo que so podera utilizar <b>PF</b> ou <b>PJ</b>
     * @param nomePessoa Setar uma informação do tipo String da Tabela Pessoa no
     * Nome Pessoa
     * @param documPessoa Setar uma informação do tipo String da Tabela Pessoa
     * no documento do Pessoa,sendo quanto o tipoPessoa se<b>PF</b> so poderá
     * anexa a infornação for de 11 digito, senão <b>PJ</b> so poderá anexa a
     * infornação for de 14 digito
     */
    public static void excluirPessoa(String Tabela, String logPessoa) {
        try {
            Pessoa();
            if (!Tabela.isEmpty() && !logPessoa.isEmpty()) {
                String sql = "";
                if (Tabela.equalsIgnoreCase(Pessoa.getTABELA())) {
                    Pessoa.setIdpessoa(Pessoa.obterIdPessoa(logPessoa));
                    sql = "delete from " + Pessoa.getTABELA() + " where id" + Pessoa.getTABELA() + " = ? ";
                } else if (Tabela.equalsIgnoreCase(Cliente.getTABELA())) {
                    Pessoa.setIdpessoa(Pessoa.obterIdPessoa(logPessoa));
                    Cliente.setIdCliente(Cliente.obterIdCliente(logPessoa));
                    sql = "delete from " + Cliente.getTABELA() + " where id" + Pessoa.getTABELA() + " = ? ";
                } else if (Tabela.equalsIgnoreCase(Fornecedor.getTABELA())) {
                    Pessoa.setIdpessoa(Pessoa.obterIdPessoa(logPessoa));
                    Fornecedor.setIdFornecedor(Fornecedor.obterIdFornecedor(logPessoa));
                    sql = "delete from " + Fornecedor.getTABELA() + " where id" + Pessoa.getTABELA() + " = ? ";
                }
                pst = conexao.prepareStatement(sql);
                String s = "";
                if (Tabela.equalsIgnoreCase(Pessoa.getTABELA())) {
                    Pessoa.pesquisarPessoa(Tabela, logPessoa);
                    s = "Login: " + Pessoa.getLogin() + "\nSenha: " + Pessoa.getSenha() + "Tipo de pessoa: " + Pessoa.getTipoPessoa() + "\nNome: " + Pessoa.getNome();
                    pst.setInt(1, Pessoa.getIdpessoa());

                } else if (Tabela.equalsIgnoreCase(Cliente.getTABELA())) {
                    Cliente.pesquisarCliente(logPessoa);
                    s = "Login: " + Cliente.getLogin() + "\nSenha: " + Cliente.getSenha() + "\nTipo de pessoa: " + Cliente.getTipoPessoa() + "\nNome: " + Cliente.getNome() + "\ndomumento: " + Cliente.getDocum();
                    pst.setInt(1, Cliente.getIdCliente());
                } else if (Tabela.equalsIgnoreCase(Fornecedor.getTABELA())) {
                    Fornecedor.pesquisarFornecedor(logPessoa);
                    s = "Login: " + Fornecedor.getLogin() + "\nSenha: " + Fornecedor.getSenha() + "\nTipo de pessoa: " + Fornecedor.getTipoPessoa() + "\nNome: " + Fornecedor.getNome() + "\ndomumento: " + Fornecedor.getDocum();
                    pst.setInt(1, Fornecedor.getIdFornecedor());
                }

                int excluir = JOptionPane.showConfirmDialog(null, s, Tabela, JOptionPane.OK_CANCEL_OPTION);
                if (excluir == JOptionPane.OK_OPTION) {
                    int excluido = pst.executeUpdate(sql);
                    System.out.println("" + excluido);
                    if (excluido >= 0) {
                        Messagem.chamarTela(Messagem.EXCLUIDO(s));
                        if (!Tabela.equalsIgnoreCase(Pessoa.getTABELA())) {
                            int pes = JOptionPane.showConfirmDialog(null, "Deseja excluido todos os dados", sql, JOptionPane.OK_CANCEL_OPTION);
                            if (pes == JOptionPane.OK_OPTION) {
                                Pessoa.excluirPessoa(Pessoa.getTABELA(), logPessoa);
                            }
                        }
                    }
                }
            } else {
                Messagem.chamarTela(Messagem.VAZIO(
                        CampoVazio(Tabela, logPessoa, null, null, null, null, null)));
            }
        } catch (SQLException e) {
            Messagem.chamarTela(Tabela + " Excluir :" + e);
        }
    }

    /**
     * FAZER Este metodo Pesquisa na tabela do banco de dados
     *
     * @param Tabela Setar uma informação do tipo String no nome da Tabela
     * @param logPessoa Setar uma informação do tipo String da Tabela Pessoa no
     * novo Login Pessoa
     * @param senPessoa Setar uma informação do tipo String da Tabela Pessoa no
     * Senha Pessoa
     * @param conSenPessoa Setar uma informação do tipo String da Tabela Pessoa
     * na Confirmação da senha Pessoa
     * @param tipoPessoa Setar uma informação do tipo String da Tabela Pessoa no
     * tipo de Pessoa, sendo que so podera utilizar <b>PF</b> ou <b>PJ</b>
     * @param nomePessoa Setar uma informação do tipo String da Tabela Pessoa no
     * Nome Pessoa
     * @param documPessoa Setar uma informação do tipo String da Tabela Pessoa
     * no documento do Pessoa,sendo quanto o tipoPessoa se<b>PF</b> so poderá
     * anexa a infornação for de 11 digito, senão <b>PJ</b> so poderá anexa a
     * infornação for de 14 digito
     */
    public static void pesquisarPessoa(String Tabela, String logPessoa) {
        try {
            String sql = "";
            if (Tabela.equalsIgnoreCase(Cliente.getTABELA())) {
                Cliente.setIdCliente(Cliente.obterIdCliente(logPessoa));
                sql = "select P.login, P.senha, P.tipoPessoa, P.nome, C.docum from " + Pessoa.getTABELA() + " as P, " + Cliente.getTABELA() + " as C where  C.id" + Pessoa.getTABELA() + " = ?";
            } else if (Tabela.equalsIgnoreCase(Fornecedor.getTABELA())) {
                Fornecedor.setIdFornecedor(Fornecedor.obterIdFornecedor(logPessoa));
                sql = "select P.login, P.senha, P.tipoPessoa, P.nome, F.docum from " + Pessoa.getTABELA() + " as P, " + Fornecedor.getTABELA() + " as F where F.id" + Pessoa.getTABELA() + " = ?";
            }

            if (Tabela.equalsIgnoreCase(Cliente.getTABELA())) {
                if (!logPessoa.isEmpty()) {
                    pst = conexao.prepareStatement(sql);
                    pst.setInt(1, Cliente.getIdCliente());
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        Cliente.setLogin(rs.getString(1));
                        Cliente.setSenha(rs.getString(2));
                        Cliente.setConfSenha(Cliente.getSenha());
                        Cliente.setTipoPessoa(rs.getString(3));
                        Cliente.setNome(rs.getString(4));
                        Cliente.setDocum(rs.getString(5));
                    }
                }
            } else if (Tabela.equalsIgnoreCase(Fornecedor.getTABELA())) {
                if (!logPessoa.isEmpty()) {
                    pst = conexao.prepareStatement(sql);
                    pst.setInt(1, Fornecedor.getIdFornecedor());
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        Fornecedor.setLogin(rs.getString(1));
                        Fornecedor.setSenha(rs.getString(2));
                        Fornecedor.setConfSenha(Fornecedor.getSenha());
                        Fornecedor.setTipoPessoa(rs.getString(3));
                        Fornecedor.setNome(rs.getString(4));
                        Fornecedor.setDocum(rs.getString(5));
                    }
                }
            }
        } catch (SQLException e) {
            Messagem.chamarTela(Tabela + " Pesquisar: " + e);
        }

    }

    /**
     * TA MONTANDO FALTA TESTA Este metodo pesquisa na Tabela pessoa atraves no
     * logim Pessoa
     *
     * @param logPessoa Setar uma informação do tipo String da Tabela Pessoa no
     * Login Pessoa
     * @return Retornar uma informação do do banco de dado tipo inteiro do ID da
     * pessoa
     */
    public static int obterIdPessoa(String logPessoa) {
        return obterIdPessoa(logPessoa, Pessoa.getTABELA());
    }

    /**
     * TA MONTANDO FALTA TESTA
     *
     * @param logPessoa Setar uma informação do tipo String da Tabela Pessoa no
     * Login Pessoa
     * @param Tabela Setar uma informação do tipo String no nome da Tabela
     * @return Retornar uma informação do banco de dado do tipo inteiro do ID da
     * pessoa
     */
    public static int obterIdPessoa(String logPessoa, String Tabela) {
        int id = 0;
        try {
            Pessoa();
            String sql = "";
            if (Tabela.equalsIgnoreCase(Pessoa.getTABELA())) {
                sql = "select id" + Pessoa.getTABELA() + " from " + Pessoa.getTABELA() + " where login = ?";
            } else if (Tabela.equalsIgnoreCase(Cliente.getTABELA())) {
                sql = "select id" + Pessoa.getTABELA() + " from " + Cliente.getTABELA() + " where login = ?";
            } else if (Tabela.equalsIgnoreCase(Fornecedor.getTABELA())) {
                sql = "select id" + Pessoa.getTABELA() + " from " + Fornecedor.getTABELA() + " where login = ? ";
            }

            if (!logPessoa.isEmpty()) {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, logPessoa);
                rs = pst.executeQuery();
                if (rs.next()) {
                    id = rs.getInt(1);
                }
            }

        } catch (Exception e) {
            Messagem.chamarTela(e + "\nclasse Pessoa");
        }
        return id;
    }
    // Sets e Gets

    /**
     * Este metodo verificar ser Todos paramentros estão vazio e que tiver ser
     * anexado no array interno, retornado assim um Array de String
     *
     * @param Tabela Setar uma informação do tipo String no nome da Tabela
     * @param logPessoa Setar uma informação do tipo String da Tabela Pessoa no
     * Login Pessoa
     * @param senPessoa Setar uma informação do tipo String da Tabela Pessoa no
     * Senha Pessoa
     * @param conSenPessoa Setar uma informação do tipo String da Tabela Pessoa
     * na Confirmação da senha Pessoa
     * @param tipoPessoa Setar uma informação do tipo String da Tabela Pessoa no
     * tipo de Pessoa, sendo que so podera utilizar <b>PF</b> ou <b>PJ</b>
     * @param nomePessoa Setar uma informação do tipo String da Tabela Pessoa no
     * Nome Pessoa
     * @param documPessoa Setar uma informação do tipo String da Tabela Pessoa
     * no documento do Pessoa,sendo quanto o tipoPessoa se<b>PF</b> so poderá
     * anexa a infornação for de 11 digito, senão <b>PJ</b> so poderá anexa a
     * infornação for de 14 digito
     * @return
     */
    public static String[] CampoVazio(String Tabela, String logPessoa, String senPessoa, String conSenPessoa, String tipoPessoa, String nomePessoa, String documPessoa) {
        String[] campo = new String[7];
        for (int i = 0; i < campo.length;) {
            if (Tabela.isEmpty()) {
                campo[i] = "tabela";
                i++;
            }
            if (logPessoa.isEmpty()) {
                campo[i] = "login";
                i++;
            }
            if (senPessoa.isEmpty()) {
                campo[i] = "Senha";
                i++;
            }
            if (conSenPessoa.isEmpty()) {
                campo[i] = "Confimação de Senha";
                i++;
            }
            if (tipoPessoa.isEmpty()) {
                campo[i] = "Tipo de Pessoa";
                i++;
            }
            if (nomePessoa.isEmpty()) {
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
            }
            if (documPessoa.isEmpty()) {
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
            }
        }
        return campo;
    }

    /**
     * Este Metodo verifica os parametros senPessoa e conSenPessoa sao iquais e
     * a quantidade de caracteres retornado uma String
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
     * Este Metodo verifica os parametros senPessoa e conSenPessoa sao iquais e
     * a quantidade de caracteres retornado um valor Boolean
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
     * se sim o valor será TRUE</p>
     * <p>
     * se não o valor será FALSE</p>
     */
    public static Boolean campoDiferente(String senPessoa, String conSenPessoa, int quant) {
        if (senPessoa.equalsIgnoreCase(conSenPessoa) && senPessoa.length() >= quant && conSenPessoa.length() >= quant) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @return Retornar uma informação do tipo inteiro do id pessoa
     */
    public static int getIdpessoa() {
        return idpessoa;
    }

    /**
     *
     * @param idpessoa Setar uma informação do tipo inteiro do id pessoa
     */
    public static void setIdpessoa(int idpessoa) {
        Pessoa.idpessoa = idpessoa;
    }

    /**
     *
     * @return Retornar uma informação do tipo String da Tabela Pessoa no Login
     * Pessoa
     */
    public static String getLogin() {
        return login;
    }

    /**
     *
     * @param login Setar uma informação do tipo String da Tabela Pessoa no
     * Login Pessoa
     */
    public static void setLogin(String login) {
        Pessoa.login = login;
    }

    /**
     *
     * @return Retornar uma informação do tipo String da Tabela Pessoa no Senha
     * Pessoa
     */
    public static String getSenha() {
        return senha;
    }

    /**
     *
     * @param senha Setar uma informação do tipo String da Tabela Pessoa no
     * Senha Pessoa
     */
    public static void setSenha(String senha) {
        Pessoa.senha = senha;
    }

    /**
     *
     * @return Retornar uma informação do tipo String da Tabela Pessoa na
     * Confirmação da senha Pessoa
     */
    public static String getConfSenha() {
        return confSenha;
    }

    /**
     *
     * @param confSenha Setar uma informação do tipo String da Tabela Pessoa na
     * Confirmação da senha Pessoa
     */
    public static void setConfSenha(String confSenha) {
        Pessoa.confSenha = confSenha;
    }

    /**
     *
     * @return Retornar uma informação do tipo String da Tabela Pessoa no tipo
     * de Pessoa, sendo que so podera utilizar <b>PF</b> ou <b>PJ</b>
     */
    public static String getTipoPessoa() {
        return tipoPessoa;
    }

    /**
     *
     * @param tipoPessoa Setar uma informação do tipo String da Tabela Pessoa no
     * tipo de Pessoa, sendo que so podera utilizar <b>PF</b> ou <b>PJ</b>
     */
    public static void setTipoPessoa(String tipoPessoa) {
        Pessoa.tipoPessoa = tipoPessoa;
    }

    /**
     *
     * @return Retornar uma informação do tipo String da Tabela Pessoa no Nome
     * Pessoa
     */
    public static String getNome() {
        return nome;
    }

    /**
     *
     * @param nome Setar uma informação do tipo String da Tabela Pessoa no Nome
     * Pessoa
     */
    public static void setNome(String nome) {
        Pessoa.nome = nome;
    }

    /**
     *
     * @return Retornar uma informação do tipo String no nome da Tabela
     */
    public static String getTABELA() {
        return TABELA;
    }

}
