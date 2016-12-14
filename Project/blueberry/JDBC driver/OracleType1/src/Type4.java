/*
 * Type4.java
 *
 * Created on January 23, 2011, 9:54 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author VMR
 */
import java.sql.*;
public class Type4 {
    public static void main(String[] args) {
        try {
            Connection con;
            Statement stmt;
            ResultSet rs;
            
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con=DriverManager.getConnection("jdbc:oracle:thin:hr/hr@localhost:1521:niit");
            stmt=con.createStatement();
            rs=stmt.executeQuery("Select * from employees");
            while(rs.next())
                System.out.println(rs.getString("first_name")+" "+rs.getString("last_name"));
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
    /** Creates a new instance of Type4 */
    public Type4() {
    }
    
}
