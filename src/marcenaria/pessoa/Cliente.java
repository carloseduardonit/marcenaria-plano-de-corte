/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marcenaria.pessoa;

import java.sql.*;
import javax.swing.JOptionPane;
import marcenaria.Const.Messagem;
import marcenaria.dado.ModuloConector;

/**
 *
 * @author Carlos
 */
public class Cliente extends Pessoa {

    public static void main(String[] args) {
        Cliente.setLogin("Carlos");
        Cliente.setSenha("39568eu1");
        Cliente.setTipoPessoa("pf");
        Cliente.setNome(Cliente.getLogin());
        Cliente.setDocum("12345678901");
        adicionarCliente(Cliente.getLogin(), Cliente.getSenha(), Cliente.getSenha(), Cliente.getTipoPessoa(), Cliente.getNome(), Cliente.getDocum());
    }
    private static final String TABELA = Cliente.class.getSimpleName();
    private static int cpf, idCliente;
    private static String tipoPessoa, docum;
    private static Connection conexao;
    private static PreparedStatement pst;
    private static ResultSet rs;
    private static ResultSetMetaData rsmd;
    private static Statement stmt;

    private static void cliente() {
        conexao = ModuloConector.getConecction();
    }

    /**
     * TA MONTANDO FALTA TESTA Este Metodo
     */
    public static void criarCliente() {
        String sql = "create table if not exists " + Cliente.getTABELA() + "(id" + Pessoa.getTABELA() + " int not null unique, " + "login varchar(15) not  null unique, "
                + "id" + Cliente.getTABELA() + " int  auto_increment primary key, "
                + "docum varchar(14) not null unique, " + "foreign key (login) references "
                + Pessoa.getTABELA().toLowerCase() + "(login)," + "foreign key (id" + Pessoa.getTABELA()
                + ") references " + Pessoa.getTABELA().toLowerCase() + "(id" + Pessoa.getTABELA() + "))";
        if (!ModuloConector.VerificarNaoExistirTabela(Pessoa.getTABELA())) {
            Pessoa.criarPessoa();
        }
        ModuloConector.criarTabela(sql, Cliente.getTABELA());
    }

    /**
     * TA MONTANDO FALTA TESTA Este Metodo
     */
    public static void deletarCliente() {
        ModuloConector.deletarTabela(getTABELA());
    }

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
     * no tipo de Cliente, sendo que so podera utilizar <b>PF</b>
     * ou <b>PJ</b>
     * @param nomeCliente Setar uma informação do tipo String da Tabela Cliente
     * no Nome Cliente
     * @param documCliente Setar uma informação do tipo String da Tabela Cliente
     * no documento do Cliente,sendo quanto o tipoPessoa se<b>PF</b> so poderá
     * anexa a infornação for de 11 digito, senão <b>PJ</b> so poderá anexa a
     * infornação for de 14 digito
     */
    public static void adicionarCliente(String logCliente, String senCliente, String conSenCliente, String tipoPessoa,
            String nomeCliente, String documCliente) {
        int idPessoac = Pessoa.obterIdPessoa(logCliente);
        try {
            cliente();
            String sql = "insert into " + Cliente.getTABELA().toLowerCase() + " (id" + Pessoa.getTABELA() + ", login, docum) values (?,?,?)";
            if (idPessoac > 0) {
                Fornecedor.pesquisarFornecedor(logCliente);
                pst = conexao.prepareStatement(sql);
                pst.setInt(1, idPessoac);
                pst.setString(2, Fornecedor.getLogin());
                pst.setString(3, Fornecedor.getDocum());
                // ver melhoria
            } else {
                Pessoa.adicionarPessoa(logCliente, senCliente, conSenCliente, tipoPessoa, nomeCliente, false);
                pst = conexao.prepareStatement(sql);
                pst.setInt(1, idPessoac);
                pst.setString(2, logCliente);
                pst.setString(3, documCliente);
            }
            int adicionado = pst.executeUpdate();
            if (adicionado > 0) {
                Messagem.chamarTela(Messagem.ADICIONADO(Cliente.exibirClientetoString(logCliente)));
            }
        } catch (SQLException e) {
            Messagem.chamarTela(Cliente.getTABELA() + " Adicionar: " + e);
        } finally {
            ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
        }
    }

    /**
     * TA MONTANDO FALTA TESTA Este Metodo
     *
     * @param nlogCliente Setar uma informação do tipo String da Tabela Cliente
     * no novo Login Cliente
     * @param logCliente Setar uma informação do tipo String da Tabela Cliente
     * no Login Cliente
     * @param senCliente Setar uma informação do tipo String da Tabela Cliente
     * no Senha Cliente
     * @param conSenCliente Setar uma informação do tipo String da Tabela
     * Cliente na Confirmação da senha Cliente
     * @param tipoPessoa Setar uma informação do tipo String da Tabela Cliente
     * no tipo de Cliente, sendo que so podera utilizar <b>PF</b>
     * ou <b>PJ</b>
     * @param nomeCliente Setar uma informação do tipo String da Tabela Cliente
     * no Nome Cliente
     * @param documCliente Setar uma informação do tipo String da Tabela Cliente
     * no documento do Cliente,sendo quanto o tipoPessoa se<b>PF</b> so poderá
     * anexa a infornação for de 11 digito, senão <b>PJ</b> so poderá anexa a
     * infornação for de 14 digito
     */
    public static void editarCliente(String nlogCliente, String logCliente, String senCliente, String conSenCliente,
            String tipoPessoa, String nomeCliente, String documCliente) {
        try {
            if (!nlogCliente.isEmpty() && !logCliente.isEmpty() && !senCliente.isEmpty()
                    && !conSenCliente.isEmpty() && !tipoPessoa.isEmpty() && !nomeCliente.isEmpty()
                    && !documCliente.isEmpty()) {
                Pessoa.editarPessoa(nlogCliente, logCliente, senCliente, conSenCliente, tipoPessoa, nomeCliente,false);
                Pessoa.setIdpessoa(Pessoa.obterIdPessoa(logCliente));
                Cliente.setIdCliente(Cliente.obterIdCliente(logCliente));
                cliente();
                String sql = "uptade " + Cliente.getTABELA() + " set login = ?, docum = ? where id" + Pessoa.getTABELA() + " = ? or id" + Cliente.getTABELA() + " = ?", mens = Cliente.exibirClientetoString(logCliente), mens1;
                pst = conexao.prepareStatement(sql);
                pst.setString(1, nlogCliente);
                pst.setString(2, documCliente);
                pst.setInt(3, Pessoa.getIdpessoa());
                pst.setInt(4, Cliente.getIdCliente());
                int editada = pst.executeUpdate();
                if (editada == 0) {
                    mens1 = Cliente.exibirClientetoString(nlogCliente);
                    Messagem.chamarTela(Messagem.EDITADO(" Antigo " + mens + "\n Novo " + mens1));
                }
            }else{
                Messagem.chamarTela(Messagem.VAZIO(
                        CampoVazio( logCliente, senCliente, conSenCliente, tipoPessoa, nomeCliente, documCliente)));
            }
        } catch (SQLException e) {
            Messagem.chamarTela(Cliente.getTABELA() + " Editar: " + e);
        } finally {
            ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
        }
    }

    /**
     * TA MONTANDO FALTA TESTA Este Metodo
     *
     * @param logCliente Setar uma informação do tipo String da Tabela Cliente
     * no Login Cliente
     */
    public static void excluirCliente(String logCliente) {
        try {
            cliente();
            Pessoa.setIdpessoa(Pessoa.obterIdPessoa(logCliente));
            Cliente.setIdCliente(Cliente.obterIdCliente(logCliente));
            String sql = "delete from " + Cliente.getTABELA() + " where id" + Pessoa.getTABELA() + " = ?  or id" + Cliente.getTABELA() + " = ?", s = Cliente.exibirClientetoString(logCliente);
            pst.setInt(1, Pessoa.getIdpessoa());
            pst.setInt(2, Cliente.getIdCliente());
            int excluir = JOptionPane.showConfirmDialog(null, s, Cliente.getTABELA(), JOptionPane.OK_CANCEL_OPTION);
            if (excluir == JOptionPane.OK_OPTION) {
                int excluido = pst.executeUpdate(sql);
                System.out.println("" + excluido);
                if (excluido >= 0) {
                    Messagem.chamarTela(Messagem.EXCLUIDO(s));
                    int pes = JOptionPane.showConfirmDialog(null, "Deseja excluido todos os dados", sql,
                            JOptionPane.OK_CANCEL_OPTION);
                    if (pes == JOptionPane.OK_OPTION) {
                        Pessoa.excluirPessoa(logCliente);
                    }
                }
            }
        } catch (Exception e) {
            Messagem.chamarTela(Cliente.getTABELA() + " Excluir: " + e);
        } finally {
            ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
        }
    }

    /**
     * ok Este Metodo Exibir uma tela como a informação do banco de dados
     * obtendo o resultado mediante a uma pesquisa na Tabela conforme a
     * informação obtida no parametro <b>Tabela</b> e do parametro
     * <b>logPessoa</b>. Apos verificar qual tabela sera afetada
     *
     *
     * @param logPessoa Setar uma informação do tipo String da Tabela Pessoa no
     * Login Pessoa
     */
    public static void exibirCliente(String logPessoa) {
        Messagem.chamarTela(Cliente.exibirClientetoString(logPessoa) + "\n");
    }

    /**
     * OK Este Metodo Exibir uma tela como a informação do banco de dados
     * obtendo o resultado mediante a uma pesquisa na Tabela conforme a
     * informação obtida no parametro <b>Tabela</b> e do parametro
     * <b>logPessoa</b>. Apos verificar qual tabela sera afetada
     *
     * @param logPessoa Setar uma informação do tipo String da Tabela Pessoa no
     * Login Pessoa
     * @return Retornar umma informação de valor String
     */
    public static String exibirClientetoString(String logPessoa) {
        String sql;
        Cliente.pesquisarCliente(logPessoa);
        if (Cliente.getIdpessoa() == 0 && Cliente.getIdCliente() == 0 && Cliente.getLogin().isEmpty() && Cliente.getNome().isEmpty() && Cliente.getSenha().isEmpty() && Cliente.getTipoPessoa().isEmpty()) {
            sql = "Não ha Cliente cadastrada!!!";
        } else {
            sql = " Cadastro Cliente\n";
            if (Cliente.getIdpessoa() > 0) {
                sql += "\nId da Pessoa: " + Cliente.getIdpessoa();
            }
            if (Cliente.getIdCliente() > 0) {
                sql += "\nId do Cliente: " + Cliente.getIdCliente();
            }
            if (!Cliente.getLogin().isEmpty()) {
                sql += "\nLogin da Pessoa: " + Cliente.getLogin();
            }
            if (!Cliente.getNome().isEmpty()) {
                sql += "\nNome da Pessoa: " + Cliente.getNome();
            }
            if (!Cliente.getSenha().isEmpty()) {
                sql += "\nSenha da Pessoa: " + Cliente.getSenha();
            }
            if (!Cliente.getTipoPessoa().isEmpty()) {
                sql += "\nTipo de pessoa: " + Cliente.getTipoPessoa();
            }
            if (Cliente.getTipoPessoa().equalsIgnoreCase("pf")) {
                sql += "\nnumero do CPF: " + Cliente.getDocum();
            } else if (Cliente.getTipoPessoa().equalsIgnoreCase("pj")) {
                sql += "\nnumero do CNPJ: " + Cliente.getDocum();
            }
        }
        return sql;
    }

    /**
     * TA MONTANDO FALTA TESTA Este Metodo
     *
     * @param logCliente Setar uma informação do tipo String da Tabela Cliente
     * no Login Cliente
     */
    public static void pesquisarCliente(String logCliente) {
        try {
            cliente();
            Cliente.setIdpessoa(Pessoa.obterIdPessoa(logCliente));
            Cliente.setIdCliente(Cliente.obterIdCliente(logCliente));
            String sql = "select P.login, P.senha, P.tipoPessoa, P.nome, C.docum from " + Pessoa.getTABELA() + " as P, "
                    + Cliente.getTABELA() + " as C where  C.id" + Pessoa.getTABELA() + " = ? or P.id" + Pessoa.getTABELA() + " = ?";
            if (!logCliente.isEmpty()) {
                pst = conexao.prepareStatement(sql);
                pst.setInt(1, Cliente.getIdCliente());
                pst.setInt(2, Cliente.getIdpessoa());
                rs = pst.executeQuery();
                if (rs.next()) {
                    Cliente.setLogin(rs.getString(1));
                    Cliente.setSenha(rs.getString(2));
                    Cliente.setConfSenha(Cliente.getSenha());
                    Cliente.setTipoPessoa(rs.getString(3));
                    Cliente.setNome(rs.getString(4));
                    Cliente.setDocum(rs.getString(5));
                }
            } else {
                Messagem.chamarTela(Messagem.VAZIO("login "));
            }
        } catch (SQLException e) {
            Messagem.chamarTela(Cliente.getTABELA() + " Pesquisar: " + e);
        } finally {
            ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
        }
    }

    /**
     * Este Metodo
     *
     * @param logCliente Setar uma informação do tipo String da Tabela Cliente
     * no Login Cliente
     * @return Retornar o id da Tabela Cliente atraves do Login.
     */
    public static int obterIdCliente(String logCliente) {
        return Pessoa.obterIdPessoa(Cliente.getTABELA(), logCliente);
    }
    // Sets e Gets

    /**
     * Este Metodo Retornar uma informação de valor inteiro do CPF
     *
     * @return Retorn
     */
    public static int getCpf() {
        return cpf;
    }

    /**
     * Este Metodo Setar uma informação de valor inteiro do CPF
     *
     * @param cpf Setar uma informação de valor inteiro do CPF
     */
    public static void setCpf(int cpf) {
        Cliente.cpf = cpf;
    }

    /**
     * Este Metodo Retornar uma informação do tipo inteiro da Tabela Cliente no
     * ID Cliente
     *
     * @return Retornar uma informação do tipo inteiro da Tabela Cliente no ID
     * Cliente
     */
    public static int getIdCliente() {
        return idCliente;
    }

    /**
     * Este Metodo Setar uma informação do tipo inteiro da Tabela Cliente no ID
     * Cliente
     *
     * @param idCliente Setar uma informação do tipo inteiro da Tabela Cliente
     * no ID Cliente
     */
    public static void setIdCliente(int idCliente) {
        Cliente.idCliente = idCliente;
    }

    /**
     * Este Metodo Retornar uma informação do tipo String da Tabela Cliente no
     * tipo de pessoa
     *
     * @return Retornar uma informação do tipo String da Tabela Cliente no tipo
     * de pessoa
     */
    public static String getTipoPessoa() {
        return tipoPessoa;
    }

    /**
     * Este Metodo setar uma informação do tipo String da Tabela Cliente no tipo
     * de pessoa
     *
     * @param tipoPessoa setar uma informação do tipo String da Tabela Cliente
     * no tipo de pessoa
     */
    public static void setTipoPessoa(String tipoPessoa) {
        Cliente.tipoPessoa = tipoPessoa;
    }

    /**
     * Este Metodo Retornar um valor de String da Tabela Cliente
     *
     * @return Retornar um valor de String da Tabela Cliente
     */
    public static String getTABELA() {
        return TABELA;
    }

    /**
     * Este Metodo Retornar uma informação de valor String do documento do
     * Cliente.
     *
     * @return Retornar uma informação de valor String do documento do Cliente.
     */
    public static String getDocum() {
        return docum;
    }

    /**
     * Este Metodo Setar uma informação de valor String do documento do Cliente.
     *
     * @param docum Setar uma informação de valor String do documento do
     * Cliente.
     */
    public static void setDocum(String docum) {
        Cliente.docum = docum;
    }

}
