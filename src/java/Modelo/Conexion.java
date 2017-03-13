
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class Conexion {
    private Connection conexion;
    private Statement sentencia;
    private ResultSet resultados;
   
    
    
  
    //Aqui cargamos la libreria


//Aqui se le envian el usuario de la base de datos, la clable y el nombre de la base de datos
public Conexion(String usuario, String pass, String baseDeDatos) throws ClassNotFoundException, SQLException {

    Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
    
       //Nos conectamos a la BD
       conexion = DriverManager.getConnection (
			"jdbc:derby://localhost:1527/"+baseDeDatos,
			usuario,pass);

}


public ResultSet ejecutarConsulta(String sql){

    return null;

}

public boolean insertar(usuario usuario) throws SQLException{

    String user=usuario.getUsuario();
    sentencia=conexion.createStatement();
    resultados=sentencia.executeQuery("SELECT * FROM usuario where usuario='"+user+"'");
    conexion.close();
    
    if(!resultados.next()){
        sentencia=conexion.createStatement();
        int n=sentencia.executeUpdate("INSERT INTO usuario "+
		     "VALUES('"+usuario.getNombre()+"','"+usuario.getApellidos()+"','"+usuario.getCorreo()+"','"+usuario.getUsuario()+"','"+usuario.getPassword()+"')");
 
        conexion.close();
       return true;

    }
    
    return false;

}



 
 public void cerrarConsulta() throws SQLException{
 
  conexion.close();
 
 
 }


    
}
