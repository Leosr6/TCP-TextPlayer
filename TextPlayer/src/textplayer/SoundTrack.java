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
    private final int bpmChangeAmount = 5;
    private final int minBpm = 5;
    private final int maxBpm = 300;
    private final int volumeChangeAmount = 10;
    private final int minVolume = 0;
    private final int maxVolume = 127;
    //
    private final Track songTrack;
    private final List<Instrument> instrumentsList;
    private int BPM; 
    private int octave;
    private int volume;

    SoundTrack(Sequence songSequence, List<Instrument> instruments, int bpm) 
    {
        songTrack = songSequence.createTrack();
        instrumentsList = instruments;
        BPM = bpm;
        octave = 4;
        volume = 100;
    }
    
    public void addIncreaseVolumeEvent() throws InvalidMidiDataException
    {
        MidiEvent songEvent;
        ShortMessage songShortMessage;
        
        if (volume < maxVolume)
        {
            volume = volume + volumeChangeAmount;
            songShortMessage = new ShortMessage(ShortMessage.CONTROL_CHANGE, 7, volume);
            songEvent = new MidiEvent(songShortMessage, songTrack.ticks()+1);
            songTrack.add(songEvent);
        }
    }

    public void addDecreaseVolumeEvent() throws InvalidMidiDataException
    {   
        MidiEvent songEvent;
        ShortMessage songShortMessage;
        
        if (volume > minVolume)
        {
            volume = volume - volumeChangeAmount;
            songShortMessage = new ShortMessage(ShortMessage.CONTROL_CHANGE, 7, volume);
            songEvent = new MidiEvent(songShortMessage, songTrack.ticks()+1);
            songTrack.add(songEvent);   
        }
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
    
    public void addIncreaseBpmEvent() 
    {
        if (BPM < maxBpm)
            BPM = BPM + bpmChangeAmount;
    }

    public void addDecreaseBpmEvent() 
    {
        if (BPM > minBpm)
            BPM = BPM - bpmChangeAmount;
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
