package lab8.core;

import lab8.model.LibraryItem;
import lab8.user.User;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import java.util.HashSet;
import java.util.Set;

public class Simulation {
  public static final int SIMULATION_DAYS = 365;

  private final Library library;
  private final Random random = new Random();

  private final Map<LibraryItem, User> borrowed = new HashMap<>();
  private final Map<User, Map<LibraryItem, LocalDate>> borrowedDates = new HashMap<>();
  private final Set<LibraryItem> notifiedOverdue = new HashSet<>();

  public int totalBorrows = 0;
  public int totalReturns = 0;
  public int totalOverdues = 0;
  public double totalFines = 0;

  public Simulation(Library library) {
    this.library = library;
  }

  public void run(LocalDate start) {
    for (int i = 0; i < SIMULATION_DAYS; i++) {
      LocalDate day = start.plusDays(i);

      for (User user : library.getUsers()) {
        processReturns(user, day);
        processBorrows(user, day);
      }
    }
  }

  private void processBorrows(User user, LocalDate day) {
    LibraryItem item = library.chooseRandomItemForUser(user);

    if (item == null) {
      return;
    }

    double roll = random.nextDouble();

    if (item instanceof lab8.model.Book) {
      if (roll > Library.BOOK_BORROW_PROB) {
        return;
      }
    }

    if (item instanceof lab8.model.Journal) {
      if (roll > Library.JOURNAL_BORROW_PROB) {
        return;
      }
    }

    if (item instanceof lab8.model.Film) {
      if (roll > Library.FILM_BORROW_PROB) {
        return;
      }
    }

    boolean success = library.borrowItem(user, item, day);

    if (!success) {
      return;
    }

    borrowed.put(item, user);
    borrowedDates
        .computeIfAbsent(user, u -> new HashMap<>())
        .put(item, day);

    totalBorrows++;
  }

  private void processReturns(User user, LocalDate day) {
    Map<LibraryItem, LocalDate> items = borrowedDates.get(user);

    if (items == null) {
      return;
    }

    Map<LibraryItem, LocalDate> copy = new HashMap<>(items);

    for (LibraryItem item : copy.keySet()) {
      boolean doReturn = false;
      int overdueDays = item.daysOverdue(day);

      if (overdueDays > 0 && !notifiedOverdue.contains(item)) {
        library.notifyObservers(user, item, overdueDays);
        notifiedOverdue.add(item);
      }

      if (user.isOnTime()) {
        long due = items.get(item).plusDays(item.getLoanPeriod()).toEpochDay();

        if (day.toEpochDay() == due) {
          doReturn = true;
        }
      } else {
        double roll = random.nextDouble();

        if (roll < User.RETURN_PROBABILITY) {
          doReturn = true;
        }
      }

      if (!doReturn) {
        continue;
      }

      if (overdueDays > 0) {
        totalOverdues++;
        totalFines += item.computeFine(day);
      }

      library.returnItem(user, item, day);
      borrowed.remove(item);
      items.remove(item);

      totalReturns++;
    }
  }
}
