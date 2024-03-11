package main.java.com.ubo.tp.message.message.sender;

import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.datamodel.Message;
import main.java.com.ubo.tp.message.ihm.session.ISession;


public class MessageSenderController implements MessageSenderViewObserver{
    IDatabase db;
    ISession session;
    MessageSenderView messageSenderView;
    public MessageSenderController(IDatabase db, ISession session, MessageSenderView messageSenderView){
        this.db = db;
        this.session = session;
        this.messageSenderView = messageSenderView;
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
        this.db.addMessage(new Message(this.session.getConnectedUser(), msg));
        this.messageSenderView.clearMessageTextField();
    }
}
