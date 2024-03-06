package main.java.com.ubo.tp.message.ihm.userlist;

import main.java.com.ubo.tp.message.datamodel.User;

import javax.swing.*;
import java.awt.*;

public class UserView extends JPanel {
    public UserView(User user){
        super(new GridBagLayout());
        this.initUserLabel(user);
    }

    protected void initUserLabel(User user){
        ImageIcon avatar = new ImageIcon(user.getAvatarPath());
        JLabel userLabel = new JLabel("<html>" + user.getName() + "<br>" + user.getUserTag() + "</html>", avatar, JLabel.LEFT);

        this.add(userLabel, new GridBagConstraints(0, 0, 1, 1, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
    }

    protected void initSu
}
