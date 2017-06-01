package order;

import exception.DataValidationException;
import exception.NullParamException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

public class OrderLoader {

public static ArrayList<Order> load() {
        
        ArrayList<Order> orders = new ArrayList<Order>();
        try {
            String fileName = "Orders.xml";

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            File orderXml = new File(fileName);
            if (!orderXml.exists()) {
                System.err.println("**** XML File '" + fileName + "' cannot be found");
                System.exit(-1);
            }
            
            Document doc = db.parse(orderXml);
            doc.getDocumentElement().normalize();

            NodeList orderEntries = doc.getDocumentElement().getChildNodes();
            
            //create ArrayList storing facilities

            for (int i = 0; i < orderEntries.getLength(); i++) {
                if (orderEntries.item(i).getNodeType() == Node.TEXT_NODE) {
                    continue;
                }
                String entryName = orderEntries.item(i).getNodeName();
                if (!entryName.equals("Order")) {
                    System.err.println("Unexpected node found: " + entryName);
                    continue;
                }
                
                // Get a node attribute
                NamedNodeMap orderMap = orderEntries.item(i).getAttributes();
                
                Element order = (Element) orderEntries.item(i);
                String orderId = order.getElementsByTagName("Id").item(0).getTextContent();
                String orderTimeS = order.getElementsByTagName("OrderTime").item(0).getTextContent();
                String orderDest = order.getElementsByTagName("Destination").item(0).getTextContent();
                int orderTime = Integer.parseInt(orderTimeS);

                // Store pairs of <Name, Distance> in neighbors, <Id, Quantity> in inventories
                ArrayList<OrderItem> items = new ArrayList<OrderItem>(); 
                
                NodeList itemList = order.getElementsByTagName("Item");
                
                // Get Neighbors - there can be 0 or more
                for (int j = 0; j < itemList.getLength(); j++) {
                    if (itemList.item(j).getNodeType() == Node.TEXT_NODE) {
                        continue;
                    }
                    entryName = itemList.item(j).getNodeName();
                    if (!entryName.equals("Item")) {
                        System.err.println("Unexpected node found: " + entryName);
                        continue;
                    }

                    // Get neighbors' information
                    order = (Element) itemList.item(j);
                    String itemName = order.getElementsByTagName("Name").item(0).getTextContent();
                    String itemQtyS = order.getElementsByTagName("Qty").item(0).getTextContent();               
                    int itemQty = Integer.parseInt(itemQtyS);
                    OrderItem item=new OrderItem(itemName,itemQty);
                    // Put into ArrayList
                    items.add(item);
                    //System.out.println(items);
                }

                Order orderobj = OrderFactory.createOrder(orderId, 
                                    orderTime, orderDest, items);
                orders.add(orderobj);
            }
        } catch (NullParamException | DataValidationException | ParserConfigurationException 
                | SAXException | IOException | DOMException e) {
            System.out.println(e.getMessage());
        }
        
        return orders;
    }
}
