package main.java.com.ubo.tp.message.ihm.connectedhome;

import main.java.com.ubo.tp.message.ihm.session.ISession;

import javax.swing.*;
import java.awt.*;

/**
 * Home view when user is connected
 */
public class ConnectedHomeView extends JPanel {
    public ConnectedHomeView(ISession session){
        super(new GridBagLayout());
        this.initLeftNavBar(session);
        this.initMainContent();
    }

    protected void initLeftNavBar(ISession session) {
        JPanel leftNavBar = new JPanel(new GridBagLayout());

        AccountNavBarView accountNavBarView = new AccountNavBarView(session);
        leftNavBar.add(accountNavBarView, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
        JPanel test = new JPanel(new GridBagLayout());
        test.add(new JButton("users"), new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
        leftNavBar.add(test, new GridBagConstraints(0, 1, 1, 1, 1, 4, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));

        this.add(leftNavBar, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
    }

    protected void initMainContent() {
        JPanel mainContent = new JPanel(new GridBagLayout());
        mainContent.add(new JButton("Main content"), new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));

        this.add(mainContent, new GridBagConstraints(1, 0, 1, 1, 4, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
    }
}
