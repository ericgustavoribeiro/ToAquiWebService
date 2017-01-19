package com.ejt.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	private static Connection connection;
    public static Connection getConnection(int sistema) throws Exception {
    	String conexao = "";
    	String usuario = "";
    	String senha = "";
    	if (sistema == Database.ORACLE) {
    		//conexao = "jdbc:oracle:thin:@localhost:1521:XE";
    		conexao = "jdbc:oracle:thin:@localhost:1521:XE";
    		usuario = "root";
    		senha = "123@qwe";
    		//jdbc:oracle:thin:@localhost:1521:xe
    	}else if(sistema == Database.MYSQL){
//          conexao = "jdbc:mysql73995-toaqui.jelasticlw.com.br";
//     		usuario = "root";
//     		senha = "EYQafo61989";	
    		
        conexao = "jdbc:mysql://localhost/toaqui";
// 		usuario = "root";
// 		senha = "123@qwe";

        //
//       			
    		usuario = "toaqui";			
    		senha = "toaqui";

    	}
    	else {
    		throw new IllegalArgumentException("Tipo de banco não suportado");
    	}
    	if (connection == null) {
            try {
            	if(sistema == Database.MYSQL){
            	DriverManager.registerDriver(new com.mysql.jdbc.Driver()); 
            	connection = DriverManager.getConnection(conexao, usuario, senha);
            	}else if (sistema == Database.ORACLE){
            		//DriverManager.registerDriver(new com.oracle.); 
                	connection = DriverManager.getConnection(conexao, usuario, senha);	
            	}
            	} catch(SQLException e) {
	    		throw new Exception("SQLException => ConnectionManager: " + e.getMessage());
	    	}
        }
        return connection;
    }
    public static void close() throws Exception {
    	connection.close();
    }
}
