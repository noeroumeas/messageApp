package com.ubo.tp.message.connected.accountnavbar;

import com.ubo.tp.message.connected.NavigatorObserver;
import com.ubo.tp.message.datamodel.User;
import com.ubo.tp.message.ihm.session.ISession;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AccountNavbarComponent extends JPanel implements NavigatorObserver {
    protected ArrayList<NavigatorObserver> observers;
    public AccountNavbarComponent(ISession session) {
        super(new GridBagLayout());
        this.observers = new ArrayList<>();
        AccountNavbarView accountNavBarView = new AccountNavbarView();
        AccountNavbarController accountNavbarController = new AccountNavbarController(session);
        accountNavBarView.addObserver(accountNavbarController);
        accountNavbarController.addObserver(this);
        session.addObserver(accountNavBarView);
        this.add(accountNavBarView, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
    }

    @Override
    public void switchHome() {
        //unused
    }

    @Override
    public void switchUserProfile(User user) {
        //unused

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
