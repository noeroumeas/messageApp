package com.ubo.tp.message.login;

import com.ubo.tp.message.core.EntityManager;
import com.ubo.tp.message.core.database.IDatabase;
import com.ubo.tp.message.datamodel.User;
import com.ubo.tp.message.ihm.session.ISession;

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
    protected EntityManager entityManager;
    public LoginController(EntityManager entityManager, IDatabase db, ISession session, RegisterView registerView, LoginView loginView){
        this.registerView = registerView;
        this.loginView = loginView;
        this.registerView.addObserver(this);
        this.loginView.addObserver(this);
        this.database = db;
        this.session = session;
        this.entityManager = entityManager;
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
     * @param password
     */
    @Override
    public void login(String tag, String password) {
        Set<User> users = this.database.getUsers();

        for(User u : users){
            if(u.getUserTag().equals(tag) && u.getUserPassword().equals(password)){
                session.connect(u);
                return;
            }
        }
        this.loginView.displayMessage();
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
     *
     * @param name
     * @param tag
     * @param password
     * @param password2
     * @param avatarPath
     */
    @Override
    public void register(String name, String tag, String password, String password2, String avatarPath) {
        RegisterError registerError = isRegisterValid(name, tag, password, password2, avatarPath);
        String path = avatarPath == null ? "src/main/resources/images/logo_50.png" : avatarPath;
        if(registerError == RegisterError.VALID){
            UUID userUUID = UUID.randomUUID();
            User newUser = new User(userUUID, tag, password, name, new HashSet<>(), path);
            this.entityManager.writeUserFile(newUser);
            this.registerView.displayMessage(registerError);
            this.switchToLogin();
        } else {
            this.registerView.displayMessage(registerError);
        }
    }

    /**
     * Verifie la validité des champs name, tag et avatarPath
     *
     * @param name
     * @param tag
     * @param password
     * @param password2
     * @param path
     * @return
     */
    protected RegisterError isRegisterValid(String name, String tag, String password, String password2, String path){
        Boolean isTagEmpty = tag.isEmpty();
        Boolean isNameEmpty = name.isEmpty();
        if(!password.equals(password2)){
            return RegisterError.PASSWORD_NOT_SAME;
        }
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
