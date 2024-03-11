package main.java.com.ubo.tp.message.userlist;

import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.filter.Filterable;

public class UserFilterable extends User implements Filterable {

    public UserFilterable(User user) {
        super(user.getUuid(), user.getUserTag(), user.getUserPassword(), user.getName(), user.getFollows(), user.getAvatarPath());
    }

    @Override
    public boolean contains(String filter) {
        if(filter.isEmpty()) {
            return true;
        }
        return mUserTag.contains(filter) || mName.contains(filter);
    }
}
