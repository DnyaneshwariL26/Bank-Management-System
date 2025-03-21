package jdbc_hello;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class bank_system {

	
	    public static Connection connect() {
	        String url = "jdbc:postgresql://localhost:5432/bank_system"; // Update database URL if needed
	        String user = "postgres"; // Database username
	        String password = "admin"; // Database password

	        try {
	            Connection conn = DriverManager.getConnection(url, user, password);
	            System.out.println("Connected to PostgreSQL database!");
	            return conn;
	        } catch (SQLException e) {
	            System.out.println("Error connecting to database: " + e.getMessage());
	            return null;
	        }
	    }

	    // Method to insert user
	    public void insertUser(String name, String email, String phone) {
	        String sql = "INSERT INTO Users (name, email, phone) VALUES (?, ?, ?)";
	        try (Connection conn = connect();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, name);
	            pstmt.setString(2, email);
	            pstmt.setString(3, phone);
	            pstmt.executeUpdate();
	            System.out.println("User inserted successfully!");
	        } catch (SQLException e) {
	            System.out.println("Error inserting user: " + e.getMessage());
	        }
	    }

	    // Method to update user's phone
	    public void updateUser(int userId, String newPhone) {
	        String sql = "UPDATE Users SET phone = ? WHERE user_id = ?";
	        try (Connection conn = connect();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, newPhone);
	            pstmt.setInt(2, userId);
	            pstmt.executeUpdate();
	            System.out.println("User updated successfully!");
	        } catch (SQLException e) {
	            System.out.println("Error updating user: " + e.getMessage());
	        }
	    }

	    // Method to delete user
	    public void deleteUser(int userId) {
	        String sql = "DELETE FROM Users WHERE user_id = ?";
	        try (Connection conn = connect();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setInt(1, userId);
	            pstmt.executeUpdate();
	            System.out.println("User deleted successfully!");
	        } catch (SQLException e) {
	            System.out.println("Error deleting user: " + e.getMessage());
	        }
	    }

	    // Method to display users
	    public void displayUsers() {
	        String sql = "SELECT * FROM Users";
	        try (Connection conn = connect();
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(sql)) {
	            System.out.println("User Details:");
	            while (rs.next()) {
	                System.out.println("User ID: " + rs.getInt("user_id"));
	                System.out.println("Name: " + rs.getString("name"));
	                System.out.println("Email: " + rs.getString("email"));
	                System.out.println("Phone: " + rs.getString("phone"));
	                System.out.println("----------------------------");
	            }
	        } catch (SQLException e) {
	            System.out.println("Error fetching user details: " + e.getMessage());
	        }
	    }
	
// Insert account
public void insertAccount(int userId, double balance) {
    String sql = "INSERT INTO Accounts (user_id, balance) VALUES (?, ?)";
    try (Connection conn = connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, userId);
        pstmt.setDouble(2, balance);
        pstmt.executeUpdate();
        System.out.println("Account inserted successfully!");
    } catch (SQLException e) {
        System.out.println("Error inserting account: " + e.getMessage());
    }
}

// Update account balance
public void updateAccountBalance(int accountId, double newBalance) {
    String sql = "UPDATE Accounts SET balance = ? WHERE account_id = ?";
    try (Connection conn = connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setDouble(1, newBalance);
        pstmt.setInt(2, accountId);
        pstmt.executeUpdate();
        System.out.println("Account balance updated successfully!");
    } catch (SQLException e) {
        System.out.println("Error updating account: " + e.getMessage());
    }
}

// Delete account
public void deleteAccount(int accountId) {
    String sql = "DELETE FROM Accounts WHERE account_id = ?";
    try (Connection conn = connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, accountId);
        pstmt.executeUpdate();
        System.out.println("Account deleted successfully!");
    } catch (SQLException e) {
        System.out.println("Error deleting account: " + e.getMessage());
    }
}

// Display all accounts
public void displayAccounts() {
    String sql = "SELECT * FROM Accounts";
    try (Connection conn = connect();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
        System.out.println("Account Details:");
        while (rs.next()) {
            System.out.println("Account ID: " + rs.getInt("account_id"));
            System.out.println("User ID: " + rs.getInt("user_id"));
            System.out.println("Balance: " + rs.getDouble("balance"));
            System.out.println("----------------------------");
        }
    } catch (SQLException e) {
        System.out.println("Error fetching accounts: " + e.getMessage());
    }
}

    // Insert a new transaction
    public static void insertTransaction(int accountId, double amount, String transactionType) {
        String sql = "INSERT INTO Transactions (account_id, amount, transaction_type) VALUES (?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, accountId);
            pstmt.setDouble(2, amount);
            pstmt.setString(3, transactionType);
            pstmt.executeUpdate();
            System.out.println("Transaction added successfully!");
        } catch (SQLException e) {
            System.out.println("Error inserting transaction: " + e.getMessage());
        }
    }

    // Update a transaction amount
    public static void updateTransactionAmount(int transactionId, double newAmount) {
        String sql = "UPDATE Transactions SET amount = ? WHERE transaction_id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, newAmount);
            pstmt.setInt(2, transactionId);
            pstmt.executeUpdate();
            System.out.println("Transaction updated successfully!");
        } catch (SQLException e) {
            System.out.println("Error updating transaction: " + e.getMessage());
        }
    }

    // Delete a transaction
    public static void deleteTransaction(int transactionId) {
        String sql = "DELETE FROM Transactions WHERE transaction_id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, transactionId);
            pstmt.executeUpdate();
            System.out.println("Transaction deleted successfully!");
        } catch (SQLException e) {
            System.out.println("Error deleting transaction: " + e.getMessage());
        }
    }

    // Display all transactions
    public static void displayTransactions() {
        String sql = "SELECT * FROM Transactions";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("Transaction Details:");
            while (rs.next()) {
                System.out.println("Transaction ID: " + rs.getInt("transaction_id"));
                System.out.println("Account ID: " + rs.getInt("account_id"));
                System.out.println("Amount: " + rs.getDouble("amount"));
                System.out.println("Transaction Type: " + rs.getString("transaction_type"));
                System.out.println("Transaction Date: " + rs.getTimestamp("transaction_date"));
                System.out.println("----------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching transactions: " + e.getMessage());
        }
    }


	
    public static void main(String[] args) {
        bank_system bankSystem = new bank_system(); // Create an instance of bank_system
        Scanner scanner = new Scanner(System.in);

        int choice; // Declare `choice` outside the loop

        do {
            System.out.println("=== Bank Management System ===");
            System.out.println("1. Insert User");
            System.out.println("2. Update User Phone");
            System.out.println("3. Delete User");
            System.out.println("4. Display Users");
            System.out.println("5. Insert Account");
            System.out.println("6. Update Account Balance");
            System.out.println("7. Delete Account");
            System.out.println("8. Display Accounts");
            System.out.println("9. Insert Transaction");
            System.out.println("10. Display Transactions");
            System.out.println("11. Update Transaction Amount");
            System.out.println("12. Delete Transaction");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.next();
                    System.out.print("Enter email: ");
                    String email = scanner.next();
                    System.out.print("Enter phone: ");
                    String phone = scanner.next();
                    bankSystem.insertUser(name, email, phone);
                    break;
                case 2:
                    System.out.print("Enter user ID: ");
                    int userId = scanner.nextInt();
                    System.out.print("Enter new phone: ");
                    String newPhone = scanner.next();
                    bankSystem.updateUser(userId, newPhone);
                    break;
                case 3:
                    System.out.print("Enter user ID: ");
                    int deleteUserId = scanner.nextInt();
                    bankSystem.deleteUser(deleteUserId);
                    break;
                case 4:
                    bankSystem.displayUsers();
                    break;
                case 5:
                    System.out.print("Enter user ID: ");
                    int accountUserId = scanner.nextInt();
                    System.out.print("Enter initial balance: ");
                    double balance = scanner.nextDouble();
                    bankSystem.insertAccount(accountUserId, balance);
                    break;
                case 6:
                    System.out.print("Enter account ID: ");
                    int accountId = scanner.nextInt();
                    System.out.print("Enter new balance: ");
                    double newBalance = scanner.nextDouble();
                    bankSystem.updateAccountBalance(accountId, newBalance);
                    break;
                case 7:
                    System.out.print("Enter account ID: ");
                    int deleteAccountId = scanner.nextInt();
                    bankSystem.deleteAccount(deleteAccountId);
                    break;
                case 8:
                    bankSystem.displayAccounts();
                    break;
                case 9:
                    System.out.print("Enter account ID: ");
                    int accountIdForTransaction = scanner.nextInt();
                    System.out.print("Enter transaction amount: ");
                    double amount = scanner.nextDouble();
                    System.out.print("Enter transaction type (Deposit/Withdrawal): ");
                    String transactionType = scanner.next();
                    bankSystem.insertTransaction(accountIdForTransaction, amount, transactionType);
                    break;
                case 10:
                    bankSystem.displayTransactions();
                    break;
                case 11:
                    System.out.print("Enter transaction ID: ");
                    int transactionIdToUpdate = scanner.nextInt();
                    System.out.print("Enter new transaction amount: ");
                    double newTransactionAmount = scanner.nextDouble();
                    bankSystem.updateTransactionAmount(transactionIdToUpdate, newTransactionAmount);
                    break;
                case 12:
                    System.out.print("Enter transaction ID to delete: ");
                    int transactionIdToDelete = scanner.nextInt();
                    bankSystem.deleteTransaction(transactionIdToDelete);
                    break;
                case 0:
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        } while (choice != 0);

        scanner.close();
    }
}