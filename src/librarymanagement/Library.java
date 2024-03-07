/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagement;

import database.DBconnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * 1Write a JDBC program to insert record to all the tables. 2. Write a JDBC
 * program to Update the book details 3. Write a JDBC program to Delete the book
 * details 4. Write a JDBC program to get the details of book borrowed by a
 * specific student from library. In the following format
 *
 * @author gouth
 */
class Student {

    int sid;
    String studentName;
    String email;
    String password;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public Student(String studentName, String email, String password) {
        this.studentName = studentName;
        this.email = email;
        this.password = password;
    }

    public Student() {
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

class Book {

    int bookId;
    String bookName;
    int noofCopies;
    String category;

    public Book() {

    }

    public Book(String bookName, int noofCopies, String category) {

        this.bookName = bookName;
        this.noofCopies = noofCopies;
        this.category = category;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getNoofCopies() {
        return noofCopies;
    }

    public void setNoofCopies(int noofCopies) {
        this.noofCopies = noofCopies;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}

class BorrowedBooks {

    int bookId;
    int sid;
    String borrowDate;
    String returnDate;

    public BorrowedBooks() {
    }

    public BorrowedBooks(int bookid, int sid, String borrowDate, String returnDate) {
        this.bookId = bookid;
        this.sid = sid;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

}

public class Library {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Scanner scan = new Scanner(System.in);
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        Connection con = DBconnection.getDBConnection();
        do {
            System.out.println("1 Insert records\n2 Update Book Deetails"
                    + "\n3 Delete Book Details"
                    + "\n4 Fetch details of book borrowed by a specific student from library"
                    + "\n5 print the total numbers of books in an library of particular category."
                    + "\n6 Exit");
            System.out.println("Enter your choice");
            int choice = scan.nextInt();
            switch (choice) {
                case 1: {
                    char addmore;
                    do {
                        System.out.println("1 Insert student Details\n2 insert Book Details"
                                + "\n3 insert Borrowed Book Details");
                        System.out.println("Enter your choice");
                        int ch = scan.nextInt();
                        switch (ch) {
                            case 1: {

                                insertStudent(pstmt, con);

                            }

                            break;
                            case 2: {

                                insertBook(pstmt, con);

                            }
                            break;
                            case 3: {

                                insertBorrowedBook(pstmt, con);

                            }
                            break;

                            default:
                                System.out.println("Invalid Choice");
                        }
                        System.out.println("Do you want to add more y/n: ");
                        addmore = scan.next().charAt(0);
                    } while (addmore == 'Y' || addmore == 'y');
                }
                break;

                case 2: {
                    char addMore;
                    do {
                        updateBookDetails(pstmt, con);
                        System.out.println("Do you want to Update more y/n: ");
                        addMore = scan.next().charAt(0);
//            scan.nextLine();
                    } while (addMore == 'Y' || addMore == 'y');
                }
                break;
                case 3: {
                    char addMore;
                    do {
                        deleteBookDetails(pstmt, con);
                        System.out.println("Do you want to Delete more y/n: ");
                        addMore = scan.next().charAt(0);
//            scan.nextLine();
                    } while (addMore == 'Y' || addMore == 'y');
                }
                break;
                case 4: {
                    char addMore;
                    do {
                        selectBookDetails(pstmt, con, rs);
                        System.out.println("Do you want to Fetch more y/n: ");
                        addMore = scan.next().charAt(0);
//            scan.nextLine();
                    } while (addMore == 'Y' || addMore == 'y');
                }
                break;
                case 5: {
                    countBookDetails(pstmt, con, rs);
                }
                break;
                case 6:
                    System.out.println("Exiting");
                    System.exit(0);

                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        } while (true);

    }

    public static void insertStudent(PreparedStatement pstmt, Connection con) throws ClassNotFoundException, SQLException {
        Scanner scan = new Scanner(System.in);
        con = DBconnection.getDBConnection();

        System.out.println("Enter Student Name: ");
        String studentName = scan.nextLine();
        System.out.println("Enter Student Email: ");
        String email = scan.nextLine();
        System.out.println("Enter Password: ");
        String password = scan.nextLine();
        Student std = new Student(studentName, email, password);
        String insertsql = "insert into student(studentName,email,password) values(?,?,?)";
        pstmt = con.prepareStatement(insertsql);
        pstmt.setString(1, std.getStudentName());
        pstmt.setString(2, std.getEmail());
        pstmt.setString(3, std.getPassword());
        int i = pstmt.executeUpdate();
        if (i > 0) {
            System.out.println("Record Inserted!");
        } else {
            System.out.println("Record Not insete to DB!");
        }
        pstmt.close();
        con.close();

    }

    public static void insertBook(PreparedStatement pstmt, Connection con) throws ClassNotFoundException, SQLException {
        Scanner scan = new Scanner(System.in);
        con = DBconnection.getDBConnection();

        System.out.println("Enter Book Name: ");
        String bookName = scan.nextLine();
        System.out.println("Enter No of Copies: ");
        int noofcopies = scan.nextInt();
        scan.nextLine();
        System.out.println("Enter category : "
                + "\nuser restricted to choose only ('Computer','Electroics','Mechanical') books");
        String category = scan.nextLine();
        Book bk = new Book(bookName, noofcopies, category);
        String insertsql = "insert into book(bookName,noofcopies,category) values(?,?,?)";
        pstmt = con.prepareStatement(insertsql);
        pstmt.setString(1, bk.getBookName());
        pstmt.setInt(2, bk.getNoofCopies());
        pstmt.setString(3, bk.getCategory());
        int i = pstmt.executeUpdate();
        if (i > 0) {
            System.out.println("Record Inserted! for book table");
        } else {
            System.out.println("Record Not insete to DB!");
        }
        pstmt.close();
        con.close();
    }

    public static void insertBorrowedBook(PreparedStatement pstmt, Connection con) throws ClassNotFoundException, SQLException {
        Scanner scan = new Scanner(System.in);
        con = DBconnection.getDBConnection();
        System.out.println("Enter Book id");
        int bookid = scan.nextInt();
        scan.nextLine();
        System.out.println("Enter Student id");
        int sid = scan.nextInt();
        scan.nextLine();
        System.out.println("Enter Book borrowed Date: ");
        String borrowedDate = scan.nextLine();
        System.out.println("Enter Book Return Date: ");
        String returnDate = scan.nextLine();
        BorrowedBooks bb = new BorrowedBooks(bookid, sid, borrowedDate, returnDate);
        String insertsql = "insert into BorrowedBooks(bookid,sid,borrowedDate,returnDate) values(?,?,?,?)";
        pstmt = con.prepareStatement(insertsql);
        pstmt.setInt(1, bb.getBookId());
        pstmt.setInt(2, bb.getSid());
        pstmt.setString(3, bb.getBorrowDate());
        pstmt.setString(4, bb.getReturnDate());
        int i = pstmt.executeUpdate();
        if (i > 0) {
            System.out.println("Record Inserted! for book table");
        } else {
            System.out.println("Record Not insete to DB!");
        }
        pstmt.close();
        con.close();
    }

    public static void updateBookDetails(PreparedStatement pstmt, Connection con) throws ClassNotFoundException, SQLException {
        Scanner scan = new Scanner(System.in);
        Book bb = new Book();
        con = DBconnection.getDBConnection();
        System.out.println("Enter Book Name");
        String bookName = scan.nextLine();
        bb.setBookName(bookName);
        System.out.println("Enter new No of Copies: ");
        int noofcopies = scan.nextInt();
        scan.nextLine();
        bb.setNoofCopies(noofcopies);

        String updateBookSQL = "UPDATE Book SET noofcopies = ? WHERE bookName = ?";
        pstmt = con.prepareStatement(updateBookSQL);
        pstmt.setInt(1, bb.getNoofCopies());
        pstmt.setString(2, bb.getBookName());

        int i = pstmt.executeUpdate();
        if (i > 0) {
            System.out.println("Record Updated!");
        } else {
            System.out.println("Record Not Updated to DB!");
        }
        pstmt.close();
        con.close();
    }

    public static void deleteBookDetails(PreparedStatement pstmt, Connection con) throws ClassNotFoundException, SQLException {
        Scanner scan = new Scanner(System.in);
        Book bb = new Book();
        con = DBconnection.getDBConnection();
        System.out.println("Enter Book Name");
        String bookName = scan.nextLine();
        bb.setBookName(bookName);
        String deleteBookSQL = "delete from book WHERE bookName = ?";
        pstmt = con.prepareStatement(deleteBookSQL);
        pstmt.setString(1, bb.getBookName());
        int i = pstmt.executeUpdate();
        if (i > 0) {
            System.out.println("Record Deleted!");
        } else {
            System.out.println("Record Not Deleted from DB!");
        }
        pstmt.close();
        con.close();
    }

    public static void selectBookDetails(PreparedStatement pstmt, Connection con, ResultSet rs) throws ClassNotFoundException, SQLException {
        Scanner scan = new Scanner(System.in);
        Student std = new Student();
        con = DBconnection.getDBConnection();
        System.out.println("Enter Student Id");
        int sid = scan.nextInt();
        std.setSid(sid);
        String fetchBookSQL = "select s.sid,s.studentName,b.bookName,bb.borrowedDate"
                + " from student s join borrowedbooks bb "
                + "on s.sid=bb.sid join book b on bb.bookid=b.bookid where s.sid=?";
        pstmt = con.prepareStatement(fetchBookSQL);
        pstmt.setInt(1, std.getSid());
        rs = pstmt.executeQuery();
        if (rs.next()) {
            System.out.println("Student id : " + rs.getInt(1));
            System.out.println("Student Name : " + rs.getString(2));
            System.out.println("Book Borrowed : " + rs.getString(3));
            System.out.println("Borrowed Date : " + rs.getString(4));

        } else {
            System.out.println("Record Not Found ");
        }
        pstmt.close();
        con.close();
        rs.close();
    }

    public static void countBookDetails(PreparedStatement pstmt, Connection con, ResultSet rs) throws ClassNotFoundException, SQLException {
        Scanner scan = new Scanner(System.in);
        Book b = new Book();
        con = DBconnection.getDBConnection();

        String countBookSQL = "select category,sum(noofcopies) as Total from book group by category";
        pstmt = con.prepareStatement(countBookSQL);

        rs = pstmt.executeQuery();
        while (rs.next()) {
            System.out.println("Book category : " + rs.getString(1));
            System.out.println("Book count : " + rs.getInt(2));
            System.out.println("*******************************************888");

        }
        pstmt.close();
        con.close();
        rs.close();
    }

}
