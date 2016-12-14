import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "del_cart", urlPatterns = {"/del_cart"})
public class del_cart extends HttpServlet implements HttpSessionListener
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession s=request.getSession();
        List cart=(LinkedList) s.getAttribute("cart");
        String home=response.encodeRedirectURL("loginservlet");
        String logout=response.encodeRedirectURL("logout");
        String save=response.encodeRedirectURL("savecart");
        String user=(String) s.getAttribute("user");
        String add=response.encodeRedirectURL("shopservlet");
         out.println("<html>");
            out.println("<head>");
            out.println("<title>Shopping Cart</title>");  
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

out.println("Welcome "+user+"<br><h1>Shopping Cart</h1>");
        
        try {
        
            
        int i=0;
          
            Iterator it=cart.iterator();
            while(it.hasNext())
            {
                shopping_item si=(shopping_item) it.next();
                i++;
            String q=(request.getParameter("del"+i));
            
            if(q.equalsIgnoreCase("del"))
                            cart.remove(si);
            
                
            }
            if(cart.isEmpty())
            {
                out.println("<br><br><font color='red'><h1>Cart Empty</h1></font>");
            }
            else
            {
            out.println("<form method='post' action='del_cart'>");
            out.println("<table border=1 cellpadding=5 cell spacing=5>");
            out.println("<tr>");
            out.println("<td>Delete</td><th>Item Code</th><th>Name</th><th>Quantity</th><th>Item Price</th><th>Total Price</th>");
            out.println("</tr>");
            i=0;
            double total=0;
            it=cart.iterator();
            while(it.hasNext())
            {
                i++;
                shopping_item si=(shopping_item) it.next();
                out.println("<tr>");
                out.println("<td><input type='radio' name='del"+i+"' value='del'>Yes<input type='radio' name='del"+i+"' value='No' checked>No</td>");
                out.println(si.toString());
                out.println("</tr>");
                total+=(si.getPrice()*si.getQty());
            }
            out.println("<tr>");
            out.println("<th>Total</th><th> </th><th> </th><th> </th><th> </th><th>"+total+"</th>");
            out.println("</tr>");
            out.println("<tr><td colspan=6 align='center'><input type='submit' value='Update Cart'></td></tr>");
            out.println("</table>");
            out.println("</form>");
            
            out.println("<a href='"+save+"'>Save Cart</a>&nbsp;&nbsp;<a href='"+add+"'>Add More Items To Cart</a>");
        } 
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
            throws ServletException, IOException {
  try
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
