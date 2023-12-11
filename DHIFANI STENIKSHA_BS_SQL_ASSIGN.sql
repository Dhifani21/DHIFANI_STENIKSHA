----------------------- SQL ASSIGNMENT - BANKING SYSTEM -----------------------------

----------------------- TASKS 1 ---------------------

----------------------- 1 ------------------------

CREATE DATABASE HMBank;
USE HMBank;

----------------------- 2,5,6 --------------------

CREATE TABLE Customers (
  customer_id INT PRIMARY KEY,
  first_name VARCHAR(50),
  last_name VARCHAR(50),
  DOB DATE,
  email VARCHAR(100),
  phone_number VARCHAR(15),
  address VARCHAR(255)
);

CREATE TABLE Accounts (
  account_id INT PRIMARY KEY,
  customer_id INT,
  account_type VARCHAR(50),
  balance DECIMAL(10, 2),
  FOREIGN KEY (customer_id) REFERENCES Customers(customer_id)
);

CREATE TABLE Transactions (
  transaction_id INT PRIMARY KEY,
  account_id INT,
  transaction_type VARCHAR(50),
  amount DECIMAL(10, 2),
  transaction_date DATE,
  FOREIGN KEY (account_id) REFERENCES Accounts(account_id)
);

----------------------- TASKS 2 ------------------------

----------------------- 1 ------------------------

INSERT INTO Customers (customer_id, first_name, last_name, DOB, email, phone_number, address)
VALUES 
(1, 'Liji', 'Lawrence', '2001-01-01', 'lijilaw@gmail.com', '9134567890', '123 Main St'),
(2, 'Jany', 'Dim', '2002-02-02', 'janydim@gmail.com', '9887654321', '456 Maple Ave'),
(3, 'Agnel', 'James', '1990-03-03', 'agneljames@gmail.com', '9862223333', '789 Oak Dr'),
(4, 'Bergi', 'Johnson', '1995-04-04', 'bergi@gmail.com', '9823334444', '321 Pine Ln'),
(5, 'Reshma', 'Gigi', '2000-05-05', 'reshgigi@gmail.com', '9734445555', '654 Willow Rd'),
(6, 'David', 'Davis', '1999-06-06', 'david.davis@gmail.com', '9345556666', '987 Cedar St'),
(7, 'Evangeline', 'Evans', '2001-07-07', 'evanevans@gmail.com', '9056667777', '345 Birch Ave'),
(8, 'Frank', 'Franklin', '1998-08-08', 'frankfranklin@gmail.com', '9667778888', '678 Spruce Dr'),
(9, 'Grace', 'Griffin', '1997-09-09', 'gracegriffin@gmail.com', '9778889999', '912 Elm Ln'),
(10, 'Harsi', 'Harrison', '2002-10-10', 'harryharrison@gmail.com', '9889990000', '234 Poplar Rd');

INSERT INTO Accounts (account_id, customer_id, account_type, balance)
VALUES 
(11, 1, 'Current', 1200.00),
(21, 2, 'Savings', 5000.00),
(31, 3, 'Savings', 31000.00),
(41, 4, 'Savings', 21000.00),
(51, 5, 'Current', 50000.00),
(61, 6, 'Savings', 65000.00),
(71, 7, 'Savings', 6000.00),
(81, 8, 'Savings', 18000.00),
(91, 9, 'Current', 9000.00),
(12, 10, 'Savings', 70000.00);

INSERT INTO Transactions (transaction_id, account_id, transaction_type, amount, transaction_date)
VALUES 
(101, 11, 'Deposit', 500.00, '2023-10-05'),
(201, 21, 'Deposit', 2000.00, '2023-06-02'),
(301, 31, 'Deposit', 3000.00, '2023-07-03'),
(401, 41, 'Withdrawal', 4000.00, '2023-07-11'),
(501, 51, 'Deposit', 10000.00, '2023-07-05'),
(601, 61, 'Deposit', 6000.00, '2023-08-02'),
(701, 71, 'Deposit', 5000.00, '2023-08-15'),
(801, 81, 'Withdrawal', 8000.00, '2023-09-20'),
(901, 91, 'Deposit', 5000.00, '2023-10-23'),
(111, 12, 'Withdrawal', 30000.00, '2023-11-14');

SELECT * FROM Accounts;
SELECT * FROM Customers;

----------------------- 2 . 1 -------------

SELECT first_name, last_name, 
    (SELECT account_type FROM Accounts WHERE Customers.customer_id = Accounts.customer_id) AS account_type, email FROM Customers;
  
----------------------- 2 . 2 -------------
  
SELECT transaction_id, transaction_type, amount, transaction_date, 
    (SELECT first_name FROM Customers WHERE customer_id = (SELECT customer_id FROM Accounts WHERE account_id = Transactions.account_id)) AS first_name, 
    (SELECT last_name FROM Customers WHERE customer_id = (SELECT customer_id FROM Accounts WHERE account_id = Transactions.account_id)) AS last_name
FROM Transactions;

----------------------- 2 . 3 -------------

UPDATE Accounts
SET balance = balance + 12000
WHERE account_id = 31;

----------------------- 2 . 4 -------------

SELECT CONCAT(first_name, ' ', last_name) AS full_name
FROM Customers; 

----------------------- 2 . 5 -------------

DELETE FROM Accounts
WHERE balance = 0 AND account_type = 'Savings'; 

----------------------- 2 . 6 --------------

SELECT * FROM Customers WHERE address = '654 Willow Rd'; 

----------------------- 2 . 7 ---------------

SELECT account_id,balance FROM Accounts WHERE account_id = 51; 

----------------------- 2 . 8 ---------------

SELECT * FROM Accounts WHERE account_type = 'Current' AND balance > 1000;

----------------------- 2 . 9 ----------------

SELECT * FROM Transactions WHERE account_id = 91; 

----------------------- 2 . 10 ----------------

SELECT account_id, balance, balance * 0.02 AS interest_accrued FROM Accounts
WHERE account_type = 'Savings'; 

----------------------- 2 . 11 ----------------

SELECT * FROM Accounts
WHERE balance < 5000; 

----------------------- 2 . 12 -----------------

SELECT * FROM Customers
WHERE address NOT LIKE '987 Cedar St'; 

-------------------- TASKS 3 -----------------

----------------------- 1 -----------------

SELECT AVG(balance) AS average_balance
FROM Accounts; 

----------------------- 2 ------------------

SELECT account_id, balance FROM Accounts
ORDER BY balance DESC LIMIT 10; 

----------------------- 3 -------------------

SELECT SUM(amount) AS total_deposits FROM Transactions
WHERE transaction_type = 'Deposit' AND DATE(transaction_date) = '2023-08-15'; 

----------------------- 4 --------------------

ALTER TABLE Customers ADD COLUMN join_date DATE;

UPDATE Customers SET join_date = '2022-04-12'
WHERE customer_id = 1;

UPDATE Customers SET join_date = '2019-03-10'
WHERE customer_id = 2;

UPDATE Customers SET join_date = '2021-01-21'
WHERE customer_id = 3;

UPDATE Customers SET join_date = '2014-09-13'
WHERE customer_id = 4;

UPDATE Customers SET join_date = '2022-10-02'
WHERE customer_id = 5;

UPDATE Customers SET join_date = '2022-03-03'
WHERE customer_id = 6;

UPDATE Customers SET join_date = '2022-11-01'
WHERE customer_id = 7;

UPDATE Customers SET join_date = '2023-06-23'
WHERE customer_id = 8;

UPDATE Customers SET join_date = '2023-01-16'
WHERE customer_id = 9;

UPDATE Customers SET join_date = '2021-06-19'
WHERE customer_id = 10;

SELECT Customer_id,first_name,last_name,join_date  FROM Customers
ORDER BY join_date ASC
LIMIT 1;

SELECT Customer_id, first_name,last_name,join_date FROM Customers
ORDER BY join_date DESC
LIMIT 1;

----------------------- 5 ------------------

SELECT Transactions.transaction_id, Transactions.transaction_type, Transactions.amount, Transactions.transaction_date, Accounts.account_type
FROM Transactions
INNER JOIN Accounts ON Transactions.account_id = Accounts.account_id;

----------------------- 6 ------------------

SELECT Customers.customer_id, Customers.first_name, Customers.last_name, Accounts.account_id, Accounts.account_type, Accounts.balance
FROM Customers
INNER JOIN Accounts ON Customers.customer_id = Accounts.customer_id;

----------------------- 7 ------------------

SELECT Transactions.transaction_id, Transactions.transaction_type, Transactions.amount, Transactions.transaction_date, Customers.customer_id, Customers.first_name, Customers.last_name
FROM Transactions
INNER JOIN Accounts ON Transactions.account_id = Accounts.account_id
INNER JOIN Customers ON Accounts.customer_id = Customers.customer_id
WHERE Accounts.account_id = 41; 

----------------------- 8 -----------------

INSERT INTO Accounts (account_id, customer_id, account_type, balance) VALUES
(32, 1, 'Savings', 6000.00),
(42, 1, 'Current', 2100.00);
SELECT Customers.customer_id, Customers.first_name, Customers.last_name, COUNT(Accounts.account_id) AS NoOfAccounts
FROM Customers
INNER JOIN Accounts ON Customers.customer_id = Accounts.customer_id
GROUP BY Customers.customer_id, Customers.first_name, Customers.last_name
HAVING COUNT(Accounts.account_id) > 1; 

----------------------- 9 -----------------

SELECT 'Deposit' AS transaction_type, SUM(amount) AS TotalAmount
FROM Transactions
WHERE transaction_type = 'Deposit'
UNION ALL
SELECT 'Withdrawal' AS transaction_type, SUM(amount) AS TotalAmount
FROM Transactions
WHERE transaction_type = 'Withdrawal'
UNION ALL
SELECT 'Difference' AS transaction_type, 
    ((SELECT SUM(amount) FROM Transactions WHERE transaction_type = 'Deposit') -
    (SELECT SUM(amount) FROM Transactions WHERE transaction_type = 'Withdrawal')) AS TotalAmount;
    
----------------------- 10 -----------------

SELECT account_id, transaction_date, AVG(amount) AS AvgBal FROM Transactions
WHERE transaction_date BETWEEN '2023-06-01' AND '2023-08-30'
GROUP BY account_id, transaction_date; 

----------------------- 11 ------------------

SELECT account_type, SUM(balance) AS TotalBalance FROM Accounts
GROUP BY account_type;

----------------------- 12 ------------------- 

SELECT account_id, COUNT(*) AS NoOfTransactions FROM Transactions
GROUP BY account_id
ORDER BY NoOfTransactions DESC; 

----------------------- 13 -------------------

SELECT Customers.customer_id, Customers.first_name, Customers.last_name, Accounts.account_type, SUM(Accounts.balance) AS TotalBalance
FROM Customers
INNER JOIN Accounts ON Customers.customer_id = Accounts.customer_id
GROUP BY Customers.customer_id, Accounts.account_type
ORDER BY TotalBalance DESC; 

----------------------- 14 -------------------- 

SELECT account_id, transaction_date, amount, COUNT(*) AS NoOfDuplicates FROM Transactions
GROUP BY account_id, transaction_date, amount
HAVING COUNT(*) > 1; 

----------------------- TASKS 4 ----------------

-------------------------- 1 -------------------

SELECT Customers.customer_id, Customers.first_name, Customers.last_name, Accounts.balance AS HighestBalance FROM Customers
INNER JOIN Accounts ON Customers.customer_id = Accounts.customer_id
WHERE Accounts.balance = (SELECT MAX(balance) FROM Accounts); 

-------------------------- 2 -------------------

SELECT customer_id, COUNT(account_id) AS NoOfAccounts, AVG(balance) AS AvgBal FROM Accounts
WHERE customer_id IN (
  SELECT customer_id FROM Accounts
  GROUP BY customer_id
  HAVING COUNT(account_id) > 1
)
GROUP BY customer_id;

-------------------------- 3 --------------------

SELECT account_id FROM Transactions
WHERE amount > ( SELECT AVG(amount) FROM Transactions ); 

-------------------------- 4 --------------------

SELECT Customers.customer_id,Customers.first_name, Customers.last_name FROM Customers
LEFT JOIN Accounts ON Customers.customer_id = Accounts.customer_id
LEFT JOIN Transactions ON Accounts.account_id = Transactions.account_id
WHERE Transactions.transaction_id IS NULL; 

-------------------------- 5 ---------------------

SELECT Accounts.account_id, Accounts.balance, TotalBalance.total FROM Accounts
LEFT JOIN Transactions ON Accounts.account_id = Transactions.account_id
CROSS JOIN (
  SELECT SUM(balance) AS total
  FROM Accounts
  LEFT JOIN Transactions ON Accounts.account_id = Transactions.account_id
  WHERE Transactions.transaction_id IS NULL
) AS TotalBalance WHERE Transactions.transaction_id IS NULL; 

-------------------------- 6 ----------------------

SELECT Transactions.* FROM Transactions
WHERE account_id IN (
  SELECT account_id FROM Accounts
  WHERE balance = ( SELECT MIN(balance) FROM Accounts)
); 

-------------------------- 7 -----------------------

SELECT account_id ,customer_id, account_type FROM Accounts
GROUP BY customer_id,account_id
HAVING COUNT(DISTINCT account_type) > 1; 

-------------------------- 8 -----------------------

SELECT account_type, COUNT(*) / (SELECT COUNT(*) FROM Accounts) * 100 AS percentage FROM Accounts
GROUP BY account_type; 

-------------------------- 9 ------------------------

SELECT Transactions.* FROM Transactions
JOIN Accounts ON Transactions.account_id = Accounts.account_id
WHERE Accounts.customer_id = 3; 

-------------------------- 10 ------------------------

SELECT account_type, 
       (SELECT SUM(balance) FROM Accounts AS SubAccounts WHERE SubAccounts.account_type = MainAccounts.account_type) AS TotalBalance
FROM Accounts AS MainAccounts
GROUP BY account_type;