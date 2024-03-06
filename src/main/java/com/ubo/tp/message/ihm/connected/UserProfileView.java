package main.java.com.ubo.tp.message.ihm.connected;

import main.java.com.ubo.tp.message.datamodel.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UserProfileView extends JPanel {
    ArrayList<NavigatorObserver> observers;
    public UserProfileView(){
        super(new GridBagLayout());
        this.observers = new ArrayList<>();
    }
    public void init(User user) {
        this.removeAll();
        this.initGoHome();
        this.initNameAndTag(user);
        this.initAvatar(user);
        this.revalidate();
        this.repaint();
    }
    protected void initGoHome(){
        JButton goHomeButton = new JButton("Accueil");
        goHomeButton.addActionListener(e -> this.notifySwitchHome());

        this.add(goHomeButton, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));
    }
    protected void initNameAndTag(User user){
        JLabel nameAndTag = new JLabel(user.getName() + " " + user.getUserTag(), JLabel.CENTER);

        this.add(nameAndTag, new GridBagConstraints(0, 1, 1, 1, 5, 2, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
    }

    protected void initAvatar(User user){
        ImageIcon avatar = new ImageIcon(user.getAvatarPath());
        JLabel avatarLabel = new JLabel(avatar);

        this.add(avatarLabel, new GridBagConstraints(0, 6, 1, 1, 5, 5, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
    }
    public void addObserver(NavigatorObserver observer){
        this.observers.add(observer);
    }
    protected void notifySwitchHome(){
        for(NavigatorObserver o : this.observers){
            o.switchHome();
        }
    }
}
