/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Conexion;
import Modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author enrique
 */
@WebServlet(name = "servletRegistroUsu", urlPatterns = {"/servletRegistroUsu"})
public class ServletRegistroUsu extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet servletRegistroUsu</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet servletRegistroUsu at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        //processRequest(request, response);
//
//        String nombre = request.getParameter("name");
//        String apellidos = request.getParameter("apellidos");
//        String correo = request.getParameter("email");
//        String user = request.getParameter("usuario");
//        String password = request.getParameter("password");
//        boolean probando = false;
//
//        Usuario us = new Usuario(nombre, apellidos, correo, user, password);
//
//        Connection conexion = null; //Objeto para la conexión a la BD
//        Statement sentencia = null; //Objeto para la ejecutar una sentencia
//        ResultSet resultados = null;//Objeto para guardar los resultados
//        int n = 0;
//        //devolvemos la respuesta
//        response.setContentType("text/html");
//        PrintWriter out = response.getWriter();
//
//        try {
//
//            //Leemos el driver java db derby
//            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
//
//            //Nos conectamos a la BD
//            conexion = DriverManager.getConnection(
//                    "jdbc:derby://localhost:1527/DBUsuarios",
//                    "moi", "123");
//
//            //Creamos una sentencia a partir de la conexión
//            sentencia = conexion.createStatement();
//
//            //Cogemos todos los datos de la asignaturas
//            resultados = sentencia.executeQuery("SELECT * FROM usuario where usuario='" + user + "'");
//            // conexion.close();
//            if (!resultados.next()) {
//                sentencia = conexion.createStatement();
//                n = sentencia.executeUpdate("INSERT INTO usuario "
//                        + "VALUES('" + nombre + "','" + apellidos + "','" + correo + "','" + user + "','" + password + "')");
//
//                conexion.close();
//
//            }
//
//        } catch (ClassNotFoundException e1) {
//            //Error si no puedo leer el driver de Oracle 
//            out.println("ERROR:No encuentro el driver de la BD: "
//                    + e1.getMessage());
//        } catch (SQLException e2) {
//            //Error SQL: login/passwd mal
//            out.println("ERROR:Fallo en SQL: " + e2.getMessage());
//        } finally {
//            //Finalmente desconecto de la BD
//            try {
//                if (conexion != null) {
//                    conexion.close();
//                }
//            } catch (SQLException e3) {
//                out.println("ERROR:Fallo al desconectar de la BD: "
//                        + e3.getMessage());
//            }
//        }
//
//        String envio = "";
//
//        //si se registro(inserto el Usuario)
//        if (n == 1) {
//            envio = "El usuario " + nombre + " " + apellidos + " fue registrado correctamente!";
//
//        } else {
//
//            envio = "Ya existe un usuario con nombre de usurio: " + user + ". Por favor utilice otro!";
//
//        }
//
//        request.getSession().setAttribute("mensaje", envio);
//        response.sendRedirect("http://localhost:8080/Gestion_De_Videos/registroRes.jsp");
//
//    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("name");
        String apellidos = request.getParameter("apellidos");
        String correo = request.getParameter("email");
        String user = request.getParameter("usuario");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");

        String envio = "";
        boolean ok = false;
        
        if(password.equals(repassword)) {
             Usuario usuario = new Usuario(nombre, apellidos, correo, user, password);
            Conexion conexion = new Conexion();
            if(!conexion.existeUsuario(usuario.getUsuario())) {
                ok = conexion.insertarUsuario(usuario);
                if(ok) {
                    envio = "El usuario " + nombre + " " + apellidos + " fue registrado correctamente!";
                } else {
                    envio = "Ha habido un problema interno al crear el usuario.";
                }
            } else {
                envio = "Ya existe un usuario con nombre de usurio: " + user + ". Por favor utilice otro!";
            }
        } else {
            envio = "Las 2 contraseñas introducidas no coinciden.";
        }
        
        //devolvemos la respuesta
        response.setContentType("text/html");
        request.getSession().setAttribute("mensaje", envio);
        if(ok) {
            response.sendRedirect("http://localhost:8080/Gestion_De_Videos/registroRes.jsp");
        } else {
            response.sendRedirect("");
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
