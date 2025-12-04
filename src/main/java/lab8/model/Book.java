package lab8.model;

import lab8.user.User;

public final class Book extends LibraryItem implements Loanable {
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

  @Override
  public int getLoanPeriod(User user) {
    return BOOK_LOAN_PERIOD;
  }

  @Override
  public double getDailyOverdueFee() {
    return BOOK_FINE;
  }

  @Override
  public boolean matches(String keyword) {
    String k = keyword.toLowerCase();

    return title.toLowerCase().contains(k)
        || author.toLowerCase().contains(k)
        || genre.toLowerCase().contains(k)
        || publisher.toLowerCase().contains(k);
  }
}
