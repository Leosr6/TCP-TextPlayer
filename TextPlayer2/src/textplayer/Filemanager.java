package textplayer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.filechooser.*;

/**
 *
 * @author gnmartins
 */
public class Filemanager {

    private static File handler;
    
    public Filemanager()
    {
        
    }
    
    public static List<String> loadFromFile()
    {
        JFileChooser chooser = new JFileChooser();
        FileFilter f = chooser.getFileFilter();
        chooser.removeChoosableFileFilter(f);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos .txt", "txt", "text");
        chooser.setFileFilter(filter);
        int retrival = chooser.showOpenDialog(null);
        if (retrival == JFileChooser.APPROVE_OPTION)
        {
            handler = chooser.getSelectedFile();
            return load();
        }    
        else
        {
            System.out.println("Escolha um arquivo existente ou um nome válido.");
            return null;
        }
    }
    
    public static boolean saveToFile(String content)
    {
        JFileChooser chooser = new JFileChooser();
        int retrival = chooser.showSaveDialog(null);
        if (retrival == JFileChooser.APPROVE_OPTION)
        {
            handler = new File(chooser.getSelectedFile() + ".txt");
            return save(content);
        }    
        else
        {
            System.out.println("Escolha um arquivo existente ou um nome válido.");
            return false;
        }
            
    }
    
    private static List<String> load() 
    {
        List<String> text = new ArrayList<String>();
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(handler))) 
        {
            while ((line = reader.readLine()) != null)
                text.add(line);
            reader.close();
            return text;
        }
        catch (Exception e)
        {
            System.err.format("Error trying to read '%s'.", handler.getName());
            return null;
        }
    }
    
    private static boolean save(String content)
    {
       
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(handler)))
        {
            writer.write(content);
            writer.close();
            return true;
        }
        catch (Exception e)
        {
            System.err.format("Error trying to write to '%s'.", handler.getName());
            return false;
        }
       
    }
    
    
}