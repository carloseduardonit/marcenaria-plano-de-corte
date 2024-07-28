/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarcenaria;

import java.text.DecimalFormat;

/**
 *
 * @author carlos
 */
public class Compensado {
    
    
    private static int idCompensado, idLoja;
    private static double largura, comprimento, espessura, preco, frete;
    private static String tipo;

    private static double[] vcomprimento;
    private static double[] vlargura;
    private static double[][] vpreco;

    public static int getIdCompensado() {
        return idCompensado;
    }

    public static void setIdCompensado(int idCompensado) {
        Compensado.idCompensado = idCompensado;
    }

    public static int getIdLoja() {
        return idLoja;
    }

    public static void setIdLoja(int idLoja) {
        Compensado.idLoja = idLoja;
    }

    public static double getLargura() {
        return largura;
    }

    public static void setLargura(double largura) {
        Compensado.largura = largura;
    }

    public static double getComprimento() {
        return comprimento;
    }

    public static void setComprimento(double comprimento) {
        Compensado.comprimento = comprimento;
    }

    public static double getEspessura() {
        return espessura;
    }

    public static void setEspessura(double espessura) {
        Compensado.espessura = espessura;
    }

    public static double getPreco() {
        return preco;
    }

    public static void setPreco(double preco) {
        Compensado.preco = preco;
    }

    public static double getFrete() {
        return frete;
    }

    public static void setFrete(double frete) {
        Compensado.frete = frete;
    }

    public static String getTipo() {
        return tipo;
    }

    public static void setTipo(String tipo) {
        Compensado.tipo = tipo;
    }

    public static void adicinarCompensado() {
        Compensado.setIdCompensado(getIdCompensado() + 1);
        System.out.println("");
    }

    
    public static void compensadoDivisor(double comp, double larg, int div) {
        DecimalFormat df = new DecimalFormat();
        int c = (int) (comp / div);
        int l = (int) (larg / div);
        double preco = 100;
       df.applyPattern("R$ #,##0.00");
        Compensado.vpreco = new double[c][l];
        Compensado.vcomprimento = new double[c];
        Compensado.vlargura = new double[l];
        
        for (int i = 0; i < Compensado.vcomprimento.length; i++) {
            Compensado.vcomprimento[i] = div * (i + 1);
        }
        for (int i = 0; i < Compensado.vlargura.length; i++) {
            Compensado.vlargura[i] = div * (i + 1);
        }
        for (int i = 0; i < Compensado.vcomprimento.length; i++) {
            for (int j = 0; j < Compensado.vlargura.length; j++) {
                Compensado.imprimir(Compensado.vcomprimento[i], Compensado.vlargura[j]);
               double co =(Compensado.vcomprimento[i]/Compensado.vcomprimento[Compensado.vcomprimento.length-1])*100;
               double la =(Compensado.vlargura[j]/Compensado.vlargura[Compensado.vlargura.length-1])*100;
                System.out.print(" = "+df.format((preco*co/100)*la/100)+" \n");
            }
        }
    }

    public static void imprimir(double comp, double larg) {
        System.out.println(comp + "X" + larg);
    }
}
