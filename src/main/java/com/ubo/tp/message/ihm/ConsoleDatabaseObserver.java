package main.java.com.ubo.tp.message.ihm;

import main.java.com.ubo.tp.message.core.database.IDatabaseObserver;
import main.java.com.ubo.tp.message.datamodel.Message;
import main.java.com.ubo.tp.message.datamodel.User;

public class ConsoleDatabaseObserver implements IDatabaseObserver {
    @Override
    public void notifyMessageAdded(Message addedMessage) {
        System.out.println("Message added to database : " + addedMessage.getUuid() + ":" + addedMessage.getText());
    }

    @Override
    public void notifyMessageDeleted(Message deletedMessage) {
        System.out.println("Message deleted from database : " + deletedMessage.getUuid() + ":" + deletedMessage.getText());
    }

    @Override
    public void notifyMessageModified(Message modifiedMessage) {
        System.out.println("Message modified from database : " + modifiedMessage.getUuid() + ":" + modifiedMessage.getText());
    }

    @Override
    public void notifyUserAdded(User addedUser) {
        System.out.println("User added to database : " + addedUser.getUuid() + ":" + addedUser.getName());
    }

    @Override
    public void notifyUserDeleted(User deletedUser) {
        System.out.println("User deleted from database : " + deletedUser.getUuid() + ":" + deletedUser.getName());
    }

    @Override
    public void notifyUserModified(User modifiedUser) {
        System.out.println("User modified from database : " + modifiedUser.getUuid() + ":" + modifiedUser.getName());
    }
}
