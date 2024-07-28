/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author carlos
 */
public class Funcionario {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    static String nomeCompleto, login, email, senha, celular, cargo, sexo,complemento;
    static int numero, cnpj, idPessoa, cep;

    String erro(String nomeCompleto,String login, String email, String senha,String celular,int cnpj,int cep) {
        String erros = "O(s) campo(s) esta(m) Vazio(s):";
        //int count = 0;
        if (nomeCompleto.isEmpty()) {erros += "\n Nome Completo";} 
        if(login.isEmpty()){erros += "\n Login;";}
        if(email.isEmpty()){erros += "\n Email;";}
        if(senha.isEmpty()){erros +="\n Senha";}
        if(celular.isEmpty()){erros +="\n Telefone Fixo ou Celular";}
        if(String.valueOf(cnpj).isEmpty()){erros +="\n Empresa";}
        if(String.valueOf(cep).isEmpty()){erros +="\n CEP";}
        return erros;
    }

    void adicionarFuncionario(String nomeCompleto, String login, String email, String senha, String celular, String cargo, String sexo,String complemento, int numero, int cnpj, int idPessoa, int cep) {
        String sql = "insert into funcionario(nomeCompleto,login,email,senha,celular,cargo,sexo,numero,cnpj,idPessoa,cep)values(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nomeCompleto);//obrigatorio
            pst.setString(2, login);//obrigatorio
            pst.setString(3, email);//obrigatorio
            pst.setString(4, senha);//obrigatorio
            pst.setString(5, celular);//obrigatorio
            pst.setString(6, cargo);
            pst.setString(7, sexo);
            // int
            pst.setInt(8, numero);
            pst.setInt(9, cnpj);//obrigatorio
            pst.setInt(10, idPessoa);
            pst.setInt(11, cep);//obrigatorio
            if (!nomeCompleto.isEmpty() && !login.isEmpty() && !email.isEmpty() && !senha.isEmpty() && !celular.isEmpty()&&!String.valueOf(cnpj).isEmpty()) {
               
            } else {
                String er =erro(nomeCompleto, login, email, senha,celular,cnpj,cep);
               JOptionPane.showMessageDialog(null,er);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    void editarFuncionario(String nomeCompleto, String email, String senha, String celular, String cargo, int numero, int cnpj, int idPessoa, int cep) {

    }

    void excluirFuncionario(String nomeCompleto, String email, String senha, String celular, String cargo, int numero, int cnpj, int idPessoa, int cep) {

    }

    void pesquisarFuncionario(String nomeCompleto, String email, String senha, String celular, String cargo, int numero, int cnpj, int idPessoa, int cep) {

    }
}
