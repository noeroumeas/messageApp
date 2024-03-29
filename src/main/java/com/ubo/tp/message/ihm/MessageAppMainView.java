package com.ubo.tp.message.ihm;

import javax.swing.*;
import java.awt.*;

/**
 * Classe de la vue principale de l'application.
 */
public class MessageAppMainView {
    JFrame mFrame;
    JPanel mPanel;
    public void init(){
        initMFrame();
        initWindowMenu();
        this.mFrame.setVisible(true);
    }

    protected void initMFrame(){
        JFrame frame = new JFrame("MessageApp");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("src/main/resources/images/logo_50.png");
        frame.setIconImage(icon.getImage());
        frame.setSize(1200,700);

        JPanel panel = new JPanel(new GridBagLayout());
        frame.setContentPane(panel);
        this.mPanel = panel;
        this.mFrame = frame;
    }

    protected void initWindowMenu(){
        if(this.mFrame == null){
            this.initMFrame();
        }

        this.mFrame.setJMenuBar(new WindowMenuBar());
    }

    public void setMainPanel(JPanel newPanel) {
        this.mPanel.removeAll();
        this.mPanel.add(newPanel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
        this.mPanel.revalidate();
        this.mPanel.repaint();
    }
}
