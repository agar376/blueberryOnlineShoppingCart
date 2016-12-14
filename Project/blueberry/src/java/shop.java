import java.io.*;
import java.util.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
@WebServlet(name = "shop", urlPatterns = {"/shopservlet"})
public class shop extends HttpServlet implements HttpSessionListener
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession s=request.getSession();
        String home=response.encodeRedirectURL("loginservlet");
        String logout=response.encodeRedirectURL("logout");
        String user=(String) s.getAttribute("user");
            
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

out.println("Welcome "+user+"<br><h1>Shopping List</h1>");
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","system","shobhit");
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM ITEMS");
            out.println("<form action='"+url+"' method='post'>");
            out.println("<table border=1 cellpadding=5 cell spacing=5>");
            out.println("<tr>");
            out.println("<th>Item Code</th><th>Name</th><th>Quantity</th><th>Item Price</th>");
            out.println("</tr>");
            int i=1;
            while(rs.next())
            {
                out.println("<tr>");
                out.println("<td>"+rs.getString("itemcode")+"</td><td>"+rs.getString("name")+"</td><td><input type='text' name='qty"+i+"'><td>"+rs.getString("itemprice")+"</td>");
                out.println("</tr>");
                i++;
            }
            
            out.println("</table>");
            out.println("<input type='submit' value='Add To Cart'>\t\t<input type='reset' value='Clear'>");
            out.println("</form>");
            
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
