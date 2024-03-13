package main.java.com.ubo.tp.message.login;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class RegisterView extends JPanel {
    /**
     * Observers de la vue
     */
    protected ArrayList<RegisterViewObserver> observers = new ArrayList<>();

    protected JTextField tagTextField;

    protected JTextField nameTextField;

    protected JPasswordField passwordTextField;
    protected JPasswordField password2TextField;


    /**
     * chemin d'acces de l'avatar selectionne par l'utilisateur
     */
    protected String avatarFilePath;
    public RegisterView(){
        super(new GridBagLayout());
        this.initNameElements();
        this.initTagElements();
        this.initPasswordElements();
        this.initAvatarElements();
        this.initButtons();
    }

    /**
     * Initialiser les elements lies au nom
     */
    protected void initNameElements(){

        Dimension textFieldDimensions = new Dimension(100,30);

        JLabel nameLabel = new JLabel("Nom* : ");
        JTextField nameTextField = new JTextField(20);
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

        JLabel tagLabel = new JLabel("Tag* : ");
        JTextField tagTextField = new JTextField(20);
        tagTextField.setSize(textFieldDimensions);

        this.add(tagLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));
        this.add(tagTextField, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));
        this.tagTextField = tagTextField;
    }


    protected void initPasswordElements() {
        Dimension textFieldDimensions = new Dimension(100,30);

        JLabel passwordLabel = new JLabel("Mot de passe* : ");
        JPasswordField passwordTextField = new JPasswordField(20);
        passwordTextField.setSize(textFieldDimensions);

        JLabel password2Label = new JLabel("Confirmer le mot de passe* : ");
        JPasswordField password2TextField = new JPasswordField(20);
        passwordTextField.setSize(textFieldDimensions);

        this.add(passwordLabel, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));
        this.add(passwordTextField, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));
        this.passwordTextField = passwordTextField;

        this.add(password2Label, new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));
        this.add(password2TextField, new GridBagConstraints(1, 3, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));
        this.password2TextField = password2TextField;
    }
    /**
     * Initialiser les elements liés à l'avatar
     */
    protected void initAvatarElements(){
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
                    try {
                        ImageIcon rawImage = new ImageIcon(ImageIO.read(file));
                        ImageIcon thumbnail = new ImageIcon(rawImage.getImage().getScaledInstance(40,40, Image.SCALE_SMOOTH));
                        avatarJLabel.setIcon(thumbnail);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    avatarJLabel.revalidate();
                    avatarJLabel.repaint();
                    avatarFilePath = file.getPath();
                }
            }
        });

        this.add(avatarJLabel, new GridBagConstraints(0, 4, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));
        this.add(avatarButtonSelectFile, new GridBagConstraints(1, 4, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));
    }

    /**
     * Initialiser les boutons
     */
    protected void initButtons(){
        JButton registerButton = new JButton("S'inscrire");
        JButton loginButton = new JButton("Déjà un compte ?");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notifySwitchToLogin();
            }
        });

        registerButton.addActionListener(e -> notifyRegister(nameTextField.getText(), tagTextField.getText(), new String(passwordTextField.getPassword()), new String(password2TextField.getPassword()), avatarFilePath));
        this.add(loginButton, new GridBagConstraints(0, 5, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));
        this.add(registerButton, new GridBagConstraints(1, 5, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));
    }

    protected void displayMessage(RegisterError registerResult){
        switch (registerResult){
            case PASSWORD_NOT_SAME:
                JOptionPane.showMessageDialog(this, "Les mot de passes ne correspondent pas", "Erreur", JOptionPane.ERROR_MESSAGE);
                break;
            case TAG_EMPTY:
                JOptionPane.showMessageDialog(this, "Le tag est obligatoire", "Erreur", JOptionPane.ERROR_MESSAGE);
                break;
            case NAME_EMPTY:
                JOptionPane.showMessageDialog(this, "Le nom est obligatoire", "Erreur", JOptionPane.ERROR_MESSAGE);
                break;
            case TAG_AND_NAME_EMPTY:
                JOptionPane.showMessageDialog(this, "Le nom et le tag sont obligatoires", "Erreur", JOptionPane.ERROR_MESSAGE);
                break;
            case TAG_ALREADY_USED:
                JOptionPane.showMessageDialog(this, "Tag déjà existant", "Erreur", JOptionPane.ERROR_MESSAGE);
                break;
            case VALID:
                JOptionPane.showMessageDialog(this, "Compte créé avec succès, vous pouvez maintenant vous connecter");
                break;
        }
    }

    /**
     * ajouter un observateur
     * @param observer
     */
    public void addObserver(RegisterViewObserver observer){
        observers.add(observer);
    }

    /**
     * Emettre un evenement de changement de vue vers login
     */
    protected void notifySwitchToLogin(){
        for(RegisterViewObserver o : observers){
            o.switchToLogin();
        }
    }

    /**
     * Emettre un evenement register
     *
     * @param name
     * @param tag
     * @param password
     * @param avatarFilePath
     */
    protected void notifyRegister(String name, String tag, String password, String password2, String avatarFilePath){
        for(RegisterViewObserver o : observers){
            o.register(name, tag, password, password2, avatarFilePath);
        }
    }
}
