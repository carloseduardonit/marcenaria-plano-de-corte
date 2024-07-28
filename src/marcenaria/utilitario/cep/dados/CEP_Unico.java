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
public class CEP_Unico extends CEP {

    private static int Seq;
    private static String Nome, NomeSemAcento, CEP, UF;
    private static final String TABELA = CEP_Unico.class.getSimpleName().toLowerCase();
    private static Connection conexao;
    private static ResultSet rs;
    private static ResultSetMetaData rsmd;
    private static PreparedStatement pst;
    private static Statement stmt;

    public static void main(String[] args) {
        inicio(1, Nome, NomeSemAcento, CEP, UF, true);
        System.out.println(getCEP() + "\n"
                + getNome() + "\n");
    }

    /**
     *
     * @param Seq Setar uma informação de valor inteiro sobre a sequencia da
     * tabela Cep_Unico.
     * @param Nome Setar uma informação de valor String sobre o nome com acento
     * da tabela Cep_Unico.
     * @param NomeSemAcento Setar uma informação de valor String sobre o nome
     * sem acento da tabela Cep_Unico.
     * @param CEP Setar uma informação de valor String sobre o nimero do CEP da
     * tabela Cep_Unico.
     * @param UF Setar uma informação de valor String sobre a uf(estado) da
     * tabela Cep_Unico.
     * @param ou Setar uma informação de valor booleando na instrução sql da
     * tabela Cep_Unico.
     */
    public static void inicio(int Seq, String Nome, String NomeSemAcento, String CEP, String UF, boolean ou) {
        if (NaoHACampoVazio(Seq, Nome, NomeSemAcento, CEP, UF)) {
            String sql = "Select seq, nome, nomesemacento, cep, uf from " + getTABELA() + " where ", a, resultado;
            int i = 1;
            if (ou) {
                a = " or ";
            } else {
                a = " and ";
            }
            if (!String.valueOf(Seq).isEmpty()) {
                sql += "seq = ?";
                i++;
            }
            if (!Nome.isEmpty()) {
                if (i > 1) {
                    i++;
                    sql += a;
                }
                sql += "Nome =?";
            }
            if (!NomeSemAcento.isEmpty()) {
                if (i > 1) {
                    i++;
                    sql += a;
                }
                sql += "NomeSemAcento=?";
            }
            if (!CEP.isEmpty()) {
                if (i > 1) {
                    i++;
                    sql += a;
                }
                sql += "cep=?";
            }
            if (!UF.isEmpty()) {
                if (i > 1) {
                    i++;
                    sql += a;
                }
                sql += "uf=?";
            }
            i = 1;
            try {
                cepUnico();
                pst = conexao.prepareStatement(sql);
                if (!String.valueOf(Seq).isEmpty()) {
                    pst.setInt(i++, Seq);
                }
                if (!Nome.isEmpty()) {
                    pst.setString(i++, Nome);
                }
                if (!NomeSemAcento.isEmpty()) {
                    pst.setString(i++, NomeSemAcento);
                }
                if (!CEP.isEmpty()) {
                    pst.setString(i++, CEP);
                }
                if (!UF.isEmpty()) {
                    pst.setString(i++, UF);
                }
                rs = pst.executeQuery();
                if (rs.next()) {
                    i = 1;
                    setSeq(rs.getInt(i++));
                    setNome(rs.getString(i++));
                    setNomeSemAcento(rs.getString(i++));
                    setCEP(rs.getString(i++));
                    setUF(rs.getString(i++));
                }
            } catch (SQLException e) {
                Messagem.chamarTela(e);
            } finally {
                ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
            }
        }
    }

    //inicio do Acesso ao Banco
    private static void cepUnico() {
        conexao = ModuloConector.getConecction(DADO);
    }

    /**
     * Este metodo e para Retornar uma informação de valor String para obter a
     * sequencia da tabela Cep_Unico.
     *
     * @param Nome Setar uma informação de valor String sobre o nome com acento
     * da tabela Cep_Unico.
     * @param NomeSemAcento Setar uma informação de valor String sobre o nome
     * sem acento da tabela Cep_Unico.
     * @param CEP Setar uma informação de valor String sobre o nimero do CEP da
     * tabela Cep_Unico.
     * @param UF Setar uma informação de valor String sobre a uf(estado) da
     * tabela Cep_Unico.
     * @param ou Setar uma informação de valor booleando na instrução sql da
     * tabela Cep_Unico.
     * @return Retornar uma informação de valor String para obter a sequencia da
     * tabela Cep_Unico.
     */
    public static int paraSeq(String Nome, String NomeSemAcento, String CEP, String UF, boolean ou) {
        if (NaoHACampoVazio(0, Nome, NomeSemAcento, CEP, UF)) {
            String sql = "Select seq from " + getTABELA() + " where ", a;
            int i = 1, resultado = 0;
            if (ou) {
                a = " or ";
            } else {
                a = " and ";
            }
            if (!Nome.isEmpty()) {
                if (i > 1) {
                    i++;
                    sql += a;
                }
                sql += "Nome =?";
            }
            if (!NomeSemAcento.isEmpty()) {
                if (i > 1) {
                    i++;
                    sql += a;
                }
                sql += "NomeSemAcento=?";
            }
            if (!CEP.isEmpty()) {
                if (i > 1) {
                    i++;
                    sql += a;
                }
                sql += "cep=?";
            }
            if (!UF.isEmpty()) {
                if (i > 1) {
                    i++;
                    sql += a;
                }
                sql += "uf=?";
            }
            i = 1;
            try {
                cepUnico();
                pst = conexao.prepareStatement(sql);
                if (!Nome.isEmpty()) {
                    pst.setString(i++, Nome);
                }
                if (!NomeSemAcento.isEmpty()) {
                    pst.setString(i++, NomeSemAcento);
                }
                if (!CEP.isEmpty()) {
                    pst.setString(i++, CEP);
                }
                if (!UF.isEmpty()) {
                    pst.setString(i++, UF);
                }
                rs = pst.executeQuery();
                if (rs.next()) {
                    i = 1;
                    resultado = rs.getInt(i++);
                }
            } catch (SQLException e) {
                Messagem.chamarTela(e);
            } finally {
                ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
            }
            return resultado;
        }
        return 0;
    }

    /**
     * Este metodo e para Retornar uma informação de valor String para obter o
     * Nome com Acento da tabela Cep_Unico.
     *
     * @param Seq Setar uma informação de valor inteiro sobre a sequencia da
     * tabela Cep_Unico.
     * @param NomeSemAcento Setar uma informação de valor String sobre o nome
     * sem acento da tabela Cep_Unico.
     * @param CEP Setar uma informação de valor String sobre o nimero do CEP da
     * tabela Cep_Unico.
     * @param UF Setar uma informação de valor String sobre a uf(estado) da
     * tabela Cep_Unico.
     * @param ou Setar uma informação de valor booleando na instrução sql da
     * tabela
     * @return Retornar uma informação de valor String da tabela Cep_Unico.
     */
    public static String paraNome(int Seq, String NomeSemAcento, String CEP, String UF, boolean ou) {
        String sql = "Select  nome from " + getTABELA() + " where ", a, resultado = "";
        int i = 1;
        if (NaoHACampoVazio(Seq, "", NomeSemAcento, CEP, UF)) {
            if (ou) {
                a = " or ";
            } else {
                a = " and ";
            }
            if (!String.valueOf(Seq).isEmpty()) {
                sql += "seq = ?";
                i++;
            }
            if (!NomeSemAcento.isEmpty()) {
                if (i > 1) {
                    i++;
                    sql += a;
                }
                sql += "NomeSemAcento=?";
            }
            if (!CEP.isEmpty()) {
                if (i > 1) {
                    i++;
                    sql += a;
                }
                sql += "cep=?";
            }
            if (!UF.isEmpty()) {
                if (i > 1) {
                    i++;
                    sql += a;
                }
                sql += "uf=?";
            }
            i = 1;
            try {
                cepUnico();
                pst = conexao.prepareStatement(sql);
                if (!String.valueOf(Seq).isEmpty()) {
                    pst.setInt(i++, Seq);
                }
                if (!NomeSemAcento.isEmpty()) {
                    pst.setString(i++, NomeSemAcento);
                }
                if (!CEP.isEmpty()) {
                    pst.setString(i++, CEP);
                }
                if (!UF.isEmpty()) {
                    pst.setString(i++, UF);
                }
                rs = pst.executeQuery();
                if (rs.next()) {
                    i = 1;
                    resultado = rs.getString(i++);
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
     * Este metodo e para Retornar uma informação de valor String para obter o
     * Nome sem Acento da tabela Cep_Unico.
     *
     * @param Seq Setar uma informação de valor inteiro sobre a sequencia da
     * tabela Cep_Unico.
     * @param Nome Setar uma informação de valor String sobre o nome com acento
     * da tabela Cep_Unico.
     * @param CEP Setar uma informação de valor String sobre o nimero do CEP da
     * tabela Cep_Unico.
     * @param UF Setar uma informação de valor String sobre a uf(estado) da
     * tabela Cep_Unico.
     * @param ou Setar uma informação de valor booleando na instrução sql da
     * tabela Cep_Unico.
     * @return Retornar uma informação de valor String da tabela Cep_Unico.
     */
    public static String paraNomeSemAcento(int Seq, String Nome, String CEP, String UF, boolean ou) {
        String sql = "Select nomesemacento from " + getTABELA() + " where ", a, resultado = "";
        int i = 1;
        if (NaoHACampoVazio(Seq, Nome, "", CEP, UF)) {
            if (ou) {
                a = " or ";
            } else {
                a = " and ";
            }
            if (!String.valueOf(Seq).isEmpty()) {
                sql += "seq = ?";
                i++;
            }
            if (!Nome.isEmpty()) {
                if (i > 1) {
                    i++;
                    sql += a;
                }
                sql += "Nome =?";
            }
            if (!CEP.isEmpty()) {
                if (i > 1) {
                    i++;
                    sql += a;
                }
                sql += "cep=?";
            }
            if (!UF.isEmpty()) {
                if (i > 1) {
                    i++;
                    sql += a;
                }
                sql += "uf=?";
            }
            i = 1;
            try {
                cepUnico();
                pst = conexao.prepareStatement(sql);
                if (!String.valueOf(Seq).isEmpty()) {
                    pst.setInt(i++, Seq);
                }
                if (!Nome.isEmpty()) {
                    pst.setString(i++, Nome);
                }
                if (!CEP.isEmpty()) {
                    pst.setString(i++, CEP);
                }
                if (!UF.isEmpty()) {
                    pst.setString(i++, UF);
                }
                rs = pst.executeQuery();
                if (rs.next()) {
                    i = 1;
                    resultado = rs.getString(i++);
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
     * Este metodo e para Retornar uma informação de valor String para obter o
     * CEP da tabela Cep_Unico.
     *
     * @param Seq Setar uma informação de valor inteiro sobre a sequencia da
     * tabela Cep_Unico.
     * @param Nome Setar uma informação de valor String sobre o nome com acento
     * da tabela Cep_Unico.
     * @param NomeSemAcento Setar uma informação de valor String sobre o nome
     * sem acento da tabela Cep_Unico.
     * @param UF Setar uma informação de valor String sobre a uf(estado) da
     * tabela Cep_Unico.
     * @param ou Setar uma informação de valor booleando na instrução sql da
     * tabela Cep_Unico.
     * @return Retornar uma informação de valor String para obter o CEP da
     * tabela Cep_Unico.
     */
    public static String paraCEP(int Seq, String Nome, String NomeSemAcento, String UF, boolean ou) {
        String sql = "Select cep from " + getTABELA() + " where ", a, resultado = "";
        int i = 1;
        if (NaoHACampoVazio(Seq, Nome, NomeSemAcento, "", UF)) {
            if (ou) {
                a = " or ";
            } else {
                a = " and ";
            }
            if (!String.valueOf(Seq).isEmpty()) {
                sql += "seq = ?";
                i++;
            }
            if (!Nome.isEmpty()) {
                if (i > 1) {
                    i++;
                    sql += a;
                }
                sql += "Nome =?";
            }
            if (!NomeSemAcento.isEmpty()) {
                if (i > 1) {
                    i++;
                    sql += a;
                }
                sql += "NomeSemAcento=?";
            }
            if (!UF.isEmpty()) {
                if (i > 1) {
                    i++;
                    sql += a;
                }
                sql += "uf=?";
            }
            i = 1;
            try {
                cepUnico();
                pst = conexao.prepareStatement(sql);
                if (!String.valueOf(Seq).isEmpty()) {
                    pst.setInt(i++, Seq);
                }
                if (!Nome.isEmpty()) {
                    pst.setString(i++, Nome);
                }
                if (!NomeSemAcento.isEmpty()) {
                    pst.setString(i++, NomeSemAcento);
                }
                if (!UF.isEmpty()) {
                    pst.setString(i++, UF);
                }
                rs = pst.executeQuery();
                if (rs.next()) {
                    i = 1;
                    resultado = rs.getString(i++);
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
     * Este Metodo e para Retornar uma informação de valor String para obter a
     * UF (Estado) da tabela Cep_Unico.
     *
     * @param Seq Setar uma informação de valor inteiro sobre a sequencia da
     * tabela Cep_Unico.
     * @param Nome Setar uma informação de valor String sobre o nome com acento
     * da tabela Cep_Unico.
     * @param NomeSemAcento Setar uma informação de valor String sobre o nome
     * sem acento da tabela Cep_Unico.
     * @param CEP Setar uma informação de valor String sobre o nimero do CEP da
     * tabela Cep_Unico.
     * @param ou Setar uma informação de valor booleando na instrução sql da
     * tabela Cep_Unico.
     * @return Retornar uma informação de valor String para obter a UF (Estado)
     * da tabela Cep_Unico.
     */
    public static String paraUF(int Seq, String Nome, String NomeSemAcento, String CEP, boolean ou) {
        String sql = "Select seq, nome, nomesemacento, cep, uf from " + getTABELA() + " where ", a, resultado = "";
        int i = 1;
        if (NaoHACampoVazio(Seq, Nome, NomeSemAcento, CEP, "")) {
            if (ou) {
                a = " or ";
            } else {
                a = " and ";
            }
            if (!String.valueOf(Seq).isEmpty()) {
                sql += "seq = ?";
                i++;
            }
            if (!Nome.isEmpty()) {
                if (i > 1) {
                    i++;
                    sql += a;
                }
                sql += "Nome =?";
            }
            if (!NomeSemAcento.isEmpty()) {
                if (i > 1) {
                    i++;
                    sql += a;
                }
                sql += "NomeSemAcento=?";
            }
            if (!CEP.isEmpty()) {
                if (i > 1) {
                    i++;
                    sql += a;
                }
                sql += "cep=?";
            }
            i = 1;
            try {
                cepUnico();
                pst = conexao.prepareStatement(sql);
                if (!String.valueOf(Seq).isEmpty()) {
                    pst.setInt(i++, Seq);
                }
                if (!Nome.isEmpty()) {
                    pst.setString(i++, Nome);
                }
                if (!NomeSemAcento.isEmpty()) {
                    pst.setString(i++, NomeSemAcento);
                }
                if (!CEP.isEmpty()) {
                    pst.setString(i++, CEP);
                }
                rs = pst.executeQuery();
                if (rs.next()) {
                    i = 1;
                    resultado = rs.getString(i++);
                }
            } catch (SQLException e) {
                Messagem.chamarTela(e);
            } finally {
                ModuloConector.fecharConexao(conexao, rs, rsmd, pst, stmt);
            }
        }
        return resultado;
    }

    //Fim do Acesso ao Banco
    /**
     *
     * @param Seq Setar uma informação de valor inteiro sobre a sequencia da
     * tabela Cep_Unico.
     * @param Nome Setar uma informação de valor String sobre o nome com acento
     * da tabela Cep_Unico.
     * @param NomeSemAcento Setar uma informação de valor String sobre o nome
     * sem acento da tabela Cep_Unico.
     * @param CEP Setar uma informação de valor String sobre o nimero do CEP da
     * tabela Cep_Unico.
     * @param UF Setar uma informação de valor String sobre a uf(estado) da
     * tabela Cep_Unico.
     * @param ou Setar uma informação de valor booleando na instrução sql da
     * tabela Cep_Unico.
     */
    private static boolean NaoHACampoVazio(int Seq, String Nome, String NomeSemAcento, String NCEP, String UF) {
        return HACampoVazio(Seq, Nome, NomeSemAcento, NCEP, UF);
    }

    /**
     *
     * @param Seq Setar uma informação de valor inteiro sobre a sequencia da
     * tabela Cep_Unico.
     * @param Nome Setar uma informação de valor String sobre o nome com acento
     * da tabela Cep_Unico.
     * @param NomeSemAcento Setar uma informação de valor String sobre o nome
     * sem acento da tabela Cep_Unico.
     * @param CEP Setar uma informação de valor String sobre o nimero do CEP da
     * tabela Cep_Unico.
     * @param UF Setar uma informação de valor String sobre a uf(estado) da
     * tabela Cep_Unico.
     * @param ou Setar uma informação de valor booleando na instrução sql da
     * tabela Cep_Unico.
     */
    private static boolean HACampoVazio(int Seq, String Nome, String NomeSemAcento, String NCEP, String UF) {
        boolean resultado = String.valueOf(Seq).isEmpty() || Nome.isEmpty() || NomeSemAcento.isEmpty() || NCEP.isEmpty() || UF.isEmpty();
        if (resultado) {
            Messagem.VAZIO(CampoVaziotoString(Seq, Nome, NomeSemAcento, NCEP, UF));
        }
        return resultado;
    }

    /**
     *
     * @param Seq Setar uma informação de valor inteiro sobre a sequencia da
     * tabela Cep_Unico.
     * @param Nome Setar uma informação de valor String sobre o nome com acento
     * da tabela Cep_Unico.
     * @param NomeSemAcento Setar uma informação de valor String sobre o nome
     * sem acento da tabela Cep_Unico.
     * @param CEP Setar uma informação de valor String sobre o nimero do CEP da
     * tabela Cep_Unico.
     * @param UF Setar uma informação de valor String sobre a uf(estado) da
     * tabela Cep_Unico.
     * @param ou Setar uma informação de valor booleando na instrução sql da
     * tabela Cep_Unico.
     */
    private static String[] CampoVaziotoString(int Seq, String Nome, String NomeSemAcento, String NCEP, String UF) {
        String[] campoVazio = new String[5];
        int i = 0;
        if (String.valueOf(Seq).isEmpty()) {
            campoVazio[i++] = "Sequencia";
        }
        if (Nome.isEmpty()) {
            campoVazio[i++] = "Nome";
        }
        if (NomeSemAcento.isEmpty()) {
            campoVazio[i++] = "Nome sem Acento";
        }
        if (NCEP.isEmpty()) {
            campoVazio[i++] = "Numero do CEP";
        }
        if (UF.isEmpty()) {
            campoVazio[i++] = "Estado";
        }
        return campoVazio;
    }
//Inicio do SETS e GETS

    /**
     * Este metodo e para Retornar uma informação de valor String para obter a
     * sequencia da tabela Cep_Unico.
     *
     * @return Retornar uma informação de valor String para obter a sequencia da
     * tabela Cep_Unico.
     */
    public static int getSeq() {
        return Seq;
    }

    /**
     * Este metodo e para Setar uma informação de valor String para obter a
     * sequencia da tabela Cep_Unico.
     *
     * @param Seq Setar uma informação de valor String para obter a sequencia da
     * tabela Cep_Unico.
     */
    public static void setSeq(int Seq) {
        CEP_Unico.Seq = Seq;
    }

    /**
     * Este metodo e para Retornar uma informação de valor String para obter o
     * Nome com Acento da tabela Cep_Unico.
     *
     * @return Retornar uma informação de valor String para obter o Nome com
     * Acento da tabela Cep_Unico.
     */
    public static String getNome() {
        return Nome;
    }

    /**
     * Este metodo e para Setar uma informação de valor String para obter o Nome
     * com Acento da tabela Cep_Unico.
     *
     * @param Nome Setar uma informação de valor String sobre o nome com acento
     * da tabela Cep_Unico.
     */
    public static void setNome(String Nome) {
        CEP_Unico.Nome = Nome;
    }

    /**
     * Este metodo e para Retornar uma informação de valor String para obter o
     * Nome sem Acento da tabela Cep_Unico.
     *
     * @return Retornar uma informação de valor String para obter o Nome sem
     * Acento da tabela Cep_Unico.
     */
    public static String getNomeSemAcento() {
        return NomeSemAcento;
    }

    /**
     * Este metodo e para Retornar uma informação de valor String para obter o
     * Nome sem Acento da tabela Cep_Unico.
     *
     * @param NomeSemAcento Setar uma informação de valor String sobre o nome
     * sem acento da tabela Cep_Unico.
     */
    public static void setNomeSemAcento(String NomeSemAcento) {
        CEP_Unico.NomeSemAcento = NomeSemAcento;
    }

    /**
     * Este metodo e para Retornar uma informação de valor String para obter o
     * CEP da tabela Cep_Unico.
     *
     * @return Retornar uma informação de valor String para obter o CEP da
     * tabela Cep_Unico.
     */
    public static String getCEP() {
        return CEP;
    }

    /**
     * Este metodo e para Setar uma informação de valor String para obter o CEP
     * da tabela Cep_Unico.
     *
     * @param CEP Setar uma informação de valor String para obter o CEP da
     * tabela Cep_Unico.
     */
    public static void setCEP(String CEP) {
        CEP_Unico.CEP = CEP;
    }

    /**
     * Este Metodo e para Retornar uma informação de valor String para obter a
     * UF (Estado) da tabela Cep_Unico.
     *
     * @return Retornar uma informação de valor String para obter a UF (Estado)
     * da tabela Cep_Unico.
     */
    public static String getUF() {
        return UF;
    }

    /**
     * Este Metodo e para Setar uma informação de valor String para obter a UF
     * (Estado) da tabela Cep_Unico.
     *
     * @param UF Setar uma informação de valor String para obter a UF (Estado)
     * da tabela Cep_Unico.
     */
    public static void setUF(String UF) {
        CEP_Unico.UF = UF;
    }

    /**
     * Este Metodo e para Retornar uma informação de valor String para obter o
     * nome da tabela Cep_Unico.
     *
     * @return Retornar uma informação de valor String para obter o nome da
     * tabela Cep_Unico.
     */
    public static String getTABELA() {
        return TABELA;
    }
    //Fim do SETS e GETS 

}
