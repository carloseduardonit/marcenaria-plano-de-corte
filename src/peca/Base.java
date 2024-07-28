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
public class Base extends Peca {

    public Base(int quantPeca, long comprimento, long largura, long espessura, String descrição) {
        super(quantPeca, comprimento, largura, espessura, descrição);
    }

    public Base(int quantPeca, long comprimento, long largura, long espessura) {
        super(quantPeca, comprimento, largura, espessura, tipoPeca.BAS.toString());
    }

    public Base(int quantPeca, long comprimento, long largura) {
        super(quantPeca, comprimento, largura, espessuraMDFMM.MM18.getEspessuraMDF(), tipoPeca.BAS.toString());
    }

    @Override
    public String toString() {

        return this.getDescrição() + "\n\n"
                + "Quantidade de base: " + this.getQuantPeca() + (this.getQuantPeca() <= 1 ? " unidade" : " unidades")
                + "\nComprimento: " + this.getComprimento()
                + " CM\nLargura: " + this.getLargura()
                + " CM\nEspessura: " + this.getEspessura()
                + " MM\n\n";
    }

    @Override
    public void exibir(int quantPeca, long comprimento, long largura, long espessura, String descrição) {

    }

    @Override
    public String getNome() {
        return Peca.getNome(this);
    }

    public Pecas cadastrarPecas(Pecas peca) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
