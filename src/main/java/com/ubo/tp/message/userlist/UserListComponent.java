package main.java.com.ubo.tp.message.userlist;

import main.java.com.ubo.tp.message.connected.NavigatorObserver;
import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.filter.FilterComponent;
import main.java.com.ubo.tp.message.filter.FilteredElementsModel;
import main.java.com.ubo.tp.message.filter.UnfilteredElementsModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UserListComponent extends JPanel implements UserListViewObserver {
    ArrayList<NavigatorObserver> observers;
    public UserListComponent(IDatabase db){
        super(new GridBagLayout());
        this.observers = new ArrayList<>();

        UserSearchView userSearchView = new UserSearchView();
        FilterComponent<UserFilterable> filterComponent = new FilterComponent<>(userSearchView);
        FilteredElementsModel<UserFilterable> filteredUsers = filterComponent.getFilteredElementsModel();
        UnfilteredElementsModel<UserFilterable> unfilteredUsers = filterComponent.getUnfilteredElementsModel();

        UserListController userListController = new UserListController(unfilteredUsers);

        UserListView userListView = new UserListView(filteredUsers.getElements());

        db.addObserver(userListController);
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
