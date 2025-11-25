package lab8.model;

import java.time.LocalDate;

public abstract class LibraryItem {
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

    return overdue * finePerDay;
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
}
