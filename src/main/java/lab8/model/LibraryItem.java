package lab8.model;

import java.time.LocalDate;

public abstract sealed class LibraryItem implements Searchable, Comparable<LibraryItem> permits Book, Journal, Film {
  public static final int NO_LOAN_USER = -1;

  protected final String id;
  protected final String title;
  protected int loanPeriod;
  protected double finePerDay;

  protected int borrowedBy = NO_LOAN_USER;
  protected LocalDate borrowedDate;

  public LibraryItem(String id, String title, int loanPeriod, double finePerDay) {
    this.id = id;
    this.title = title;
    this.loanPeriod = loanPeriod;
    this.finePerDay = finePerDay;
  }

  public boolean isAvailable() {
    return borrowedBy == NO_LOAN_USER;
  }

  public void borrow(int userId, LocalDate day) {
    this.borrowedBy = userId;
    this.borrowedDate = day;
  }

  public void returnItem() {
    this.borrowedBy = NO_LOAN_USER;
    this.borrowedDate = null;
  }

  public int daysOverdue(LocalDate today) {
    if (borrowedDate == null) {
      return 0;
    }

    LocalDate due = borrowedDate.plusDays(loanPeriod);
    return (int) (today.toEpochDay() - due.toEpochDay());
  }

  public boolean isOverdue(LocalDate today) {
    return daysOverdue(today) > 0;
  }

  public double computeFine(LocalDate today) {
    int overdue = daysOverdue(today);

    if (overdue <= 0) {
      return 0;
    }

    return overdue * ((Loanable) this).getDailyOverdueFee();
  }

  @Override
  public int compareTo(LibraryItem other) {
    int byTitle = this.title.compareToIgnoreCase(other.title);
    if (byTitle != 0) {
      return byTitle;
    }

    int thisType = typeOrder(this);
    int otherType = typeOrder(other);
    if (thisType != otherType) {
      return Integer.compare(thisType, otherType);
    }

    return Integer.compare(this.getYearForComparison(), other.getYearForComparison());
  }

  private static int typeOrder(LibraryItem item) {
    if (item instanceof Book)
      return 0;
    if (item instanceof Journal)
      return 1;
    if (item instanceof Film)
      return 2;
    return 3;
  }

  public String getId() {
    return id;
  }

  public double getFinePerDay() {
    return finePerDay;
  }

  public int getLoanPeriod() {
    return loanPeriod;
  }

  public void setLoanPeriod(int days) {
    this.loanPeriod = days;
  }

  protected int getYearForComparison() {
    return 0;
  }
}
