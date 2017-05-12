package item;

/** 
* @ClassName: ItemFactory
* @Description: factory to build up item using factory pattern
*/
public class ItemFactory {
	public static Item createItem(String itemId, int itemPrice){
		return new ItemImpl(itemId, itemPrice);
	}
}