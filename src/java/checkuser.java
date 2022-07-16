/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author leomu
 */
@WebServlet(urlPatterns = {"/checkuser"})
public class checkuser extends HttpServlet {

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
            
            Persistencia base = new Persistencia();
            ResultSet rs;
            
            out.println("<h1> Proyecto: " + request.getContextPath() + "</h1>" );
            out.println("<h1> Ingresando con usuario: " + request.getParameter("inputEmail") + "</h1>" );
            
            // mostramos el usuario que ingreso
            
            rs = base.consultaSQL("select * from usuarios where usuario="
                +"'" + request.getParameter("inputEmail") + "'" + "and clave = " + "'" +
                request.getParameter("inputPassword") + "'");

            //rs = base.consultaSQL("select * from users");
            
            if (rs.next()){
                rs.beforeFirst();
                out.println("Ingreso correcto, datos del usuario:");
                while (rs.next()) {
                    out.println("<BR><BR>");
                    out.println("ID usuario = " + rs.getString("idusuario")+"<BR>");
                    out.println("Email = " + rs.getString("usuario")+"<BR>");
                    out.println("Nombre = " + rs.getString("nombreyapellido")+"<BR>");
                    out.println("Clave = " + rs.getString("clave")+"<BR>");
                }
            } else {
                out.println("Ingreso incorrecto.");
            }
            out.println("<BR><BR><BR><BR>");
            // mostramos la lista de usuarios

            rs = base.consultaSQL("select * from usuarios");
            
            if (rs.next()){
                rs.beforeFirst();
                out.println("lista de usuario:");
                out.println("<BR><BR>");
                while (rs.next()) {
                    out.println("ID usuario = " + rs.getString("idusuario")+"<BR>");
                    out.println("Email = " + rs.getString("usuario")+"<BR>");
                    out.println("Nombre = " + rs.getString("nombreyapellido")+"<BR>");
                    out.println("Clave = " + rs.getString("clave")+"<BR><BR>");
                }
            } else {
                out.println("No hay usuarios la base de datos");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(checkuser.class.getName()).log(Level.SEVERE, null, ex);
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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
