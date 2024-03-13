package com.ubo.tp.message.userlist;

import com.ubo.tp.message.core.database.IDatabaseObserver;
import com.ubo.tp.message.datamodel.Message;
import com.ubo.tp.message.datamodel.User;
import com.ubo.tp.message.filter.UnfilteredElementsModel;

public class UserListController implements IDatabaseObserver {
    protected UnfilteredElementsModel<User> unfilteredUsers;
    public UserListController(UnfilteredElementsModel<User> unfilteredUsers) {
        this.unfilteredUsers = unfilteredUsers;
    }

    @Override
    public void notifyMessageAdded(Message addedMessage) {

    }

    @Override
    public void notifyMessageDeleted(Message deletedMessage) {

    }

    @Override
    public void notifyMessageModified(Message modifiedMessage) {

    }

    @Override
    public void notifyUserAdded(User addedUser) {
        this.unfilteredUsers.add(addedUser);
    }

    @Override
    public void notifyUserDeleted(User deletedUser) {}

    @Override
    public void notifyUserModified(User modifiedUser) {}

}
