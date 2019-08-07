/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marcenaria.pessoa;

import java.awt.Dimension;

/**
 *
 * @author Carlos
 */
public class Layout {

    private static Dimension Minimo, Maximo, Perfil;

    public static Dimension getMinimo() {
        return Minimo;
    }

    public static void setMinimo(Dimension Minimo) {
        Layout.Minimo = Minimo;
    }

    public static void setMinimo(int w, int h) {
        Layout.Minimo.setSize(w, h);
    }

    public static void setMinimo(double w, double h) {
        Layout.Minimo.setSize(w, h);
    }

    public static Dimension getMaximo() {
        return Maximo;
    }

    public static void setMaximo(Dimension Maximo) {
        Layout.Maximo = Maximo;
    }

    public static void setMaximo(int w, int h) {
        Layout.Maximo.setSize(w, h);
    }

    public static void setMaximo(double w, double h) {
        Layout.Maximo.setSize(w, h);
    }

    public static Dimension getPerfil() {
        return Perfil;
    }

    public static void setPerfil(Dimension Perfil) {
        Layout.Perfil = Perfil;
    }

    public static void setPerfil(int w, int h) {
        Layout.Perfil.setSize(w, h);
    }

    public void setPerfil(double w, double h) {
        Layout.Perfil.setSize(w, h);
    }
}
