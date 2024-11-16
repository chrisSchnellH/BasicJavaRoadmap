import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        SimpleSocialNetwork network = new SimpleSocialNetwork();
        boolean continueProgram = true;

        System.out.println("Welcome to the Simple Social Network!");

        // Main menu
        while (continueProgram) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add user");
            System.out.println("2. Add friendship");
            System.out.println("3. Display friends");
            System.out.println("4. Check if two users are connected");
            System.out.println("5. Exit program");
            System.out.print("Your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Handle newline after int input

            switch (choice) {
                case 1:
                    System.out.print("Enter the name of the user: ");
                    String user = scanner.nextLine();
                    network.addUser(user);
                    break;

                case 2:
                    System.out.print("Enter the name of the first user: ");
                    String user1 = scanner.nextLine();
                    System.out.print("Enter the name of the second user: ");
                    String user2 = scanner.nextLine();
                    network.addFriendship(user1, user2);
                    break;

                case 3:
                    System.out.print("Enter the name of the user: ");
                    String userFriends = scanner.nextLine();
                    network.displayFriends(userFriends);
                    break;

                case 4:
                    System.out.print("Enter the name of the first user: ");
                    String connectedUser1 = scanner.nextLine();
                    System.out.print("Enter the name of the second user: ");
                    String connectedUser2 = scanner.nextLine();
                    boolean connected = network.areConnected(connectedUser1, connectedUser2);
                    if (connected) {
                        System.out.println("'" + connectedUser1 + "' and '" + connectedUser2 + "' are connected.");
                    } else {
                        System.out.println("'" + connectedUser1 + "' and '" + connectedUser2 + "' are not connected.");
                    }
                    break;

                case 5:
                    continueProgram = false;
                    System.out.println("Exiting program.");
                    break;

                default:
                    System.out.println("Invalid option. Please choose again.");
                    break;
            }
        }

        scanner.close();
    }
}