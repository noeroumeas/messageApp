package com.ubo.tp.message.message.sender;

import com.ubo.tp.message.core.EntityManager;
import com.ubo.tp.message.datamodel.Message;
import com.ubo.tp.message.ihm.session.ISession;


public class MessageSenderController implements MessageSenderViewObserver{
    protected ISession session;
    protected MessageSenderView messageSenderView;
    protected EntityManager entityManager;
    public MessageSenderController(ISession session, MessageSenderView messageSenderView, EntityManager entityManager){
        this.session = session;
        this.messageSenderView = messageSenderView;
        this.entityManager = entityManager;
    }
    @Override
    public void notifyMessageSend(String msg) {
        if(msg.isEmpty()){
            this.messageSenderView.displayMessageEmpty();
            return;
        }
        if(msg.length() > 200){
            this.messageSenderView.displayMessageTooBig();
            return;
        }
        Message newMessage = new Message(this.session.getConnectedUser(), msg);
        this.entityManager.writeMessageFile(newMessage);
        this.messageSenderView.clearMessageTextField();
    }
}
