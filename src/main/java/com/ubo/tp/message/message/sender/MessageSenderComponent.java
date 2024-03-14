package com.ubo.tp.message.message.sender;

import com.ubo.tp.message.core.EntityManager;
import com.ubo.tp.message.ihm.session.ISession;

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
