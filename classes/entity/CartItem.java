package entity;

public class CartItem {
    private int quantity;
    private int orderId;
	private Product product;

    public CartItem(Product product, int quantity, int orderId) { //con order id
        this.product = product;
        this.quantity = quantity;
        this.orderId = orderId;
    }
    
	public CartItem(Product product, int quantity) { //iniziale
		 this.product = product;
	        this.quantity = quantity;
	}
	public Product getproduct() {
		return product;
	}

	public void setproduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
}
