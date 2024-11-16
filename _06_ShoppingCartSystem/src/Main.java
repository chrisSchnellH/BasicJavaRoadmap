import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ShoppingCart shoppingCart = new ShoppingCart();

        boolean continueShopping = true;

        System.out.println("Welcome to the Online Shop!");

        while (continueShopping) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add physical product");
            System.out.println("2. Add digital product");
            System.out.println("3. Show all items in the cart");
            System.out.println("4. Show total price");
            System.out.println("5. Exit program");
            System.out.print("Your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Handle the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter the name of the physical product: ");
                    String physicalName = scanner.nextLine();
                    System.out.print("Enter the price of the product: ");
                    double physicalPrice = scanner.nextDouble();
                    PhysicalProduct physicalProduct = new PhysicalProduct(physicalName, physicalPrice);
                    shoppingCart.addItem(physicalProduct);
                    break;

                case 2:
                    System.out.print("Enter the name of the digital product: ");
                    String digitalName = scanner.nextLine();
                    System.out.print("Enter the price of the product: ");
                    double digitalPrice = scanner.nextDouble();
                    DigitalProduct digitalProduct = new DigitalProduct(digitalName, digitalPrice);
                    shoppingCart.addItem(digitalProduct);
                    break;

                case 3:
                    shoppingCart.displayItems();
                    break;

                case 4:
                    double totalPrice = shoppingCart.calculateTotalPrice();
                    System.out.println("The total price of all items in the cart is: " + totalPrice);
                    break;

                case 5:
                    continueShopping = false;
                    System.out.println("Exiting the program.");
                    break;

                default:
                    System.out.println("Invalid option. Please choose again.");
                    break;
            }
        }

        scanner.close();
    }
}