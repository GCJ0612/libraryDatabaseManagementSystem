/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1;

import database.DBconnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author gouth
 */
public class AddBatch {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection con=DBconnection.getDBConnection();
        Statement stmt=con.createStatement();
        String sql1="update players set teamname='KKR' where pid=102";
        String sql2="update players set teamname='LCG' where pid=104";
        stmt.addBatch(sql1);
        stmt.addBatch(sql2);
        int[] count=stmt.executeBatch();
        if(count[0]>0||count[1]>0){
            System.out.println("Records updated");
            
        }else{
            System.out.println("Records not updated");
        }
    }
}
