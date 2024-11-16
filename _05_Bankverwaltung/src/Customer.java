public class Customer {
    private String name;
    private int accountNumber;
    private double balance;

    // Constructor
    public Customer(String name, int accountNumber) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = 0.0;
    }

    // Getter
    public String getName() {
        return name;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    // Deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(amount + "€ has been deposited into your account.");
        } else {
            System.out.println("Invalid amount.");
        }
    }

    // Withdraw money
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println(amount + "€ has been withdrawn from your account.");
        } else {
            System.out.println("Invalid amount or insufficient funds.");
        }
    }

    // Show Balance
    public void showBalance() {
        System.out.println("Balance for " + name + ": " + balance + "€.");
    }
}

