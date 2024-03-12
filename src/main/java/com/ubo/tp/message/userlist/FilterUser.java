package main.java.com.ubo.tp.message.userlist;

import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.filter.Filter;

public class FilterUser extends Filter<User, String> {
    public FilterUser(String filterElement) {
        super(filterElement);
    }

    @Override
    public boolean isFiltered(User element) {
        if(this.filterElement.isEmpty()) {
            return false;
        }
        return !(element.getUserTag().contains(this.filterElement) || element.getName().contains(this.filterElement));
    }
}
