package order;

/**
 * Object to store one item and the required quantity of it in an order.
 */
public class OrderItem {
    
    private String item;
    private int qtt;

    public OrderItem(String item, int qtt) {
        this.item = item;
        this.qtt = qtt;
    }

    public String getItem() {
        return item;
    }

    public int getQtt() {
        return qtt;
    }
    
    
}
