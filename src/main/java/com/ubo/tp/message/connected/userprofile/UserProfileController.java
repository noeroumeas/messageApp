package main.java.com.ubo.tp.message.connected.userprofile;

import main.java.com.ubo.tp.message.connected.NavigatorObserver;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.session.ISession;

import java.util.ArrayList;

public class UserProfileController implements UserProfileViewObserver{
    ArrayList<NavigatorObserver> observers;
    ISession session;
    UserProfileModel userProfileModel;
    public UserProfileController(ISession session, UserProfileModel userProfileModel){
        this.observers = new ArrayList<>();
        this.session = session;
        this.userProfileModel = userProfileModel;
    }

    public void addObserver(NavigatorObserver observer){
        this.observers.add(observer);
    }

    @Override
    public void switchHome() {
        for(NavigatorObserver o : this.observers){
            o.switchHome();
        }
    }

    @Override
    public void switchUserProfile(User user) {

    }

    @Override
    public void switchMyProfile() {

    }

    @Override
    public void notifyUnfollow(User u) {
        this.session.getConnectedUser().removeFollowing(u.getUserTag());
    }

    @Override
    public void notifyFollow(User u) {
        this.session.getConnectedUser().addFollowing(u.getUserTag());
    }

    public void setUser(User u) {
        this.userProfileModel.setUser(u);
    }
}
