package entity;

public class Console {
	private int id;
    private String name;
    private int brand_id;

    public Console() {}

    public Console(int id, String name, int brand_id) {
        this.id = id;
        this.name = name;
        this.brand_id = brand_id;
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


	public int getBrandId() {
		return brand_id;
	}

	public void setBrandId(int brand_id) {
		this.brand_id = brand_id;
	}

	
    
}
