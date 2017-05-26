package order;

import exception.DataValidationException;
import exception.NullParamException;
import java.util.ArrayList;

public class OrderFactory {

	public static Order createOrder(String orderId, int orderTime, String orderDest, 
			ArrayList<OrderItem> orderItems) throws NullParamException, DataValidationException{
		return new OrderImpl(orderId, orderTime, orderDest, orderItems);
	}
}
