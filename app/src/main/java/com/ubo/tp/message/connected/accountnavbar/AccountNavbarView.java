package com.ubo.tp.message.connected.accountnavbar;

import com.ubo.tp.message.connected.NavigatorObserver;
import com.ubo.tp.message.datamodel.User;
import com.ubo.tp.message.ihm.session.ISessionObserver;
import com.ubo.tp.message.utils.ImagePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class AccountNavbarView extends JPanel implements ISessionObserver {
    protected ArrayList<AccountNavbarViewObserver> observers;
    protected JPanel avatar;
    protected JLabel nameAndTag;

    public AccountNavbarView(){
        super(new GridBagLayout());
        this.observers = new ArrayList<>();
        this.initDisconnectButton();
        this.initAccountInfo();
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                notifySwitchMyProfile();
            }
        });
    }

    protected void initDisconnectButton() {
        JButton disconnectButton = new JButton("Se deconnecter");
        disconnectButton.addActionListener(e -> notifyDisconnect());
        this.add(disconnectButton, new GridBagConstraints(2, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));

    }

    protected void initAccountInfo() {
        this.nameAndTag = new JLabel("", JLabel.CENTER);
        this.nameAndTag.setFont(new Font("Arial", Font.PLAIN, 18));
        this.nameAndTag.setMaximumSize(new Dimension(10000, 60));
        this.avatar = new JPanel(new GridBagLayout());

        this.add(nameAndTag, new GridBagConstraints(1, 0, 1, 1, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(10,0,10,0), 0, 0));
        this.add(avatar, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(10,0,10,0), 0, 0));
    }

    @Override
    public void notifyLogin(User u) {
        this.refreshAccountInfo(u);
    }
    protected void refreshAccountInfo(User u){
        File avatarFile = new File(u.getAvatarPath());
        if(avatarFile.exists()) {
            JPanel avatarPanel = new ImagePanel(avatarFile, new Dimension(50, 50));
            this.avatar.removeAll();
            this.avatar.add(avatarPanel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        }
        this.nameAndTag.setText("<html>" + u.getName() + "<br>" + u.getUserTag() + "</html>");
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
