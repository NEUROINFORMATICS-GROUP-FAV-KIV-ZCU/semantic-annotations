/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.zcu.kiv.annotations.gui;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Tomáš
 */
public class SelectedFilter extends FileFilter {

        String suffix1 = "0";
        String suffix2 = "0";
        String suffix3 = "0";

        public SelectedFilter(String string1, String string2, String string3){
            suffix1 = string1;
            suffix2 = string2;
            suffix3 = string3;
        }
                
        public SelectedFilter(String string) {
        suffix1 = string;
        }

	
	public boolean accept(File f) {

            if (f.isDirectory()) return true;
            
            if (f.getName().endsWith(suffix1) || f.getName().endsWith(suffix1.toUpperCase())) {

                return true;
            }
            if (f.getName().endsWith(suffix2) || f.getName().endsWith(suffix2.toUpperCase())) {

                return true;
            }
            if (f.getName().endsWith(suffix3) || f.getName().endsWith(suffix3.toUpperCase())) {

                return true;
            }else {
                return false;
            }
        }

	
	public String getDescription() {
            if(suffix2.contains("0"))
            return suffix1;
            else{
                return suffix1 + ", " + suffix2 +", "+ suffix3;
            }
	}

    
    
}
