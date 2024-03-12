package main.java.com.ubo.tp.message.message;

import main.java.com.ubo.tp.message.core.database.IDatabaseObserver;
import main.java.com.ubo.tp.message.datamodel.Message;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.filter.UnfilteredElementsModel;
import main.java.com.ubo.tp.message.utils.MessageWrapper;

public class MessagesController implements IDatabaseObserver {
    protected UnfilteredElementsModel<MessageFilterable> messages;
    public MessagesController(UnfilteredElementsModel<MessageFilterable> messages) {
        this.messages = messages;
    }

    @Override
    public void notifyMessageAdded(Message addedMessage) {
        messages.add(MessageWrapper.messageToMessageFilterable(addedMessage));
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
