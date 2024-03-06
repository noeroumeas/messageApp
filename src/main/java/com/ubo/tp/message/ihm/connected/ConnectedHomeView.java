package main.java.com.ubo.tp.message.ihm.connected;

import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.session.ISession;
import main.java.com.ubo.tp.message.ihm.userlist.UserListComponent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Home view when user is connected
 */
public class ConnectedHomeView extends JPanel implements NavigatorObserver{
    ArrayList<NavigatorObserver> observers;
    public ConnectedHomeView(ISession session, IDatabase db){
        super(new GridBagLayout());
        this.observers = new ArrayList<>();
        this.initLeftNavBar(session, db);
        this.initMainContent();
    }

    protected void initLeftNavBar(ISession session, IDatabase db) {
        JPanel leftNavBar = new JPanel(new GridBagLayout());

        AccountNavBarView accountNavBarView = new AccountNavBarView(session);
        accountNavBarView.addObserver(this);
        leftNavBar.add(accountNavBarView, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
        UserListComponent userListComponent = new UserListComponent(db);
        userListComponent.addObserver(this);
        leftNavBar.add(userListComponent, new GridBagConstraints(0, 1, 1, 1, 1, 4, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));

        this.add(leftNavBar, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
    }
    public void addObserver(NavigatorObserver observer){
        this.observers.add(observer);
    }

    protected void initMainContent() {
        JPanel mainContent = new JPanel(new GridBagLayout());
        mainContent.add(new JButton("Main content"), new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));

        this.add(mainContent, new GridBagConstraints(1, 0, 1, 1, 4, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
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
