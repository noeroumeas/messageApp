package main.java.com.ubo.tp.message.navigator;

import javax.swing.*;

public class NavigatorController implements NavigatorObserver {
    protected JFrame mainView;
    public NavigatorController(JFrame mainView){
        this.mainView = mainView;
    }
    @Override
    public void switchToLogin() {

    }

    @Override
    public void switchToRegister() {

    }
}
