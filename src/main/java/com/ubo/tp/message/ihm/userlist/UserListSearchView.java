package main.java.com.ubo.tp.message.ihm.userlist;

import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.core.database.IDatabaseObserver;
import main.java.com.ubo.tp.message.datamodel.Message;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.connected.NavigatorObserver;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class UserListSearchView extends JPanel implements IDatabaseObserver, NavigatorObserver {
    JPanel userList;
    int userListSize;
    ArrayList<NavigatorObserver> observers;
    IDatabase database;
    String userFilter = "";
    public UserListSearchView(IDatabase database) {
        super(new GridBagLayout());
        this.observers = new ArrayList<>();
        this.database = database;
        this.initUserSearchBar();
        this.initUserViewList();
        database.addObserver(this);

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
                userFilter = searchBar.getText();
                refreshViewList();
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
        Set<User> users = this.database.getUsers();
        this.userListSize = 0;
        this.userList.removeAll();
        Iterator<User> usersIterator = users.stream().iterator();
        while(usersIterator.hasNext()){
            User u = usersIterator.next();
            if(this.isUserValid(this.userFilter, u)) {
                this.addUserViewToList(u);
            }
        }
        this.userList.revalidate();
        this.userList.repaint();
        this.revalidate();
        this.repaint();

    }

    protected Boolean isUserValid(String searchedElement, User u){
        if(searchedElement.isEmpty()){
            return true;
        }
        return u.getUserTag().contains(searchedElement) || u.getName().contains(searchedElement);
    }

    protected void addUserViewToList(User u){
        UserView userView = new UserView(u);
        userView.addObserver(this);
        this.userList.add(userView, new GridBagConstraints(0, this.userListSize++, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5,0,5,0), 0, 0));
    }
    public void addObserver(NavigatorObserver observer){
        this.observers.add(observer);
    }

    @Override
    public void notifyMessageAdded(Message addedMessage) {

    }

    @Override
    public void notifyMessageDeleted(Message deletedMessage) {

    }

    @Override
    public void notifyMessageModified(Message modifiedMessage) {

    }

    @Override
    public void notifyUserAdded(User addedUser) {
        this.refreshViewList();
    }

    @Override
    public void notifyUserDeleted(User deletedUser) {

    }

    @Override
    public void notifyUserModified(User modifiedUser) {

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
