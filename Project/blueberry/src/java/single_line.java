import java.sql.*;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "single_line", urlPatterns = {"/single_line"})
public class single_line extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String home=response.encodeRedirectURL("loginservlet");
        HttpSession s=request.getSession();
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","system","shobhit");
            Statement stmt=con.createStatement();
            String str=request.getParameter("sq");
            String logout=response.encodeRedirectURL("logout");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Administrator: Single Line SQL Execution</title>");  
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

            
            out.println("<h1>Administrator</h1><br><h3>Single Line SQL Execution</h3>");
            out.println("<form method='post' action='single_line'>");
            out.println("<table cell spacing='100%'>");
            
            out.println("<input type='text' name='sq' size=120 maxlength=500 value='"+str+"'>");
               
            out.println("</table><input type='submit' value='Run'></form>");
            out.println("<br><br><br>");
            out.println("<hr width=100% size=5 noshade>");
            out.println("<table cell spacing='100%'>");
            if(str.length()>5)
            {
            if(str.substring(0,6).equalsIgnoreCase("select"))
            {
                 ResultSet rs=stmt.executeQuery(str);
                 ResultSetMetaData md = rs.getMetaData();
                 int col = md.getColumnCount();
                 out.println("<table border ='1' cellpadding='5' cellspacing='5'><tr>");
                     for (int i = 1; i <= col; i++)
                     {
                            String col_name = md.getColumnName(i);
                            out.print("<td>"+col_name+"</td>");
                     }
                     out.println("</tr>");
                 while(rs.next())
                 {
                     out.println("<tr>");
                     for (int i = 1; i <= col; i++)
                     {
                            String st=rs.getString(i);
                            if(md.getColumnName(i).equalsIgnoreCase("DOB"))
                                st=st.substring(0, 10);
                            out.print("<td>"+st+"</td>");
                            
                     }
                     out.println("</tr>");
                 }
                 out.println("</table>");
            }
           }
            else
            {
                int z=stmt.executeUpdate(str);
                out.println(z+" Row(s) Affected");
            }
            out.println("</table>");
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
