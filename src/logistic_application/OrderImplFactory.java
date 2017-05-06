package logistic_application;

import java.util.Map;

public class OrderImplFactory {

	public static Order createOrder(String orderId, int orderTime, String orderDest, 
									Map<String, Integer> orderItems){
		return new OrderImpl(orderId, orderTime, orderDest, orderItems);
	}
}
