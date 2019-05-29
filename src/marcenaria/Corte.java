/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
        CC[0] = 100;
        CC[1] = 100;
        LL[0] = 60;
        LL[1] = 60;
        //PecaParaCortar(220, 160, 220,160, true);
        cote(220, 160, CC, LL, s);
        //dividirCorte(0, 220, 160, c, l);

    }
    static Connection conexao;
    static PreparedStatement pst;
    static Statement stmt;
    static ResultSet rs;
    private static final String TABELA = Corte.class.getSimpleName();
    private static double[] compriCorte = new double[2];
    private static double[] largCorte = new double[compriCorte.length];
    static double c = 50, l = 160, s = 0.5;

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
                    System.out.println(getCompriCorte(w) + "X" + getLargCorte(w));
                }
            }
        }
        System.out.println(c + "X" + l);
    }

    /**
     * @param comprChapa
     * @param largChapa
     * @param precChapa
     * @param compPeca
     * @param largPeca
     * @return
     */
    public static double precoPecaParaCortar(double comprChapa, double largChapa, double precChapa, double compPeca, double largPeca) {

        double preco;
        if (precChapa < 0 || compPeca > comprChapa || largPeca > largChapa) {
            preco = 0;
        } else {
            preco = precChapa * (compPeca / comprChapa) * (largPeca / largChapa);
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
        Corte.compriCorte[pos] = compriCorte;
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
     * @param pos
     * @param largChapa
     * @param largPeca
     * @param comprChapa
     */
    public static void horizontalCorte(int pos, double largChapa, double largPeca, double comprChapa) {
        if (largChapa != 0 && comprChapa != 0) {
            System.out.println("h");
            setLargCorte(pos, largChapa, largPeca);
            setCompriCorte(pos, comprChapa);
        }
    }

    /**
     * @param pos
     * @param comprChapa
     * @param comprPeca
     * @param largChapa
     */
    public static void verticalCorte(int pos, double comprChapa, double comprPeca, double largChapa) {
        if (largChapa != 0 && comprPeca != 0) {
            System.out.println("v");
            setCompriCorte(pos, comprChapa, comprPeca);
            setLargCorte(pos, largChapa);
        }
    }

    /**
     * Testando
     *
     * @param pos
     * @param comprChapa
     * @param comprPeca
     * @param largChapa
     * @param largPeca
     * @param serra
     */
    public static void dividirCorte(int pos, double comprChapa, double largChapa, double comprPeca, double largPeca, double serra) {
        double larg, comp;
        for (int i = 0; i <= 1; i++) {
            if (i == 0) {
                verticalCorte(i, comprChapa, comprPeca + serra, largChapa);
            } else {
                horizontalCorte(i, largChapa, largPeca + serra, comprChapa);
            }
        }
        if ((getCompriCorte(0) / comprChapa) * (getLargCorte(0) / largChapa) * 100 < (getCompriCorte(1) / comprChapa) * (getLargCorte(1) / largChapa) * 100) {
            larg = getLargCorte(0);
            comp = getCompriCorte(0);
            verticalCorte(0, comprChapa, comprPeca + serra, largPeca);
        } else {
            larg = getLargCorte(1);
            comp = getCompriCorte(1);
            horizontalCorte(1, largChapa, largPeca + serra, comprPeca);
        }
    }

    /**
     * @param cc
     * @param lc
     * @param cp
     * @param lp
     * @param serra
     */
    public static void cote(double cc, double lc, double cp, double lp, double serra) {
        if (lc > lp) {
            if (cc > cp) {
                System.out.println("1) " + lc + ">" + lp + " " + cc + ">" + cp);
                dividirCorte(0, cp, lp, cp, lp, serra);
            } else if (cc < cp) {
                System.out.println("2) " + lc + ">" + lp + " " + cc + "<" + cp);
                coteCorrecao(cc, lc, cp, lp, serra);
            } else if (cc == cp) {
                System.out.println("3) " + lc + ">" + lp + " " + cc + "=" + cp);
                dividirCorte(0, cp, lp, cp - serra, lp, serra);
            }
        } else if (lc < lp) {
            if (cc > cp) {
                System.out.println("4) " + lc + "<" + lp + " " + cc + ">" + cp);
                coteCorrecao(cc, lc, cp, lp, serra);
            } else if (cc < cp) {
                System.out.println("5) " + lc + "<" + lp + " " + cc + "<" + cp);
                coteCorrecao(cc, lc, cp, lp, serra);
            } else if (cc == cp) {
                System.out.println("6) " + lc + "<" + lp + " " + cc + "=" + cp);
                coteCorrecao(cc, lc, cp - serra, lp, serra);
            }
        } else if (lc == lp) {
            if (cc > cp) {
                System.out.println("7) " + lc + "=" + lp + " " + cc + ">" + cp);
                dividirCorte(0, cc, lc, cp, lp - serra, serra);
            } else if (cc < cp) {
                System.out.println("8) " + lc + "=" + lp + " " + cc + "<" + cp);
                coteCorrecao(cc, lc, cp, lp, serra);
            } else if (cc == cp) {
                System.out.println("9) " + lc + "=" + lp + " " + cc + "=" + cp);
                dividirCorte(0, cc, lc, cp - serra, lp - serra, serra);
            }
        }
    }

    /**
     */
    public static void cote(double cc, double lc, double[] cp, double[] lp, double serra) {
        setCompriCorte(cp.length, 2);
        setLargCorte(lp.length, 2);
        for (int i = 0; i < getCompriCorte().length; i++) {
            if (i == 0) {
                cote(cc, lc, cp[i], lp[i], serra);
                planodeCorte();
            } else {
                for (int j = 0;; j++) {
                    cote(getCompriCorte(j), getLargCorte(j), cp[i], lp[i], serra);
                    planodeCorte();
                }
            }
        }
    }

    /**
     * @param cc
     * @param lc
     * @param cp
     * @param lp
     * @param serra
     */
    public static void coteCorrecao(double cc, double lc, double cp, double lp, double serra) {
        if (lc > cp) {
            if (cc > lp) {
                System.out.println("1correcão\n" + lc + ">" + cp + " " + cc + ">" + lp);
                dividirCorte(0, cc, lc, lp, cp, serra);
            } else if (cc < lp + serra) {
                System.out.println("2correcão\n" + lc + ">" + cp + " " + cc + "<" + lp);
            } else if (cc == lp) {
                System.out.println("3correcão\n" + lc + ">" + cp + " " + cc + "=" + lp);
                dividirCorte(0, cp, lp, cp, lp - serra, serra);
            }
        } else if (lc < cp) {
            if (cc > lp) {
                System.out.println("4correcão\n" + lc + "<" + cp + " " + cc + ">" + lp);
            } else if (cc < lp) {
                System.out.println("5correcão\n" + lc + "<" + cp + " " + cc + "<" + lp);
            } else if (cc == lp) {
                System.out.println("6correcão\n" + lc + "<" + cp + " " + cc + "=" + lp);
            }
        } else if (lc == cp) {
            if (cc > lp) {
                System.out.println("7correcão\n" + lc + "=" + cp + " " + cc + ">" + lp);
                dividirCorte(0, cc, lc, lp, cp - serra, serra);
            } else if (cc < lp) {
                System.out.println("8correcão\n" + lc + "=" + cp + " " + cc + "<" + lp);
            } else if (cc == lp) {
                System.out.println("9correcão\n" + lc + "=" + cp + " " + cc + "=" + lp);
                dividirCorte(0, cc, lc, lp - serra, cp - serra, serra);
            }
        }
    }
}
