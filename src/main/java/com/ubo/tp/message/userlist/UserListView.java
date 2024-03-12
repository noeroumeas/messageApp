package main.java.com.ubo.tp.message.userlist;

import main.java.com.ubo.tp.message.connected.NavigatorObserver;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.filter.FilterElementsModelObserver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UserListView extends JPanel implements NavigatorObserver, FilterElementsModelObserver<User> {
    protected int userListSize;
    protected JPanel userList;

    protected List<UserListViewObserver> observers;
    public UserListView(List<User> users){
        super(new GridBagLayout());
        this.observers = new ArrayList<>();
        this.initUserViewList(users);
    }
    protected void initUserViewList(List<User> users){

        this.userList = new JPanel(new GridBagLayout());
        JScrollPane userListScrollPane = new JScrollPane(this.userList);
        userListScrollPane.getVerticalScrollBar().setUnitIncrement(10);
        this.add(userListScrollPane, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
        refreshViewList(users);
    }

    protected void refreshViewList(List<User> users){
        this.userListSize = 0;
        this.userList.removeAll();
        for(User u : users){
            this.addUserViewToList(u);
        }

        this.userList.revalidate();
        this.userList.repaint();
    }

    protected void addUserViewToList(User u){
        UserView userView = new UserView(u);
        userView.addObserver(this);
        this.userList.add(userView, new GridBagConstraints(0, this.userListSize++, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5,0,5,0), 0, 0));
    }
    public void addObserver(UserListViewObserver observer){
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

    @Override
    public void elementsChanged(List<User> users) {
        this.refreshViewList(users);
    }

    @Override
    public void elementAdded(User u) {
        this.addUserViewToList(u);
    }

    @Override
    public void elementRemoved(User u) {

    }
}
