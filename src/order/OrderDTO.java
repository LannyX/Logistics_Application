/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package order;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author dell
 */
public class OrderDTO {
    public String id;
    public int time;
    public String dest;
    public Map<String, Integer> items;
    
    public OrderDTO (String orderId,int orderTime,String orderDest,
                        Map<String, Integer> orderItems){
        id=orderId;
        time=orderTime;
        dest=orderDest;
        /*items=new TreeMap<String, Integer>();
        for (String item:orderItems.keySet()) {
            items.put(item, orderItems.get(item));
        }
        */
        /*items = new HashMap<String, Integer>();
        for (String item:orderItems.keySet()) {
            items.put(item, orderItems.get(item));
        }*/
        items=orderItems;
        //System.out.println (items);
    }

}
