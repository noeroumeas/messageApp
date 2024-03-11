package main.java.com.ubo.tp.message.message.sender;

import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.ihm.session.ISession;

public class MessageSenderComponent {
    protected MessageSenderView messageSenderView;
    public MessageSenderComponent(IDatabase db, ISession session) {
        this.messageSenderView = new MessageSenderView();
        MessageSenderController messagesController = new MessageSenderController(db, session, this.messageSenderView);
        messageSenderView.addObserver(messagesController);
    }
    public MessageSenderView getMessageSenderView() {
        return messageSenderView;
    }
}
