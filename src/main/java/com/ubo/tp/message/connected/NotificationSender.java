package com.ubo.tp.message.connected;

import com.ubo.tp.message.core.database.IDatabaseObserver;
import com.ubo.tp.message.datamodel.Message;
import com.ubo.tp.message.datamodel.User;
import com.ubo.tp.message.ihm.session.ISessionObserver;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class NotificationSender implements IDatabaseObserver, ISessionObserver {
    protected Set<String> followedUsers;
    protected SystemTray tray;
    protected TrayIcon trayIcon;
    public NotificationSender() {
        this.followedUsers = new HashSet<>();
        this.tray = SystemTray.getSystemTray();
        Image image = new ImageIcon("src/main/resources/images/logo_20.png").getImage();

        this.trayIcon = new TrayIcon(image, "New Message");
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("New message");


        try {
            tray.add(trayIcon);
        } catch (AWTException ignored) {
        }

    }

    @Override
    public void notifyMessageAdded(Message addedMessage){
        boolean isSenderFollowed = this.followedUsers.contains(addedMessage.getSender().getUserTag());
        if(!isSenderFollowed){
            return;
        }
        String messageText = addedMessage.getText();
        String messageTextInNotification;
        if (messageText.length() < 50){
            messageTextInNotification = messageText;
        } else {
            messageTextInNotification = messageText.substring(0,50) + " ...";
        }

        trayIcon.displayMessage(addedMessage.getSender().getUserTag(), messageTextInNotification, TrayIcon.MessageType.INFO);
    }

    @Override
    public void notifyMessageDeleted(Message deletedMessage) {
        //unused

    }

    @Override
    public void notifyMessageModified(Message modifiedMessage) {
        //unused

    }

    @Override
    public void notifyUserAdded(User addedUser) {
        //unused

    }

    @Override
    public void notifyUserDeleted(User deletedUser) {
        //unused

    }

    @Override
    public void notifyUserModified(User modifiedUser) {
        this.followedUsers = modifiedUser.getFollows();
    }

    @Override
    public void notifyLogin(User connectedUser) {
        this.followedUsers = connectedUser.getFollows();
    }

    @Override
    public void notifyLogout() {
        this.followedUsers = new HashSet<>();
    }
}
