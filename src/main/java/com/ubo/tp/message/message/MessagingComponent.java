package com.ubo.tp.message.message;

import com.ubo.tp.message.core.EntityManager;
import com.ubo.tp.message.core.database.IDatabase;
import com.ubo.tp.message.datamodel.Message;
import com.ubo.tp.message.filter.FilterComponent;
import com.ubo.tp.message.filter.FilteredElementsModel;
import com.ubo.tp.message.filter.UnfilteredElementsModel;
import com.ubo.tp.message.ihm.session.ISession;
import com.ubo.tp.message.message.sender.MessageSenderComponent;

import javax.swing.*;
import java.awt.*;

public class MessagingComponent extends JPanel {

    public MessagingComponent(IDatabase db, ISession session, EntityManager entityManager) {
        super(new GridBagLayout());
        MessageSearchView messageSearchView = new MessageSearchView();
        FilterMessage filter = new FilterMessage("");
        FilterComponent<Message, String> filterComponent = new FilterComponent<>(messageSearchView, filter);
        FilteredElementsModel<Message> filteredMessages = filterComponent.getFilteredElementsModel();
        UnfilteredElementsModel<Message> messages = filterComponent.getUnfilteredElementsModel();
        MessagesView messagesView = new MessagesView();
        filteredMessages.addObserver(messagesView);
        MessagesController messagesController = new MessagesController(messages);
        db.addObserver(messagesController);
        MessageSenderComponent messageSenderComponent = new MessageSenderComponent(session, entityManager);
        this.add(messageSearchView, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
        this.add(messagesView, new GridBagConstraints(0, 1, 1, 1, 1, 6, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
        this.add(messageSenderComponent.getMessageSenderView(), new GridBagConstraints(0, 7, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));

    }
}
