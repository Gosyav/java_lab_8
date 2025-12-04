package lab8.core;

import lab8.model.LibraryItem;
import lab8.user.User;

public interface OverdueObserver {
  void notifyOverdue(User user, LibraryItem item, int daysLate);
}
