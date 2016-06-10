/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textplayer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Gabriel
 */
public class MidiManager {
    
    private static File handler;
    
    public static void saveMidi(Sequence sequence)
    {
        JFileChooser chooser = new JFileChooser();
        int chosen = chooser.showSaveDialog(null);
        if (chosen == JFileChooser.APPROVE_OPTION)
        {
            handler = chooser.getSelectedFile();
            //If file with selected name doesn't already exists, 
            //appends '.txt' to the selected file name.
            if (!handler.exists()) 
                handler = new File(chooser.getSelectedFile() + ".mid");
            boolean success = write(sequence);
            if (success)
            {
                JOptionPane.showMessageDialog(null,
                        "Midi saved.");
            }
            else
            {
                JOptionPane.showMessageDialog(null,
                        "Error saving midi.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }    
    }
    
    private static boolean write(Sequence sequence)
    {
        try {
            MidiSystem.write(sequence, MidiSystem.getMidiFileTypes()[0], handler);
            return true;
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(MidiManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}
