package logistic_application;

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

}
