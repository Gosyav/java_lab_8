package lab8.user;

import lab8.model.LibraryItem;

public abstract class User {
  public static final double RETURN_PROBABILITY = 0.02;

  protected final int id;
  protected final boolean onTime;

  protected int borrowedBooks;
  protected int borrowedJournals;
  protected int borrowedFilms;

  public User(int id, boolean onTime) {
    this.id = id;
    this.onTime = onTime;
  }

  public abstract boolean canBorrowBook();

  public abstract boolean canBorrowJournal();

  public abstract boolean canBorrowFilm();

  public void borrowItem(LibraryItem item) {
    if (item instanceof lab8.model.Book)
      borrowedBooks++;
    else if (item instanceof lab8.model.Journal)
      borrowedJournals++;
    else if (item instanceof lab8.model.Film)
      borrowedFilms++;
  }

  public void returnItem(LibraryItem item) {
    if (item instanceof lab8.model.Book)
      borrowedBooks--;
    else if (item instanceof lab8.model.Journal)
      borrowedJournals--;
    else if (item instanceof lab8.model.Film)
      borrowedFilms--;
  }

  public int getId() {
    return id;
  }

  public boolean isOnTime() {
    return onTime;
  }
}
