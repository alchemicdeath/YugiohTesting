import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.print.Doc;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 */
public class buildCardList2
{
    public static void main(String[] args) throws FileNotFoundException {
        // Placeholders for card information.
        String name = "", lobR = "", dlR = "", number = "", setNumber = "";
        // For input from user.
        Scanner scanner = new Scanner(System.in);
        System.out.println("What set do you wish to edit?: ");
        setNumber = scanner.nextLine();

        // FileOutputStream output = new FileOutputStream(file);

        File file = new File("src/main/resources/" + setNumber + ".xml");
        if (file.exists())
        {
            System.out.println("Does Exist");
        }
        else
        {
            System.out.println("Does not Exist");
        }
    }

    private static void createNewXml(String setNumber)
    {

    }

    private static void addElement(Document doc, String number)
    {
        String filename = doc + ".xml";
        // get the files nodelist
        NodeList cards = doc.getElementsByTagName("card");

        for (int i = 0; i < cards.getLength(); i++)
        {
            Node card = cards.item(i);

            card.getAttributes().getNamedItem("cardNumber")
                                    .setTextContent(number);
            Element cardName = doc.createElement("cardName");
            cardName.appendChild(doc.createTextNode(""));
            card.appendChild(cardName);
        }

    }
}
