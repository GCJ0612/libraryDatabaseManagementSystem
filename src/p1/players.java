/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author gouth
 */
public class players {

    int pid;
    String pname;
    String teamname;
    int nof30;
    int nof50;
    int noof100;

    public players() {
    }

    public players(int pid, String pname, String teamname, int nof30, int nof50, int noof100) {
        this.pid = pid;
        this.pname = pname;
        this.teamname = teamname;
        this.nof30 = nof30;
        this.nof50 = nof50;
        this.noof100 = noof100;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }

    public int getNof30() {
        return nof30;
    }

    public void setNof30(int nof30) {
        this.nof30 = nof30;
    }

    public int getNof50() {
        return nof50;
    }

    public void setNof50(int nof50) {
        this.nof50 = nof50;
    }

    public int getNoof100() {
        return noof100;
    }

    public void setNoof100(int noof100) {
        this.noof100 = noof100;
    }

}

class cricket {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        players play = new players();
        Scanner scan = new Scanner(System.in);
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = null;
        while (true) {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1", "root", "root");
            System.out.println("1 Add Player Details "
                    + "\n2 Delete specific Player details "
                    + "\n3 View all Player Details "
                    + "\n 4 View Specific player Details "
                    + "\n5 View the player details pid pname "
                    + "teamname noof100 who has scored more no,of 100"
                    + "\n6 Exit");
            int ch = scan.nextInt();
            switch (ch) {
                case 1: // Add the player Details(Insert)
                    System.out.println("Enter Player Id : ");
                    int pid = scan.nextInt();
                    scan.nextLine();
                    System.out.println("Enter Player Name : ");
                    String pname = scan.nextLine();
                    System.out.println("Enter Player TeamName : ");
                    String teamname = scan.nextLine();
                    System.out.println("Enter Player's Number of 30s : ");
                    int nof30 = scan.nextInt();
                    scan.nextLine();
                    System.out.println("Enter Player's Number of 50s : ");
                    int nof50 = scan.nextInt();
                    scan.nextLine();
                    System.out.println("Enter Player's Number of 100s : ");
                    int nof100 = scan.nextInt();
                    scan.nextLine();
                    players pl = new players(pid, pname, teamname, nof30, nof50, nof100);
//                    String insertsql = "INSERT INTO PLAYERS VALUES('" + pl.pid + "','" + pl.pname + "','" + pl.teamname + "',"
//                            + "'" + pl.nof30 + "','" + pl.nof50 + "','" + pl.noof100 + "')";
                    
                    String insertsql="insert into players values(?,?,?,?,?,?)";
                    Statement stmt = con.createStatement();
                    PreparedStatement ps=con.prepareStatement(insertsql);
                    ps.setInt(1, pl.getPid());
                    ps.setString(2, pl.getPname());
                    ps.setString(3, pl.getTeamname());
                    ps.setInt(4, pl.getNof30());
                    ps.setInt(5, pl.getNof50());
                    ps.setInt(6, pl.getNoof100());
                    
                    
                    int i = ps.executeUpdate();
                    if (i > 0) {
                        System.out.println("Data inserted successfully");
                    } else {
                        System.out.println("Data is not inserted");
                    }
                    System.out.println("***********************************************************************");
                    stmt.close();
                    ps.close();
                    break;
                case 2://Delete the specific Player Details(delete where)
                    System.out.println("Enter the player Id to delete the data");
                    pid = scan.nextInt();
                    play.setPid(pid);
                    //String delsql = "DELETE FROM PLAYERS WHERE PID='" + play.getPid() + "'";
                    String delsql = "DELETE FROM PLAYERS WHERE PID=?";
                   PreparedStatement ps1=con.prepareStatement(delsql);
                    ps1.setInt(1,play.getPid());
                    stmt = con.createStatement();
                    System.out.println("Do you really want to delete: Y/N:");
                    char YorN = scan.next().charAt(0);
                    if (YorN == 'y' || YorN == 'y') {
                        i = ps1.executeUpdate();
                        if (i > 0) {
                            System.out.println("Data deleted successfully");
                        } else {
                            System.out.println("Player id Doesn't exist");
                        }
                    } else {
                        System.out.println("Deletion cancelled");
                    }
                    System.out.println("***********************************************************************");
                    stmt.close();
                    ps1.close();
                    break;
                case 3://View all the Player Details(select)
                    String viewall = "SELECT * FROM PLAYERS";
                    stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(viewall);
                    while (rs.next()) {
                        System.out.println("Player ID : " + rs.getInt("pid"));
                        System.out.println("Player  : " + rs.getString(2));
                        System.out.println("Player TeamName : " + rs.getString(3));
                        System.out.println("Player's no,of 30s : " + rs.getInt(4));
                        System.out.println("Player's no,of 50s : " + rs.getInt(5));
                        System.out.println("Player's no,of 100s : " + rs.getInt(6));
                    }
                    System.out.println("***********************************************************************");
                    rs.close();
                    break;
                case 4://View Specific Player Details

                    System.out.println("Enter Player ID to display the data");
                    pid = scan.nextInt();
                    play.setPid(pid);
                    String viewsql = "SELECT * FROM PLAYERS WHERE PID='" + play.getPid() + "'";
                    stmt = con.createStatement();
                    ResultSet rs1 = stmt.executeQuery(viewsql);
                    if (rs1.next()) {
                        System.out.println("Player ID : " + rs1.getInt("pid"));
                        System.out.println("Player  : " + rs1.getString(2));
                        System.out.println("Player TeamName : " + rs1.getString(3));
                        System.out.println("Player's no,of 30s : " + rs1.getInt(4));
                        System.out.println("Player's no,of 50s : " + rs1.getInt(5));
                        System.out.println("Player's no,of 100s : " + rs1.getInt(6));
                    } else {
                        System.out.println("Employee not found");
                    }
                    System.out.println("***********************************************************************");
                    rs1.close();
                    break;
                case 5:/*View the player deatils pid pname teamame noof100 who has scoreed more no,of 100*/
                    String hundredssql = "SELECT PID,PNAME,TEAMNAME,NOOF100 FROM PLAYERS WHERE NOOF100=(SELECT MAX(NOOF100) FROM PLAYERS)";
                    stmt = con.createStatement();
                    ResultSet rs2 = stmt.executeQuery(hundredssql);
                    if (rs2.next()) {
                        System.out.println("Player ID : " + rs2.getInt("pid"));
                        System.out.println("Player  : " + rs2.getString(2));
                        System.out.println("Player TeamName : " + rs2.getString(3));
                        System.out.println("Max no,of 100s : " + rs2.getInt(4));
                    }else{
                        System.out.println("Invalid ID /id Doesen't exist");
                    }
                    System.out.println("***********************************************************************");
                    rs2.close();
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
            con.close();
        }
    }
}
