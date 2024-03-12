package main.java.com.ubo.tp.message.message.sender;

import main.java.com.ubo.tp.message.core.EntityManager;
import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.ihm.session.ISession;

public class MessageSenderComponent {
    protected MessageSenderView messageSenderView;
    public MessageSenderComponent(ISession session, EntityManager entityManager) {
        this.messageSenderView = new MessageSenderView();
        MessageSenderController messagesController = new MessageSenderController(session, this.messageSenderView, entityManager);
        messageSenderView.addObserver(messagesController);
    }
    public MessageSenderView getMessageSenderView() {
        return messageSenderView;
    }
}
