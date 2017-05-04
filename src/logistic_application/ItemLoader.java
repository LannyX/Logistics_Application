package logistic_application;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/** 
* @ClassName: ItemLoader
* @Description: load information through xml file and create items
*/
public class ItemLoader {

	   
    /** 
    * @Title: load 
    * @Description: The only static method in class to create items
    */ 
    public static ArrayList<Item> load() {
        
        ArrayList<Item> itemList = new ArrayList<Item>();
        try {
            String fileName = "Items.xml";

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            File itemXml = new File(fileName);
            if (!itemXml.exists()) {
                System.err.println("**** XML File '" + fileName + "' cannot be found");
                System.exit(-1);
            }
            
            Document doc = db.parse(itemXml);
            doc.getDocumentElement().normalize();

            NodeList itemEntries = doc.getDocumentElement().getChildNodes();
            
            //create ArrayList storing facilities

            for (int i = 0; i < itemEntries.getLength(); i++) {
                if (itemEntries.item(i).getNodeType() == Node.TEXT_NODE) {
                    continue;
                }
                String entryName = itemEntries.item(i).getNodeName();
                if (!entryName.equals("Item")) {
                    System.err.println("Unexpected node found: " + entryName);
                    continue;
                }
                
                // Get a node attribute
                //NamedNodeMap fcltMap = itemEntries.item(i).getAttributes();
                
                // Get information of a node 
                //String fcltId = fcltMap.getNamedItem("Id").getNodeValue();
                
                Element fclt = (Element) itemEntries.item(i);
                String itemId = fclt.getElementsByTagName("Id").item(0).getTextContent();
                String itemPriceS = fclt.getElementsByTagName("Price").item(0).getTextContent();
                int itemPrice = Integer.parseInt(itemPriceS);

                Item item = ItemImplFactory.createItem(itemId, itemPrice);
                itemList.add(item);
            }

        } catch (ParserConfigurationException | SAXException | IOException | DOMException e) {
            e.printStackTrace();
        }
        
        return itemList;
    }
	
}
