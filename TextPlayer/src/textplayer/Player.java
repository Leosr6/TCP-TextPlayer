/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textplayer;

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
    
    public void setSequencer(Sequencer sequencer) throws InvalidMidiDataException, MidiUnavailableException {
        synth = MidiSystem.getSynthesizer();
        synth.open();
        this.sequencer = sequencer;
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
        if (status != NOT_INITIALIZED)
        {
            System.out.println("Stopping...");
            setStatus(STOPPED);

            sequencer.stop();
        }
    }
    
}
