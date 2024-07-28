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
public abstract class Peca implements Pecas {

    private int quantPeca;
    private long altura, comprimento, largura, espessura;

    public enum tipoPeca {
        BAS("Base"), FUN("Fundo"), LAT("Lateral"), PRA("Prateleira"), POR("Porta"), TAM("Tampo");

        @Override
        public String toString() {
            return "Nome da Peca: " + tipo;
        }
        String tipo;

        private tipoPeca(String tipo) {
            this.tipo = tipo;
        }

    }

    public enum espessuraMDFMM {
        MM03(3), MM06(6), MM09(9), MM12(12), MM15(15), MM18(18), MM22(22);
        int espessuraMDF;

        private espessuraMDFMM(int espessuraMDF) {
            this.espessuraMDF = espessuraMDF;
        }

        public int getEspessuraMDF() {
            return espessuraMDF;
        }

    }
    private String descrição;
    private String nome;

    public abstract String getNome();

    public Peca() {

    }

    public Peca(int quantPeca, long comprimento, long largura, long espessura, String descrição) {
        this.quantPeca = quantPeca;
        this.comprimento = comprimento;
        this.largura = largura;
        this.espessura = espessura;
        this.descrição = descrição;
    }

    public Peca(int quantPeca, long comprimento, long largura) {

    }

    public abstract void exibir(int quantPeca, long comprimento, long largura, long espessura, String descrição);

    //public abstract void exibirTela(Peca peca);
    public int getQuantPeca() {
        return quantPeca;
    }

    public void setQuantPeca(int quantPeca) {
        this.quantPeca = quantPeca;
    }

    public long getComprimento() {
        return comprimento;
    }

    public void setComprimento(long comprimento) {
        this.comprimento = comprimento;
    }

    public long getLargura() {
        return largura;
    }

    public void setLargura(long largura) {
        this.largura = largura;
    }

    public long getEspessura() {
        return espessura;
    }

    public void setEspessura(long espessura) {
        this.espessura = espessura;
    }

    public String getDescrição() {
        return descrição;
    }

    public void setDescrição(String descrição) {
        this.descrição = descrição;
    }

    @Override
    public String toString() {
        return "Peca{" + "quantPeca=" + quantPeca + ", comprimento=" + comprimento + ", largura=" + largura + ", espessura=" + espessura + ", descri\u00e7\u00e3o=" + descrição + '}';
    }

    public static String getNome(Peca peca) {
        return peca.getClass().getSimpleName();
    }

}
