import java.io.*;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebFilter;

@WebFilter(filterName = "filter_login", urlPatterns = {"/loginservlet"})

public class filter_login implements Filter 
{
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException 
    {
        PrintWriter out=response.getWriter();
        Date sysDate = new Date ();
            String sysDateStr = sysDate.toString ();
            sysDateStr=(sysDateStr.substring(4, 10)+sysDate.toString ().substring(23));
        String str=((HttpServletRequest)request).getParameter("pass");
        String user=((HttpServletRequest)request).getParameter("user");
        String home=((HttpServletResponse)response).encodeRedirectURL("index.jsp");
        HttpSession session=((HttpServletRequest)request).getSession(false);
        out.println("<html>");
            out.println("<head>");
            out.println("<title>Login</title>"); 
            out.println("<style> a:link {text-decoration: none} a:visited {text-decoration: none; color: blue} a:active {text-decoration: none} font a:visited{color: white} </style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div>");
        out.println("<div align='top' style='width:100%;height:60px;background-color: blue;'>");
           out.println("<div style='width:25%; float: left; text-align: right'> ");
out.println("<font color='white' size='10' face='times new roman'><a href= '"+home+"' >blueberry</a></font>");
out.println("</div><div style='width: 25%; padding: 1px;float: right; text-align: right; text-transform: capitalize; text-decoration: blink'>"+sysDateStr+"</div></div>");
out.println("<br>  <div style='height: 700px;'>");
        out.println("<div style='float: left;display: block;width: 150px; '> </div>");
out.println("        <div style='float: right;width: 150px; display: block; '>  </div>");
out.println("<div style='display: block; text-align: center; padding: 0px 160px 5px 160px'>  ");

            
         try 
         {
            
        if(str.length()<6 || user.equals("") || (session == null))
        {
            out.println("<h1>blueberry Online Shopping</h1><br><font color='red'> Invalid Username Or Password </font>");
        
            out.println("<form method='post' action='loginservlet'><table border ='1' cellpadding='5' cellspacing='5'>");
            out.println("<tr><td>UserName</td><td><input type='text' name='user' value='"+user+"'></td></tr><tr><td>Password</td><td><input type='password' name='pass'></td></tr>");
            out.println("<tr><td><input type='submit' value='Log In'></td><td><input type='reset' value='Clear'></td></tr></table></form><br><a href='regis.jsp'>Sign Up </a>");
        }
        else
        {
            chain.doFilter(request,response);
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
public void destroy() {        
    }
    @Override
    public void init(FilterConfig filterConfig) {     
    }
}

