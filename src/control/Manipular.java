/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.Scanner;

/**
 *
 * @author Carlos
 */
public abstract class Manipular {

    private final static Scanner entrada = new Scanner(System.in);

    public static int getEntrada() {
        return entrada.nextInt();
    }

    public static boolean isEntrada() {
        return entrada.nextBoolean();
    }

    public static boolean isEntradatoString() {
        boolean is = false, resposta = false;
        do {
            System.out.println("Responda: s ou sim ou n ou nao");
            String aString = entrada.next();
            switch (aString.toLowerCase()) {
                case "s":
                case "sim":
                    resposta = true;
                    is = true;
                    break;
                case "n":
                case "nao":
                    resposta = true;
                    is = false;
                    break;
                default:
                    System.out.println("Informou o valor errado !!!");
            }
        } while (!resposta);
        return is;
    }

}
