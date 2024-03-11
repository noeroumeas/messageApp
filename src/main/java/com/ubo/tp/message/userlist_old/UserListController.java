package main.java.com.ubo.tp.message.userlist_old;

import main.java.com.ubo.tp.message.connected.NavigatorObserver;
import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.core.database.IDatabaseObserver;
import main.java.com.ubo.tp.message.datamodel.Message;
import main.java.com.ubo.tp.message.datamodel.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class UserListController implements IDatabaseObserver, UserListSearchViewObserver, NavigatorObserver {
    protected IDatabase database;
    protected FilteredUsersModel filteredUsers;
    protected UserListSearchView userListSearchView;
    protected String filter;
    public UserListController(IDatabase database, FilteredUsersModel filteredUsers, UserListSearchView userListSearchView) {
        this.database = database;
        this.filteredUsers = filteredUsers;
        this.filter = "";
        this.userListSearchView = userListSearchView;
        this.userListSearchView.addObserver(this);
        this.database.addObserver(this);
        refreshViewList();
    }

    protected void refreshViewList(){
        Set<User> users = this.database.getUsers();
        List<User> filteredUsers = new ArrayList<>();
        Iterator<User> usersIterator = users.stream().iterator();
        while(usersIterator.hasNext()){
            User u = usersIterator.next();
            if(this.isUserValid(this.filter, u)) {
                filteredUsers.add(u);
            }
        }
        this.filteredUsers.setUsers(filteredUsers);
    }

    protected Boolean isUserValid(String searchedElement, User u){
        if(searchedElement.isEmpty()){
            return true;
        }
        return u.getUserTag().contains(searchedElement) || u.getName().contains(searchedElement);
    }

    @Override
    public void switchHome() {

    }

    @Override
    public void switchUserProfile(User user) {

    }

    @Override
    public void switchMyProfile() {

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
        refreshViewList();
    }

    @Override
    public void notifyUserDeleted(User deletedUser) {
        refreshViewList();
    }

    @Override
    public void notifyUserModified(User modifiedUser) {
        refreshViewList();
    }

    @Override
    public void notifyFilterChanged(String filter) {
        this.filter = filter;
        refreshViewList();
    }
}
