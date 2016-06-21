/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textplayer;

import java.util.List;
import java.util.Random;
import javax.sound.midi.*;

/**
 *
 * @author Leonardo
 */
public class SoundTrack {
    
    // Constants
    public static final int BPM_CHANGE_AMOUNT = 5;
    public static final int MIN_BPM = 5;
    public static final int MAX_BPM = 300;
    public static final int DEFAULT_BPM = 120;
    public static final int DEFAULT_VOLUME = 63;
    public static final int DEFAULT_OCTAVE = 4;
    
    public static final int VOLUME_CHANGE_AMOUNT = 10;
    public static final int MIN_VOLUME = 0;
    public static final int MAX_VOLUME = 120;
    //
    private final Track songTrack;
    private final List<Instrument> instrumentsList;
    private int BPM; 
    private int octave;
    private int volume;

    public SoundTrack(Sequence songSequence, List<Instrument> instruments) 
    {
        songTrack = songSequence.createTrack();
        instrumentsList = instruments;
        BPM = DEFAULT_BPM;
        octave = DEFAULT_OCTAVE;
        volume = DEFAULT_VOLUME;
    }
    
    public void addIncreaseVolumeEvent() throws InvalidMidiDataException
    {
        MidiEvent songEvent;
        ShortMessage songShortMessage;
        
        if (volume + VOLUME_CHANGE_AMOUNT < MAX_VOLUME)
        {
            volume = volume + VOLUME_CHANGE_AMOUNT;
            songShortMessage = new ShortMessage(ShortMessage.CONTROL_CHANGE, 7, volume);
            songEvent = new MidiEvent(songShortMessage, songTrack.ticks()+1);
            songTrack.add(songEvent);
        }
    }

    public void addDecreaseVolumeEvent() throws InvalidMidiDataException
    {   
        MidiEvent songEvent;
        ShortMessage songShortMessage;
        
        if (volume - VOLUME_CHANGE_AMOUNT > MIN_VOLUME)
        {
            volume = volume - VOLUME_CHANGE_AMOUNT;
            songShortMessage = new ShortMessage(ShortMessage.CONTROL_CHANGE, 7, volume);
            songEvent = new MidiEvent(songShortMessage, songTrack.ticks()+1);
            songTrack.add(songEvent);   
        }
    }
    
    public void addResetVolumeEvent() throws InvalidMidiDataException
    {   
        MidiEvent songEvent;
        ShortMessage songShortMessage;
        
        volume = 100;
        songShortMessage = new ShortMessage(ShortMessage.CONTROL_CHANGE, 7, volume);
        songEvent = new MidiEvent(songShortMessage, songTrack.ticks()+1);
        songTrack.add(songEvent);  
    }

    public void addPauseEvent() throws InvalidMidiDataException 
    {
        MidiEvent songEvent;
        ShortMessage songShortMessage;
        
        songShortMessage = new ShortMessage(ShortMessage.NOTE_OFF, 0, 0);
        songEvent = new MidiEvent(songShortMessage, songTrack.ticks()+ BPM);
        songTrack.add(songEvent); 
    }

    public void addChangeInstrumentEvent() throws InvalidMidiDataException 
    {
        MidiEvent songEvent;
        ShortMessage songShortMessage;
        Random randomGenerator = new Random();
        int randomIndex = randomGenerator.nextInt(instrumentsList.size());
        int randomInstrument = instrumentsList.get(randomIndex).getPatch().getProgram();        
        songShortMessage = new ShortMessage(ShortMessage.PROGRAM_CHANGE, randomInstrument, 0);
        songEvent = new MidiEvent(songShortMessage, songTrack.ticks()+1);
        songTrack.add(songEvent);    
    }
    
    public void decreaseBpm() 
    {
        if (BPM < MAX_BPM)
            BPM = BPM + BPM_CHANGE_AMOUNT;
    }

    public void increaseBpm() 
    {
        if (BPM > MIN_BPM)
            BPM = BPM - BPM_CHANGE_AMOUNT;
    }

    public void increaseOctave() 
    {
        if (octave < 9)
            octave++;
    }

    public void decreaseOctave() 
    {
        if (octave > -1)
            octave--;
    }
 
    public void resetOctave() 
    {
        octave = 4;
    }

    public void addPlayNoteEvent(int intEquiToNote) throws InvalidMidiDataException 
    {
        MidiEvent songEvent;
        ShortMessage songShortMessage;
        
        songShortMessage = new ShortMessage(ShortMessage.NOTE_ON, intEquiToNote + octave*12, volume);
        songEvent = new MidiEvent(songShortMessage, songTrack.ticks()+1);
        songTrack.add(songEvent); 
        
        songShortMessage = new ShortMessage(ShortMessage.NOTE_OFF, intEquiToNote + octave*12, 0);
        songEvent = new MidiEvent(songShortMessage, songTrack.ticks() + BPM);
        songTrack.add(songEvent); 
    }
}
