package order;

import order.OrderImpl;
import order.Order;
import java.util.Map;

public class OrderImplFactory {

	public static Order createOrder(String orderId, int orderTime, String orderDest, 
									Map<String, Integer> orderItems){
		return new OrderImpl(orderId, orderTime, orderDest, orderItems);
	}
}
