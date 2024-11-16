import java.util.ArrayList;

public class Library {

    private ArrayList<Book> bookList;

    public Library() {
        this.bookList = new ArrayList<>();
    }

    // add a book
    public void addBook(Book book) {
        bookList.add(book);
        System.out.println("The Book '" + book.getTitle() + "' by " + book.getAuthor() + " was added.");
    }

    // search a book
    public Book searchBook(String title) {
        for (Book book : bookList) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    // show all books
    public void showAllBooks() {
        if(bookList.isEmpty()) {
            System.out.println("Library is empty!");
        } else {
            System.out.println("\n List of all books:");
            for (Book book : bookList) {
                System.out.println(book.getTitle() + " by " + book.getAuthor() + (book.isBorrowed() ? " is on loan" : " is not on loan"));
            }
        }
    }
}
