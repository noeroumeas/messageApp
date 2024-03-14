package com.ubo.tp.message.connected.userprofile;

import com.ubo.tp.message.datamodel.User;

public interface UserProfileModelObserver {
    public void notifyUserChanged(User u);
}
