/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.zcu.kiv.annotations.gui;

import cz.zcu.kiv.annotations.application.IprojectManager;
import cz.zcu.kiv.annotations.data.FileSavedAnnotAttr;
import cz.zcu.kiv.annotations.data.Iattribute;
import cz.zcu.kiv.annotations.data.IclassItem;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.swing.JTree;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author Administrator
 */
public class JTreeWrapper implements TreeSelectionListener, ChangeListener{
    
    private JTree tree;
    private int counter=0;
    
    private MainWindow master;
    
    public JTreeWrapper(JTree tree, MainWindow master){
        this.tree = tree;
        this.master = master;
        tree.addTreeSelectionListener(this);
        tree.setCellRenderer(new JTreeCellRenderer());
    }
    
    public void update(boolean newProject){
        IprojectManager project = master.project;
        List<IclassItem> classes = project.getClasses();
        List <Object> objectsList = null;
        if(newProject){
            counter++;
        }else{
            Enumeration <TreePath> expandedDescendants = tree.getExpandedDescendants(new TreePath(((DefaultMutableTreeNode)tree.getModel().getRoot()).getPath()));
            objectsList = new ArrayList<Object>();
            while(expandedDescendants.hasMoreElements()){
                TreePath path = expandedDescendants.nextElement();
                Object userObject = ((DefaultMutableTreeNode)path.getLastPathComponent()).getUserObject();
                objectsList.add(userObject);
            }
        }
        
        tree.setSelectionPath(null);
        DefaultMutableTreeNode TopNode = new DefaultMutableTreeNode(project.toString()+counter);
        tree.setModel(new DefaultTreeModel(TopNode));
        for(IclassItem classItem : classes){
            System.out.println(classItem.getPackageName()+", "+classItem.getName());
            String pckStr = classItem.getPackageName();
            String [] packages;
            if(pckStr==null){
                System.err.println("No package for: "+classItem.getName());
                packages = new String [0];
            }else{
                packages = pckStr.split("\\.");
            }
            DefaultMutableTreeNode recNode = TopNode;
            for(int i=0;i<packages.length;i++){
                DefaultMutableTreeNode pckNode = new DefaultMutableTreeNode(packages[i]);
                Enumeration <DefaultMutableTreeNode> children = recNode.children();
                DefaultMutableTreeNode dupNode = null;
                while(children.hasMoreElements()){
                    DefaultMutableTreeNode childItem = (DefaultMutableTreeNode)children.nextElement();
                    if(childItem.getUserObject() instanceof String){
                        if(((String)childItem.getUserObject()).equals(packages[i])){
                            dupNode = childItem;
                            break;
                        }
                    }
                }
                if(dupNode == null){
                    recNode.add(pckNode);
                    recNode = pckNode;
                }                
                else{
                    recNode = dupNode;
                }
            }
            DefaultMutableTreeNode classNode = new DefaultMutableTreeNode(classItem);
            recNode.add(classNode);
            List<Iattribute> classAttributes = classItem.getClassAttributes();
            for(Iattribute atribute : classAttributes){
                boolean doAdd = false;
                switch(atribute.getType()){
                    case FileSavedAnnotAttr.TYPE_FIELD:
                        doAdd = master.getFilters()[0];
                        break;
                    case FileSavedAnnotAttr.TYPE_CONST:
                        doAdd = master.getFilters()[2];
                        break;
                    case FileSavedAnnotAttr.TYPE_METHD:
                        doAdd = master.getFilters()[1];
                        break;
                }
                if(doAdd){
                    DefaultMutableTreeNode node = new DefaultMutableTreeNode(atribute);
                    classNode.add(node);
                }
            }
        }
        DefaultMutableTreeNode node = TopNode;
        if(newProject){           
            TreePath unfoldPath = new TreePath(node);
            while(node.getChildCount()<2 && node.getChildCount()>0){
                unfoldPath = unfoldPath.pathByAddingChild(node.getFirstChild());
                node = (DefaultMutableTreeNode) node.getFirstChild();
            }
            tree.setSelectionPath(unfoldPath);
        }
        else{
            Enumeration enumeration = node.breadthFirstEnumeration();
            while(enumeration.hasMoreElements()){
                node = (DefaultMutableTreeNode)enumeration.nextElement();
                if(objectsList.contains(node.getUserObject())){
                    tree.expandPath(new TreePath(node.getPath()));
                }
            }
        }
        tree.invalidate();
    }

    public void valueChanged(TreeSelectionEvent e) {
        TreePath[] selected = tree.getSelectionPaths();
        if(selected!=null){
            for(int i=0;i<selected.length;i++){
                System.out.println(selected[i].toString());
            }
        }
        master.anotsWrapper.update();
    }
    
    public TreePath[] getSelected(){
        return tree.getSelectionPaths();
    }
    
    /*
     * Testing purposes
     */
    public TreePath getFirstSelected(){
        return tree.getSelectionPaths()[0];
    }

    public void stateChanged(ChangeEvent e) {
        System.out.println("ding");
        this.update(false);
    }
    
}
