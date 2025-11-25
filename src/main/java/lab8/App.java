package lab8;

import lab8.core.Library;
import lab8.core.Simulation;
import lab8.data.LibraryLoader;
import lab8.user.Student;
import lab8.user.Faculty;

import java.time.LocalDate;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class App {
  public static final String BOOKS_PATH = "./books.csv";
  public static final String JOURNALS_PATH = "./jlist.csv";
  public static final String FILMS_PATH = "./movies.csv";

  public static final int STUDENT_COUNT = 80;
  public static final int FACULTY_COUNT = 20;
  public static final int ON_TIME_USERS = 67;
  public static final int TOTAL_USERS = 100;

  public static void main(String[] args) {
    Library library = new Library();

    library.addBooks(LibraryLoader.loadBooks(BOOKS_PATH));
    library.addJournals(LibraryLoader.loadJournals(JOURNALS_PATH));
    library.addFilms(LibraryLoader.loadFilms(FILMS_PATH));

    List<Boolean> onTimeList = new ArrayList<>();
    for (int i = 0; i < ON_TIME_USERS; i++) {
      onTimeList.add(true);
    }

    for (int i = ON_TIME_USERS; i < TOTAL_USERS; i++) {
      onTimeList.add(false);
    }

    Collections.shuffle(onTimeList);

    for (int i = 0; i < STUDENT_COUNT; i++) {
      library.addUser(new Student(i, onTimeList.get(i)));
    }

    for (int i = 0; i < FACULTY_COUNT; i++) {
      library.addUser(new Faculty(STUDENT_COUNT + i, onTimeList.get(STUDENT_COUNT + i)));
    }

    Simulation simulation = new Simulation(library);
    simulation.run(LocalDate.of(2025, 1, 1));

    System.out.println("Simulation finished.");
    System.out.println("Total borrowed: " + simulation.totalBorrows);
    System.out.println("Total returned: " + simulation.totalReturns);
    System.out.println("Total overdue items: " + simulation.totalOverdues);
    System.out.println("Total fines collected: " + simulation.totalFines);
  }
}
