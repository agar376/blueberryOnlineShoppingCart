import java.sql.*;
import java.io.*;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "update", urlPatterns = {"/update"})
public class update extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String home=response.encodeRedirectURL("loginservlet");
        HttpSession s=request.getSession();
        List errormsgs=new LinkedList();
        String u_qty=request.getParameter("u1");
        String u_price=request.getParameter("u2");
        
        String itemcode=request.getParameter("itemcode");
        String code=request.getParameter("code");
        
        String i_code=request.getParameter("i1");
        String i_name=request.getParameter("i2");
        String i_qty=request.getParameter("i3");
        String i_price=request.getParameter("i4");
        
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","system","shobhit");
            Statement stmt=con.createStatement();
            
            String logout=response.encodeRedirectURL("logout");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Administrator: Insert/Update</title>");  
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

if(code.equalsIgnoreCase("update"))
{
    if(itemcode.equalsIgnoreCase("select"))
        errormsgs.add("Enter Valid Item");
    if(u_price.isEmpty()&&u_qty.isEmpty())
    errormsgs.add("Enter Price Or Quantity");
}
if(code.equalsIgnoreCase("insert"))
{
    if(itemcode.equalsIgnoreCase("select"))
        errormsgs.add("Enter Valid Item");
    if(i_code.isEmpty())
        errormsgs.add("Enter Valid Item Code");
    if(i_name.isEmpty())
        errormsgs.add("Enter Valid Item Name");
    if(i_qty.isEmpty())
        errormsgs.add("Enter Valid Item Quantity");
    if(i_price.isEmpty())
        errormsgs.add("Enter Valid Item Price");
    
}

        }
                catch(Exception e)
                {
                    
                }
                
                
        finally 
        { 
            try
            {
                
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","system","shobhit");
            Statement stmt=con.createStatement();
            
            
            
            if(!(errormsgs.isEmpty()))
                    {
                        out.println("<h1>Administrator</h1><br><h3>Insert/Update</h3><br>If Updating Set New Values. (New Price or Previous + Purchased Quantity) <br><br>");
                        out.println("<br><br>");
            out.println("<font color='red'>");
            out.println("<ul>");
            for(int i=0;i<errormsgs.size();i++)
                out.println("<li>"+errormsgs.get(i).toString() +"</li>");
            out.println("</ul>");
            out.println("</font>");
                        out.println("<br><br><form method='post' action='update'>");
            out.println("<table cell spacing='100%'>");
            ResultSet rs=stmt.executeQuery("select * from items");
             out.println("<tr><th colspan='2'>Item Code and Item Name</th><th>Total Quantity</th><th>Item Price</th><th>Select</th></tr>");
            out.println("<tr><td colspan='2'><select name='itemcode'><option value='Select'>Select</option>");
             while(rs.next())
             {
                 out.println("<option value='"+rs.getString("itemcode")+"'>"+rs.getString("itemcode")+" and "+rs.getString("name")+"</option>");
            
             }
             out.println("</select></td>");
             out.println("<td><input type='text' name='u1'></td>");
             out.println("<td><input type='text' name='u2'></td>");
             out.println("<td><input type='radio' name='code' value='Update' checked> Update</td>");
             out.println("</tr>");
            out.println("<tr>");
            out.println("<td><input type='text' name='i1' value='"+i_code+"'></td>");
             out.println("<td><input type='text' name='i2' value='"+i_name+"'></td>");
             out.println("<td><input type='text' name='i3' value='"+i_qty+"'></td>");
             out.println("<td><input type='text' name='i4' value='"+i_price+"'></td>");
             out.println("<td><input type='radio' name='code' value='Insert'> Insert</td>");
            out.println("</tr>");
            out.println("</table><br><br><input type='submit' value='Run'></form>");
            out.println("<br><br><br>");
                    }
            else
            {
                
                if(code.equalsIgnoreCase("update"))
                {
                    if(!(u_qty.isEmpty() && u_price.isEmpty()))
                        stmt.executeUpdate("update items set itemprice='"+u_price+"',qty='"+u_qty+"' where itemcode='"+itemcode+"'");
                    else if(u_qty.isEmpty())
                    stmt.executeUpdate("update items set itemprice='"+u_price+"' where itemcode='"+itemcode+"'");
                    else
                    stmt.executeUpdate("update items set qty='"+u_qty+"' where itemcode='"+itemcode+"'");
                    
                }
                else
                {
                    stmt.executeUpdate("insert into items (itemcode,name,qty,itemprice) values('"+i_code+"','"+i_name+"','"+i_qty+"','"+i_price+"')");
                }
                out.println("<h3>Updation Successful</h3>");
                
            }
            }
            catch(Exception e)
            {
                
            }
            finally
            {
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
