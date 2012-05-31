package cz.zcu.kiv.annotations.application;

import cz.zcu.kiv.annotations.data.Annotation;
import cz.zcu.kiv.annotations.data.Iannotation;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class contains static methods to compare attribute's or class'es
 * annotations and returns a list of annotations that has common
 * values to all selected attributes or classes.
 *
 * @author Filip Markvart
 */
public class CommonAnnots {

    public static List<String[]> getCommonAnnots(IprojectManager project, List<String[]> compareList) {

       List<String[]> common = new ArrayList<String[]>();


       List<List<Iannotation>> allAnnots = new ArrayList<List<Iannotation>>();

       List<Iannotation> first;

       if (compareList.get(0)[1] != null){
            first = project.getClassesAttrAnnotations(compareList.get(0)[0], compareList.get(0)[1]);

            for (String[] item: compareList) {

            allAnnots.add(project.getClassesAttrAnnotations(item[0], item[1]));
            }
       }else {
           first = project.getClassesAnnotations(compareList.get(0)[0]);


           for (String[] item: compareList) {

            allAnnots.add(project.getClassesAnnotations(item[0]));
            }
       }
       List<Iannotation> finalList = new ArrayList<Iannotation>();


      Set<Iannotation> removeList = new HashSet<Iannotation>();

      for (Iannotation annot: first){ //base list

          for (List<Iannotation> inner: allAnnots) {

              for (Iannotation item: inner) {

                  if (item.getName().equals(annot.getName())) {

                      if (item.isChangen() && annot.isChangen() && item.getValue().equals(annot.getValue())) {
                        continue;
                      }else {

                          removeList.add(annot);
                      }
              }
          }

      }

     }

      for (Iannotation annot: first) {
          
          if (!removeList.contains(annot)){
              finalList.add(annot);
          }
      }

      if (finalList.size() == 0) {
          return null;
      }

      for (Iannotation annot: finalList) {

          if (annot.isParam()) {
              String[] noParam = {annot.getName(), annot.getValue()};
              common.add(noParam);

          }else{
              String[] param = {annot.getName(), null};
              common.add(param);
          }

      }




      return common;
    }
}
