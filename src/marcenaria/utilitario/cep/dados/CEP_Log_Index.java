/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marcenaria.utilitario.cep.dados;

import java.sql.*;
import marcenaria.Const.Messagem;
import marcenaria.dado.ModuloConector;
import static marcenaria.utilitario.cep.dados.CEP.DADO;

/**
 *
 * @author Carlos
 */
public class CEP_Log_Index extends CEP {

    private static int id;
    private static String cep5, uf;
    private static final String TABELA = CEP_Log_Index.class.getSimpleName().toUpperCase();
    private static Connection conexao;
    private static ResultSet rs;
    private static ResultSetMetaData rsmd;
    private static PreparedStatement pst;
    private static Statement stmt;

    public static void main(String[] args) {
        System.out.println(paraCep5(0, "AC", true));
        System.out.println(paraID("", "RJ", true));
        System.out.println(paraUF(id, "24752", true));
    }

    /**
     * Este metodo e para agilizar o processo de desenvolvimento dos metodo
     * semelhantes desta classe.
     *
     * @param id Setar uma informação de valor inteiro do ID da tabela
     * Cep_LOG_Index.
     * @param cep5 Setar uma informação de valor String do CEP com 5 digito da
     * tabela Cep_LOG_Index.
     * @param uf Setar uma informação de valor String do UF (Estado) da tabela
     * Cep_LOG_Index.
     * @param ou Setar uma informação de valor booleando para instrução sql da
     * tabela Cep_LOG_Index.
     */
    public static void obter(int id, String cep5, String uf, boolean ou) {
        String sql = "Select id, cep5, uf from " + getTABELA() + " where ", a;
        int i = 1;
        if (ou) {
            a = " or ";
        } else {
            a = "and";
        }
        if (!String.valueOf(id).isEmpty() && id != 0) {
            i++;
            sql = "id =?";
        }
        if (!cep5.isEmpty()) {
            if (i > 1) {
                sql += a;
                i++;
            }
            sql = "cep5 = ?";
        }
        if (!uf.isEmpty()) {
            if (i > 1) {
                sql += a;
                i++;
            }
            sql = "uf = ?";
        }
        i = 1;
        try {
            cepLogIndex();
            pst = conexao.prepareStatement(sql);
            if (!String.valueOf(id).isEmpty() && id != 0) {
                pst.setInt(i++, id);
            }
            if (!cep5.isEmpty()) {
                pst.setString(i++, cep5);
            }
            if (!uf.isEmpty()) {
                pst.setString(i++, uf);
            }
            rs = pst.executeQuery();
            if (rs.next()) {
                setId(rs.getInt(1));
                setCep5(rs.getString(2));
                setUf(rs.getString(3));
            }
        } catch (SQLException e) {
            Messagem.chamarTela(e);
        } finally {
            ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
        }
    }

    //inicio do Acesso ao Banco
    private static void cepLogIndex() {
        conexao = ModuloConector.getConecction(DADO);
    }

    /**
     * Este Metodo e para Retornar uma informação de valor inteiro do ID da
     * tabela Cep_LOG_Index.
     *
     * @param cep5 Setar uma informação de valor String do CEP com 5 digito da
     * tabela Cep_LOG_Index.
     * @param uf Setar uma informação de valor String do UF (Estado) da tabela
     * Cep_LOG_Index.
     * @param ou Setar uma informação de valor booleando para instrução sql da
     * tabela Cep_LOG_Index.
     * @return Retornar uma informação de valor inteiro do ID da tabela
     * Cep_LOG_Index.
     */
    public static int paraID(String cep5, String uf, boolean ou) {
        String sql = "Select id from " + getTABELA() + " where ", a;
        int i = 1, resultado = 0;
        if (NaoHaCampoVazio(0, cep5, uf)) {
            if (ou) {
                a = " or ";
            } else {
                a = " and ";
            }
            if (!cep5.isEmpty()) {
                i++;
                sql = "cep5 = ?";
            }
            if (!uf.isEmpty()) {
                if (i > 1) {
                    sql += a;
                }
                sql += "uf = ?";
            }
            i = 1;
            try {
                cepLogIndex();
                pst = conexao.prepareStatement(sql);
                if (!cep5.isEmpty()) {
                    pst.setString(i++, cep5);
                }
                if (!uf.isEmpty()) {
                    pst.setString(i++, uf);
                }
                rs = pst.executeQuery();
                if (rs.next()) {
                    resultado = rs.getInt(1);
                }
            } catch (SQLException e) {
                Messagem.chamarTela(e);
            } finally {
                ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
            }
        }
        return resultado;
    }

    /**
     * Este Metodo e para Retornar uma informação de valor inteiro do ID da
     * tabela Cep_LOG_Index atraves do parametro CEP com 5 digitos.
     *
     * @param cep5 Setar uma informação de valor String do CEP com 5 digito da
     * tabela Cep_LOG_Index.
     * @return Retornar uma informação de valor inteiro do ID da tabela
     * Cep_LOG_Index.
     */
    public static int deCep5paraID(String cep5) {
        return paraID(cep5, "", true);
    }

    /**
     * Este Metodo e para Retornar uma informação de valor inteiro do ID da
     * tabela Cep_LOG_Index atraves do parametro UF(Estado).
     *
     * @param uf Setar uma informação de valor String do UF (Estado) da tabela
     * Cep_LOG_Index.
     * @return Retornar uma informação de valor inteiro do ID da tabela
     * Cep_LOG_Index.
     */
    public static int deUFparaID(String uf) {
        return paraID("", uf, true);
    }

    /**
     * Este Metodo e para Retornar uma informação de valor String do CEP com 5
     * digito da tabela Cep_LOG_Index.
     *
     * @param id Setar uma informação de valor inteiro do ID da tabela
     * Cep_LOG_Index.
     * @param uf Setar uma informação de valor String do UF (Estado) da tabela
     * Cep_LOG_Index.
     * @param ou Setar uma informação de valor booleando para instrução sql da
     * tabela Cep_LOG_Index.
     * @return Retornar uma informação de valor String do CEP com 5 digito da
     * tabela Cep_LOG_Index.
     */
    public static String paraCep5(int id, String uf, boolean ou) {
        String sql = "Select Cep5 from " + getTABELA() + " where ", a, resultado = "";
        int i = 1;
        if (NaoHaCampoVazio(id, null, uf)) {
            if (ou) {
                a = " or ";
            } else {
                a = " and ";
            }
            if (!String.valueOf(id).isEmpty() && id != 0) {
                i++;
                sql = "id =?";
            }
            if (!uf.isEmpty()) {
                if (i > 1) {
                    sql += a;
                }
                sql += "uf = ?";
            }
            i = 1;
            try {
                cepLogIndex();
                pst = conexao.prepareStatement(sql);
                if (!String.valueOf(id).isEmpty() && id != 0) {
                    pst.setInt(i++, id);
                }
                if (!uf.isEmpty()) {
                    pst.setString(i++, uf);
                }
                rs = pst.executeQuery();
                if (rs.next()) {
                    resultado = rs.getString(1);
                }
            } catch (SQLException e) {
                Messagem.chamarTela(e);
            } finally {
                ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
            }
        }
        return resultado;
    }

    /**
     * Este Metodo e para Retornar uma informação de valor String do CEP com 5
     * digito da tabela Cep_LOG_Index.
     *
     * @param id Setar uma informação de valor inteiro do ID da tabela
     * Cep_LOG_Index.
     * @return Retornar uma informação de valor String do CEP com 5 digito da
     * tabela Cep_LOG_Index.
     */
    public static String deIDparaCep5(int id) {
        return paraCep5(id, "", true);
    }

    /**
     * Este Metodo e para Retornar uma informação de valor String do CEP com 5
     * digito da tabela Cep_LOG_Index.
     *
     * @param uf Setar uma informação de valor String do UF (Estado) da tabela
     * Cep_LOG_Index.
     * @return Retornar uma informação de valor String do CEP com 5 digito da
     * tabela Cep_LOG_Index.
     */
    public static String deIDparaCep5(String uf) {
        return paraCep5(0, uf, true);
    }

    /**
     * Este Metodo e para Retornar uma informação de valor String do UF (Estado)
     * da tabela Cep_LOG_Index.
     *
     * @param id Setar uma informação de valor inteiro do ID da tabela
     * Cep_LOG_Index.
     * @param cep5 Setar uma informação de valor String do CEP com 5 digito da
     * tabela Cep_LOG_Index.
     * @param ou Setar uma informação de valor booleando para instrução sql da
     * tabela Cep_LOG_Index.
     * @return Retornar uma informação de valor String do UF (Estado) da tabela
     * Cep_LOG_Index.
     */
    public static String paraUF(int id, String cep5, boolean ou) {
        String sql = "Select uf from " + getTABELA() + " where ", a, resultado = "";
        int i = 1;
        if (NaoHaCampoVazio(id, cep5, null)) {
            if (ou) {
                a = " or ";
            } else {
                a = " and ";
            }
            if (!String.valueOf(id).isEmpty() && id != 0) {
                i++;
                sql = "id =?";
            }
            if (!cep5.isEmpty()) {
                if (i > 1) {
                    sql += a;
                }
                sql += "cep5 = ?";
            }
            i = 1;
            try {
                cepLogIndex();
                pst = conexao.prepareStatement(sql);
                if (!String.valueOf(id).isEmpty() && id != 0) {
                    pst.setInt(i++, id);
                }
                if (!cep5.isEmpty()) {
                    pst.setString(i++, cep5);
                }
                rs = pst.executeQuery();
                if (rs.next()) {
                    resultado = rs.getString(1);
                }
            } catch (SQLException e) {
                Messagem.chamarTela(e);
            } finally {
                ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
            }
        }
        return resultado;
    }

    /**
     * Este Metodo e para Retornar uma informação de valor String do UF (Estado)
     * da tabela Cep_LOG_Index atraves do parametro ID.
     *
     * @param id Setar uma informação de valor inteiro do ID da tabela
     * Cep_LOG_Index.
     * @return Retornar uma informação de valor String do UF (Estado) da tabela
     * Cep_LOG_Index.
     */
    public static String deIDparaUF(int id) {
        return paraUF(id, "", true);
    }

    /**
     * Este Metodo e para Retornar uma informação de valor String do UF (Estado)
     * da tabela Cep_LOG_Index atraves do parametro CEP com 5 digito.
     *
     * @param cep5 Setar uma informação de valor String do CEP com 5 digito da
     * tabela Cep_LOG_Index.
     * @return Retornar uma informação de valor String do UF (Estado) da tabela
     * Cep_LOG_Index.
     */
    public static String deCEP5paraUF(String cep5) {
        return paraUF(0, cep5, true);
    }

    /**
     * @param id Setar uma informação de valor inteiro do ID da tabela
     * Cep_LOG_Index.
     * @param cep5 Setar uma informação de valor String do CEP com 5 digito da
     * tabela Cep_LOG_Index.
     * @param uf Setar uma informação de valor String do UF (Estado) da tabela
     * Cep_LOG_Index.
     */
    private static boolean HaCampoVazio(int id, String cep5, String uf) {
        boolean resultado = String.valueOf(id).isEmpty() || cep5.isEmpty() || uf.isEmpty();
        if (resultado) {
            Messagem.VAZIO(CampoVaziotoString(id, cep5, uf));
        }
        return resultado;
    }

    /**
     * @param id Setar uma informação de valor inteiro do ID da tabela
     * Cep_LOG_Index.
     * @param cep5 Setar uma informação de valor String do CEP com 5 digito da
     * tabela Cep_LOG_Index.
     * @param uf Setar uma informação de valor String do UF (Estado) da tabela
     * Cep_LOG_Index.
     */
    private static boolean NaoHaCampoVazio(int id, String cep5, String uf) {
        return !HaCampoVazio(id, cep5, uf);
    }

    /**
     * @param id Setar uma informação de valor inteiro do ID da tabela
     * Cep_LOG_Index.
     * @param cep5 Setar uma informação de valor String do CEP com 5 digito da
     * tabela Cep_LOG_Index.
     * @param uf Setar uma informação de valor String do UF (Estado) da tabela
     * Cep_LOG_Index.
     */
    private static String[] CampoVaziotoString(int id, String cep5, String uf) {
        String[] campoVazio = new String[3];
        int i = 0;
        if (String.valueOf(id).isEmpty()) {
            campoVazio[i++] = "ID";
        }
        if (cep5.isEmpty()) {
            campoVazio[i++] = "CEP de 5 digito";
        }
        if (uf.isEmpty()) {
            campoVazio[i++] = "UF";
        }
        return campoVazio;
    }

    //Fim do Acesso ao Banco
//Inicio do SETS e GETS
    /**
     * Este Metodo e para Retornar uma informação de valor inteiro do ID da
     * tabela Cep_LOG_Index.
     *
     * @return Setar uma informação de valor inteiro do ID da tabela
     * Cep_LOG_Index.
     */
    public static int getId() {
        return id;
    }

    /**
     * Este Metodo e para Retornar uma informação de valor inteiro do ID da
     * tabela Cep_LOG_Index.
     *
     * @param id Este Metodo e para Retornar uma informação de valor inteiro do
     * ID da tabela Cep_LOG_Index.
     */
    public static void setId(int id) {
        CEP_Log_Index.id = id;
    }

    /**
     * Este Metodo e para Retornar uma informação de valor String do CEP com 5
     * digito da tabela Cep_LOG_Index.
     *
     * @return Retornar uma informação de valor String do CEP com 5 digito da
     * tabela Cep_LOG_Index.
     */
    public static String getCep5() {
        return cep5;
    }

    /**
     * Este Metodo e para Setar uma informação de valor String do CEP com 5
     * digito da tabela Cep_LOG_Index.
     *
     * @param cep5 Setar uma informação de valor String do CEP com 5 digito da
     * tabela Cep_LOG_Index.
     */
    public static void setCep5(String cep5) {
        CEP_Log_Index.cep5 = cep5;
    }

    /**
     * Este Metodo e para Retornar uma informação de valor String do UF (Estado)
     * da tabela Cep_LOG_Index.
     *
     * @return Retornar uma informação de valor String do UF (Estado) da tabela
     * Cep_LOG_Index.
     */
    public static String getUf() {
        return uf;
    }

    /**
     * Este Metodo e para Setar uma informação de valor String do UF (Estado) da
     * tabela Cep_LOG_Index.
     *
     * @param uf Setar uma informação de valor String do UF (Estado) da tabela
     * Cep_LOG_Index.
     */
    public static void setUf(String uf) {
        CEP_Log_Index.uf = uf;
    }

    /**
     * Este Metodo e para Retornar uma informação de valor String do Nome da
     * tabela Cep_LOG_Index.
     *
     * @return Retornar uma informação de valor String do Nome da tabela
     * Cep_LOG_Index.
     */
    public static String getTABELA() {
        return TABELA;
    }
    //Fim do SETS e GETS 

}
