import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Scanner;

public class TransactionDAO {
    
    // BORROW BOOK
    public void borrowBook() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Book ID: ");
        int bookId = sc.nextInt();

        System.out.print("Enter User ID: ");
        int userId = sc.nextInt();

        Connection con = null;

        try {

            con = DBConnection.getConnection();

            // Start transaction
            con.setAutoCommit(false);
          
            // Check Book
            String bookSql =
                    "SELECT quantity, available " +
                    "FROM Books WHERE book_id=?";

            PreparedStatement bookPs =
                    con.prepareStatement(bookSql);

            bookPs.setInt(1, bookId);

            ResultSet bookRs =
                    bookPs.executeQuery();

            if (!bookRs.next()) {

                System.out.println(
                        "Book ID not found."
                );

                con.rollback();
                return;
            }

            int available =
                    bookRs.getInt("available");


            if (available <= 0) {

                System.out.println(
                        "Book is currently not available."
                );

                con.rollback();
                return;
            }
        
            // Check User
            String userSql =
                    "SELECT user_id FROM Users WHERE user_id=?";

            PreparedStatement userPs =
                    con.prepareStatement(userSql);

            userPs.setInt(1, userId);

            ResultSet userRs =
                    userPs.executeQuery();

            if (!userRs.next()) {

                System.out.println(
                        "User ID not found."
                );

                con.rollback();
                return;
            }
          
            // Insert Transaction
            String transactionSql =
                    "INSERT INTO Transactions " +
                    "(book_id, user_id, borrow_date, status) " +
                    "VALUES (?, ?, ?, ?)";

            PreparedStatement transactionPs =
                    con.prepareStatement(transactionSql);

            transactionPs.setInt(1, bookId);
            transactionPs.setInt(2, userId);

            transactionPs.setDate(
                    3,
                    Date.valueOf(LocalDate.now())
            );

            transactionPs.setString(
                    4,
                    "BORROWED"
            );

            transactionPs.executeUpdate();
           
            // Decrease Available Books
            String updateBookSql =
                    "UPDATE Books " +
                    "SET available = available - 1 " +
                    "WHERE book_id=?";

            PreparedStatement updatePs =
                    con.prepareStatement(updateBookSql);

            updatePs.setInt(1, bookId);

            updatePs.executeUpdate();

            // Commit everything
            con.commit();

            System.out.println(
                    "Book Borrowed Successfully!"
            );

        } catch (Exception e) {

            try {

                if (con != null) {
                    con.rollback();
                }

            } catch (Exception rollbackError) {
                rollbackError.printStackTrace();
            }

            e.printStackTrace();

        } finally {

            try {

                if (con != null) {
                    con.setAutoCommit(true);
                    con.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
         sc.close();
    }   

       // RETURN BOOK
       public void returnBook() {

        Scanner sc = new Scanner(System.in);

        System.out.print(
                "Enter Transaction ID: "
        );

        int transactionId = sc.nextInt();

        Connection con = null;

        try {

            con = DBConnection.getConnection();

            con.setAutoCommit(false);
           
            // Find active transaction
            String findSql =
                    "SELECT book_id, status " +
                    "FROM Transactions " +
                    "WHERE transaction_id=?";

            PreparedStatement findPs =
                    con.prepareStatement(findSql);

            findPs.setInt(1, transactionId);

            ResultSet rs =
                    findPs.executeQuery();

            if (!rs.next()) {

                System.out.println(
                        "Transaction ID not found."
                );

                con.rollback();
                return;
            }

            int bookId =
                    rs.getInt("book_id");

            String status =
                    rs.getString("status");

            if (!status.equals("BORROWED")) {

                System.out.println(
                        "This book has already been returned."
                );

                con.rollback();
                return;
            }
           
            // Update Transaction
            String updateTransactionSql =
                    "UPDATE Transactions " +
                    "SET return_date=?, status=? " +
                    "WHERE transaction_id=?";

            PreparedStatement transactionPs =
                    con.prepareStatement(
                            updateTransactionSql
                    );

            transactionPs.setDate(
                    1,
                    Date.valueOf(LocalDate.now())
            );

            transactionPs.setString(
                    2,
                    "RETURNED"
            );

            transactionPs.setInt(
                    3,
                    transactionId
            );

            transactionPs.executeUpdate();
           
            // Increase Available Books
            String updateBookSql =
                    "UPDATE Books " +
                    "SET available = available + 1 " +
                    "WHERE book_id=?";

            PreparedStatement bookPs =
                    con.prepareStatement(updateBookSql);

            bookPs.setInt(1, bookId);

            bookPs.executeUpdate();

            // Commit
            con.commit();

            System.out.println(
                    "Book Returned Successfully!"
            );

        } catch (Exception e) {

            try {

                if (con != null) {
                    con.rollback();
                }

            } catch (Exception rollbackError) {
                rollbackError.printStackTrace();
            }

            e.printStackTrace();

        } finally {

            try {

                if (con != null) {
                    con.setAutoCommit(true);
                    con.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
         sc.close();
    }
    
    // VIEW TRANSACTIONS
    public void viewTransactions() {

        String sql =
                "SELECT t.transaction_id, " +
                "b.title, " +
                "u.name, " +
                "t.borrow_date, " +
                "t.return_date, " +
                "t.status " +
                "FROM Transactions t " +
                "JOIN Books b ON t.book_id = b.book_id " +
                "JOIN Users u ON t.user_id = u.user_id " +
                "ORDER BY t.transaction_id DESC";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println(
                    "\n========== TRANSACTION LIST =========="
            );

            boolean found = false;

            while (rs.next()) {

                found = true;

                System.out.println(
                        "Transaction ID : " +
                        rs.getInt("transaction_id")
                );

                System.out.println(
                        "Book           : " +
                        rs.getString("title")
                );

                System.out.println(
                        "User           : " +
                        rs.getString("name")
                );

                System.out.println(
                        "Borrow Date    : " +
                        rs.getDate("borrow_date")
                );

                System.out.println(
                        "Return Date    : " +
                        rs.getDate("return_date")
                );

                System.out.println(
                        "Status         : " +
                        rs.getString("status")
                );

                System.out.println(
                        "--------------------------------------"
                );
            }

            if (!found) {

                System.out.println(
                        "No transactions found."
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }       
    } 
}