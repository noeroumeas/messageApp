package main.java.com.ubo.tp.message.connected.userprofile;

import main.java.com.ubo.tp.message.connected.NavigatorObserver;
import main.java.com.ubo.tp.message.core.EntityManager;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.session.ISession;

import java.util.ArrayList;

public class UserProfileController implements UserProfileViewObserver{
    protected ArrayList<NavigatorObserver> observers;
    protected ISession session;
    protected UserProfileModel userProfileModel;
    protected EntityManager entityManager;
    public UserProfileController(EntityManager entityManager, ISession session, UserProfileModel userProfileModel){
        this.observers = new ArrayList<>();
        this.session = session;
        this.userProfileModel = userProfileModel;
        this.entityManager = entityManager;
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
        User sessionUser = this.session.getConnectedUser();
        sessionUser.removeFollowing(u.getUserTag());
        this.entityManager.writeUserFile(sessionUser);
    }

    @Override
    public void notifyFollow(User u) {
        User sessionUser = this.session.getConnectedUser();
        sessionUser.addFollowing(u.getUserTag());
        this.entityManager.writeUserFile(sessionUser);
    }

    public void setUser(User u) {
        this.userProfileModel.setUser(u);
    }
}
