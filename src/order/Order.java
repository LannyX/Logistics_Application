package order;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import item.Item;

public interface Order {

	public String getOrderId();
	
	public int getOrderTime();
	
	public String getDesination();
        
	//public Collection<String> getItems();
        
	//public List<String> getItems();
    //public int getItemQtt(String item);
    
    public Map<String, Integer> getOrderItems();
	
	public void printOrder();
	
	public void printOrderItems();
}
