/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textplayer;

import java.util.Arrays;
import java.util.List;
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
        
        /* 
        TESTE 1:
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
        
        TESTE 2:
        Synthesizer synth = MidiSystem.getSynthesizer();
        synth.open();
        Sequencer sequencer = MidiSystem.getSequencer();
        sequencer.open();
        Sequence sequence = new Sequence(Sequence.PPQ, 4);
        Track newTrack = sequence.createTrack();
        int ticks = 0;
        
        for (Instrument instrument:synth.getLoadedInstruments())
        {
            newTrack.add(new MidiEvent(new ShortMessage(ShortMessage.PROGRAM_CHANGE, instrument.getPatch().getProgram(), 0), ticks));
            newTrack.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_ON, 60, 63), ticks));
            ticks+= 16;
            newTrack.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_OFF, 60, 0), ticks));
        }

        sequencer.setSequence(sequence);
        sequencer.start();*/
        
        java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            
            List<String> instruments = Arrays.asList(
                    "Bass", 
                    "Drums",
                    "Guitar", 
                    "Keyboard");
            
            new MainFrame(instruments).setVisible(true);
            }
        });
    }
    
}
