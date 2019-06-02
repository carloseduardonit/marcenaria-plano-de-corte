/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marcenaria.metrica;

/**
 *
 * @author Carlos
 */
public class Circulo {

    double pi = Math.PI;
    private static double raio, perimento, diametro;

    /**
     * @return
     */
    public static double getRaio() {
        return raio;
    }

    /**
     * @param raio
     */
    public static void setRaio(double raio) {
        Circulo.raio = raio;
    }

    /**
     * @return
     */
    public static double getPerimento() {
        return perimento;
    }

    /**
     * @param perimento
     */
    public static void setPerimento(double perimento) {
        Circulo.perimento = perimento;
    }

    /**
     * @return
     */
    public static double getDiametro() {
        return diametro;
    }

    /**
     * @param diametro
     */
    public static void setDiametro(double diametro) {
        Circulo.diametro = diametro;
    }

    /**
     * 
     */
    public static void setDiametro() {
        Circulo.diametro = getRaio() * 2;
    }

    /**
     * @param d
     */
    public static void setDiametro(int d) {
        Circulo.diametro = getRaio() * 2 + 1;
    }

}
