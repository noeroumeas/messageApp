package main.java.com.ubo.tp.message.ihm.userlist;

import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.core.database.IDatabaseObserver;
import main.java.com.ubo.tp.message.datamodel.Message;
import main.java.com.ubo.tp.message.datamodel.User;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Iterator;
import java.util.Set;

public class UserListSearchView extends JPanel implements IDatabaseObserver {
    JPanel userList;
    int userListSize;
    public UserListSearchView(IDatabase database) {
        super(new GridBagLayout());
        this.initUserSearchBar();
        this.initUserViewList(database.getUsers());
        database.addObserver(this);;

    }
    public void initUserSearchBar(){
        JPanel searchPanel = new JPanel(new GridBagLayout());
        JTextField searchBar = new JTextField(20);
        searchPanel.add(searchBar, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
        this.add(searchPanel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
    }
    protected void initUserViewList(Set<User> users){
        JPanel userList = new JPanel(new GridBagLayout());
        this.userList = userList;
        Iterator<User> usersIterator = users.stream().iterator();
        while(usersIterator.hasNext()){
            User u = usersIterator.next();
            this.addUserViewToList(u);
        }
        this.add(userList, new GridBagConstraints(0, 1, 1, 1, 1, 9, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
    }

    protected void addUserViewToList(User u){
        UserView userView = new UserView(u);
        this.userList.add(userView, new GridBagConstraints(0, this.userListSize++, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
        this.revalidate();
        this.repaint();
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
        this.addUserViewToList(addedUser);
    }

    @Override
    public void notifyUserDeleted(User deletedUser) {

    }

    @Override
    public void notifyUserModified(User modifiedUser) {

    }
}
