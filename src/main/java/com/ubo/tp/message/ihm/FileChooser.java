package com.ubo.tp.message.ihm;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;

public class FileChooser {
    protected JFileChooser fChooser;
    public FileChooser(){
        this.fChooser = new JFileChooser(FileSystemView.getFileSystemView());
        this.fChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    }

    public File getFolder(JFrame originFrame){
        int returnVal = this.fChooser.showOpenDialog(originFrame);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return this.fChooser.getSelectedFile();
        }
        return this.getFolder(originFrame);

    }

}
