package lab8.model;

import lab8.user.User;

public final class Journal extends LibraryItem implements Loanable {
  public static final int JOURNAL_STUDENT_LOAN = 3;
  public static final int JOURNAL_FACULTY_LOAN = 7;
  public static final double JOURNAL_FINE = 2.0;

  private final String eissn;
  private final String publisher;
  private final String latestIssue;
  private final String url;

  public Journal(String id, String title, String eissn, String publisher, String latestIssue, String url) {
    super(id, title, JOURNAL_FACULTY_LOAN, JOURNAL_FINE);
    this.eissn = eissn;
    this.publisher = publisher;
    this.latestIssue = latestIssue;
    this.url = url;
  }

  @Override
  public int getLoanPeriod(User user) {
    if (user instanceof lab8.user.Student) {
      return JOURNAL_STUDENT_LOAN;
    }

    return JOURNAL_FACULTY_LOAN;
  }

  @Override
  public double getDailyOverdueFee() {
    return JOURNAL_FINE;
  }

  @Override
  public boolean matches(String keyword) {
    String k = keyword.toLowerCase();

    return title.toLowerCase().contains(k)
        || eissn.toLowerCase().contains(k)
        || publisher.toLowerCase().contains(k)
        || latestIssue.toLowerCase().contains(k)
        || url.toLowerCase().contains(k);
  }

  @Override
  protected int getYearForComparison() {
    try {
      return Integer.parseInt(latestIssue.replaceAll("\\D", ""));
    } catch (NumberFormatException e) {
      return 0;
    }
  }
}
