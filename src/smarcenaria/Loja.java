/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarcenaria;
import dao.ModuloConexao;
import java.util.Scanner;
/**
 *
 * @author carlos
 */
public class Loja {
    static Scanner ler=new Scanner(System.in);
    public static int getIdLoja() {
        return idLoja;
    }

    public static void setIdLoja(int idLoja) {
        Loja.idLoja = idLoja;
    }
    private static int  idLoja=0;
    private static String razaoSocial, endereço, telefone;

    public Loja(String razaoSocial, String endereço, String telefone) {
        Loja.razaoSocial = razaoSocial;
        Loja.endereço = endereço;
        Loja.telefone = telefone;
    }

    
    public static String getRazaoSocial() {
        return razaoSocial;
    }

    public static void setRazaoSocial(String razaoSocial) {
        Loja.razaoSocial = razaoSocial;
    }

    public static String getEndereço() {
        return endereço;
    }

    public static void setEndereço(String endereço) {
        Loja.endereço = endereço;
    }

    public static String getTelefone() {
        return telefone;
    }

    public static void setTelefone(String telefone) {
        Loja.telefone = telefone;
    }

    public static void adicionarLoja(){
        Loja.setIdLoja(Loja.getIdLoja()+1);
        System.out.println("Qual o nome da Empresa:");
        Loja.setRazaoSocial(ler.nextLine());
        System.out.println("Qual o endereço da Empresa:");
        Loja.setEndereço(ler.nextLine());
        System.out.println("Qual o telefone da Empresa:");
        Loja.setTelefone(ler.nextLine());
    }
}
