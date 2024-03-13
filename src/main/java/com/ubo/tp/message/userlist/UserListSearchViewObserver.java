package com.ubo.tp.message.userlist;

import com.ubo.tp.message.connected.NavigatorObserver;

public interface UserListSearchViewObserver extends NavigatorObserver {
    public void notifyFilterChanged(String filter);
}
