/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import javax.swing.JOptionPane;

/**
 *
 * @author Carlos
 */
public class Gaveteiro {

    private static view.Movel telaMovel = new view.Movel();
    private static view.Gaveteiro telaGaveteiro = new view.Gaveteiro();
    private static view.Lateral telaLateral = new view.Lateral();

    public static void main(String[] args) {
        System.out.println(isGaveteiro());
    }

    public Gaveteiro() {
    }

    public Gaveteiro(view.Movel telaMovel) {
        if (isGaveteiro()) {
            telaGaveteiro.setVisible(true);
            view.TelaPrincipal.AreadeTrabalho.add(telaGaveteiro);
        } else {
            telaLateral.setVisible(true);
            view.TelaPrincipal.AreadeTrabalho.add(telaLateral);
        }
    }

    public static boolean isGaveteiro() {
        int Gaveteiro = JOptionPane.showConfirmDialog(null, "Este Armario tem Gaveteiro", "Gaveteiro", JOptionPane.YES_NO_OPTION);
        boolean temGaveteiro = Gaveteiro == JOptionPane.YES_OPTION;
        if (temGaveteiro) {
            serarMesmaMedidaPorta(view.TelaPrincipal.getM());
        }
        return temGaveteiro;
    }

    public static boolean serarMesmaMedidaPorta(view.Movel telaMovel) {
        int Gaveteiro = JOptionPane.showConfirmDialog(null, "O Gaveteiro sera a mesma medida do que as portas", "Gaveteiro", JOptionPane.YES_NO_OPTION);
        boolean seraMesma = Gaveteiro == JOptionPane.YES_OPTION;
        if (seraMesma) {
            try {
                int portasefrente = Integer.parseInt(JOptionPane.showInputDialog(null, "Serar quantas portas?")) + 1;
                telaGaveteiro.txtAltura.setText(telaMovel.txtAltura.getText());
                double resultado = Integer.parseInt(telaMovel.txtComprimento.getText()) / portasefrente;
                telaGaveteiro.txtComprimento.setText(String.valueOf(resultado));
                telaGaveteiro.txtEspessura.setText(telaMovel.txtEspessura.getText());
                telaGaveteiro.txtLargura.setText(telaMovel.txtLargura.getText());

            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, nfe, "Gaveteiro", JOptionPane.YES_OPTION);
            }
        }
        return seraMesma;
    }

}
