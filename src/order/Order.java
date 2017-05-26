package order;


import java.util.ArrayList;

public interface Order {

	public String getOrderId();
	
	public int getOrderTime();
	
	public String getDesination();
        
	//public Collection<String> getItems();
        
	//public List<String> getItems();
    //public int getItemQtt(String item);
    
    public ArrayList<OrderItem> getOrderItems();
	
	public void printOrder();
	
	public void printOrderItems();
}
