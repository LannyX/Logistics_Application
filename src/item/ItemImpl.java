package item;

import java.text.DecimalFormat;

/** 
* @ClassName: ItemImpl
* @Description: implementation of item using strategy pattern
*/
public class ItemImpl implements Item{
	
	private String itemId;
	private int itemPrice;
	
	public ItemImpl(String itemId, int itemPrice){
		this.itemId = itemId;
		this.itemPrice = itemPrice;
	}

	@Override
	public String getItemId() {
		return itemId;
	}

	@Override
	public int getItemPrice() {
		return itemPrice;
	}

	@Override
	public void printItem() {
        DecimalFormat df=new DecimalFormat("#,###"); 
		String itemPriceS = "$" + df.format(itemPrice);
		System.out.printf("  %-8s:  %-8s", itemId, itemPriceS);
	}

	
}
