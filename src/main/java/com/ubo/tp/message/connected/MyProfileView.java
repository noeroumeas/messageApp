package com.ubo.tp.message.connected;

import com.ubo.tp.message.datamodel.User;
import com.ubo.tp.message.ihm.session.ISessionObserver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MyProfileView extends JPanel implements ISessionObserver {
    protected ArrayList<NavigatorObserver> observers;
    protected JLabel nameAndTag;
    protected JLabel avatar;
    public MyProfileView(){
        super(new GridBagLayout());
        this.observers = new ArrayList<>();
        this.initGoHome();
        this.initNameAndTag();
        this.initAvatar();
    }
    protected void initGoHome(){
        JButton goHomeButton = new JButton("Accueil");
        goHomeButton.addActionListener(e -> this.notifySwitchHome());

        this.add(goHomeButton, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));
    }
    protected void initNameAndTag(){
        this.nameAndTag = new JLabel("", JLabel.CENTER);

        this.add(this.nameAndTag, new GridBagConstraints(0, 1, 1, 1, 5, 2, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
    }

    protected void initAvatar(){
        this.avatar = new JLabel("", JLabel.CENTER);

        this.add(this.avatar, new GridBagConstraints(0, 6, 1, 1, 5, 5, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
    }

    protected void refreshNameAndTag(User user){
        this.nameAndTag.setText(user.getName() + " " + user.getUserTag());
    }

    protected void refreshAvatar(User user) {
        this.avatar.setIcon(new ImageIcon(user.getAvatarPath()));
    }

    protected void notifySwitchHome(){
        for(NavigatorObserver o : this.observers){
            o.switchHome();
        }
    }

    public void addObserver(NavigatorObserver observer){
        this.observers.add(observer);
    }
    @Override
    public void notifyLogin(User connectedUser) {
        this.refreshNameAndTag(connectedUser);
        this.refreshAvatar(connectedUser);
        this.revalidate();
        this.repaint();
    }

    @Override
    public void notifyLogout() {

    }
}
