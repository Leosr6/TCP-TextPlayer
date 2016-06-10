/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textplayer;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.*;

/**
 *
 * @author Leonardo
 */

public class TextPlayer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MidiUnavailableException, InterruptedException, InvalidMidiDataException {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            try {
                new MainFrame().setVisible(true);
            } catch (MidiUnavailableException ex) {
                Logger.getLogger(TextPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        });
    }
    
}