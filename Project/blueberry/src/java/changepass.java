import java.sql.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
@WebServlet(name = "changepass", urlPatterns = {"/changepass"})
public class changepass extends HttpServlet implements HttpSessionListener {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession s=request.getSession();
        String url=response.encodeRedirectURL("changepass");
        List errormsgs=new LinkedList();
        String user=(String) s.getAttribute("user");
        String pass=request.getParameter("pass");
        String cpass=request.getParameter("cpass");
        String old=request.getParameter("old");
        String home=response.encodeRedirectURL("loginservlet");
        String logout=response.encodeRedirectURL("logout");
            
        
        out.println("<html>");
            out.println("<head>");
            out.println("<title>Change Password</title>");  
            out.println("<style> a:link {text-decoration: none} a:visited {text-decoration: none; color: blue} a:active {text-decoration: none} font a:visited{color: white} #white{color: white; text-decoration: underline}</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div>");
        out.println("<div align='top' style='width:100%;height:60px;background-color: blue;'>");
           out.println("<div style='width:25%; float: left; text-align: right'> ");
out.println("<font color='white' size='10' face='times new roman'><a href= '"+home+"' >blueberry</a></font>");
out.println("</div>");
out.println("<div style='width: 25%; padding: 1px;float: right; text-align: right; text-transform: capitalize; text-decoration: blink'>");
out.println("<a href= '"+logout+"' >Logout</a></div>");
out.println("</div>");
out.println("<br>  <div style='height: 700px;'>");
        out.println("<div style='float: left;display: block;width: 150px; '> </div>");
out.println("        <div style='float: right;width: 150px; display: block; '>  </div>");
out.println("<div style='display: block; text-align: center; padding: 0px 160px 5px 160px'>  ");

out.println("<h1>Change Password</h1>");
        try {
            out.println("Welcome "+user);
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","system","shobhit");
                Statement stmt=con.createStatement();
            
            ResultSet rs=stmt.executeQuery("SELECT password FROM login where username='"+user+"'");
            while(rs.next())
            {
                if(!(old.equalsIgnoreCase(rs.getString("password"))))
                errormsgs.add("Password Incorrect");
            }
             if(!(pass.equals(cpass)))
                errormsgs.add("Password Do Not Match");
             else if(pass.length()<6)
             errormsgs.add("Password Cannot Be Less Than 6 Character Long");
            
        }
        
        catch(Exception e)
        {
            
        }
                
        finally {      
            
            if(!(errormsgs.isEmpty()))
            {
            out.println("<br><br>");
            out.println("<font color='red'>");
            out.println("<ul>");
            for(int i=0;i<errormsgs.size();i++)
                out.println("<li>"+errormsgs.get(i).toString() +"</li>");
            out.println("</ul>");
            out.println("</font>");
            
            if(!(errormsgs.isEmpty()))
            {
            out.println("<form method='post' action='"+url+"'>");
            out.println("<table border='1' cellspacing='5' cellpadding='5'>");
            out.println("<tr><td>Old Password</td><td><input type='password' name=old></td></tr>");
            out.println("<tr><td>Password</td><td><input type='password' name='pass'></td></tr>");
            out.println("<tr><td>Confirm Password</td><td><input type='password' name=cpass></td></tr>");
            
            out.println("</table>");
            out.println("<center><input type='submit' value='Update'><input type='Reset' value='Clear'></center>");
            out.println("</form>");
            }
         }
            else
            {
                try
                {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","system","shobhit");
                Statement stmt=con.createStatement();
                 int z=stmt.executeUpdate("update login set password='"+pass+"' where username='"+user+"'");
                 RequestDispatcher view=request.getRequestDispatcher("confirm");
                view.forward(request, response);
                }
                catch(Exception e)
                {
                    
                }
            }
            
            
            out.println("</div>  </div>");
            out.println("<div style='clear: both'>");
                out.println("<center>");
                    out.println("&copy; Copyright @ Shobhit Agarwal");
                    out.println("<br> ");
                    out.println("Contact: ");
                    out.println("<address>9501240890</address>");
               out.println(" </center>");
            out.println("</div>  </div>");
out.println("</body>");
out.println("</html>");
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
