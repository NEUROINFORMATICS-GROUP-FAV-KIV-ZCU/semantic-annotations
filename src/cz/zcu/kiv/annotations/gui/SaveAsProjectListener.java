package cz.zcu.kiv.annotations.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

/**
 * Jmenu item Save project listener class is used to save actual
 * project data and user annotations to one project file and save
 * it on disk.
 *
 * @author Filip Markvart
 */
public class SaveAsProjectListener implements ActionListener{


    private MainWindow master;


     public SaveAsProjectListener(MainWindow mainWindow) {

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

        File projectFile;

        if ((projectFile = getFileName()) != null) {

            if (master.project.saveProject(projectFile)) {
                master.setSaveFile(projectFile);
                master.enableSaving();
            }else {
                JOptionPane.showMessageDialog(master, "Can not save project.");
            }
        }
    }


    /**
     * Method creates a dialog asking to user to saving project name file.
     *
     * @return Name of the file with path
     */
    public File getFileName() {

        JFileChooser fileChooser = new JFileChooser(master.lastDir);

        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setAcceptAllFileFilterUsed(true);
        fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooser.setFileFilter(new SaveAsSelectedFilter("apf"));
        
            if ((fileChooser.showDialog(master, "Select file") == 0)) {
                master.lastDir = fileChooser.getCurrentDirectory();
                File selected = fileChooser.getSelectedFile();
                if (selected.exists()) {

                    int choice = JOptionPane.showConfirmDialog(fileChooser, "File already exists, overwrite it?", "Overwrite?", JOptionPane.YES_NO_OPTION);
                    
                    if (choice == 0){
                        return fileChooser.getSelectedFile();
                    }else {

                        return null;
                    }
                }


                return fileChooser.getSelectedFile();

            }else {
              return null;
            }
    }

    
}