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
public class MMpara extends Para {

    public static void main(String[] args) {
        System.out.println("mm " + MM(10) + "\ncm: " + CM(10)
                + "\nmt: " + MT(10));
    }

    public static double MT(double mm) {
        return Para.divisorMedida(mm, 100);
    }

    public static double CM(double mm) {
        return Para.divisorMedida(mm, 10);
    }

    public static double MM(double mm) {
        return Para.multiplicadorMedida(mm, 1);
    }
}
