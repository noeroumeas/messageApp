package main.java.com.ubo.tp.message.connected;

import main.java.com.ubo.tp.message.connected.userprofile.UserProfileComponent;
import main.java.com.ubo.tp.message.core.EntityManager;
import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.session.ISession;

import javax.swing.*;
import java.awt.*;

public class ConnectedComponent extends JPanel implements NavigatorObserver {
    protected MyProfileView myProfileView;
    protected ConnectedHomeView homeView;
    protected UserProfileComponent userProfileComponent;
    protected ISession session;
    public ConnectedComponent(ISession session, IDatabase db, EntityManager entityManager){
        super(new GridBagLayout());
        this.session = session;
        this.homeView = new ConnectedHomeView(session, db, entityManager);
        this.homeView.addObserver(this);
        this.myProfileView = new MyProfileView();
        session.addObserver(this.myProfileView);
        this.myProfileView.addObserver(this);
        this.userProfileComponent = new UserProfileComponent(entityManager, session);

        this.userProfileComponent.addObserver(this);
        NotificationSender notificationSender = new NotificationSender();
        session.addObserver(notificationSender);
        db.addObserver(notificationSender);
        this.switchHome();

    }

    @Override
    public void switchHome() {
        this.switchView(this.homeView);
    }

    @Override
    public void switchUserProfile(User u) {
        if(u.getUserTag().equals(this.session.getConnectedUser().getUserTag())){
            this.switchMyProfile();
            return;
        }
        this.userProfileComponent.setUser(u);
        this.switchView(this.userProfileComponent);
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
