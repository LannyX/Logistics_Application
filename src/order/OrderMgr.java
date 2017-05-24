package order;

import exception.DataValidationException;
import exception.NullParamException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class OrderMgr {

	private ArrayList<Order> orders;
	//private ArrayList<Order> backOrders;        
	private static OrderMgr orderMgr;
	
    private OrderMgr(){
        orders = OrderLoader.load();
    }
    
    public static OrderMgr getInstance(){
        if (orderMgr==null) orderMgr = new OrderMgr();
        return orderMgr;
    }
    
    public void printAllOrder() {
        for (int i=0; i<orders.size(); i++){
            orders.get(i).printOrder();
        }
        System.out.println("-------------------------------------------------------------------------------\n");
    }
    
    public void printOneOrder(int orderIdx) {
        orders.get(orderIdx).printOrder();
    }
    
    public OrderDTO getOrder (int i) {
        Order order=orders.get(i-1);
        //HashMap<String, Integer> orderItems=new HashMap<>();
        //LinkedHashMap<String, Integer> orderItems=new LinkedHashMap<>();
        //List<String> item = order.getItems();
        //System.out.println(item);
        /*for (String item:order.getItems()) {
            orderItems.put(item, order.getItemQtt(item));
            //System.out.println(orderItems);
        }
        */
        //List<String> item = new ArrayList<> (orderItems.keySet());
        //System.out.println(item);

        return new OrderDTO (order.getOrderId(),order.getOrderTime(),order.getDesination(),order.getOrderItems());
    }
    
    public int getOrderCount() {
        return orders.size();
    }
    
    //public void generateBackOrder()
    
}
