package logistic_application;

public class ItemImpl implements Item{
	
	private String itemId;
	private int itemPrice;
	
	public ItemImpl(String itemId, int itemPrice){
		this.itemId = itemId;
		this.itemPrice = itemPrice;
	}
	

	@Override
	public String getItemId() {
		// TODO Auto-generated method stub
		return itemId;
	}

	@Override
	public int getItemPrice() {
		// TODO Auto-generated method stub
		return itemPrice;
	}


	@Override
	public void printItem() {
		// TODO Auto-generated method stub

		System.out.println(itemId+" :"+ new String(new char[8-itemId.length()]).replace("\0", " ") +"$"+itemPrice);
	}

	
}
