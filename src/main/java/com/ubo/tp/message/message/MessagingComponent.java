package main.java.com.ubo.tp.message.message;

import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.filter.FilterComponent;
import main.java.com.ubo.tp.message.filter.FilteredElementsModel;
import main.java.com.ubo.tp.message.filter.UnfilteredElementsModel;
import main.java.com.ubo.tp.message.ihm.session.ISession;
import main.java.com.ubo.tp.message.message.sender.MessageSenderComponent;
import main.java.com.ubo.tp.message.utils.MessageWrapper;

import javax.swing.*;
import java.awt.*;

public class MessagingComponent extends JPanel {

    public MessagingComponent(IDatabase db, ISession session) {
        super(new GridBagLayout());
        MessageSearchView messageSearchView = new MessageSearchView();
        FilterComponent<MessageFilterable> filterComponent = new FilterComponent<>(messageSearchView);
        FilteredElementsModel<MessageFilterable> filteredMessages = filterComponent.getFilteredElementsModel();
        UnfilteredElementsModel<MessageFilterable> messages = filterComponent.getUnfilteredElementsModel();
        MessagesController messagesController = new MessagesController(messages);
        MessagesView messagesView = new MessagesView(MessageWrapper.messagesToMessagesFilterable(db.getMessages()));
        db.addObserver(messagesController);
        filteredMessages.addObserver(messagesView);
        MessageSenderComponent messageSenderComponent = new MessageSenderComponent(db, session);
        this.add(messageSearchView, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
        this.add(messagesView, new GridBagConstraints(0, 1, 1, 1, 1, 6, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
        this.add(messageSenderComponent.getMessageSenderView(), new GridBagConstraints(0, 7, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));

    }
}
