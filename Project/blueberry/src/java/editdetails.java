import com.sun.org.apache.bcel.internal.generic.GotoInstruction;
import java.sql.*;
import java.io.*;
import java.math.BigInteger;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
@WebServlet(name = "editdetails", urlPatterns = {"/editdetails"})
public class editdetails extends HttpServlet implements HttpSessionListener {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession s=request.getSession();
        String user=(String) s.getAttribute("user");
        String url=response.encodeRedirectURL("editdetails");
        String home=response.encodeRedirectURL("loginservlet");
        String logout=response.encodeRedirectURL("logout");
            
        
          List errormsgs=new LinkedList();
         String day=(request.getParameter("day"));
        String month=(request.getParameter("month"));
        String year=(request.getParameter("year"));
        String name=request.getParameter("name");
        String email=request.getParameter("email");
        String phone=request.getParameter("phone");
        String date=null;
        
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Edit Details</title>");  
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
       out.println("Welcome "+user);
            out.println("<br><br>");
     
out.println("<h1>Edit Details</h1>");
            
            
            
        try {
            
            
            if(phone.length()>0 && phone.length()!=10)
                errormsgs.add("Enter Valid Mobile Number");
            if(day.equalsIgnoreCase("select")&&month.equalsIgnoreCase("select")&&year.equalsIgnoreCase("select"))
                date=day+"-"+month+"-"+year;
            else
            {
             if(!(day.equalsIgnoreCase("select")))
                 {
                     if(!(month.equalsIgnoreCase("select")))
                     {
                         if(!(year.equals("select")))
                         {
                                 Calendar cal = Calendar.getInstance();
  Calendar currentcal = Calendar.getInstance();
  cal.set(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day));
  currentcal.set(currentcal.get(Calendar.YEAR),currentcal.get(Calendar.MONTH), currentcal.get(Calendar.DAY_OF_MONTH));
            if(cal.after(currentcal))
                errormsgs.add("Invalid Date Of Birth");
                             
                         }
                         else
                             errormsgs.add("Enter Valid Date Of Birth");
                     }
                     else
                         errormsgs.add("Enter Valid Date Of Birth");
                     
                 
                 }
                else
                         errormsgs.add("Enter Valid Date Of Birth"); 
             
            }
        }
        
        catch(Exception e)
        {
            
        }
        finally {  
            
            out.println("<br><br>");  
            if(!(errormsgs.isEmpty()))
            {
           
            out.println("<font color='red'>");
            out.println("<ul>");
            for(int i=0;i<errormsgs.size();i++)
                out.println("<li>"+errormsgs.get(i).toString() +"</li>");
            out.println("</ul>");
            out.println("</font>");
            out.println("<form method='post' action='"+url+"'>");
            out.println("<table border='1' cellspacing='5' cellpadding='5' align='center'>" +
            "<tr><td>Name</td><td><input type='text' name='name'></td></tr>" +

            "<tr><td>Date of Birth</td><td><select name='day' ><option> Select");
            for(int i=1;i<=31;i++){
            out.println("<option>"+i); }                
            out.println("</select>"+
            "<select name='month'> <option> Select");
            for(int i=1;i<=12;i++){
            out.println("<option>"+i); }                  
            out.println("</select>"+
            "<select name='year' ><option> Select");
            for(long i=1990;i<=2011;i++){
            out.println("<option>"+i);}                
            out.println("</td></select></tr>"+
            
            "<tr><td>Email</td><td><input type='text' name='email'></td></tr>"+
            "<tr><td>Mobile Number</td><td><input type='text' name='phone'></td></tr>"+
            "<tr><td><input type='submit' value='Submit'></td><td><input type='reset' value='Clear'></td></tr>"+
            "</table>"+
            "</form>");
            }
            else if(day.equalsIgnoreCase("select")&&month.equalsIgnoreCase("select")&&year.equalsIgnoreCase("select")&&name.isEmpty()&&phone.isEmpty()&&email.isEmpty())
                    {
                         out.println("<font color='red'>");
                         out.println("<h1>Nothing to Update</h1>");
                         out.println("</font>");
                    }
            else
            {
               try
                {
                Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","system","shobhit");
                Statement stmt=con.createStatement();
                
                if(!(name.length()==0))
                stmt.executeUpdate("update registration set name='"+name+"' where username='"+user+"'");
                
                if(!(day.equalsIgnoreCase("select")) && !(month.equalsIgnoreCase("select")) && !(year.equals("select")))
                stmt.executeUpdate("update registration set dob=TO_DATE('"+date+"','DD/MM/YYYY') where username='"+user+"'");
                
                if(phone.length()==10)
                stmt.executeUpdate("update registration set phone='"+phone+"' where username='"+user+"'");
                
                if(email.length()>0)
                stmt.executeUpdate("update registration set email='"+email+"' where username='"+user+"'");
                
                
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
