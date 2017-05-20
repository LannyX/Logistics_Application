package order;

import order.Order;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class OrderMgr {

	private ArrayList<Order> orders;
	private static OrderMgr orderMgr;
	
    private OrderMgr(){
        orders = OrderLoader.load();
    }
    
    public static OrderMgr getInstance(){
        if (orderMgr==null) orderMgr = new OrderMgr();
        return orderMgr;
    }
    
    public void printReport(){
        for (int i=0; i<orders.size(); i++){
            orders.get(i).printOrder();
        }
    }
    
    public OrderDTO getOrder (int i) {
        Order order=orders.get(i);
        HashMap<String, Integer> orderItems=new HashMap<>();
        for (String item:order.getItems()) {
            orderItems.put(item, order.getItemQtt(item));
        }
        return new OrderDTO (order.getOrderId(),order.getOrderTime(),order.getDesination(),orderItems);
    }
    
    public int getOrderCount() {
        return orders.size();
    }
    
    
}
