/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marcenaria.tela.pessoa;

import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import marcenaria.Const.Messagem;
import marcenaria.Marcenaria;
import marcenaria.material.Chapa;
import marcenaria.material.Peca;
import marcenaria.material.Pedaco;
import marcenaria.pessoa.Cliente;
import marcenaria.pessoa.Fornecedor;

/**
 *
 * @author Carlos
 */
public class Control {

    private static int tcs = 7;
    Marcenaria m = new Marcenaria();
    Cliente Cl = new Cliente();
    Fornecedor fr = new Fornecedor();

    /**
     * @param Quantidade Setar uma informação de valor JText de Quantidade
     * @param Produto Setar uma informação de valor JText de Tipo de Produto
     * @param Comprimento Setar uma informação de valor JText de Comprimento
     * @param Largura Setar uma informação de valor JText de Largura
     * @param Espessura Setar uma informação de valor JText de Espessura
     * @param Preco Setar uma informação de valor JText de Preço
     * @param Fornecedor Setar uma informação de valor JText de login do
     * fornecedor
     */
    public static void ADDChapa(JTextField Quantidade, JTextField Produto, JTextField Comprimento, JTextField Largura,
            JTextField Espessura, JTextField Preco, JTextField Fornecedor) {
        Chapa.adicionarChapa(Control.deJTextparaInt(Produto), Control.deJTextparaDouble(Comprimento),
                Control.deJTextparaDouble(Largura), Control.deJTextparaDouble(Espessura),
                Control.deJTextparaDouble(Preco), Produto.getText(), Fornecedor.getText());
    }

    /**
     * @param login Setar uma informação de valor JText de login
     * @param senha Setar uma informação de valor JText de senha
     * @param conSenha Setar uma informação de valor JText de confimação de
     * senha
     * @param TipoPessoa Setar uma informação de valor JText de tipo de pessoa
     * @param nome Setar uma informação de valor JText de nome
     * @param documento Setar uma informação de valor JText de documento
     */
    public static void ADDCliente(JTextField login, JTextField senha, JTextField conSenha, JComboBox<String> TipoPessoa,
            JTextField nome, JTextField documento) {
        if (Cliente.campoDiferente(senha.getText(), conSenha.getText(), getTcs())) {
            Cliente.adicionarCliente(login.getText(), senha.getText(), conSenha.getText(),
                    TipoPessoa.getSelectedItem().toString(), nome.getText(), documento.getText());
        } else {
            Messagem.chamarTela(Cliente.CampoDiferente(senha.getText(), conSenha.getText(), getTcs()));
        }

    }

    /**
     *  @param login Setar uma informação de valor JText de login
     * @param senha Setar uma informação de valor JText de senha
     * @param conSenha Setar uma informação de valor JText de confimação de
     * senha
     * @param TipoPessoa Setar uma informação de valor JText de tipo de pessoa
     * @param nome Setar uma informação de valor JText de nome
     * @param documento Setar uma informação de valor JText de documento
     */
    public static void ADDFornecedor(JTextField login, JTextField senha, JTextField conSenha,
            JComboBox<String> TipoPessoa, JTextField nome, JTextField documento) {
        if (Fornecedor.campoDiferente(senha.getText(), conSenha.getText(), getTcs())) {
            Fornecedor.adicionarFornecedor(login.getText(), senha.getText(), conSenha.getText(),
                    TipoPessoa.getSelectedItem().toString(), nome.getText(), documento.getText());
        } else {
            Messagem.chamarTela(Cliente.CampoDiferente(senha.getText(), conSenha.getText(), getTcs()));
        }
    }

    /**
     * @param Quantidade Setar uma informação de valor JText de Quantidade
     * @param Produto Setar uma informação de valor JText de Tipo de Produto
     * @param Comprimento Setar uma informação de valor JText de Comprimento
     * @param Largura Setar uma informação de valor JText de Largura
     * @param Espessura Setar uma informação de valor JText de Espessura
     * @param Preco Setar uma informação de valor JText de Preço
     */
    public static void ADDPedaco(JTextField Quantidade, JTextField Produto, JTextField Comprimento, JTextField Largura,
            JTextField Espessura, JTextField Preco) {
        Pedaco.adicionarPedaco(Control.deJTextparaInt(Quantidade),
                Control.deJTextparaDouble(Comprimento),
                Control.deJTextparaDouble(Largura), Control.deJTextparaDouble(Espessura),
                Control.deJTextparaDouble(Preco), Produto.getText());
    }

    /**
     * @param Quantidade Setar uma informação de valor JText de Quantidade
     * @param Produto Setar uma informação de valor JText de Tipo de Produto
     * @param Comprimento Setar uma informação de valor JText de Comprimento
     * @param Largura Setar uma informação de valor JText de Largura
     * @param Espessura Setar uma informação de valor JText de Espessura
     * @param Preco Setar uma informação de valor JText de Preço
     * @param Fornecedor Setar uma informação de valor JText de login do
     * fornecedor
     */
    public static void ADDPeca(JTextField Quantidade, JTextField Produto, JTextField Comprimento, JTextField Largura,
            JTextField Espessura, JTextField Preco, JTextField Fornecedor) {
        Peca.adicionarPeca(Control.deJTextparaInt(Quantidade), Control.deJTextparaDouble(Comprimento),
                Control.deJTextparaDouble(Largura), Control.deJTextparaDouble(Espessura),
                Control.deJTextparaDouble(Preco), Produto.getText(), Fornecedor.getText());
    }

    /**
     * @param Quantidade Setar uma informação de valor JText de Quantidade
     * @param Produto Setar uma informação de valor JText de Tipo de Produto
     * @param Comprimento Setar uma informação de valor JText de Comprimento
     * @param Largura Setar uma informação de valor JText de Largura
     * @param Espessura Setar uma informação de valor JText de Espessura
     * @param Preco Setar uma informação de valor JText de Preço
     * @param Fornecedor Setar uma informação de valor JText de login do
     * fornecedor
     */
    public static void EDITChapa(JTextField Quantidade, JTextField Produto, JTextField Comprimento, JTextField Largura,
            JTextField Espessura, JTextField Preco,JTextField Fornecedor) {
        Chapa.editarChapa(Fornecedor.getText());
    }

    /**
     * @param login Setar uma informação de valor JText de login
     * @param senha Setar uma informação de valor JText de senha
     * @param conSenha Setar uma informação de valor JText de confimação de
     * senha
     * @param TipoPessoa Setar uma informação de valor JText de tipo de pessoa
     * @param nome Setar uma informação de valor JText de nome
     * @param documento Setar uma informação de valor JText de documento
     */
    public static void EDITCliente( JTextField login, JTextField senha, JTextField conSenha,
            JComboBox<String> TipoPessoa, JTextField nome, JTextField documento) {
        if (Cliente.campoDiferente(senha.getText(), conSenha.getText(), getTcs())) {
            Cliente.editarCliente(login.getText(), senha.getText(), conSenha.getText(),
                    TipoPessoa.getSelectedItem().toString(), nome.getText(), documento.getText());
        } else {
            Messagem.chamarTela(Cliente.CampoDiferente(senha.getText(), conSenha.getText(), getTcs()));
        }
    }

    /**
     * @param nlogin Setar uma informação de valor JText de novo login
     ** @param login Setar uma informação de valor JText de login
     * @param senha Setar uma informação de valor JText de senha
     * @param conSenha Setar uma informação de valor JText de confimação de
     * senha
     * @param TipoPessoa Setar uma informação de valor JText de tipo de pessoa
     * @param nome Setar uma informação de valor JText de nome
     * @param documento Setar uma informação de valor JText de documento
     */
    public static void EDITFornecedor( JTextField login, JTextField senha, JTextField conSenha,
            JComboBox<String> TipoPessoa, JTextField nome, JTextField documento) {
        // if (Cliente.campoDiferente(senha.getText(), conSenha.getText(), getTcs())) {
        Fornecedor.editarFornecedor( login.getText(), senha.getText(), conSenha.getText(),
                TipoPessoa.getSelectedItem().toString(), nome.getText(), documento.getText());
        LimpaDados(login, senha, conSenha, TipoPessoa, nome, documento);
        // } else {
        // Messagem.chamarTela(Cliente.CampoDiferente(senha.getText(),
        // conSenha.getText(), getTcs()));
        // }
    }

    /**
     * @param Quantidade Setar uma informação de valor JText de Quantidade
     * @param Produto Setar uma informação de valor JText de Tipo de Produto
     * @param Comprimento Setar uma informação de valor JText de Comprimento
     * @param Largura Setar uma informação de valor JText de Largura
     * @param Espessura Setar uma informação de valor JText de Espessura
     * @param Preco Setar uma informação de valor JText de Preço
     * @param Fornecedor Setar uma informação de valor JText de login do
     * fornecedor
     */
    public static void EDITPeca(JTextField Quantidade, JTextField Produto, JTextField Comprimento, JTextField Largura,
            JTextField Espessura, JTextField Preco,JTextField Fornecedor) {
        Peca.editarPeca(Quantidade.getText());
    }

    /**
     * @param Quantidade Setar uma informação de valor JText de Quantidade
     * @param Produto Setar uma informação de valor JText de Tipo de Produto
     * @param Comprimento Setar uma informação de valor JText de Comprimento
     * @param Largura Setar uma informação de valor JText de Largura
     * @param Espessura Setar uma informação de valor JText de Espessura
     * @param Preco Setar uma informação de valor JText de Preço
     * @param Fornecedor Setar uma informação de valor JText de login do
     * fornecedor
     */
    public static void EDITPedaco(JTextField Quantidade, JTextField Produto, JTextField Comprimento, JTextField Largura,
            JTextField Espessura, JTextField Preco,JTextField Fornecedor) {
        // Pedaco.editarPedaco(tcs, tcs, tcs, tcs, tcs, incData);
    }

    /**
     * @param Quantidade Setar uma informação de valor JText de Quantidade
     * @param Produto Setar uma informação de valor JText de Tipo de Produto
     * @param Comprimento Setar uma informação de valor JText de Comprimento
     * @param Largura Setar uma informação de valor JText de Largura
     * @param Espessura Setar uma informação de valor JText de Espessura
     * @param Preco Setar uma informação de valor JText de Preço
     * @param Fornecedor Setar uma informação de valor JText de login do
     * fornecedor
     */
    public static void DELETEChapa(JTextField Quantidade, JTextField Produto, JTextField Comprimento,
            JTextField Largura, JTextField Espessura, JTextField Preco,JTextField Fornecedor) {
        Chapa.excluirChapa();
    }

    /**
     * @param login Setar uma informação de valor JText de login
     * @param senha Setar uma informação de valor JText de senha
     * @param conSenha Setar uma informação de valor JText de confimação de
     * senha
     * @param TipoPessoa Setar uma informação de valor JText de tipo de pessoa
     * @param nome Setar uma informação de valor JText de nome
     * @param documento Setar uma informação de valor JText de documento
     */
    public static void DELETECliente(JTextField login, JTextField senha, JTextField conSenha,
            JComboBox<String> TipoPessoa, JTextField nome, JTextField documento) {
        if (!login.getText().isEmpty()) {
            // senha.setText(String.valueOf(11111111));
            // conSenha.setText(String.valueOf(11111111));
            Cliente.excluirCliente(login.getText());
            LimpaDados(login, senha, conSenha, TipoPessoa, nome, documento);
        } else {
            Messagem.chamarTela(Cliente.CampoDiferente(senha.getText(), conSenha.getText(), getTcs()));
        }
    }

    /**
     * @param login Setar uma informação de valor JText de login
     * @param senha Setar uma informação de valor JText de senha
     * @param conSenha Setar uma informação de valor JText de confimação de
     * senha
     * @param TipoPessoa Setar uma informação de valor JText de tipo de pessoa
     * @param nome Setar uma informação de valor JText de nome
     * @param documento Setar uma informação de valor JText de documento
     */
    public static void DELETEFornecedor(JTextField login, JTextField senha, JTextField conSenha,
            JComboBox<String> TipoPessoa, JTextField nome, JTextField documento) {
        if (!login.getText().isEmpty()) {
            // senha.setText(String.valueOf(11111111));
            // conSenha.setText(String.valueOf(11111111));
            Fornecedor.excluirFornecedor(login.getText());
        } else {
            Messagem.chamarTela(Cliente.CampoDiferente(senha.getText(), conSenha.getText(), getTcs()));
        }
    }

    /**
     * @param Quantidade Setar uma informação de valor JText de Quantidade
     * @param Produto Setar uma informação de valor JText de Tipo de Produto
     * @param Comprimento Setar uma informação de valor JText de Comprimento
     * @param Largura Setar uma informação de valor JText de Largura
     * @param Espessura Setar uma informação de valor JText de Espessura
     * @param Preco Setar uma informação de valor JText de Preço
     * @param Fornecedor Setar uma informação de valor JText de login do
     * fornecedor
     */
    public static void DELETEPeca(JTextField Quantidade, JTextField Produto, JTextField Comprimento, JTextField Largura,
            JTextField Espessura, JTextField Preco,JTextField Fornecedor) {
        // Peca.excluirPeca(tcs, tcs, tcs, tcs, tcs, incData);
    }

    /**
     * @param Quantidade Setar uma informação de valor JText de Quantidade
     * @param Produto Setar uma informação de valor JText de Tipo de Produto
     * @param Comprimento Setar uma informação de valor JText de Comprimento
     * @param Largura Setar uma informação de valor JText de Largura
     * @param Espessura Setar uma informação de valor JText de Espessura
     * @param Preco Setar uma informação de valor JText de Preço
     * @param Fornecedor Setar uma informação de valor JText de login do
     * fornecedor
     */
    public static void DELETEPedaco(JTextField Quantidade, JTextField Produto, JTextField Comprimento,
            JTextField Largura, JTextField Espessura, JTextField Preco,JTextField Fornecedor) {
        // Pedaco.excluirPedaco(tcs, tcs, tcs, tcs, tcs, incData);
    }

    /**
     * @param Quantidade Setar uma informação de valor JText de Quantidade
     * @param Produto Setar uma informação de valor JText de Tipo de Produto
     * @param Comprimento Setar uma informação de valor JText de Comprimento
     * @param Largura Setar uma informação de valor JText de Largura
     * @param Espessura Setar uma informação de valor JText de Espessura
     * @param Preco Setar uma informação de valor JText de Preço
     * @param Fornecedor Setar uma informação de valor JText de login do
     * fornecedor
     */
    public static void SEACHChapa(JTextField Quantidade, JTextField Produto, JTextField Comprimento, JTextField Largura,
            JTextField Espessura, JTextField Preco,JTextField Fornecedor) {
        Chapa.pesquisarChapa(tcs, tcs, tcs, tcs, tcs, tipoMaterial, fornecedor, true);
    }

    /**
     * @param login Setar uma informação de valor JText de login
     * @param senha Setar uma informação de valor JText de senha
     * @param conSenha Setar uma informação de valor JText de confimação de
     * senha
     * @param TipoPessoa Setar uma informação de valor JText de tipo de pessoa
     * @param nome Setar uma informação de valor JText de nome
     * @param documento Setar uma informação de valor JText de documento
     */
    public static void SEACHCliente(JTextField login, JTextField senha, JTextField conSenha,
            JComboBox<String> TipoPessoa, JTextField nome, JTextField documento) {
        try {
            // if (Cliente.campoDiferente(senha.getText(), conSenha.getText(), getTcs())) {
            Cliente.pesquisarCliente(login.getText());
            SetarCliente(login, senha, conSenha, TipoPessoa, nome, documento);
            // } else {
            // Messagem.chamarTela(Cliente.CampoDiferente(senha.getText(),
            // conSenha.getText(), getTcs()));
            // }
        } catch (NullPointerException npe) {
            Messagem.chamarTela("Cliente Pesquisa " + npe);

        }
    }

    /**
     * @param login Setar uma informação de valor JText de login
     * @param senha Setar uma informação de valor JText de senha
     * @param conSenha Setar uma informação de valor JText de confimação de
     * senha
     * @param TipoPessoa Setar uma informação de valor JText de tipo de pessoa
     * @param nome Setar uma informação de valor JText de nome
     * @param documento Setar uma informação de valor JText de documento
     */
    public static void SEACHFornecedor(JTextField login, JTextField senha, JTextField conSenha,
            JComboBox<String> TipoPessoa, JTextField nome, JTextField documento) {
        // if (Cliente.campoDiferente(senha.getText(), conSenha.getText(), getTcs())) {
        Fornecedor.pesquisarFornecedor(login.getText());
        SetarFornecedor(login, senha, conSenha, TipoPessoa, nome, documento);
        // } else {
        // Messagem.chamarTela(Cliente.CampoDiferente(senha.getText(),
        // conSenha.getText(), getTcs()));
        // }
    }

    /**
     * @param Quantidade Setar uma informação de valor JText de Quantidade
     * @param Produto Setar uma informação de valor JText de Tipo de Produto
     * @param Comprimento Setar uma informação de valor JText de Comprimento
     * @param Largura Setar uma informação de valor JText de Largura
     * @param Espessura Setar uma informação de valor JText de Espessura
     * @param Preco Setar uma informação de valor JText de Preço
     * @param Fornecedor Setar uma informação de valor JText de login do
     * fornecedor
     */
    public static void SEACHPeca(JTextField Quantidade, JTextField Produto, JTextField Comprimento, JTextField Largura,
            JTextField Espessura, JTextField Preco,JTextField Fornecedor) {
        Peca.pesquisarPeca(tcs, tcs, tcs, tcs, tcs, tipoMaterial, fornecedor);
    }

    /**
     * @param Quantidade Setar uma informação de valor JText de Quantidade
     * @param Produto Setar uma informação de valor JText de Tipo de Produto
     * @param Comprimento Setar uma informação de valor JText de Comprimento
     * @param Largura Setar uma informação de valor JText de Largura
     * @param Espessura Setar uma informação de valor JText de Espessura
     * @param Preco Setar uma informação de valor JText de Preço
     * @param Fornecedor Setar uma informação de valor JText de login do
     * fornecedor
     */
    public static void SEACHPedaco(JTextField Quantidade, JTextField Produto, JTextField Comprimento,
            JTextField Largura, JTextField Espessura, JTextField Preco,JTextField Fornecedor) {
        // Pedaco.pesquisarPedaco(tcs, tcs, tcs, tcs, tcs, incData, true);
    }

    /**
     * Este metodo desablitar a ediçao do paramentro de valor Jtext e Mudar o
     * texto do paramentro de valor JLabel mediante a informação setada atraves
     * do paramento de valor JComboBox.
     * <p>
     * sendo <b>TipoPessoa</b>:</p> <p>se <b>TipoPessoa</b> for iqual a "pf" será
     * habilitado o parametro <b>documento</b> e mudará o texto do parametro
     * para "N° CPF:",</p> ou se <b>TipoPessoa</b> for iqual a "pj" será habilitado
     * o parametro <b>documento</b> e mudará o texto do parametro para "N°
     * CNPJ:",<p> ou se <b>TipoPessoa</b> Nâo for iqual a "pj" ou a "pf" será
     * Desabilitado o parametro <b>documento</b> e mudará o texto do parametro
     * para "Documento:".</p>
     *
     * @param TipoPessoa Seta uma informação de valor JComboBox do tipo de
     * pessoa
     * @param ldocumento Retornar uma informação de valor JLabel do texto do
     * documento
     * @param documento Retornar uma informação de valor JText do texto do
     * documento
     */
    public static void DesabilitaeMudaDoc(JComboBox<String> TipoPessoa, JLabel ldocumento, JTextField documento) {
        try {
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
        } catch (NullPointerException npe) {
            documento.setEnabled(true);
            Messagem.chamarTela(npe);
        }
    }

    /**
     * Este Metodo Muda de Cor dos parametros do valor Jtext.
     * <p>
     * sendo confSenha iqual senha E os digitos da <b>senha</b> tem que ser
     * maior ou iqual a 7 E os digitos da <b>confSenha</b> tem que ser maior ou
     * iqual a 7 E</p>
     *
     * @param senha Setar uma informação de valor JText da Senha
     * @param confSenha Setar uma informação de valor JText da confimação Senha
     */
    public static void MudarCor(JTextField confSenha, JTextField senha) {
        if (confSenha.getText().equalsIgnoreCase(senha.getText()) && senha.getText().length() >= 7
                && confSenha.getText().length() >= 7) {
            confSenha.setBackground(Color.GREEN);
            senha.setBackground(Color.GREEN);
        } else {
            confSenha.setBackground(Color.red);
            senha.setBackground(Color.red);
        }
    }

    /**
     * Este metodo retornar os valores dos campos Vazios
     *
     * @param login Retornar uma informação de valor JText do login
     * @param senha Retornar uma informação de valor JText da Senha
     * @param conSenha Retornar uma informação de valor JText da Confirmação da
     * Senha
     * @param TipoPessoa Retornar uma informação de valor JText do tipo pessoa
     * @param nome Retornar uma informação de valor JText do nome
     * @param documento Retornar uma informação de valor JText da documentação
     */
    public static void LimpaDados(JTextField login, JTextField senha, JTextField conSenha, JComboBox<String> TipoPessoa,
            JTextField nome, JTextField documento) {
        login.setText("");
        senha.setText("");
        conSenha.setText("");
        TipoPessoa.setSelectedIndex(0);
        nome.setText("");
        documento.setText("");
    }

    /**
     * Este metodo retornar os valores dos campos do banco de dados da tabela
     * Cliente
     *
     * @param login Retornar uma informação de valor JText do login
     * @param senha Retornar uma informação de valor JText da Senha
     * @param conSenha Retornar uma informação de valor JText da Confirmação da
     * Senha
     * @param TipoPessoa Retornar uma informação de valor JText do tipo pessoa
     * @param nome Retornar uma informação de valor JText do nome
     * @param documento Retornar uma informação de valor JText da documentação
     */
    public static void SetarCliente(JTextField login, JTextField senha, JTextField conSenha,
            JComboBox<String> TipoPessoa, JTextField nome, JTextField documento) {
        login.setText(Cliente.getLogin());
        senha.setText(Cliente.getSenha());
        conSenha.setText(Cliente.getSenha());
        TipoPessoa.setSelectedItem(Cliente.getTipoPessoa());
        nome.setText(Cliente.getNome());
        documento.setText(Cliente.getDocum());
    }

    /**
     * Este metodo retornar os valores dos campos do banco de dados da tabela
     * Fornecedor
     *
     * @param login Retornar uma informação de valor JText do login
     * @param senha Retornar uma informação de valor JText da Senha
     * @param conSenha Retornar uma informação de valor JText da Confirmação da
     * Senha
     * @param TipoPessoa Retornar uma informação de valor JText do tipo pessoa
     * @param nome Retornar uma informação de valor JText do nome
     * @param documento Retornar uma informação de valor JText da documentação
     */
    public static void SetarFornecedor(JTextField login, JTextField senha, JTextField conSenha,
            JComboBox<String> TipoPessoa, JTextField nome, JTextField documento) {
        login.setText(Fornecedor.getLogin());
        senha.setText(Fornecedor.getSenha());
        conSenha.setText(Fornecedor.getSenha());
        TipoPessoa.setSelectedItem(Fornecedor.getTipoPessoa());
        nome.setText(Fornecedor.getNome());
        documento.setText(Fornecedor.getDocum());
    }

    /**
     * Este Metodo inserir o usuario Zero nas tabelas Cliente e Fornecedor.
     */
    public static void UsuarioZERO() {
        JTextField login = null, senha = null, conSenha = null, nome = null, documento = null;
        JComboBox<String> TipoPessoa = null;
        login.setText("System");
        senha.setText(login + "32");
        conSenha.setText(senha.toString());
        nome.setText(login.toString());
        documento.setText("00000000");
        TipoPessoa.setToolTipText(String.valueOf(login));
        ADDCliente(login, senha, conSenha, TipoPessoa, nome, documento);
        ADDFornecedor(login, senha, conSenha, TipoPessoa, nome, documento);
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

    /**
     * Este metodo setar uma informação de valor JText e Retorna uma informação
     * de valor inteiro
     *
     * @param text Setar uma informação de valor JText da varivel
     * @return Retornar uma informação de valor Inteiro da variavel
     */
    public static int deJTextparaInt(JTextField text) {
        return Integer.valueOf(text.getText());
    }

    /**
     * Este metodo setar uma informação de valor JText e Retorna uma informação
     * de valor Double
     *
     * @param text Setar uma informação de valor JText da varivel
     * @return Retornar uma informação de valor Double da variavel
     */
    public static Double deJTextparaDouble(JTextField text) {
        return Double.valueOf(text.getText());
    }

    /**
     * Este metodo Setará informações de Valor JText mediante a selecionamento
     * da linha determinada Tabela.
     *
     * @param Quantidade Retornar uma informação de valor JText da Quantidade
     * @param Produto Retornar uma informação de valor JText do Tipo de Produto
     * @param Comprimento Retornar uma informação de valor JText do Comprimento
     * do produto
     * @param Largura Retornar uma informação de valor JText da largura do
     * Produto
     * @param Espessura Retornar uma informação de valor JText da Espessura do
     * Produto
     * @param Preco Retornar uma informação de valor JText da Preço do Produto
     * @param tab Setar informar de valor Jtable
     *
     *
     */
    public static void SetarCampo(JTextField Quantidade, JTextField Produto, JTextField Comprimento, JTextField Largura,
            JTextField Espessura, JTextField Preco, JTable tab) {
        int row = tab.getSelectedRow(), n = -1, comp = n, larg = n, espe = n, qua = n, pro = n, pre = n;
        for (int i = 0; i < tab.getColumnCount(); i++) {
            if (tab.getColumnName(i).equalsIgnoreCase("Comprimento")) {
                comp = i;
            } else if (tab.getColumnName(i).equalsIgnoreCase("Largura")) {
                larg = i;
            } else if (tab.getColumnName(i).equalsIgnoreCase("Espessura")) {
                espe = i;
            } else if (tab.getColumnName(i).equalsIgnoreCase("Quantidade")) {
                qua = i;
            } else if (tab.getColumnName(i).equalsIgnoreCase("Produto")) {
                pro = i;
            } else if (tab.getColumnName(i).equalsIgnoreCase("Preço")
                    || tab.getColumnName(i).equalsIgnoreCase("Preco")) {
                pre = i;
            }
        }

        if (comp != n) {
            Comprimento.setText(String.valueOf(tab.getValueAt(row, comp)));
        } else {
            Comprimento.setText("0");
        }
        if (larg != n) {
            Largura.setText(String.valueOf(tab.getValueAt(row, larg)));
        } else {
            Largura.setText("0");
        }
        if (espe != n) {
            Espessura.setText(String.valueOf(tab.getValueAt(row, espe)));
        } else {
            Espessura.setText("0");
        }
        if (pre != n) {
            Preco.setText(String.valueOf(tab.getValueAt(row, pre)));
        } else {
            Preco.setText("0");
        }
        if (pro != n) {
            Produto.setText(String.valueOf(tab.getValueAt(row, pro)));
        } else {
            Produto.setText("0");
        }
        if (qua != n) {
            Quantidade.setText(String.valueOf(tab.getValueAt(row, qua)));
        } else {
            Quantidade.setText("0");
        }

    }
}
