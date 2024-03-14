package com.ubo.tp.message.message;

import com.ubo.tp.message.datamodel.Message;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageView extends JPanel {
    protected Message message;
    public MessageView(Message message){
        super(new GridBagLayout());
        this.message = message;
        this.initInfoMessage(message);
        this.initMessage(message);
    }

    protected void initInfoMessage(Message message) {
        Date date = new Date(message.getEmissionDate());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        String dateText = dateFormat.format(date);
        JLabel messageInfoLabel = new JLabel("<html><i><b>@" + message.getSender().getUserTag() + "</b><br>" + dateText + "</i></html>", JLabel.LEFT);
        messageInfoLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        this.add(messageInfoLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
    }

    protected void initMessage(Message message) {
        JLabel messageLabel = new JLabel("<html>" + message.getText() + "</html>", JLabel.LEFT);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.add(messageLabel, new GridBagConstraints(0, 1, 1, 1, 1, 3, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));

    }
}
