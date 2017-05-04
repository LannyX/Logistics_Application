package logistic_application;

/** 
* @ClassName: ItemImplFactory
* @Description: factory to build up item using factory pattern
*/
public class ItemImplFactory {
	public static Item createItem(String itemId, int itemPrice){
		return new ItemImpl(itemId, itemPrice);
	}
}