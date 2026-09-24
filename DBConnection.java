import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/LibraryDB";

    private static final String USER = "root";
    private static final String PASSWORD = "Wasif";

    public static Connection getConnection() {

        Connection con = null;

        try {
            con = DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD
            );

            System.out.println("Database Connected!");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return con;
    }
}