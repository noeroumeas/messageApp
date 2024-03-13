package com.ubo.tp.message.ihm;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;

public class FileChooser {
    protected JFileChooser fileChooser;
    public FileChooser(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        this.fileChooser = fileChooser;
    }

    public File getFolder(JFrame originFrame){
        int returnVal = fileChooser.showOpenDialog(originFrame);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File folder = fileChooser.getSelectedFile();
            System.out.println("File: " + folder.getName() + ".");
            return folder;
        }
        System.out.println("Open command cancelled by user.");
        return this.getFolder(originFrame);

    }

}
