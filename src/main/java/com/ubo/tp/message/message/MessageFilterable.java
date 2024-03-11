package main.java.com.ubo.tp.message.message;

import main.java.com.ubo.tp.message.datamodel.Message;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.filter.Filterable;

import java.util.UUID;

public class MessageFilterable extends Message implements Filterable {
    public MessageFilterable(User sender, String text) {
        super(sender, text);
    }
    public MessageFilterable(Message message) {
        super(message.getSender(), message.getText());
    }

    public MessageFilterable(UUID messageUuid, User sender, long emissionDate, String text) {
        super(messageUuid, sender, emissionDate, text);
    }

    @Override
    public boolean contains(String filter) {
        if(filter.isEmpty()){
            return true;
        } else if(filter.charAt(0) == '@'){
            String realFilter = filter.substring(1);
            Boolean isUserTagMatch = this.getSender().getUserTag().contains(realFilter);
            Boolean isMessageCitingTagMatch = this.getUserTags().stream().anyMatch(e -> e.contains(realFilter));
            return isUserTagMatch || isMessageCitingTagMatch;
        } else if(filter.charAt(0) == '#'){
            String realFilter = filter.substring(1);
            return this.getTags().stream().anyMatch(e -> e.contains(realFilter));
        }
        return this.mText.contains(filter);
    }
}
