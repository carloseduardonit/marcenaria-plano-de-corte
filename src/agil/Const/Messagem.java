/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agil.Const;

import javax.swing.JOptionPane;

/**
 *
 * @author Carlos
 * @since
 */
public class Messagem {

    private static int deleta = 3, criada = 3;
    private String [] vazia = new String [1];
//input

    /**
     * @param ad
     * @return
     */
    public static final String ADICIONADO(String ad) {
        String mens = ad + " Foi Adicionado com sucesso !!!";
        return mens;
    }

    /**
     * @param cr
     * @return
     */
    public static final String CRIADO(String cr) {
        String mens = cr + " Foi Criada com sucesso !!!";
        return mens;
    }

    /**
     * @param ed
     * @return
     */
    public static final String EDITADO(String ed) {
        String mens = ed + " Foi Editado com sucesso !!!";
        return mens;
    }

    /**
     * @param ex
     * @return
     */
    public static final String EXCLUIDO(String ex) {
        String mens = ex + " Foi Excluido com sucesso !!!";
        return mens;
    }

    /**
     * @param im
     * @return
     */
    public static final String IMPRIMIDO(String im) {
        String mens = im + " Foi Imprimido com Sucesso !!!";
        return mens;
    }

    /**
     * @param va
     * @return
     */
    public static final String VAZIO(String va) {
        String mens = "Campo " + va + " esta vazio";
        return mens;
    }

    /**
     * @param va
     * @return
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

    public static final String tabelaCriada(String tabela) {
        return "Tabela "+tabela+" Criada com sucesso !!!";
    }

    public static final String tabelaDeletada(String tabela) {
        return "Tabela "+tabela+" Deletada com sucesso !!!";
    }

//Tela
    /**
     * @param o
     *
     */
    public static void chamarTela(Class o) {
        JOptionPane.showMessageDialog(null, o.getSimpleName());
    }

    /**
     * @param o
     */
    public static void chamarTela(Object o) {
        JOptionPane.showMessageDialog(null, o);
    }

    /**
     * @param o
     */
    public static void chamarTela(String o) {
        JOptionPane.showMessageDialog(null, o);
    }

    /**
     * @param mens
     * @param title
     * @param opcao
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
     * @param tabela
     */
    public static void criadoTabela(String tabela) {
        String opcao = "criada";
        String mens = "Deseja Criar a Tabela " + tabela + " !!", title = tabela;
        chamarTelaConfirma(mens, title, opcao);
    }

    /**
     * @param tabela
     */
    public static void deletadaTabela(String tabela) {
        String opcao = "deleta";
        String mens = "Deseja Excluir a Tabela " + tabela + " !!", title = tabela;
        chamarTelaConfirma(mens, title, opcao);
    }

    /**
     * *
     * @param res
     * @return
     */
    public static int deletar(int res) {
        return res;
    }

    /**
     * @return
     */
    public static int getDeleta() {
        return deleta;
    }

    /**
     * @param deleta
     */
    public static void setDeleta(int deleta) {
        Messagem.deleta = deleta;
    }

    /**
     * @return
     */
    public static int getCriada() {
        return criada;
    }

    /**
     * @param criada
     */
    public static void setCriada(int criada) {
        Messagem.criada = criada;
    }

}
