package main.java.com.ubo.tp.message.userlist;

import main.java.com.ubo.tp.message.connected.NavigatorObserver;

public interface UserListSearchViewObserver extends NavigatorObserver {
    public void notifyFilterChanged(String filter);
}
