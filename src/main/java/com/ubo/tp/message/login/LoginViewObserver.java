package main.java.com.ubo.tp.message.login;

public interface LoginViewObserver {
    void switchToRegister();
    void login(String tag, String name);
}
