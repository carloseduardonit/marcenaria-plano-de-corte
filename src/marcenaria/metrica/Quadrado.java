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
public class Quadrado {

    private static double lado, area, perimetro;

    /**
     * 
     * @return Retornar um valor double do lado
     */
    public static double getLado() {
        return lado;
    }

    /**
     * 
     * @param lado Informar um valor double no lado.
     */
    public void setLado(double lado) {
        this.lado = lado;
    }

    /**
     * 
     * @param area  Informar um valor double na area
     */
    public static void setLadodeArea(double area) {
        Quadrado.lado = area / 2;
    }

    /**
     * 
     * @param area Informar um valor double na area
     */
    public static void setLadodePerimetro(double area) {
        Quadrado.lado = area / 2;
    }

    /**
     * 
     * @return Retornar um valor double da area
     */
    public static double getArea() {
        return area;
    }

    /**
     * 
     * @param area Informar um valor double na area
     */
    public static void setArea(double area) {
        Quadrado.area = area;
    }

    /**
     * 
     * @param perimetro Informar um valor double no perimetro.
     */
    public static void setAreadePerimetro(double perimetro) {
        Quadrado.area = area;
    }

    /**
     * 
     * @param lado Informar um valor double no lado.
     */
    public static void setAreadeLado(double lado) {
        Quadrado.area = area;
    }

    /**
     * 
     * @return Retornar um valor double  do perimetro
     */
    public static double getPerimetro() {
        return perimetro;
    }

    /**
     * 
     * @param perimetro Informar um valor double no perimetro.
     */
    public static void setPerimetro(double perimetro) {
        Quadrado.perimetro = perimetro;
    }

    /**
     * Tem que Fazer
     * @param lado Informar um valor double no lado.
     */
    public static void setPerimetrodeLado(double lado) {
        Quadrado.perimetro = Math.pow(lado, 4);
    }

    /**
     * Tem que Fazer
     * @param area Informar um valor double na area
     */
    public static void setPerimetrodeArea(double area) {
        Quadrado.perimetro = perimetro;
    }
}
