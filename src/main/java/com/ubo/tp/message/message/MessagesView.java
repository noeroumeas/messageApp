package main.java.com.ubo.tp.message.message;

import main.java.com.ubo.tp.message.datamodel.Message;
import main.java.com.ubo.tp.message.filter.FilterElementsModelObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.List;

public class MessagesView extends JPanel implements FilterElementsModelObserver<Message> {
    protected JPanel messageList;
    protected int messagesNumber;
    protected JScrollBar messagesScrollBar;
    public MessagesView(){
        super(new GridBagLayout());
        initMessageViewList();
    }
    protected void initMessageViewList(){
        this.messageList = new JPanel(new GridBagLayout());
        JScrollPane messageListScrollPane = new JScrollPane(this.messageList);
        this.messagesScrollBar = messageListScrollPane.getVerticalScrollBar();
        this.messagesScrollBar.setUnitIncrement(10);
        this.add(messageListScrollPane, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
        this.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                setScrollBarToBottom();
            }

            @Override
            public void componentMoved(ComponentEvent e) {

            }

            @Override
            public void componentShown(ComponentEvent e) {
                setScrollBarToBottom();
            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });
    }

    protected void refreshViewList(List<Message> messages){
        this.messagesNumber = 0;
        this.messageList.removeAll();

        for(Message m : messages){
            this.addMessageToList(this.messagesNumber++, m);
        }
        this.messageList.revalidate();
        this.messageList.repaint();
    }

    protected void addMessageToList(int i, Message message){
        this.messageList.add(new MessageView(message), new GridBagConstraints(0, i, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5,0,5,0), 0, 0));
    }

    @Override
    public void elementsChanged(List<Message> messages) {
        this.refreshViewList(messages);
        setScrollBarToBottom();
    }

    @Override
    public void elementAdded(Message m) {
        this.addMessageToList(this.messagesNumber, m);
        setScrollBarToBottom();
    }

    protected void setScrollBarToBottom(){
        Runnable runnable = () -> {
            messagesScrollBar.setValue(messagesScrollBar.getMaximum());
        };
        SwingUtilities.invokeLater(runnable);
    }

    @Override
    public void elementRemoved(Message u) {

    }
}
