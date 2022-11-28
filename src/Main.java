//package lab_12_FileAway;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardOpenOption.CREATE;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main
{
    public static void main(String[] args) throws FileNotFoundException
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        File selectedFile;
        String lineStr;

        try
        {
            File dir = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(dir);
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile();
                Path path = selectedFile.toPath();
                InputStream in = new BufferedInputStream(Files.newInputStream(path, CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                int totalLines = 0;
                int totalWords = 0;
                int totalChars = 0;
                while (reader.ready())
                {
                    lineStr = reader.readLine();
                    String[] wordsArray = lineStr.split(" ");
                    totalWords += wordsArray.length;
                    totalChars += lineStr.length();
                    totalLines++;
                }
                System.out.println("File Selected: " + selectedFile.getName());
                System.out.println("File Path: " + path);
                System.out.println("Total lines in file : " + totalLines);
                System.out.println("Total words in file : " + totalWords);
                System.out.println("Total characters in file : " + totalChars);
                reader.close();
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!!!");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
