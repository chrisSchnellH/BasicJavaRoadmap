public class Book {

    private String title;
    private String author;
    private boolean isOnLoan;

    public Book() {}

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isOnLoan = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isBorrowed() {
        return isOnLoan;
    }

    // Borrow a book
    public void borrowBook() {
        if(!isOnLoan) {
            isOnLoan = true;
            System.out.println("The Book '" + title + "' was successfully borrowed.");
        } else {
            System.out.println("The Book '" + title + "' is not on loan.");
        }
    }

    // Return a book
    public void returnBook() {
        if(isOnLoan) {
            isOnLoan = false;
            System.out.println("The Book '" + title + "' was successfully returned.");
        }
    }
}
