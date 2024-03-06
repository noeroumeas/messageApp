package main.java.com.ubo.tp.message.ihm.connected;

import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.session.ISession;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class ConnectedComponent extends JPanel implements NavigatorObserver {
    protected UserProfileView userProfileView;
    protected ConnectedHomeView homeView;
    public ConnectedComponent(ISession session, IDatabase db){
        super(new GridBagLayout());
        this.homeView = new ConnectedHomeView(session, db);
        this.homeView.addObserver(this);
        this.userProfileView = new UserProfileView(session);
        this.userProfileView.addObserver(this);
        this.switchHome();
    }

    @Override
    public void switchHome() {
        this.switchView(this.homeView);
    }

    @Override
    public void switchUserProfile() {
        this.switchView(this.userProfileView);
    }

    protected void switchView(JPanel view){
        this.removeAll();
        this.add(view, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
        this.revalidate();
        this.repaint();
    }
}
