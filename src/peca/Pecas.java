/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peca;

import control.Manipular;

/**
 *
 * @author Carlos
 */
public interface Pecas {

    /**
     *
     * @param peca
     * @return
     */
    public static Pecas cadastrarPecas() {
        Peca pec = null;
        int PecaInt = 0;
        long resComprimento = 0, resLargura = 0, resEspessura = 0;
        boolean pecaSair = true;
        System.out.println("A medida da " + "tem " + pec.getComprimento() + "X" + pec.getLargura() + "X" + pec.getEspessura()
        );
        if (Manipular.isEntradatoString()) {
            do {
                System.out.println("Quantas unidade Bases?");
                PecaInt = Manipular.getEntrada();
                if (PecaInt > 0) {
                    pec = new Base(PecaInt, pec.getComprimento(), pec.getLargura());
                    pecaSair = false;
                } else if (PecaInt == 0) {
                    pecaSair = false;
                }
            } while (pecaSair);
        } else {
            System.out.println("Quantas unidade " + pec.getNome() + "?");
            PecaInt = Manipular.getEntrada();
            System.out.println("Qual o comprimento da Base: ");
            resComprimento = Manipular.getEntrada();
            System.out.println("Qual a largura da Base: ");
            resLargura = Manipular.getEntrada();
            System.out.println("Qual a Espessua da Base: ");
            resEspessura = Manipular.getEntrada();
            pec = new Base(PecaInt, resComprimento, resLargura, resEspessura);
        }
        return pec;
    }
}
