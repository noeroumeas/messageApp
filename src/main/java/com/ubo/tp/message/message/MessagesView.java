package main.java.com.ubo.tp.message.message;

import main.java.com.ubo.tp.message.filter.FilterElementsModelObserver;
import main.java.com.ubo.tp.message.userlist.UserListViewObserver;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MessagesView extends JPanel implements FilterElementsModelObserver<MessageFilterable> {
    protected int messageListSize;
    protected JPanel messageList;
    public MessagesView(List<MessageFilterable> messages){
        super(new GridBagLayout());
        this.initMessageViewList(messages);
    }
    protected void initMessageViewList(List<MessageFilterable> messages){

        this.messageList = new JPanel(new GridBagLayout());
        JScrollPane messageListScrollPane = new JScrollPane(this.messageList);
        messageListScrollPane.getVerticalScrollBar().setUnitIncrement(10);
        this.add(messageListScrollPane, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
        refreshViewList(messages);
    }

    protected void refreshViewList(List<MessageFilterable> messages){
        this.messageListSize = 0;
        this.messageList.removeAll();
        for(MessageFilterable u : messages){
            this.addMessageViewToList(u);
        }

        this.messageList.revalidate();
        this.messageList.repaint();
    }

    protected void addMessageViewToList(MessageFilterable u){
        this.messageList.add(new MessageView(u), new GridBagConstraints(0, this.messageListSize++, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5,0,5,0), 0, 0));
    }

    @Override
    public void elementsChanged(List<MessageFilterable> messages) {
        this.refreshViewList(messages);
    }

    @Override
    public void elementAdded(MessageFilterable u) {
        this.addMessageViewToList(u);
    }

    @Override
    public void elementRemoved(MessageFilterable u) {

    }
}
