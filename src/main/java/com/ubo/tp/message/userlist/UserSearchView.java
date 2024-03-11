package main.java.com.ubo.tp.message.userlist;

import main.java.com.ubo.tp.message.filter.SearchView;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class UserSearchView extends SearchView<UserFilterable> {
    UserSearchView(){
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
        searchBar.setPreferredSize(new Dimension(10000, 30));
        searchPanel.add(searchBar, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
        this.add(searchPanel, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
    }
}
