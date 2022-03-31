import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class buildCardList
{
    public static void main(String[] args) throws ParserConfigurationException,
            TransformerException, IOException, SAXException
    {
        // Placeholders for card information.
        String name = "", lobR = "", dlR = "", number = "", setNumber = "";

        // For input from user.
        Scanner scanner = new Scanner(System.in);

        // Document Builder.
        DocumentBuilderFactory docFactory =
                DocumentBuilderFactory.newInstance();
        docFactory.setIgnoringElementContentWhitespace(true);
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // ---------------------------------------------------------------------
        // Get the cards Set Id/
        System.out.println("What set do you wish to edit?: ");
        setNumber = scanner.nextLine();

        // Set the card set file name.
        File file = new File("src/main/resources/" + setNumber + ".xml");
        Document doc;
        Element root;

        // ---------------------------------------------------------------------
        // Read the card set file.
        Scanner fileInput = new Scanner("src/main/resources/" + setNumber +
                "xml");

        //----------------------------------------------------------------------
        // Get the new cards information.
        System.out.println("What is the cards name: ");
        name = scanner.nextLine();

        System.out.println("What is the cards lob rarity: ");
        lobR = scanner.nextLine();

        System.out.println("What is the cards duel links rarity: ");
        dlR = scanner.nextLine();

        System.out.println("What is the cards number: ");
        number = scanner.nextLine();
        // ---------------------------------------------------------------------

        if (file.exists())
        {
            doc = docBuilder.parse(file);
            root = doc.getDocumentElement();

            System.out.println(root);

            Collection<cardObj> cards = new ArrayList<cardObj>();
            cards.add(new cardObj(name, lobR, dlR, number));

            for (cardObj newCard : cards)
            {
                // Add XML elements
                Element set = doc.createElement("card");
                //Add XML attribute
                set.setAttribute("cardNumber", number);
                root.appendChild(set);
                Element cardName = doc.createElement("cardName");
                cardName.appendChild(doc.createTextNode(name));
                set.appendChild(cardName);
                Element lobRarity = doc.createElement("lobRarity");
                lobRarity.appendChild(doc.createTextNode(lobR));
                set.appendChild(lobRarity);
                Element dlRarity = doc.createElement("duelLinksRarity");
                dlRarity.appendChild(doc.createTextNode(dlR));
                set.appendChild(dlRarity);

                root.appendChild(set);
            }
        }
        else
        {
            doc = docBuilder.newDocument();
            root = doc.createElement("set");

            // Add XML elements
            Element set = doc.createElement("card");
            //Add XML attribute
            set.setAttribute("cardNumber", number);
            root.appendChild(set);
            Element cardName = doc.createElement("cardName");
            cardName.appendChild(doc.createTextNode(name));
            set.appendChild(cardName);
            Element lobRarity = doc.createElement("lobRarity");
            lobRarity.appendChild(doc.createTextNode(lobR));
            set.appendChild(lobRarity);
            Element dlRarity = doc.createElement("duelLinksRarity");
            dlRarity.appendChild(doc.createTextNode(dlR));
            set.appendChild(dlRarity);

            doc.appendChild(root);
        }

        // ---------------------------------------------------------------------
        try
        {
            if (file.createNewFile())
            {
                writeXml(doc, file);
            }
            else
            {
                writeXml(doc, file);
            }
        }
        catch (IOException e)
        {
            System.out.println("Error");
            e.printStackTrace();
        }
    }
// #############################################################################
    // Write doc to output stream
    private static void writeXml(Document doc, File output)
            throws TransformerException
    {
        TransformerFactory transformerFactory =
                TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        // ---------------------------------------------------------------------
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);
        transformer.transform(source, result);
    }
}
