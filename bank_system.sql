CREATE TABLE Users (
    user_id SERIAL PRIMARY KEY,
    name VARCHAR(50),
    email VARCHAR(100) UNIQUE,
    phone VARCHAR(15)
);
CREATE TABLE Accounts (
    account_id SERIAL PRIMARY KEY,
    user_id INT REFERENCES Users(user_id),
    balance DECIMAL(10, 2) DEFAULT 0.00
);

CREATE TABLE Transactions (
    transaction_id SERIAL PRIMARY KEY,
    account_id INT REFERENCES Accounts(account_id),
    amount DECIMAL(10, 2),
    transaction_type VARCHAR(10), -- 'DEPOSIT' or 'WITHDRAW'
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
select * from Users;
select * from Accounts;
select * from Transactions;