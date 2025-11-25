package lab8.model;

public class Book extends LibraryItem {
  public static final int BOOK_LOAN_PERIOD = 14;
  public static final double BOOK_FINE = 0.5;

  private final String author;
  private final String genre;
  private final String publisher;

  public Book(String id, String title, String author, String genre, String publisher) {
    super(id, title, BOOK_LOAN_PERIOD, BOOK_FINE);
    this.author = author;
    this.genre = genre;
    this.publisher = publisher;
  }
}
