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
/**
     * File filter defines the selected file format
     * of structure to save file
     */
    public class SaveAsSelectedFilter extends FileFilter {

        String suffix;

        public SaveAsSelectedFilter(String type) {

            suffix = type;
        }

	@Override
	public boolean accept(File f) {

            if (f.isDirectory()) return true;
            if (f.getName().contains(suffix) || f.getName().contains(suffix.toUpperCase())) {

                return true;
            }else {
                return false;
            }
        }

	@Override
	public String getDescription() {

            return suffix;
	}
    }
