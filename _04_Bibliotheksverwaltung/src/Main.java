import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        boolean exit = false;

        System.out.println("Welcome to the library system.");

        while (!exit) {
            System.out.println("\n Choose an option:");
            System.out.println("1. Add a book");
            System.out.println("2. Borrow a book");
            System.out.println("3. Return a book");
            System.out.println("4. Show all books");
            System.out.println("5. Leave the system");
            System.out.println("Your choice: (Enter 1,2,3,4 or 5)");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                // add book
                case 1:
                    System.out.println("Enter book title: ");
                    String titel = scanner.nextLine();
                    System.out.println("Enter book author:");
                    String author = scanner.nextLine();
                    Book newBook = new Book(titel, author);
                    library.addBook(newBook);
                    break;

                // borrow book
                case 2:
                    System.out.println("Enter book title, to borrow a book: ");
                    String loanTitle = scanner.nextLine();
                    Book loanBook = library.searchBook(loanTitle);
                    if (loanBook != null) {
                        loanBook.borrowBook();
                    } else {
                        System.out.println("The Book '" + loanTitle + "' is not available.");
                    }
                    break;

                // return book
                case 3:
                    System.out.println("Enter the book title to return the book");
                    String returnTitle = scanner.nextLine();
                    Book returnBook = library.searchBook(returnTitle);
                    if (returnBook != null) {
                        returnBook.returnBook();
                    } else {
                        System.out.println("The Book '" + returnTitle + "' is not from here.");
                    }
                    break;

                // show all books
                case 4:
                    library.showAllBooks();
                    break;

                // Exit
                case 5:
                    exit = true;
                    System.out.println("Leave the system.");
                    break;


                default:
                    System.out.println("Enter an valid option. Try again.");
                    break;
            }
        }
        scanner.close();
    }
}