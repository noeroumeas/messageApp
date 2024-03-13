package main.java.com.ubo.tp.message.login;

public interface RegisterViewObserver {
    public void switchToLogin();

    public void register(String tag, String name, String password, String avatarPath, String avatarFilePath);
}
