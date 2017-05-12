
package facility;

import exception.DataValidationException;
import exception.NullParamException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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
* @ClassName: FacilityLoader
* @Description: load information through xml file and create facilites
*/
public class FacilityLoader {
    
    /** 
    * @Title: load
    * @Description: The only static method in class to load and create facilities
    */   
    public static HashMap<String, Facility> load() {
        
        HashMap<String, Facility> fclts = new HashMap <String, Facility>();
        try {
            String fileName = "facilities.xml";

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            File fcltXml = new File(fileName);
            if (!fcltXml.exists()) {
                System.err.println("**** XML File '" + fileName + "' cannot be found");
                System.exit(-1);
            }
            
            Document doc = db.parse(fcltXml);
            doc.getDocumentElement().normalize();

            NodeList fcltEntries = doc.getDocumentElement().getChildNodes();
            
            //create ArrayList storing facilities

            for (int i = 0; i < fcltEntries.getLength(); i++) {
                if (fcltEntries.item(i).getNodeType() == Node.TEXT_NODE) {
                    continue;
                }
                String entryName = fcltEntries.item(i).getNodeName();
                if (!entryName.equals("Facility")) {
                    System.err.println("Unexpected node found: " + entryName);
                    continue;
                }
                
                // Get a node attribute
                NamedNodeMap fcltMap = fcltEntries.item(i).getAttributes();
                
                // Get information of a node 
                String fcltId = fcltMap.getNamedItem("Id").getNodeValue();
                
                Element fclt = (Element) fcltEntries.item(i);
                String fcltName = fclt.getElementsByTagName("Name").item(0).getTextContent();
                String fcltRateS = fclt.getElementsByTagName("Rate").item(0).getTextContent();
                String fcltCostS = fclt.getElementsByTagName("Cost").item(0).getTextContent();
                int fcltRate = Integer.parseInt(fcltRateS);
                int fcltCost = Integer.parseInt(fcltCostS);

                // Store pairs of <Name, Distance> in neighbors, <Id, Quantity> in inventories
                Map <String, Integer> neighbors = new HashMap<String, Integer>(); 
                Map <String, Integer> inventories = new HashMap<String, Integer>();
                
                NodeList neighborList = fclt.getElementsByTagName("Link");
                NodeList inventoryList = fclt.getElementsByTagName("Inventory");
                
                // Get Neighbors - there can be 0 or more
                for (int j = 0; j < neighborList.getLength(); j++) {
                    if (neighborList.item(j).getNodeType() == Node.TEXT_NODE) {
                        continue;
                    }
                    entryName = neighborList.item(j).getNodeName();
                    if (!entryName.equals("Link")) {
                        System.err.println("Unexpected node found: " + entryName);
                        continue;
                    }

                    // Get neighbors' information
                    fclt = (Element) neighborList.item(j);
                    String neighborName = fclt.getElementsByTagName("Name").item(0).getTextContent();
                    String neighborDistS = fclt.getElementsByTagName("Distance").item(0).getTextContent();               
                    int neighborDist = Integer.parseInt(neighborDistS);
                    // Put into Hashmap
                    neighbors.put(neighborName, neighborDist);
                }
                        
                // Get Inventories - there can be 0 or more

                for (int k = 0; k < inventoryList.getLength(); k++) {
                    if (inventoryList.item(k).getNodeType() == Node.TEXT_NODE) {
                        continue;
                    }
                    entryName = inventoryList.item(k).getNodeName();
                    if (!entryName.equals("Inventory")) {
                        System.err.println("Unexpected node found: " + entryName);
                        continue;
                    }

                    // Get inventories' information
                    fclt = (Element) inventoryList.item(k);
                    String inventoryItemID = fclt.getElementsByTagName("ItemID").item(0).getTextContent();
                    String inventoryQttS = fclt.getElementsByTagName("Quantity").item(0).getTextContent();
                    int inventoryQtt = Integer.parseInt(inventoryQttS);
                    // Put into Hashmap
                    inventories.put(inventoryItemID, inventoryQtt); 
                }
                
                // Handle duplicate facility data
                if (fclts.containsKey(fcltName)) {
                    System.err.println("Duplicate facility data found: " + fcltName);
                    continue;
                }
                Facility facility = FacilityFactory.createFacility(fcltName, 
                                    fcltRate, fcltCost, neighbors, new Inventory(inventories));
                fclts.put(fcltName,facility);
            }

        } catch (NullParamException | DataValidationException | ParserConfigurationException 
                | SAXException | IOException | DOMException e) {
            System.out.println(e.getMessage());
        }
        return fclts;
    }
}
