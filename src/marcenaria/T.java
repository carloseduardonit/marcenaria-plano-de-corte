/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marcenaria;

import marcenaria.dado.DataBase;

/**
 *
 * @author Carlos
 */
public class T {

  
    static String nomeArquivo = "C:\\Users\\Carlos\\Documents\\NetBeansProjects\\Agil\\src\\agil\\dado\\cep.sql";
   
    public static void main(String[] args)  {
        DataBase.importarBackupdataBaseSQL(nomeArquivo);
    }
}
