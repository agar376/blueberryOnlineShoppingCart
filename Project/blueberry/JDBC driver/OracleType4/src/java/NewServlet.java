/*
 * NewServlet.java
 *
 * Created on January 30, 2011, 7:01 AM
 */

import java.io.*;
import java.net.*;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author oracle
 * @version
 */
import javax.naming.*;
import javax.sql.*;
import java.sql.*;
public class NewServlet extends HttpServlet {
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    Context ctx;
    DataSource ds;
    Connection con;
    Statement stmt;
    ResultSet rs;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet NewServlet</title>");
        out.println("</head>");
        out.println("<body>");
        try {
            ctx=new InitialContext();
            ds=(DataSource)ctx.lookup("jdbc/oracle");
            con=ds.getConnection();
            stmt=con.createStatement();
            rs=stmt.executeQuery("Select * from employees");
            while(rs.next())
                out.println(rs.getString("first_name")+" "+rs.getString("last_name")+"<br>");
        } catch (Exception e) {
            out.println(e);
        }
        out.println("<h1>Servlet NewServlet at " + request.getContextPath () + "</h1>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Short description";
    }
    // </editor-fold>
}
