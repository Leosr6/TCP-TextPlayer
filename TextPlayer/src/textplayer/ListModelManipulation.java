/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textplayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import javax.swing.DefaultListModel;

/**
 *
 * @author Gabriel
 */
public class ListModelManipulation {

    /*
        Return a sorted version of the model
    */
    public static DefaultListModel sort (DefaultListModel model)
    {
        List list = Collections.list(model.elements());
        Collections.sort(list);

        DefaultListModel sortedModel = new DefaultListModel();
        
        for (Object s : list)
            sortedModel.addElement(s);
        
        return sortedModel;
    }
    
    /*
        
    */
    public static List<String> toString (DefaultListModel model)
    {
        List listOfObjects = Collections.list(model.elements());
        List<String> listOfStrings = new ArrayList<String>();
        for (Object s : listOfObjects)
            listOfStrings.add((String) s);
        
        return listOfStrings;     
    }
    
    /*
        Move specified element from source to target.
        Returns updated source as first element of the list, and the updated
            target as the second element.
    */
    public static List<DefaultListModel> moveElement (DefaultListModel source, DefaultListModel target, Object element)
    {
        if (element != null)
        {
            target.addElement(element);
            source.removeElement(element);
        }
        
        List<DefaultListModel> updatedModels = new ArrayList<DefaultListModel>();
        updatedModels.add(source);
        updatedModels.add(sort(target));
        return updatedModels;
    }
    
    /*
        Move all elements from source to target.
        Returns updated source as first element of the list, and the updated
            target as the second element.
    */
    public static List<DefaultListModel> moveAllElements (DefaultListModel source, DefaultListModel target)
    {
        try 
        {
            Object element = source.firstElement();
            while (source != null)
            {
                target.addElement(element);
                source.removeElement(element);
                element = source.firstElement();
            }
        } catch (NoSuchElementException exception) {}
        
        List<DefaultListModel> updatedModels = new ArrayList<DefaultListModel>();
        updatedModels.add(source);
        updatedModels.add(sort(target));
        return updatedModels;
    }
    
    
    
}
