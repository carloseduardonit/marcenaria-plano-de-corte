/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marcenaria.utilitario.cep.dados;

import java.sql.*;
import marcenaria.Const.Messagem;
import marcenaria.dado.*;

/**
 *
 * @author Carlos Eduardo dos santos Figueiredo Este Classe e responsavel pela
 * manipulação da entidade CEP confoorme a ao ca
 * @since 21/09/19 -criada
 */
public class CEP {

    private static int ID;
    private static String cidade, logradouro, bairro, cep, estado, tipoLogradouro, aUF;
    public static final String DADO = "Utilitarios";
    private static String complemento = " qd 44", numero;
    private static Connection conexao;
    private static marcenaria.utilitario.cep.enu.UF uf;
    private static marcenaria.utilitario.cep.dados.UF UF;
    private static ResultSet rs;
    private static ResultSetMetaData rsmd;
    private static PreparedStatement pst;
    private static Statement stmt;

    public static void main(String[] args) {
        System.out.println(CEP.ObterUF(cep));

    }

    /**
     * Acessar o banco de daodos
     *
     * @author Carlos Eduardo dos santos Figueiredo
     * @since 21/09/19 -criada
     */
    private static void cep() {
        conexao = ModuloConector.getConecction(DADO);
    }

    public static void criarBasedeDadoCEP() {
        String nomeArquivo = "C:\\Users\\Carlos\\Documents\\NetBeansProjects\\Agil\\src\\agil\\dado\\cep.sql";
        DataBase.importarBackupdataBaseSQL(nomeArquivo, DADO);
    }

    // INICIO acessar a tabela de ESTADO.
    /**
     * Testar Este Metodo Obtem o endereço atraves parametro do cep
     *
     * @author Carlos Eduardo dos santos Figueiredo
     * @param Cep Setar uma informação de valor String do cep do CEP.
     * @since 25/09/19 -criada
     *
     */
    public static void ObterEnderecodeCEP(String Cep) {
        CEP.ObterEnderecodeCEP(CEP.ObterUF(Cep), Cep);
    }

    /**
     * Testar Este Metodo Obtem o endereço atraves parametro do cep
     *
     * @author Carlos Eduardo dos santos Figueiredo
     * @param UF Setar uma informação de valor String do UF do CEP.
     * @param CEP Setar uma informação de valor String do cep do CEP.
     * @since 21/09/19 -criada
     *
     */
    public static void ObterEnderecodeCEP(String UF, String CEP) {
        if (NãoHaCampoVazio(UF, CEP, null, 0)) {
            try {
                String sql = "select  cidade, logradouro, bairro, tp_logradouro from " + UF + " where cep = ?";
                cep();
                pst = conexao.prepareStatement(sql);
                pst.setString(1, CEP);
                rs = pst.executeQuery();
                if (rs.next()) {
                    int i = 1;
                    setCidade(rs.getString(i++));//
                    setLogradouro(rs.getString(i++));//
                    setBairro(rs.getString(i++));//
                    setEstado(UF);
                    setCep(CEP);
                    setTipoLogradouro(rs.getString(i));//
                }
            } catch (SQLException e) {
                Messagem.chamarTela("Obter o Endereço: " + e);
            } finally {
                ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
            }
        }
    }

    /**
     * testar Este Metodo obtem uma informação de valor String da UF atraves do
     * parametro cep do CEP.
     *
     * @author Carlos Eduardo dos santos Figueiredo
     * @param Cep Setar uma informação de valor String do cep do CEP.
     * @return Retornar uma informação de valor String da UF.
     * @since 25/09/19 -criada
     *
     */
    public static String ObterUF(String Cep) {
        String UF = "", sql = "select uf from uf where cep1 = ? and cep2 = ?", cep5;
        if (Cep == null) {
            Messagem.chamarTela(" Valor não informado !!!! ");
        } else {
            if (NãoHaCampoVazio(null, Cep, null, 0)) {
                try {
                    cep5 = Cep.substring(0, 5);
                    cep();
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, cep5);
                    pst.setString(2, cep5);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        UF = rs.getString(1);
                    }
                } catch (NullPointerException npe) {
                    Messagem.chamarTela("Obter o UF: " + npe);
                } catch (SQLException e) {
                    Messagem.chamarTela("Obter o UF: " + e);
                } finally {
                    ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
                }
            }
        }
        return UF;
    }

    /**
     * Testar Este Metodo Retornar Obtenção uma informação de valor String do
     * Estado conforme os parametro cep.
     *
     * @author Carlos Eduardo dos santos Figueiredo
     * @param Cep Setar uma informação de valor String do cep do CEP.
     * @return Retornar Obtenção uma informação de valor String do Estado
     * conforme os parametro cep.
     * @since 25/09/19 -criada
     *
     */
    public static String ObterEstadodeCEP(String Cep) {
        String UFa = "";
        if (NãoHaCampoVazio(null, Cep, null, 0)) {
            if (UF.eEsteUF(Cep)) {
                String sql = "select nome from " + DADO + ".uf where uf = ?";
                try {
                    cep();
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, UF.getaUF());
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        UFa = rs.getString(1);
                    }
                } catch (SQLException e) {
                    Messagem.chamarTela("Obter o UF: " + e);
                } finally {
                    ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
                }
            }
        }
        return UFa;
    }

    /**
     * Testar Este Metodo Retornar uma informação de valor String do endereço
     * conforme os parametro cep, conplemento e numero.
     *
     * @author Carlos Eduardo dos santos Figueiredo
     * @param cep Setar uma informação de valor String do cep do CEP.
     * @param complemento Setar uma informação de valor String do complemento do
     * CEP.
     * @param numero Setar uma informação de valor inteiro do numero do CEP.
     * @return Retornar uma informação de valor String do endereço conforme os
     * parametro cep, complemento e numero.
     * @since 25/09/19 -criada
     *
     */
    public static String EnderecoToString(String cep, String complemento, int numero) {
        String uf = CEP.ObterUF(cep);
        return EnderecoToString(uf, cep, complemento, numero);
    }

    /**
     * Testar Este Metodo Retornar uma informação de valor String do endereço
     * conforme os parametro UF, cep, conplemento e numero.
     *
     * @author Carlos Eduardo dos santos Figueiredo
     * @param UF Setar uma informação de valor String do UF do CEP.
     * @param cep Setar uma informação de valor String do cep do CEP.
     * @param complemento Setar uma informação de valor String do complemento do
     * CEP.
     * @param numero Setar uma informação de valor inteiro do numero do CEP.
     * @return Retornar uma informação de valor String do endereço conforme os
     * parametro UF, cep, conplemento e numero.
     * @since 25/09/19 -criada
     *
     */
    public static String EnderecoToString(String UF, String cep, String complemento, int numero) {
        String endereço = "";
        CEP.ObterEnderecodeCEP(UF, cep);
        if (!getCep().isEmpty()) {
            endereço += "CEP: " + getCep();
        }
        if (!getTipoLogradouro().isEmpty() && !getLogradouro().isEmpty()) {
            endereço += "\nEndereço: " + getTipoLogradouro() + " " + getLogradouro();
        }
        if (numero > 0) {
            endereço += " " + numero;
        }
        if (!complemento.isEmpty()) {
            endereço += " " + complemento;
        }
        if (!getBairro().isEmpty()) {
            endereço += "\nBairro: " + getBairro();
        }
        if (!getCidade().isEmpty()) {
            endereço += "\nCidade: " + getCidade();
        }
        if (!getEstado().isEmpty()) {
            endereço += "\nEstado: " + getEstado();
        }
        return endereço;
    }

    /**
     * OK Este Metodo Retornar um array de informação de valor String dos campos
     * vazios.
     *
     * @param UF Setar uma informação de valor String do UF do CEP.
     * @param cep Setar uma informação de valor String do cep do CEP.
     * @param complemento Setar uma informação de valor String do complemento do
     * CEP.
     * @param numero Setar uma informação de valor inteiro do numero do CEP.
     * @return Retornar um array de informação de valor String dos campos
     * vazios.
     */
    public static String[] campoVazio(String UF, String cep, String complemento, int numero) {
        String[] campo = new String[4];
        int i = 0;
        if (UF.isEmpty()) {
            campo[i++] = "Estado";
        }
        if (cep.isEmpty()) {
            campo[i++] = "n° CEP";
        }
        if (complemento.isEmpty()) {
            campo[i++] = "complemento";
        }
        if (String.valueOf(numero).isEmpty() && numero < 0) {
            campo[i++] = "Numero";
        }
        return campo;
    }

    /**
     * OK Este Metodo retorna um valor boolean se Não ha Campos Vazios: TRUE: se
     * não tiver Campos Vazios FALSE: se tiver Campos Vazios
     *
     * @param UF Setar uma informação de valor String do UF do CEP.
     * @param cep Setar uma informação de valor String do cep do CEP.
     * @param complemento Setar uma informação de valor String do complemento do
     * CEP.
     * @param numero Setar uma informação de valor inteiro do numero do CEP.
     * @return Retornar uma informação de valor boolean se não tem campo vazio
     * TRUE: se tiver Campos Vazios FALSE: se não tiver Campos Vazios
     */
    public static boolean HaCampoVazio(String UF, String cep, String complemento, int numero) {
        if (UF == null) {
            UF = "0";
        }
        if (cep == null) {
            cep = "0";
        }
        if (complemento == null) {
            complemento = "0";
        }
        boolean resp = UF.isEmpty() || cep.isEmpty() || complemento.isEmpty() || (String.valueOf(numero).isEmpty() && numero < 0);
        return resp;
    }

    /**
     * OK Este Metodo retorna um valor boolean se Não ha Campos Vazios: TRUE: se
     * não tiver Campos Vazios FALSE: se tiver Campos Vazios e mostra o uma tela
     * se informação com os campos vazios.
     *
     *
     * @param UF Setar uma informação de valor String do UF do CEP.
     * @param cep Setar uma informação de valor String do cep do CEP.
     * @param complemento Setar uma informação de valor String do complemento do
     * CEP.
     * @param numero Setar uma informação de valor inteiro do numero do CEP.
     * @return Retornar uma informação de valor boolean se não tem campo vazio
     * TRUE: se não tiver Campos Vazios FALSE: se tiver Campos Vazios
     */
    public static boolean NãoHaCampoVazio(String UF, String cep, String complemento, int numero) {
        boolean resp = HaCampoVazio(UF, cep, complemento, numero);
        if (resp) {
            Messagem.chamarTela(Messagem.VAZIO(campoVazio(UF, cep, complemento, numero)));
        }
        return !resp;
    }

    // inicio da SETS E GETS
    /**
     * Este metodo Retornar uma informação de valor inteiro do ID do CEP.
     *
     * @author Carlos Eduardo dos santos Figueiredo
     * @return Retornar uma informação de valor inteiro do ID do CEP.
     * @since 21/09/19 -criada
     *
     */
    public static int getID() {
        return ID;
    }

    /**
     * Este metodo Setar uma informação de valor inteiro do ID do CEP.
     *
     * @author Carlos Eduardo dos santos Figueiredo
     * @param ID Setar uma informação de valor inteiro do ID do CEP.
     * @since 21/09/19 -criada
     *
     */
    public static void setID(int ID) {
        CEP.ID = ID;
    }

    /**
     * Este metodo Retornar uma informação de valor String do Cidade do CEP.
     *
     * @author Carlos Eduardo dos santos Figueiredo
     * @return Retornar uma informação de valor String do Cidade do CEP.
     * @since 21/09/19 -criada
     *
     */
    public static String getCidade() {
        return cidade;
    }

    /**
     * Este metodo Setar uma informação de valor String do Cidade do CEP.
     *
     * @author Carlos Eduardo dos santos Figueiredo
     * @param cidade Setar uma informação de valor String do Cidade do CEP.
     * @since 21/09/19 -criada
     *
     */
    public static void setCidade(String cidade) {
        CEP.cidade = cidade;
    }

    /**
     * Este metodo Retornar uma informação de valor String do logradouro do CEP.
     *
     * @author Carlos Eduardo dos santos Figueiredo
     * @return Retornar uma informação de valor String do logradouro do CEP.
     * @since 21/09/19 -criada
     *
     */
    public static String getLogradouro() {
        return logradouro;
    }

    /**
     * Este metodo Setar uma informação de valor String do logradouro do CEP.
     *
     * @author Carlos Eduardo dos santos Figueiredo
     * @param logradouro Setar uma informação de valor String do logradouro do
     * CEP.
     * @since 21/09/19 -criada
     *
     */
    public static void setLogradouro(String logradouro) {
        CEP.logradouro = logradouro;
    }

    /**
     * Este metodo Retornar uma informação de valor String do Bairro do CEP.
     *
     * @author Carlos Eduardo dos santos Figueiredo
     * @return Retornar uma informação de valor String do Bairro do CEP.
     * @since 21/09/19 -criada
     *
     */
    public static String getBairro() {
        return bairro;
    }

    /**
     * Este metodo Setar uma informação de valor String do Bairro do CEP.
     *
     * @author Carlos Eduardo dos santos Figueiredo
     * @param bairro Setar uma informação de valor String do Bairro do CEP.
     * @since 21/09/19 -criada
     *
     */
    public static void setBairro(String bairro) {
        CEP.bairro = bairro;
    }

    /**
     * Este Metodo Retornar uma informação de valor String do n° CEP do CEP.
     *
     * @author Carlos Eduardo dos santos Figueiredo
     * @return Retornar uma informação de valor String do n° CEP do CEP.
     * @since 21/09/19 -criada
     *
     */
    public static String getCep() {
        return cep;
    }

    /**
     * Este Metodo Setar uma informação de valor String do n° CEP do CEP.
     *
     * @author Carlos Eduardo dos santos Figueiredo
     * @param cep Setar uma informação de valor String do n° CEP do CEP.
     * @since 21/09/19 -criada
     *
     */
    public static void setCep(String cep) {
        CEP.cep = cep;
    }

    /**
     * Este Metodo Retornar uma informação de valor String do Estado do CEP.
     *
     * @author Carlos Eduardo dos santos Figueiredo
     * @return Retornar uma informação de valor String do Estado do CEP.
     * @since 21/09/19 -criada
     *
     */
    public static String getEstado() {
        return estado;
    }

    /**
     * Este Metodo Setar uma informação de valor String do Estado do CEP.
     *
     * @author Carlos Eduardo dos santos Figueiredo
     * @param estado Setar uma informação de valor String do Estado do CEP.
     * @since 21/09/19 -criada
     *
     */
    public static void setEstado(String estado) {
        CEP.estado = estado;
    }

    /**
     * Este Metodo Retornar uma informação de valor String do Tipo de logradouro
     * do CEP.
     *
     * @author Carlos Eduardo dos santos Figueiredo
     * @return Retornar uma informação de valor String do Tipo de logradouro do
     * CEP.
     * @since 21/09/19 -criada
     *
     */
    public static String getTipoLogradouro() {
        return tipoLogradouro;
    }

    /**
     * Este Metodo Setar uma informação de valor String do Tipo de logradouro do
     * CEP.
     *
     * @author Carlos Eduardo dos santos Figueiredo
     * @param tipoLogradouro Setar uma informação de valor String do Tipo de
     * logradouro do CEP.
     * @since 21/09/19 -criada
     *
     */
    public static void setTipoLogradouro(String tipoLogradouro) {
        CEP.tipoLogradouro = tipoLogradouro;
    }
// fim da SETS E GETS
}
