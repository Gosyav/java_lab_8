package lab8;

import lab8.user.Student;
import lab8.model.Book;
import lab8.model.Journal;
import lab8.model.Film;
import lab8.model.LibraryItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class StudentTest {
  private LibraryItem book() {
    return new Book("i", "t", "g", "100", "p");
  }

  private LibraryItem journal() {
    return new Journal("i", "t", "g", "100", "p", "2025");
  }

  private LibraryItem film() {
    return new Film("i", "t", "d", "100", "s", "2025", "p");
  }

  @Test
  public void testBookLimit() {
    Student s = new Student(1, true);

    s.borrowItem(book());
    s.borrowItem(book());
    s.borrowItem(book());

    assertFalse(s.canBorrowBook());
  }

  @Test
  public void testJournalLimit() {
    Student s = new Student(1, true);

    s.borrowItem(journal());
    s.borrowItem(journal());
    s.borrowItem(journal());

    assertFalse(s.canBorrowJournal());
  }

  @Test
  public void testFilmLimit() {
    Student s = new Student(1, true);

    s.borrowItem(film());

    assertFalse(s.canBorrowFilm());
  }
}
