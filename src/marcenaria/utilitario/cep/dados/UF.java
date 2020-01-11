/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marcenaria.utilitario.cep.dados;

import java.sql.*;
import marcenaria.Const.Messagem;
import marcenaria.dado.ModuloConector;

/**
 *
 * @author Carlos
 */
public class UF {

    private static Connection conexao;
    private static ResultSet rs;
    private static ResultSetMetaData rsmd;
    private static PreparedStatement pst;
    private static Statement stmt;
    private static String uf, nomeEstado, cep1Digito5, cep2Digito5;
    private static marcenaria.utilitario.cep.enu.UF ufa;

    private static void uf() {
        conexao = ModuloConector.getConecction("teste1");
    }

    /**
     * OK Este Metodo obtem o CEP 1 da tabela UF atraves do paramento UF
     *
     * @author Carlos Eduardo dos santos Figueiredo
     * @param UF Setar uma informação de valor String do UF do CEP.
     * @return Retornar obtebção o CEP 1 da tabela UF atraves do paramento UF
     * @since 21/09/19 -criada
     *
     */
    public static int ObterCEP1deUF(String UF) {
        int cepLocal = 0;
        if (NãoHaCampoVazio(UF)) {
            try {
                String sql = "select cep1 from uf where uf = ?";
                uf();
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
     * @author Carlos Eduardo dos santos Figueiredo
     * @param UF Setar uma informação de valor String do UF do CEP.
     * @author Carlos Eduardo dos santos Figueiredo
     * @return Retornar obtebção o CEP 2 da tabela UF atraves do paramento UF
     * @since 21/09/19 -criada
     *
     */
    public static int ObterCEP2deUF(String UF) {
        int cepLocal = 0;
        if (NãoHaCampoVazio(UF)) {
            try {//esta Ok
                String sql = "select cep2 from uf where uf = ?";
                uf();
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

    /**
     * Este Metodo obtem o Estado da tabela UF atraves do paramento UF
     * @author Carlos Eduardo dos santos Figueiredo
     * @param UF Setar uma informação de valor String do UF do CEP.
     * @author Carlos Eduardo dos santos Figueiredo
     * @return
     */
    public static String ObterEstadodeUF(String UF) {
        String estado = "", sql;
        try {
            uf();
            sql = "select nome from uf where uf = ?";
            pst = conexao.prepareStatement(sql);
            pst.setString(1, UF);
            rs = pst.executeQuery();
            if (rs.next()) {
                estado = rs.getString(1);
            }
        } catch (SQLException e) {
            Messagem.chamarTela("" + e);
        } finally {
            ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
        }
        return estado;
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
        for (marcenaria.utilitario.cep.enu.UF a : marcenaria.utilitario.cep.enu.UF.values()) {
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
     * @param uf Setar uma informação de valor String do UF do CEP.
     * @return
     */
    public static boolean NãoHaCampoVazio(String uf) {
        boolean a = UF.HaCampoVazio(uf);
        if (a) {
            Messagem.chamarTela(Messagem.VAZIO(campoVazio(uf)));
        }
        return !a;
    }

    /**
     * @author Carlos Eduardo dos santos Figueiredo
     * @param UF Setar uma informação de valor String do UF do CEP.
     * @return
     */
    public static boolean HaCampoVazio(String UF) {
        boolean a = UF.isEmpty();

        return a;
    }

    /**
     * @author Carlos Eduardo dos santos Figueiredo
     * @param uf
     * @return
     */
    public static String campoVazio(String uf) {
        String a = "";
        if (uf.isEmpty()) {
            a = "UF";
        }
        return a;
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
        return uf;
    }

    /**
     * Este Método Setar uma informação de valor String da UF.
     *
     * @author Carlos Eduardo dos santos Figueiredo
     * @param aUF Setar uma informação de valor String da UF.
     * @since 10/11/19 -criada
     *
     */
    public static void setaUF(String aUF) {
        uf = aUF;
    }

    /**
     * Este Método Retornar uma informação de valor String da UF.
     *
     * @author Carlos Eduardo dos santos Figueiredo
     * @return Retornar uma informação de valor String da UF.
     * @since 10/11/19 -criada
     */
    public static String getUf() {
        return uf;
    }

    /**
     * @author Carlos Eduardo dos santos Figueiredo
     * @param uf
     */
    public static void setUf(String uf) {
        UF.uf = uf;
    }

    /**
     * @author Carlos Eduardo dos santos Figueiredo
     * @return
     */
    public static String getNomeEstado() {
        return nomeEstado;
    }

    /**
     * @author Carlos Eduardo dos santos Figueiredo
     * @param nomeEstado
     */
    public static void setNomeEstado(String nomeEstado) {
        UF.nomeEstado = nomeEstado;
    }

    /**
     * @author Carlos Eduardo dos santos Figueiredo
     * @return
     */
    public static String getCep1Digito5() {
        return cep1Digito5;
    }

    /**
     * @author Carlos Eduardo dos santos Figueiredo
     * @param cep1Digito5
     */
    public static void setCep1Digito5(String cep1Digito5) {
        UF.cep1Digito5 = cep1Digito5;
    }

    /**
     * @author Carlos Eduardo dos santos Figueiredo
     * @return
     */
    public static String getCep2Digito5() {
        return cep2Digito5;
    }

    /**
     * @author Carlos Eduardo dos santos Figueiredo
     * @param cep2Digito5
     */
    public static void setCep2Digito5(String cep2Digito5) {
        UF.cep2Digito5 = cep2Digito5;
    }

    /**
     * @author Carlos Eduardo dos santos Figueiredo
     * @return
     */
    public static marcenaria.utilitario.cep.enu.UF getUfa() {
        return ufa;
    }

    /**
     * @author Carlos Eduardo dos santos Figueiredo
     * @param ufa
     */
    public static void setUfa(marcenaria.utilitario.cep.enu.UF ufa) {
        UF.ufa = ufa;
    }

}
