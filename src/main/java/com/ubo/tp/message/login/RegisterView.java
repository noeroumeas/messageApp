package main.java.com.ubo.tp.message.login;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class RegisterView extends JPanel {
    protected ArrayList<RegisterViewObserver> observers = new ArrayList<>();
    protected String avatarFilePath;
    public RegisterView(){
        super();
        this.setLayout(new GridBagLayout());
        Dimension textFieldDimensions = new Dimension(100,30);

        JLabel nameLabel = new JLabel("Nom* : ");
        JTextField nameTextField = new JTextField(20);
        nameTextField.setSize(textFieldDimensions);

        JLabel tagLabel = new JLabel("Tag* : ");
        JTextField tagTextField = new JTextField(20);
        tagTextField.setSize(textFieldDimensions);

        JButton registerButton = new JButton("S'inscrire");
        JButton loginButton = new JButton("Déjà un compte ?");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notifySwitchToLogin();
            }
        });

        ImageIcon avatarImage = new ImageIcon();
        JLabel avatarJLabel = new JLabel(avatarImage);
        JFileChooser avatarFileChooser = new JFileChooser();
        JButton avatarButtonSelectFile = new JButton("Selectionner une image");
        FileFilter imageFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
        avatarFileChooser.addChoosableFileFilter(imageFilter);

        avatarButtonSelectFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = avatarFileChooser.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = avatarFileChooser.getSelectedFile();
                    avatarButtonSelectFile.setText(file.getName());
                    avatarFilePath = file.getPath();
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notifyRegister(nameTextField.getText(), tagTextField.getText(), avatarFilePath);
            }
        });

        this.add(nameLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));
        this.add(nameTextField, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));
        this.add(tagLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));
        this.add(tagTextField, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));
        this.add(avatarJLabel, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));
        this.add(avatarButtonSelectFile, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));

        this.add(loginButton, new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));
        this.add(registerButton, new GridBagConstraints(1, 3, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));
    }
    public void addObserver(RegisterViewObserver observer){
        observers.add(observer);
    }

    protected void notifySwitchToLogin(){
        for(RegisterViewObserver o : observers){
            o.switchToLogin();
        }
    }

    protected void notifyRegister(String name, String tag, String avatarPath){
        for(RegisterViewObserver o : observers){
            o.register(name, tag, avatarPath);
        }
    }
}
