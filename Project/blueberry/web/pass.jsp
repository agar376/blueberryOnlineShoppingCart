<%@page contentType="text/html" pageEncoding="UTF-8" import ="javax.servlet.http.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            a:link {text-decoration: none}
a:visited {text-decoration: none; color: blue}
a:active {text-decoration: none}
font a:visited{color: white}
#white{color: white; text-decoration: underline}
        </style>
        <title>Edit Details</title>
    </head>
    <%
            
            String url=response.encodeRedirectURL("changepass");
        String home=response.encodeRedirectURL("loginservlet");
        String logout=response.encodeRedirectURL("logout");
        %>
    <body>
        <div>
        <div align="top" style="width:100%;height:60px;background-color: blue;">
            <div style="width:25%; float: left; text-align: right">
                <font color="white" size="10" face="times new roman"><a href= "<%=home%>" >blueberry</a></font>
            </div>
            <div style="width: 25%; padding: 1px;float: right; text-align: right; text-transform: capitalize; text-decoration: blink">
                <a id="white" href= "<%=logout%>" >Logout</a>
            </div>
        </div>
         
        
        <br>
            <div style="height: 700px;">
        <div style="float: left;display: block;width: 150px; "> </div>
        <div style="float: right;width: 150px; display: block; ">  </div>
<div style="display: block; text-align: center; padding: 0px 160px 5px 160px">  

        <h1>Change Password</h1>
            <br><br>
            <br><br>
            <form method="post" action="<%=url%>"><center>
            <table border='1' cellspacing='5' cellpadding='5'>
            <tr><td>Old Password</td><td><input type='password' name=old></td></tr>
            <tr><td>Password</td><td><input type='password' name='pass'></td></tr>
            <tr><td>Confirm Password</td><td><input type='password' name=cpass></td></tr>
            
            </table>
            <br><br><input type='submit' value='Update'><input type='Reset' value='Clear'></center>
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
