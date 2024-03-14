package com.ubo.tp.message.connected.accountnavbar;

import com.ubo.tp.message.connected.NavigatorObserver;

public interface AccountNavbarViewObserver extends NavigatorObserver {
    public void notifyDisconnect();
}
