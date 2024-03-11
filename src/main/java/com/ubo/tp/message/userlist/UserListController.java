package main.java.com.ubo.tp.message.userlist;

import main.java.com.ubo.tp.message.core.database.IDatabaseObserver;
import main.java.com.ubo.tp.message.datamodel.Message;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.filter.UnfilteredElementsModel;
import main.java.com.ubo.tp.message.utils.UserWrapper;

public class UserListController implements IDatabaseObserver {
    UnfilteredElementsModel<UserFilterable> unfilteredUsers;
    public UserListController(UnfilteredElementsModel<UserFilterable> unfilteredUsers) {
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
        this.unfilteredUsers.add(UserWrapper.userToUserFilterable(addedUser));
    }

    @Override
    public void notifyUserDeleted(User deletedUser) {}

    @Override
    public void notifyUserModified(User modifiedUser) {}

}
