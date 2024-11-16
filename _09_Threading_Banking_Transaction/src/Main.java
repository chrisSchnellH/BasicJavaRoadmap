public class Main {
    public static void main(String[] args) throws InterruptedException {
        BankAccount account = new BankAccount(1000); // Initial balance of 1000

        // Create multiple customer threads
        Thread customer1 = new Thread(new Customer(account, false, 500), "Customer 1"); // Withdraw 500
        Thread customer2 = new Thread(new Customer(account, true, 200), "Customer 2");  // Deposit 200
        Thread customer3 = new Thread(new Customer(account, false, 700), "Customer 3"); // Withdraw 700
        Thread customer4 = new Thread(new Customer(account, true, 500), "Customer 4");  // Deposit 500

        // Start customer threads
        customer1.start();
        customer2.start();
        customer3.start();
        customer4.start();

        // Wait for all threads to complete
        customer1.join();
        customer2.join();
        customer3.join();
        customer4.join();

        // Print the transaction log
        account.printTransactionLog();
    }
}