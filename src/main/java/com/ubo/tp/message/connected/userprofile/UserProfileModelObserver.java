package main.java.com.ubo.tp.message.connected.userprofile;

import main.java.com.ubo.tp.message.datamodel.User;

public interface UserProfileModelObserver {
    public void notifyUserChanged(User u);
}
