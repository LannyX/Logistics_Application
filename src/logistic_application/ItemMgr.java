package logistic_application;

import java.util.ArrayList;

public class ItemMgr {
	
	private ArrayList<Item> itemList;
	
	private static ItemMgr itemMgr;
	
	//load the file in c'tor   
    private ItemMgr(){
        itemList = ItemLoader.load();
    }
    
    public static ItemMgr getInstance(){
        if (itemMgr==null) itemMgr = new ItemMgr();
        return itemMgr;
    }
    
    public void printItemReport(){
    	int i = 0;
        for (Item item: itemList){
            i++;
        	item.printItem();
        	if (i%4 == 0) System.out.println();
        }
    }
    

}