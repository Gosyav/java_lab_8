package lab8;

import lab8.model.Book;
import lab8.model.LibraryItem;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class LibraryItemTest {
  private static final LocalDate START = LocalDate.of(2025, 1, 1);
  private static final int LOAN_PERIOD = 7;
  private static final double DELTA = 0.0001;

  private LibraryItem createItem() {
    Book b = new Book("t", "a", "g", "100", "p");
    b.setLoanPeriod(LOAN_PERIOD);
    return b;
  }

  @Test
  public void testDaysOverdue() {
    LibraryItem item = createItem();
    item.borrow(1, START);

    LocalDate day = START.plusDays(10);
    int overdue = item.daysOverdue(day);

    assertEquals(3, overdue);
  }

  @Test
  public void testIsOverdue() {
    LibraryItem item = createItem();
    item.borrow(1, START);

    LocalDate beforeDue = START.plusDays(6);
    LocalDate afterDue = START.plusDays(8);

    assertFalse(item.isOverdue(beforeDue));
    assertTrue(item.isOverdue(afterDue));
  }

  @Test
  public void testComputeFine() {
    LibraryItem item = createItem();
    item.borrow(1, START);

    LocalDate day = START.plusDays(12);
    double fine = item.computeFine(day);

    assertEquals(5 * item.getFinePerDay(), fine, DELTA);
  }
}
