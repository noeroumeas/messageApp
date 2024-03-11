package main.java.com.ubo.tp.message.connected;

import main.java.com.ubo.tp.message.datamodel.User;

public interface NavigatorObserver {
    public void switchHome();
    public void switchUserProfile(User user);
    public void switchMyProfile();

}
