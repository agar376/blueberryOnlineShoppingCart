import java.sql.*;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "admin", urlPatterns = {"/adminservlet"})
public class admin extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String home=response.encodeRedirectURL("loginservlet");
        HttpSession s=request.getSession();
        String logout=response.encodeRedirectURL("logout");
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>blueberry</title>");  
            out.println("<style> a:link {text-decoration: none} a:visited {text-decoration: none; color: blue} a:active {text-decoration: none} font a:visited{color: white} #white{color: white; text-decoration: underline}</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div>");
        out.println("<div align='top' style='width:100%;height:60px;background-color: blue;'>");
           out.println("<div style='width:25%; float: left; text-align: right'> ");
out.println("<font color='white' size='10' face='times new roman'><a href= '"+home+"' >blueberry</a></font>");
out.println("</div>");
out.println("<div style='width: 25%; padding: 1px;float: right; text-align: right; text-transform: capitalize; text-decoration: blink'>");
out.println("<a id='white' href= '"+logout+"' >Logout</a></div>");
out.println("</div>");
out.println("<br>  <div style='height: 700px;'>");
        out.println("<div style='float: left;display: block;width: 150px; '> </div>");
out.println("        <div style='float: right;width: 150px; display: block; '>  </div>");
out.println("<div style='display: block; text-align: center; padding: 0px 160px 5px 160px'>  ");
            String url1=response.encodeRedirectURL("update.jsp");
            String url2=response.encodeRedirectURL("single_line");
           
            out.println("<br><br><a href='"+url1+"'>Insert/Update Items</a>");
            out.println("<br><br><a href='"+url2+"'>Single Line SQL Execution</a>");
           
        }
                catch(Exception e)
                {
                    
                }
        finally { 
            out.println("</div>  </div>");
            out.println("<div style='clear: both'>");
                out.println("<br><br><br><br><br><center>");
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
}
