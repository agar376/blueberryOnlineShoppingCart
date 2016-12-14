
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Register</title>
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
                <font color="white" size="10" face="times new roman"><a href= "<%=home%>" >blueberry</a></font>
            </div>
            </div>

<br>
            <div style="height: 700px;">
        <div style="float: left;display: block;width: 150px; "> </div>
        <div style="float: right;width: 150px; display: block; ">  </div>
<div style="display: block; text-align: center; padding: 0px 160px 5px 160px">  


       <h1>Registration Form</h1>
            <br><br>
            
            <form method="post" action="regservlet">
            <table border="1" cellspacing="5" cellpadding="5" align="center">
            <tr><td>UserName</td><td><input type="text" name="user"></td></tr>
            <tr><td>Password</td><td><input type="password" name="pass"></td></tr>
            <tr><td>Confirm Password</td><td><input type="password" name=cpass></td></tr>
            <tr><td>Name</td><td><input type="text" name="name"></td></tr>

            <tr><td>Date of Birth</td><td><select name="day" ><option> Select
            <%for(int i=1;i<=31;i++){%>
            <option><%=i%><% } %>                
            </select>
            <select name="month"> <option> Select
            <%for(int i=1;i<=12;i++){%>
            <option><%=i%><% } %>                 
            </select>
            <select name="year" ><option> Select
            <%for(long i=1990;i<=2011;i++){%>
            <option><%=i%><% } %>                
            </td></select></tr>
            
            <tr><td>Email</td><td><input type="text" name="email"></td></tr>
            <tr><td>Mobile Number</td><td><input type="text" name="phone"></td></tr>
            <tr><td><input type="submit" value="Submit"></td><td><input type="reset" value="Clear"></td></tr>
            </table>
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

