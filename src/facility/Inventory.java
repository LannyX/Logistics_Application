package facility;

import exception.DataValidationException;
import exception.NullParamException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

/** 
* @ClassName: Inventory
* @Description: inventory of one facility
*/
public class Inventory {

	private Map<String, Integer> inventory;
	
	public Inventory(Map<String, Integer> inventory){
		this.inventory = inventory;
	}

    /** 
    * @Title: ownsItem 
    * @Description: check if inventory contains one item
    */    
    public boolean ownsItem(String itemId){
        return inventory.containsKey(itemId);
    }
    
    /** 
    * @Title: getItems
    * @Description: get a collection of all items in inventory
    */
    public Collection<String> getItems() {
        return inventory.keySet();
    }
    
    /** 
    * @Title: getQtt 
    * @Description: get the number of item in inventory
    */   
    public int getQtt(String itemId) {
        return inventory.get(itemId);
    }

    /** 
    * @Title: decreaseQtt 
    * @Description: input a required item Qtt, uptdate the facility Qtt and return new required Qtt
    */
    public int decreaseQtt(String itemId, int amount) throws 
                                    NullParamException,DataValidationException{
        if (itemId==null) throw new NullParamException("Null item Id is not allowed.");
        if (!inventory.containsKey(itemId)) 
            throw new DataValidationException("No such item in this inventory.");
        int left=inventory.get(itemId);
        if (amount<=left) {
            inventory.put(itemId,left-amount);
            return 0;
        } else {
            inventory.put(itemId,0);
            return amount-left;
        }
    }

    /** 
    * @Title: printInv 
    * @Description: print out the inventory list
    */
    public void printInv() {
        System.out.println("Active Inventory:");
        System.out.println("   Item ID     Quantity");
        String[] keySetS = (String[])inventory.keySet()
                .toArray(new String[inventory.keySet().size()]);
        Arrays.sort(keySetS);
        for (String item:keySetS){
            if (inventory.get(item)>0) {
                System.out.printf("   %-12s%d\n",item,inventory.get(item));
            }
        }
        System.out.println();
        System.out.print("Depleted (Used-up) Inventory: ");
        boolean flag=false;

        for (String item:keySetS){
            if (inventory.get(item)==0) {
                System.out.print(item+" ");
                flag=true;
            }
        }
        if (!flag) System.out.print("None");
        System.out.println();
    }
  
         
}
