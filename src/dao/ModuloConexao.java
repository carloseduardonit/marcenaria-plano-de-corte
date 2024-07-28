/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author carlos
 */
public class ModuloConexao {
    //Variavel String com caminho  do driver do MySQL
    private final static String DRIVER = "com.mysql.jdbc.Driver";
    //Variavel String com caminho  do Banco de Dados no MySQL
    private final static String URL ="jdbc:mysql://localhost:3306/";
    //Variavel String com o usuario do Banco de dados
    private final static String USER = "Carlos";
    //Variavel String com o Senha do Banco de dados
    private final static String PASS =  "";
    /*Connection conect= null;
    ResultSet rs =null;
    PreparedStatement pst=null;*/
    public static Connection Conector(){
        Connection conexao =null;
         // alinha  abaixo chama o  drive
        // Estabelecendo o acesso ao banco de dados
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            return conexao;
        }
    }
    public static void Reset() throws SQLException {
       Connection conexao =null;
       conexao.close();
       Conector();
    }

}
    