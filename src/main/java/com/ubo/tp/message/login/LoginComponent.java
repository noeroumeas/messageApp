package main.java.com.ubo.tp.message.login;

import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.ihm.session.ISession;
import main.java.com.ubo.tp.message.navigator.NavigatorObserver;

import javax.swing.*;
import java.awt.*;

public class LoginComponent implements NavigatorObserver {
    protected JPanel mainView;
    protected LoginView loginView;
    protected RegisterView registerView;
    protected LoginController loginController;
    protected ISession session;
    public LoginComponent(IDatabase db){
        this.mainView = new JPanel();
        this.mainView.setLayout(new GridBagLayout());

        this.loginView = new LoginView();
        this.registerView = new RegisterView();

        this.mainView.add(this.registerView, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
        this.mainView.add(this.loginView, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
        this.switchToLogin();

        this.loginController = new LoginController(db, this.registerView, this.loginView);
        this.loginController.addObserver(this);
    }



    public JPanel getMainView() {
        return mainView;
    }

    @Override
    public void switchToLogin() {
        this.loginView.setVisible(true);
        this.registerView.setVisible(false);
    }

    @Override
    public void switchToRegister() {
        this.loginView.setVisible(false);
        this.registerView.setVisible(true);
    }
}
