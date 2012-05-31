/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.zcu.kiv.annotations.gui;

import cz.zcu.kiv.annotations.data.Iannotation;
import cz.zcu.kiv.annotations.data.Iattribute;
import cz.zcu.kiv.annotations.data.IclassItem;
import java.util.*;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

/**
 *
 * @author Administrator
 */
public class AnotsWrapper implements DocumentListener, ListSelectionListener{
    
    private static final byte CLASSES_SELECTED = 1;
    private static final byte ATRIBS_SELECTED = 2;
    
    
    private JList avaiable_anot;
    private Set <Iannotation> avail_anot_set;
    private JList added_anot;
    private Set <Iannotation> added_anot_set;
    private JTextField valueField;
    
    private MainWindow master;
    
    private boolean currentlyUpdating = false;
    private boolean fieldNotChanged = false;
    
    public AnotsWrapper(JList avaiable, JList added, JTextField valueField, MainWindow master){
        this.avaiable_anot = avaiable;
        this.added_anot = added;
        this.valueField = valueField;
        this.master = master;
        CustomListCellRenderer renderer = new CustomListCellRenderer();
        avaiable_anot.setCellRenderer(renderer);
        added_anot.setCellRenderer(renderer);
        valueField.getDocument().addDocumentListener(this);
        added.addListSelectionListener(this);
    }
    
    public void selectedAnotsAdd(){
        System.out.println("Now adding selected annotations");
        TreePath [] paths = master.jtreeWrapper.getSelected();        
        //TODO: replace loop with sane solution
        Object [] vals = avaiable_anot.getSelectedValues();
        Set <String> selection = new HashSet<String>();
        for(int i=0;i<vals.length;i++){
            selection.add(((AnotGUIRep)vals[i]).getName());
        }
        for(TreePath path : paths){
            DefaultMutableTreeNode leaf = (DefaultMutableTreeNode)path.getLastPathComponent();
            Object leafObj = leaf.getUserObject();
            //move all annotations in the selected stuff to a set
            if(leafObj instanceof IclassItem){
                IclassItem Aclass = (IclassItem)leafObj;
                List <Iannotation> list = Aclass.getClassAnnotations();
                for(Iannotation anot : list){
                    if(selection.contains(anot.getName())){
                        anot.setNoParam(true);
                    }                    
                }
            }else if(leafObj instanceof Iattribute){
                Iattribute atri = (Iattribute)leafObj;
                List <Iannotation> list = atri.getAttrAnnotations();
                for(Iannotation anot : list){
                    if(selection.contains(anot.getName())){
                        anot.setNoParam(true);
                    }  
                }
            }
        }
        this.update();
    }
    
    public void selectedAnotsRemove(){
        System.out.println("Now removing selected annotations");
        TreePath [] paths = master.jtreeWrapper.getSelected();        
        //TODO: replace loop with sane solution
        Object [] vals = added_anot.getSelectedValues();
        Set <String> selection = new HashSet<String>();
        for(int i=0;i<vals.length;i++){
            selection.add(((AnotGUIRep)vals[i]).getName());
        }
        for(TreePath path : paths){
            DefaultMutableTreeNode leaf = (DefaultMutableTreeNode)path.getLastPathComponent();
            Object leafObj = leaf.getUserObject();
            //move all annotations in the selected stuff to a set
            if(leafObj instanceof IclassItem){
                IclassItem Aclass = (IclassItem)leafObj;
                List <Iannotation> list = Aclass.getClassAnnotations();
                for(Iannotation anot : list){
                    if(selection.contains(anot.getName())){
                        anot.setNoParam(false);
                    }                    
                }
            }else if(leafObj instanceof Iattribute){
                Iattribute atri = (Iattribute)leafObj;
                List <Iannotation> list = atri.getAttrAnnotations();
                for(Iannotation anot : list){
                    if(selection.contains(anot.getName())){
                        anot.setNoParam(false);
                    }  
                }
            }
        }
        this.update();
    }
    
    public void allAnotsAdd(){
        System.out.println("Now adding all annotations");
        TreePath [] paths = master.jtreeWrapper.getSelected();
        for(TreePath path : paths){
            DefaultMutableTreeNode leaf = (DefaultMutableTreeNode)path.getLastPathComponent();
            Object leafObj = leaf.getUserObject();
            //move all annotations in the selected stuff to a set
            if(leafObj instanceof IclassItem){
                IclassItem Aclass = (IclassItem)leafObj;
                List <Iannotation> list = Aclass.getClassAnnotations();
                for(Iannotation anot : list){
                    anot.setNoParam(true);
                }
            }else if(leafObj instanceof Iattribute){
                Iattribute atri = (Iattribute)leafObj;
                List <Iannotation> list = atri.getAttrAnnotations();
                for(Iannotation anot : list){
                    anot.setNoParam(true);
                }
            }
        }
        this.update();
    }
    
    public void allAnotsRemove(){
        System.out.println("Now removing all annotations");
        TreePath [] paths = master.jtreeWrapper.getSelected();
        for(TreePath path : paths){
            DefaultMutableTreeNode leaf = (DefaultMutableTreeNode)path.getLastPathComponent();
            Object leafObj = leaf.getUserObject();
            //move all annotations in the selected stuff to a set
            if(leafObj instanceof IclassItem){
                IclassItem Aclass = (IclassItem)leafObj;
                List <Iannotation> list = Aclass.getClassAnnotations();
                for(Iannotation anot : list){
                    anot.setNoParam(false);
                }
            }else if(leafObj instanceof Iattribute){
                Iattribute atri = (Iattribute)leafObj;
                List <Iannotation> list = atri.getAttrAnnotations();
                for(Iannotation anot : list){
                    anot.setNoParam(false);
                }
            }
        }
        this.update();
    }
    
    public void update(){
        avail_anot_set = new HashSet<Iannotation>();
        added_anot_set = new HashSet<Iannotation>();
        TreePath [] paths = master.jtreeWrapper.getSelected();
        //zero if nothing viable is selected
        //CLASSES_SELECTED if only classes are selected
        //ATRIBS_SELECTED if only atributes are selected
        //something else if non-viable selection
        byte selectionMask = 0; 
        if(paths != null){
            for(TreePath path : paths){
                DefaultMutableTreeNode leaf = (DefaultMutableTreeNode)path.getLastPathComponent();
                Object leafObj = leaf.getUserObject();
                //move all annotations in the selected stuff to a set
                if(leafObj instanceof IclassItem){
                    selectionMask |= CLASSES_SELECTED; //binary OR
                    IclassItem Aclass = (IclassItem)leafObj;
                    List <Iannotation> list = Aclass.getClassAnnotations();
                    for(Iannotation anot : list){
                        if(anot.isChangen()){
                            added_anot_set.add(anot);
                        }else{
                            avail_anot_set.add(anot);
                        }
                    }
                }else if(leafObj instanceof Iattribute){
                    selectionMask |= ATRIBS_SELECTED; //binary OR
                    Iattribute atri = (Iattribute)leafObj;
                    List <Iannotation> list = atri.getAttrAnnotations();
                    for(Iannotation anot : list){
                        if(anot.isChangen()){
                            added_anot_set.add(anot);
                        }else{
                            avail_anot_set.add(anot);
                        }
                    }
                }
            }
        }

        if(selectionMask != CLASSES_SELECTED && selectionMask != ATRIBS_SELECTED){
            master.disableAnotsButtons();
            //handle collision by emptying lists
            DefaultListModel availModel = new DefaultListModel();
            DefaultListModel addedModel = new DefaultListModel();
            added_anot.setModel(addedModel);  
            avaiable_anot.setModel(availModel);
            
        }
        else{
            master.enableAnotsButtons();
            //filter to only unique anotation names
            SortedSet <String> avail_anot_names = new TreeSet<String>();
            SortedSet <String> added_anot_names = new TreeSet<String>();
            for(Iannotation anot : avail_anot_set){
                avail_anot_names.add(anot.getName());
            }
            for(Iannotation anot : added_anot_set){
                added_anot_names.add(anot.getName());
            }

            //get the first selected item to retrieve
            //the annotations from
            DefaultMutableTreeNode leaf = (DefaultMutableTreeNode)paths[0].getLastPathComponent();
            Object leafObj = leaf.getUserObject();       
            //now to attach values(if any) to the names
            List <AnotGUIRep> avail_anot_NwithV = new LinkedList<AnotGUIRep>();
            List <AnotGUIRep> added_anot_NwithV = new LinkedList<AnotGUIRep>();       
            for(String str : avail_anot_names){
                boolean hasValue = false;
                if(leafObj instanceof IclassItem){
                    IclassItem Aclass = (IclassItem)leafObj;
                    List <Iannotation> list = Aclass.getClassAnnotations();
                    for(Iannotation anot : list){
                        if(str.equals(anot.getName())){
                            hasValue = anot.isParam();
                        }                    
                    }
                }else if(leafObj instanceof Iattribute){
                    Iattribute atri = (Iattribute)leafObj;
                    List <Iannotation> list = atri.getAttrAnnotations();
                    for(Iannotation anot : list){
                        if(str.equals(anot.getName())){
                            hasValue = anot.isParam();
                        }  
                    }
                }
                avail_anot_NwithV.add(new AnotGUIRep(str, "", hasValue));
            }
            for(String str : added_anot_names){
                //Note to past self: MY EYES
                //OH GOD MY EYES
                String value = "NOT YET ASSINGED 789";
                for(Iannotation anot : added_anot_set){
                    if(anot.getName().equals(str)){
                        if(anot.getValue()!=null){
                            if(value.equals("NOT YET ASSINGED 789") || value.equals(anot.getValue())){
                                value = anot.getValue();
                            }else{
                                value = "<DIFFERENT>";
                            }
                        }
                    }
                }
                boolean hasValue = false;
                if(leafObj instanceof IclassItem){
                    IclassItem Aclass = (IclassItem)leafObj;
                    List <Iannotation> list = Aclass.getClassAnnotations();
                    for(Iannotation anot : list){
                        if(str.equals(anot.getName())){
                            hasValue = anot.isParam();
                        }                    
                    }
                }else if(leafObj instanceof Iattribute){
                    Iattribute atri = (Iattribute)leafObj;
                    List <Iannotation> list = atri.getAttrAnnotations();
                    for(Iannotation anot : list){
                        if(str.equals(anot.getName())){
                            hasValue = anot.isParam();
                        }  
                    }
                }
                added_anot_NwithV.add(new AnotGUIRep(str, value, hasValue));
            }

            DefaultListModel availModel = new DefaultListModel();
            DefaultListModel addedModel = new DefaultListModel();

            for(AnotGUIRep anot : avail_anot_NwithV){
                availModel.addElement(anot);
            }        
            for(AnotGUIRep anot : added_anot_NwithV){
                addedModel.addElement(anot);
            }
            added_anot.setModel(addedModel);  
            avaiable_anot.setModel(availModel);
        }
        
    }

    public void insertUpdate(DocumentEvent e) {
        fieldChange();
    }

    public void removeUpdate(DocumentEvent e) {
        fieldChange();
    }

    public void changedUpdate(DocumentEvent e) {
        fieldChange();
    }
    
    private void fieldChange(){
        if(!fieldNotChanged){
            currentlyUpdating=true;
            System.out.println("Field change event");
            String value = valueField.getText();
            TreePath [] paths = master.jtreeWrapper.getSelected();        
            //TODO: replace loop with sane solution
            Object [] vals = added_anot.getSelectedValues();
            Set <String> selection = new HashSet<String>();
            for(int i=0;i<vals.length;i++){
                selection.add(((AnotGUIRep)vals[i]).getName());
            }
            for(TreePath path : paths){
                DefaultMutableTreeNode leaf = (DefaultMutableTreeNode)path.getLastPathComponent();
                Object leafObj = leaf.getUserObject();
                //move all annotations in the selected stuff to a set
                if(leafObj instanceof IclassItem){
                    IclassItem Aclass = (IclassItem)leafObj;
                    List <Iannotation> list = Aclass.getClassAnnotations();
                    for(Iannotation anot : list){
                        if(selection.contains(anot.getName())){
                            if(anot.isParam()){
                                anot.setValue(value);
                            }
                        }                    
                    }
                }else if(leafObj instanceof Iattribute){
                    Iattribute atri = (Iattribute)leafObj;
                    List <Iannotation> list = atri.getAttrAnnotations();
                    for(Iannotation anot : list){
                        if(selection.contains(anot.getName())){
                            if(anot.isParam()){
                                anot.setValue(value);
                            }
                        }  
                    }
                }
            }
            int[] indices = added_anot.getSelectedIndices();
            this.update();
            added_anot.setSelectedIndices(indices); 
            currentlyUpdating=false;
        }
        fieldNotChanged = false;
    }

    public void valueChanged(ListSelectionEvent e) {
        if(!currentlyUpdating){
            updateValueField();
        }
    }

    private void updateValueField() {
        Object[] selectedAnotsAsObjects = added_anot.getSelectedValues();
        List <AnotGUIRep> selectedAnots = new LinkedList<AnotGUIRep>();
        //just in case
        for(Object obj : selectedAnotsAsObjects){
            if(obj instanceof AnotGUIRep){
                selectedAnots.add((AnotGUIRep)obj);
            }
        }
        Set <String> values = new HashSet<String>();
        for(AnotGUIRep select : selectedAnots){
            if(select.hasValue()){
                values.add(select.getValue());
            }
        }
        valueField.getDocument().removeDocumentListener(this);
        if(values.size()>1){
            valueField.setText("<DIFFERENT>");
        }
        if(values.size()==1){
            valueField.setText(values.iterator().next());
        }
        if(values.isEmpty()){
            valueField.setText("");
        }
        valueField.getDocument().addDocumentListener(this);
    }

    
}
