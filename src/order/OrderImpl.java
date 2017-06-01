package order;

import exception.DataValidationException;
import exception.NullParamException;
import facility.FacilityMgr;
import item.ItemMgr;
import java.util.ArrayList;

/**
 * A concrete implementation of Order. It contains an order ID, order time,
 * order destination and a collection of order items.
 */
public class OrderImpl implements Order{
	
	private String orderId;
	private int orderTime;
	private String orderDest;
	private ArrayList<OrderItem> orderItems;
	
	public OrderImpl(String orderId, int orderTime, String orderDest, 
                        ArrayList<OrderItem> orderItems) 
                        throws NullParamException,DataValidationException{
            
            //Handle exceptions of input orderId
            if (orderId==null) throw new NullParamException("Null orderId is not allowed.");
            if (orderId.equals("")) throw new DataValidationException("Empty orderId is not allowed.");
            //Handle exception of orderTime            
            if (orderTime<=0) throw new DataValidationException("Non-positive order time is unreasonable.");
            
            //Handle exceptions of input orderDest
            if (orderDest==null) throw new NullParamException("Null order destination is not allowed.");
            if (orderDest.equals("")) throw new DataValidationException("Empty order destination is not allowed.");
            if (!FacilityMgr.getInstance().fcltExist(orderDest)) throw new DataValidationException("Destination is not found.");
            
            //Exceptions of item name and quantity               
            for (OrderItem item: orderItems) {
                if (item.getItem()==null) throw new NullParamException("Null item is not allowed.");
                if (item.getItem().equals("")) 
                    throw new DataValidationException("Empty item name is not allowed.");
                if (!ItemMgr.getInstance().itemExist(item.getItem()))
                    throw new DataValidationException("Order contains item not existing in catalog.");

                if (item.getQtt()<=0) 
                    throw new DataValidationException("Non-positive item quantity is unreasonable.");
            }
            
            this.orderId = orderId;
            this.orderTime = orderTime;
            this.orderDest = orderDest;
            this.orderItems = orderItems;
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
            System.out.println("-----------------------------------------------"
                    + "--------------------------------");
            System.out.println("Order #"+ orderId.charAt((orderId.length() -1) ));
            System.out.println("  Order Id: 	"+ orderId);
            System.out.println("  Order Time: 	Day "+ orderTime);
            System.out.println("  Destitation: 	"+ orderDest);
            printOrderItems();
	}


        @Override
	public void printOrderItems() {
            System.out.println("  List of Order Items: ");
            int i = 1;
            for(OrderItem item: orderItems){
                String itemWC = item.getItem() + ",";
                System.out.printf("	%d) Item ID:	%-8s	Quantity: %-4d\n", i, itemWC, item.getQtt());
                i++;
            }
        }

        
	@Override
	public ArrayList<OrderItem> getOrderItems() {
            ArrayList<OrderItem> orderItemsC=new ArrayList<OrderItem>(orderItems);
            return orderItemsC;
	}


	
}
