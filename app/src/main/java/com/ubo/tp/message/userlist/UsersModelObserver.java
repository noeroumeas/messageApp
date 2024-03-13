package com.ubo.tp.message.userlist;

import com.ubo.tp.message.datamodel.User;

import java.util.List;

public interface UsersModelObserver {
    public void usersChanged(List<User> users);
    public void userAdded(User u);
}
