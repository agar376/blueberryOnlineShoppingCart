import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;

import javax.servlet.http.*;
@WebFilter(filterName = "filter_view", urlPatterns = {"/loginservlet"})
public class filter_view implements Filter {
    
    private FilterConfig filterConfig = null;
    
    public filter_view() {
    }    
    
    
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        String pass=((HttpServletRequest)request).getParameter("pass");
        String user=((HttpServletRequest)request).getParameter("user");
        PrintWriter out = response.getWriter();
         out.println("<html>");
            out.println("<head>");
            out.println("<title>View</title>");  
            out.println("<style> a:link {text-decoration: none} a:visited {text-decoration: none; color: blue} a:active {text-decoration: none} font a:visited{color: white} </style>");
            out.println("</head>");
            out.println("<body>");
            
       try 
         {
              Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","system","shobhit");
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("SELECT password FROM login where username='"+user+"'");
        String match="";
        while(rs.next())
        {
            match=rs.getString("password");
        }
         if((user.equalsIgnoreCase("admin")) && (pass.equals("shobhit")))
            {
                RequestDispatcher view=request.getRequestDispatcher("adminservlet");
                view.forward(request, response);
            }
         else if((user.equalsIgnoreCase("employee")) && (pass.equals("shobhit")))
                 {
                RequestDispatcher view=request.getRequestDispatcher("employee");
                view.forward(request, response);
                     
                 }
         else if(pass.equals(match))
                    {
                         RequestDispatcher view=request.getRequestDispatcher("loginservlet");
                         view.forward(request, response);
                    }
            else
            {
                out.println("<h1>blueberry Online Shopping</h1><br><font color='red'>");
                out.println("Invalid Username Or Password");
                out.println("</font>");
                out.println("<form method='post' action='loginservlet'><table border ='1' cellpadding='5' cellspacing='5'>");
                out.println("<tr><td>UserName</td><td><input type='text' name='user' value='"+user+"'></td></tr><tr><td>Password</td><td><input type='password' name='pass'></td></tr>");
                out.println("<tr><td><input type='submit' value='Log In'></td><td><input type='reset' value='Clear'></td></tr></table></form><br><a href='regis.jsp'>Sign Up </a>");
        
            }
         }
        catch(Exception e)
        {
        }
            finally {    
            
            out.println("</body>");
            out.println("</html>");
            out.close();
        }
        
        
    }

    
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    } 
    @Override
    public void destroy() {        
    }

    
    @Override
    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
        }

    
    
    
} 