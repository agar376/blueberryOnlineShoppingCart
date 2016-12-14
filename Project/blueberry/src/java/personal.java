import java.io.*;
import java.util.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
@WebServlet(name = "personal", urlPatterns = {"/personaldetails"})
public class personal extends HttpServlet  implements HttpSessionListener
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession s=request.getSession();
        String home=response.encodeRedirectURL("loginservlet");
        String logout=response.encodeRedirectURL("logout");
        String user=(String) s.getAttribute("user");
        String edit=response.encodeRedirectURL("edit.jsp");
            
        String url=response.encodeRedirectURL("cartservlet");
        List cart=(LinkedList) s.getAttribute("cart");
        if(cart==null)
        {
            cart=new LinkedList();
            s.setAttribute("cart", cart);
        }
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Shopping</title>");  
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

out.println("Welcome "+user+"<h1>Personal Details</h1>");
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","system","shobhit");
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT name,dob,email,phone FROM REGISTRATION where username='"+user+"'");
            out.println("<table border=0 cellpadding=10 cell spacing=10 text-align='left'>");
            while(rs.next())
            {
                out.println("<tr>");
                out.println("<th>Name</th><td>"+rs.getString("name")+"</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th>Date Of Birth</th><td>"+rs.getDate("dob")+"</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("</th><th>Email</th><td>"+rs.getString("email")+"</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th>Phone Number</th><td>"+rs.getString("phone")+"</td>");
                out.println("</tr>");
            }
            
            out.println("</table>");
            out.println("<br><br><a href='"+edit+"'>Edit Details</a>");
            
        } 
        catch(Exception e)
        {
            
        }
            finally {    
            
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

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {try
            {
        processRequest(request, response);
            }        
        catch(Exception e)
        {
            
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {try
            {
        processRequest(request, response);
            }        
        catch(Exception e)
        {
            
        }
    }

   
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
