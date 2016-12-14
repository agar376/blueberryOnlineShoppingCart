
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "confirm", urlPatterns = {"/confirm"})
public class confirm extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession s=request.getSession();
        String user=(String) s.getAttribute("user");
        String home=response.encodeRedirectURL("loginservlet");
        String logout=response.encodeRedirectURL("logout");
             
        PrintWriter out = response.getWriter();
        try {
           
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Confirmation</title>");  
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

out.println("<p>Welcome "+user+"<br><br><h1>Updation Successful</h1>");
            
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
            
        } finally {            
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
