package lab8.model;

import lab8.user.User;

public final class Film extends LibraryItem implements Loanable {
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

  @Override
  public int getLoanPeriod(User user) {
    return FILM_LOAN_PERIOD;
  }

  @Override
  public double getDailyOverdueFee() {
    return FILM_FINE;
  }

  @Override
  public boolean matches(String keyword) {
    String k = keyword.toLowerCase();

    return title.toLowerCase().contains(k)
        || genre.toLowerCase().contains(k)
        || director.toLowerCase().contains(k)
        || year.toLowerCase().contains(k)
        || runtime.toLowerCase().contains(k)
        || rating.toLowerCase().contains(k);
  }

  @Override
  protected int getYearForComparison() {
    try {
      return Integer.parseInt(year.replaceAll("\\D", ""));
    } catch (NumberFormatException e) {
      return 0;
    }
  }
}
