package logistic_application;

import java.util.Map;

public class Inventory {

	private String id;
	private int quantity;
	private Map<String, Integer> inventory;
	
	public Inventory(Map<String, Integer> inventory){
		this.inventory = inventory;
	}


	public void inventory(String id, int quantity){
		this.id = id;
		this.quantity = quantity;
	}
}
