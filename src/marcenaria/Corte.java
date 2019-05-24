/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marcenaria;

import java.sql.*;
import marcenaria.dado.ModuloConector;

/**
 *
 * @author Carlos
 */
public class Corte {

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
            if (largChapa < largPeca && comprChapa > largPeca && comprChapa > largPeca && rot == true) {
                //
                setCompriCorte(i, comprChapa, largPeca);
                setLargCorte(i, largChapa, compPeca);
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
        if (largPeca > largChapa || largPeca < 0 || largChapa < 0) {
            Corte.largCorte[pos] = -1;
        } else {
            Corte.largCorte[pos] = largChapa - largPeca;
        }

    }
}
