package logistic_application;

public class ItemImplFactory {
	public static Item createItem(String itemId, int itemPrice){
		return new ItemImpl(itemId, itemPrice);
	}
}