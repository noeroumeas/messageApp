package main.java.com.ubo.tp.message.message;

import main.java.com.ubo.tp.message.filter.SearchView;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class MessageSearchView extends SearchView<MessageFilterable> {
    public MessageSearchView() {
        super(new GridBagLayout());
        this.initUserSearchBar();
    }

    public void initUserSearchBar(){
        JPanel searchPanel = new JPanel(new GridBagLayout());
        JTextField searchBar = new JTextField(20);
        searchBar.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                warn();
            }
            public void removeUpdate(DocumentEvent e) {
                warn();
            }
            public void insertUpdate(DocumentEvent e) {
                warn();
            }

            public void warn() {
                notifyFilterChange(searchBar.getText());
            }
        });
        ImageIcon searchIcon = new ImageIcon("src/main/resources/images/searchIcon.png");
        JLabel searchIconLabel = new JLabel(searchIcon);
        searchBar.setPreferredSize(new Dimension(10000, 50));
        searchPanel.add(searchBar, new GridBagConstraints(0, 0, 1, 1, 9, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
        searchPanel.add(searchIconLabel, new GridBagConstraints(9, 0, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));

        this.add(searchPanel, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
    }
}
