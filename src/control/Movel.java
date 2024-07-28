/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.beans.PropertyVetoException;
import java.util.ArrayList;
import javax.swing.JTextField;

/**
 *
 * @author Carlos
 */
public class Movel {

    @SuppressWarnings("unchecked")
    public Movel() {

    }

    public Movel(JTextField txtQuantidadeMovel, JTextField txtAltura, JTextField txtComprimento, JTextField txtEspessura, JTextField txtLargura, JTextField descricao) {
        ArrayList<projeto.Movel> unidadesMoveis = new ArrayList<>();
        int itens = Integer.valueOf(txtQuantidadeMovel.getText());
        for (int itensAdd = 1; itensAdd <= itens; itensAdd++) {
            projeto.Movel projetoMovel;
            projetoMovel = new projeto.Movel(longar(txtAltura.getText()), longar(txtComprimento.getText()), longar(txtLargura.getText()), longar(txtEspessura.getText()), descricao.getText());
            view.Movel telaMovel = view.TelaPrincipal.getM();
            if (telaMovel.isVisible()) {
                try {
                    telaMovel.setClosed(false);
                    System.out.println(telaMovel.getName());
                    telaMovel.setClosed(true);
                    view.TelaPrincipal.setM(telaMovel);
                } catch (PropertyVetoException ex) {
                    System.err.println(ex);
                }
                if (itensAdd == 1) {
                    control.Gaveteiro gaveteiro = new Gaveteiro(telaMovel);
                } else if (itensAdd > 1) {
                    projetoMovel = new projeto.Movel(longar(txtAltura.getText()), longar(txtComprimento.getText()), longar(txtLargura.getText()), longar(txtEspessura.getText()), descricao.getText());
                }
            }
            unidadesMoveis.add(projetoMovel);
            System.out.println("item: " + itensAdd);
        }
    }

    private static long longar(String txt) {
        return Long.valueOf(txt);
    }

    public static void temp(view.Movel m) {
        m.txtAltura.setText("750");
        m.txtComprimento.setText("1000");
        m.txtDescricao.setText("n/a");
        m.txtIDArmario.setText("0");
        m.txtLargura.setText("500");
        m.txtEspessura.setText("15");
        m.txtQuantidadeArmario.setText("1");
    }
}
