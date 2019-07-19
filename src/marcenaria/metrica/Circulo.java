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

    /**Este Metodo Retornar uma informação de valor double do raio do Circulo
     * @return Retornar uma informação de valor double do raio do Circulo
     */
    public static double getRaio() {
        return raio;
    }

    /**Este Metodo Setar uma informação de valor double do raio do Circulo
     * @param raio Setar uma informação de valor double do raio do Circulo
     */
    public static void setRaio(double raio) {
        Circulo.raio = raio;
    }

    /**Este Metodo Retornar uma informação de valor double do Perimetro do Circulo
     * @return Retornar uma informação de valor double do Perimetro do Circulo 
     */
    public static double getPerimento() {
        return perimento;
    }

    /**Este Metodo Setar uma informação de valor double do Perimetro do Circulo
     * @param perimento Setar uma informação de valor double do Perimetro do Circulo
     */
    public static void setPerimento(double perimento) {
        Circulo.perimento = perimento;
    }

    /**Este Metodo Retornar uma informação de valor double do Diametro do Circulo
     * @return Retornar uma informação de valor double do Diametro do Circulo
     */
    public static double getDiametro() {
        return diametro;
    }

    /**Este Metodo Setar uma informação de valor double do Diametro do Circulo
     * @param diametro Setar uma informação de valor double do Diametro do Circulo
     */
    public static void setDiametro(double diametro) {
        Circulo.diametro = diametro;
    }

    /**
     * Este Metodo Setar uma informação de valor double do Diametro do Circulo
     */
    public static void setDiametro() {
        Circulo.diametro = getRaio() * 2;
    }

    /**Este Metodo Setar uma informação de valor double do Diametro do Circulo
     * @param d Setar uma informação de valor double do Diametro do Circulo
     */
    public static void setDiametro(int d) {
        Circulo.diametro = getRaio() * 2 + 1;
    }

}
