/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.zcu.kiv.annotations.gui;


import cz.zcu.kiv.annotations.application.IprojectManager;
import cz.zcu.kiv.annotations.application.ProjectManager;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
/**
 *
 * @author Zbyněk Němec
 */
public class MainWindow extends javax.swing.JFrame {

    public IprojectManager project;
    
    JMenuItem NewProject = new JMenuItem("New Project");
    JMenuItem OpenProject = new JMenuItem("Load Project");
    JMenuItem SaveProject = new JMenuItem("Save Project");
    JMenuItem SaveAsProject = new JMenuItem("Save Project as...");
    JMenuItem Generate = new JMenuItem("Generate files");
    JMenuItem Exit = new JMenuItem("Exit");
    
    public AnotsWrapper anotsWrapper;
    public JTreeWrapper jtreeWrapper;
    
    private boolean savingEnabled = false;
    private boolean savingAsEnabled = false;
    public  File saveFile = null;
    public  File lastDir = new File(".");
    
    
    private Frame mainWindow;
    /**
     * Creates new form NewJFrame
     */
    public MainWindow() {
        project = new ProjectManager();
        
        initComponents();
        
    }
    
    public void setCursorCus(Cursor cursor){
        this.setCursor(cursor);
        setCursorRec(cursor, this);
        
    }
    
    public void setCursorRec(Cursor cursor, Component comp){
        comp.setCursor(cursor);
        Component[] components = null;
        if(comp instanceof Container){
            components = ((Container)comp).getComponents();
        }
        
        if(components != null){
            for(Component component : components){
                setCursorRec(cursor, component);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        fields = new javax.swing.JToggleButton();
        methods = new javax.swing.JToggleButton();
        constructors = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tree = new javax.swing.JTree();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        set_values = new javax.swing.JTextField();
        gen_button = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        available_anot = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        added_anot = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        down = new javax.swing.JButton();
        up_all = new javax.swing.JButton();
        down_all = new javax.swing.JButton();
        up = new javax.swing.JButton();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));

        jPanel2.setAlignmentX(0.0F);
        jPanel2.setAlignmentY(0.0F);
        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        fields.setText("Fields");
        methods.setText("Methods");
        constructors.setText("Constructors");
        
        fields.setSelected(true);
        methods.setSelected(true);
        constructors.setSelected(true);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(fields)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(methods)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(constructors))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(fields)
                .addComponent(methods)
                .addComponent(constructors))
        );

        tree.setMaximumSize(new java.awt.Dimension(460, 640));
        tree.setMinimumSize(new java.awt.Dimension(460, 640));
        DefaultMutableTreeNode TopNode = new DefaultMutableTreeNode("No project");
        tree.setModel(new DefaultTreeModel(TopNode));
        jScrollPane1.setViewportView(tree);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Set values");

        set_values.setColumns(20);
        jScrollPane4.setViewportView(set_values);

        gen_button.setText("Generate");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane4))
                .addGap(18, 18, 18)
                .addComponent(gen_button)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(gen_button))
        );

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Available anotation");

        available_anot.setModel(new javax.swing.AbstractListModel() {
            String[] strings = new String[0];
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        available_anot.setMaximumSize(new java.awt.Dimension(420, 640));
        available_anot.setMinimumSize(new java.awt.Dimension(420, 640));
        jScrollPane5.setViewportView(available_anot);

        added_anot.setModel(new javax.swing.AbstractListModel() {
            String[] strings = new String[0];
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        added_anot.setMaximumSize(new java.awt.Dimension(420, 640));
        added_anot.setMinimumSize(new java.awt.Dimension(420, 640));
        jScrollPane2.setViewportView(added_anot);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Added anotation");

        down.setText("<");
        down.addActionListener(new java.awt.event.ActionListener() {
            
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anotsWrapper.selectedAnotsRemove();
                
            } 
        });
        
        up_all.setText(">>");
        up_all.addActionListener(new java.awt.event.ActionListener() {
            
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anotsWrapper.allAnotsAdd();
                
            }
        });
        down_all.setText("<<");
        down_all.addActionListener(new java.awt.event.ActionListener() {
            
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anotsWrapper.allAnotsRemove();
               
            }

        });

        up.setText(">");
        up.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                anotsWrapper.selectedAnotsAdd();
               
            }
        });
        
        Exit.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.exit(0);
               
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(up, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(up_all, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(down_all, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(down, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(up_all)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(up)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(down)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(down_all))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1))
                .addGap(8, 8, 8)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(10, 10, 10))
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE))
                .addContainerGap())
        );

        JMenu menuItemProject = new JMenu ("File");
        jMenuBar2.add(menuItemProject);
 
        SaveProject.setEnabled(savingEnabled);
        SaveAsProject.setEnabled(savingAsEnabled);
        Generate.setEnabled(savingAsEnabled);
        menuItemProject.add(NewProject);
	menuItemProject.add(OpenProject);
	menuItemProject.add(SaveProject);
        menuItemProject.add(SaveAsProject);
        menuItemProject.add(Generate);
        menuItemProject.add(Exit);   

        setJMenuBar(jMenuBar2);
        
        NewProject.addActionListener(new NewProjectListener(this));
        SaveProject.addActionListener(new SaveProjectListener(this));
        SaveAsProject.addActionListener(new SaveAsProjectListener(this));
        OpenProject.addActionListener(new OpenProjectListener(this));
        Generate.addActionListener(new GenerateListener((this)));
        gen_button.addActionListener(new GenerateListener((this)));
        

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        
        anotsWrapper = new AnotsWrapper(available_anot, added_anot, set_values,this);
        jtreeWrapper = new JTreeWrapper(tree,this);
        
        fields.addChangeListener(jtreeWrapper);
        constructors.addChangeListener(jtreeWrapper);
        methods.addChangeListener(jtreeWrapper);
        
        disableAnotsButtons();
        disableGeneration();
    }// </editor-fold>                                                                      

    /**
     * @param args the command line arguments
     */
   
    // Variables declaration - do not modify
    private javax.swing.JList added_anot;
    private javax.swing.JToggleButton fields;
    private javax.swing.JList available_anot;
    private javax.swing.JToggleButton constructors;
    private javax.swing.JButton down;
    private javax.swing.JButton down_all;
    private javax.swing.JButton gen_button;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JToggleButton methods;
    private javax.swing.JTextField set_values;
    private javax.swing.JTree tree;
    private javax.swing.JButton up;
    private javax.swing.JButton up_all;
    // End of variables declaration
 public void enableSavingAs() {
        savingAsEnabled = true;
        SaveAsProject.setEnabled(savingAsEnabled);
        Generate.setEnabled(savingAsEnabled);
    }

    public void disableSaving() {
        savingAsEnabled = false;
        savingEnabled = false;
        SaveAsProject.setEnabled(savingAsEnabled);
        Generate.setEnabled(savingAsEnabled);
//        SaveProject.setEnabled(savingEnabled);
    }
    
    public void enableSaving() {
        savingEnabled = true;
        SaveProject.setEnabled(savingEnabled);
    }

    public void setSaveFile(File projectFile) {
        saveFile = projectFile;
    }

    public boolean[] getFilters() {
        boolean [] returns = new boolean [3];
        returns[0] = fields.isSelected();
        returns[1] = methods.isSelected();
        returns[2] = constructors.isSelected();
        return returns;
    }
    
    public void enableGeneration(){
        Generate.setEnabled(true);
        gen_button.setEnabled(true);
    }
    
    public void disableGeneration(){
        Generate.setEnabled(false);
        gen_button.setEnabled(false);
    }
       
    public void enableAnotsButtons(){
        up.setEnabled(true);
        up_all.setEnabled(true);
        down.setEnabled(true);
        down_all.setEnabled(true);
    }
    
    public void disableAnotsButtons(){
        up.setEnabled(false);
        up_all.setEnabled(false);
        down.setEnabled(false);
        down_all.setEnabled(false);
    }
    

}
