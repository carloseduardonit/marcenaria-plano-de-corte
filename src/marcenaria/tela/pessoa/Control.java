/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marcenaria.tela.pessoa;

import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import marcenaria.Const.Messagem;
import marcenaria.Marcenaria;
import marcenaria.pessoa1.Cliente;
import marcenaria.pessoa1.Fornecedor;

/**
 *
 * @author Carlos
 */
public class Control {

    private static int tcs = 7;
    Marcenaria m = new Marcenaria();
    Cliente Cl = new Cliente();
    Fornecedor fr = new Fornecedor();

    public static void ADDCliente(JTextField login, JTextField senha, JTextField conSenha, JComboBox<String> TipoPessoa, JTextField nome, JTextField documento) {
        if (Cliente.campoDiferente(senha.getText(), conSenha.getText(), getTcs())) {
            Cliente.adicionarCliente(login.getText(), senha.getText(), conSenha.getText(), TipoPessoa.getSelectedItem().toString(), nome.getText(), documento.getText());
        } else {
            Messagem.chamarTela(Cliente.CampoDiferente(senha.getText(), conSenha.getText(), getTcs()));
        }

    }

    public static void ADDFornecedor(JTextField login, JTextField senha, JTextField conSenha, JComboBox<String> TipoPessoa, JTextField nome, JTextField documento) {
        if (Cliente.campoDiferente(senha.getText(), conSenha.getText(), getTcs())) {
            Fornecedor.adicionarFornecedor(login.getText(), senha.getText(), conSenha.getText(), TipoPessoa.getSelectedItem().toString(), nome.getText(), documento.getText());
        } else {
            Messagem.chamarTela(Cliente.CampoDiferente(senha.getText(), conSenha.getText(), getTcs()));
        }
    }

    public static void EDITCliente(JTextField nlogin, JTextField login, JTextField senha, JTextField conSenha, JComboBox<String> TipoPessoa, JTextField nome, JTextField documento) {
        if (Cliente.campoDiferente(senha.getText(), conSenha.getText(), getTcs())) {
            Cliente.editarCliente(nlogin.getText(), login.getText(), senha.getText(), conSenha.getText(), TipoPessoa.getSelectedItem().toString(), nome.getText(), documento.getText());
        } else {
            Messagem.chamarTela(Cliente.CampoDiferente(senha.getText(), conSenha.getText(), getTcs()));
        }
    }

    public static void EDITFornecedor(JTextField nlogin, JTextField login, JTextField senha, JTextField conSenha, JComboBox<String> TipoPessoa, JTextField nome, JTextField documento) {
        if (Cliente.campoDiferente(senha.getText(), conSenha.getText(), getTcs())) {
            Fornecedor.editarFornecedor(nlogin.getText(), login.getText(), senha.getText(), conSenha.getText(), TipoPessoa.getSelectedItem().toString(), nome.getText(), documento.getText());
        } else {
            Messagem.chamarTela(Cliente.CampoDiferente(senha.getText(), conSenha.getText(), getTcs()));
        }
    }

    public static void DELETECliente(JTextField login, JTextField senha, JTextField conSenha, JComboBox<String> TipoPessoa, JTextField nome, JTextField documento) {
        if (Cliente.campoDiferente(senha.getText(), conSenha.getText(), getTcs())) {
            Cliente.excluirCliente(login.getText(), senha.getText(), conSenha.getText(), TipoPessoa.getSelectedItem().toString(), nome.getText(), documento.getText());
        } else {
            Messagem.chamarTela(Cliente.CampoDiferente(senha.getText(), conSenha.getText(), getTcs()));
        }
    }

    public static void DELETEFornecedor(JTextField login, JTextField senha, JTextField conSenha, JComboBox<String> TipoPessoa, JTextField nome, JTextField documento) {
        if (Cliente.campoDiferente(senha.getText(), conSenha.getText(), getTcs())) {
            Fornecedor.excluirFornecedor(login.getText(), senha.getText(), conSenha.getText(), TipoPessoa.getSelectedItem().toString(), nome.getText(), documento.getText());
        } else {
            Messagem.chamarTela(Cliente.CampoDiferente(senha.getText(), conSenha.getText(), getTcs()));
        }
    }

    public static void SEACHCliente(JTextField login, JTextField senha, JTextField conSenha, JComboBox<String> TipoPessoa, JTextField nome, JTextField documento) {
        if (Cliente.campoDiferente(senha.getText(), conSenha.getText(), getTcs())) {
            Cliente.pesquisarCliente(login.getText(), senha.getText(), conSenha.getText(), TipoPessoa.getSelectedItem().toString(), nome.getText(), documento.getText());
        } else {
            Messagem.chamarTela(Cliente.CampoDiferente(senha.getText(), conSenha.getText(), getTcs()));
        }
    }

    public static void SEACHFornecedor(JTextField login, JTextField senha, JTextField conSenha, JComboBox<String> TipoPessoa, JTextField nome, JTextField documento) {
        if (Cliente.campoDiferente(senha.getText(), conSenha.getText(), getTcs())) {
            Fornecedor.pesquisarFornecedor(login.getText(), senha.getText(), conSenha.getText(), TipoPessoa.getSelectedItem().toString(), nome.getText(), documento.getText());
        } else {
            Messagem.chamarTela(Cliente.CampoDiferente(senha.getText(), conSenha.getText(), getTcs()));
        }
    }

    public static void DesabilitaeMudaDoc(JComboBox<String> TipoPessoa, JLabel ldocumento, JTextField documento) {
        if (TipoPessoa.getSelectedItem().toString().equalsIgnoreCase("pf")) {
            ldocumento.setText("n° CPF:");
            documento.setEnabled(true);
        } else if (TipoPessoa.getSelectedItem().toString().equalsIgnoreCase("pj")) {
            ldocumento.setText("n° CNPJ:");
            documento.setEnabled(true);
        } else {
            ldocumento.setText("Documento:");
            documento.setEnabled(false);
        }
    }

    public static void MudarCor(JTextField confSenha, JTextField senha) {
        if (confSenha.getText().equalsIgnoreCase(senha.getText()) && senha.getText().length() >= 7 && confSenha.getText().length() >= 7) {
            confSenha.setBackground(Color.GREEN);
            senha.setBackground(Color.GREEN);
        } else {
            confSenha.setBackground(Color.red);
            senha.setBackground(Color.red);
        }
    }

    public static void LimpaDados(JTextField login, JTextField senha, JTextField conSenha, JComboBox<String> TipoPessoa, JTextField nome, JTextField documento) {
        login.setText("");
        senha.setText("");
        conSenha.setText("");
        TipoPessoa.setSelectedIndex(0);
        nome.setText("");
        documento.setText("");
    }

    public static void SetarCliente(JTextField login, JTextField senha, JTextField conSenha, JComboBox<String> TipoPessoa, JTextField nome, JTextField documento) {
        login.setText(Cliente.getLogin());
        senha.setText(Cliente.getSenha());
        conSenha.setText(Cliente.getSenha());
        TipoPessoa.setSelectedItem(Cliente.getTipoPessoa());
        nome.setText(Cliente.getNome());
        documento.setText(String.valueOf(Cliente.getCpf()));
    }

    public static void SetarFornecedor(JTextField login, JTextField senha, JTextField conSenha, JComboBox<String> TipoPessoa, JTextField nome, JTextField documento) {
        login.setText(Fornecedor.getLogin());
        senha.setText(Fornecedor.getSenha());
        conSenha.setText(Fornecedor.getSenha());
        TipoPessoa.setSelectedItem(Fornecedor.getTipoPessoa());
        nome.setText(Fornecedor.getNome());
        documento.setText(String.valueOf(Fornecedor.getCNPJ()));
    }
    public static  void UsuarioZERO(String Tabela){
        
        //ADDCliente(login, senha, conSenha, TipoPessoa, nome, documento);
        //ADDFornecedor(login, senha, conSenha, TipoPessoa, nome, documento);
    }
    /**
     * @return the tcs
     */
    public static int getTcs() {
        return tcs;
    }

    /**
     * @param tcs the tcs to set
     */
    public void setTcs(int tcs) {
        this.tcs = tcs;
    }
}
