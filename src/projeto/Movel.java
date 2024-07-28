/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import peca.Base;

/**
 *
 * @author Carlos
 */
public class Movel extends Projeto {
//medidas 90X150X40

    public Movel() {
        super();
    }

    public Movel(long altura, long comprimento, long largura, long espessura, String deStrin) {
        super(altura, comprimento, largura, espessura, deStrin);
    }

    public Movel(long altura, long comprimento, long largura, long espessura) {
        super(altura, comprimento, largura, espessura);
    }

    @Override
    public String toString() {
        String a = "";
        super.toString();
        if (lt != null) {
            a += lt.toString();
        }
        if (bases != null) {
            for (Base base : bases) {
                a += base.toString();
            }
        } else if (bs != null) {
            a += bs.toString();
        }
        if (fd != null) {
            a += fd.toString();
        }
        if (tp != null) {
            a += tp.toString();
        }
        if (po != null) {
            a += po.toString();
        }
        if (pr != null) {
            a += pr.toString();
        }
        return a;
    }

}
