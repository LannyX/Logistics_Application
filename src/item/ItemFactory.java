package item;

import exception.DataValidationException;
import exception.NullParamException;

/** 
* @ClassName: ItemFactory
* @Description: factory to build up item using factory pattern
*/
public class ItemFactory {
	public static Item createItem(String itemId, int itemPrice)
                                throws NullParamException,DataValidationException{
		return new ItemImpl(itemId, itemPrice);
	}
}