package logistic_application;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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

public class Facility_loader {

  public static void main(String[] args) {

        try {
            String fileName = "facility_network.xml";

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            File xml = new File(fileName);
            if (!xml.exists()) {
                System.err.println("**** XML File '" + fileName + "' cannot be found");
                System.exit(-1);
            }
            
            Document doc = db.parse(xml);
            doc.getDocumentElement().normalize();

            NodeList fcltNetworkEntries = doc.getDocumentElement().getChildNodes();

            for (int i = 0; i < fcltNetworkEntries.getLength(); i++) {
                if (fcltNetworkEntries.item(i).getNodeType() == Node.TEXT_NODE) {
                    continue;
                }
                
                String entryName = fcltNetworkEntries.item(i).getNodeName();
                if (!entryName.equals("Facility")) {
                    System.err.println("Unexpected node found: " + entryName);
                    return;
                }
                
                // Get a node attribute
                NamedNodeMap fcltNetworkMap = fcltNetworkEntries.item(i).getAttributes();
                String fcltId = fcltNetworkMap.getNamedItem("Id").getNodeValue();

                // Get information of a node 
                Element fcltNetwork = (Element) fcltNetworkEntries.item(i);
                String fcltName = fcltNetwork.getElementsByTagName("Name").item(0).getTextContent();
                String fcltRate = fcltNetwork.getElementsByTagName("Rate").item(0).getTextContent();
                String fcltCost = fcltNetwork.getElementsByTagName("Cost").item(0).getTextContent();
                
                int fcltRateint = Integer.parseInt(fcltRate);
                int fcltCostint = Integer.parseInt(fcltCost);
                // Get Links - there can be 0 or more
                ArrayList<String> linksDescriptions = new ArrayList<>();
                NodeList linksList = fcltNetwork.getElementsByTagName("Link");
                for (int j = 0; j < linksList.getLength(); j++) {
                    if (linksList.item(j).getNodeType() == Node.TEXT_NODE) {
                        continue;
                    }

                    entryName = linksList.item(j).getNodeName();
                    if (!entryName.equals("Link")) {
                        System.err.println("Unexpected node found: " + entryName);
                        return;
                    }

                    // Get some named nodes
                    fcltNetwork = (Element) linksList.item(j);
                    String neighborName = fcltNetwork.getElementsByTagName("Name").item(0).getTextContent();
                    String neighborDist = fcltNetwork.getElementsByTagName("Distance").item(0).getTextContent();               
                    // Create a string summary of the book
                    linksDescriptions.add(neighborName + " is " + neighborDist + " miles away" );

                }
                        
                // Get all nodes named "Inventory" - there can be 0 or more
                ArrayList<String> inventoryDescriptions = new ArrayList<>();
                NodeList inventoryList = fcltNetwork.getElementsByTagName("Inventory");
                for (int j = 0; j < inventoryList.getLength(); j++) {
                    if (inventoryList.item(j).getNodeType() == Node.TEXT_NODE) {
                        continue;
                    }

                    entryName = inventoryList.item(j).getNodeName();
                    if (!entryName.equals("Inventory")) {
                        System.err.println("Unexpected node found: " + entryName);
                        return;
                    }

                    // Get some named nodes
                    fcltNetwork = (Element) inventoryList.item(j);
                    String inventoryItemID = fcltNetwork.getElementsByTagName("ItemID").item(0).getTextContent();
                    String inventoryQ = fcltNetwork.getElementsByTagName("Quantity").item(0).getTextContent();
        
                    System.out.println( inventoryItemID);
                    // Create a string summary of the book
                    inventoryDescriptions.add("InventoryItemID: " + inventoryItemID + " Quantity: " + inventoryQ + ". " );
                    //+ bookDate + " [" + bookIsbn13 + "]");
                }

                Facility facility = FacilityImplFactory.loadFacility(fcltName, fcltRateint, fcltCostint);
                
                System.out.println(facility.getfcltName() + facility.getfcltRate()+ facility.getfcltCost());
                // Here I would create a Store object using the data I just loaded from the XML
                //System.out.println("Facility name: "+ fcltName +" [Rate /day:" + fcltRate + " Cost: $" + fcltCost + "] \n" + linksDescriptions +"\n" + inventoryDescriptions + "\n");
                
            }

        } catch (ParserConfigurationException | SAXException | IOException | DOMException e) {
            e.printStackTrace();
        }
    }

    
}