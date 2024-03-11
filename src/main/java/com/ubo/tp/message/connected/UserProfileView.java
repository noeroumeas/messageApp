package main.java.com.ubo.tp.message.connected;

import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.session.ISession;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UserProfileView extends JPanel {
    ArrayList<UserProfileViewObserver> observers;
    ISession session;
    JButton followButton;
    ActionListener followButtonActionListener;
    public UserProfileView(ISession session){
        super(new GridBagLayout());
        this.session = session;
        this.observers = new ArrayList<>();
    }
    public void init(User user) {
        this.removeAll();
        this.initGoHome();
        this.initNameAndTag(user);
        this.initAvatar(user);
        this.initFollowButton(user);
        this.revalidate();
        this.repaint();
    }

    protected void initGoHome(){
        JButton goHomeButton = new JButton("Accueil");
        goHomeButton.addActionListener(e -> this.notifySwitchHome());

        this.add(goHomeButton, new GridBagConstraints(0, 0, 1, 1, 1, 2, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));
    }

    private void initFollowButton(User user) {
        this.followButton = new JButton();

        this.add(this.followButton, new GridBagConstraints(0, 3, 1, 1, 1, 2, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));
        refreshFollowButton(user);
    }

    private void refreshFollowButton(User user) {
        this.followButton.removeActionListener(this.followButtonActionListener);
        if(session.getConnectedUser().isFollowing(user)){
            this.followButton.setText("unfollow");
             this.followButtonActionListener = e -> {
                 this.notifyUnfollow(user);
                 this.refreshFollowButton(user);
            };


        } else {
            this.followButton.setText("follow");
            this.followButtonActionListener = e -> {
                this.notifyFollow(user);
                this.refreshFollowButton(user);
            };

        }
        this.followButton.addActionListener(this.followButtonActionListener);
        this.revalidate();
        this.repaint();
    }

    private void notifyFollow(User user) {
        for(UserProfileViewObserver o : this.observers){
            o.notifyFollow(user);
        }
    }

    private void notifyUnfollow(User user) {
        for(UserProfileViewObserver o : this.observers){
            o.notifyUnfollow(user);
        }
    }

    protected void initNameAndTag(User user){
        JLabel nameAndTag = new JLabel(user.getName() + " " + user.getUserTag(), JLabel.CENTER);

        this.add(nameAndTag, new GridBagConstraints(0, 1, 1, 1, 5, 2, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
    }

    protected void initAvatar(User user){
        ImageIcon avatar = new ImageIcon(user.getAvatarPath());
        JLabel avatarLabel = new JLabel(avatar);

        this.add(avatarLabel, new GridBagConstraints(0, 4, 1, 1, 5, 2, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
    }
    public void addObserver(UserProfileViewObserver observer){
        this.observers.add(observer);
    }
    protected void notifySwitchHome(){
        for(NavigatorObserver o : this.observers){
            o.switchHome();
        }
    }
}
