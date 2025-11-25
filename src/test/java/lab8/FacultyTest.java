package lab8;

import lab8.user.Faculty;
import lab8.model.Book;
import lab8.model.Journal;
import lab8.model.Film;
import lab8.model.LibraryItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FacultyTest {
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
  public void testFacultyHasNoLimits() {
    Faculty f = new Faculty(1, true);

    for (int i = 0; i < 100; i++) {
      f.borrowItem(book());
      f.borrowItem(journal());
      f.borrowItem(film());
    }

    assertTrue(f.canBorrowBook());
    assertTrue(f.canBorrowJournal());
    assertTrue(f.canBorrowFilm());
  }
}
