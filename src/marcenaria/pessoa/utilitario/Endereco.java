/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marcenaria.pessoa.utilitario;

import dados.*;
import endereco.banco.CEP;
import informacao.Messagem;
import java.sql.*;
import javax.swing.JOptionPane;
import marcenaria.pessoa.Cliente;
import marcenaria.pessoa.Fornecedor;
import marcenaria.pessoa.Pessoa;

/**
 *
 * @author Carlos Eduardo dos Santos Figueiredo
 */
public class Endereco extends CEP {

    public static void main(String[] args) {
        usuarioZeroenderecoZero();
    }

    private static int ID, IDPessoa, IDCliente, IDFornecedor, numero, quantEndereco;
    private static final String TABELA = Endereco.class.getSimpleName();
    private static String complemento;
    private static Connection conexao;
    private static ResultSet rs;
    private static ResultSetMetaData rsmd;
    private static PreparedStatement pst;
    private static Statement stmt;

    private static void usuarioZeroenderecoZero() {
        if (Pessoa.NaoExisterTabelaPessoa()) {
            Pessoa.pessoaZero();
        }
        DataBase.importarBackupdataBaseSQL("C:\\Users\\Carlos\\Documents\\NetBeansProjects\\Marcenaria\\src\\marcenaria\\pessoa\\utilitario\\EnderecoZero.sql", Pessoa.banco);
    }

    /**
     *
     * este Método faz a conexão com o banco de dados primário
     */
    private static void endereco() {
        conexao = ModuloConector.getConecction(Pessoa.banco);
    }

    /**
     * ta OK este método faz a verificação se a tabela cliente e fornecedor
     * existe, senao existir um ou ambas serao criadas , logo apos criação da
     * tabela Endereço no banco de dado principal
     */
    public static void criarEndereco() {
        if (Cliente.NaoExisterTabelaCliente()) {
            Cliente.criarCliente();
        }
        if (Fornecedor.NaoExisteTabelaFornecedor()) {
            Fornecedor.criarFornecedor();
        }
        String sql = "create table if not exists " + getTABELA() + " ("
                + "id" + getTABELA() + " int auto_increment primary key, "
                + "id" + Pessoa.getTABELA() + " int not null default 0, "
                + "id" + Cliente.getTABELA() + " int not null default 0, "
                + "id" + Fornecedor.getTABELA() + " int not null default 0, "
                + "numero int not null default 0, "
                + "quantEndereco int not null default 0, "
                + "complemento varchar(200), "
                + "cep varchar(9) not null, "
                + "foreign key (id" + Pessoa.getTABELA() + ") references " + Pessoa.getTABELA() + " (id" + Pessoa.getTABELA() + "), "
                + "foreign key (id" + Cliente.getTABELA() + ") references " + Cliente.getTABELA() + " (id" + Cliente.getTABELA() + "), "
                + "foreign key (id" + Fornecedor.getTABELA() + ") references " + Fornecedor.getTABELA() + " (id" + Fornecedor.getTABELA() + "))";
        Table.criarTabela(sql, getTABELA());
    }

    /**
     *
     * Precisa ser testado. Este metodo faz a deletacao da tabela Endereço
     */
    public static void deletarEndereco() {
        Table.deletarTabela(Endereco.getTABELA());
    }

    /**
     *
     * Este Método faz a adição de informações na tabela Endereço do banco de
     * dados principal.
     *
     * @param login Setar uma informação de valor String do login da pessoa e/ou
     * cliente e/ou fornecedor
     * @param CEP Setar uma informação de valor String do CEP
     * @param Numero Setar uma informação de valor inteiro do número do endereço
     * @param Complemento Setar uma informação de valor String do complemento de
     * endereço
     */
    public static void adicionarEndereco(String login, String CEP, int Numero, String Complemento) {
        if (NaoHaCampoVazio(login, CEP, Numero, Complemento)) {
            try {
                String sql = "insert into " + Endereco.getTABELA()
                        + "(id" + Pessoa.getTABELA() + ", id" + Cliente.getTABELA()
                        + ", id" + Fornecedor.getTABELA()
                        + ", cep, numero, complemento) values (?,?,?,?,?,?)";
                int i = 1, j = 1;
                endereco();
                pst = conexao.prepareStatement(sql);
                pst.setInt(i++, Pessoa.obterIdPessoa(login));
                pst.setInt(i++, Cliente.obterIdClientetoCliente(login));
                pst.setInt(i++, Fornecedor.obterIdFornecedortoFornecedor(login));
                pst.setString(i++, CEP);
                pst.setInt(i++, Numero);
                pst.setString(i++, Complemento);
                int adicionar = pst.executeUpdate();
                if (adicionar > 0) {
                    Messagem.chamarTela(Messagem.ADICIONADO(Endereco.exibirEnderecoToString(login, CEP)));
                }
            } catch (SQLException e) {
                Messagem.chamarTela("Adicionar endereço: " + e);
            } finally {
                ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
            }
        }
    }

    /**
     * Este metodo faz a editacao da tabela Endereço do banco de dados
     * principal.
     *
     * @param login Setar uma informação de valor String do login da pessoa e/ou
     * cliente e/ou fornecedor
     * @param CEP Setar uma informação de valor String do CEP
     * @param Numero Setar uma informação de valor inteiro do número do endereço
     * @param Complemento Setar uma informação de valor String do complemento de
     * endereço
     */
    public static void editarEndereco(String login, String CEP, int Numero, String Complemento) {
        editarEndereco(0, login, CEP, Numero, 0, Complemento);
    }

    /**
     *
     * Este metodo faz a editacao da tabela Endereço do banco de dados
     * principal.
     *
     * @param IDEndereco Setar uma informação de valor inteiro do Id do Endereço
     * @param login Setar uma informação de valor String do login da pessoa e/ou
     * cliente e/ou fornecedor
     * @param CEP Setar uma informação de valor String do CEP
     * @param Numero Setar uma informação de valor inteiro do número do endereço
     * @param Complemento Setar uma informação de valor String do complemento de
     * endereço
     */
    public static void editarEndereco(int IDEndereco, String login, String CEP, int Numero, String Complemento) {
        editarEndereco(IDEndereco, login, CEP, Numero, 0, Complemento);
    }

    /**
     * Este metodo faz a editacao da tabela Endereço do banco de dados
     * principal.
     *
     * @param IDEndereco Setar uma informação de valor inteiro do Id do Endereço
     * @param login Setar uma informação de valor String do login da pessoa e/ou
     * cliente e/ou fornecedor
     * @param CEP Setar uma informação de valor String do CEP
     * @param Numero Setar uma informação de valor inteiro do número do endereço
     * @param quantEndereço Setar uma informação de valor inteiro da quantidade
     * de Endereço
     * @param Complemento Setar uma informação de valor String do complemento de
     * endereço
     *
     */
    public static void editarEndereco(int IDEndereco, String login, String CEP, int Numero, int quantEndereço, String Complemento) {
        if (NaoHaCampoVazio(login, CEP, Numero, Complemento)) {
            try {
                int i = 1, count = 0;
                String sql = "update " + Endereco.getTABELA()
                        + " set ";
                if (!login.isEmpty()) {
                    sql += " IDPessoa = ?, IDCliente=?, IDFornecedor = ?";
                    count++;
                }
                if (!CEP.isEmpty()) {
                    if (count > 0) {
                        sql += ", ";
                    }
                    sql += "cep =?";
                    count++;
                }
                if (!String.valueOf(Numero).isEmpty()) {
                    if (count > 0) {
                        sql += ", ";
                    }
                    sql += "numero =?";
                    count++;
                }
                if (!String.valueOf(quantEndereco).isEmpty()) {
                    if (count > 0) {
                        sql += ", ";
                    }
                    sql += "quantEndereco=?";
                    count++;
                }
                if (!Complemento.isEmpty()) {
                    if (count > 0) {
                        sql += ", ";
                    }
                    sql += "Complemento =?";
                    count++;
                }
                sql += " where id" + getTABELA() + " =?";
                endereco();
                pst = conexao.prepareStatement(sql);
                if (!login.isEmpty()) {
                    pst.setInt(i++, Pessoa.obterIdPessoa(login));
                    pst.setInt(i++, Cliente.obterIdClientetoCliente(login));
                    pst.setInt(i++, Fornecedor.obterIdFornecedortoFornecedor(login));
                }
                if (CEP.isEmpty()) {
                    pst.setString(i++, CEP);
                }
                if (String.valueOf(Numero).isEmpty()) {
                    pst.setInt(i++, Numero);
                }
                if (!Complemento.isEmpty()) {
                    pst.setString(i++, Complemento);
                }
                pst.setInt(i++, IDEndereco);
                int editado = pst.executeUpdate();
                if (editado > 0) {
                    Messagem.chamarTela(Messagem.EDITADO(EnderecoToString(CEP, Complemento, Numero)));
                }
            } catch (SQLException e) {
                Messagem.chamarTela("Editar endereço: " + e);
            } finally {
                ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
            }
        }
    }

    /**
     * Este Método excluir informação na Tabela Endereço do banco de dados
     * principal.
     *
     * @param login Setar uma informação de valor String do login da pessoa e/ou
     * cliente e/ou fornecedor
     * @param CEP Setar uma informação de valor String do CEP
     * @param Numero Setar uma informação de valor inteiro do número do endereço
     * @param Complemento Setar uma informação de valor String do complemento de
     * endereço
     * @param ou Setar uma informação de valor boolean na instrução sql se o
     * valor for True: incluir OR no sql. se o valor for False: incluir AND no
     * sql.
     *
     *
     */
    public static void excluirEndereco(String login, String CEP, int Numero, String Complemento, boolean ou) {
        if (NaoHaCampoVazio(login, CEP, Numero, Complemento)) {
            try {
                int i = 1, count = 0, excluido, excluir;
                String sql = "delete  from " + Endereco.getTABELA() + " where ", a;
                if (ou) {
                    a = " or ";
                } else {
                    a = " and ";
                }
                if (!login.isEmpty()) {
                    sql += "login =?";
                    count++;
                }
                if (!CEP.isEmpty()) {
                    if (count > 0) {
                        sql += a;
                    }
                    sql += "cep =?";
                    count++;
                }
                if (!String.valueOf(Numero).isEmpty()) {
                    if (count > 0) {
                        sql += a;
                    }
                    sql += "numero +?";
                    count++;
                }
                if (!Complemento.isEmpty()) {
                    if (count > 0) {
                        sql += a;
                    }
                    sql += "complemento =?";
                    count++;
                }
                endereco();
                pst = conexao.prepareStatement(sql);
                if (!login.isEmpty()) {
                    pst.setInt(i++, Pessoa.obterIdPessoa(login));
                    pst.setInt(i++, Cliente.obterIdClientetoCliente(login));
                    pst.setInt(i++, Fornecedor.obterIdFornecedortoFornecedor(login));
                }
                if (!CEP.isEmpty()) {
                    pst.setString(i++, CEP);
                }
                if (!String.valueOf(Numero).isEmpty()) {
                    pst.setInt(i++, Numero);
                }
                if (!Complemento.isEmpty()) {
                    pst.setString(i++, Complemento);
                }
                excluir = JOptionPane.showInternalConfirmDialog(null, EnderecoToString(CEP, Complemento, Numero), getTABELA(), JOptionPane.OK_CANCEL_OPTION);
                if (excluir == JOptionPane.OK_OPTION) {
                    excluido = pst.executeUpdate();
                    if (excluido > 0) {
                        Messagem.chamarTela(Messagem.EXCLUIDO(EnderecoToString(CEP, Complemento, Numero)));
                    }
                }
            } catch (SQLException e) {
                Messagem.chamarTela("Excluir Endereco: " + e);
            } finally {
                ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
            }
        }
    }

    /**
     * Este Método faz a pesquisar na tabela Endereço no banco de dado
     * principal.
     *
     * @param login Setar uma informação de valor String do login da pessoa e/ou
     * cliente e/ou fornecedor
     * @param CEP Setar uma informação de valor String do CEP
     * @param Numero Setar uma informação de valor inteiro do número do endereço
     * @param Complemento Setar uma informação de valor String do complemento de
     * endereço
     * @param ou Setar uma informação de valor boolean na instrução sql se o
     * valor for True: incluir OR no sql. se o valor for False: incluir AND no
     * sql.
     */
    public static void pesquisarEndereco(String login, String CEP, int Numero, String Complemento, boolean ou) {
        if (NaoHaCampoVazio(login, CEP, Numero, Complemento)) {
            String sql = " select id" + Endereco.getTABELA() + ", id" + Pessoa.getTABELA() + ", id" + Cliente.getTABELA() + ", id" + Fornecedor.getTABELA() + ", numero, quantEndereco, complemento, cep from " + Endereco.getTABELA() + " where ", a;
            if (ou) {
                a = " or ";
            } else {
                a = " and ";
            }
            try {
                int i = 1, j = 1, cont = 0;

                if (!login.isEmpty()) {
                    sql += "id" + Pessoa.getTABELA() + "= ?" + a + "id" + Cliente.getTABELA() + "= ?" + a + "id" + Fornecedor.getTABELA() + "= ? ";
                    cont++;
                }
                if (!CEP.isEmpty()) {
                    if (cont > 0) {
                        sql += a;
                    }
                    sql += "Cep = ?";
                }
                if (numero >= 0) {
                    if (cont > 0) {
                        sql += a;
                    }
                    sql += "numero = ?";
                }
                if (!Complemento.isEmpty()) {
                    if (cont > 0) {
                        sql += a;
                    }
                    sql += "Complemento = ?";
                }
                endereco();
                pst = conexao.prepareStatement(sql);
                if (!login.isEmpty()) {
                    pst.setInt(j++, Pessoa.obterIdPessoa(login));
                    pst.setInt(j++, Cliente.obterIdClientetoCliente(login));
                    pst.setInt(j++, Fornecedor.obterIdFornecedortoFornecedor(login));
                }
                if (!CEP.isEmpty()) {
                    pst.setString(j++, CEP);
                }
                if (numero >= 0) {
                    pst.setInt(j++, Numero);
                }
                if (!Complemento.isEmpty()) {
                    pst.setString(j, Complemento);
                }
                rs = pst.executeQuery();
                if (rs.next()) {
                    setID(rs.getInt(i++));
                    setIDPessoa(rs.getInt(i++));
                    setIDCliente(rs.getInt(i++));
                    setIDFornecedor(rs.getInt(i++));
                    setNumero(rs.getInt(i++));
                    setQuantEndereco(rs.getInt(i++));
                    setComplemento(rs.getString(i++));
                    setCep(rs.getString(i++));
                    ObterEnderecodeCEP(getCep());
                }
            } catch (SQLException e) {
                Messagem.chamarTela("Pesquisar Endereço: " + e);
            } finally {
                ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
            }
        }
    }

    /**
     * Este Método faz a exibição em tela da informação de endereço
     *
     * @param login Setar uma informação de valor String do login da pessoa e/ou
     * cliente e/ou fornecedor
     * @param cep Setar uma informação de valor String do CEP
     */
    public static void exibirEndereco(String login, String cep) {
        Messagem.chamarTela(exibirEnderecoToString(login, cep));
    }

    /**
     * Este Método retorna uma informação de valor String do endereço
     *
     * @param login Setar uma informação de valor String do login da pessoa e/ou
     * cliente e/ou fornecedor
     * @param cep Setar uma informação de valor String do CEP de Endereço.
     * @return retorna uma informação de valor String do endereço completo
     */
    public static String exibirEnderecoToString(String login, String cep
    ) {
        pesquisarEndereco(login, cep, -1, "", true);
        CEP.EnderecoToString(Endereco.getCep(), Endereco.getComplemento(), Endereco.getNumero());
        return "";
    }

    /**
     * Este Metodo obter o Id Endereço da tabela endereço no banco de dados
     * primario. se retornar 0 e por que não há registro.
     *
     * @param login Setar uma informação de valor String do login da pessoa e/ou
     * cliente e/ou fornecedor
     * @param cep Setar uma informação de valor String do CEP de Endereço.
     * @param numero Setar uma informação de valor inteiro do número do endereço
     * @param complemento Setar uma informação de valor String do complemento de
     * endereço
     * @param ou Setar uma informação de valor boolean na instrução sql se o
     * valor for True: incluir OR no sql. se o valor for False: incluir AND no
     * sql.
     * @return Retornar o Id Endereço da tabela endereço no banco de dados
     * primario.
     */
    public static int ObterIDEndereco(String login, String cep, int numero, String complemento, boolean ou) {
        String sql = "select idEndereco from " + Endereco.getTABELA() + " where ", a;
        int res = 0, i = 1, j = i, count = res;
        if (ou) {
            a = " or ";
        } else {
            a = " and ";
        }

        if (!login.isEmpty()) {
            sql += "id" + Pessoa.getTABELA() + " =?" + a + "id" + Cliente.getTABELA() + " =?" + a + "id" + Fornecedor.getTABELA() + " =?";
            count++;
        }
        if (!cep.isEmpty()) {
            if (count > 0) {
                sql += a;
            }
            sql += "cep =?";
            count++;
        }
        if (!complemento.isEmpty()) {
            if (count > 0) {
                sql += a;
            }
            sql += "complemento =?";
            count++;
        }
        try {
            endereco();
            pst = conexao.prepareStatement(sql);
            if (!login.isEmpty()) {
                pst.setInt(i++, Pessoa.obterIdPessoa(login));
                pst.setInt(i++, Cliente.obterIdClientetoCliente(login));
                pst.setInt(i++, Fornecedor.obterIdFornecedortoFornecedor(login));
            }
            if (!cep.isEmpty()) {
                pst.setString(i++, cep);
            }
            if (!complemento.isEmpty()) {
                pst.setString(i++, complemento);
            }
            rs = pst.executeQuery();
            if (rs.next()) {
                res = rs.getInt(j);
            }
        } catch (Exception e) {
            Messagem.chamarTela("Obter o id do Endereço: " + e);
        } finally {
            ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
        }
        return res;
    }

    /**
     * ok Este Método Retornar uma informação de valor booleano se ha campos
     * vazios
     *
     * @param login Setar uma informação de valor String do login da pessoa e/ou
     * cliente e/ou fornecedor
     * @param CEP Setar uma informação de valor String do CEP
     * @param Numero Setar uma informação de valor inteiro do número do endereço
     * @param Complemento Setar uma informação de valor String do complemento de
     * endereço
     *
     * @return Retornar se nao ha campo Vazio.
     */
    public static boolean NaoHaCampoVazio(String login, String CEP, int Numero, String Complemento) {
        boolean res = !haCampoVazio(login, CEP, Numero, Complemento, true);
        return res;
    }

    /**
     * ok Este Método Retornar uma informação de valor booleano se ha campos
     * vazios
     *
     * @param login Setar uma informação de valor String do login da pessoa e/ou
     * cliente e/ou fornecedor
     * @param CEP Setar uma informação de valor String do CEP
     * @param Numero Setar uma informação de valor inteiro do número do endereço
     * @param Complemento Setar uma informação de valor String do complemento de
     * endereço
     * @param mensagem Setar uma informação de valor booleano do complemento de
     * endereço
     * @return Retornar se ha campo Vazio.
     */
    public static boolean haCampoVazio(String login, String CEP, int Numero, String Complemento, boolean mensagem) {
        boolean res = login.isEmpty() || CEP.isEmpty() || String.valueOf(Numero).isEmpty() || Numero < 0 || Complemento.isEmpty();
        if (res && mensagem) {
            Messagem.chamarTela(Messagem.VAZIO(CampoVazio(login, CEP, Numero, Complemento)));
        }
        return res;
    }

    /**
     * Este Método Retornar um array de informação de valor String dos campos
     * vazios
     *
     * @param login Setar uma informação de valor String do login da pessoa e/ou
     * cliente e/ou fornecedor
     * @param CEP Setar uma informação de valor String do CEP
     * @param Numero Setar uma informação de valor inteiro do número do endereço
     * @param Complemento Setar uma informação de valor String do complemento de
     * endereço
     *
     * @return Retornar um array de informação de valor String dos campos vazios
     */
    public static String[] CampoVazio(String login, String CEP, int Numero, String Complemento) {
        String[] vazio = new String[4];
        int i = 0;
        if (login.isEmpty()) {
            vazio[i++] = "Login";
        }
        if (CEP.isEmpty()) {
            vazio[i++] = "CEP";
        }
        if (String.valueOf(Numero).isEmpty() || Numero < 0) {
            vazio[i++] = "numero";
        }
        if (Complemento.isEmpty()) {
            vazio[i++] = "Complemento";
        }
        return vazio;
    }

    /**
     *
     * Este Método faz a limpeza dos get e set
     */
    public static void limparCampos() {
        String limpar = "";
        int limp = 0;
        preencherCampos(limp, limp, limp, limp, limp, limp, limpar, limpar, limpar);
    }

    /**
     * Este Metodo e responsavel pelo prenchimento dos sets da Classe endereço
     *
     * @param IDEndereco setar uma informação de valor inteiro do ID de endereço
     * @param IDPessoa Setar uma informação de valor inteiro do IDPessoa de
     * endereço
     * @param IDCliente Setar uma informação de valor inteiro do IDPessoa de
     * endereço
     * @param IDFornecedor Setar uma informação de valor inteiro do IDFornecedor
     * de endereço
     * @param quantEndereco Setar uma informação de valor inteiro da quantidade
     * de Endereço
     * @param login Setar uma informação de valor String do login da pessoa e/ou
     * cliente e/ou fornecedor
     * @param CEP Setar uma informação de valor String do CEP
     * @param Numero Setar uma informação de valor inteiro do número do endereço
     * @param Complemento Setar uma informação de valor String do complemento de
     * endereço
     *
     */
    public static void preencherCampos(int IDEndereco, int IDPessoa, int IDCliente, int IDFornecedor, int quantEndereco, int Numero, String login, String CEP, String Complemento) {
        setID(IDEndereco);
        Pessoa.setIdpessoa(IDPessoa);
        Cliente.setIdCliente(IDCliente);
        Fornecedor.setIdFornecedor(IDFornecedor);
        Pessoa.setLogin(login);
        setCep(CEP);
        setNumero(Numero);
        setQuantEndereco(quantEndereco);
        setComplemento(Complemento);
        ObterEnderecodeCEP(getCep());
    }
    //Inicio do SETS e GETS

    /**
     *
     * Este Método Retornar uma informação de valor String do nome da Tabela de
     * endereço
     *
     * @return Retornar uma informação de valor String do nome da Tabela de
     * endereço
     */
    public static String getTABELA() {
        return TABELA;
    }

    /**
     *
     * Este Método Retornar uma informação de valor inteiro do ID de endereço
     *
     * @return Retornar uma informação de valor inteiro do ID de endereço
     */
    public static int getID() {
        return ID;
    }

    /**
     *
     * Este Método setar uma informação de valor inteiro do ID de endereço
     *
     * @param ID setar uma informação de valor inteiro do ID de endereço
     */
    public static void setID(int ID) {
        Endereco.ID = ID;
    }

    /**
     *
     * Este Método Retornar uma informação de valor inteiro do IDPessoa de
     * endereço
     *
     * @return Retornar uma informação de valor inteiro do IDPessoa de endereço
     */
    public static int getIDPessoa() {
        return IDPessoa;
    }

    /**
     *
     * Este Método Setar uma informação de valor inteiro do IDPessoa de endereço
     *
     * @param IDPessoa Setar uma informação de valor inteiro do IDPessoa de
     * endereço
     */
    public static void setIDPessoa(int IDPessoa) {
        Endereco.IDPessoa = IDPessoa;
    }

    /**
     *
     * Este Método Retornar uma informação de valor inteiro do IDCliente de
     * endereço
     *
     * @return Retornar uma informação de valor inteiro do IDCliente de endereço
     */
    public static int getIDCliente() {
        return IDCliente;
    }

    /**
     * Este Método setar uma informação de valor inteiro do IDCliente de
     * endereço
     *
     * @param IDCliente setar uma informação de valor inteiro do IDCliente de
     * endereço
     */
    public static void setIDCliente(int IDCliente) {
        Endereco.IDCliente = IDCliente;
    }

    /**
     *
     * Este Método Retornar uma informação de valor inteiro do IDFornecedor de
     * endereço
     *
     * @return Retornar uma informação de valor inteiro do IDFornecedor de
     * endereço
     */
    public static int getIDFornecedor() {
        return IDFornecedor;
    }

    /**
     *
     * Este Método setar uma informação de valor inteiro do IDFornecedor de
     * endereço
     *
     * @param IDFornecedor setar uma informação de valor inteiro do IDFornecedor
     * de endereço
     */
    public static void setIDFornecedor(int IDFornecedor) {
        Endereco.IDFornecedor = IDFornecedor;
    }

    /**
     *
     * Este Método Retornar uma informação de valor inteiro da número de
     * endereço
     *
     * @return Retornar uma informação de valor inteiro da número de endereço
     */
    public static int getNumero() {
        return numero;
    }

    /**
     *
     * Este Método setar uma informação de valor inteiro da número de endereço
     *
     * @param numero setar uma informação de valor inteiro da número de endereço
     */
    public static void setNumero(int numero) {
        Endereco.numero = numero;
    }

    /**
     *
     * Este Método Retornar uma informação de valor inteiro da quantidade de
     * endereço
     *
     * @return Retornar uma informação de valor inteiro da quantidade de
     * endereço
     */
    public static int getQuantEndereco() {
        return quantEndereco;
    }

    /**
     * Este Método setar uma informação de valor inteiro da quantidade de
     * endereço
     *
     * @param quantEndereco setar uma informação de valor inteiro da quantidade
     * de endereço
     */
    public static void setQuantEndereco(int quantEndereco) {
        Endereco.quantEndereco = quantEndereco;
    }

    /**
     *
     * Este Método Retornar uma infornação de valor String no complemento do
     * Endereço
     *
     * @return Retornar uma infornação de valor String no complemento do
     * Endereço
     */
    public static String getComplemento() {
        return complemento;
    }

    /**
     *
     * Este Método setar uma infornação de valor String no complemento do
     * Endereço a
     *
     * @param complemento setar uma infornação de valor String no complemento do
     * Endereço
     */
    public static void setComplemento(String complemento) {
        Endereco.complemento = complemento;
    }
    //FIM do SETS e GETS
}
