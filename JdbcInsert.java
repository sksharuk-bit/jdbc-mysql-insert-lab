import java.sql.*;

public class JdbcInsert {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/mydatabase";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";

    public static void main(String[] args) {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS users ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "name VARCHAR(100), "
                + "email VARCHAR(100))";

        String insertSQL = "INSERT INTO users (name, email) VALUES (?, ?)";

        try (
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            PreparedStatement createStmt = connection.prepareStatement(createTableSQL)
        ) {
            createStmt.execute();
            
            try (PreparedStatement insertStmt = connection.prepareStatement(insertSQL)) {
                insertStmt.setString(1, "John Doe");
                insertStmt.setString(2, "johndoe@example.com");
                insertStmt.executeUpdate();

                insertStmt.setString(1, "Jane Doe");
                insertStmt.setString(2, "janedoe@example.com");
                insertStmt.executeUpdate();
            }

            System.out.println("Data inserted successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
