/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import static control.Manipular.getEntrada;
import static control.Manipular.isEntradatoString;
import java.util.ArrayList;
import java.util.Scanner;
import peca.Base;
import peca.Fundo;
import peca.Lateral;
import peca.Peca;
import peca.Pecas;
import peca.Porta;
import peca.Prateleira;
import peca.Tampo;

/**
 *
 * @author Carlos
 */
public class Projeto {

    private Scanner entrada = new Scanner(System.in);
    private long altura, comprimento, largura, espessura;
    private String decrição;
    public Base bs;
    public Lateral lt;
    public Fundo fd;
    public Tampo tp;
    public Prateleira pr;
    public Porta po;
    ArrayList<Base> bases;
    ArrayList<Lateral> laterals;
    ArrayList<Fundo> fundos;
    ArrayList<Tampo> tampos;
    ArrayList<Prateleira> prateleiras;
    ArrayList<Porta> portas;

    public Projeto() {
        boolean alturaOK = true, comprimentoOK = true, larguraOK = true, espessuraOK = true;
        System.out.println("Desejar informar o valor da Altura?");
        if (isEntradatoString()) {
            do {
                System.out.println("Informe o valor da Altura: ");
                this.altura = getEntrada();
                if (altura != 0) {
                    alturaOK = false;
                }
            } while (alturaOK);
        }
        System.out.println("Desejar informar o valor do Comprimento?");
        if (isEntradatoString()) {
            do {
                System.out.println("Informe o valor do Comprimento: ");
                this.comprimento = getEntrada();
                if (comprimento != 0) {
                    comprimentoOK = false;
                }
            } while (comprimentoOK);
        }
        System.out.println("Desejar informar o valor da Largura?");
        if (isEntradatoString()) {
            do {
                System.out.println("Informe o valor da Largura:");
                this.largura = getEntrada();
                if (largura != 0) {
                    larguraOK = false;
                }
            } while (larguraOK);
        }
        System.out.println("Desejar informar o valor da Espessura?");
        if (isEntradatoString()) {
            do {
                System.out.println("Informe o valor da Espessura: ");
                this.espessura = getEntrada();
                if (espessura != 0) {
                    espessuraOK = false;
                }
            } while (espessuraOK);
        }
        Projetar(altura, comprimento, largura, espessura);
    }

    public Projeto(long altura, long comprimento, long largura, long espessura, String decrição) {
        this.altura = altura;
        this.comprimento = comprimento;
        this.largura = largura;
        this.espessura = espessura;
        this.decrição = decrição;
    }

    public Projeto(long altura, long comprimento, long largura, long espessura) {
        this.altura = altura;
        this.comprimento = comprimento;
        this.largura = largura;
        this.espessura = espessura;
        Projetar(altura, comprimento, largura, espessura);
    }

    public void Projetar(long altura, long comprimento, long largura, long espessura) {

        boolean lateralSair = true;
        boolean portaSair = true;
        boolean tampoSair = true;
        boolean prateleiraSair = true;
        anexarBase();
        anexarFundo();
        /*System.out.println("tem lateral?");
        if (isEntradatoString()) {
            do {
                System.out.println("Quantos laterais?");
                int lateral = getEntrada();
                if (lateral > 0) {
                    lt = new Lateral(lateral, altura, largura);
                    lateralSair = false;
                } else if (lateral == 0) {
                    lateralSair = false;
                }
            } while (lateralSair);
        }

        System.out.println("tem Porta?");
        if (isEntradatoString()) {
            do {
                System.out.println("Quantos Portas?");
                int porta = getEntrada();
                System.out.println("Vire o lado:");
                boolean virar = isEntradatoString();
                if (porta > 0) {
                    po = new Porta(porta, altura, comprimento / porta);
                    portaSair = false;
                } else if (porta == 0) {
                    portaSair = false;
                }
            } while (portaSair);
        }
        System.out.println("tem Tampo?");
        if (isEntradatoString()) {
            do {
                System.out.println("Quantos Tampos?");
                int tampo = getEntrada();
                if (tampo > 0) {
                    tp = new Tampo(tampo, comprimento, largura);
                    tampoSair = false;
                } else if (tampo == 0) {
                    tampoSair = false;
                }
            } while (tampoSair);
        }
        System.out.println("tem Prateleira?");
        if (isEntradatoString()) {
            do {
                System.out.println("Quantas Prateleira?");
                int prateleira = getEntrada();
                if (prateleira > 0) {
                    pr = new Prateleira(prateleira, comprimento, largura);
                    prateleiraSair = false;
                } else if (prateleira == 0) {
                    prateleiraSair = false;
                }
            } while (prateleiraSair);
        } */
    }

    @SuppressWarnings("unchecked")
    private void anexarBase() {
        int base = 0;
        boolean baseSair = true;
        System.out.println("tem Base?");
        if (isEntradatoString()) {
            System.out.println("São quantas Base diferentes?");
            int tipo = getEntrada();
            if (tipo <= 0) {

            } else if (tipo == 1) {
                bs = cadastrarBase();
            } else {
                bases = new ArrayList<>();
                for (int i = 0; i < tipo; i++) {
                    System.out.println(i);
                    bases.add(cadastrarBase());
                }
            }
        }
    }

    private Base cadastrarBase() {
        Base base = null;
        int baseInt = 0;
        long resComprimento = 0, resLargura = 0, resEspessura = 0;
        boolean baseSair = true;
        System.out.println("A medida da Base  tem " + this.comprimento + "X" + this.largura + "X" + this.espessura);
        if (isEntradatoString()) {
            do {
                System.out.println("Quantas unidade Bases?");
                baseInt = getEntrada();
                if (baseInt > 0) {
                    base = new Base(baseInt, comprimento, largura);
                    baseSair = false;
                } else if (baseInt == 0) {
                    baseSair = false;
                }
            } while (baseSair);
        } else {
            System.out.println("Quantas unidade Bases?");
            baseInt = getEntrada();
            System.out.println("Qual o comprimento da Base: ");
            resComprimento = getEntrada();
            System.out.println("Qual a largura da Base: ");
            resLargura = getEntrada();
            System.out.println("Qual a Espessua da Base: ");
            resEspessura = getEntrada();
            base = new Base(baseInt, resComprimento, resLargura, resEspessura);
        }
        return base;
    }

    private void anexarFundo() {
        boolean fundoSair = true;
        Fundo FD = null;
        System.out.println("tem fundo?");
        if (isEntradatoString()) {
            do {
                System.out.println("Quantos Fundos?");
                int fundo = getEntrada();
                if (fundo > 0) {
                    fd = cadastrarFundo();
                    fundoSair = false;
                } else {
                    fundos = new ArrayList<>();
                    for (int i = 0; i < fundo; i++) {
                        fundos.add(cadastrarFundo());
                    }
                    fundoSair = false;
                }
            } while (fundoSair);
        }
    }

    private Pecas cadastrarPecas(Pecas peca) {
        Peca pec = null;
        int PecaInt = 0;
        long resComprimento = 0, resLargura = 0, resEspessura = 0;
        boolean pecaSair = true;
        System.out.println("A medida da " + "tem " + this.comprimento + "X" + this.largura + "X" + this.espessura);
        if (isEntradatoString()) {
            do {
                System.out.println("Quantas unidade Bases?");
                PecaInt = getEntrada();
                if (PecaInt > 0) {
                    pec = new Base(PecaInt, comprimento, largura);
                    pecaSair = false;
                } else if (PecaInt == 0) {
                    pecaSair = false;
                }
            } while (pecaSair);
        } else {
            System.out.println("Quantas unidade " + pec.getNome() + "?");
            PecaInt = getEntrada();
            System.out.println("Qual o comprimento da Base: ");
            resComprimento = getEntrada();
            System.out.println("Qual a largura da Base: ");
            resLargura = getEntrada();
            System.out.println("Qual a Espessua da Base: ");
            resEspessura = getEntrada();
            pec = new Base(PecaInt, resComprimento, resLargura, resEspessura);
        }
        return pec;
    }

    private Fundo cadastrarFundo() {
        return (Fundo) cadastrarPecas(fd);
    }
}
