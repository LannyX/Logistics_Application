package logistic_application;

import java.util.ArrayList;
import java.util.Collection;

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
        //Collection<Order> orderObj=orders.get();
        for (int i=0; i<orders.size(); i++){
            orders.get(i).printOrder();
        }
    }
}
