/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marcenaria.pessoa.cliente;

import java.sql.*;
import marcenaria.Const.Messagem;
import marcenaria.dado.ModuloConector;
import marcenaria.pessoa.Cliente;

/**
 * @since 21/07/2019
 * @author Carlos Eduardo dos Santos Figueiredo
 */
public class Projeto {

    public static void main(String[] args) {
        criarProjeto();
        deletarProjeto();
        setIdCliente(1);
        setNomeProjeto("Banheiro");
        setDescricaoProjeto("projeto ");
        adicionarProjeto(getNomeProjeto(), getDescricaoProjeto(), getIdCliente(), getPrecoProjeto());
    }
    private static Connection conexao;
    private static ResultSet rs;
    private static ResultSetMetaData rsmd;
    private static PreparedStatement pst;
    private static Statement stmt;
    private static final String TABELA = Projeto.class.getSimpleName();
    private static int idProjeto, idCliente;
    private static double precoProjeto;
    private static String nomeProjeto, descricaoProjeto;

    /**
     * Este Metodo Faz a conexão do banco de dados
     */
    public static void Proj() {
        conexao = ModuloConector.getConecction();
    }
//Incio da Manipulação do Banco de dados.
    /**
     * Este metodo faz a criação a tabela de nome Projeto
     *
     * @since 21/07/2019
     * @author Carlos Eduardo dos Santos Figueiredo
     * @version 1.0 criação da classe
     * @version 1.1 edição na intrução sql acrescentado["nome"+getTABELA()+ "
     * varchar(20)," + "descricao"+getTABELA()+ " varchar(20),"]
     */
    public static void criarProjeto() {
      if (ModuloConector.VerificarNaoExistirTabela(Cliente.getTABELA())){
          Cliente.criarCliente();
      }
            String sql = "create table if not exists " + Projeto.getTABELA()
                    + "( id" + Projeto.getTABELA() + " int auto_increment primary key, "
                    + "nome" + Projeto.getTABELA() + " varchar(20),"
                    + "descricao" + Projeto.getTABELA() + " varchar(20),"
                    + "id" + Cliente.getTABELA() + " int not null, "
                    + "preco" + Projeto.getTABELA() + " double default 0, "
                    + "foreign key (id" + Cliente.getTABELA() + ") references " + Cliente.getTABELA().toLowerCase() + " (id" + Cliente.getTABELA() + ") )";
            ModuloConector.criarTabela(sql, Projeto.getTABELA());
        
    }

    /**
     * Este metodo faz a deletação a tabela de nome Projeto
     *
     * @since 21/07/2019
     * @author Carlos Eduardo dos Santos Figueiredo
     * @version 1.0
     */
    public static void deletarProjeto() {
        try {
            Proj();
            String sql = "drop table if exists " + getTABELA();
            Messagem.deletadaTabela(getTABELA());
            if (Messagem.getDeleta() == 0) {
                stmt = conexao.createStatement();
                int deletar = stmt.executeUpdate(sql);
                if (deletar == 0) {
                    Messagem.chamarTela(Messagem.tabelaDeletada(getTABELA()));
                    ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
                } else {
                    ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
                }
            }
        } catch (SQLException e) {
            Messagem.chamarTela(e);
            ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
        }
    }
//Fim da Manipulação do Banco de dados.
//Incio da Manipulação da Tabela do Banco de dados.
    /**
     * Este metodo faz a inserção na tabela de nome Projeto
     *
     * @param nomeProjeto Setar uma informação de valor
     * @param descricaoProjeto Setar uma informação de valor
     * @param idCliente Setar uma informação de valor Inteiro do id de Cliente
     * @param precoProjeto Setar uma informação de valor Double do preço do
     * projeto
     * @since 22/07/2019
     * @author Carlos Eduardo dos Santos Figueiredo
     * @version 1.0
     */
    public static void adicionarProjeto(String nomeProjeto, String descricaoProjeto, int idCliente, double precoProjeto) {
        try {
            Proj();
            String sql = "insert into " + getTABELA()
                    + " (nome" + getTABELA() + ", descricao" + getTABELA() + ", id" + Cliente.getTABELA() + ", preco" + getTABELA()
                    + ") values (?,?,?,?)";
            if (!String.valueOf(idCliente).isEmpty()) {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, nomeProjeto);
                pst.setString(2, descricaoProjeto);
                pst.setInt(3, idCliente);
                pst.setDouble(4, precoProjeto);
                int adicionar = pst.executeUpdate();
                if (adicionar == 0) {
                    Messagem.chamarTela(Messagem.ADICIONADO(getTABELA()));
                    ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
                }
            }
        } catch (Exception e) {
            Messagem.chamarTela(e);
            ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
        }
    }

    /**
     * Este metodo faz a editação na tabela de nome Projeto
     *
     * @param idProjeto Setar uma informação de valor Inteiro do id do Projeto
     * @param nomeProjeto Setar uma informação de valor
     * @param descricaoProjeto Setar uma informação de valor
     * @param idCliente Setar uma informação de valor Inteiro do id de Cliente
     * @param precoProjeto Setar uma informação de valor Double do preço do
     * projeto
     * @since 23/07/2019
     * @author Carlos Eduardo dos Santos Figueiredo
     * @version 1.0
     */
    public static void editarProjeto(int idProjeto, String nomeProjeto, String descricaoProjeto, int idCliente, double precoProjeto) {
        try {
            Proj();
            String sql = "uptade " + getTABELA() + " set  nome" + getTABELA()
                    + " = ?, descricao" + getTABELA() + " = ?, id" + Cliente.getTABELA()
                    + " = ?, preco" + getTABELA() + " where id" + getTABELA() + " = ?";
            if (!String.valueOf(idCliente).isEmpty() && String.valueOf(idCliente).isEmpty() && String.valueOf(precoProjeto).isEmpty()) {
                pst = conexao.prepareStatement(sql);
                pst.setInt(1, idProjeto);
                pst.setInt(2, idCliente);
                pst.setDouble(3, precoProjeto);
                int editar = pst.executeUpdate();
                if (editar == 0) {
                    Messagem.chamarTela(Messagem.EDITADO(getTABELA()));
                }
            } else {
                ModuloConector.fecharConexao(conexao);
            }
        } catch (Exception e) {
            Messagem.chamarTela(e);
            ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
        }
    }

    /**
     * Este metodo faz a exclução da tabela de nome Projeto
     *
     * @param ou Setar uma informação de valor
     * @param idProjeto Setar uma informação de valor Inteiro do id do Projeto
     * @param nomeProjeto Setar uma informação de valor
     * @param descricaoProjeto Setar uma informação de valor
     * @param idCliente Setar uma informação de valor Inteiro do id de Cliente
     * @param precoProjeto Setar uma informação de valor Double do preço do
     * projeto
     * @since 23/07/2019
     * @author Carlos Eduardo dos Santos Figueiredo
     * @version 1.0
     */
    public static void excluirProjeto(boolean ou, int idProjeto, String nomeProjeto, String descricaoProjeto, int idCliente, double precoProjeto) {
        try {
            String sql = "", a = "";
            if (ou) {
                a = "or";
            } else {
                a = "and";
            }
            sql = " delete from " + getTABELA()
                    + " where id" + getTABELA() + " = ?" + a
                    + " id" + Cliente.getTABELA() + " = ?," + a
                    + " preco" + getTABELA() + " = ?";
            if (String.valueOf(idProjeto).isEmpty() && String.valueOf(idCliente).isEmpty() && String.valueOf(precoProjeto).isEmpty()) {
                Proj();
                pst = conexao.prepareStatement(sql);
                pst.setInt(1, idProjeto);
                pst.setInt(2, idCliente);
                pst.setDouble(3, precoProjeto);
                int excluir = pst.executeUpdate();
                if (excluir == 0) {
                    Messagem.chamarTela(Messagem.EXCLUIDO(getTABELA()));
                    ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
                }
            }
        } catch (SQLException e) {
            Messagem.chamarTela(e);
            ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
        }
    }

    /**
     * Este metodo criar a tabela de nome Projeto
     *
     * @param ou Setar uma informação de valor
     * @param idProjeto Setar uma informação de valor Inteiro do id do Projeto
     * @param nomeProjeto Setar uma informação de valor
     * @param descricaoProjeto Setar uma informação de valor
     * @param idCliente Setar uma informação de valor Inteiro do id de Cliente
     * @param precoProjeto Setar uma informação de valor Double do preço do
     * projeto
     * @since 21/07/2019
     * @author Carlos Eduardo dos Santos Figueiredo
     * @version 1.0
     */
    public static void pesquisarProjeto(boolean ou, int idProjeto, String nomeProjeto, String descricaoProjeto, int idCliente, double precoProjeto) {
        try {
            String sql = "", a = "";
            if (ou) {
                a = "or";
            } else {
                a = "and";
            }
            sql = "select * from " + getTABELA()
                    + " where  id" + getTABELA() + " = ? " + a
                    + " id" + Cliente.getTABELA() + " = ? " + a
                    + " preco" + getTABELA() + " = ?";
            if (String.valueOf(idProjeto).isEmpty() && String.valueOf(idCliente).isEmpty() && String.valueOf(precoProjeto).isEmpty()) {
                pst = conexao.prepareStatement(sql);
                pst.setInt(1, idProjeto);
                pst.setInt(2, idCliente);
                pst.setDouble(3, precoProjeto);
                rs = pst.executeQuery();
                if (rs.next()) {
                    setIdProjeto(rs.getInt(1));
                    setIdCliente(rs.getInt(2));
                    setPrecoProjeto(rs.getDouble(3));
                    ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
                }
            }
        } catch (SQLException e) {
            Messagem.chamarTela(e);
            ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
        }
    }
//Fim da Manipulação da Tabela do Banco de dados.
    public static String sqlString(boolean ou, int tipo, int idProjeto, int idCliente, double precoProjeto) {
        String sql = "", a;
        if (ou) {
            a = "or";
        } else {
            a = "and";
        }
        switch (tipo) {
            case 1:
                sql = "";
                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            default:
                break;
        }
        return sql;
    }
//Incio dos Gets e Sets 

    /**
     * Este metodo criar a tabela de nome Projeto
     *
     * @since 21/07/2019
     * @author Carlos Eduardo dos Santos Figueiredo
     * @version 1.0
     * @return Retornar uma informação de valor String do nome da Tabela Projeto
     */
    public static String getTABELA() {
        return TABELA;
    }

    /**
     * Este metodo Retornar uma informação de valor Inteiro do id do Projeto
     *
     * @since 21/07/2019
     * @author Carlos Eduardo dos Santos Figueiredo
     * @version 1.0
     * @return Retornar uma informação de valor Inteiro do id do Projeto
     */
    public static int getIdProjeto() {
        return idProjeto;
    }

    /**
     * Este metodo Setar uma informação de valor Inteiro do id do Projeto
     *
     * @since 21/07/2019
     * @author Carlos Eduardo dos Santos Figueiredo
     * @version 1.0
     * @param idProjeto Setar uma informação de valor Inteiro do id do Projeto
     */
    public static void setIdProjeto(int idProjeto) {
        Projeto.idProjeto = idProjeto;
    }

    /**
     * Este metodo Retornar uma informação de valor Inteiro do id de Cliente
     *
     * @since 21/07/2019
     * @author Carlos Eduardo dos Santos Figueiredo
     * @version 1.0
     * @return Retornar uma informação de valor Inteiro do id de Cliente
     */
    public static int getIdCliente() {
        return idCliente;
    }

    /**
     * Este metodo Setar uma informação de valor Inteiro do id de Cliente
     *
     * @since 21/07/2019
     * @author Carlos Eduardo dos Santos Figueiredo
     * @version 1.0
     * @param idCliente Setar uma informação de valor Inteiro do id de Cliente
     */
    public static void setIdCliente(int idCliente) {
        Projeto.idCliente = idCliente;
    }

    /**
     * Este metodo Retornar uma informação de valor Double do preço do projeto
     *
     * @since 21/07/2019
     * @author Carlos Eduardo dos Santos Figueiredo
     * @version 1.0
     * @return Retornar uma informação de valor Double do preço do projeto
     */
    public static double getPrecoProjeto() {
        return precoProjeto;
    }

    /**
     * Este metodo Setar uma informação de valor Double do preço do projeto
     *
     * @since 21/07/2019
     * @author Carlos Eduardo dos Santos Figueiredo
     * @version 1.0
     * @param precoProjeto Setar uma informação de valor Double do preço do
     * projeto
     */
    public static void setPrecoProjeto(double precoProjeto) {
        Projeto.precoProjeto = precoProjeto;
    }

    /**
     * Este Metodo Retornar uma informação de valor String do nome do projeto.
     *
     * @since 24/07/2019
     * @author Carlos Eduardo dos Santos Figueiredo
     * @version 1.1
     * @return Retornar uma informação de valor String do nome do projeto.
     */
    public static String getNomeProjeto() {
        return nomeProjeto;
    }

    /**
     * Este Metodo Setar uma informação de valor String do nome do projeto.
     *
     * @since 24/07/2019
     * @author Carlos Eduardo dos Santos Figueiredo
     * @version 1.1
     * @param nomeProjeto Setar uma informação de valor String do nome do
     * projeto.
     */
    public static void setNomeProjeto(String nomeProjeto) {
        Projeto.nomeProjeto = nomeProjeto;
    }

    /**
     * Este Metodo Retornar uma informação de valor String da descriçao do projeto.
     *
     *
     * @since 24/07/2019
     * @author Carlos Eduardo dos Santos Figueiredo
     * @version 1.1
     * @return Retornar uma informação de valor String da descriçao do projeto.
     */
    public static String getDescricaoProjeto() {
        return descricaoProjeto;
    }

    /**
     * Este Metodo Setar uma informação de valor String da descriçao do projeto.
     *
     *
     * @since 24/07/2019
     * @author Carlos Eduardo dos Santos Figueiredo
     * @version 1.1
     * @param descricaoProjeto Setar uma informação de valor String da descriçao
     * do projeto.
     */
    public static void setDescricaoProjeto(String descricaoProjeto) {
        Projeto.descricaoProjeto = descricaoProjeto;
    }
}//Fim dos Gets e Sets 
