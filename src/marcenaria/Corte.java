/*
 * To change this license header, choose License Headers in Project PropertiespeChapa.
 * To change this template file, choose Tools | TemplatespeChapa
 * and open the template in the editor.
 */
package marcenaria;

import java.sql.*;
import javax.swing.JOptionPane;
import marcenaria.dado.ModuloConector;

/**
 *
 * @author Carlos
 */
public class Corte {

    public static void main(String[] args) {
        double[] CC = new double[2];
        double[] LL = new double[CC.length];
        CC[0] = 109.5;
        CC[1] = 109.5;
        LL[0] = 79.5;
        LL[1] = 79.5;
        //PecaParaCortar(220, 160, 220,160, true);
        cote(220, 160, 1.8, CC, LL, s);
        //dividirCorte(0, 220, 160, c, l);

    }
    static Connection conexao;
    static PreparedStatement pst;
    static Statement stmt;
    static ResultSet rs;
    private static final String TABELA = Corte.class.getSimpleName();
    private static double[] compriCorte = new double[2];
    private static double[] largCorte = new double[compriCorte.length];
    private static double[] espeCorte = new double[largCorte.length];
    static double c = 109.5, l = 79.5, s = 0.5;

    /**
     * *
     */
    public static void Corte() {
        conexao = ModuloConector.getConecction();
    }

    /**
     *
     */
    public static void planodeCorte() {
        for (int w = 0; w < getLargCorte().length; w++) {
            if (getCompriCorte(w) != 0.0) {
                if (getLargCorte(w) != 0.0) {
                    System.out.println(getCompriCorte(w) + "X" + getLargCorte(w) + "X" + getEspeCorte(w));
                }
            }
        }
        System.out.println("\n");
        //System.out.println(c + "X" + l);
    }

    /**
     * @param comprChapa
     * @param largChapa
     * @param precomprChapa
     * @param compPeca
     * @param largPeca
     * @return
     */
    public static double precoPecaParaCortar(double comprChapa, double largChapa, double precomprChapa, double compPeca, double largPeca) {
        double preco;
        if (precomprChapa < 0 || compPeca > comprChapa || largPeca > largChapa) {
            preco = 0;
        } else {
            preco = precomprChapa * (compPeca / comprChapa) * (largPeca / largChapa);
        }
        return preco;
    }

    /**
     * @param comprChapa
     * @param largChapa
     * @param compPeca
     * @param largPeca
     * @param rot
     */
    public static void PecaParaCortar(double comprChapa, double largChapa, double compPeca, double largPeca, boolean rot) {
        for (int i = 0; i < getCompriCorte().length; i++) {
            if (largChapa < largPeca && comprChapa > largPeca && largChapa > compPeca && rot == true) {
                //se a largura da chapa for menor que a largura da peça E
                //se o comprimento da chapa for maior que a largura da peça E
                //se a largura da chapa for maior que o comprimento da peca E
                // se rot for true
                setCompriCorte(i, comprChapa, largPeca);
                setLargCorte(i, largChapa, compPeca);
            } else if (largChapa < largPeca && comprChapa > largPeca && largChapa > compPeca && rot == false) {
                //se a largura da chapa for menor que a largura da peça E
                //se o comprimento da chapa for maior que a largura da peça E
                //se a largura da chapa for maior que o comprimento da peca E
                // se rot for false
                String frase = "";
                if (largChapa < largPeca) {
                    frase = "A Medida da Largura da peca: " + largPeca
                            + "\ne Maior a Medida da Largura da Chapa: " + largChapa;
                } else {
                    frase = "A Medida da Largura da peca: " + largPeca
                            + "\ne Menor ou Iqual a Medida da Largura da Chapa: " + largChapa;
                }
                if (comprChapa < compPeca) {
                    frase += "\nA Medida da Comprimento da peca: " + compPeca
                            + "\ne Maior a Medida da Comprimento da Chapa: " + comprChapa;
                } else {
                    frase += "\nA Medida da Comprimento da peca: " + compPeca
                            + "\ne Menor ou Iqual a Medida da Comprimento da Chapa: " + comprChapa;
                }
                JOptionPane.showMessageDialog(null, frase);
            } else if (comprChapa == compPeca && largChapa == largPeca) {

            } else {
                setCompriCorte(i, comprChapa, compPeca);
                setLargCorte(i, largChapa, largPeca);
                String a = " c: " + getCompriCorte(i) + " l: " + getLargCorte(i);
                JOptionPane.showMessageDialog(null, a);
            }
        }
    }

    /**
     * -- Inico do Sets e Gets
     */
    /**
     * @return
     */
    public static double[] getCompriCorte() {
        return compriCorte;
    }

    /**
     * @param pos
     * @return
     */
    public static double getCompriCorte(int pos) {
        return compriCorte[pos];
    }

    /**
     * @param compriCorte
     */
    public static void setCompriCorte(double[] compriCorte) {
        Corte.compriCorte = compriCorte;
    }

    /**
     * @param array
     * @param multiplicado
     */
    public static void setCompriCorte(int array, int multiplicado) {
        if (multiplicado > 0) {
            Corte.compriCorte = new double[array * multiplicado];
        } else {
            JOptionPane.showMessageDialog(null, "O multiplicado é:" + multiplicado + " tem que ser maior que 0");
        }
    }

    /**
     * @param pos
     * @param compriCorte
     */
    public static void setCompriCorte(int pos, double compriCorte) {
        if (compriCorte < 0) {
            Corte.compriCorte[pos] = -1;
        } else {
            Corte.compriCorte[pos] = compriCorte;
        }
    }

    /**
     *
     * @param pos
     * @param comprChapa
     * @param comprPeca
     */
    public static void setCompriCorte(int pos, double comprChapa, double comprPeca) {
        if (comprPeca > comprChapa || comprPeca <= 0 || comprChapa <= 0) {
            Corte.compriCorte[pos] = -1;
        } else {
            Corte.compriCorte[pos] = comprChapa - comprPeca;
        }
    }

    /**
     * @return
     */
    public static double[] getLargCorte() {
        return largCorte;
    }

    /**
     * @param pos
     * @return
     */
    public static double getLargCorte(int pos) {
        return largCorte[pos];
    }

    /**
     * @param largCorte
     */
    public static void setLargCorte(double[] largCorte) {
        Corte.largCorte = largCorte;
    }

    /**
     * @param pos
     * @param largCorte
     */
    public static void setLargCorte(int pos, double largCorte) {
        Corte.largCorte[pos] = largCorte;
    }

    /**
     * @param array
     * @param multiplicado
     */
    public static void setLargCorte(int array, int multiplicado) {
        if (multiplicado > 0) {
            Corte.largCorte = new double[array * multiplicado];
        } else {
            JOptionPane.showMessageDialog(null, "O multiplicado é:" + multiplicado + " tem que ser maior que 0");
        }
    }

    /**
     * @param pos
     * @param largChapa
     * @param largPeca
     */
    public static void setLargCorte(int pos, double largChapa, double largPeca) {
        if (largPeca > largChapa || largPeca <= 0 || largChapa <= 0) {
            Corte.largCorte[pos] = -1;
        } else {
            Corte.largCorte[pos] = largChapa - largPeca;
        }
    }

    /**
     * @return
     */
    public static double[] getEspeCorte() {
        return espeCorte;
    }

    /**
     * @param pos
     * @return
     */
    public static double getEspeCorte(int pos) {
        return espeCorte[pos];
    }

    /**
     * @param espeCorte
     */
    public static void setespeChapapeCorte(double[] espeCorte) {
        Corte.espeCorte = espeCorte;
    }

    /**
     * @param pos
     * @param espeCorte
     */
    public static void setEspeCorte(int pos, double espeCorte) {
        if (espeCorte <= 0) {
            Corte.espeCorte[pos] = -1;
        } else {
            Corte.espeCorte[pos] = espeCorte;
        }
    }

    /**
     * @param array
     * @param multiplicado
     */
    public static void setEspeCorte(int array, int multiplicado) {
        if (multiplicado > 0) {
            Corte.espeCorte = new double[array * multiplicado];
        } else {
            JOptionPane.showMessageDialog(null, "O multiplicado é:" + multiplicado + " tem que ser maior que 0");
        }
    }

    /**
     * @param pos
     * @param espeChapa
     * @param espePeca
     */
    public static void setEspeCorte(int pos, double espeChapa, double espePeca) {
        if (espePeca > espeChapa || espePeca <= 0 || espeChapa <= 0) {
            Corte.espeCorte[pos] = -1;
        } else {
            Corte.espeCorte[pos] = espeChapa - espePeca;
        }
    }

    /**
     * --Fim do Sets e gets--
     */
    /**
     * @param pos
     * @param largChapa
     * @param largPeca
     * @param espeChapa
     * @param comprChapa
     */
    public static void horizontalCorte(int pos, double largChapa, double largPeca, double espeChapa, double comprChapa) {
        if (largChapa != 0 && comprChapa != 0) {
            System.out.println("h");
            setLargCorte(pos, largChapa, largPeca);
            setEspeCorte(pos, espeChapa);
            setCompriCorte(pos, comprChapa);
        }
    }

    /**
     * @param pos
     * @param comprChapa
     * @param comprPeca
     * @param espeChapa
     * @param largChapa
     */
    public static void verticalargChapaorte(int pos, double comprChapa, double comprPeca, double espeChapa, double largChapa) {
        if (largChapa != 0 && comprPeca != 0) {
            System.out.println("v");
            setCompriCorte(pos, comprChapa, comprPeca);
            setEspeCorte(pos, espeChapa);
            setLargCorte(pos, largChapa);
        }
    }

    /**
     * TespeChapatando
     *
     * @param pos
     * @param comprChapa
     * @param comprPeca
     * @param espeChapa
     * @param largChapa
     * @param largPeca
     */
    public static void dividirCorte(int pos, double comprChapa, double largChapa, double espeChapa, double comprPeca, double largPeca) {
        double larg, comp;
        for (int i = 0; i <= 1; i++) {
            if (i == 0) {
                verticalargChapaorte(i, comprChapa, comprPeca, espeChapa, largChapa);
            } else {
                horizontalCorte(i, largChapa, largPeca, espeChapa, comprChapa);
            }
        }
        if ((getCompriCorte(0) / comprChapa) * (getLargCorte(0) / largChapa) * 100 < (getCompriCorte(1) / comprChapa) * (getLargCorte(1) / largChapa) * 100) {
            larg = getLargCorte(0);
            comp = getCompriCorte(0);
            verticalargChapaorte(0, comprChapa, comprPeca, espeChapa, largPeca);
        } else {
            larg = getLargCorte(1);
            comp = getCompriCorte(1);
            horizontalCorte(1, largChapa, largPeca, espeChapa, comprPeca);
        }
    }

    /**
     * @param comprChapa
     * @param largChapa
     * @param espeChapa
     * @param comprPeca
     * @param largPeca
     * @param serra
     */
    public static void cote(double comprChapa, double largChapa, double espeChapa, double comprPeca, double largPeca, double serra) {
        if (largChapa >= largPeca + serra) {
            if (comprChapa >= comprPeca + serra) {
                System.out.println("1) " + largChapa + ">" + largPeca + " " + comprChapa + ">" + comprPeca);
                dividirCorte(0, comprChapa, largChapa, espeChapa, comprPeca + serra, largPeca + serra);
            } else if (comprChapa < comprPeca) {
                System.out.println("2) " + largChapa + ">" + largPeca + " " + comprChapa + "<" + comprPeca);
                coteCorrecao(comprChapa, largChapa, espeChapa, comprPeca, largPeca, serra);
            } else if (comprChapa == comprPeca) {
                System.out.println("3) " + largChapa + ">" + largPeca + " " + comprChapa + "=" + comprPeca);
                dividirCorte(0, comprChapa, largChapa, espeChapa, comprPeca, largPeca);
            }
        } else if (largChapa < largPeca) {
            if (comprChapa >= comprPeca+serra) {
                System.out.println("4) " + largChapa + "<" + largPeca + " " + comprChapa + ">" + comprPeca);
                coteCorrecao(comprChapa, largChapa, espeChapa, comprPeca, largPeca, serra);
            } else if (comprChapa < comprPeca) {
                System.out.println("5) " + largChapa + "<" + largPeca + " " + comprChapa + "<" + comprPeca);
                coteCorrecao(comprChapa, largChapa, espeChapa, comprPeca, largPeca, serra);
            } else if (comprChapa == comprPeca) {
                System.out.println("6) " + largChapa + "<" + largPeca + " " + comprChapa + "=" + comprPeca);
                coteCorrecao(comprChapa, largChapa, espeChapa, comprPeca, largPeca, serra);
            }
        } else if (largChapa == largPeca) {
            if (comprChapa >= comprPeca + serra) {
                System.out.println("7) " + largChapa + "=" + largPeca + " " + comprChapa + ">" + comprPeca);
                dividirCorte(0, comprChapa, largChapa, espeChapa, comprPeca + serra, largPeca);
            } else if (comprChapa < comprPeca) {
                System.out.println("8) " + largChapa + "=" + largPeca + " " + comprChapa + "<" + comprPeca);
                coteCorrecao(comprChapa, largChapa, espeChapa, comprPeca, largPeca, serra);
            } else if (comprChapa == comprPeca) {
                System.out.println("9) " + largChapa + "=" + largPeca + " " + comprChapa + "=" + comprPeca);
                dividirCorte(0, comprChapa, largChapa, espeChapa, comprPeca, largPeca);
            }
        }
        planodeCorte();
    }

    /**
     * @param comprChapa
     * @param largChapa
     * @param espeChapa
     * @param comprPeca
     * @param largPeca
     * @param serra
     */
    public static void cote(double comprChapa, double largChapa, double espeChapa, double[] comprPeca, double[] largPeca, double serra) {
        setCompriCorte(comprPeca.length, 1);
        setLargCorte(largPeca.length, 1);
        for (int i = 0; i < getCompriCorte().length; i++) {
            if (i == 0) {
                cote(comprChapa, largChapa, espeChapa, comprPeca[i], largPeca[i], serra);
            } else {
                if ((comprChapa / getCompriCorte(0) * largChapa / getLargCorte(0)) >= (comprChapa / getCompriCorte(1) * largChapa / getLargCorte(1))) {
                    System.out.println("0");
                    cote(getCompriCorte(0), getLargCorte(0), espeChapa, comprPeca[i], largPeca[i],serra);
                    planodeCorte();
                } else {
                    System.out.println("1");
                    cote(getCompriCorte(1), getLargCorte(1), espeChapa, comprPeca[i], largPeca[i], serra);
                }
            }
        }
    }

    /**
     * PARCIALMENTE PRONTO
     *
     * @param comprChapa
     * @param largChapa
     * @param comprPeca
     * @param espeChapa
     * @param largPeca
     * @param serra
     */
    public static void coteCorrecao(double comprChapa, double largChapa, double comprPeca, double espeChapa, double largPeca, double serra) {
        if (largChapa >= comprPeca + serra) {
            if (comprChapa >= largPeca + serra) {
                System.out.println("1) correcão\n" + largChapa + ">" + comprPeca + " " + comprChapa + ">" + largPeca);
                dividirCorte(0, comprChapa, largChapa, espeChapa, largPeca + serra, comprPeca + serra);
            } else if (comprChapa < largPeca + serra) {
                System.out.println("2) correcão\n" + largChapa + ">" + comprPeca + " " + comprChapa + "<" + largPeca);
            } else if (comprChapa == largPeca) {
                System.out.println("3) correcão\n" + largChapa + ">" + comprPeca + " " + comprChapa + "=" + largPeca);
                dividirCorte(0, comprPeca, largPeca, comprPeca, espeChapa, largPeca);
            }
        } else if (largChapa < comprPeca) {
            if (comprChapa > largPeca) {
                System.out.println("4) correcão\n" + largChapa + "<" + comprPeca + " " + comprChapa + ">" + largPeca);
            } else if (comprChapa < largPeca) {
                System.out.println("5) correcão\n" + largChapa + "<" + comprPeca + " " + comprChapa + "<" + largPeca);
            } else if (comprChapa == largPeca) {
                System.out.println("6) correcão\n" + largChapa + "<" + comprPeca + " " + comprChapa + "=" + largPeca);
            }
        } else if (largChapa == comprPeca) {
            if (comprChapa >= largPeca + serra) {
                System.out.println("7) correcão\n" + largChapa + "=" + comprPeca + " " + comprChapa + ">" + largPeca);
                dividirCorte(0, comprChapa, largChapa, espeChapa, largPeca + serra, comprPeca);
            } else if (comprChapa < largPeca) {
                System.out.println("8) correcão\n" + largChapa + "=" + comprPeca + " " + comprChapa + "<" + largPeca);
            } else if (comprChapa == largPeca) {
                System.out.println("9) correcão\n" + largChapa + "=" + comprPeca + " " + comprChapa + "=" + largPeca);
                dividirCorte(0, comprChapa, largChapa, espeChapa, largPeca, comprPeca);
            }
        }
    }

    /**
     * @param comprChapa
     * @param largChapa
     * @param espeChapa
     * @param comprPeca
     * @param largPeca
     * @param serra
     */
    public static void SomaPeça(double comprChapa, double largChapa, double espeChapa, double[] comprPeca, double[] largPeca, double serra) {
        double[][] peca = new double[comprPeca.length + 1][largPeca.length + 1];
        double somacomprPeca, somalargPeca;

        for (int i = 0; i < peca.length;) {
            for (;;) {

            }
        }
    }
}
