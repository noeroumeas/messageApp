package com.ubo.tp.message.ihm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class WindowMenuBar extends JMenuBar{

    public WindowMenuBar(){
        super();
        JMenu fileMenu = createFileMenu();
        JMenu infoMenu = createInfoMenu();
        this.add(fileMenu);
        this.add(infoMenu);
    }

    /**
     * creation du Menu de fichier
     * @return
     */
    protected JMenu createFileMenu(){
        JMenu menu = new JMenu("Fichier");
        JMenuItem quitMenuItem = new JMenuItem("Quitter");
        ImageIcon exitIcon = new ImageIcon("src/main/resources/images/exitIcon_20.png");
        quitMenuItem.setIcon(exitIcon);
        quitMenuItem.addActionListener(e -> System.exit(0));

        menu.add(quitMenuItem);
        return menu;
    }

    /**
     * creation du menu d'info
     * @return
     */
    protected JMenu createInfoMenu(){
        JMenu menu = new JMenu("?");
        menu.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ImageIcon icon = new ImageIcon("src/main/resources/images/logo_50.png");
                JOptionPane.showMessageDialog(null, "<html><center>UBO M2-TIIL<br>Département Informatique</center></html>", "A propos", JOptionPane.INFORMATION_MESSAGE, icon);
            }

            @Override
            public void mousePressed(MouseEvent e) {

                //unused
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //unused

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //unused

            }

            @Override
            public void mouseExited(MouseEvent e) {
                //unused

            }
        });
        return menu;
    }
}
