package lab8.core;

import lab8.model.LibraryItem;
import lab8.user.User;

public final class EmailNotifier implements OverdueObserver {

  @Override
  public void notifyOverdue(User user, LibraryItem item, int daysLate) {
    System.out.printf(
        "[EMAIL] User %d has overdue item %s (%s) by %d days%n",
        user.getId(),
        item.getId(),
        item.getClass().getSimpleName(),
        daysLate);
  }
}
