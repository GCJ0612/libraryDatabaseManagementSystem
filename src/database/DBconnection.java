/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author gouth
 */
public class DBconnection {
    public static Connection getDBConnection() throws ClassNotFoundException, SQLException{
    Class.forName("com.mysql.jdbc.Driver");
    String dbName="db1";
     String url="jdbc:mysql://localhost:3306/";
         String userName="root";
         String passWord="root";
            Connection con=DriverManager.getConnection(url+dbName, userName, passWord);
            return con;
    }
}
