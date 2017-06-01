package order;

import java.util.ArrayList;

/**
 * Order data transfer object.
 */
public class OrderDTO {
    public String id;
    public int time;
    public String dest;
    public ArrayList<OrderItem> items;
    
    public OrderDTO (String orderId,int orderTime,String orderDest,
                        ArrayList<OrderItem> orderItems){
        id=orderId;
        time=orderTime;
        dest=orderDest;
        items=new ArrayList<>(orderItems);
    }

}
