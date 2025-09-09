import java.sql.*;
import java.util.Scanner;

public class Book {
    private Scanner scanner = new Scanner(System.in);

    // Show all books
    public void displayBooks() {
        String url = "jdbc:mysql://localhost:3306/library";
        String user = "root";
        String password = "root";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {

            String query = "SELECT * FROM book";
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("Name | Issue | Back");
            System.out.println("-----------------------");

            while (rs.next()) {
                String name = rs.getString("name");
                String issue = rs.getString("issue");
                String back = rs.getString("back");
                System.out.println(name + " | " + issue + " | " + back);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Add a new book
    public void addbook() {
        System.out.println("Enter Book Name: ");
        String name = scanner.nextLine();
        String issue = "not issued";
        String back = "yes";

        String url = "jdbc:mysql://localhost:3306/library";
        String user = "root";
        String password = "root";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {

            String query = "INSERT INTO book (name, issue, back) VALUES ('" + name + "', '" + issue + "', '" + back + "')";
            stmt.executeUpdate(query);
            System.out.println("Book added successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Issue a book
    public void issuebook() {
        System.out.println("Enter Book Name to Issue: ");
        String name = scanner.nextLine();

        String url = "jdbc:mysql://localhost:3306/library";
        String user = "root";
        String password = "root";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {

            String query = "SELECT * FROM book WHERE name='" + name + "'";
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                String issue = rs.getString("issue");
                if (issue.equals("issued")) {
                    System.out.println("Book is already issued.");
                } else {
                    String query2 = "UPDATE book SET issue='issued', back='no' WHERE name='" + name + "'";
                    stmt.executeUpdate(query2);
                    System.out.println("Book issued successfully!");
                }
            } else {
                System.out.println("Book not found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Return a book
    public void returnbook() {
        System.out.println("Enter Book Name to Return: ");
        String name = scanner.nextLine();

        String url = "jdbc:mysql://localhost:3306/library";
        String user = "root";
        String password = "root";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {

            String query = "SELECT * FROM book WHERE name='" + name + "'";
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                String issue = rs.getString("issue");
                if (issue.equals("issued")) {
                    String query2 = "UPDATE book SET issue='not issued', back='yes' WHERE name='" + name + "'";
                    stmt.executeUpdate(query2);
                    System.out.println("Book returned successfully!");
                } else {
                    System.out.println("This book was not issued.");
                }
            } else {
                System.out.println("Book not found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
