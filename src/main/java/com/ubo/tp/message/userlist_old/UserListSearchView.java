package main.java.com.ubo.tp.message.userlist_old;

import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.connected.NavigatorObserver;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UserListSearchView extends JPanel implements UsersModelObserver, NavigatorObserver {
    JPanel userList;
    int userListSize;

    protected List<UserListSearchViewObserver> observers;
    FilteredUsersModel filteredUsersModel;
    public UserListSearchView(FilteredUsersModel filteredUsers) {
        super(new GridBagLayout());
        this.filteredUsersModel = filteredUsers;
        this.observers = new ArrayList<>();
        filteredUsers.addObserver(this);
        this.initUserSearchBar();
        this.initUserViewList();

    }

    public void initUserSearchBar(){
        JPanel searchPanel = new JPanel(new GridBagLayout());
        JTextField searchBar = new JTextField(20);
        searchBar.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                warn();
            }
            public void removeUpdate(DocumentEvent e) {
                warn();
            }
            public void insertUpdate(DocumentEvent e) {
                warn();
            }

            public void warn() {
                notifyFilterChanged(searchBar.getText());
            }
        });
        searchBar.setPreferredSize(new Dimension(10000, 30));
        searchPanel.add(searchBar, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
        this.add(searchPanel, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
    }
    protected void initUserViewList(){
        JPanel userList = new JPanel(new GridBagLayout());
        JScrollPane userListScrollPane = new JScrollPane(userList);
        this.userList = userList;
        this.add(userListScrollPane, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
        refreshViewList();
    }

    protected void refreshViewList(){
        List<User> users = this.filteredUsersModel.getUsers();
        this.userListSize = 0;
        this.userList.removeAll();

        for(User u : users){
            this.addUserViewToList(u);
        }

        this.userList.revalidate();
        this.userList.repaint();
        this.revalidate();
        this.repaint();
    }

    protected void addUserViewToList(User u){
        UserView userView = new UserView(u);
        userView.addObserver(this);
        this.userList.add(userView, new GridBagConstraints(0, this.userListSize++, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5,0,5,0), 0, 0));
    }
    public void addObserver(UserListSearchViewObserver observer){
        this.observers.add( observer);
    }

    public void notifyFilterChanged(String filter){
        for(UserListSearchViewObserver o : this.observers){
            o.notifyFilterChanged(filter);
        }
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
    public void usersChanged(List<User> users) {
        this.refreshViewList();
    }

    @Override
    public void userAdded(User u) {
        this.refreshViewList();
    }
}
