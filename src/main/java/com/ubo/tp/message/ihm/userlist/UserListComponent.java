package main.java.com.ubo.tp.message.ihm.userlist;

import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.datamodel.User;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class UserListComponent extends JPanel {
    public UserListComponent(IDatabase db){
        super(new GridBagLayout());
        this.add(new UserListSearchView(db), new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
    }
}
