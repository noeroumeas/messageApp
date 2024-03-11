package main.java.com.ubo.tp.message.userlist_old;

import main.java.com.ubo.tp.message.datamodel.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FilteredUsersModel {
    protected List<UsersModelObserver> observers;
    protected List<User> users;

    public FilteredUsersModel(){
        this.observers = new ArrayList<>();
        this.users = new ArrayList<>();
    }


    public FilteredUsersModel(Set<User> users) {
        this.observers = new ArrayList<>();
        this.users = new ArrayList<>(users);
    }

    public void add(User u){
        this.users.add(u);
        notifyUserAdded(u);
    }

    public List<User> getUsers(){
        return new ArrayList<>(this.users);
    }

    public void setUsers(List<User> users){
        this.users = new ArrayList<>(users);
        this.notifyUsersChanged();
    }

    public void addObserver(UsersModelObserver observer){
        this.observers.add(observer);
    }

    protected void notifyUserAdded(User u){
        for(UsersModelObserver o : this.observers){
            o.userAdded(u);
        }
    }

    protected void notifyUsersChanged(){
        for(UsersModelObserver o : this.observers){
            o.usersChanged(new ArrayList<>(this.users));
        }
    }

}
