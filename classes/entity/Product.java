package entity;

import java.io.InputStream;

public class Product {
	private int id;
    private String name;
    private double price;
    private String description;
    private int brandId;
    private int consoleId;
    private int amount;
    private String tag;
    private String type;
    private InputStream image; 
    

    public Product(int id, String name, double price, String description, int brandId, int consoleId, int amount, String tag, String type, InputStream inputStream) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.brandId = brandId;
        this.consoleId = consoleId;
        this.amount = amount;
        this.tag = tag;
        this.type = type;
        this.image = inputStream;
    }
    public Product(InputStream inputStream) {
       
        this.image = inputStream;
    }















	public InputStream getImage() {
		return image;
	}


	public void setImage(InputStream image) {
		this.image = image;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getBrandId() {
		return brandId;
	}


	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}


	public int getConsoleId() {
		return consoleId;
	}


	public void setConsoleId(int consoleId) {
		this.consoleId = consoleId;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public String getTag() {
		return tag;
	}


	public void setTag(String tag) {
		this.tag = tag;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}
    
	
}
