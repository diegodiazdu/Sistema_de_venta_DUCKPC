
package Controlador;

import java.sql.*;
import javax.swing.JOptionPane;

public class Conexion {
    
      Connection con = null;
      String user = "root";
      String pass = "";
      String Driver = ("com.mysql.jdbc.Driver");
      String url = "jdbc:mysql://localhost:3306/duck-pc";

    public Connection conectar(){
        
        try{
            
            Class.forName(Driver);
            
            con = DriverManager.getConnection(url,user,pass);
            
            //JOptionPane.showMessageDialog(null, "Conectado!!! ","",JOptionPane.INFORMATION_MESSAGE);
            
        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null, "Error al conectar! "+e.getMessage(),"",JOptionPane.ERROR_MESSAGE);
        
    }
        return con;
    
}
}
