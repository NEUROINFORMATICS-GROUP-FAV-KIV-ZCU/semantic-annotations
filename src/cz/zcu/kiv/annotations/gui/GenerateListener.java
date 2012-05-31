/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.zcu.kiv.annotations.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author Pete
 */
public class GenerateListener implements ActionListener{
    
     private MainWindow master;

     public GenerateListener(MainWindow mainWindow) {
        this.master = mainWindow;
     }

    public void actionPerformed(ActionEvent e) {
        File file = getSelectedFile();
        if(file==null){
            System.out.println("Generation cancelled");
        }
        else{
            master.project.exportClasses(file);
        }
    }
    
    private File getSelectedFile() {

        JFileChooser fileChooser = new JFileChooser(master.lastDir);     
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if ((fileChooser.showDialog(master, "Select directory") == 0)) {
            master.lastDir = fileChooser.getCurrentDirectory();
            return fileChooser.getSelectedFile();

        }else {
            return null;
        }

        
    }
    
}
