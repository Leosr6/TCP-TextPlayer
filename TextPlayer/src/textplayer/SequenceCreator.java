/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textplayer;

import java.util.List;
import javax.sound.midi.*;

/**
 *
 * @author Gabriel
 */
public class SequenceCreator {
    
    public static Sequence create(List<String> instruments, String song, int bpm) 
            throws MidiUnavailableException, InvalidMidiDataException
    {
        //CODIGO COPIADO DO TEXTPLAYER <<<< REMOVER DEPOIS
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
        
        return sequence;
    }
    
}
