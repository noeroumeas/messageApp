package main.java.com.ubo.tp.message.searchList;

import main.java.com.ubo.tp.message.connected.NavigatorObserver;
import main.java.com.ubo.tp.message.core.database.IDatabaseObserver;
import main.java.com.ubo.tp.message.datamodel.Message;
import main.java.com.ubo.tp.message.datamodel.User;

public class SearchListController implements IDatabaseObserver, NavigatorObserver {
    public SearchListController(){

    }

    @Override
    public void switchHome() {

    }

    @Override
    public void switchUserProfile(User user) {

    }

    @Override
    public void switchMyProfile() {

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

    }

    @Override
    public void notifyUserDeleted(User deletedUser) {

    }

    @Override
    public void notifyUserModified(User modifiedUser) {

    }
}
