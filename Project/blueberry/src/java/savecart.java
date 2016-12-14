import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;
import java.text.*;
import java.util.*;
@WebServlet(name = "savecart", urlPatterns = {"/savecart"})
public class savecart extends HttpServlet implements HttpSessionListener {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession s=request.getSession(false);
        String user=(String) s.getAttribute("user");
        ServletContext c=getServletContext();
        List cart=(LinkedList) s.getAttribute("cart");   
        String home=response.encodeRedirectURL("loginservlet");
        String logout=response.encodeRedirectURL("logout");
        List errormsgs=new LinkedList();
        String shop=response.encodeRedirectURL("shopservlet");    
        int avail=0;
        
        
             out.println("<html>");
            out.println("<head>");
            out.println("<title>Save Cart</title>");  
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
 int book=0;
            
        try {
            java.util.Date date = Calendar.getInstance().getTime();
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String today = formatter.format(date);
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","system","shobhit");
                Statement stmt=con.createStatement();
                out.println("Welcome "+user);
                 Iterator it=cart.iterator();
            while(it.hasNext())
            {
                shopping_item si=(shopping_item) it.next();
                int itemcode=si.getItem_code();
                String itemname=si.getItem_name();
                int qty=si.getQty();
                double itemprice=si.getPrice();  
                       ResultSet qrs=stmt.executeQuery("select qty from items where itemcode='"+itemcode+"'");
            
                while(qrs.next())
        {
            avail= Integer.parseInt(qrs.getString("qty"));
        }
                if(avail<qty)
                {
                    
                    errormsgs.add("Requested Item " +itemname+ " Quantity Is Not Available (Max Avaialable: "+avail+")");
                    cart.remove(si);
                }
                 
            }
                 
        }
        
        catch(Exception e)
        {
            
        }
        finally {
            if(!(errormsgs.isEmpty()))
            {
                out.println("<font color='red'>");
            out.println("<ul>");
            for(int i=0;i<errormsgs.size();i++)
                out.println("<li>"+errormsgs.get(i).toString() +"</li>");
            out.println("</ul>");
            out.println("</font>");
            }
            else if(cart.isEmpty())
                    {
                        out.println("<font color='red'>");
                        out.println("<h1>Cart Is Empty</h1>");
                        out.println("</font>");
                    }
            else
            {
            java.util.Date date = Calendar.getInstance().getTime();
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String today = formatter.format(date);
            try
            {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","system","shobhit");
                Statement stmt=con.createStatement();
                
                ResultSet brs=stmt.executeQuery("select bookings from registration where username='"+user+"'");
                 while(brs.next())
                 {
                     book=Integer.parseInt(brs.getString("bookings"));
                 }
                 book++;
                    stmt.executeUpdate("insert into booking_history(booking_code,username,booking_date,status) values('BH_"+user+"_"+book+"','"+user+"',TO_DATE('"+today+"','DD/MM/YYYY'),'Pending')");
                 stmt.executeUpdate("create table BH_"+user+"_"+book+"(itemcode varchar(20),name varchar(20),qty varchar(20),itemprice varchar(20),total varchar(20))"); 
                 
                double total=0;
                 Iterator it=cart.iterator();
            while(it.hasNext())
            {
                double itemtotal=0;
                shopping_item si=(shopping_item) it.next();
                int itemcode=si.getItem_code();
                String itemname=si.getItem_name();
                int qty=si.getQty();
                double itemprice=si.getPrice();  
                itemtotal=(si.getPrice()*si.getQty());
                total+=itemtotal;
                       ResultSet qrs=stmt.executeQuery("select qty from items where itemcode='"+itemcode+"'");
            
                while(qrs.next())
        {
            avail= Integer.parseInt(qrs.getString("qty"));
        }
                    
                    stmt.executeUpdate("insert into BH_"+user+"_"+book+"(itemcode,name,qty,itemprice,total) values('"+itemcode+"','"+itemname+"','"+qty+"','"+itemprice+"','"+itemtotal+"')");
         
                
                stmt.executeUpdate("update items set qty='"+(avail-qty)+"' where name='"+itemname+"'");
                 
            }
                stmt.executeUpdate("insert into BH_"+user+"_"+book+" values('-','-','-','-','"+total+"')");
                 stmt.executeUpdate("update registration set bookings='"+book+"' where username='"+user+"'");
                 out.println("<h1>Cart Save Successful</h1>");
            
        } 
            
        catch(Exception e)
        {
            
        }
            }  
            out.println("<br><br><a href='"+shop+"'>Back to Shop</a>");
            
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
