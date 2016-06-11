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
 * @author Leonardo
 */
public class SoundSequence {
    
    private static String song;
    
    public static Sequence createSoundSequence(List<Instrument> instruments, String textSong) throws InvalidMidiDataException
    {
        song = textSong;
        Sequence songSequence = new Sequence(Sequence.PPQ, SoundTrack.DEFAULT_BPM);
        SoundTrack songTrack = new SoundTrack(songSequence, instruments);
        createEvents(songTrack);
        
        return songSequence;
    }
    
    private static void createEvents(SoundTrack songTrack) throws InvalidMidiDataException
    {
        songTrack.addChangeInstrumentEvent();
        
        for (char songChar:song.toCharArray())
        {
            switch(songChar)
            {
                case '!':
                    songTrack.addIncreaseVolumeEvent();
                    break;
                case ' ':
                    songTrack.addPauseEvent();
                    break;
                case '?':
                    songTrack.resetOctave();
                    break;
                case '\n':
                    songTrack.addChangeInstrumentEvent();
                    break;
                case ';':
                    songTrack.decreaseBpm();
                    break;
                case ',':
                    songTrack.addResetVolumeEvent();
                default:
                    if (Character.isLetter(songChar))
                        selectLetterEvent(songTrack, Character.toLowerCase(songChar));
                    else
                        if (Character.isDigit(songChar))
                            selectNumberEvent(songTrack, Character.getNumericValue(songChar));
                    break;
                        
            }
        }
        
    }

    private static void selectLetterEvent(SoundTrack songTrack, char songChar) throws InvalidMidiDataException 
    {
        int intEquiToNote;
        intEquiToNote = InputDecodifier.convertCharToInt(songChar);
        
        if (InputDecodifier.isValidNote(intEquiToNote))
            songTrack.addPlayNoteEvent(intEquiToNote);
        else
            if (InputDecodifier.isVogalLetter(songChar))
                songTrack.addDecreaseVolumeEvent();
            else
                songTrack.increaseBpm();
        
    }
    
    private static void selectNumberEvent(SoundTrack songTrack, int songCharAsNumber) 
    {
        if (songCharAsNumber%2 == 1)
            songTrack.increaseOctave();
        else
            songTrack.decreaseOctave();
    }
    
    
}