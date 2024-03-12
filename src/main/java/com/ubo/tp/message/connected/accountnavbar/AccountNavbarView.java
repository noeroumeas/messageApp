package main.java.com.ubo.tp.message.connected.accountnavbar;

import main.java.com.ubo.tp.message.connected.NavigatorObserver;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.session.ISessionObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class AccountNavbarView extends JPanel implements ISessionObserver {
    protected ArrayList<AccountNavbarViewObserver> observers;
    protected ImageIcon avatar;
    protected JLabel nameAndTagAndAvatar;
    public AccountNavbarView(){
        super(new GridBagLayout());
        this.observers = new ArrayList<>();
        this.initDisconnectButton();
        this.initAccountInfo();
    }

    protected void initDisconnectButton() {
        JButton disconnectButton = new JButton("Se deconnecter");
        disconnectButton.addActionListener(e -> notifyDisconnect());
        this.add(disconnectButton, new GridBagConstraints(2, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));

    }

    protected void initAccountInfo() {

        this.avatar = new ImageIcon();
        this.nameAndTagAndAvatar = new JLabel("", avatar, JLabel.CENTER);
        nameAndTagAndAvatar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                notifySwitchMyProfile();
            }
        });

        this.add(nameAndTagAndAvatar, new GridBagConstraints(0, 0, 1, 1, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(10,0,10,0), 0, 0));
    }

    @Override
    public void notifyLogin(User u) {
        this.refreshAccountInfo(u);
    }
    protected void refreshAccountInfo(User u){
        ImageIcon avatar = new ImageIcon(u.getAvatarPath());
        this.avatar.setImage(avatar.getImage());

        this.nameAndTagAndAvatar.setText("<html>" + u.getName() + "<br>" + u.getUserTag() + "</html>");
        this.revalidate();
        this.repaint();
    }
    protected void notifyDisconnect() {
        for(AccountNavbarViewObserver o : this.observers){
            o.notifyDisconnect();
        }
    }

    public void addObserver(AccountNavbarViewObserver observer){
        this.observers.add(observer);
    }
    protected void notifySwitchMyProfile() {
        for(NavigatorObserver o : this.observers){
            o.switchMyProfile();
        }
    }
    @Override
    public void notifyLogout() {}
}
