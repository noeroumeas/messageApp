package main.java.com.ubo.tp.message.message;

import main.java.com.ubo.tp.message.datamodel.Message;
import main.java.com.ubo.tp.message.filter.FilterElementsModelObserver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MessagesView extends JPanel implements FilterElementsModelObserver<Message> {
    protected JPanel messageList;
    protected List<Message> messages;
    public MessagesView(List<Message> messages){
        super(new GridBagLayout());
        this.initMessageViewList(messages);
    }
    protected void initMessageViewList(List<Message> messages){
        this.messageList = new JPanel(new GridBagLayout());
        JScrollPane messageListScrollPane = new JScrollPane(this.messageList);
        messageListScrollPane.getVerticalScrollBar().setUnitIncrement(10);
        this.add(messageListScrollPane, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
        refreshViewList(messages);
    }

    protected void refreshViewList(List<Message> messages){
        this.messages = new ArrayList<>();
        for(Message m : messages){
            this.addMessageToList(m);
        }
        this.displayMessageList();
    }

    protected void displayMessageList() {
        this.messageList.removeAll();
        int i;
        for(i = 0; i < this.messages.size(); i++){
            this.addMessageViewToList(i, new MessageView(this.messages.get(i)));
        }
        this.messageList.revalidate();
        this.messageList.repaint();
    }

    protected void addMessageViewToList(int i, MessageView messageView){
        this.messageList.add(messageView, new GridBagConstraints(0, i, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5,0,5,0), 0, 0));
    }

    protected void addMessageToList(Message message){
        int debut = 0;
        int fin = messages.size() - 1;

        while (debut <= fin) {
            int milieu = debut + (fin - debut) / 2;
            if (messages.get(milieu).getEmissionDate() < message.getEmissionDate()) {
                debut = milieu + 1;
            } else if (messages.get(milieu).getEmissionDate() > message.getEmissionDate()) {
                fin = milieu - 1;
            } else {
                debut = milieu;
                break;
            }
        }
        this.messages.add(debut, message);
    }

    @Override
    public void elementsChanged(List<Message> messages) {
        this.refreshViewList(messages);
    }

    @Override
    public void elementAdded(Message m) {
        this.addMessageToList(m);
        this.displayMessageList();

    }

    @Override
    public void elementRemoved(Message u) {

    }
}
