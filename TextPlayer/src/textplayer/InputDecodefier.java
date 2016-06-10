/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textplayer;

/**
 *
 * @author leonardo.rosa
 */
public class InputDecodefier {
    
    public static boolean isVallidNote(int intEquiToNote)
    {
        return intEquiToNote >= 0;
    }
    
    public static boolean isVogalLetter(char character)
    {
        return character == 'o' || character == 'i' || character == 'u';
    }
    
    public static int convertCharToInt(char character)
    {
        int intEquiToNote;
        
        switch(character)
        {
            case 'a':
                intEquiToNote = 21;
                break;
            case 'b':
                intEquiToNote = 23;
                break;
            case 'c':
                intEquiToNote = 12;
                break;
            case 'd':
                intEquiToNote = 14;
                break;
            case 'e':
                intEquiToNote = 16;
                break;
            case 'f':
                intEquiToNote = 17;
                break;
            case 'g':
                intEquiToNote = 19;
                break;
            default:
                intEquiToNote = -1;
                break;
        }
        
        return intEquiToNote;
    }
}
