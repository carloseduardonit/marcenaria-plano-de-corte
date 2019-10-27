/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marcenaria.utilitario;

import java.sql.*;
import marcenaria.Const.Messagem;
import marcenaria.dado.ModuloConector;
import marcenaria.utilitario.cep.UF;

/**
 *
 * @author Carlos Eduardo dos santos Figueiredo
 * @since 21/09/19 -criada
 */
public class CEP {

    private static int ID;
    private static String cidade, logradouro, bairro, cep, estado, tipoLogradouro, aUF;
    private static final String Dado = "teste1";
    private static String complemento = " qd 44", numero;
    private static Connection conexao;
    private static UF uf;
    private static ResultSet rs;
    private static ResultSetMetaData rsmd;
    private static PreparedStatement pst;
    private static Statement stmt;

    public static void main(String[] args) {
        System.out.println(CEP.ObterUF(cep));
        /**
         * for (UF a : UF.values()){ String t = a.name(); System.out.println(t);
         * } *
         */
    }

    /**
     * Acessar o banco de daodos
     *
     * @author Carlos Eduardo dos santos Figueiredo
     * @since 21/09/19 -criada
     */
    private static void cep() {
        conexao = ModuloConector.getConecction(Dado);
    }

    //  INICIO acessa a tabela UF.
    /**
     * OK Este Metodo obtem o CEP 1 da tabela UF atraves do paramento UF
     *
     * @author Carlos Eduardo dos santos Figueiredo
     * @param UF Setar uma informação de valor String do UF do CEP.
     * @since 21/09/19 -criada
     *
     */
    private static int ObterCEP1deUF(String UF) {
        int cepLocal = 0;
        if (NãoHaCampoVazio(UF, null, null, 0)) {
            try {
                String sql = "select cep1 from uf where uf = ?";
                cep();
                pst = conexao.prepareStatement(sql);
                int i = 1;
                pst.setString(i, UF.toLowerCase());
                rs = pst.executeQuery();
                if (rs.next()) {
                    cepLocal = Integer.parseInt(rs.getString(1));
                }
            } catch (NumberFormatException | SQLException e) {
                Messagem.chamarTela("Obter o CEP1 de UF: " + e);
            } finally {
                ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
            }
        }
        return cepLocal;
    }

    /**
     * OK Este Metodo obtem o CEP 2 da tabela UF atraves do paramento UF
     *
     * @param UF Setar uma informação de valor String do UF do CEP.
     * @author Carlos Eduardo dos santos Figueiredo
     * @since 21/09/19 -criada
     *
     */
    private static int ObterCEP2deUF(String UF) {
        int cepLocal = 0;
        if (NãoHaCampoVazio(UF, null, null, 0)) {
            try {//esta Ok
                String sql = "select cep2 from uf where uf = ?";
                cep();
                pst = conexao.prepareStatement(sql);
                int i = 1;
                pst.setString(i, UF);
                rs = pst.executeQuery();
                if (rs.next()) {
                    cepLocal = Integer.parseInt(rs.getString(1));
                }
                return cepLocal;
            } catch (NumberFormatException | SQLException e) {
                Messagem.chamarTela("Obter o CEP2 de UF: " + e);
            } finally {
                ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
            }
        }
        return cepLocal;
    }

    // FIM acessa a tabela UF.
    // INICIO acessar a tabela de ESTADO.
    /**
     * Este Metodo Obtem o endereço atraves parametro do cep
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
     * Este Metodo Obtem o endereço atraves parametro do cep
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
     * Este Metodo obtem uma informação de valor String da UF atraves do
     * parametro cep do CEP.
     *
     * @author Carlos Eduardo dos santos Figueiredo
     * @param Cep Setar uma informação de valor String do cep do CEP.
     * @return Retornar uma informação de valor String da UF.
     * @since 25/09/19 -criada
     *
     */
    public static String ObterUF(String Cep) {
        String UF = "", sql = "select uf from uf where cep1 = ? and cep2 = ?", cep5 ;
        if (Cep == null) {
             Messagem.chamarTela(" Valor não informado !!!! " );
        }else{
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
     * OK Este Metodo Retornar uma informação de valor boolean se este e UF
     * atraves do parametro CEP
     *
     * @author Carlos Eduardo dos santos Figueiredo
     * @param Cep Setar uma informação de valor String do cep do CEP.
     * @return Retornar uma informação de valor boolean se este e UF atraves do
     * parametro CEP
     * @since 25/09/19 -criada
     *
     */
    public static boolean eEsteUF(String Cep) {
        boolean resp = false;
        for (UF a : UF.values()) {
            String t = a.name();
            resp = eEsteUF(t, Cep);
            if (resp) {
                setaUF(t);
                break;
            }
        }
        return resp;
    }

    /**
     * Este Metodo Retornar uma informação de valor boolean se este e UF atraves
     * dos parametros UF e CEP.
     *
     * @author Carlos Eduardo dos santos Figueiredo
     * @param UF Setar uma informação de valor String do UF do CEP.
     * @param Cep Setar uma informação de valor String do cep do CEP.
     * @return Retornar uma informação de valor boolean se este e UF atraves do
     * parametro UF e CEP.
     * @since 25/09/19 -criada
     *
     */
    public static boolean eEsteUF(String UF, String Cep) {
        String cep5 = Cep.substring(0, 5);
        return Integer.parseInt(cep5) >= ObterCEP1deUF(UF.toLowerCase()) && Integer.parseInt(cep5) <= ObterCEP2deUF(UF.toLowerCase());
    }

    /**
     * Este Metodo Retornar Obtenção uma informação de valor String do Estado
     * conforme os parametro cep.
     *
     * @author Carlos Eduardo dos santos Figueiredo
     * @param Cep Setar uma informação de valor String do cep do CEP.
     * @return Retornar Obtenção uma informação de valor String do Estado
     * conforme os parametro cep.
     * @since 25/09/19 -criada
     *
     */
    public static String ObterEstadodeCEP(String Cep) {
        String UF = "";
        if (NãoHaCampoVazio(null, Cep, null, 0)) {
            if (CEP.eEsteUF(Cep)) {
                String sql = "select nome from " + Dado + ".uf where uf = ?";
                try {
                    cep();
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, UF);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        UF = rs.getString(1);
                    }
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
     * Este Metodo Retornar uma informação de valor String do endereço conforme
     * os parametro cep, conplemento e numero.
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
     * Este Metodo Retornar uma informação de valor String do endereço conforme
     * os parametro UF, cep, conplemento e numero.
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
     * OK Este Metodo retorna um valor boolean se Não ha Campos Vazios:
     * <li>
     * <ul><b>TRUE: </b>se não tiver Campos Vazios</ul>
     * <ul><b>FALSE: </b>se tiver Campos Vazios</ul>
     * </li>
     *
     * @param UF Setar uma informação de valor String do UF do CEP.
     * @param cep Setar uma informação de valor String do cep do CEP.
     * @param complemento Setar uma informação de valor String do complemento do
     * CEP.
     * @param numero Setar uma informação de valor inteiro do numero do CEP.
     * @return Retornar uma informação de valor boolean se não tem campo vazio
     * <li>
     * <ul><b>TRUE: </b>se tiver Campos Vazios</ul>
     * <ul><b>FALSE: </b>se não tiver Campos Vazios</ul>
     * </li>
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
     * OK Este Metodo retorna um valor boolean se Não ha Campos Vazios:
     * <li>
     * <ul><b>TRUE: </b>se não tiver Campos Vazios</ul>
     * <ul><b>FALSE: </b>se tiver Campos Vazios e mostra o uma tela se
     * informação com os campos vazios.</ul>
     * </li>
     *
     * @param UF Setar uma informação de valor String do UF do CEP.
     * @param cep Setar uma informação de valor String do cep do CEP.
     * @param complemento Setar uma informação de valor String do complemento do
     * CEP.
     * @param numero Setar uma informação de valor inteiro do numero do CEP.
     * @return Retornar uma informação de valor boolean se não tem campo vazio
     * <li>
     * <ul><b>TRUE: </b>se não tiver Campos Vazios</ul>
     * <ul><b>FALSE: </b>se tiver Campos Vazios</ul>
     * </li>
     */
    public static boolean NãoHaCampoVazio(String UF, String cep, String complemento, int numero) {
        boolean resp = HaCampoVazio(UF, cep, complemento, numero);
        if (resp) {
            Messagem.chamarTela(Messagem.VAZIO(campoVazio(UF, cep, complemento, numero)));
        }
        return !resp;
    }
    // SETS E GETS

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

    /**
     * Este Método Retornar uma informação de valor String da UF.
     *
     * @author Carlos Eduardo dos santos Figueiredo
     * @return Retornar uma informação de valor String da UF.
     * @since 21/09/19 -criada
     *
     */
    public static String getaUF() {
        return aUF;
    }

    /**
     * Este Método Setar uma informação de valor String da UF.
     *
     * @author Carlos Eduardo dos santos Figueiredo
     * @param aUF Setar uma informação de valor String da UF.
     * @since 19/10/19 -criada
     *
     */
    public static void setaUF(String aUF) {
        CEP.aUF = aUF;
    }

}
