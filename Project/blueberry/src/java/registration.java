import java.util.*;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet(name = "registration", urlPatterns = {"/regservlet"})
public class registration extends HttpServlet implements HttpSessionListener
{

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException,SQLException,ServletException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        List errormsgs=new LinkedList();
        String home=response.encodeRedirectURL("loginservlet");
        String user=request.getParameter("user");
        String pass=request.getParameter("pass");
        String cpass=request.getParameter("cpass");
        String name=request.getParameter("name");
        String email=request.getParameter("email");
        String phone=request.getParameter("phone");
         String day=(request.getParameter("day"));
        String month=(request.getParameter("month"));
        String year=(request.getParameter("year"));
       out.println("<html>");
            out.println("<head>");
            out.println("<title>Register</title>");  
            out.println("<style> a:link {text-decoration: none} a:visited {text-decoration: none; color: blue} a:active {text-decoration: none} font a:visited{color: white} </style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div>");
        out.println("<div align='top' style='width:100%;height:60px;background-color: blue;'>");
           out.println("<div style='width:25%; float: left; text-align: right'> ");
out.println("<font color='white' size='10' face='times new roman'><a href= '"+home+"' >blueberry</a></font>");
out.println("</div></div>");
out.println("<br>  <div style='height: 700px;'>");
        out.println("<div style='float: left;display: block;width: 150px; '> </div>");
out.println("        <div style='float: right;width: 150px; display: block; '>  </div>");
out.println("<div style='display: block; text-align: center; padding: 0px 160px 5px 160px'>  ");

        
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","system","shobhit");
                Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT username FROM login where username='"+user+"'");
            while(rs.next())
            {
                if(user.equalsIgnoreCase(rs.getString("username")))
                errormsgs.add("UserName Already Exists");
            }
            if(user.length()==0)
                errormsgs.add("Enter Valid UserName");
            if(pass.length()<6)
                errormsgs.add("Password Cannot Be Less Than 6 Characters");
            if(!(pass.equals(cpass)))
                errormsgs.add("Password Do Not Match");
            if(name.length()==0)
                errormsgs.add("Enter Valid Name");
             if(!(day.equalsIgnoreCase("select")&&month.equalsIgnoreCase("select")&&year.equals("select")))
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
            if(email.length()==0)
                errormsgs.add("Enter Valid Email");
            if(phone.length()!=10)
                errormsgs.add("Enter Valid Mobile Number");
            
           
            
        }
        catch(Exception e)
        {
            errormsgs.add("Invalid Entry");
            
        }

         finally
        {
            
            if(!(errormsgs.isEmpty()))
            {
            
            out.println("<h1>Registration Form</h1>");
            out.println("<br><br>");
            out.println("<font color='red'>");
            out.println("<ul>");
            for(int i=0;i<errormsgs.size();i++)
                out.println("<li>"+errormsgs.get(i).toString() +"</li>");
            out.println("</ul>");
            out.println("</font>");
            out.println("<form method='post' action='regservlet'>");
            out.println("<table border='1' cellspacing='5' cellpadding='5'>");
            out.println("<tr><td>UserName</td><td><input type='text' name='user' value='"+user+"'></td></tr>");
            out.println("<tr><td>Password</td><td><input type='password' name='pass'></td></tr>");
            out.println("<tr><td>Confirm Password</td><td><input type='password' name=cpass></td></tr>");
            out.println("<tr><td>Name</td><td><input type='text' name='name' value='"+name+"'></td></tr>");

            out.println("<tr><td>Date of Birth</td><td><select name='day'> <option value='Select'>Select</option>");
            for(int i=1;i<=31;i++)
            {
            out.println("<option value='"+i+"'");
            if(!day.equals("Select"))
            if(i==Integer.parseInt(day))
                out.println("selected");
            out.println(">"+i+"</option>");
            }
            out.println("</select>");
            
            out.println("<select name='month'> <option value='Select'>Select");
            for(int i=1;i<=12;i++)
            {
            out.println("<option value='"+i+"'");
            if(!day.equals("Select"))
            if(i==Integer.parseInt(month))
                out.println("selected");
            out.println(">"+i+"</option>");
            }
            out.println("</select>");
            
            out.println("<select name='year'> <option value='Select'>Select</option>");
            for(long i=1990;i<=2011;i++)
            {
            out.println("<option value='"+i+"'");
            if(!day.equals("Select"))
            if(i==Long.parseLong(year))
                out.println("selected");
            out.println(">"+i+"</option>");
            }
            out.println("</select>");
                         
            out.println("</td></tr>");
            
            out.println("<tr><td>Email</td><td><input type='text' name='email' value='"+email+"'></td></tr>");
            out.println("<tr><td>Mobile Number</td><td><input type='text' name='phone' value='"+phone+"'></td></tr>");
            out.println("<tr><td><input type='submit' value='Submit'></td><td><input type='reset' value='Clear'></td></tr>");
            out.println("</table>");
            out.println("</form>");
            }
            else
            {
                try
                {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","system","shobhit");
                Statement stmt=con.createStatement();
                stmt.executeUpdate("insert into registration(username,name,dob,email,phone,bookings) values('"+user+"','"+name+"',TO_DATE('"+day+"-"+month+"-"+year+"','DD/MM/YYYY'),'"+email+"','"+phone+"',0)");
                stmt.executeUpdate("insert into login(username,password) values('"+user+"','"+pass+"')");
                RequestDispatcher view=request.getRequestDispatcher("loginservlet");
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
        try
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
