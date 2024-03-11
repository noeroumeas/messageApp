package main.java.com.ubo.tp.message.userlist_old;

import main.java.com.ubo.tp.message.connected.NavigatorObserver;

public interface UserListSearchViewObserver extends NavigatorObserver {
    public void notifyFilterChanged(String filter);
}
