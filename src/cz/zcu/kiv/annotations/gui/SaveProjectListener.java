/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.zcu.kiv.annotations.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JOptionPane;

/**
 *
 * @author Pete
 */
public class SaveProjectListener implements ActionListener{
private MainWindow master;


     public SaveProjectListener(MainWindow mainWindow) {

        this.master = mainWindow;
     }
    /**
    * Gather user created annotations and save this data to file.
    * The annotation file and all project classes are ziped to one
    * file and seved to disk.
    *
    * @param e
    */
    public void actionPerformed(ActionEvent e) {
        master.project.saveProject(master.saveFile);
    }
}

