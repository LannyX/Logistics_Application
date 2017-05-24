package order;

import exception.DataValidationException;
import exception.NullParamException;
import facility.FacilityMgr;
import item.Item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class OrderImpl implements Order{
	
	private String orderId;
	private int orderTime;
	private String orderDest;
	private Map<String, Integer> orderItems;
	
	public OrderImpl(String orderId, int orderTime, String orderDest, 
                        Map<String, Integer> orderItems) 
                        throws NullParamException,DataValidationException{
            
            //Handle exceptions of input orderId
            if (orderId==null) throw new NullParamException("Null orderId is not allowed.");
            if (orderId.equals("")) throw new DataValidationException("Empty orderId is not allowed.");
            //Handle exception of orderTime            
            if (orderTime<0) throw new DataValidationException("Negative order time is unreasonable.");
            
            //Handle exceptions of input orderDest
            if (orderDest==null) throw new NullParamException("Null order destination is not allowed.");
            if (orderDest.equals("")) throw new DataValidationException("Empty order destination is not allowed.");
            if (!FacilityMgr.getInstance().fcltExist(orderDest)) throw new DataValidationException("Destination is not found.");
            
            //Exceptions of items               
            for (String item: orderItems.keySet()) {
                if (item==null) throw new NullParamException("Null item is not allowed.");
                if (item.equals("")) 
                    throw new DataValidationException("Empty item name is not allowed.");
                if (orderItems.get(item)<=0) 
                    throw new DataValidationException("Non-positive item quantity is unreasonable.");
            }
            
            this.orderId = orderId;
            this.orderTime = orderTime;
            this.orderDest = orderDest;
            this.orderItems = orderItems;
            //System.out.println(this.orderItems);
	}
	
	@Override
	public String getOrderId() {
		return orderId;
	}

	@Override
	public int getOrderTime() {
		return orderTime;
	}

	@Override
	public String getDesination() {
		return orderDest;
	}



	@Override
	public void printOrder() {
		// TODO Auto-generated method stub
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("Order #"+ orderId.charAt((orderId.length() -1) ));
        System.out.println("  Order Id: 	"+ orderId);
        System.out.println("  Order Time: 	Day "+ orderTime);
        System.out.println("  Destitation: 	"+ orderDest);
        //System.out.println("	List of Order Items: "+ orderItems);
        
        printOrderItems();
	}



	@Override
	public void printOrderItems() {
        System.out.println("  List of Order Items: ");
        Map<String, Integer> orderItemsSorted = new TreeMap<String, Integer>(orderItems);
        int i = 1;
        for(String item: orderItemsSorted.keySet()){
        	String itemWC = item + ",";
        	if (orderItemsSorted.get(item) > 0){
        		System.out.printf("	%d) Item ID:	%-8s	Quantity: %-4d\n", i, itemWC, orderItemsSorted.get(item));
        	}
        	i++;
        }
		
	}
/*
	@Override
    public Collection<String> getItems() {
        Collection<String> items=orderItems.keySet();
        return items;
    }

    @Override

    public List<String> getItems() {
        List<String> itemsCopy=new ArrayList<>();
        for (Map.Entry<String, Integer> entry: orderItems.entrySet()){
        	itemsCopy.add(entry.getKey());
        	System.out.println(itemsCopy);
        }
        return itemsCopy;
    }

    @Override
    public int getItemQtt(String item) {
        return orderItems.get(item);
    }
*/
	@Override
	public Map<String, Integer> getOrderItems() {
		// TODO Auto-generated method stub
		Map<String, Integer> orderItemsMap = new TreeMap<String, Integer>(orderItems);
		//Map<String, Integer> orderItemsMap = new LinkedHashMap<String, Integer>(orderItems);
		System.out.println(orderItemsMap);
		/*for (String item:orderItemsMap.keySet()) {
            orderItemsMap.put(item, orderItemsMap.get(item));
         
        	//System.out.println(orderItemsMap);
               
	}*/
		return orderItemsMap;
		//return this.orderItems;
	}

	
}
