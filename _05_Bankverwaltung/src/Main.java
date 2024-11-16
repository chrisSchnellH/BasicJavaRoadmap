import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();
        boolean exit = false;

        while (!exit) {
            System.out.println("\nWelcome to the Bank Management System!");
            System.out.println("1. Open Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Show Account Balance");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int selection = scanner.nextInt();

            switch (selection) {
                case 1:
                    System.out.print("Enter the customer's name: ");
                    String name = scanner.next();
                    bank.openAccount(name);
                    break;
                case 2:
                    System.out.print("Enter the account number: ");
                    int depositAccountNumber = scanner.nextInt();
                    Customer customerToDeposit = bank.getCustomer(depositAccountNumber);
                    if (customerToDeposit != null) {
                        System.out.print("Enter the amount you want to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        customerToDeposit.deposit(depositAmount);
                    }
                    break;
                case 3:
                    System.out.print("Enter the account number: ");
                    int withdrawAccountNumber = scanner.nextInt();
                    Customer customerToWithdraw = bank.getCustomer(withdrawAccountNumber);
                    if (customerToWithdraw != null) {
                        System.out.print("Enter the amount you want to withdraw: ");
                        double withdrawalAmount = scanner.nextDouble();
                        customerToWithdraw.withdraw(withdrawalAmount);
                    }
                    break;
                case 4:
                    System.out.print("Enter the account number: ");
                    int showAccountNumber = scanner.nextInt();
                    Customer customerToShow = bank.getCustomer(showAccountNumber);
                    if (customerToShow != null) {
                        customerToShow.showBalance();
                    }
                    break;
                case 5:
                    System.out.println("Close the program.");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}
