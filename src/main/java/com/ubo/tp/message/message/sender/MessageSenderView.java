package main.java.com.ubo.tp.message.message.sender;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MessageSenderView extends JPanel {
    protected List<MessageSenderViewObserver> observers;
    protected JTextArea messageTextField;
    public MessageSenderView() {
        super(new GridBagLayout());
        this.observers = new ArrayList<>();
        this.initTextField();
        this.initButtonSend();
    }

    protected void initTextField() {

        this.messageTextField = new JTextArea();
        this.messageTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        messageTextField.setLineWrap(true);
        this.add(new JScrollPane(this.messageTextField), new GridBagConstraints(0, 0, 1, 1, 5, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
    }

    protected void initButtonSend() {
        JButton sendButton = new JButton("Envoyer");
        sendButton.addActionListener(e -> notifySendMessage(this.messageTextField.getText()));
        this.add(sendButton, new GridBagConstraints(5, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
    }

    protected void notifySendMessage(String text) {
        for(MessageSenderViewObserver o : this.observers){
            o.notifyMessageSend(text);
        }
    }

    public void addObserver(MessageSenderViewObserver o){
        this.observers.add(o);
    }

    public void displayMessageTooBig() {
        JOptionPane.showMessageDialog(this, "Message trop long, veuillez ne pas dépasser 200 caractères", "Erreur", JOptionPane.ERROR_MESSAGE);
    }

    public void clearMessageTextField(){
        this.messageTextField.setText("");
    }

    public void displayMessageEmpty() {
        JOptionPane.showMessageDialog(this, "Message Vide", "Erreur", JOptionPane.ERROR_MESSAGE);
    }
}
