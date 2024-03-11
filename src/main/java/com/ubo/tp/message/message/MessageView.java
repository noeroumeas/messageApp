package main.java.com.ubo.tp.message.message;

import main.java.com.ubo.tp.message.connected.NavigatorObserver;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageView extends JPanel {
    protected MessageFilterable message;
    public MessageView(MessageFilterable message){
        super(new GridBagLayout());
        this.message = message;
        this.initInfoMessage(message);
        this.initMessage(message);
    }

    private void initInfoMessage(MessageFilterable message) {

        Date date = new Date(message.getEmissionDate());
        SimpleDateFormat DateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        String dateText = DateFormat.format(date);
        JLabel messageInfoLabel = new JLabel("<html><i><b>@" + message.getSender().getUserTag() + "</b><br>" + dateText + "</i></html>", JLabel.LEFT);
        messageInfoLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        this.add(messageInfoLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
    }

    private void initMessage(MessageFilterable message) {
        JLabel messageLabel = new JLabel("<html>" + message.getText() + "</html>", JLabel.LEFT);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.add(messageLabel, new GridBagConstraints(0, 1, 1, 1, 1, 3, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));

    }
}
