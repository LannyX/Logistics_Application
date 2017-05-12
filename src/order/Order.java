package order;

public interface Order {

	public String getOrderId();
	
	public int getOrderTime();
	
	public String getDesination();
	
	public void printOrder();
	
	public void printOrderItems();
}
