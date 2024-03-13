package com.ubo.tp.message.message;

import com.ubo.tp.message.datamodel.Message;
import com.ubo.tp.message.filter.Filter;

public class FilterMessage extends Filter<Message, String> {
    public FilterMessage(String filterElement) {
        super(filterElement);
    }

    @Override
    public boolean isFiltered(Message element) {
        if(this.filterElement.isEmpty()){
            return false;
        } else if(this.filterElement.charAt(0) == '@'){
            String realFilter = this.filterElement.substring(1);
            Boolean isUserTagMatch = element.getSender().getUserTag().contains(realFilter);
            Boolean isMessageCitingTagMatch = element.getUserTags().stream().anyMatch(e -> e.contains(realFilter));
            return !(isUserTagMatch || isMessageCitingTagMatch);
        } else if(this.filterElement.charAt(0) == '#'){
            String realFilter = this.filterElement.substring(1);
            return !element.getTags().stream().anyMatch(e -> e.contains(realFilter));
        }
        return !element.getText().contains(this.filterElement);
    }
}
