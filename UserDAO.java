import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class UserDAO {
    
    // ADD USER    
    public void addUser(String name, String email, String phone) {

        String sql =
                "INSERT INTO Users (name, email, phone) " +
                "VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);

            ps.executeUpdate();

            System.out.println(
                    "User Added Successfully!"
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  
    // VIEW USERS
    public void viewUsers() {

        String sql = "SELECT * FROM Users";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("\n========== USER LIST ==========");

            boolean found = false;

            while (rs.next()) {

                found = true;

                System.out.println(
                        "User ID : " +
                        rs.getInt("user_id")
                );

                System.out.println(
                        "Name    : " +
                        rs.getString("name")
                );

                System.out.println(
                        "Email   : " +
                        rs.getString("email")
                );

                System.out.println(
                        "Phone   : " +
                        rs.getString("phone")
                );

                System.out.println(
                        "-------------------------------"
                );
            }

            if (!found) {
                System.out.println("No users found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  
    // UPDATE USER  
    public void updateUser() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter User ID to update: ");
        int userId = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter new Name: ");
        String name = sc.nextLine();

        System.out.print("Enter new Email: ");
        String email = sc.nextLine();

        System.out.print("Enter new Phone: ");
        String phone = sc.nextLine();

        String sql =
                "UPDATE Users SET name=?, email=?, phone=? " +
                "WHERE user_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setInt(4, userId);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                System.out.println(
                        "User Updated Successfully!"
                );

            } else {

                System.out.println(
                        "User ID not found."
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        sc.close();
    }
   
    // DELETE USER    
    public void deleteUser() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter User ID to delete: ");
        int userId = sc.nextInt();

        String sql =
                "DELETE FROM Users WHERE user_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setInt(1, userId);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                System.out.println(
                        "User Deleted Successfully!"
                );

            } else {

                System.out.println(
                        "User ID not found."
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Cannot delete this user."
            );

            System.out.println(
                    "The user may have transaction records."
            );
        }
        sc.close();
    }
}