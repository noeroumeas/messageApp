package main.java.com.ubo.tp.message.ihm.connectedhome;

import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.session.ISession;
import main.java.com.ubo.tp.message.ihm.session.ISessionObserver;

import javax.swing.*;
import java.awt.*;

public class AccountNavBarView extends JPanel implements ISessionObserver {
    ISession session;
    public AccountNavBarView(ISession session){
        super(new GridBagLayout());
        session.addObserver(this);
        this.session = session;
        JButton disconnectButton = new JButton("Se deconnecter");
        disconnectButton.addActionListener(e -> this.session.disconnect());
        this.add(disconnectButton, new GridBagConstraints(2, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));
    }

    @Override
    public void notifyLogin(User connectedUser) {
        ImageIcon avatar = new ImageIcon(connectedUser.getAvatarPath());
        JLabel nameAndTagAndAvatar = new JLabel("<html>" + connectedUser.getName() + "<br>" + connectedUser.getUserTag() + "</html>", avatar, JLabel.CENTER);
        this.add(nameAndTagAndAvatar, new GridBagConstraints(0, 0, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
    }

    @Override
    public void notifyLogout() {}
}
