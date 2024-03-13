package com.ubo.tp.message.userlist;

import com.ubo.tp.message.connected.NavigatorObserver;
import com.ubo.tp.message.datamodel.User;
import com.ubo.tp.message.utils.ImagePanel;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class UserView extends JPanel {
    protected ArrayList<NavigatorObserver> observers;
    protected User user;
    public UserView(User user){
        super(new GridBagLayout());
        this.observers = new ArrayList<>();
        this.user = user;
        this.initUserLabel(user);
        this.initSeeProfile(user);
    }

    protected void initUserLabel(User user){
        File avatarFile = new File(user.getAvatarPath());
        JPanel avatarPanel = new ImagePanel(avatarFile, new Dimension(50,50));

        JLabel userLabel = new JLabel("<html>" + user.getName() + "<br>" + user.getUserTag() + "</html>", JLabel.CENTER);

        this.add(avatarPanel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
        this.add(userLabel, new GridBagConstraints(1, 0, 1, 1, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
    }

    protected void initSeeProfile(User user){
        JButton buttonSeeProfile = new JButton("profil");
        buttonSeeProfile.addActionListener(e -> notifySwitchUserProfile(user));
        this.add(buttonSeeProfile, new GridBagConstraints(3, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));
    }


    public void addObserver(NavigatorObserver observer){
        this.observers.add(observer);
    }
    protected void notifySwitchUserProfile(User user) {
        for(NavigatorObserver o : this.observers){
            o.switchUserProfile(user);
        }
    }

    public User getUser() {
        return user;
    }
}
