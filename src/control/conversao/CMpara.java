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
public class CMpara extends Para {

    public static void main(String[] args) {
        System.out.println("mm " + MM(10) + "cm: " + CM(10)
                + "\nmt: " + MT(10));
    }

    public static double MT(double cm) {
        return Para.divisorMedida(cm, 100);
    }

    public static double CM(double cm) {
        return Para.divisorMedida(cm, 1);
    }

    public static double MM(double cm) {
        return Para.multiplicadorMedida(cm, 10);
    }
}
