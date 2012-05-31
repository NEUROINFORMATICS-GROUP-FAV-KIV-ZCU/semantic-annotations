/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.zcu.kiv.annotations.gui;

import cz.zcu.kiv.annotations.application.IprojectLoader;
import cz.zcu.kiv.annotations.application.ProjectLoader;
import cz.zcu.kiv.annotations.data.Project;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author Pete
 */
public class OpenProjectListener implements ActionListener{
    
    private MainWindow master;
    
    public OpenProjectListener(MainWindow master){
        this.master = master;
    }

    public void actionPerformed(ActionEvent e) {
        IprojectLoader projectLoader = new ProjectLoader();
        File selectedFile = getSelectedFile();
        if(selectedFile == null){
            System.out.println("Open project cancelled");
        }
        else{
            Project project;
            try {
                project = projectLoader.openExistingProject(selectedFile);
                master.project.setProjectData(project);
                master.enableSavingAs();
                master.setSaveFile(selectedFile);
                master.enableSaving();
                master.jtreeWrapper.update(true);
                master.enableGeneration();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(OpenProjectListener.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(OpenProjectListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
        
    }
    
    private File getSelectedFile() {

        JFileChooser fileChooser = new JFileChooser(master.lastDir);     
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
        fileChooser.setFileFilter(new SelectedFilter("apf"));

        if ((fileChooser.showDialog(master, "Select file") == 0)) {
            master.lastDir = fileChooser.getCurrentDirectory();
            return fileChooser.getSelectedFile();

        }else {
            return null;
        }

        
    }
    
}
