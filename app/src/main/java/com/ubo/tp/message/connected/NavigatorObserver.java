package com.ubo.tp.message.connected;

import com.ubo.tp.message.datamodel.User;

public interface NavigatorObserver {
    public void switchHome();
    public void switchUserProfile(User user);
    public void switchMyProfile();

}
