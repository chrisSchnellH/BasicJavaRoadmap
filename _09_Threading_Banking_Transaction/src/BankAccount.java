import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private int balance;
    // Logging
    private List<String> transactionLog = new ArrayList<>();

    public BankAccount(int initialBalance) {
        this.balance = initialBalance;
    }

    // Thread security for withdraw
    public synchronized void withdraw(int amount) throws InterruptedException {
        // Wait for enough money
        while (balance < amount) {
            System.out.println(Thread.currentThread().getName() + " wants to withdraw " + amount + " but there is not enough balance. Waiting for deposit...");
            wait();
        }
        System.out.println(Thread.currentThread().getName() + " is withdrawing " + amount + ".");
        balance -= amount;
        transactionLog.add(Thread.currentThread().getName() + " withdrew " + amount + ". New balance: " + balance);
        System.out.println("New balance after withdrawal: " + balance);
    }

    // Thread security for deposit
    public synchronized void deposit(int amount) {
        System.out.println(Thread.currentThread().getName() + " is depositing " + amount + ".");
        balance += amount;
        transactionLog.add(Thread.currentThread().getName() + " deposited " + amount + ". New balance: " + balance);
        System.out.println("New balance after deposit: " + balance);
        notifyAll(); // Notify waiting threads
    }

    // Show Logging
    public synchronized void printTransactionLog() {
        System.out.println("\n--- Transaction Log ---");
        for (String logEntry : transactionLog) {
            System.out.println(logEntry);
        }
        System.out.println("--- End of Log ---\n");
    }

    public synchronized int getBalance() {
        return balance;
    }
}
