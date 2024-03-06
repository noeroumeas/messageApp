package main.java.com.ubo.tp.message.ihm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class WindowMenuBar extends JMenuBar{

    public WindowMenuBar(){
        super();
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = createFileMenu();
        JMenu infoMenu = createInfoMenu((JFrame) menuBar.getParent());
        menuBar.add(fileMenu);
        menuBar.add(infoMenu);
    }

    protected JMenu createFileMenu(){
        JMenu menu = new JMenu("Fichier");
        JMenuItem quitMenuItem = new JMenuItem("Quitter");
        ImageIcon exitIcon = new ImageIcon("src/main/resources/images/exitIcon_20.png");
        quitMenuItem.setIcon(exitIcon);
        quitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menu.add(quitMenuItem);
        return menu;
    }

    protected JMenu createInfoMenu(JFrame originFrame){
        JMenu menu = new JMenu("?");
        menu.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ImageIcon icon = new ImageIcon("src/main/resources/images/logo_50.png");
                JOptionPane.showMessageDialog(originFrame, "<html><center>UBO M2-TIIL<br>Département Informatique</center></html>", "A propos", JOptionPane.INFORMATION_MESSAGE, icon);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        return menu;
    }
}
