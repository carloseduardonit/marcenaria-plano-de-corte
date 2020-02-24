/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marcenaria;

import java.sql.Date;
import java.util.Scanner;
import marcenaria.material.Chapa;
import marcenaria.material.Peca;
import marcenaria.material.Pedaco;
import marcenaria.pessoa.Cliente;
import marcenaria.pessoa.Fornecedor;

/**
 *
 * @author Carlos
 */
public class T {

    static Scanner ler;
    static String loginUsuario, senhaUsuario, tipodeUsuario, tipoMaterial,nomeUsuario, documUsuario;
    static Date incData;
    static int quantidade;
    static double comprimento, largura, espessura, preco;
    static boolean virar, texturar;
    static String nomeArquivo = "C:\\Users\\Carlos\\Documents\\NetBeansProjects\\Agil\\src\\agil\\dado\\cep.sql";

    public static void main(String[] args) {
        menuPrincipal();
    }

    public static void menuPrincipal() {
        boolean principal = true;       
        do { ler = new Scanner(System.in);
        
            System.out.println("Menu Principal:"
                    + "\n1: para Pessoa"
                    + "\n2: para Material:"
                    + "\n3: para Sair");
            int opc = ler.nextInt();
            switch (opc) {
                case 1:
                    menuPessoa();
                    principal = false;
                    break;
                case 2:
                    menuMaterial();
                    principal = false;
                    break;
                case 3:
                    System.exit(0);
                    principal = false;
                    break;
                default:
                    System.out.println("Opção invalida\n\n");
            } ler.close();
        } while (principal);
       
    }

    public static void menuPessoa() {
        ler = new Scanner(System.in);
        boolean pessoa = true;
        do {
            System.out.println("Menu:"
                    + "\n1:Cliente"
                    + "\n2:Fornecedor");
            int opc = ler.nextInt();
            switch (opc) {
                case 1:
                    menuCliente();
                    pessoa = false;
                    break;
                case 2:
                    menuFornercedor();
                    pessoa = false;
                    break;
                default:
                    System.out.println("Opção invalida\n\n");
            }
        } while (pessoa);
        ler.close();
    }

    public static void TelaPessoa(int opcao) {
       
        boolean tp = true;
        do { 
            ler = new Scanner(System.in);
            System.out.println("Login do  Usuario:");
            loginUsuario = ler.nextLine();
            if (opcao < 3) {
                System.out.println("Senha  do Usuario:");
                senhaUsuario = ler.nextLine();
                System.out.println("Nome do  Usuario:");
                nomeUsuario = ler.nextLine();
                System.out.println("Senha  do Usuario:");
                documUsuario = ler.nextLine();
                System.out.println("Tipo de Pessoa:\n 1: PF ou 2:PJ");
                int op = ler.nextInt();
                switch (op) {
                    case 1:
                        tipodeUsuario = "PF";
                        
                        break;
                    case 2:
                        tipodeUsuario = "PJ";
                        break;
                    default:
                        System.out.println("Opção invalida\n\n");
                }
                ler.close();  
            }
        } while (tp);
        
    }

    public static void menuCliente() {
        ler = new Scanner(System.in);
        System.out.println("Menu Cliente"
                + "\n1: Para Cadastrar o cliente;"
                + "\n2: Para Editar  o cliente;"
                + "\n3: para Excluir o cliente"
                + "\n4: Para Pesquisar o cliente");
        int opc = ler.nextInt();
        switch (opc) {
            case 1:
                TelaPessoa(opc);
                Cliente.adicionarCliente(loginUsuario, senhaUsuario, senhaUsuario, tipodeUsuario, nomeUsuario, documUsuario);
                limpaPessoa();
                break;
            case 2:
                TelaPessoa(opc);
                Cliente.editarCliente(loginUsuario, senhaUsuario, senhaUsuario, tipodeUsuario, nomeUsuario, documUsuario);
                limpaPessoa();
                break;
            case 3:
                TelaPessoa(opc);
                Cliente.excluirCliente(loginUsuario);
                limpaPessoa();
                break;
            case 4:
                TelaPessoa(opc);
                Cliente.exibirCliente(loginUsuario);
                limpaPessoa();
                break;
            default:
                System.out.println("Opção invalida\n\n");
        }
        ler.close();
    }

    public static void menuFornercedor() {
        ler = new Scanner(System.in);
        boolean fornecedor = true;
        do {
            System.out.println("Menu Fornecedor"
                    + "\n1: Para Cadastrar o Fornecedor;"
                    + "\n2: Para Editar  o Fornecedor;"
                    + "\n3: para Excluir o Fornecedor"
                    + "\n4: Para Pesquisar o Fornecedor");
            int opc = ler.nextInt();
           ler.close();
           switch (opc) {
                case 1:
                    TelaPessoa(opc);
                    Fornecedor.adicionarFornecedor(loginUsuario, senhaUsuario, senhaUsuario, tipodeUsuario, nomeArquivo, documUsuario);
                    fornecedor = false;
                    limpaPessoa();
                    break;
                case 2:
                    TelaPessoa(opc);
                    Fornecedor.editarFornecedor(loginUsuario, senhaUsuario, senhaUsuario, tipodeUsuario, nomeArquivo, documUsuario);
                    fornecedor = false;
                    limpaPessoa();
                    break;
                case 3:
                    TelaPessoa(opc);
                    Fornecedor.excluirPessoa(loginUsuario);
                    fornecedor = false;
                    limpaPessoa();
                    break;
                case 4:
                    TelaPessoa(opc);
                    Fornecedor.exibirFornecedor(loginUsuario);
                    fornecedor = false;
                    limpaPessoa();
                    break;
                default:
                    System.out.println("Opção invalida\n\n");
            }
        } while (fornecedor);
        
    }

    public static void limpaPessoa() {
        loginUsuario = "";
        senhaUsuario = "";
        tipodeUsuario = "";
        nomeUsuario = "";
        documUsuario = "";
    }

    public static void TelaMaterial(int opçao) {
        ler = new Scanner(System.in);
        if (opçao < 3) {
            System.out.println("Quantidade:");
            quantidade = ler.nextInt();
            System.out.println("Conprimento:");
            comprimento = ler.nextDouble();
            System.out.println("Largura:");
            largura = ler.nextDouble();
            System.out.println("Espessura:");
            espessura = ler.nextDouble();
            System.out.println("Preço");
            preco = ler.nextDouble();
        }
        ler.close();
    }

    public static void limpaMaterial() {
        quantidade = 0;
        comprimento = 0;
        largura = 0;
        espessura = 0;
        preco = 0;
    }

    private static void menuMaterial() {
        ler = new Scanner(System.in);
        boolean material = true;
        do {
            System.out.println("Menu Material"
                    + "\n1: Chapa"
                    + "\n2: Pedaço"
                    + "\n3:Peça");
            int opc = ler.nextInt();
            switch (opc) {
                case 1:
                    menuChapa();
                    material = false;
                    break;
                case 2:
                    menuPedaco();
                    material = false;
                    break;
                case 3:
                    menuPeca();
                    material = false;
                    break;
                default:
                    System.out.println("Opção invalida\n\n");
            }
        } while (material);
    }

    private static void menuChapa() {
        ler = new Scanner(System.in);
        boolean chapa = true;
        do {
            System.out.println("Menu Chapa:"
                    + "\n1: Adicionar  a Chapa"
                    + "\n2: Editar a Chapa"
                    + "\n3: Excluir a Chapa"
                    + "\n4: Exibir a Chapa");
            int opc = ler.nextInt();
            switch (opc) {
                case 1:
                    Chapa.adicionarChapa(quantidade, comprimento, largura, espessura, preco, tipoMaterial, nomeUsuario);
                    chapa = false;
                    break;
                case 2:
                    Chapa.editarChapa(quantidade, comprimento, largura, espessura, preco, tipoMaterial, nomeUsuario);
                    chapa = false;
                    break;
                case 3:
                    Chapa.excluirChapa(quantidade, comprimento, largura, espessura, preco, tipoMaterial, nomeUsuario);
                    break;
                case 4:
                    Chapa.exibirChapatoString(comprimento, largura, espessura, preco, tipoMaterial, nomeUsuario);
                    chapa = false;
                    break;
                default:
                    System.out.println("Opção invalida\n\n");
            }
        } while (chapa);
        ler.close();
    }

    private static void menuPedaco() {
        ler = new Scanner(System.in);
        boolean pedaco = true;
        do {
            System.out.println("Menu do Pedaço"
                    + "\n0: Voltar opção anteriores"
                    + "\n1: Adicionar o Pedaço"
                    + "\n2: Edutar o Pedaço"
                    + "\n3: Excluir o Pedaço"
                    + "\n4: Pesquisar o Pedaço");
            int opc = ler.nextInt();
            switch (opc) {
                case 0:
                    menuMaterial();
                    pedaco = false;
                    break;
                case 1:
                    Pedaco.adicionarPedaco(quantidade, comprimento, largura, espessura, preco, tipoMaterial);
                    pedaco = false;
                    break;
                case 2:
                    Pedaco.editarPedaco(quantidade, comprimento, largura, espessura, incData);
                    pedaco = false;
                    break;
                case 3:
                    Pedaco.excluirPedaco(opc, opc, comprimento, largura, espessura, incData);
                    pedaco = false;
                    break;
                case 4:
                    Pedaco.pesquisarPedaco(opc, opc, comprimento, largura, espessura, tipodeUsuario, incData, virar);
                    pedaco = false;
                    break;
                default:
                    System.out.println("Opção invalida\n\n");
            }
        } while (pedaco);
        ler.close();
    }

    private static void menuPeca() {
        ler = new Scanner(System.in);
        boolean peca = true;
        do {
            System.out.println("Menu da Peça"
                    + "\n1: Adicionar a Peça"
                    + "\n2: Editar a Peça"
                    + "\n3: Excluir a Peça"
                    + "\n4: Pesquisar a Peça");
            int opc = ler.nextInt();
            switch (opc) {
                case 1:
                    Peca.adicionarPeca(quantidade, preco, largura, espessura, preco, tipoMaterial, loginUsuario);
                    peca = false;
                    break;
                case 2:
                    Peca.editarPeca(quantidade, preco, largura, espessura, preco, tipoMaterial, loginUsuario);
                    peca = false;
                    break;
                case 3:
                    Peca.excluirPeca(quantidade, preco, largura, espessura, preco, tipoMaterial, loginUsuario);
                    peca = false;
                    break;
                case 4:
                    Peca.pesquisarPeca(quantidade, preco, largura, espessura, preco, tipoMaterial, loginUsuario);
                    peca = false;
                    break;
                default:
                    System.out.println("Opção invalida\n\n");
            }
        } while (peca);
        ler.close();
    }
}
