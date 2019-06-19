/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marcenaria.Pessoa;

import java.sql.*;
import marcenaria.Const.Messagem;
import marcenaria.dado.ModuloConector;

/**
 * 16/06/2019
 *
 * @author Carlos
 */
public class Pessoa {

    private static int idpessoa;
    private static final String TABELA = Pessoa.class.getSimpleName();
    private static String login, senha, confSenha, tipoPessoa, nome;
    private static Connection conexao;
    private static PreparedStatement pst;
    private static ResultSet rs;
    private static Statement stmt;

    public static void Pessoa() {
        conexao = ModuloConector.getConecction();
    }

    /**
     * TA MONTANDO FALTA TESTA
     *
     * @param Tabela
     * @param logPessoa
     * @param senPessoa
     * @param conSenPessoa
     * @param tipoPessoa
     * @param nomePessoa
     * @param documPessoa
     */
    public static void adicionarPessoa(String Tabela, String logPessoa, String senPessoa, String conSenPessoa,
            String tipoPessoa, String nomePessoa, String documPessoa) {
        try {
            Pessoa();
            int idPessoa = 0;
            String sql = "";
            if (Tabela.equalsIgnoreCase(Pessoa.getTABELA())) {
                sql = "insert into (login,senha,tipoPessoa,nome) from " + getTABELA() + " values (?,?,?,?)";
            } else if (Tabela.equalsIgnoreCase(Cliente.getTABELA())) {
                Pessoa.adicionarPessoa(Tabela, logPessoa, senPessoa, conSenPessoa, tipoPessoa, nomePessoa, documPessoa);
                idPessoa = Pessoa.obterIdPessoa(logPessoa);
                sql = "insert into (id" + Pessoa.getTABELA() + ",login ,CPF) from " + Cliente.getTABELA()
                        + " values (?,?,?)";
            } else if (Tabela.equalsIgnoreCase(Fornecedor.getTABELA())) {
                Pessoa.adicionarPessoa(Tabela, logPessoa, senPessoa, conSenPessoa, tipoPessoa, nomePessoa, documPessoa);
                sql = "insert into (id" + Pessoa.getTABELA() + ",login ,CNPJ) from " + Fornecedor.getTABELA()
                        + " values (?,?,?)";
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
                            Messagem.chamarTela("CPJ falta " + (11 - documPessoa.length()) + "digitos");
                        } else if (documPessoa.length() > 11 && tipoPessoa.equalsIgnoreCase("pf")) {
                            Messagem.chamarTela("CNPJ tem mais " + (documPessoa.length() - 11) + "digitos");
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
     * TA MONTANDO FALTA INCLUI AS CHAVES ESTRAGEIRA E TESTA
     *
     * @param Tabela
     */
    public static void criarPessoa(String Tabela) {
        try {
            Pessoa();
            String sql = "create table if not exists " + Tabela;
            if (Tabela.equalsIgnoreCase(Pessoa.getTABELA())) {
                sql += " id" + Pessoa.getTABELA() + " int primary key auto_increment,"
                        + "login varchar(15) not  null unique," + "senha varchar(9) not null,"
                        + "tipoPessoa varchar(2) not null," + "nome varchar(100) not null";
            } else if (Tabela.equalsIgnoreCase(Cliente.getTABELA())) {
                sql += " id" + Pessoa.getTABELA() + "int not null," + "login varchar(15) not  null unique," + "id"
                        + Cliente.getTABELA() + "int primary key auto_increment,"
                        + "docum varchar(14) not null unique,";
            } else if (Tabela.equalsIgnoreCase(Fornecedor.getTABELA())) {
                sql += " id" + Pessoa.getTABELA() + "int not null," + "login varchar(15) not  null unique," + "id"
                        + Fornecedor.getTABELA() + "int primary key auto_increment,"
                        + "docum varchar(14) not null unique,";
            } else {
            }
            if (Tabela.equalsIgnoreCase(Pessoa.getTABELA()) || Tabela.equalsIgnoreCase(Cliente.getTABELA())
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
     * TA MONTANDO FALTA TESTA
     *
     * @param Tabela
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
     * FAZER
     *
     * @param Tabela
     * @param nlogPessoa
     * @param logPessoa
     * @param senPessoa
     * @param conSenPessoa
     * @param tipoPessoa
     * @param nomePessoa
     * @param documPessoa
     */
    public static void editarPessoa(String Tabela, String nlogPessoa, String logPessoa, String senPessoa,
            String conSenPessoa, String tipoPessoa, String nomePessoa, String documPessoa) {
        try {
            Pessoa();
            String sql = "";
            if (!Tabela.isEmpty() && !nlogPessoa.isEmpty() && !logPessoa.isEmpty() && !senPessoa.isEmpty()
                    && !conSenPessoa.isEmpty() && !tipoPessoa.isEmpty() && !nomePessoa.isEmpty()
                    && !documPessoa.isEmpty()) {
                if (senPessoa.equalsIgnoreCase(conSenPessoa)) {
                    if (Tabela.equalsIgnoreCase(Pessoa.getTABELA())) {
                        sql = "uptade " + Pessoa.getTABELA() + " set login =?, senha=?, tipoPessoa=?, nome=? where id"
                                + Pessoa.getTABELA() + "=?";
                    } else if (Tabela.equalsIgnoreCase(Cliente.getTABELA())) {
                        Pessoa.setIdpessoa(Pessoa.obterIdPessoa(logPessoa));
                        Cliente.setIdCliente(Cliente.obterIdCliente(logPessoa));
                        Pessoa.editarPessoa(Pessoa.getTABELA(), nlogPessoa, logPessoa, senPessoa, conSenPessoa, tipoPessoa,
                                nomePessoa, documPessoa);
                        sql = "uptade " + Cliente.getTABELA() + " set login =?,docum=? where id" + Pessoa.getTABELA() + "=? or id" + Cliente.getTABELA() + "=?";
                    } else if (Tabela.equalsIgnoreCase(Fornecedor.getTABELA())) {
                        Pessoa.setIdpessoa(Pessoa.obterIdPessoa(logPessoa));
                        Fornecedor.setIdFornecedor(Fornecedor.obterIdFornecedor(logPessoa));
                        Pessoa.editarPessoa(Pessoa.getTABELA(), nlogPessoa, logPessoa, senPessoa, conSenPessoa, tipoPessoa,
                                nomePessoa, documPessoa);
                        sql = "uptade " + Fornecedor.getTABELA() + " set login =?,docum=? where id" + Pessoa.getTABELA() + "=? or id" + Fornecedor.getTABELA() + "=?";
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
     * FAZER
     *
     * @param Tabela
     * @param logPessoa
     * @param senPessoa
     * @param conSenPessoa
     * @param tipoPessoa
     * @param nomePessoa
     * @param documPessoa
     */
    public static void excluirPessoa(String Tabela, String logPessoa, String senPessoa, String conSenPessoa,String tipoPessoa, String nomePessoa, String documPessoa) {
        try {
            Pessoa();
            if (!Tabela.isEmpty() && !logPessoa.isEmpty() && !senPessoa.isEmpty() && !conSenPessoa.isEmpty() && !tipoPessoa.isEmpty() && !nomePessoa.isEmpty() && !documPessoa.isEmpty()) {
                String sql = "";
                if (Tabela.equalsIgnoreCase(Pessoa.getTABELA())) {
                    Pessoa.setIdpessoa(Pessoa.obterIdPessoa(logPessoa));
                    sql = "";
                } else if (Tabela.equalsIgnoreCase(Cliente.getTABELA())) {
                    Pessoa.setIdpessoa(Pessoa.obterIdPessoa(logPessoa));
                    Cliente.setIdCliente(Cliente.obterIdCliente(logPessoa));
                    Pessoa.excluirPessoa(Pessoa.getTABELA(), logPessoa, senPessoa, conSenPessoa, tipoPessoa, nomePessoa, documPessoa);
                    sql = "";
                } else if (Tabela.equalsIgnoreCase(Fornecedor.getTABELA())) {
                    Pessoa.setIdpessoa(Pessoa.obterIdPessoa(logPessoa));
                    Fornecedor.setIdFornecedor(Fornecedor.obterIdFornecedor(logPessoa));
                    Pessoa.excluirPessoa(Pessoa.getTABELA(), logPessoa, senPessoa, conSenPessoa, tipoPessoa, nomePessoa, documPessoa);
                    sql = "";
                }
                pst = conexao.prepareStatement(sql);
                if (Tabela.equalsIgnoreCase(Pessoa.getTABELA())) {
                    pst.setString(1, logPessoa);
                    pst.setString(2, senPessoa);
                    pst.setString(3, tipoPessoa);
                    pst.setString(4, nomePessoa);
                    pst.setInt(5, Pessoa.getIdpessoa());
                } else if (Tabela.equalsIgnoreCase(Cliente.getTABELA())) {
                    pst.setString(1, logPessoa);
                    pst.setString(2, documPessoa);
                    pst.setInt(3, Pessoa.getIdpessoa());
                    pst.setInt(4, Cliente.getIdCliente());
                } else if (Tabela.equalsIgnoreCase(Fornecedor.getTABELA())) {
                    pst.setString(1, logPessoa);
                    pst.setString(2, documPessoa);
                    pst.setInt(3, Pessoa.getIdpessoa());
                    pst.setInt(4, Fornecedor.getIdFornecedor());
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
     * FAZER
     *
     * @param Tabela
     * @param logPessoa
     * @param senPessoa
     * @param conSenPessoa
     * @param tipoPessoa
     * @param nomePessoa
     * @param documPessoa
     */
    public static void pesquisarPessoa(String Tabela, String logPessoa, String senPessoa, String conSenPessoa, String tipoPessoa, String nomePessoa, String documPessoa) {
        try {
            String sql = "";
            if (Tabela.equalsIgnoreCase(Pessoa.getTABELA())) {
                Pessoa.setIdpessoa(Pessoa.obterIdPessoa(logPessoa));
                sql = "select * from " + Pessoa.getTABELA() + " where id" + Pessoa.getTABELA() + " = ?";
            } else if (Tabela.equalsIgnoreCase(Cliente.getTABELA())) {
                Cliente.setIdCliente(Cliente.obterIdCliente(logPessoa));
                sql = "select docum, login from " + Cliente.getTABELA() + " where id" + Cliente.getTABELA() + " = ?";
            } else if (Tabela.equalsIgnoreCase(Fornecedor.getTABELA())) {
                Fornecedor.setIdFornecedor(Fornecedor.obterIdFornecedor(logPessoa));
                sql = "select docum, login from " + Fornecedor.getTABELA() + " where login = ?";
            }

            if (Tabela.equalsIgnoreCase(Pessoa.getTABELA())) {
                if (!logPessoa.isEmpty()) {
                    pst = conexao.prepareStatement(sql);
                    pst.setInt(1, Pessoa.getIdpessoa());
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        Pessoa.setNome(rs.getString(1));
                        Pessoa.setSenha(rs.getString(2));
                        Pessoa.setTipoPessoa(rs.getString(3));
                    }
                }
            } else if (Tabela.equalsIgnoreCase(Cliente.getTABELA())) {
                if (!logPessoa.isEmpty()) {
                    pst = conexao.prepareStatement(sql);
                    pst.setInt(1, Cliente.getIdCliente());
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        Cliente.setCpf(rs.getInt(1));
                        Cliente.setLogin(rs.getString(2));
                    }
                }
            } else if (Tabela.equalsIgnoreCase(Fornecedor.getTABELA())) {
                if (!logPessoa.isEmpty()) {
                    pst = conexao.prepareStatement(sql);
                    pst.setInt(1, Fornecedor.getIdFornecedor());
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        Fornecedor.setCNPJ(rs.getString(1));
                        Fornecedor.setLogin(rs.getString(2));
                    }
                }
            }
        } catch (SQLException e) {
            Messagem.chamarTela(e);
        }

    }

    /**
     * TA MONTANDO FALTA TESTA
     *
     * @param logPessoa
     * @return
     */
    public static int obterIdPessoa(String logPessoa) {
        return obterIdPessoa(logPessoa, getTABELA());
    }

    /**
     * TA MONTANDO FALTA TESTA
     *
     * @param logPessoa
     * @param Tabela
     * @return
     */
    public static int obterIdPessoa(String logPessoa, String Tabela) {
        int id = 0;
        try {
            Pessoa();
            String sql = "";
            if (Tabela.equalsIgnoreCase(Pessoa.getTABELA())) {
                sql = "select id" + Pessoa.getTABELA() + " from " + Pessoa.getTABELA() + " where login = ?";
            } else if (Tabela.equalsIgnoreCase(Cliente.getTABELA())) {
                sql = "select id" + Cliente.getTABELA() + " from " + Cliente.getTABELA() + " where login = ?";
            } else if (Tabela.equalsIgnoreCase(Fornecedor.getTABELA())) {
                sql = "select id" + Fornecedor.getTABELA() + " from " + Fornecedor.getTABELA() + " where login = ?";
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
     * TA MONTANDO FALTA TESTA
     *
     * @param Tabela
     * @param logPessoa
     * @param senPessoa
     * @param conSenPessoa
     * @param tipoPessoa
     * @param nomePessoa
     * @param documPessoa
     * @return
     */
    public static String[] CampoVazio(String Tabela, String logPessoa, String senPessoa, String conSenPessoa,
            String tipoPessoa, String nomePessoa, String documPessoa) {
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
     *
     * @return
     */
    public static int getIdpessoa() {
        return idpessoa;
    }

    /**
     *
     * @param idpessoa
     */
    public static void setIdpessoa(int idpessoa) {
        Pessoa.idpessoa = idpessoa;
    }

    /**
     *
     * @return
     */
    public static String getLogin() {
        return login;
    }

    /**
     *
     * @param login
     */
    public static void setLogin(String login) {
        Pessoa.login = login;
    }

    /**
     *
     * @return
     */
    public static String getSenha() {
        return senha;
    }

    /**
     *
     * @param senha
     */
    public static void setSenha(String senha) {
        Pessoa.senha = senha;
    }

    /**
     *
     * @return
     */
    public static String getConfSenha() {
        return confSenha;
    }

    /**
     *
     * @param confSenha
     */
    public static void setConfSenha(String confSenha) {
        Pessoa.confSenha = confSenha;
    }

    /**
     *
     * @return
     */
    public static String getTipoPessoa() {
        return tipoPessoa;
    }

    /**
     *
     * @param tipoPessoa
     */
    public static void setTipoPessoa(String tipoPessoa) {
        Pessoa.tipoPessoa = tipoPessoa;
    }

    /**
     *
     * @return
     */
    public static String getNome() {
        return nome;
    }

    /**
     *
     * @param nome
     */
    public static void setNome(String nome) {
        Pessoa.nome = nome;
    }

    /**
     *
     * @return
     */
    public static String getTABELA() {
        return TABELA;
    }

}
