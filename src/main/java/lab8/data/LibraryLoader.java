package lab8.data;

import lab8.model.Book;
import lab8.model.Film;
import lab8.model.Journal;

import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class LibraryLoader {
  public static final String SEP = ";";

  public static List<Book> loadBooks(String path) {
    List<Book> list = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
      String line;
      br.readLine();

      while ((line = br.readLine()) != null) {
        String[] p = line.split(SEP);
        String[] f = new String[5];

        for (int i = 0; i < 5; i++) {
          if (i < p.length) {
            f[i] = p[i];
          } else {
            f[i] = "";
          }
        }

        Book b = new Book(f[0], f[1], f[2], f[3], f[4]);
        list.add(b);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    return list;
  }

  public static List<Journal> loadJournals(String path) {
    List<Journal> list = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
      String line;
      br.readLine();

      while ((line = br.readLine()) != null) {
        String[] p = line.split(SEP);
        String[] f = new String[6];

        for (int i = 0; i < 6; i++) {
          if (i < p.length) {
            f[i] = p[i];
          } else {
            f[i] = "";
          }
        }

        Journal j = new Journal(f[0], f[1], f[2], f[3], f[4], f[5]);
        list.add(j);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    return list;
  }

  public static List<Film> loadFilms(String path) {
    List<Film> list = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
      String line;
      br.readLine();

      while ((line = br.readLine()) != null) {
        String[] p = line.split(SEP);
        String[] f = new String[7];

        for (int i = 0; i < 7; i++) {
          if (i < p.length) {
            f[i] = p[i];
          } else {
            f[i] = "";
          }
        }

        Film film = new Film(f[0], f[1], f[2], f[3], f[4], f[5], f[6]);
        list.add(film);
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    return list;
  }
}
