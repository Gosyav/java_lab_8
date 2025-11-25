package lab8.user;

public class Student extends User {
  public static final int MAX_BOOKS = 3;
  public static final int MAX_JOURNALS = 3;
  public static final int MAX_FILMS = 1;

  public Student(int id, boolean onTime) {
    super(id, onTime);
  }

  @Override
  public boolean canBorrowBook() {
    return borrowedBooks < MAX_BOOKS;
  }

  @Override
  public boolean canBorrowJournal() {
    return borrowedJournals < MAX_JOURNALS;
  }

  @Override
  public boolean canBorrowFilm() {
    return borrowedFilms < MAX_FILMS;
  }
}
