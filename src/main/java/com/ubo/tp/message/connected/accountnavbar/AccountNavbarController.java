package main.java.com.ubo.tp.message.connected.accountnavbar;

import main.java.com.ubo.tp.message.connected.NavigatorObserver;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.session.ISession;

import java.util.ArrayList;

public class AccountNavbarController implements AccountNavbarViewObserver {
    protected ArrayList<NavigatorObserver> observers;
    protected ISession session;
    public AccountNavbarController(ISession session) {
        this.observers = new ArrayList<>();
        this.session = session;
    }

    @Override
    public void notifyDisconnect(){
        this.session.disconnect();
    }

    @Override
    public void switchHome() {

    }

    @Override
    public void switchUserProfile(User user) {

    }

    @Override
    public void switchMyProfile() {
        for(NavigatorObserver o : this.observers){
            o.switchMyProfile();
        }
    }
    public void addObserver(NavigatorObserver observer){
        this.observers.add(observer);
    }
}
