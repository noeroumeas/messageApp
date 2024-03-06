package main.java.com.ubo.tp.message.login;

import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.ihm.session.ISession;

import javax.swing.*;
import java.awt.*;

public class LoginComponent extends JPanel implements NavigatorObserver {
    /**
     * vue de connexion
     */
    protected LoginView loginView;
    /**
     * vue de création de compte
     */
    protected RegisterView registerView;
    /**
     * controleur login
     */
    protected LoginController loginController;

    public LoginComponent(IDatabase db, ISession session){
        super(new GridBagLayout());

        this.loginView = new LoginView();
        this.registerView = new RegisterView();

        this.switchToLogin();

        this.loginController = new LoginController(db, session, this.registerView, this.loginView);
        this.loginController.addObserver(this);
    }

    /**
     * display login view
     */
    @Override
    public void switchToLogin() {
        this.changeView(this.loginView);
    }


    /**
     * display register view
     */
    @Override
    public void switchToRegister() {
        this.changeView(this.registerView);
    }

    protected void changeView(JPanel view) {
        this.removeAll();
        this.add(view, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
        this.revalidate();
        this.repaint();
    }
}
