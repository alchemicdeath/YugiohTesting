import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.print.Doc;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 *
 */
public class buildCardList2
{
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {

        List<cardObj> cards = new ArrayList<>();

        // Placeholders for card information.
        String name = "", lobR = "", dlR = "", number = "", setNumber = "";

        // For input from user.
        Scanner scanner = new Scanner(System.in);
        System.out.println("What set do you wish to edit?: ");
        setNumber = scanner.nextLine();

        File file = new File("src/main/resources/" + setNumber + ".xml");

        // Document Builder
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(file);

        // Gets all elements with the tag card
        NodeList nodeList = doc.getElementsByTagName("card");
        doc.getDocumentElement().normalize();

        for (int itr = 0; itr < nodeList.getLength(); itr++)
        {
            Node node = nodeList.item(itr);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element eElement = (Element) node;

                number = eElement.getElementsByTagName("cardNumber").item
                        (0).getTextContent();
                name = eElement.getElementsByTagName("cardName").item(0).
                        getTextContent();
                System.out.println(name);
                dlR = eElement.getElementsByTagName("duelLinksRarity").item
                        (0).getTextContent();
                lobR =  eElement.getElementsByTagName("lobRarity").item
                        (0).getTextContent();
            }
            cards.add(new cardObj(name, lobR, dlR, number));
        }
        System.out.println(cards.get(0));

        for (int i = 0; i < cards.size(); i++)
        {
            System.out.println((cards.get(i)).getName());
        }

        if (file.exists())
        {
            System.out.println("Does Exist");
        }
        else
        {
            System.out.println("Does not Exist");
        }
    }}
