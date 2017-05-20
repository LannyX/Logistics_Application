package order;

import java.util.Collection;

public interface Order {

	public String getOrderId();
	
	public int getOrderTime();
	
	public String getDesination();
        
        public Collection<String> getItems();
        
        public int getItemQtt(String item);
	
	public void printOrder();
	
	public void printOrderItems();
}
