import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<Integer, Customer> customerList = new HashMap<>();
    private static int currentAccountNumber = 1000; // Static variable for unique account numbers

    // Create new Customer
    public void openAccount(String name) {
        Customer newCustomer = new Customer(name, currentAccountNumber);
        customerList.put(currentAccountNumber, newCustomer);
        System.out.println("Account opened for " + name + ". Account Number: " + currentAccountNumber);
        currentAccountNumber++;
    }

    public Customer getCustomer(int accountNumber) {
        if (customerList.containsKey(accountNumber)) {
            return customerList.get(accountNumber);
        } else {
            System.out.println("No customer exists with this account number.");
            return null;
        }
    }
}
