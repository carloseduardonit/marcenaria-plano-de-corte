/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marcenaria.Const;

import javax.swing.JOptionPane;

/**
 *
 * @author Carlos
 * @since 20/10/2018
 */
public class Messagem {

    private static int deleta = 3, criada = 3;
    private String[] vazia = new String[1];
//input

    /**Este metodo retornar um texto:<p><b>
     * "ad Foi adicionado com sucesso !!!"</b>
     * @param ad Informar um valor String na Messagem Adicionar.
     * @return Retornar a Messagem (ad Foi adicionado com sucesso !!!)
     */
    public static final String ADICIONADO(String ad) {
        String mens = ad + " Foi Adicionado com sucesso !!!";
        return mens;
    }

    /**<h1>Este metodo retornar um texto:</h1><p><b>
     * "cr Foi criada com sucesso !!!"</b></p>
     * @param cr Informar um valor String na Messagem Criar.
     * @return Retornar a Messagem (cr Foi criada com sucesso !!!)
     */
    public static final String CRIADO(String cr) {
        String mens = cr + " Foi Criada com sucesso !!!";
        return mens;
    }

    /**Este metodo retornar um texto:\n
     * "ed Foi editada com sucesso !!!"
     * @param ed Informar um valor String na Messagem Editada.
     * @return Retornar a Messagem (ed Foi editada com sucesso !!!)
     */
    public static final String EDITADO(String ed) {
        String mens = ed + " Foi Editado com sucesso !!!";
        return mens;
    }

    /**Este metodo retornar um texto:\n
     * "ex Foi excluido com sucesso !!!"
     * @param ex Informar um valor String na Messagem Excluido.
     * @return Retornar a Messagem (ex Foi excluido com sucesso !!!)
     */
    public static final String EXCLUIDO(String ex) {
        String mens = ex + " Foi Excluido com sucesso !!!";
        return mens;
    }

    /**Este metodo retornar um texto:\n
     * "im Foi imprimido com sucesso !!!"
     * @param im Informar um valor String na Messagem Imprimido.
     * @return Retornar a Messagem (im Foi imprimido com sucesso !!!)
     */
    public static final String IMPRIMIDO(String im) {
        String mens = im + " Foi Imprimido com Sucesso !!!";
        return mens;
    }

    /** Este Metodo retornar  um texto:\n
	* "Campo va esta vazio"
     * @param va Informar um valor String na Messagem Vazio.
     * @return Retornar a Messagem (Campo va esta vazio);
     */
    public static final String VAZIO(String va) {
        String mens = "Campo " + va + " esta vazio";
        return mens;
    }

    /**
     * @param va Informar um Array String na Messagem Vazio.
     * @return Retornar a Messagem (Campos vazios: Array de va);
     */
    public static final String VAZIO(String[] va) {
        String mens = "Campos vazios:\n";
        for (int i = 0; i < va.length; i++) {
            if (i < va.length - 1) {
                mens += va[i] + ";\n";
            } else {
                mens += va[i] + ".";
            }
        }
        return mens;
    }
//Tabela

    /** Este metodo Retornar um texto:
     * "Tabela tabela Criada com sucesso !!!"
     * @param tabela Informar um valor String com nome da Tabela.
     * @return Retornar (Tabela tabela Criada com sucesso !!!).
     */
    public static final String tabelaCriada(String tabela) {
        return "Tabela " + tabela + " Criada com sucesso !!!";
    }

    /**
     * @param tabela Informar um valor String com nome da Tabela
     * @return Retornar (Tabela tabela Deletada com sucesso !!!).
     */
    public static final String tabelaDeletada(String tabela) {
        return "Tabela " + tabela + " Deletada com sucesso !!!";
    }

//Tela
    /**
     * @param o Informar um valor de Class na Messagem da Tela
     *
     */
    public static void chamarTela(Class o) {
        JOptionPane.showMessageDialog(null, o.getSimpleName());
    }

    /**
     * @param o Informar um Valor de Objeto na Messagem da Tela.
     */
    public static void chamarTela(Object o) {
        JOptionPane.showMessageDialog(null, o);
    }

    /**
     * @param o Informar um valor String na Messagem da Tela
     */
    public static void chamarTela(String o) {
        JOptionPane.showMessageDialog(null, o);
    }

    /**
     * @param mens Informar um valor String para Messagem.
     * @param title Informar um valor String para Titulo.
     * @param opcao Informar um valor String para Opção: deleta ou Deleta -
     * Utilizar um metodo Auxiliar setDeleta(int deleta) ou criada ou Criada -
     * Utilizar um metodo Auxiliar setCriada(int criada)
     */
    public static void chamarTelaConfirma(String mens, String title, String opcao) {
        int ver = JOptionPane.showConfirmDialog(null, mens, title, JOptionPane.OK_CANCEL_OPTION);
        switch (opcao) {
            case "deleta":
            case "Deleta":
                switch (ver) {
                    case 0:
                        Messagem.setDeleta(0);
                        break;
                    case -1:
                        Messagem.setDeleta(-1);
                        break;
                    case 2:
                        Messagem.setDeleta(2);
                        break;
                    default:
                        break;
                }
                break;
            case "criada":
            case "Criada":
                switch (ver) {
                    case 0:
                        Messagem.setCriada(ver);
                        break;
                    case -1:
                        Messagem.setCriada(ver);
                        break;
                    case 2:
                        Messagem.setCriada(ver);
                        break;
                    default:
                        break;
                }
                break;
        }
    }

    /**
     * Este metodo Pergunta ser desejar Criar uma tabela, utilizar um metodo
     * auxiliar chamarTelaConfirma(mens,title,opcao)
     *
     * @param tabela Informar um valor String com nome da Tabela
     */
    public static void criadoTabela(String tabela) {
        String opcao = "criada";
        String mens = "Deseja Criar a Tabela " + tabela + " !!", title = tabela;
        chamarTelaConfirma(mens, title, opcao);
    }

    /**
     * Este metodo pergunta ser desejar Deletar uma tabela, utilizar um metodo
     * auxiliar chamarTelaConfirma(mens,title,opcao)
     *
     * @param tabela Informar um valor String com nome da Tabela
     */
    public static void deletadaTabela(String tabela) {
        String opcao = "deleta";
        String mens = "Deseja Excluir a Tabela " + tabela + " !!", title = tabela;
        chamarTelaConfirma(mens, title, opcao);
    }

    
    /**
     * @return Retornar um valor inteiro na Variavel de Controle de Deletação.
     */
    public static int getDeleta() {
        return deleta;
    }

    /**
     * @param deleta Informar um valor inteiro na Variavel de Controle de
     * Deletação.
     */
    public static void setDeleta(int deleta) {
        Messagem.deleta = deleta;
    }

    /**
     * Este metodo Retornar um valor inteiro na Variavel de Controle de Criação.
     *
     * @return Retornar um valor inteiro na Variavel de Controle de Criação.
     */
    public static int getCriada() {
        return criada;
    }

    /**  Este metodo Setar Informar um valor inteiro na Variavel de Controle de
     * Criação.
     * @param criada  Setar Informar um valor inteiro na Variavel de Controle de
     * Criação.
     */
    public static void setCriada(int criada) {
        Messagem.criada = criada;
    }

}
