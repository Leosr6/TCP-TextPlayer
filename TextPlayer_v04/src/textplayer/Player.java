/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textplayer;

import java.util.List;

/**
 *
 * @author Gabriel
 */
public class Player {
    
    private List<String> instruments;
    private String textSong;
    private int bpm = 150; //valor default (mudar?)
    
    public Player(List<String> instruments, String song)
    {
        this.instruments = instruments;
        this.textSong = song;
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
    }
    
    public int getBPM()
    {
        return this.bpm;
    }
    
    //Debugging function
    public void printInstruments()
    {
        System.out.println("Instruments being used:");
        for (String s : this.instruments)
            System.out.println(s);
    }
    
    //Debugging function
    public void printSong()
    {
        System.out.println("Song:");
        System.out.println(textSong);
    }
    
    public void playSong()
    {
        /* 
            TO DO:
                tudo
        
            POSSIVEL BUG: converter os \n da string que nem no save()
        */
        
        //printInstruments();
        //printSong();
    }
    
}
