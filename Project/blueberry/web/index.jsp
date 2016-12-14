
<%@page contentType="text/html" pageEncoding="UTF-8" import ="java.util.Date" import ="javax.servlet.http.*"%>
 <%
            
            String url=response.encodeRedirectURL("loginservlet");
        %>
        
<!DOCTYPE html>
<html>
    <head>
        <title>blueberry Online Shopping</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            a:link {text-decoration: none}
a:visited {text-decoration: none; color: blue}
a:active {text-decoration: none}
font a:visited{color: white}

        </style>
    </head>
    <body>
        <div>
        <div align="top" style="width:100%;height:60px;background-color: blue;">
            <div style="width:25%; float: left; text-align: right">
                <%String home=response.encodeRedirectURL("index.jsp");%>
                <font color="white" size="10" face="times new roman"><a href= "<%=home%>">blueberry</a></font>
            </div>
            <div style="width: 25%; padding: 1px;float: right; text-align: right; text-transform: capitalize; text-decoration: blink">
              
          <%
            Date sysDate = new Date ();
            String sysDateStr = sysDate.toString ();
            sysDateStr=(sysDateStr.substring(4, 10)+sysDate.toString ().substring(23));
        %>
             <%=sysDateStr%>
            
            <br />
            <%
             ServletContext c=getServletContext();
             Integer count=(Integer) c.getAttribute("count");
            %>
            Number of Hits <%=count%>
          
        
            
            </div>
            </div>
            <br>
            <div style="height: 700px;">
        <div style="float: left;display: block;width: 150px; "> </div>
        <div style="float: right;width: 150px; display: block; ">  </div>
<div style="display: block; text-align: center; padding: 0px 160px 5px 160px">  
    <h1>blueberry Online Shopping</h1>
    
        <form method="post" action="<%=url%>">
            <table border ="2" align="center">
                <tr>
                    <td>UserName</td>
                    <td><input type="text" name="user"></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="pass"></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Log In"></td>
                    <td><input type="reset" value="Clear"></td>
                </tr>
            </table>
            <br>
            <a href="regis.jsp">Sign Up </a>
            <br>
        </form>
                  
        </div>
            </div>
            <div style="clear: both">
                <center>
                    &copy; Copyright @ Shobhit Agarwal
                    <br> 
                    Contact: 
                    <address>9501240890</address>
                </center>
            </div>
        </div>
            
        </body>
</html>
