/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.zcu.kiv.annotations.gui;

import cz.zcu.kiv.annotations.data.FileSavedAnnotAttr;
import cz.zcu.kiv.annotations.data.Iattribute;
import cz.zcu.kiv.annotations.data.IclassItem;
import java.awt.Component;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 *
 * @author Petr Šroub
 */
public class JTreeCellRenderer extends DefaultTreeCellRenderer{
    
    private static Icon icon_const = createImageIcon("res/cons.png" ,"constructor");
    private static Icon icon_field = createImageIcon("res/field.png" ,"field");
    private static Icon icon_methd = createImageIcon("res/methd.png" ,"method");
    
    public JTreeCellRenderer(){
    
    }
    
    @Override
    public Component getTreeCellRendererComponent(
                        JTree tree,
                        Object value,
                        boolean sel,
                        boolean expanded,
                        boolean leaf,
                        int row,
                        boolean hasFocus) {

        super.getTreeCellRendererComponent(
                        tree, value, sel,
                        expanded, leaf, row,
                        hasFocus);
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        if (node.getUserObject() instanceof Iattribute) {
            Icon icon = this.getLeafIcon(); //default
            switch(((Iattribute)node.getUserObject()).getType()){
                case FileSavedAnnotAttr.TYPE_CONST:
                    icon = icon_const;
                    break;
                case FileSavedAnnotAttr.TYPE_FIELD:
                    icon = icon_field;
                    break;
                case FileSavedAnnotAttr.TYPE_METHD:
                    icon = icon_methd;
                    break;
            }
            setIcon(icon);
        }
        if (node.getUserObject() instanceof IclassItem) {
            setIcon(this.getLeafIcon());
        }
        return this;
    }
    
    private static ImageIcon createImageIcon(String path, String description) {
        URL resource = JTreeCellRenderer.class.getResource(path);
        return new ImageIcon(resource, description);
    }
    
}
