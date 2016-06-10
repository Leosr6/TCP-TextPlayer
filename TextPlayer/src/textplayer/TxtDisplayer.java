/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textplayer;

import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Gabriel
 */
public class TxtDisplayer {
    
    public static void display(List<String> list, String displayname)
    {
        JFrame frame = new JFrame(displayname);
        JLabel label = new JLabel("readme");
        
        String displayed =  "<html>";
        for (String s : list)
        {
            displayed += s;
            displayed += "<br>";
        }
        displayed += "</html>";
            
        
        label.setText(displayed);
        frame.add(label);
        
        
        frame.setVisible(true);      
    }
    
}
