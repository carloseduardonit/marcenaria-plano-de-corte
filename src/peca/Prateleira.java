/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peca;

/**
 *
 * @author Carlos
 */
public class Prateleira extends Peca {

    public Prateleira(int quantPeca, long comprimento, long largura, long espessura, String descrição) {
        super(quantPeca, comprimento, largura, espessura, descrição);
    }

    public Prateleira(int quantPeca, long comprimento, long largura) {
        super(quantPeca, comprimento, largura, espessuraMDFMM.MM18.espessuraMDF, tipoPeca.PRA.toString());
    }

    @Override
    public String toString() {
        return this.getDescrição() + "\n\n"
                + "Quantidade de " + Prateleira.class.getSimpleName() + ": " + this.getQuantPeca() + (this.getQuantPeca() <= 1 ? " unidade" : " unidades")
                + "\nComprimento: " + this.getComprimento()
                + " CM\nLargura: " + this.getLargura()
                + " CM\nEspessura: " + this.getEspessura()
                + " MM\n\n";
    }

    @Override
    public void exibir(int quantPeca, long comprimento, long largura, long espessura, String descrição) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNome() {
        return Peca.getNome(this);
    }

}
