/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.zcu.kiv.annotations.gui;

/**
 *
 * @author Administrator
 */
class AnotGUIRep {
    
    private String name;
    private String value;
    private boolean hasValue;
    
    public AnotGUIRep(String name, String value, boolean hasValue){
        this.name = name;
        this.value = value;
        this.hasValue = hasValue;
    }
    
    public String getName(){
        return name;
    }
    
    public String getValue(){
        return value;
    }

    boolean hasValue() {
        return hasValue;
    }
    
}
