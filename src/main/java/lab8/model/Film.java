package lab8.model;

public class Film extends LibraryItem {
  public static final int FILM_LOAN_PERIOD = 2;
  public static final double FILM_FINE = 5.0;

  private final String genre;
  private final String director;
  private final String year;
  private final String runtime;
  private final String rating;

  public Film(String id, String title, String genre, String director, String year, String runtime, String rating) {
    super(id, title, FILM_LOAN_PERIOD, FILM_FINE);
    this.genre = genre;
    this.director = director;
    this.year = year;
    this.runtime = runtime;
    this.rating = rating;
  }
}
