package order;

import java.util.ArrayList;

public interface Order {
    
    public String getOrderId();
    
    public int getOrderTime();
    
    public String getDesination();
    
    public ArrayList<OrderItem> getOrderItems();
    
    public void printOrder();
    
    public void printOrderItems();
}
