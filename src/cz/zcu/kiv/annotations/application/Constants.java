package cz.zcu.kiv.annotations.application;

/**
 * This class contatins all neccessary constatns to correct
 * run of the application
 *
 * @author Filip Markvart
 */
public class Constants {

    public static final String TEMP_PATH = "temp";
    public static final String ROOT_PATH = "actProject";
    public static final String ORIG_FILES = "origFiles";
    
    public static final String APP_ROOT_PATH = ".";
    
    public static final String PARSER_CHAR = "@";

    public static final char CLASS_ANNOT = 'C';
    public static final char ATTR_ANNOT = 'A';

    public static final String PCKG_FILE = "package.dat";
    public static final String PROJECT_DATA = "annotations.dat";
    public static final String SOURCES = "sources";

    public static final String PROJECT_TYPE = "apf";

    // JAIF file constants

    public static final String jaifFileName = "Annotations.jaif";

    public static final String headerPack = "package thewebsemantic:";

    public static final String annotDefBegin = "annotation @";
    public static final String annotDefEnd = ": @Retention(value=RUNTIME) @java.lang.annotation.Target(value={TYPE_USE})";
    
    public static final String annotDefType = "String value";
    
    public static final String annotItemPack = "package ";
    public static final String annotItemPackEnd = ":";
    
    public static final String annotItemBeg = "class ";
    public static final String annotItemMidd = ": @thewebsemantic.";

    public static final String annotItemEnd1 = "(\"";
    public static final String annotItemEnd2 = "\")";

    public static final String annotItemBegField = "field ";
    public static final String annotItemBegConst = "method ";
    public static final String annotItemBegMethd = "method ";

    public static final String attrAnnotFile = "attrAnnots.ini";
    public static final String classAnnotFile = "classAnnots.ini";

    
}
