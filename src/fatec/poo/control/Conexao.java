package fatec.poo.control;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;


public class Conexao {
   private String connectionString;
   private String driver;
   private String usuario;
   private String senha;
   private Connection connection=null;

   public Conexao() {   
        this.usuario="BD1921018";
        this.senha="BD1921018";
        this.driver = "oracle.jdbc.driver.OracleDriver";
        this.connectionString="jdbc:oracle:thin:@192.168.1.6:1521:xe";
   }
   
   public Conexao(String usuario, String senha) {   
        this.usuario = usuario;
        this.senha = senha;   
   }

   public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
   }

   public void setDriver(String driver) {
        this.driver = driver;
   }
   
   public Connection conectar(){
        
	   if (connection == null){
	      try {
                 Class.forName(driver);
                 connection = DriverManager.getConnection(connectionString, usuario, senha);                            
	         System.out.println("Conexao OK");
              }
              catch (Exception ex) {
                  System.out.println("Falha na Conexao");
                  System.out.println("Usuario:" + this.usuario);
                  System.out.println("Senha:" + this.senha);
                  System.out.println("Connection:" + this.connectionString);
                  System.out.println("Driver:" + this.driver);
                  System.out.println(ex.toString() + ex.getMessage());
              }
	   }
       
	   return connection;
   }
   
   public void fecharConexao(){
        if (connection != null){
	   try {
                  connection.close();
           } catch (Exception ex) {
                   System.out.println(ex.toString());    
           }
        }   
    }
}