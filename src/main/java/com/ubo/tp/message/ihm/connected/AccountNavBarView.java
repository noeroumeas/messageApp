package main.java.com.ubo.tp.message.ihm.connected;

import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.session.ISession;
import main.java.com.ubo.tp.message.ihm.session.ISessionObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class AccountNavBarView extends JPanel implements ISessionObserver {
    ArrayList<NavigatorObserver> observers;
    public AccountNavBarView(ISession session){
        super(new GridBagLayout());
        this.observers = new ArrayList<>();
        session.addObserver(this);
        JButton disconnectButton = new JButton("Se deconnecter");
        disconnectButton.addActionListener(e -> session.disconnect());
        this.add(disconnectButton, new GridBagConstraints(2, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));
    }
    public void addObserver(NavigatorObserver observer){
        this.observers.add(observer);
    }
    @Override
    public void notifyLogin(User connectedUser) {
        ImageIcon avatar = new ImageIcon(connectedUser.getAvatarPath());
        JLabel nameAndTagAndAvatar = new JLabel("<html>" + connectedUser.getName() + "<br>" + connectedUser.getUserTag() + "</html>", avatar, JLabel.CENTER);
        nameAndTagAndAvatar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                notifySwitchUserProfile();
            }
        });
        this.add(nameAndTagAndAvatar, new GridBagConstraints(0, 0, 1, 1, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
    }

    protected void notifySwitchUserProfile() {
        for(NavigatorObserver o : this.observers){
            o.switchUserProfile();
        }
    }
    @Override
    public void notifyLogout() {}
}
