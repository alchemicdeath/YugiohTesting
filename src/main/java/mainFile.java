import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 */
public class mainFile
{
    static ArrayList<String> setList;

    public static void main(String[] args) throws IOException
    {
        loadFile();
        // Call method to build the list of sets.
        menu();
    }

    public static void menu()
    {
        int choice;
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. New card set\n2. Add card to set\n3. Exit");
        choice = scanner.nextInt();
        if (choice == 1)
        {
            newSet();
        }
        if (choice == 3)
        {
            System.exit(0);
        }
    }

    // Write to the set file
    public void outFile()
    {

    }

    // Load the set file
    public static void loadFile() throws IOException
    {
        Scanner s = new Scanner(new File("src/main/resources/setList.txt"));
        setList = new ArrayList<>();
        while (s.hasNext())
        {
            setList.add(s.next());
        }
        s.close();

        // For Testing
        for (int i = 0; i < setList.size(); i++)
        {
            System.out.println(setList.get(i));
        }
    }

    // Load the sets XML
    public void loadSet()
    {

    }

    // Write out the new set XML
    public static void newSet()
    {
        Scanner scanner = new Scanner(System.in);
        String setName, setIdentifier;
        System.out.println("What is the Set Name: ");
        setName = scanner.nextLine();
        System.out.println("What is the set Identifier: ");
        setIdentifier = scanner.nextLine();

        setList.add(setIdentifier);

        // Build new Set file.
        try
        {
            File file = new File("src/main/resources/" + setName + ".xml");
            if(file.createNewFile())
            {
                System.out.println("File created: " + file);
            }
            else
            {
                System.out.println("File Exists");
            }
        }
        catch (IOException e)
        {
            System.out.println("Error");
            e.printStackTrace();
        }

        // Append to Set List
        try
        {
            File file = new File("src/main/resources/setList.txt");
            if(file.createNewFile())
            {
                System.out.println("File created: " + file);
                try
                {
                    FileWriter fw = new FileWriter(file);
                    for (int i = 0; i < setList.size(); i++) {
                        fw.write(setList.get(i) + "\n");
                    }
                    fw.close();
                }
                catch(IOException ioe)
                {
                    System.err.println("IOException: " + ioe.getMessage());
                }
            }
            else
            {
                System.out.println("File Exists");
                try
                {
                    FileWriter fw = new FileWriter(file,true);
                    fw.write(setIdentifier + "\n");
                    fw.close();
                }
                catch(IOException ioe)
                {
                    System.err.println("IOException: " + ioe.getMessage());
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("Error");
            e.printStackTrace();
        }
    }
}
