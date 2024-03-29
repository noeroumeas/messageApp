package com.ubo.tp.message.userlist;

import com.ubo.tp.message.connected.NavigatorObserver;
import com.ubo.tp.message.core.database.IDatabase;
import com.ubo.tp.message.datamodel.User;
import com.ubo.tp.message.filter.FilterComponent;
import com.ubo.tp.message.filter.FilteredElementsModel;
import com.ubo.tp.message.filter.UnfilteredElementsModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UserListComponent extends JPanel implements UserListViewObserver {
    protected ArrayList<NavigatorObserver> observers;
    public UserListComponent(IDatabase db){
        super(new GridBagLayout());
        this.observers = new ArrayList<>();

        UserSearchView userSearchView = new UserSearchView();
        FilterUser filter = new FilterUser("");
        FilterComponent<User, String> filterComponent = new FilterComponent<>(userSearchView, filter);
        FilteredElementsModel<User> filteredUsers = filterComponent.getFilteredElementsModel();
        UnfilteredElementsModel<User> unfilteredUsers = filterComponent.getUnfilteredElementsModel();

        UserListController userListController = new UserListController(unfilteredUsers);

        db.addObserver(userListController);
        UserListView userListView = new UserListView(filteredUsers.getElements());

        filteredUsers.addObserver(userListView);
        userListView.addObserver(this);
        this.add(userSearchView, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
        this.add(userListView, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
    }

    public void addObserver(NavigatorObserver observer){
        this.observers.add(observer);
    }

    @Override
    public void switchHome() {
        //unused
    }

    @Override
    public void switchUserProfile(User user) {
        for(NavigatorObserver o : this.observers){
            o.switchUserProfile(user);
        }
    }

    @Override
    public void switchMyProfile() {
        //unused

    }
}
