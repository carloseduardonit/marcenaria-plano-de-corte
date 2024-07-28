/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marcenaria.utilitario.cep.enu;

/**
 *
 * @author Carlos
 */
public enum UF {
    AC(1), AL(2), AM(3), AP(4), BA(5), CE(6), DF(7), ES(8), GO(9), MA(10), MG(11), MS(12), MT(13), PA(14), PB(15), PE(16), PI(17), PR(18), RJ(19), RN(20), RO(21), RR(22), RS(23), SC(24), SE(25), SP(26), TO(27);

    private UF(int index) {
        this.numeroEstado = index;
    }
    private int numeroEstado;
    private String nomeEstado;

    public String getNomeEstado() {
        
        return nomeEstado;
    }

    public int getNumeroEstado() {
        return numeroEstado;
    }

    public String getNomeEstado(int numeroEstado) {
        String estado = "";
        switch (numeroEstado) {
            case 1:
                estado = AC.toString();
                break;
            case 2:
                estado = AL.toString();
                break;
            case 3:
                estado = UF.AM.toString();
                break;
            case 4:
                estado = UF.AP.toString();
                break;
            case 5:
                estado = "";
                break;
            case 6:
                estado = "";
                break;
            case 7:
                estado = "";
                break;
            case 8:
                estado = "";
                break;
            case 9:
                estado = "";
                break;
            case 10:
                estado = "";
                break;
            case 11:
                estado = "";
                break;
            case 12:
                estado = "";
                break;
            case 13:
                estado = "";
                break;
            case 14:
                estado = "";
                break;
            case 15:
                estado = "";
                break;
            case 16:
                estado = "";
                break;
            case 17:
                estado = "";
                break;
            case 18:
                estado = "";
                break;
            case 19:
                estado = "";
                break;
            case 20:
                estado = "";
                break;
            case 21:
                estado = "";
                break;
            case 22:
                estado = "";
                break;
            case 23:
                estado = "";
                break;
            case 24:
                estado = "";
                break;
            case 25:
                estado = "";
                break;
            case 26:
                estado = "";
                break;
            case 27:
                estado = "";
                break;
        }
        return estado;
    }

}
