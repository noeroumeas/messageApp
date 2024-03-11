package main.java.com.ubo.tp.message.utils;

import main.java.com.ubo.tp.message.datamodel.Message;
import main.java.com.ubo.tp.message.message.MessageFilterable;

import java.util.ArrayList;
import java.util.Set;

public class MessageWrapper {
    public static MessageFilterable messageToMessageFilterable(Message message){
        return new MessageFilterable(message);
    }

    public static ArrayList<MessageFilterable> messagesToMessagesFilterable(Set<Message> messages){
        ArrayList<MessageFilterable> messagesFilterable = new ArrayList<>();
        for(Message m : messages){
            messagesFilterable.add(new MessageFilterable(m));
        }
        return messagesFilterable;
    }
}
