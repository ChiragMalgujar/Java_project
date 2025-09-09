import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestDB {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/student"; // your database
        String user = "root";  // MySQL username
        String password = "root"; // MySQL password

        try {
            // Connect to database
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Connected to database!");

            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM student"; // your table
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int studentClass = rs.getInt("class"); // 👈 class as integer

                System.out.println(id + " | " + name + " | " + studentClass);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
