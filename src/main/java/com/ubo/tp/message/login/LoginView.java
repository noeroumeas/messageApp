package main.java.com.ubo.tp.message.login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoginView extends JPanel {
    /**
     * Observateurs de la vue
     */
    protected ArrayList<LoginViewObserver> observers = new ArrayList<>();
    protected JTextField nameTextField;
    protected JTextField tagTextField;
    public LoginView(){
        super(new GridBagLayout());
        this.initNameElements();
        this.initTagElements();
        this.initButtons();
    }

    /**
     * Initialiser les elements lies au nom
     */
    protected void initNameElements(){
        Dimension textFieldDimensions = new Dimension(100,30);

        JLabel nameLabel = new JLabel("Nom : ");
        JTextField nameTextField = new JTextField("testName",20);
        nameTextField.setSize(textFieldDimensions);

        this.add(nameLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));
        this.add(nameTextField, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));
        this.nameTextField = nameTextField;
    }

    /**
     * Initialiser les elements lies au tag
     */
    protected void initTagElements(){
        Dimension textFieldDimensions = new Dimension(100,30);

        JLabel tagLabel = new JLabel("Tag : ");
        JTextField tagTextField = new JTextField("testTag",20);
        tagTextField.setSize(textFieldDimensions);

        this.add(tagLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));
        this.add(tagTextField, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));
        this.tagTextField = tagTextField;
    }

    /**
     * Initialiser les boutons
     */
    protected void initButtons(){
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

        this.add(registerButton, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));
        this.add(loginButton, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));

    }

    public void displayMessage(){
        JOptionPane.showMessageDialog(this, "Tag et nom d'utilisateur incorrects", "Erreur", JOptionPane.ERROR_MESSAGE);
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
    protected void notifySwitchToRegister(){
        for(LoginViewObserver o : observers){
            o.switchToRegister();
        }
    }

    /**
     * Emmetre un évenement de connexion
     * @param tag
     * @param name
     */
    protected void notifyLogin(String tag, String name){
        for(LoginViewObserver o : observers){
            o.login(tag, name);
        }
    }
}
