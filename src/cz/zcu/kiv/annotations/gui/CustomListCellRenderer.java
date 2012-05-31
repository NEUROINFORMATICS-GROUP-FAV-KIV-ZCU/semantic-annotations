/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.zcu.kiv.annotations.gui;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Administrator
 */
public class CustomListCellRenderer extends JLabel implements ListCellRenderer{
    public CustomListCellRenderer() {
         setOpaque(true);
     }
     public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus){
         if(value instanceof AnotGUIRep){
             AnotGUIRep rep = (AnotGUIRep)value;
             if(rep.hasValue()){
                 setText(rep.getName()+" ["+rep.getValue()+"]");
             }else{
                 setText(rep.getName());
             }
         }else{
             setText("Not a AnotGUIRep object!");
         }
         setBackground(isSelected ? Color.blue : Color.white);
         setForeground(isSelected ? Color.white : Color.black);
         return this;
     }
    
}
