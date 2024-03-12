package main.java.com.ubo.tp.message.connected;

import main.java.com.ubo.tp.message.connected.accountnavbar.AccountNavbarComponent;
import main.java.com.ubo.tp.message.core.EntityManager;
import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.session.ISession;
import main.java.com.ubo.tp.message.message.MessagingComponent;
import main.java.com.ubo.tp.message.userlist.UserListComponent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Home view when user is connected
 */
public class ConnectedHomeView extends JPanel implements NavigatorObserver {
    protected ArrayList<NavigatorObserver> observers;

    public ConnectedHomeView(ISession session, IDatabase db, EntityManager entityManager){
        super(new GridBagLayout());

        this.observers = new ArrayList<>();
        this.initRightNavbar(session, db);
        this.initMainContent(db, session, entityManager);
    }

    protected void initRightNavbar(ISession session, IDatabase db) {
        JPanel rightNavbar = new JPanel(new GridBagLayout());

        AccountNavbarComponent accountNavbarComponent = new AccountNavbarComponent(session);
        accountNavbarComponent.addObserver(this);
        rightNavbar.add(accountNavbarComponent, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));

        UserListComponent userListComponent = new UserListComponent(db);
        userListComponent.addObserver(this);
        rightNavbar.add(userListComponent, new GridBagConstraints(0, 1, 1, 1, 1, 4, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));

        this.add(rightNavbar, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
    }
    public void addObserver(NavigatorObserver observer){
        this.observers.add(observer);
    }

    protected void initMainContent(IDatabase db, ISession session, EntityManager entityManager) {
        JPanel mainContent = new JPanel(new GridBagLayout());
        mainContent.add(new MessagingComponent(db, session, entityManager), new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));

        this.add(mainContent, new GridBagConstraints(1, 0, 1, 1, 5, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
    }

    @Override
    public void switchHome() {
        for(NavigatorObserver o : this.observers){
            o.switchHome();
        }
    }

    @Override
    public void switchUserProfile(User user) {
        for(NavigatorObserver o : this.observers){
            o.switchUserProfile(user);
        }
    }

    @Override
    public void switchMyProfile() {
        for(NavigatorObserver o : this.observers){
            o.switchMyProfile();
        }
    }
}
