/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marcenaria;

import java.util.Scanner;
import marcenaria.pessoa.utilitario.Endereco;

/**
 *
 * @author Carlos
 */
public class Teste {

    static Scanner ler = new Scanner(System.in);
static String login,CEP,Complemento;
static int numero;
    public static void main(String[] args) {
        System.out.println("digite 1 para adicionar: \n"
                + "digite 2 para editar: \n"
                + "digite 3 para Pesquisar: \n"
                + "digite 4 para Excluir: ");
        int a = ler.nextInt();
        switch (a) {
            case 1:
                System.out.println("Informe o login: ");
                login = ler.nextLine();
                System.out.println("Informe  o cep:");
                CEP = ler.nextLine();
                System.out.println("Informe  do Numero:");
                numero = ler.nextInt();
                System.out.println("Informe  o Complemento:");
                Complemento =ler.nextLine();
                Endereco.adicionarEndereco(login, CEP, numero, Complemento);
                break;
            case 2:
                System.out.println("Informe o login: ");
                login =ler.nextLine();
                System.out.println("Informe  o cep:");
                CEP = ler.nextLine();
                System.out.println("Informe  do Numero:");
                numero = ler.nextInt();
                System.out.println("Informe  o Complemento:");
                Complemento =ler.nextLine();
                Endereco.editarEndereco(login, CEP, numero, Complemento);
                break;
            case 3:
                System.out.println("Informe o login: ");
                login =ler.nextLine();
                System.out.println("Informe  o cep:");
                CEP = ler.nextLine();
                System.out.println("Informe  do Numero:");
                numero = ler.nextInt();
                System.out.println("Informe  o Complemento:");
                break;
            case 4:
                break;
        }
    }
}
