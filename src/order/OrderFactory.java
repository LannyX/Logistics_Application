package order;

import exception.DataValidationException;
import exception.NullParamException;
import java.util.Map;

public class OrderFactory {

	public static Order createOrder(String orderId, int orderTime, String orderDest, 
			Map<String, Integer> orderItems) throws NullParamException, DataValidationException{
		return new OrderImpl(orderId, orderTime, orderDest, orderItems);
	}
}
