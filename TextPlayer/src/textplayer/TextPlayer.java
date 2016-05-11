/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textplayer;

import javax.sound.midi.*;

/**
 *
 * @author Leonardo
 */

public class TextPlayer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MidiUnavailableException, InterruptedException {
        
        Synthesizer synth = MidiSystem.getSynthesizer();
        int instrumentNumber;
        synth.open();
        MidiChannel ch = synth.getChannels()[0];
        
        for (Instrument i:synth.getLoadedInstruments())
        {
            Thread.sleep(1000);
            instrumentNumber = i.getPatch().getProgram();
            ch.programChange(instrumentNumber);
            System.out.println(i.getName() + ", " + instrumentNumber);
            ch.noteOn(60, 50); // 0 à 127
            Thread.sleep(2000);
            ch.noteOff(60);
        }
       
        
        
        
    }
    
}
