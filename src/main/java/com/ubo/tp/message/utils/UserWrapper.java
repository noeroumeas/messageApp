package main.java.com.ubo.tp.message.utils;

import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.userlist.UserFilterable;

import java.util.ArrayList;
import java.util.Set;

public class UserWrapper {
    public static UserFilterable userToUserFilterable(User user){
        return new UserFilterable(user);
    }

    public static ArrayList<UserFilterable> usersToUsersFilterable(Set<User> users){
        ArrayList<UserFilterable> usersFilterable = new ArrayList<>();
        for(User u : users){
            usersFilterable.add(new UserFilterable(u));
        }
        return usersFilterable;
    }
}
