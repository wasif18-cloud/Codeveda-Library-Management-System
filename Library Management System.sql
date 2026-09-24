CREATE DATABASE LibraryDB;
USE LibraryDB;

CREATE TABLE Books (
    book_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    author VARCHAR(100),
    category VARCHAR(50),
    quantity INT NOT NULL,
    available INT NOT NULL
);

CREATE TABLE Users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    phone VARCHAR(20)
);

CREATE TABLE Transactions (
    transaction_id INT PRIMARY KEY AUTO_INCREMENT,
    book_id INT,
    user_id INT,
    borrow_date DATE,
    return_date DATE,
    status VARCHAR(20),

    FOREIGN KEY (book_id) REFERENCES Books(book_id),
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);


USE LibraryDB;

SHOW TABLES;

DESCRIBE Books;

DESCRIBE Users;

DESCRIBE Transactions;

INSERT INTO Books
(title, author, category, quantity, available)
VALUES
('Java Programming', 'James Gosling', 'Programming', 5, 5),
('Database Management', 'Raghu Ramakrishnan', 'Database', 3, 3);

INSERT INTO Users
(name, email, phone)
VALUES
('Wasif', 'wasif@example.com', '9876543210'),
('Rahul', 'rahul@example.com', '9876543211');