/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marcenaria.pessoa.utilitario;

import java.sql.*;
import javax.swing.JOptionPane;
import marcenaria.Const.Messagem;
import marcenaria.dado.ModuloConector;
import marcenaria.pessoa.Cliente;
import marcenaria.pessoa.Fornecedor;
import marcenaria.pessoa.Pessoa;
import marcenaria.utilitario.CEP;

/**
 *
 * @author Carlos
 */
public class Endereco extends CEP {

    public static void main(String[] args) {
        //criarEndereco();
        String login = "1", cep = "1", comp = "1";
        int nun = -1;

        Pessoa.setLogin("eii");
        CEP.setCep("24752-640");
        Endereco.setNumero(125);
        Endereco.setComplemento("Quadra 44 lote 11");
        //adicionarEndereco(Pessoa.getLogin(), getCep(), getNumero(), getComplemento());
        pesquisarEndereco(Pessoa.getLogin(), CEP.getCep(), 11, Endereco.getComplemento(), true);
    }
    private static int ID, IDPessoa, IDCliente, IDFornecedor, numero, quantEndereco;
    private static final String TABELA = Endereco.class.getSimpleName();
    private static String complemento;
    private static Connection conexao;
    private static ResultSet rs;
    private static ResultSetMetaData rsmd;
    private static PreparedStatement pst;
    private static Statement stmt;

    /**a
     *
     */
    private static void endereco() {
        conexao = ModuloConector.getConecction();
    }

    /**
     * ta OK
     */
    public static void criarEndereco() {
        if (ModuloConector.VerificarNaoExistirTabela(Cliente.getTABELA())) {
            Cliente.criarCliente();
        }
        if (ModuloConector.VerificarNaoExistirTabela(Fornecedor.getTABELA())) {
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
        ModuloConector.criarTabela(sql, getTABELA());
    }

    /**
     *
     */
    public static void deletarEndereco() {
        ModuloConector.deletarTabela(Endereco.getTABELA());
    }

    /**
     *
     * @param login
     * @param CEP
     * @param Numero
     * @param Complemento
     */
    public static void adicionarEndereco(String login, String CEP, int Numero, String Complemento) {
        if (NaoHaCampoVazio(login, CEP, Numero, Complemento)) {
            try {
                String sql = "insert into " + Endereco.getTABELA() + "(id" + Pessoa.getTABELA() + ", id" + Cliente.getTABELA() + ", id" + Fornecedor.getTABELA() + ", cep, numero, complemento) values (?,?,?,?,?,?)";
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
                    Messagem.chamarTela(Messagem.ADICIONADO(sql));
                }
            } catch (SQLException e) {
                Messagem.chamarTela("Adicionar endereço: " + e);
            } finally {
                ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
            }
        }
    }

    /**
     *
     * @param login
     * @param CEP
     * @param Numero
     * @param Complemento
     */
    public static void editarEndereco(String login, String CEP, int Numero, String Complemento) {
        if (NaoHaCampoVazio(login, CEP, Numero, Complemento)) {
            try {
                int i = 1;
                String sql = "";
                endereco();
                pst = conexao.prepareStatement(sql);
                pst.setInt(i++, Pessoa.obterIdPessoa(login));
                pst.setInt(i++, Cliente.obterIdClientetoCliente(login));
                pst.setInt(i++, Fornecedor.obterIdFornecedortoFornecedor(login));
                pst.setString(i++, CEP);
                pst.setInt(i++, Numero);
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
     * @param login
     * @param CEP
     * @param Numero
     * @param Complemento
     */
    public static void excluirEndereco(String login, String CEP, int Numero, String Complemento) {
        if (NaoHaCampoVazio(login, CEP, Numero, Complemento)) {
            try {
                int i = 1, excluido, excluir;
                String sql = "";
                endereco();
                pst = conexao.prepareStatement(sql);
                pst.setInt(i++, Pessoa.obterIdPessoa(login));
                pst.setInt(i++, Cliente.obterIdClientetoCliente(login));
                pst.setInt(i++, Fornecedor.obterIdFornecedortoFornecedor(login));
                pst.setString(i++, CEP);
                pst.setInt(i++, Numero);
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
     * @param login
     * @param CEP
     * @param Numero
     * @param Complemento
     * @param ou
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

    public static void exibirEndereco(String login, String cep) {
        Messagem.chamarTela(exibirEnderecoToString(login, cep));
    }

    public static String exibirEnderecoToString(String login, String cep
    ) {
        pesquisarEndereco(login, cep, -1, "", true);
        CEP.EnderecoToString(Endereco.getCep(), Endereco.getComplemento(), Endereco.getNumero());
        return "";
    }

    /**
     * ok
     *
     * @param login
     * @param CEP
     * @param Numero
     * @param Complemento
     * @return
     */
    public static boolean NaoHaCampoVazio(String login, String CEP, int Numero, String Complemento) {
        boolean res = !haCampoVazio(login, CEP, Numero, Complemento, true);
        return res;
    }

    /**
     * ok
     *
     * @param login
     * @param CEP
     * @param Numero
     * @param Complemento
     * @param mensagem
     * @return
     */
    public static boolean haCampoVazio(String login, String CEP, int Numero, String Complemento, boolean mensagem) {
        boolean res = login.isEmpty() || CEP.isEmpty() || String.valueOf(Numero).isEmpty() || Numero < 0 || Complemento.isEmpty();
        if (res && mensagem) {
            Messagem.chamarTela(Messagem.VAZIO(CampoVazio(login, CEP, Numero, Complemento)));
        }
        return res;
    }

    /**
     * @param login
     * @param CEP
     * @param Numero
     * @param Complemento
     * @return
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
     */
    public static void limparCampos() {
        String limpar = "";
        int limp = 0;
        preencherCampos(limp, limp, limp, limp, limp, limpar, limpar, limpar);
    }

    /**
     * @param IDEndereco
     * @param IDPessoa
     * @param IDCliente
     * @param IDFornecedor
     * @param login
     * @param CEP
     * @param Numero
     * @param Complemento
     */
    public static void preencherCampos(int IDEndereco, int IDPessoa, int IDCliente, int IDFornecedor, int Numero, String login, String CEP, String Complemento) {
        Pessoa.setLogin(login);
        setCep(CEP);
        setNumero(Numero);
        setComplemento(Complemento);
    }

    /**
     *
     * @return
     */
    public static String getTABELA() {
        return TABELA;
    }

    /**
     *
     * @return
     */
    public static int getID() {
        return ID;
    }

    /**
     *
     * @param ID
     */
    public static void setID(int ID) {
        Endereco.ID = ID;
    }

    /**
     *
     * @return
     */
    public static int getIDPessoa() {
        return IDPessoa;
    }

    /**
     *
     * @param IDPessoa
     */
    public static void setIDPessoa(int IDPessoa) {
        Endereco.IDPessoa = IDPessoa;
    }

    /**
     *
     * @return
     */
    public static int getIDCliente() {
        return IDCliente;
    }

    /**
     *
     * @param IDCliente
     */
    public static void setIDCliente(int IDCliente) {
        Endereco.IDCliente = IDCliente;
    }

    /**
     *
     * @return
     */
    public static int getIDFornecedor() {
        return IDFornecedor;
    }

    /**
     *
     * @param IDFornecedor
     */
    public static void setIDFornecedor(int IDFornecedor) {
        Endereco.IDFornecedor = IDFornecedor;
    }

    /**
     *
     * @return
     */
    public static int getNumero() {
        return numero;
    }

    /**
     *
     * @param numero
     */
    public static void setNumero(int numero) {
        Endereco.numero = numero;
    }

    /**
     *
     * @return
     */
    public static int getQuantEndereco() {
        return quantEndereco;
    }

    /**
     *
     * @param quantEndereco
     */
    public static void setQuantEndereco(int quantEndereco) {
        Endereco.quantEndereco = quantEndereco;
    }

    /**
     *
     * @return
     */
    public static String getComplemento() {
        return complemento;
    }

    /**
     *
     * @param complemento
     */
    public static void setComplemento(String complemento) {
        Endereco.complemento = complemento;
    }

}
