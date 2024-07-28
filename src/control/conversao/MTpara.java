/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.conversao;

/**
 *
 * @author Carlos
 */
public class MTpara extends Para {

    public static void main(String[] args) {
        System.out.println("mm " + MM(10) + "\ncm: " + CM(10)
                + "\nmt: " + MT(10));
    }

    public static double MT(double mt) {
        return Para.divisorMedida(mt, 1);
    }

    public static double CM(double mt) {
        return Para.multiplicadorMedida(mt, 100);
    }

    public static double MM(double mt) {
        return Para.multiplicadorMedida(mt, 1000);
    }
}
