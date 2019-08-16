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
import marcenaria.material.Chapa;

/**
 * 16/06/2019
 *
 * @author Carlos Eduardo dos Santos Figueiredo
 */
public class Fornecedor extends Pessoa {

    private static int idFornecedor;
    private static String CNPJ, tipoPessoa, docum;
    private static final String TABELA = Fornecedor.class.getSimpleName();
    private static Connection conexao;
    private static PreparedStatement pst;
    private static ResultSet rs;
    private static ResultSetMetaData rsmd;
    private static Statement stmt;

    /**
     *
     */
    private static void fornecedor() {
        conexao = ModuloConector.getConecction();
    }

    /**
     * ok Este metodo faz a exclução da informação na Tabela
     */
    public static void criarFornecedor() {
        String sql = "create table if not exists " + Fornecedor.getTABELA() + "(id" + Pessoa.getTABELA() + " int not null unique, " + "login varchar(15) not  null unique, "
                + "id" + Fornecedor.getTABELA() + " int  auto_increment primary key, "
                + "docum varchar(14) not null unique, " + "foreign key (login) references "
                + Pessoa.getTABELA().toLowerCase() + "(login)," + "foreign key (id" + Pessoa.getTABELA()
                + ") references " + Pessoa.getTABELA().toLowerCase() + "(id" + Pessoa.getTABELA() + "))";
        if (ModuloConector.VerificarNaoExistirTabela(Pessoa.getTABELA())) {
            Pessoa.criarPessoa();
        }
        ModuloConector.criarTabela(sql, Fornecedor.getTABELA());
    }

    /**
     * ok Este metodo faz a exclução da informação na Tabela
     */
    public static void deletarFornecedor() {
        if (!ModuloConector.VerificarNaoExistirTabela(Chapa.getTABELA())) {
            Chapa.deletadaChapa();
            deletarFornecedor();
        }
        ModuloConector.deletarTabela(Fornecedor.getTABELA());
    }

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
    public static void adicionarFornecedor(String logFornecedor, String senFornecedor, String consenFornecedor, String tipoPessoa, String nomeFornecedor, String documFornecedor) {
        int idCliente = Cliente.obterIdPessoatoCliente(logFornecedor);
        Pessoa.adicionarPessoa(logFornecedor, senFornecedor, consenFornecedor, tipoPessoa, nomeFornecedor);
        int idPessoa = Pessoa.obterIdPessoa(logFornecedor);
        if (!existeroFornecedor(logFornecedor)) {
            if (!logFornecedor.isEmpty() && !senFornecedor.isEmpty() & !consenFornecedor.isEmpty() && !tipoPessoa.isEmpty() && !nomeFornecedor.isEmpty() && !documFornecedor.isEmpty()) {
                try {
                    fornecedor();
                    String sql = "insert into " + Fornecedor.getTABELA().toLowerCase() + " (id" + Pessoa.getTABELA() + ", login, docum )  values (?,?,?)";
                    if (idCliente > 0) {
                        Cliente.pesquisarCliente(logFornecedor);
                        if (Cliente.getLogin().equalsIgnoreCase(logFornecedor) && Cliente.getDocum().equalsIgnoreCase(documFornecedor)) {
                            pst = conexao.prepareStatement(sql);
                            pst.setInt(1, idPessoa);
                            pst.setString(2, Cliente.getLogin());
                            pst.setString(3, Cliente.getDocum());
                        } else {
                            int res = JOptionPane.showConfirmDialog(null, "docunento salvo no Sistema: " + Cliente.getDocum() + "\ndocumento digitado:" + documFornecedor, Cliente.getTABELA(), JOptionPane.OK_CANCEL_OPTION);
                            if (res == JOptionPane.OK_OPTION) {
                                pst = conexao.prepareStatement(sql);
                                pst.setInt(1, idPessoa);
                                pst.setString(2, Cliente.getLogin());
                                pst.setString(3, Cliente.getDocum());
                            }
                        }
                    } else {
                        pst = conexao.prepareStatement(sql);
                        pst.setInt(1, idPessoa);
                        pst.setString(2, logFornecedor);
                        pst.setString(3, documFornecedor);
                    }
                    int adicionado = pst.executeUpdate();
                    if (adicionado > 0) {
                        Messagem.chamarTela(Messagem.ADICIONADO(Fornecedor.exibirFornecedortoString(logFornecedor)));
                    }
                } catch (SQLException e) {
                    Messagem.chamarTela(Fornecedor.getTABELA() + " Adicionar: " + e);
                } finally {
                    ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
                }
            } else {
                Messagem.chamarTela(Messagem.VAZIO(Pessoa.CampoVazio(logFornecedor, senFornecedor, consenFornecedor, tipoPessoa, nomeFornecedor, documFornecedor)));
            }
        } else {
            Messagem.chamarTela(Fornecedor.getTABELA() + " " + logFornecedor + " Já existe !!!");
        }
    }

    /**
     * TA MONTANDO FALTA TESTA
     *
     * @param logFornecedor Setar uma informação do tipo String da Tabela
     * Fornecedor no Login Fornecedor
     * @param senFornecedor Setar uma informação do tipo String da Tabela
     * Fornecedor no Senha Fornecedor
     * @param conSenFornecedor Setar uma informação do tipo String da Tabela
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
    public static void editarFornecedor(String logFornecedor, String senFornecedor, String conSenFornecedor, String tipoPessoa, String nomeFornecedor, String documFornecedor) {
        if (Fornecedor.existeroFornecedor(logFornecedor)) {
            if (!logFornecedor.isEmpty() && !senFornecedor.isEmpty()
                    && !conSenFornecedor.isEmpty() && !tipoPessoa.isEmpty()
                    && !nomeFornecedor.isEmpty() && !documFornecedor.isEmpty()) {
                if (VerificaDocumento(documFornecedor, tipoPessoa)) {
                    try {
                        Pessoa.setIdpessoa(Pessoa.obterIdPessoa(logFornecedor));
                        Pessoa.editarPessoa(logFornecedor, senFornecedor, conSenFornecedor, tipoPessoa, nomeFornecedor);
                        Fornecedor.setIdFornecedor(Fornecedor.obterIdPessoatoFornecedor(logFornecedor));
                        String sql = "update " + Fornecedor.getTABELA().toLowerCase() + " set docum = ? where id" + Pessoa.getTABELA() + " = ? or id" + Fornecedor.getTABELA() + " = ?", mens = Fornecedor.exibirFornecedortoString(logFornecedor), mens1;
                        fornecedor();
                        pst = conexao.prepareStatement(sql);
                        pst.setString(1, documFornecedor);
                        pst.setInt(2, Pessoa.getIdpessoa());
                        pst.setInt(3, Fornecedor.getIdFornecedor());
                        int editada = pst.executeUpdate();
                        if (editada > 0) {
                            mens1 = Fornecedor.exibirFornecedortoString(logFornecedor);
                            Messagem.chamarTela(Messagem.EDITADO(" Antigo " + mens + "\n Novo " + mens1));
                        }
                    } catch (SQLException e) {
                        Messagem.chamarTela(Fornecedor.getTABELA() + " Editar: " + e);
                    } finally {
                        ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
                    }
                } else {
                    Messagem.chamarTela(Pessoa.txtVerificaDocumento(documFornecedor, tipoPessoa));
                }
            } else {
                Messagem.chamarTela(Messagem.VAZIO(
                        CampoVazio(logFornecedor, senFornecedor, conSenFornecedor, tipoPessoa, nomeFornecedor, documFornecedor)));
            }
        } else {
            Messagem.chamarTela(Fornecedor.getTABELA() + " " + logFornecedor + " Não existe !!!");
        }
    }

    /**
     * TA MONTANDO FALTA TESTA
     *
     * @param logFornecedor Setar uma informação do tipo String da Tabela
     * Fornecedor no Login Fornecedor
     */
    public static void excluirFornecedor(String logFornecedor) {
        excluirFornecedor(logFornecedor, true);
    }

    /**
     * TA MONTANDO FALTA TESTA
     *
     * @param logFornecedor Setar uma informação do tipo String da Tabela
     * Fornecedor no Login Fornecedor
     * @param Mensagem
     */
    public static void excluirFornecedor(String logFornecedor, boolean Mensagem) {
        if (Fornecedor.existeroFornecedor(logFornecedor)) {
            int excluir = -2, excluido = excluir;
            String sql = "delete from " + Fornecedor.getTABELA() + " where id" + Pessoa.getTABELA() + " = ?  or id" + Fornecedor.getTABELA() + " = ?", s = Fornecedor.exibirFornecedortoString(logFornecedor), p = Pessoa.exibirPessoatoString(logFornecedor);
            try {
                Pessoa.setIdpessoa(Pessoa.obterIdPessoa(logFornecedor));
                Fornecedor.setIdFornecedor(Fornecedor.obterIdPessoatoFornecedor(logFornecedor));
                if (Mensagem) {
                    excluir = JOptionPane.showConfirmDialog(null, s, Fornecedor.getTABELA(), JOptionPane.OK_CANCEL_OPTION);
                } else {
                    excluir = JOptionPane.OK_OPTION;
                }
                if (excluir == JOptionPane.OK_OPTION) {
                    fornecedor();
                    pst = conexao.prepareStatement(sql);
                    pst.setInt(1, Pessoa.getIdpessoa());
                    pst.setInt(2, Fornecedor.getIdFornecedor());
                    excluido = pst.executeUpdate();
                    if (excluido >= 0 && Mensagem || !Mensagem) {
                        Messagem.chamarTela(Messagem.EXCLUIDO(s));
                    }
                }
            } catch (SQLException e) {
                Messagem.chamarTela(Fornecedor.getTABELA() + " Excluir: " + e);
            } finally {
                ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
                if (excluido >= 0 && Mensagem) {
                    int pes = JOptionPane.showConfirmDialog(null, p + "\nDeseja excluido todos os dados", Pessoa.getTABELA(), JOptionPane.OK_CANCEL_OPTION);
                    if (pes == JOptionPane.OK_OPTION) {
                        Pessoa.excluirPessoa(logFornecedor, false);
                    }
                }
            }
        } else {
            Messagem.chamarTela(Fornecedor.getTABELA() + " " + logFornecedor + " Não existe !!!");
        }
    }

    /**
     * ok Este Metodo Exibir uma tela como a informação do banco de dados
     * obtendo o resultado mediante a uma pesquisa na Tabela conforme a
     * informação obtida no parametro <b>Tabela</b> e do parametro
     * <b>logPessoa</b>. Apos verificar qual tabela sera afetada
     *
     * @param logFornecedor Setar uma informação do tipo String da Tabela Pessoa
     * no Login Pessoa
     */
    public static void exibirFornecedor(String logFornecedor) {
        Messagem.chamarTela(Fornecedor.exibirFornecedortoString(logFornecedor) + "\n");
    }

    /**
     * OK Este Metodo Exibir uma tela como a informação do banco de dados
     * obtendo o resultado mediante a uma pesquisa na Tabela conforme a
     * informação obtida no parametro <b>Tabela</b> e do parametro
     * <b>logPessoa</b>. Apos verificar qual tabela sera afetada
     *
     * @param logFornecedor Setar uma informação do tipo String da Tabela Pessoa
     * no Login Pessoa
     * @return Retornar umma informação de valor String
     */
    public static String exibirFornecedortoString(String logFornecedor) {
        String sql;
        Fornecedor.pesquisarFornecedor(logFornecedor);
        if (Fornecedor.getIdpessoa() == 0 && Fornecedor.getIdFornecedor() == 0 && Fornecedor.getLogin().isEmpty() && Fornecedor.getNome().isEmpty() && Fornecedor.getSenha().isEmpty() && Fornecedor.getTipoPessoa().isEmpty()) {
            sql = "Não ha Fornecedor cadastrada!!!";
        } else {
            sql = "Do Cadastro Fornecedor \n";
            if (Fornecedor.getIdpessoa() > 0) {
                sql += "\nId da Pessoa: " + Fornecedor.getIdpessoa();
            }
            if (Fornecedor.getIdFornecedor() > 0) {
                sql += "\nId do Fornecedor: " + Fornecedor.getIdFornecedor();
            }
            if (!Fornecedor.getLogin().isEmpty()) {
                sql += "\nLogin da Pessoa: " + Fornecedor.getLogin();
            }
            if (!Fornecedor.getNome().isEmpty()) {
                sql += "\nNome da Pessoa: " + Fornecedor.getNome();
            }
            if (!Fornecedor.getSenha().isEmpty()) {
                sql += "\nSenha da Pessoa: " + Fornecedor.getSenha();
            }
            if (!Fornecedor.getTipoPessoa().isEmpty()) {
                sql += "\nTipo de pessoa: " + Fornecedor.getTipoPessoa();
            }
            if (Fornecedor.getTipoPessoa().equalsIgnoreCase("pf")) {
                sql += "\nnumero do CPF: " + Fornecedor.getDocum();
            } else if (Fornecedor.getTipoPessoa().equalsIgnoreCase("pj")) {
                sql += "\nnumero do CNPJ: " + Fornecedor.getDocum();
            }
        }
        return sql;
    }

    /**
     * OK Este metodo Pesquisa na tabela Fornecedor do banco de dados
     *
     * @param logFornecedor Setar uma informação do tipo String da Tabela
     * Fornecedor no Login Fornecedor
     */
    public static void pesquisarFornecedor(String logFornecedor) {
        try {
            Fornecedor.setIdpessoa(Fornecedor.obterIdPessoa(logFornecedor));
            Fornecedor.setIdFornecedor(Fornecedor.obterIdPessoatoFornecedor(logFornecedor));
            String sql = "select P.login, P.senha, P.tipoPessoa, P.nome, F.docum from " + Pessoa.getTABELA() + " as P, "
                    + Fornecedor.getTABELA() + " as F where F.id" + Pessoa.getTABELA() + " = ? or P.id" + Pessoa.getTABELA() + " = ?";
            if (!logFornecedor.isEmpty()) {
                fornecedor();
                pst = conexao.prepareStatement(sql);
                pst.setInt(1, Fornecedor.getIdFornecedor());
                pst.setInt(2, Fornecedor.getIdpessoa());
                rs = pst.executeQuery();
                if (rs.next()) {
                    Fornecedor.setLogin(rs.getString(1));
                    Fornecedor.setSenha(rs.getString(2));
                    Fornecedor.setConfSenha(Fornecedor.getSenha());
                    Fornecedor.setTipoPessoa(rs.getString(3));
                    Fornecedor.setNome(rs.getString(4));
                    Fornecedor.setDocum(rs.getString(5));
                }
            } else {
                Messagem.chamarTela(Messagem.VAZIO("login "));
            }
        } catch (SQLException e) {
            Messagem.chamarTela(Fornecedor.getTABELA() + " Pesquisar: " + e);
        } finally {
            ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
        }
    }

    /**
     * Este metodo pesquisa na Tabela atraves informação do parametro
     * <b>Tabela</b> e do parametro
     * <b>logPessoa</b> retornando uma informação do do banco de dado tipo
     * inteiro do ID da pessoa
     *
     * @param logFornecedor Setar uma informação do tipo String da Tabela
     * Fornecedor no Login Fornecedor
     * @return Retornar o id da Tabela Fornecedor atraves do Login.
     */
    public static int obterIdPessoatoFornecedor(String logFornecedor) {
        return Pessoa.obterIdPessoa(Fornecedor.getTABELA(), logFornecedor);
    }

    /**
     * @param logFornecedor
     * @return
     */
    public static Boolean existeroFornecedor(String logFornecedor) {
        return obterIdPessoatoFornecedor(logFornecedor) > 0;
    }

    // Sets e Gets
    /**
     * Este metodo Retornar uma informação do tipo inteiro da Tabela Fornecedor
     * no ID Fornecedor
     *
     * @return Retornar uma informação do tipo inteiro da Tabela Fornecedor no
     * ID Fornecedor
     */
    public static int getIdFornecedor() {
        return idFornecedor;
    }

    /**
     * Este metodo Setar Informação de valor inteiro do ID do fornecedor
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
     * Este Metodo Retornar uma informação do tipo String da Tabela Fornecedor
     * com nome da tabela
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
