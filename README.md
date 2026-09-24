# 📚 Library Management System with JDBC

## 📌 Project Overview

The **Library Management System with JDBC** is a console-based Java application developed as part of **Codveda Technologies – Level 3 (Advanced), Task 1**.

The project is designed to manage library activities such as adding books, managing users, borrowing books, and returning books. It uses **Java Database Connectivity (JDBC)** to connect the Java application with a **MySQL database**.

The system reduces manual record keeping and helps maintain organized information about books, library users, and borrowing transactions.

## 🎯 Objectives

- Set up a MySQL database for the library system.
- Create database tables for books, users, and transactions.
- Implement CRUD operations using JDBC.
- Add, view, update, and delete book records.
- Add, view, update, and delete user records.
- Handle book borrowing and returning transactions.
- Update book availability after borrowing or returning.
- Practice Java object-oriented programming and database connectivity.

## ✨ Features

### 📖 Book Management

- Add new books.
- View all available books and their details.
- Update book information.
- Delete book records.
- Track total quantity and available quantity.

### 👤 User Management

- Add library users.
- View registered users.
- Update user information.
- Delete users when appropriate.

### 🔄 Transaction Management

- Borrow books by selecting a book and user.
- Check book availability before borrowing.
- Record borrowing date and transaction status.
- Return borrowed books.
- Update book availability after a return.
- View borrowing and returning history.

### 🗄️ Database Connectivity

- Connect Java with MySQL using JDBC.
- Use SQL queries through `PreparedStatement`.
- Maintain relationships using foreign keys.
- Use transactions and rollback handling for borrowing and returning operations.

## 🛠️ Technologies Used

| Technology | Purpose |
|---|---|
| Java | Application development |
| JDBC | Java–database connectivity |
| MySQL | Database management |
| MySQL Workbench | Database creation and SQL execution |
| Visual Studio Code | Development environment |
| Git and GitHub | Version control and project hosting |

## 🏗️ System Architecture

The application follows a simple layered structure:

```text
User
  |
  v
LibraryManagementSystem
  |
  v
DAO Classes
  |
  +----> BookDAO
  |
  +----> UserDAO
  |
  +----> TransactionDAO
  |
  v
DBConnection
  |
  v
MySQL Database (LibraryDB)
```

## 🗂️ Project Structure

```text
Library Management System with JDBC/
│
├── DBConnection.java
├── BookDAO.java
├── UserDAO.java
├── TransactionDAO.java
├── LibraryManagementSystem.java
├── mysql-connector-j-<version>.jar
└── README.md
```

## 🗃️ Database Design

The project uses a MySQL database named:

```sql
LibraryDB
```

The database contains three main tables:

### 1. Books Table

Stores information about books in the library.

| Column | Description |
|---|---|
| `book_id` | Primary key and auto-increment ID |
| `title` | Book title |
| `author` | Book author |
| `category` | Book category |
| `quantity` | Total number of copies |
| `available` | Number of copies currently available |

### 2. Users Table

Stores information about library users.

| Column | Description |
|---|---|
| `user_id` | Primary key and auto-increment ID |
| `name` | User name |
| `email` | User email |
| `phone` | User phone number |

### 3. Transactions Table

Stores book borrowing and returning records.

| Column | Description |
|---|---|
| `transaction_id` | Primary key and auto-increment ID |
| `book_id` | Foreign key referencing `Books` |
| `user_id` | Foreign key referencing `Users` |
| `borrow_date` | Date on which the book was borrowed |
| `return_date` | Date on which the book was returned |
| `status` | Transaction status, such as `BORROWED` or `RETURNED` |

## 🔗 Database Relationships

```text
Books (book_id)
      |
      | 1-to-many
      v
Transactions (book_id)

Users (user_id)
      |
      | 1-to-many
      v
Transactions (user_id)
```

A book can appear in multiple transaction records over time, and a user can have multiple borrowing records.

## ⚙️ Main Java Classes

### `DBConnection.java`

This class establishes a connection between Java and the MySQL database using `DriverManager`.

Main responsibility:

- Store database URL, username, and password.
- Create and return a database connection.
- Handle connection errors.

### `BookDAO.java`

This class manages book-related database operations.

Main operations:

- `addBook()`
- `viewBooks()`
- `updateBook()`
- `deleteBook()`

### `UserDAO.java`

This class manages user-related database operations.

Main operations:

- `addUser()`
- `viewUsers()`
- `updateUser()`
- `deleteUser()`

### `TransactionDAO.java`

This class manages borrowing and returning operations.

Main operations:

- `borrowBook()`
- `returnBook()`
- `viewTransactions()`

### `LibraryManagementSystem.java`

This is the main class of the application. It displays the menu and allows users to select different library operations.

## 🖥️ Application Menu

```text
===== LIBRARY MANAGEMENT SYSTEM =====

1. Add Book
2. View Books
3. Update Book
4. Delete Book
5. Add User
6. View Users
7. Update User
8. Delete User
9. Borrow Book
10. Return Book
11. View Transactions
0. Exit

Enter your choice:
```

## 🔄 Application Workflow

```text
Start
  |
  v
Connect to MySQL Database
  |
  v
Display Main Menu
  |
  +----> Book Management
  |          |
  |          +----> Add
  |          +----> View
  |          +----> Update
  |          +----> Delete
  |
  +----> User Management
  |          |
  |          +----> Add
  |          +----> View
  |          +----> Update
  |          +----> Delete
  |
  +----> Transaction Management
             |
             +----> Borrow Book
             +----> Return Book
             +----> View Transactions
  |
  v
Exit
```

## 🧰 Database Setup

Open MySQL Workbench and execute the following SQL:

```sql
CREATE DATABASE IF NOT EXISTS LibraryDB;

USE LibraryDB;

CREATE TABLE IF NOT EXISTS Books (
    book_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    author VARCHAR(100),
    category VARCHAR(50),
    quantity INT NOT NULL,
    available INT NOT NULL
);

CREATE TABLE IF NOT EXISTS Users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    phone VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS Transactions (
    transaction_id INT PRIMARY KEY AUTO_INCREMENT,
    book_id INT,
    user_id INT,
    borrow_date DATE,
    return_date DATE,
    status VARCHAR(20),

    FOREIGN KEY (book_id) REFERENCES Books(book_id),
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);
```

## 🔌 JDBC Driver Setup

Download the MySQL Connector/J driver from the official MySQL website:

https://dev.mysql.com/downloads/connector/j/

Place the downloaded JAR file inside the project folder.

Example:

```text
mysql-connector-j-9.4.0.jar
```

Use the actual filename of the JAR downloaded to your computer.

## ▶️ How to Run the Project

### 1. Check Java Installation

Open PowerShell or Command Prompt and run:

```powershell
java -version
javac -version
```

### 2. Open the Project Folder

Navigate to the folder containing the Java source files:

```powershell
cd "C:\Path\To\Library Management System with JDBC"
```

Replace the path with the actual location of your project.

### 3. Compile the Java Files

If the Connector/J file is named `mysql-connector-j-9.4.0.jar`, run:

```powershell
javac -cp ".;mysql-connector-j-9.4.0.jar" *.java
```

Replace the JAR filename if your downloaded version is different.

### 4. Run the Main Application

```powershell
java -cp ".;mysql-connector-j-9.4.0.jar" LibraryManagementSystem
```

Run `LibraryManagementSystem`, not the DAO classes, because the main menu is located in the `LibraryManagementSystem` class.

## 🧪 Testing Checklist

- [ ] Database connection works successfully.
- [ ] A new book can be added.
- [ ] Books can be displayed.
- [ ] Book details can be updated.
- [ ] Books can be deleted when there are no related transaction restrictions.
- [ ] A new user can be added.
- [ ] Users can be displayed.
- [ ] User details can be updated.
- [ ] Users can be deleted when appropriate.
- [ ] A book can be borrowed when copies are available.
- [ ] Borrowing is prevented when no copy is available.
- [ ] A borrowed book can be returned.
- [ ] Available quantity is updated after borrowing and returning.
- [ ] Transactions are displayed correctly.

## ⚠️ Error Handling

The application should handle the following situations:

- Database connection failure.
- Invalid book or user ID.
- Book not found.
- User not found.
- Book not available.
- Invalid quantity values.
- Attempting to return an already returned book.
- SQL exceptions.
- Transaction rollback when a borrowing or returning operation fails.

## 🔐 Security and Good Practices

- Do not upload database passwords to public GitHub repositories.
- Use `PreparedStatement` for SQL queries.
- Close database connections, statements, and result sets properly.
- Validate user input before executing database operations.
- Use database transactions for operations that update multiple records.
- Keep database credentials in environment variables or a configuration file for production projects.

## 📚 Learning Outcomes

After completing this project, the following concepts can be practiced:

- Java classes and objects.
- Data Access Object (DAO) pattern.
- JDBC database connectivity.
- SQL `INSERT`, `SELECT`, `UPDATE`, and `DELETE` queries.
- `PreparedStatement` and `ResultSet`.
- MySQL database and foreign keys.
- Exception handling.
- Commit and rollback operations.
- Console-based menu development.
- Basic library management system design.

## 🚀 Future Enhancements

The system can be improved by adding:

- Login and authentication for administrators.
- Search books by title, author, or category.
- Due dates and late return fine calculation.
- Book reservation functionality.
- Dashboard and reports.
- GUI using Java Swing or JavaFX.
- Password encryption and secure configuration.
- Exporting transaction reports to CSV or PDF.
- Automated unit and integration testing.

## 📌 Project Information

| Field | Details |
|---|---|
| Project | Library Management System with JDBC |
| Level | Level 3 – Advanced |
| Task | Task 1 |
| Programming Language | Java |
| Database | MySQL |
| Connectivity | JDBC |
| Application Type | Console-Based |
| Main Operations | Book Management, User Management, Borrowing, Returning |
