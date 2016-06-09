/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textplayer;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.*;

/**
 *
 * @author Gabriel
 */
public class Player {
    
    //Constant values
    public static final int PLAYING = 0x01;
    public static final int PAUSED = 0x02;
    public static final int STOPPED = 0x03;
    public static final int NOT_INITIALIZED = 0x04;
    
    private int status;
    
    private Synthesizer synth;
    private Sequencer sequencer;
    private Sequence sequence;
    
    public Player()
    {
        this.status = NOT_INITIALIZED;
    }
    
    public Player(Sequence sequence)
    {
        this.sequence = sequence;
    }
    
    public void setSequence(Sequence sequence)
    {
        this.sequence = sequence;
    }
    
    public int getStatus()
    {
        return this.status;
    }
    
    public void setStatus(int status)
    {
        this.status = status;
    }
    
    private void setSequencer() throws InvalidMidiDataException, MidiUnavailableException {
        synth = MidiSystem.getSynthesizer();
        synth.open();
        sequencer = MidiSystem.getSequencer();
        sequencer.open();
        sequencer.setSequence(sequence);
        
    }
    
    public void increaseBPM(int amount)
    {
        float bpm = sequencer.getTempoInBPM();
        bpm += amount;
        sequencer.setTempoInBPM(bpm);
    }
    
    public void decreaseBPM(int amount)
    {
        float bpm = sequencer.getTempoInBPM();
        bpm -= amount;
        sequencer.setTempoInBPM(bpm);
    }
    
    public void play() throws InterruptedException, MidiUnavailableException, InvalidMidiDataException
    {
        System.out.println("Playing...");
        setStatus(PLAYING);
        
        setSequencer();
        
        sequencer.start();
    }
    
    public void pause()
    {
        System.out.println("Pausing...");
        setStatus(PAUSED);
        
        sequencer.stop();
    }
    
    public void resume()
    {
        System.out.println("Resuming...");
        setStatus(PLAYING);
        
        sequencer.start();
    }
    
    public void stop()
    {
        System.out.println("Stopping...");
        setStatus(STOPPED);
        
        sequencer.stop();
    }
    
}
