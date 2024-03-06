package main.java.com.ubo.tp.message.ihm;

import javax.swing.*;

/**
 * Classe de la vue principale de l'application.
 */
public class MessageAppMainView {
    JFrame mFrame;
    protected FileChooser fileChooser;
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
        frame.setSize(500,500);
        this.mFrame = frame;
    }

    protected void initWindowMenu(){
        if(this.mFrame == null){
            this.initMFrame();
        }

        this.mFrame.setJMenuBar(new WindowMenuBar());
    }

    public void setMainPanel(JPanel newPanel) {
        this.mFrame.setContentPane(newPanel);
        this.mFrame.validate();
    }
}
