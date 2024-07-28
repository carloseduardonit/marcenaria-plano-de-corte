/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import dao.ModuloConexao;
import java.sql.*;

/**
 *
 * @author Carlos
 */
public class Endereco {

    Connection Conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    public String cep, endereço, bairro, cidade, uf;
    public String[][] estado = new String[27][2];

    void Endereco() {
        Conexao = ModuloConexao.Conector();
    }

    void Estado() {
        estado[0][0] = "AC";
        estado[0][1] = "Acre";
        estado[1][0] = "";
        estado[1][1] = "";
        estado[2][0] = "";
        estado[2][1] = "";
        estado[3][0] = "";
        estado[3][1] = "";
        estado[4][0] = "";
        estado[4][1] = "";
        estado[5][0] = "";
        estado[5][1] = "";
        estado[6][0] = "";
        estado[6][1] = "";
        estado[7][0] = "";
        estado[7][1] = "";
        estado[8][0] = "";
        estado[8][1] = "";
        estado[9][0] = "";
        estado[9][1] = "";
        estado[10][0] = "";
        estado[10][1] = "";
        estado[11][0] = "";
        estado[11][1] = "";
        estado[12][0] = "";
        estado[12][1] = "";
        estado[13][0] = "";
        estado[13][1] = "";
        estado[14][0] = "";
        estado[14][1] = "";
        estado[15][0] = "";
        estado[15][1] = "";
        estado[16][0] = "";
        estado[16][1] = "";
    }

    void pesquisarCEP(String cep) throws SQLException {
        int i = 0;
        Estado();
        for (;; i++) {
            String SQL = "select * from" + estado[i][0] + " where" + cep;
            pst = Conexao.prepareStatement(SQL);
            this.endereço = rs.getString(1) + " " + rs.getString(2);
            this.bairro = rs.getString(3);
            this.cidade = rs.getString(4);
            uf = this.estado[i][1];
        }
    }
}
