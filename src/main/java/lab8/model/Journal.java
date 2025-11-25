package lab8.model;

public class Journal extends LibraryItem {
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
}
