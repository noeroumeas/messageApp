package main.java.com.ubo.tp.message.ihm.userlist;

import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.connected.NavigatorObserver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UserListComponent extends JPanel implements NavigatorObserver {
    ArrayList<NavigatorObserver> observers;
    public UserListComponent(IDatabase db){
        super(new GridBagLayout());
        this.observers = new ArrayList<>();
        UserListSearchView userListSearchView = new UserListSearchView(db);
        userListSearchView.addObserver(this);
        this.add(userListSearchView, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
    }
    public void addObserver(NavigatorObserver observer){
        this.observers.add(observer);
    }

    @Override
    public void switchHome() {

    }

    @Override
    public void switchUserProfile(User user) {
        for(NavigatorObserver o : this.observers){
            o.switchUserProfile(user);
        }
    }

    @Override
    public void switchMyProfile() {

    }
}
