package main.java.com.ubo.tp.message.connected;

import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.session.ISession;
import main.java.com.ubo.tp.message.message.MessagingComponent;

import javax.swing.*;
import java.awt.*;

public class ConnectedComponent extends JPanel implements NavigatorObserver {
    protected MyProfileView myProfileView;
    protected ConnectedHomeView homeView;
    protected UserProfileView userProfile;
    protected UserProfileController userProfileController;
    protected MessagingComponent messagingComponent;
    public ConnectedComponent(ISession session, IDatabase db){
        super(new GridBagLayout());
        this.homeView = new ConnectedHomeView(session, db);
        this.homeView.addObserver(this);
        this.myProfileView = new MyProfileView();
        session.addObserver(this.myProfileView);
        this.myProfileView.addObserver(this);
        this.userProfile = new UserProfileView(session);
        this.userProfileController = new UserProfileController(session);
        this.userProfileController.addObserver(this);
        this.userProfile.addObserver(this.userProfileController);
        this.switchHome();
    }

    @Override
    public void switchHome() {
        this.switchView(this.homeView);
    }

    @Override
    public void switchUserProfile(User user) {
        this.userProfile.init(user);
        this.switchView(this.userProfile);
    }

    @Override
    public void switchMyProfile() {
        this.switchView(this.myProfileView);
    }

    protected void switchView(JPanel view){
        this.removeAll();
        this.add(view, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
        this.revalidate();
        this.repaint();
    }
}
