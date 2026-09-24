import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class BookDAO {
    
    // ADD BOOK
    public void addBook(String title, String author,
                        String category, int quantity) {

        String sql = "INSERT INTO Books " +
                     "(title, author, category, quantity, available) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, title);
            ps.setString(2, author);
            ps.setString(3, category);
            ps.setInt(4, quantity);
            ps.setInt(5, quantity);

            ps.executeUpdate();

            System.out.println("Book Added Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  
    // VIEW BOOKS
    public void viewBooks() {

        String sql = "SELECT * FROM Books";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("\n========== BOOK LIST ==========");

            boolean found = false;

            while (rs.next()) {

                found = true;

                System.out.println("Book ID   : " +
                        rs.getInt("book_id"));

                System.out.println("Title     : " +
                        rs.getString("title"));

                System.out.println("Author    : " +
                        rs.getString("author"));

                System.out.println("Category  : " +
                        rs.getString("category"));

                System.out.println("Quantity  : " +
                        rs.getInt("quantity"));

                System.out.println("Available : " +
                        rs.getInt("available"));

                System.out.println("-------------------------------");
            }

            if (!found) {
                System.out.println("No books found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
    // UPDATE BOOK
    public void updateBook() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Book ID to update: ");
        int bookId = sc.nextInt();
        sc.nextLine();

        // First check book
        String checkSql =
                "SELECT quantity, available FROM Books WHERE book_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement checkPs =
                     con.prepareStatement(checkSql)) {

            checkPs.setInt(1, bookId);

            ResultSet rs = checkPs.executeQuery();

            if (!rs.next()) {
                System.out.println("Book ID not found.");
                return;
            }

            int oldQuantity = rs.getInt("quantity");
            int oldAvailable = rs.getInt("available");

            System.out.print("Enter new Book Title: ");
            String title = sc.nextLine();

            System.out.print("Enter new Author: ");
            String author = sc.nextLine();

            System.out.print("Enter new Category: ");
            String category = sc.nextLine();

            System.out.print("Enter new Quantity: ");
            int newQuantity = sc.nextInt();

            if (newQuantity < 0) {
                System.out.println("Quantity cannot be negative.");
                return;
            }

            /*
             * Calculate how many books are currently borrowed.
             */
            int borrowed = oldQuantity - oldAvailable;

            if (newQuantity < borrowed) {
                System.out.println(
                        "New quantity cannot be less than borrowed books."
                );
                return;
            }

            int newAvailable = newQuantity - borrowed;

            String sql =
                    "UPDATE Books SET title=?, author=?, " +
                    "category=?, quantity=?, available=? " +
                    "WHERE book_id=?";

            try (PreparedStatement ps =
                         con.prepareStatement(sql)) {

                ps.setString(1, title);
                ps.setString(2, author);
                ps.setString(3, category);
                ps.setInt(4, newQuantity);
                ps.setInt(5, newAvailable);
                ps.setInt(6, bookId);

                int rows = ps.executeUpdate();

                if (rows > 0) {
                    System.out.println(
                            "Book Updated Successfully!"
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        sc.close();
    }
  
    // DELETE BOOK
    public void deleteBook() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Book ID to delete: ");
        int bookId = sc.nextInt();

        String sql = "DELETE FROM Books WHERE book_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setInt(1, bookId);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                System.out.println(
                        "Book Deleted Successfully!"
                );

            } else {

                System.out.println(
                        "Book ID not found."
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Cannot delete this book."
            );

            System.out.println(
                    "It may be used in a transaction."
            );
        }
        sc.close();
    }
}