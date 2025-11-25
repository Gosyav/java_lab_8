package lab8.core;

import lab8.model.Book;
import lab8.model.Film;
import lab8.model.Journal;
import lab8.model.LibraryItem;
import lab8.user.Faculty;
import lab8.user.Student;
import lab8.user.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Library {

  public static final double BOOK_BORROW_PROB = 0.05;
  public static final double JOURNAL_BORROW_PROB = 0.08;
  public static final double FILM_BORROW_PROB = 0.05;

  private final List<Book> books = new ArrayList<>();
  private final List<Journal> journals = new ArrayList<>();
  private final List<Film> films = new ArrayList<>();

  private final List<User> users = new ArrayList<>();

  private final Random random = new Random();

  public void addBooks(List<Book> list) {
    books.addAll(list);
  }

  public void addJournals(List<Journal> list) {
    journals.addAll(list);
  }

  public void addFilms(List<Film> list) {
    films.addAll(list);
  }

  public void addUser(User user) {
    users.add(user);
  }

  public List<User> getUsers() {
    return users;
  }

  public boolean borrowItem(User user, LibraryItem item, LocalDate day) {

    if (!item.isAvailable()) {
      return false;
    }

    if (item instanceof Book) {
      if (!user.canBorrowBook()) {
        return false;
      }
    }

    if (item instanceof Journal) {
      if (!user.canBorrowJournal()) {
        return false;
      }
    }

    if (item instanceof Film) {
      if (!user.canBorrowFilm()) {
        return false;
      }
    }

    if (item instanceof Journal) {
      if (user instanceof Student) {
        item.setLoanPeriod(Journal.JOURNAL_STUDENT_LOAN);
      } else if (user instanceof Faculty) {
        item.setLoanPeriod(Journal.JOURNAL_FACULTY_LOAN);
      }
    }

    item.borrow(user.getId(), day);
    user.borrowItem(item);

    return true;
  }

  public void returnItem(User user, LibraryItem item, LocalDate day) {
    item.computeFine(day);
    item.returnItem();
    user.returnItem(item);
  }

  public LibraryItem getRandomAvailableBook() {
    List<Book> available = new ArrayList<>();

    for (Book b : books) {
      if (b.isAvailable()) {
        available.add(b);
      }
    }

    if (available.isEmpty()) {
      return null;
    }

    int index = random.nextInt(available.size());
    return available.get(index);
  }

  public LibraryItem getRandomAvailableJournal() {
    List<Journal> available = new ArrayList<>();

    for (Journal j : journals) {
      if (j.isAvailable()) {
        available.add(j);
      }
    }

    if (available.isEmpty()) {
      return null;
    }

    int index = random.nextInt(available.size());
    return available.get(index);
  }

  public LibraryItem getRandomAvailableFilm() {
    List<Film> available = new ArrayList<>();

    for (Film f : films) {
      if (f.isAvailable()) {
        available.add(f);
      }
    }

    if (available.isEmpty()) {
      return null;
    }

    int index = random.nextInt(available.size());
    return available.get(index);
  }

  public LibraryItem chooseRandomItemForUser(User user) {
    double roll = random.nextDouble();

    if (roll < BOOK_BORROW_PROB) {
      return getRandomAvailableBook();
    }

    if (roll < BOOK_BORROW_PROB + JOURNAL_BORROW_PROB) {
      return getRandomAvailableJournal();
    }

    return getRandomAvailableFilm();
  }
}
