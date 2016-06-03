package textplayer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.*;

/**
 *
 * @author gnmartins
 */
public class FileManager {

    private static File handler;
    
    public static List<String> loadFromFile()
    {
        JFileChooser chooser = new JFileChooser();
        //Setting to choose only from .txt files
        FileFilter f = chooser.getFileFilter();
        chooser.removeChoosableFileFilter(f);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files (*.txt)", "txt", "text");
        chooser.setFileFilter(filter);
        
        int chosen = chooser.showOpenDialog(null);
        if (chosen == JFileChooser.APPROVE_OPTION)
        {
            handler = chooser.getSelectedFile();
            return read();
        }    
        return null;
    }
    
    public static void saveToFile(String content)
    {
        JFileChooser chooser = new JFileChooser();
        int chosen = chooser.showSaveDialog(null);
        if (chosen == JFileChooser.APPROVE_OPTION)
        {
            handler = chooser.getSelectedFile();
            //If file with selected name doesn't already exists, 
            //appends '.txt' to the selected file name.
            if (!handler.exists()) 
                handler = new File(chooser.getSelectedFile() + ".txt");
            boolean success = write(content);
            if (success)
            {
                JOptionPane.showMessageDialog(null,
                        "File saved.");
            }
            else
            {
                JOptionPane.showMessageDialog(null,
                        "Error saving file.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }    
    }
    
    private static List<String> read() 
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
            JOptionPane.showMessageDialog(null,
                    "Error trying to read file '" + handler.getName() + "'.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    private static boolean write(String content)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(handler)))
        {
            //Sets the breaklines to default from system
            content = content.replace("\n", System.getProperty("line.separator"));
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