package main.java.com.ubo.tp.message.login;

import main.java.com.ubo.tp.message.login.LoginViewObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoginView extends JPanel {
    /**
     * Observateurs de la vue
     */
    ArrayList<LoginViewObserver> observers = new ArrayList<>();

    public LoginView(){
        super(new GridBagLayout());
        Dimension textFieldDimensions = new Dimension(100,30);

        JLabel nameLabel = new JLabel("Nom : ");
        JTextField nameTextField = new JTextField(20);
        nameTextField.setSize(textFieldDimensions);

        JLabel tagLabel = new JLabel("Tag : ");
        JTextField tagTextField = new JTextField(20);
        tagTextField.setSize(textFieldDimensions);

        JButton loginButton = new JButton("Se connecter");
        JButton registerButton = new JButton("Pas encore inscrit ?");

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notifySwitchToRegister();
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notifyLogin(tagTextField.getText(), nameTextField.getText());
            }
        });

        this.add(nameLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));
        this.add(nameTextField, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));
        this.add(tagLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));
        this.add(tagTextField, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));
        this.add(registerButton, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));
        this.add(loginButton, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));

    }

    /**
     * ajouter un observateur à la vue
     * @param observer
     */
    public void addObserver(LoginViewObserver observer){
        this.observers.add(observer);
    }

    /**
     * Emettre un évenement de changer la vue vers register
     */
    public void notifySwitchToRegister(){
        for(LoginViewObserver o : observers){
            o.switchToRegister();
        }
    }

    /**
     * Emmetre un évenement de connexion
     * @param tag
     * @param name
     */
    public void notifyLogin(String tag, String name){
        for(LoginViewObserver o : observers){
            o.login(tag, name);
        }
    }
}
