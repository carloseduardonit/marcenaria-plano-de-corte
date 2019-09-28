/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marcenaria.pessoa;

/**
 *
 * @author Carlos
 */
public class usuarioZero {
    private static final String TABELA = usuarioZero.class.getSimpleName();
    private static int idPessoa, idCliente, idFornecedor;
    private static String login, senha, confSenha, tipo, nome, tipoPessoa;
    public static void adicionarUsuarioZero(String login,String senha,String confSenha,String tipoPessoa, String nome, String docum){
        Cliente.adicionarCliente(login, senha, confSenha, tipoPessoa, nome, docum);
        Fornecedor.adicionarFornecedor(login, senha, confSenha, tipoPessoa, nome, docum);
    }
    public static void editarUsuarioZero(String login,String senha,String confSenha,String tipoPessoa, String nome, String docum){
        Cliente.editarCliente(login, confSenha, confSenha, tipoPessoa, nome, docum);
        Fornecedor.editarFornecedor(login, nome, confSenha, tipoPessoa, nome, docum);
    }
    public static void excluirUsarioZero(String login) {
        Cliente.excluirCliente(login);
        Fornecedor.excluirFornecedor(login);
    }
    public static void  pesquisarUsarioZero(String login){
        Cliente.pesquisarCliente(login);
        Fornecedor.pesquisarFornecedor(login);
    }
}
