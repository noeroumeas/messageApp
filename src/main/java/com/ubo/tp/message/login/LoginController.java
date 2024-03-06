package main.java.com.ubo.tp.message.login;

import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.navigator.NavigatorObserver;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class LoginController implements LoginViewObserver, RegisterViewObserver {
    protected RegisterView registerView;
    protected LoginView loginView;
    protected IDatabase database;
    protected ArrayList<NavigatorObserver> observers = new ArrayList<>();
    public LoginController(IDatabase db, RegisterView registerView, LoginView loginView){
        this.registerView = registerView;
        this.loginView = loginView;
        this.registerView.addObserver(this);
        this.loginView.addObserver(this);
        this.database = db;
    }

    @Override
    public void switchToRegister() {
        this.notifySwitchToRegister();
    }

    protected void notifySwitchToRegister() {
        for(NavigatorObserver o : this.observers){
            o.switchToRegister();
        }
    }

    @Override
    public void login(String tag, String name) {
        System.out.println("tag : " + tag + "\nname : " + name);
        Set<User> users = this.database.getUsers();
        for(User u : users){
            if(u.getUserTag().equals(tag) && u.getName().equals(name)){
                return;
            }
        }
        JOptionPane.showMessageDialog(this.registerView, "Tag et nom d'utilisateur incorrects", "Erreur", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void switchToLogin() {
        this.notifySwitchToLogin();
    }

    protected void notifySwitchToLogin(){
        for(NavigatorObserver o : this.observers){
            o.switchToLogin();
        }
    }
    public void addObserver(NavigatorObserver observer){
        this.observers.add(observer);
    }
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
                System.out.println("New user added : " + newUser.toString());
                JOptionPane.showMessageDialog(this.registerView, "Compte créé avec succès, vous pouvez maintenant vous connecter");
                this.switchToLogin();
                break;
        }
    }

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
