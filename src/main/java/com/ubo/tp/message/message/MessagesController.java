package main.java.com.ubo.tp.message.message;

import main.java.com.ubo.tp.message.core.database.IDatabaseObserver;
import main.java.com.ubo.tp.message.datamodel.Message;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.filter.UnfilteredElementsModel;

import java.util.ArrayList;
import java.util.List;

public class MessagesController implements IDatabaseObserver {
    protected UnfilteredElementsModel<Message> unfilteredMessages;
    protected MessagesView messagesView;
    protected List<Message> messages;
    public MessagesController(UnfilteredElementsModel<Message> unfilteredMessages) {
        this.unfilteredMessages = unfilteredMessages;
        this.messages = unfilteredMessages.getElements();
        this.unfilteredMessages.setElements(this.messages);
    }

    @Override
    public void notifyMessageAdded(Message addedMessage) {
        MessagesOrderer.addMessageToList(this.messages, addedMessage);
        this.unfilteredMessages.setElements(this.messages);
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
