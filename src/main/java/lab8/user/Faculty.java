package lab8.user;

public class Faculty extends User {
  public static final int NO_LIMIT = Integer.MAX_VALUE;

  public Faculty(int id, boolean onTime) {
    super(id, onTime);
  }

  @Override
  public boolean canBorrowBook() {
    return borrowedBooks < NO_LIMIT;
  }

  @Override
  public boolean canBorrowJournal() {
    return borrowedJournals < NO_LIMIT;
  }

  @Override
  public boolean canBorrowFilm() {
    return borrowedFilms < NO_LIMIT;
  }
}
