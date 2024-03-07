package p1;


import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

class Emp {

    int empid;
    String name;
    float salary;
    String city;

    public Emp() {
    }

    public Emp(int empid, String name, float salary, String city) {
        this.empid = empid;
        this.name = name;
        this.salary = salary;
        this.city = city;
    }

    public int getEmpid() {
        return empid;
    }

    public void setEmpid(int empid) {
        this.empid = empid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}

public class Employee {

    public static void main(String args[]) throws ClassNotFoundException, SQLException {
        Scanner scan = new Scanner(System.in);
        Emp emp1 = new Emp();
        //JDBC Steps S1:Load the Driver
        Class.forName("com.mysql.jdbc.Driver");
        //S2:Connection to DB
        Connection con = null;

        //S3: Cretea sql statement
        while (true) {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1", "root", "root");
            System.out.println("Enter the choice ");
            System.out.println("1 to insert records \n2 for deletion \n3 View All"
                    + " \n4 to view specific details\n5 to update details "
                    + "\n6 details of emp who is having max salary\n7details of emp who is having min salary \n8 to exit");
            int choice = scan.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter Emp id");
                    int empid = scan.nextInt();
                    scan.nextLine();
                    System.out.println("Enter Emp Name");
                    String empName = scan.nextLine();
                    System.out.println("Enter Emp Salary");
                    float empSalry = scan.nextFloat();
                    scan.nextLine();
                    System.out.println("Enter Emp City");
                    String empCity = scan.nextLine();

                    Emp emp = new Emp(empid, empName, empSalry, empCity);
//                    String sql = "INSERT INTO EMP VALUES('" + emp.empid + "','" + emp.name + "',"
//                            + "'" + emp.salary + "','" + emp.city + "')";
                   
                    String sql="insert into emp values(?,?,?,?)";
                    Statement stmt = con.createStatement();
                    PreparedStatement ps=con.prepareStatement(sql);
                    ps.setInt(1, emp.getEmpid());
                    ps.setString(2, emp.getName());
                    ps.setFloat(3, emp.getSalary());
                    ps.setString(4, emp.getCity());
                    //S4:
                    int i = ps.executeUpdate();
                    if (i > 0) {
                        System.out.println("Record Inserted!");
                    } else {
                        System.out.println("Record Not insete to DB!");
                    }
                    stmt.close();
                    ps.close();
                    break;
                case 2:
                    System.out.println("Enter the ID to be deleted");
                    empid = scan.nextInt();
                    emp1.setEmpid(empid);
                    String delsql = "DELETE FROM EMP WHERE EMPID='" + emp1.getEmpid() + "'";
                    stmt = con.createStatement();
                    System.out.println("Do You want to delete the reocrd: " + empid + "?");
                    System.out.println("Press 'Y' to delete 'N' to No");
                    char choice1 = scan.next().charAt(0);
                    if (choice1 == 'Y' || choice1 == 'y') {
                        i = stmt.executeUpdate(delsql);
                        if (i > 0) {
                            System.out.println("Record deleted!");
                        } else {
                            System.out.println("ID Doesn't Exist!");
                        }
                    } else {
                        System.out.println("Deletion is Cancelled!");
                    }
                    stmt.close();
                    break;
                case 3:
                    String viewall = "SELECT * FROM EMP";
                    stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(viewall);
                    while (rs.next()) {
                        System.out.println("ID:" + rs.getInt("empid"));
                        System.out.println("Name:" + rs.getString(2));
                        System.out.println("Salary:" + rs.getFloat(3));
//                        System.out.println("City:" + rs.getString(4));
                        System.out.println("*****************");
                    }
                    rs.close();
                    break;
                case 4:
                    System.out.println("Enter the Employee ID : ");
                    empid = scan.nextInt();
                    emp1.setEmpid(empid);
                    String specificsql = "select * from emp where empid='" + emp1.getEmpid() + "'";
                    stmt = con.createStatement();
                    ResultSet rs1 = stmt.executeQuery(specificsql);
                    if (rs1.next()) {
                        System.out.println("ID:" + rs1.getInt(1));
                        System.out.println("Name:" + rs1.getString(2));
                        System.out.println("Salary:" + rs1.getFloat(3));
                        System.out.println("City:" + rs1.getString(4));
                        System.out.println("*****************");
                    } else {
                        System.out.println("Entered empid doesn't exist");
                    }
                    rs1.close();
                    break;
                case 5:
                    System.out.println("Enter the Employee id to be updated");
                    empid = scan.nextInt();
                    emp1.setEmpid(empid);
                    scan.nextLine();
                    System.out.println("Enter the new name :");
                    String newname = scan.nextLine();
                    emp1.setName(newname);
                    System.out.println("Enter the new Salary :");
                    float newsal = scan.nextFloat();
                    emp1.setSalary(newsal);

                    String updatesql = "update emp set salary='" + emp1.getSalary() + "',name='" + emp1.getName() + "' "
                            + "where empid='" + emp1.getEmpid() + "'";
                    stmt = con.createStatement();
                    i = stmt.executeUpdate(updatesql);
                    if (i > 0) {
                        System.out.println("Details updated successfully");
                    } else {
                        System.out.println("Invalid empid / ID doesn't exist");
                    }
                    stmt.close();
                    break;
                case 6:
//                    System.out.println("Enter the Employee id ");
//                    empid=scan.nextInt();
                    String maxssql = "SELECT empid,name,salary FROM emp WHERE salary=(SELECT MAX(salary) FROM emp)";
                    stmt = con.createStatement();
                    ResultSet rs2 = stmt.executeQuery(maxssql);
                    if (rs2.next()) {
                        System.out.println("Employee ID : " + rs2.getInt("empid"));
                        System.out.println("emp name  : " + rs2.getString(2));
                        System.out.println("salary : " + rs2.getFloat(3));
                    }else{
                        System.out.println("ID doesn't exist");
                    }
                    System.out.println("***********************************************************************");
                    rs2.close();
                    break;
                    case 7:
//                    System.out.println("Enter the Employee id ");
//                    empid=scan.nextInt();
                    String minssql = "SELECT empid,name,salary FROM emp WHERE salary=(SELECT MIN(salary) FROM emp)";
                    stmt = con.createStatement();
                    rs2 = stmt.executeQuery(minssql);
                    if (rs2.next()) {
                        System.out.println("Employee ID : " + rs2.getInt("empid"));
                        System.out.println("emp name  : " + rs2.getString(2));
                        System.out.println("salary : " + rs2.getFloat(3));
                    }else{
                        System.out.println("ID doesn't exist");
                    }
                    System.out.println("***********************************************************************");
                    rs2.close();
                    break;
                case 8:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Choice");
                //Stament 5:Close the connection
            }
            con.close();
        }

    }
}
