package order;

import exception.DataValidationException;
import exception.NullParamException;
import facility.FacilityMgr;
import java.util.Collection;
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
	}
	
	@Override
	public String getOrderId() {
		// TODO Auto-generated method stub
		return orderId;
	}

	@Override
	public int getOrderTime() {
		// TODO Auto-generated method stub
		return orderTime;
	}

	@Override
	public String getDesination() {
		// TODO Auto-generated method stub
		return orderDest;
	}



	@Override
	public void printOrder() {
		// TODO Auto-generated method stub
		System.out.println("------------------------------------------------------------------------");
		System.out.println("Order #"+ orderId.charAt((orderId.length() -1) ));
        System.out.println("  Order Id: 	"+ orderId);
        System.out.println("  Order Time: 	Day "+ orderTime);
        System.out.println("  Destitation: 	"+ orderDest);
        //System.out.println("	List of Order Items: "+ orderItems);
        printOrderItems();
		System.out.println("------------------------------------------------------------------------\n");
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

    @Override
    public Collection<String> getItems() {
        Collection<String> items=orderItems.keySet();
        return items;
    }

    @Override
    public int getItemQtt(String item) {
        return orderItems.get(item);
    }
    
    

	
}
