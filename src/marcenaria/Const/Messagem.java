/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marcenaria.Const;

import javax.swing.JOptionPane;

/**
 *
 * @author Carlos Eduardo dos Santos Figueiredo
 * @since 20/10/2018
 * @version 1.0
 */
public class Messagem {

    private static int deleta = 3, criada = 3;
private String[] vazia = new String[1];
//Input
    /**
     * Este metodo retornar um texto:
     * <p>
     * <b>
     * "ad Foi adicionado com sucesso !!!"</b>
     *
     * @version 1.0
     * @param ad Informar um valor String na Messagem Adicionar.
     * @return Retornar a Messagem (ad Foi adicionado com sucesso !!!)
     */
    public static final String ADICIONADO(String ad) {
        String mens = ad + " Foi Adicionado com sucesso !!!";
        return mens;
    }

    /** <h1>Este metodo retornar um texto:</h1><p>
     * <b>
     * "cr Foi criada com sucesso !!!"</b></p>
     *
     * @version 1.0
     * @param cr Informar um valor String na Messagem Criar.
     * @return Retornar a Messagem (cr Foi criada com sucesso !!!)
     */
    public static final String CRIADO(String cr) {
        String mens = cr + " Foi Criada com sucesso !!!";
        return mens;
    }

    /**
     * Este metodo retornar um texto:\n "ed Foi editada com sucesso !!!"
     *
     * @version 1.0
     * @param ed Informar um valor String na Messagem Editada.
     * @return Retornar a Messagem (ed Foi editada com sucesso !!!)
     */
    public static final String EDITADO(String ed) {
        String mens = ed + " Foi Editado com sucesso !!!";
        return mens;
    }

    /**
     * Este metodo retornar um texto:\n "ex Foi excluido com sucesso !!!"
     *
     * @version 1.0
     * @param ex Informar um valor String na Messagem Excluido.
     * @return Retornar a Messagem (ex Foi excluido com sucesso !!!)
     */
    public static final String EXCLUIDO(String ex) {
        String mens = ex + " Foi Excluido com sucesso !!!";
        return mens;
    }

    /**
     * Este metodo retornar um texto:\n "im Foi imprimido com sucesso !!!"
     *
     * @version 1.1
     * @param im Informar um valor String na Messagem Imprimido.
     * @return Retornar a Messagem (im Foi imprimido com sucesso !!!)
     */
    public static final String IMPRIMIDO(String im) {
        String mens = im + " Foi Imprimido com Sucesso !!!";
        return mens;
    }

    /**
     * Este Metodo retornar um texto:\n "Campo va esta vazio"
     *
     * @version 1.2
     * @param va Informar um valor String na Messagem Vazio.
     * @return Retornar a Messagem (Campo va esta vazio);
     */
    public static final String VAZIO(String va) {
        String mens = "Campo " + va + " esta vazio";
        return mens;
    }

    /**
     * Este metodo obtem uma Array de valor String e retonar uma Informação com
     * valor do tipo String dos campos vazio
     *
     * @version 1.2
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

    /**
     * Este metodo Retornar um texto: "Tabela tabela Criada com sucesso !!!"
     *
     * @version 1.3
     * @param tabela Informar um valor String com nome da Tabela.
     * @return Retornar (Tabela tabela Criada com sucesso !!!).
     */
    public static final String tabelaCriada(String tabela) {
        return "Tabela " + tabela + " Criada com sucesso !!!";
    }

    /**
     * Este metodo Retornar um texto: "Tabela tabela Deletada com sucesso !!!"
     *
     * @version 1.3
     * @param tabela Informar um valor String com nome da Tabela
     * @return Retornar (Tabela tabela Deletada com sucesso !!!).
     */
    public static final String tabelaDeletada(String tabela) {
        return "Tabela " + tabela + " Deletada com sucesso !!!";
    }
//Input
//Tela

    /**
     * Este metodo Chama a Metodo da Classe informado pelo parametro
     *
     * @version 1.3
     * @param o Informar um valor de Class na Messagem da Tela
     *
     */
    public static void chamarTela(Class<Object> o) {
        JOptionPane.showMessageDialog(null, o.getSimpleName());
    }

    /**
     * Este metodo Chama a Metodo da Classe informado pelo parametro
     *
     * @version 1.3
     * @param o Informar um Valor de Objeto na Messagem da Tela.
     */
    public static void chamarTela(Object o) {
        JOptionPane.showMessageDialog(null, o);
    }

    /**
     * Este metodo Chama a Metodo da Classe informado pelo parametro
     *
     * @version 1.3
     * @param o Informar um valor String na Messagem da Tela
     */
    public static void chamarTela(String o) {
        JOptionPane.showMessageDialog(null, o);
    }

    /**
     * Este metodo exibe uma tela de confimação sendo que a opção so pode ser
     * "deleta ou Deleta" ou "" Chama a Metodo da Classe informado pelo
     * parametro
     *
     * @version 1.3
     * @param mens Informar um valor String para Messagem.
     * @param title Informar um valor String para Titulo.
     * @param opcao Informar um valor String para Opção:
     * <ul>
     * <li>deleta ou Deleta - Utilizar um metodo Auxiliar
     * <code>setDeleta(int deleta)</code>
     * <ul>onde o getDeleta():
     * <li>se for <b>-1</b>: Houve uma ação de Cancelamento.
     * <li>se for <b>0</b>: Houve uma ação e obteve sucesso.
     * <li>se for <b>2</b>: Houve o fechamento da tela.
     * <li>se for <b>3</b>: Houve algum erro e não foi atualizado.
     * </ul>
     * ou
     * <li>criada ou Criada - Utilizar um metodo Auxiliar
     * <code>setCriada(int criada)</code>
     * <ul>onde o getCriada():
     * <li>se for <b>-1</b>: Houve uma ação de Cancelamento.
     * <li>se for <b>0</b>: Houve uma ação e obteve sucesso.
     * <li>se for <b>2</b>: Houve o fechamento da tela.
     * <li>se for <b>3</b>: Houve algum erro e não foi atualizado.
     * </ul>
     * </ul>
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
            default:
                JOptionPane.showMessageDialog(null, "Opçao invalida no Metodo ChamaTelaConfirma");
                break;
        }
    }

    /**
     * Este metodo Pergunta ser desejar Criar uma tabela, utilizar um metodo
     * auxiliar chamarTelaConfirma(mens,title,opcao)
     *
     * @version 1.3
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
     * @version 1.3
     * @param tabela Informar um valor String com nome da Tabela
     */
    public static void deletadaTabela(String tabela) {
        String opcao = "deleta";
        String mens = "Deseja Excluir a Tabela " + tabela + " !!", title = tabela;
        chamarTelaConfirma(mens, title, opcao);
    }
//Tela
//Gets e Sets

    /**
     * Este metodo Retornar um valor inteiro na Variavel de Controle de
     * Deletação.
     * <ul>onde o getDeleta():
     * <li>se for <b>-1</b>: Houve uma ação de Cancelamento.
     * <li>se for <b>0</b>: Houve uma ação e obteve sucesso.
     * <li>se for <b>2</b>: Houve o fechamento da tela.
     * <li>se for <b>3</b>: Houve algum erro e não foi atualizado.
     * </ul>
     *
     * @version 1.3
     * @return Retornar um valor inteiro na Variavel de Controle de Deletação.
     */
    public static int getDeleta() {
        return deleta;
    }

    /**
     * Este metodo Setar Informar um valor inteiro na Variavel de Controle de
     * Deletação.
     *
     * @version 1.3
     * @param deleta Setar Informar um valor inteiro na Variavel de Controle de
     * Deletação.
     */
    public static void setDeleta(int deleta) {
        Messagem.deleta = deleta;
    }

    /**
     * Este metodo Retornar um valor inteiro na Variavel de Controle de Criação.
     * <ul>onde o getCriada():
     * <li>se for <b>-1</b>: Houve uma ação de Cancelamento.
     * <li>se for <b>0</b>: Houve uma ação e obteve sucesso.
     * <li>se for <b>2</b>: Houve o fechamento da tela.
     * <li>se for <b>3</b>: Houve algum erro e não foi atualizado.
     * </ul>
     *
     * @version 1.3
     * @return Retornar um valor inteiro na Variavel de Controle de Criação.
     */
    public static int getCriada() {
        return criada;
    }

    /**
     * Este metodo Setar Informar um valor inteiro na Variavel de Controle de
     * Criação.
     *
     * @version 1.3
     * @param criada Setar Informar um valor inteiro na Variavel de Controle de
     * Criação.
     */
    public static void setCriada(int criada) {
        Messagem.criada = criada;
    }
//Gets e Sets
    
}
