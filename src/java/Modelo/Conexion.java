package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {

    private Connection conexion;
    
    public boolean insertarUsuario(Usuario usuario) {
        boolean ok = false;
        
        conectar();
        
        try {
            Statement sentencia = conexion.createStatement();
            int n =  sentencia.executeUpdate("INSERT INTO moi.usuario (nombre, apellidos, correo, usuario, password) "
                    + "VALUES('" + usuario.getNombre() + "','" + usuario.getApellidos() + "','" + usuario.getCorreo() + "','" + usuario.getUsuario() + "','" + usuario.getPassword() + "')");
            ok = (n==1);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            desconectar();
            return ok;
        }
    }
    
    public boolean existeUsuario(String usuario) {
        boolean existe = false;
        
        conectar();
        
        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultados = sentencia.executeQuery("SELECT * FROM moi.usuario WHERE usuario='" + usuario + "'");
            if(resultados != null && resultados.next()) {
                existe = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            desconectar();
            return existe;
        }
    }
    
    public boolean existeUsuario(String usuario, String password) {
        boolean existe = false;
        
        conectar();
        
        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultados = sentencia.executeQuery("SELECT * FROM moi.usuario WHERE usuario='" + usuario + "' AND password='"+ password +"'");
            if(resultados != null && resultados.next()) {
                existe = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            desconectar();
            System.out.println(existe);
            return existe;
        }
    }
    
    public Usuario obtenerUsuario(String user, String password) {
        Usuario usuario = null;
        
        conectar();
        
        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultados = sentencia.executeQuery("SELECT * FROM moi.usuario WHERE usuario='" + usuario + "' AND password='"+ password +"'");
            if(resultados != null && resultados.next()) {
                usuario = new Usuario();
                usuario.setNombre(resultados.getString("nombre"));
                usuario.setApellidos(resultados.getString("apellidos"));
                usuario.setCorreo(resultados.getString("correo"));
                usuario.setUsuario(resultados.getString("usuario"));
                usuario.setPassword(resultados.getString("password"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            desconectar();
            return usuario;
        }
    }
    
    private void conectar() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            //Nos conectamos a la BD
            conexion = DriverManager.getConnection(
                    "jdbc:derby://localhost:1527/DBUsuarios",
                    "moi", "123");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void desconectar() {
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion = null;
        }
    }

}
