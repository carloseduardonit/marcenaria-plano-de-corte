
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JOptionPane;
import javax.swing.*;
import projeto.Movel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Carlos
 */
public class Teste {
//medidas 90X150X40

    public static void main(String[] args) {
        Dimension size = new Dimension(300, 300);
        JOptionPane jp = null;
        Movel movel = new Movel(110, 110, 60, 18);
        String mov = movel.toString();
        JPanel paniel = new JPanel();
        paniel.setSize(600, 600);
        JTextArea area = new JTextArea(mov);
        paniel.setPreferredSize(size);
        paniel.setLayout(new BorderLayout());
        paniel.add(area);
        JScrollPane jScrollPane = new JScrollPane();
        JScrollPane jsp = new JScrollPane(paniel);

        JOptionPane.showMessageDialog(null, jsp);
        System.out.println(mov);
    }
}
