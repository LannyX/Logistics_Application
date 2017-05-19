package order;

import order.Order;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class OrderImpl implements Order{
	
	private String orderId;
	private int orderTime;
	private String orderDest;
	private Map<String, Integer> orderItems;
	
	public OrderImpl(String orderId, int orderTime, String orderDest, Map<String, Integer> orderItems){
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
		// TODO Auto-generated method stub
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

	
}
