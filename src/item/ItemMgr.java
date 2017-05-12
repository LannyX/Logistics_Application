package item;

import exception.DataValidationException;
import exception.NullParamException;
import java.util.ArrayList;

/** 
* @ClassName: ItemMgr
* @Description: define higher-lever interface to "use" items in facade pattern
*/
public class ItemMgr {
	
	private ArrayList<Item> itemList;
	
	private static ItemMgr itemMgr;
	
    private ItemMgr(){
        itemList = ItemLoader.load();
    }
    
    public static ItemMgr getInstance(){
        if (itemMgr==null) itemMgr = new ItemMgr();
        return itemMgr;
    }
    
    /** 
    * @Title: printItemReport
    * @Description: print out Item Report
    */ 
    public void printItemReport(){
    	int i = 0;
        for (Item item: itemList){
            i++;
        	item.printItem();
        	if (i%4 == 0) System.out.println();
        }
    }
    
    /** 
    * @Title: getItemPrice
    * @Description: get an item price in the itemList
    */     
    public int getItemPrice(String itemId)throws NullParamException,DataValidationException{
        if (itemId==null) {
            throw new NullParamException("Null item Id is not allowed.");
        }
        for (Item item: itemList) {
            if (item.getItemId().equals(itemId)) {
                return item.getItemPrice();
            }
        }
        throw new DataValidationException("Item does not exist in catalog.");
    }
    
    /** 
    * @Title: itemExist
    * @Description: check if an item exists in the itemList
    */     
    public boolean itemExist(String itemId){
        for (Item item: itemList) {
            if (item.getItemId().equals(itemId)) {
                return true;
            }
        }
        return false;
    }
    
}
