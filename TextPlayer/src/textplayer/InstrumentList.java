/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textplayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.sound.midi.Instrument;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

/**
 *
 * @author Gabriel
 */
public class InstrumentList {
    
    public static List<String> get() throws MidiUnavailableException
    {
        List<String> instruments = new ArrayList<String>();
        Synthesizer s = MidiSystem.getSynthesizer();
        s.open();
        for (Instrument instrument : s.getLoadedInstruments())
        {
            instruments.add(instrument.getName());
        }
        
        Collections.sort(instruments);
        s.close();
        
        return instruments;
        
        
    }
    
    public static List<Instrument> stringToInstrument(List<String> instList) throws MidiUnavailableException
    {
        List<Instrument> choosenInstruments = new ArrayList<Instrument>();
        Synthesizer s = MidiSystem.getSynthesizer();
        s.open();
        
        for (Instrument instrument : s.getLoadedInstruments())
            if (instList.contains(instrument.getName()))
                choosenInstruments.add(instrument);
        
        s.close();
                
        return choosenInstruments;
    }
    
}
