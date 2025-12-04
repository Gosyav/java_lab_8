package lab8.model;

import lab8.user.User;

public interface Loanable {
  int getLoanPeriod(User user);

  double getDailyOverdueFee();
}
