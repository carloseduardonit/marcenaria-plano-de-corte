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
    private static String cidade, logradouro, bairro, cep, estado, tipoLogradouro;
    private static final String Dado = "teste1";
    private static String complemento = " qd 44", numero;
    private static Connection conexao;
    private static UF uf;
    private static ResultSet rs;
    private static ResultSetMetaData rsmd;
    private static PreparedStatement pst;
    private static Statement stmt;

    public static void main(String[] args) {
        setCep("24752-640");
        System.out.println(CEP.eEsteUF(uf.RJ.toString().toLowerCase(), getCep()));

        /**
         * UF[] ess = UF.values(); for (int i = 1; i <= UF.values().length; i++)
         * { System.out.println(ess[i].getNomeEstado(i)); } *
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

    /**
     * @author Carlos Eduardo dos santos Figueiredo
     * @since 21/09/19 -criada
     *
     */
    private static int ObterCEP1deUF(String UF) {
        int cepLocal = 0;
        try {
            //esta ok
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
        return cepLocal;
    }

    /**
     * @author Carlos Eduardo dos santos Figueiredo
     * @since 21/09/19 -criada
     *
     */
    private static int ObterCEP2deUF(String UF) {
        int cepLocal = 0;
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
        return cepLocal;
    }

    /**
     * @author Carlos Eduardo dos santos Figueiredo
     * @param Cep
     * @since 25/09/19 -criada
     *
     */
    public static void ObterEnderecodeCEP(String Cep) {
        CEP.ObterEnderecodeCEP(CEP.ObterUF(Cep), Cep);
    }

    /**
     * @author Carlos Eduardo dos santos Figueiredo
     * @param UF
     * @param CEP
     * @since 21/09/19 -criada
     *
     */
    public static void ObterEnderecodeCEP(String UF, String CEP) {
        try {
            String sql = "select id, cidade, logradouro, bairro, tp_logradouro from " + UF + " where cep = ?";
            cep();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, CEP);
            rs = pst.executeQuery();
            if (rs.next()) {
                int i = 1;
                setID(rs.getInt(i++));
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

    /**
     * @author Carlos Eduardo dos santos Figueiredo
     * @param Cep
     * @return
     * @since 25/09/19 -criada
     *
     */
    public static String ObterUF(String Cep) {
        String UF = "", sql = "select uf from uf where cep1 >= ? and cep2 <= ?", cep5 = Cep.substring(0, 5);
        try {
            cep();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, cep5);
            pst.setString(2, cep5);
            rs = pst.executeQuery();
            if (rs.next()) {
                UF = rs.getString(1);
            }
        } catch (SQLException e) {
            Messagem.chamarTela("Obter o UF: " + e);
        } finally {
            ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
        }
        return UF;
    }

    /**
     * @author Carlos Eduardo dos santos Figueiredo
     * @param UF
     * @param Cep
     * @return
     * @since 25/09/19 -criada
     *
     */
    public static Boolean eEsteUF(String Cep) {

        return false;
    }

    /**
     * @author Carlos Eduardo dos santos Figueiredo
     * @param UF
     * @param Cep
     * @return
     * @since 25/09/19 -criada
     *
     */
    public static Boolean eEsteUF(String UF, String Cep) {
        String cep5 = Cep.substring(0, 5);
        //System.out.println(ObterCEP1deUF(UF.toLowerCase()) + " >= " + cep5 + " <= " + ObterCEP2deUF(UF.toLowerCase()));
        return Integer.parseInt(cep5) >= ObterCEP1deUF(UF.toLowerCase()) && Integer.parseInt(cep5) <= ObterCEP2deUF(UF.toLowerCase());
    }

    /**
     * @author Carlos Eduardo dos santos Figueiredo
     * @param Cep
     * @return
     * @since 25/09/19 -criada
     *
     */
    public static String ObterEstadodeCEP(String Cep) {
        String UF = "";
        if (CEP.eEsteUF(Cep)) {
            String sql = "select nome from uf where uf = ?";
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
        return UF;
    }

    public static String EnderecoToString( String cep, String complemento, int numero) {
          return EnderecoToString(ObterUF(cep), cep, complemento, numero);
      }
    /**
     * @author Carlos Eduardo dos santos Figueiredo
     * @param UF
     * @param cep
     * @param complemento
     * @param numero
     * @return
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
    // SETS E GETS

    /**
     * @author Carlos Eduardo dos santos Figueiredo
     * @return
     * @since 21/09/19 -criada
     *
     */
    public static int getID() {
        return ID;
    }

    /**
     * @author Carlos Eduardo dos santos Figueiredo
     * @param ID
     * @since 21/09/19 -criada
     *
     */
    public static void setID(int ID) {
        CEP.ID = ID;
    }

    /**
     * @author Carlos Eduardo dos santos Figueiredo
     * @return
     * @since 21/09/19 -criada
     *
     */
    public static String getCidade() {
        return cidade;
    }

    /**
     * @author Carlos Eduardo dos santos Figueiredo
     * @param cidade
     * @since 21/09/19 -criada
     *
     */
    public static void setCidade(String cidade) {
        CEP.cidade = cidade;
    }

    /**
     * @author Carlos Eduardo dos santos Figueiredo
     * @return
     * @since 21/09/19 -criada
     *
     */
    public static String getLogradouro() {
        return logradouro;
    }

    /**
     * @author Carlos Eduardo dos santos Figueiredo
     * @param logradouro
     * @since 21/09/19 -criada
     *
     */
    public static void setLogradouro(String logradouro) {
        CEP.logradouro = logradouro;
    }

    /**
     * @author Carlos Eduardo dos santos Figueiredo
     * @return
     * @since 21/09/19 -criada
     *
     */
    public static String getBairro() {
        return bairro;
    }

    /**
     * @author Carlos Eduardo dos santos Figueiredo
     * @param bairro
     * @since 21/09/19 -criada
     *
     */
    public static void setBairro(String bairro) {
        CEP.bairro = bairro;
    }

    /**
     * @author Carlos Eduardo dos santos Figueiredo
     * @return
     * @since 21/09/19 -criada
     *
     */
    public static String getCep() {
        return cep;
    }

    /**
     * @author Carlos Eduardo dos santos Figueiredo
     * @param cep
     * @since 21/09/19 -criada
     *
     */
    public static void setCep(String cep) {
        CEP.cep = cep;
    }

    /**
     * @author Carlos Eduardo dos santos Figueiredo
     * @return
     * @since 21/09/19 -criada
     *
     */
    public static String getEstado() {
        return estado;
    }

    /**
     * @author Carlos Eduardo dos santos Figueiredo
     * @param estado
     * @since 21/09/19 -criada
     *
     */
    public static void setEstado(String estado) {
        CEP.estado = estado;
    }

    /**
     * @author Carlos Eduardo dos santos Figueiredo
     * @return
     * @since 21/09/19 -criada
     *
     */
    public static String getTipoLogradouro() {
        return tipoLogradouro;
    }

    /**
     * @author Carlos Eduardo dos santos Figueiredo
     * @param tipoLogradouro
     * @since 21/09/19 -criada
     *
     */
    public static void setTipoLogradouro(String tipoLogradouro) {
        CEP.tipoLogradouro = tipoLogradouro;
    }

}
