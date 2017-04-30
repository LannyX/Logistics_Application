package logistic_application;

import java.util.Map;

public class Inventory {

	//private String id;
	//private int quantity;
	private Map<String, Integer> inventory;
	
	public Inventory(Map<String, Integer> inventory){
		this.inventory = inventory;
	}

//	public void inventory(String id, int quantity){
//		this.id = id;
//		this.quantity = quantity;
//	}

        
        // Hide the data structure(HashMap) by providing method
        public boolean ownsItem(String itemId){
            return inventory.containsKey(itemId);
        }
        
        public int getItem(String itemId){
            return inventory.get(itemId);
        }
         
        // Input the required item Qtt, uptdate the facility Qtt and return new required Qtt
        public int decreaseQtt(String itemId, int amount) {
            int left=inventory.get(itemId);
            if (amount<=left) {
                inventory.put(itemId,left-amount);
                return 0;
            } else {
                inventory.put(itemId,0);
                return amount-left;
            }
        }

        public void printInv() {
            System.out.println("Active Inventory:");
            System.out.println("   Item ID     Quantity");
            for (String item:inventory.keySet()){
                if (inventory.get(item)>0) {
                    System.out.printf("   %-12s%d\n",item,inventory.get(item));
                }
            }
            System.out.println();
            System.out.print("Depleted (Used-up) Inventory: ");
            boolean flag=false;
            for (String item:inventory.keySet()){
                if (inventory.get(item)==0) {
                    System.out.print(item+" ");
                    flag=true;
                }
            }
            if (!flag) System.out.print("None");
            System.out.println();
        }
  
         
}
