/*
 * Sample.java
 *
 * Created on January 23, 2011, 8:33 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author VMR
 */
import java.sql.*;
public class Sample {
    public static void main(String[] args) {
        
        try {
            Connection con;
            Statement stmt;
            ResultSet rs;
            
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            con=DriverManager.getConnection("jdbc:odbc:OracleData","hr","hr");
            stmt=con.createStatement();
            rs=stmt.executeQuery("Select * from employees");
            while(rs.next())
                System.out.println(rs.getString("first_name")+" "+rs.getString("last_name"));
        } catch (Exception ex) {
            System.out.println(ex);
            ex.printStackTrace();
        }
        
    }
    /** Creates a new instance of Sample */
    public Sample() {
    }
    
}
