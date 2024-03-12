package main.java.com.ubo.tp.message.connected.userprofile;

import main.java.com.ubo.tp.message.datamodel.User;

import java.util.ArrayList;

public class UserProfileModel {
    protected ArrayList<UserProfileModelObserver> observers;
    protected User currentUser;
    public UserProfileModel() {
        this.observers = new ArrayList<>();
    }

    public void setUser(User u){
        this.currentUser = u;
        this.notifyUserChanged(u);
    }

    protected void notifyUserChanged(User u) {
        for(UserProfileModelObserver o : this.observers){
            o.notifyUserChanged(u);
        }
    }

    public void addObserver(UserProfileModelObserver observer){
        this.observers.add(observer);
    }
}
