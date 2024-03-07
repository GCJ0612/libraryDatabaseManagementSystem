
import database.DBconnection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gouth
 */
public class callable {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        
        Connection con=DBconnection.getDBConnection();
//        CallableStatement cstmt=con.prepareCall("call square(?,?)");
          CallableStatement cstmt=con.prepareCall("CALL get_emp_heighest_sal(?)");
        cstmt.setInt(1,2);
        cstmt.execute();
        ResultSet rs=cstmt.executeQuery();
        while(rs.next()){
            System.out.println("Id "+ rs.getInt(1));
            System.out.println("Name "+ rs.getString(2));
            System.out.println("Salary "+rs.getFloat(3));
        }
       
        cstmt.close();
    }
}
 class Getempsal {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        
        Connection con=DBconnection.getDBConnection();
//        CallableStatement cstmt=con.prepareCall("call square(?,?)");
          CallableStatement cstmt=con.prepareCall("CALL get_emp_sal()");
//        cstmt.setInt(1,2);
        cstmt.execute();
        ResultSet rs=cstmt.executeQuery();
//        while(rs.next()){
//            System.out.println("Id "+ rs.getInt(1));
//            System.out.println("Name "+ rs.getString(2));
//            System.out.println("Salary "+rs.getFloat(3));
//        }

// ResultSet methods
        rs.next();
        System.out.println("Id "+ rs.getInt(1));
        rs.last();
        System.out.println("Id "+ rs.getInt(1));
       rs.first();
        System.out.println("Id "+ rs.getInt(1));
        rs.last();
        System.out.println("Id "+ rs.getInt(1));
//        for previous() it should not be in first index bcz it wont 
//loop in negative way if index is 1 if previious()  called it gie err
        rs.previous();
        System.out.println("Id "+ rs.getInt(1));
        rs.absolute(2);
        System.out.println("Id "+ rs.getInt(1));
        rs.absolute(-2);
        System.out.println("Id "+ rs.getInt(1));
        rs.relative(-2);
        System.out.println("Id "+ rs.getInt(1));
        rs.relative(1);
        System.out.println("Id "+ rs.getInt(1));
        cstmt.close();
    }
}
class CallFunction{
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
     Connection con=DBconnection.getDBConnection();
     CallableStatement cstmt=con.prepareCall("{?=call mul(?,?)}");
     cstmt.registerOutParameter(1, Types.INTEGER);
     cstmt.setInt(2, 5);
     cstmt.setInt(3, 8);
     cstmt.execute();
        System.out.println("Result: "+cstmt.getInt(1));
    }
}