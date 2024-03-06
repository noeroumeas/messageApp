package main.java.com.ubo.tp.message.login;

import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.session.ISession;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class LoginController implements LoginViewObserver, RegisterViewObserver {
    protected RegisterView registerView;
    protected LoginView loginView;
    protected IDatabase database;
    protected ISession session;
    protected ArrayList<NavigatorObserver> observers = new ArrayList<>();
    public LoginController(IDatabase db, ISession session, RegisterView registerView, LoginView loginView){
        this.registerView = registerView;
        this.loginView = loginView;
        this.registerView.addObserver(this);
        this.loginView.addObserver(this);
        this.database = db;
        this.session = session;
    }

    /**
     * Gestion de l'evenement de changement de vue vers register
     */
    @Override
    public void switchToRegister() {
        this.notifySwitchToRegister();
    }


    /**
     * Emmetre un evenement de changement de vue vers register
     */
    protected void notifySwitchToRegister() {
        for(NavigatorObserver o : this.observers){
            o.switchToRegister();
        }
    }

    /**
     * Gestion de l'evenement de login emit par la vue login
     * @param tag
     * @param name
     */
    @Override
    public void login(String tag, String name) {
        Set<User> users = this.database.getUsers();
        for(User u : users){
            if(u.getUserTag().equals(tag) && u.getName().equals(name)){
                session.connect(u);
                System.out.println("user connected");
                return;
            }
        }
        JOptionPane.showMessageDialog(this.registerView, "Tag et nom d'utilisateur incorrects", "Erreur", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * gerer de l'evenement de changement de vue vers login
     */
    @Override
    public void switchToLogin() {
        this.notifySwitchToLogin();
    }

    /**
     * Emettre un evenement de changement de vue vers login
     */
    protected void notifySwitchToLogin(){
        for(NavigatorObserver o : this.observers){
            o.switchToLogin();
        }
    }

    /**
     * ajouter un observateur
     * @param observer
     */
    public void addObserver(NavigatorObserver observer){
        this.observers.add(observer);
    }

    /**
     * Gerer l'evenement register, enregistrement dans la base de donnée si tag et nom non vides et tag non existant
     * @param name
     * @param tag
     * @param avatarPath
     */
    @Override
    public void register(String name, String tag, String avatarPath) {
        RegisterError registerError = isRegisterValid(name, tag, avatarPath);
        switch (registerError){
            case TAG_EMPTY:
                JOptionPane.showMessageDialog(this.registerView, "Le tag est obligatoire", "Erreur", JOptionPane.ERROR_MESSAGE);
                break;
            case NAME_EMPTY:
                JOptionPane.showMessageDialog(this.registerView, "Le nom est obligatoire", "Erreur", JOptionPane.ERROR_MESSAGE);
                break;
            case TAG_AND_NAME_EMPTY:
                JOptionPane.showMessageDialog(this.registerView, "Le nom et le tag sont obligatoires", "Erreur", JOptionPane.ERROR_MESSAGE);
                break;
            case TAG_ALREADY_USED:
                JOptionPane.showMessageDialog(this.registerView, "Tag déjà existant", "Erreur", JOptionPane.ERROR_MESSAGE);
                break;
            case VALID:
                User newUser = new User(UUID.randomUUID(), tag, "test", name, new HashSet<>(), avatarPath);
                this.database.addUser(newUser);
                JOptionPane.showMessageDialog(this.registerView, "Compte créé avec succès, vous pouvez maintenant vous connecter");
                this.switchToLogin();
                break;
        }
    }

    /**
     * Verifie la validité des champs name, tag et avatarPath
     * @param name
     * @param tag
     * @param avatarPath
     * @return
     */
    protected RegisterError isRegisterValid(String name, String tag, String avatarPath){
        Boolean isTagEmpty = tag.isEmpty();
        Boolean isNameEmpty = name.isEmpty();
        if(isTagEmpty && isNameEmpty){
            return RegisterError.TAG_AND_NAME_EMPTY;
        }
        if(isTagEmpty){
            return RegisterError.TAG_EMPTY;

        }
        if(isNameEmpty){
            return RegisterError.NAME_EMPTY;

        }
        Set<User> users = this.database.getUsers();
        for(User u : users){
            if(u.getUserTag().equals(tag)){
                return RegisterError.TAG_ALREADY_USED;

            }
        }
        return RegisterError.VALID;
    }

}
