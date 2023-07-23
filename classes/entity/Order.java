package entity;

public class Order {
	 private int id;
	    private int userId;
	    private String date;
	    private double total;
	    private String status;
	    private String name;
	    private String card;
	    private String cvc;
	    private String address;

	    public Order(int userId, String date, double total, String name, String card, String cvc, String address) {
	        this.userId = userId;
	    	this.date = date;
	        this.total = total;
	        this.name = name;
	        this.card = card;
	        this.cvc = cvc;
	        this.setAddress(address);	    
	    }

	    public Order(int id, int userId, String date, double total, String name, String card, String cvc, String address, String status) {
	    	this.id = id;
	        this.userId = userId;
    		this.date = date;
	        this.total = total;
	        this.name = name;
	        this.card = card;
	        this.cvc = cvc;
	        this.address = address;
	        this.status = status;
	    }

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}


		public double getTotal() {
			return total;
		}

		public void setTotal(double total) {
			this.total = total;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getCard() {
			return card;
		}

		public void setCard(String card) {
			this.card = card;
		}

		public String getCvc() {
			return cvc;
		}

		public void setCvc(String cvc) {
			this.cvc = cvc;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}
	    
}
