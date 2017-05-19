package item;

import exception.DataValidationException;
import exception.NullParamException;
import java.text.DecimalFormat;

/** 
* @ClassName: ItemImpl
* @Description: implementation of item using strategy pattern
*/
public class ItemImpl implements Item{
	
	private String itemId;
	private int itemPrice;
	
	public ItemImpl(String itemId, int itemPrice)
                            throws NullParamException,DataValidationException{
                //Handle exceptions of input itemId
                if (itemId==null) throw new NullParamException("Null item is not allowed.");
                if (itemId.equals("")) throw new DataValidationException("Empty item Id is not allowed.");
                //Exception of itemPrice            
                if (itemPrice<0) throw new DataValidationException("Negative item price is unreasonable.");    
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
