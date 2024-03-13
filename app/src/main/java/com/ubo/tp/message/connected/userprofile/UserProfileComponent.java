package com.ubo.tp.message.connected.userprofile;

import com.ubo.tp.message.connected.NavigatorObserver;
import com.ubo.tp.message.core.EntityManager;
import com.ubo.tp.message.datamodel.User;
import com.ubo.tp.message.ihm.session.ISession;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UserProfileComponent extends JPanel implements NavigatorObserver {
    protected ArrayList<NavigatorObserver> observers;
    protected UserProfileController userProfileController;
    public UserProfileComponent(EntityManager entityManager, ISession session) {
        super(new GridBagLayout());
        this.observers = new ArrayList<>();
        UserProfileView userProfileView = new UserProfileView(session);
        UserProfileModel userProfileModel = new UserProfileModel();
        this.userProfileController = new UserProfileController(entityManager, session, userProfileModel);

        userProfileModel.addObserver(userProfileView);
        userProfileController.addObserver(this);
        userProfileView.addObserver(userProfileController);
        this.add(userProfileView, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
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
    public void addObserver(NavigatorObserver observer){
        this.observers.add(observer);
    }

    public void setUser(User u) {
        this.userProfileController.setUser(u);
    }
}
