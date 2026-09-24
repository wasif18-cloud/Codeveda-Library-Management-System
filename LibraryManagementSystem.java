import java.util.Scanner;

public class LibraryManagementSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BookDAO bookDAO = new BookDAO();
        UserDAO userDAO = new UserDAO();
        TransactionDAO transactionDAO =
                new TransactionDAO();

        int choice;

        do {

            System.out.println();
            System.out.println(
                    "======================================"
            );

            System.out.println(
                    "       LIBRARY MANAGEMENT SYSTEM"
            );

            System.out.println(
                    "======================================"
            );

            System.out.println();

            // BOOK MENU
            System.out.println("----- BOOK MANAGEMENT -----");

            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Update Book");
            System.out.println("4. Delete Book");

            System.out.println();

            // USER MENU
            System.out.println("----- USER MANAGEMENT -----");

            System.out.println("5. Add User");
            System.out.println("6. View Users");
            System.out.println("7. Update User");
            System.out.println("8. Delete User");

            System.out.println();

            // TRANSACTION MENU
            System.out.println("----- TRANSACTIONS -----");

            System.out.println("9. Borrow Book");
            System.out.println("10. Return Book");
            System.out.println("11. View Transactions");

            System.out.println();

            System.out.println("0. Exit");

            System.out.println();

            System.out.print(
                    "Enter your choice: "
            );

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
               
                // ADD BOOK
              
                case 1:

                    System.out.print(
                            "Enter book title: "
                    );

                    String title =
                            sc.nextLine();

                    System.out.print(
                            "Enter author: "
                    );

                    String author =
                            sc.nextLine();

                    System.out.print(
                            "Enter category: "
                    );

                    String category =
                            sc.nextLine();

                    System.out.print(
                            "Enter quantity: "
                    );

                    int quantity =
                            sc.nextInt();

                    if (quantity < 0) {

                        System.out.println(
                                "Quantity cannot be negative."
                        );

                        break;
                    }

                    bookDAO.addBook(
                            title,
                            author,
                            category,
                            quantity
                    );

                    break;
               
                // VIEW BOOKS
               
                case 2:

                    bookDAO.viewBooks();

                    break;
               
                // UPDATE BOOK
               
                case 3:

                    bookDAO.updateBook();

                    break;
               
                // DELETE BOOK
                
                case 4:

                    bookDAO.deleteBook();

                    break;
                
                // ADD USER
               
                case 5:

                    System.out.print(
                            "Enter user name: "
                    );

                    String userName =
                            sc.nextLine();

                    System.out.print(
                            "Enter email: "
                    );

                    String email =
                            sc.nextLine();

                    System.out.print(
                            "Enter phone: "
                    );

                    String phone =
                            sc.nextLine();

                    userDAO.addUser(
                            userName,
                            email,
                            phone
                    );

                    break;
                
                // VIEW USERS
               
                case 6:

                    userDAO.viewUsers();

                    break;

                // UPDATE USER
               
                case 7:

                    userDAO.updateUser();

                    break;
               
                // DELETE USER
               
                case 8:

                    userDAO.deleteUser();

                    break;
                
                // BORROW BOOK
               
                case 9:

                    transactionDAO.borrowBook();

                    break;
               
                // RETURN BOOK
               
                case 10:

                    transactionDAO.returnBook();

                    break;
               
                // VIEW TRANSACTIONS
              
                case 11:

                    transactionDAO.viewTransactions();

                    break;
              
                // EXIT
               
                case 0:

                    System.out.println();

                    System.out.println(
                            "Thank you for using "
                            + "Library Management System!"
                    );

                    break;
              
                // INVALID

                default:

                    System.out.println(
                            "Invalid choice!"
                    );
            }

        } while (choice != 0);

        sc.close();
    }
}