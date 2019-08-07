/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marcenaria.pessoa1;

import java.sql.*;
import marcenaria.Const.Messagem;

/**
 * 16/06/2019
 *
 * @author Carlos Eduardo dos Santos Figueiredo
 */
public class Pessoa {
 
    public static void adicionarPessoa(String Tabela, String logPessoa, String senPessoa, String conSenPessoa,
            String tipoPessoa, String nomePessoa, String documPessoa) {
        /*try {
      
            
            int idPessoa = 0;
            if (!logPessoa.isEmpty() && !senPessoa.isEmpty() && !conSenPessoa.isEmpty() && !tipoPessoa.isEmpty()
                    && !nomePessoa.isEmpty()) {
                if (senPessoa.equals(conSenPessoa) && Tabela.equalsIgnoreCase(Pessoa.getTABELA())
                        && obterIdPessoa(logPessoa) == 0) {
                } else if (Tabela.equalsIgnoreCase(Cliente.getTABELA())) {
                    if (Pessoa.VerificaDocumento(documPessoa, tipoPessoa)) {
                    } else {
                        Messagem.chamarTela(Pessoa.txtVerificaDocumento(documPessoa, tipoPessoa));
                    }
                } else if (Tabela.equalsIgnoreCase(Fornecedor.getTABELA())) {
                    if (Pessoa.VerificaDocumento(documPessoa, tipoPessoa)) {

                    } else {
                        Messagem.chamarTela(Pessoa.txtVerificaDocumento(documPessoa, tipoPessoa));
                    }
                }
            } else {
                Messagem.chamarTela(Messagem.VAZIO(
                        CampoVazio(Tabela, logPessoa, senPessoa, conSenPessoa, tipoPessoa, nomePessoa, documPessoa)));
            }
        } catch (SQLException e) {

        }*/
    }

    public static void deletarPessoa(String Tabela) {
        /*
         * catch (SQLIntegrityConstraintViolationException sicve) { if
         * (Tabela.equalsIgnoreCase(Pessoa.getTABELA())) {
         * Fornecedor.deletarFornecedor(); Cliente.deletarCliente();
         * Pessoa.deletarPessoa(); }
         */
    }
}
