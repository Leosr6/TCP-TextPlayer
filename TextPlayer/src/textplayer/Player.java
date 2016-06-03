/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textplayer;

import java.util.List;
import javax.swing.JLabel;

/**
 *
 * @author Gabriel
 */
public class Player {
    
    //Constant values
    private final int defaultBPM = 150;
    
    private List<String> instruments;
    private String textSong;
    private int bpm = defaultBPM; //valor default (mudar?)
    JLabel bpmLabel = null;
    boolean playing = false;
    boolean paused = false;
    
    public Player(List<String> instruments, String song)
    {
        this.instruments = instruments;
        this.textSong = song;
    }
    
    public Player(JLabel bpmlabel)
    {
        this.bpmLabel = bpmlabel;
        updateBPMLabel();
    }
    
    public void setInstruments(List<String> instruments)
    {
        this.instruments = instruments;
    }
    
    public List<String> getInstruments()
    {
        return this.instruments;
    }
    
    public void setSong(String song)
    {
        this.textSong = song;
    }
    
    public String getSong()
    {
        return this.textSong;
    }
    
    public void setBPM(int bpm)
    {
        this.bpm = bpm;
        updateBPMLabel();
    }
    
    public int getBPM()
    {
        return this.bpm;
    }
    
    public void increaseBPM(int amount)
    {
        this.bpm += amount;
        updateBPMLabel();
    }
    
    public void decreaseBPM(int amount)
    {
        this.bpm -= amount;
        updateBPMLabel();
    }
    
    public void updateBPMLabel()
    {
        if (bpmLabel != null)
            this.bpmLabel.setText(String.valueOf(bpm));
    }
    
    public boolean isPaused()
    {
        return this.paused;
    }
    
    public boolean isPlaying()
    {
        return this.playing;
    }
    
    //Debugging function
    public void printInstruments()
    {
        System.out.println("Instruments being used:");
        for (String s : instruments)
            System.out.println(s);
    }
    
    //Debugging function
    public void printSong()
    {
        System.out.println("Song:");
        System.out.println(textSong);
    }
    
    public void play()
    {
        /* 
            TO DO:
                tudo
                - iterador que percorre a string da música tem que ser "global"
                    pro pause funcionar (provavelmente não dessa classe)
        
            POSSIVEL BUG: converter os \n da string que nem no save()
        */
        System.out.println("Playing...");
        playing = true;
        paused = false;
    }
    
    public void pause()
    {
        /*
            TO DO:
                tudo
        */
        System.out.println("Pausing...");
        playing = false;
        paused = true;
    }
    
    public void resume()
    {
        /*
            TO DO:
                tudo
        */
        System.out.println("Resuming...");
        paused = false;
        playing = true;
    }
    
    public void stop()
    {
        /*
            TO DO:
                tudo
        */
        System.out.println("Stopping...");
        setBPM(defaultBPM);
        playing = false;
        paused = false;
    }
    
}
