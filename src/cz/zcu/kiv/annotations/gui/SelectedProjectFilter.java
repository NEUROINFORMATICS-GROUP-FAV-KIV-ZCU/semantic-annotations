/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.zcu.kiv.annotations.gui;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Zbyněk Němec
 */
public class SelectedProjectFilter  extends FileFilter {

    private String suffix = "apf";
    
    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) return true;     
        if (f.getName().toLowerCase().endsWith(suffix))return true;
        return false;
    }

    @Override
    public String getDescription() {
        return suffix;
    }
    
}
