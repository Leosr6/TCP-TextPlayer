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
    
    public static Sequence createSoundSequence(List<Instrument> instruments, String textSong, int bpm) throws InvalidMidiDataException
    {
        
        song = textSong;
        Sequence songSequence = new Sequence(Sequence.PPQ, bpm);
        SoundTrack songTrack = new SoundTrack(songSequence, instruments, bpm);
        createEvents(songTrack);
        
        return songSequence;
    }
    
    private static void createEvents(SoundTrack songTrack) throws InvalidMidiDataException
    {
        
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
                    songTrack.ResetOctave();
                    break;
                case '\n':
                    songTrack.addChangeInstrumentEvent();
                    break;
                case ';':
                    songTrack.addDecreaseBpmEvent();
                    break;
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
        intEquiToNote = InputDecodefier.convertCharToInt(songChar);
        
        if (InputDecodefier.isVallidNote(intEquiToNote))
            songTrack.addPlayNoteEvent(intEquiToNote);
        else
            if (InputDecodefier.isVogalLetter(songChar))
                songTrack.addDecreaseVolumeEvent();
            else
                songTrack.addIncreaseBpmEvent();
        
    }
    
    private static void selectNumberEvent(SoundTrack songTrack, int songCharAsNumber) 
    {
        if (Integer.lowestOneBit(songCharAsNumber) == 1)
            songTrack.IncreaseOctave();
        else
            songTrack.DecreaseOctave();
    }
    
    
}