package main.java.com.ubo.tp.message.connected;

import main.java.com.ubo.tp.message.datamodel.User;

public interface UserProfileViewObserver extends NavigatorObserver {
    public void notifyUnfollow(User u);
    public void notifyFollow(User u);
}
