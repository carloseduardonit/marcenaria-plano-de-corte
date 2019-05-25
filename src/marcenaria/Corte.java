/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marcenaria;

import java.sql.*;
import javax.swing.JOptionPane;
import marcenaria.dado.ModuloConector;

/**
 *
 * @author Carlos
 */
public class Corte {

    public static void main(String[] args) {
        PecaParaCortar(220, 160, 220,
                 160, true);
        dividirCorte(0, 0, 0, 0, 0);
    }
    static Connection conexao;
    static PreparedStatement pst;
    static Statement stmt;
    static ResultSet rs;
    private static final String TABELA = Corte.class.getSimpleName();
    private static double[] compriCorte = new double[2];
    private static double[] largCorte = new double[compriCorte.length];

    /**
     * *
     */
    public static void Corte() {
        conexao = ModuloConector.getConecction();
    }

    /**
     *
     */
    public static void planodeCorte() {

    }

    /**
     * @param comprChapa
     * @param largChapa
     * @param precChapa
     * @param compPeca
     * @param largPeca
     * @return
     */
    public static double precoPecaParaCortar(double comprChapa, double largChapa, double precChapa, double compPeca, double largPeca) {

        double preco;
        if (precChapa < 0 || compPeca > comprChapa || largPeca > largChapa) {
            preco = 0;
        } else {
            preco = precChapa * (compPeca / comprChapa) * (largPeca / largChapa);
        }
        return preco;
    }

    /**
     * @param comprChapa
     * @param largChapa
     * @param compPeca
     * @param largPeca
     * @param rot
     */
    public static void PecaParaCortar(double comprChapa, double largChapa, double compPeca, double largPeca, boolean rot) {
        for (int i = 0; i < getCompriCorte().length; i++) {
            if (largChapa < largPeca && comprChapa > largPeca && largChapa > compPeca && rot == true) {
                //se a largura da chapa for menor que a largura da peça E
                //se o comprimento da chapa for maior que a largura da peça E
                //se a largura da chapa for maior que o comprimento da peca E
                // se rot for true
                setCompriCorte(i, comprChapa, largPeca);
                setLargCorte(i, largChapa, compPeca);
            } else if (largChapa < largPeca && comprChapa > largPeca && largChapa > compPeca && rot == false) {
                //se a largura da chapa for menor que a largura da peça E
                //se o comprimento da chapa for maior que a largura da peça E
                //se a largura da chapa for maior que o comprimento da peca E
                // se rot for false
                String frase = "";
                if (largChapa < largPeca) {
                    frase = "A Medida da Largura da peca: " + largPeca
                            + "\ne Maior a Medida da Largura da Chapa: " + largChapa;
                } else {
                    frase = "A Medida da Largura da peca: " + largPeca
                            + "\ne Menor ou Iqual a Medida da Largura da Chapa: " + largChapa;
                }
                if (comprChapa < compPeca) {
                    frase += "\nA Medida da Comprimento da peca: " + compPeca
                            + "\ne Maior a Medida da Comprimento da Chapa: " + comprChapa;
                } else {
                    frase += "\nA Medida da Comprimento da peca: " + compPeca
                            + "\ne Menor ou Iqual a Medida da Comprimento da Chapa: " + comprChapa;
                }
                JOptionPane.showMessageDialog(null, frase);
            } else if(comprChapa==compPeca&&largChapa==largPeca){
                
            }else {
                setCompriCorte(i, comprChapa, compPeca);
                setLargCorte(i, largChapa, largPeca);
                String a = " c: " + getCompriCorte(i) + " l: " + getLargCorte(i);
                JOptionPane.showMessageDialog(null, a);
            }
        }
    }

    /**
     * @return
     */
    public static double[] getCompriCorte() {
        return compriCorte;
    }

    /**
     * @param pos
     * @return
     */
    public static double getCompriCorte(int pos) {
        return compriCorte[pos];
    }

    /**
     * @param compriCorte
     */
    public static void setCompriCorte(double[] compriCorte) {
        Corte.compriCorte = compriCorte;
    }

    /**
     * @param pos
     * @param compriCorte
     */
    public static void setCompriCorte(int pos, double compriCorte) {
        Corte.compriCorte[pos] = compriCorte;
    }

    /**
     *
     * @param pos
     * @param comprChapa
     * @param comprPeca
     */
    public static void setCompriCorte(int pos, double comprChapa, double comprPeca) {
        if (comprPeca > comprChapa || comprPeca <= 0 || comprChapa <= 0) {
            Corte.compriCorte[pos] = -1;
        } else {
            Corte.compriCorte[pos] = comprChapa - comprPeca;
        }
    }

    /**
     * @return
     */
    public static double[] getLargCorte() {
        return largCorte;
    }

    /**
     * @param pos
     * @return
     */
    public static double getLargCorte(int pos) {
        return largCorte[pos];
    }

    /**
     * @param largCorte
     */
    public static void setLargCorte(double[] largCorte) {
        Corte.largCorte = largCorte;
    }

    /**
     * @param pos
     * @param largCorte
     */
    public static void setLargCorte(int pos, double largCorte) {
        Corte.largCorte[pos] = largCorte;
    }

    /**
     * @param pos
     * @param largChapa
     * @param largPeca
     */
    public static void setLargCorte(int pos, double largChapa, double largPeca) {
        if (largPeca > largChapa || largPeca <= 0 || largChapa <= 0) {
            Corte.largCorte[pos] = -1;
        } else {
            Corte.largCorte[pos] = largChapa - largPeca;
        }

    }

    /**
     * @param pos
     * @param largChapa
     * @param largPeca
     * @param comprChapa
     */
    public static void horizontalCorte(int pos, double largChapa, double largPeca, double comprChapa) {
        setLargCorte(pos, largChapa, largPeca);
        setCompriCorte(pos, comprChapa);
    }

    /**
     * @param pos
     * @param comprChapa
     * @param comprPeca
     * @param largChapa
     */
    public static void verticalCorte(int pos, double comprChapa, double comprPeca, double largChapa) {
        setCompriCorte(pos, comprChapa, comprPeca);
        setLargCorte(pos, largChapa);
    }
    public static void dividirCorte(int pos, double comprChapa, double comprPeca, double largChapa,double largPeca){
        for(int i=0;i<=1;i++){
            if(i==0){
                verticalCorte(pos, comprChapa, comprPeca, largChapa);
            }else{
                horizontalCorte(pos, largChapa, largPeca, comprChapa);
            }
        }
    }
}
