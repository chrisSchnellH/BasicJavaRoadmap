public class Customer implements Runnable {
    private BankAccount account;
    private boolean deposit; // true = deposit, false = withdraw
    private int amount;

    public Customer(BankAccount account, boolean deposit, int amount) {
        this.account = account;
        this.deposit = deposit;
        this.amount = amount;
    }

    @Override
    public void run() {
        try {
            if (deposit) {
                account.deposit(amount); // Deposit money
            } else {
                account.withdraw(amount); // Withdraw money
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " was interrupted.");
        }
    }
}
