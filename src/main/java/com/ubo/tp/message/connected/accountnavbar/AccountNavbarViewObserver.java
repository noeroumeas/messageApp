package main.java.com.ubo.tp.message.connected.accountnavbar;

import main.java.com.ubo.tp.message.connected.NavigatorObserver;

public interface AccountNavbarViewObserver extends NavigatorObserver {
    public void notifyDisconnect();
}
