<%@page import="java.sql.*"%>
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
        <title>Administrator: Insert/Update</title>
    </head>
    <%
            
            String url=response.encodeRedirectURL("update");
        String home=response.encodeRedirectURL("adminservlet");
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

            <%
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","system","shobhit");
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from items");
            %>
            <form method="post" action="<%=url%>"><center>
                         <h1>Administrator</h1><br><h3>Insert/Update</h3><br>If Updating Set New Values. (New Price or Previous + Purchased Quantity) <br><br>
            <form method='post' action='update'>
            <table cell spacing='100%'>
            
             <tr><th colspan="2">Item Code and Item Name</th><th>Total Quantity</th><th>Item Price</th><th>Select</th></tr>
            <tr><td colspan="2"><select name='itemcode'><option value='Select'>Select</option>
                        <%
                        while(rs.next())
             {
                    %>
                 <option value=<%=rs.getString("itemcode")%>> <%=rs.getString("itemcode")%> and <%=rs.getString("name")%></option>
            
                 <%
                        }
            %>
             </select></td>
             <td><input type='text' name='u1'></td>
             <td><input type='text' name='u2'></td>
             <td><input type='radio' name='code' value='Update' checked> Update</td>
             </tr>

            <tr>
            <td><input type='text' name='i1'></td>
             <td><input type='text' name='i2'></td>
             <td><input type='text' name='i3'></td>
             <td><input type='text' name='i4'></td>
             <td><input type='radio' name='code' value='Insert'> Insert</td>
            </tr>
            </table><br><br><input type='submit' value='Run'></form>
           
            
            
            

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
