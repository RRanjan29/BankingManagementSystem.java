// File: BankingManagementSystem.java

import java.util.*;

public class BankingManagementSystem {
    static Scanner sc = new Scanner(System.in);
    static Map<String, Account> accounts = new HashMap<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Create Account\n2. Login\n3. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1 -> createAccount();
                case 2 -> login();
                case 3 -> System.exit(0);
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    static void createAccount() {
        System.out.print("Enter username: ");
        String user = sc.nextLine();
        System.out.print("Enter password: ");
        String pass = sc.nextLine();
        if (accounts.containsKey(user)) {
            System.out.println("Username already exists!");
            return;
        }
        accounts.put(user, new Account(user, pass));
        System.out.println("Account created successfully!");
    }

    static void login() {
        System.out.print("Enter username: ");
        String user = sc.nextLine();
        System.out.print("Enter password: ");
        String pass = sc.nextLine();
        Account acc = accounts.get(user);
        if (acc != null && acc.checkPassword(pass)) {
            System.out.println("Login successful.");
            userMenu(acc);
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    static void userMenu(Account acc) {
        while (true) {
            System.out.println("\n1. View Balance\n2. Deposit\n3. Withdraw\n4. Logout");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> System.out.println("Balance: Rs. " + acc.getBalance());
                case 2 -> {
                    System.out.print("Amount to deposit: ");
                    double amt = sc.nextDouble();
                    acc.deposit(amt);
                }
                case 3 -> {
                    System.out.print("Amount to withdraw: ");
                    double amt = sc.nextDouble();
                    acc.withdraw(amt);
                }
                case 4 -> {
                    System.out.println("Logged out.");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}

class Account {
    private final String username;
    private final String password;
    private double balance;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 0.0;
    }

    public boolean checkPassword(String pass) {
        return this.password.equals(pass);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amt) {
        if (amt > 0) {
            balance += amt;
            System.out.println("Deposited Rs. " + amt);
        } else {
            System.out.println("Invalid amount.");
        }
    }

    public void withdraw(double amt) {
        if (amt > 0 && amt <= balance) {
            balance -= amt;
            System.out.println("Withdrawn Rs. " + amt);
        } else {
            System.out.println("Insufficient funds or invalid amount.");
        }
    }
}
