/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author maithili
 */
public class DataSource {

    Connection conn = null;

    public Connection createConnection() {

        try {
            // db parameters
            String url = "jdbc:mysql://localhost:3306/Cinema";
            String user = "root";
            String password = "root";

            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            //ResultSet rs = stmt.executeQuery("select * from Employee");
            //while (rs.next()) {
            //  System.out.println(rs.getString(1) + " " + rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            } catch (Exception e) {
                System.out.println("Error Occured " + e.toString());
            }
            return conn;
        }
    }
}
