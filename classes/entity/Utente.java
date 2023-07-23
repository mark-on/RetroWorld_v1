package entity;

public class Utente {
	private int id;
	private String username;
    private String name;
    private String lastname;
    private String password;
    private String email;
    private int isAdmin;

    public Utente() {}

    public Utente(int id, String username, String name, String lastname, String password, String email, int isAdmin) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
        this.isAdmin = isAdmin;
    }
    public Utente(String username, String name, String lastname, String password, String email, int isAdmin) {
    	this.username = username;
    	this.name = name;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
        this.isAdmin = isAdmin;
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

	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsername() {
		return username;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getisAdmin() {
		return isAdmin;
	}

	public void setAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}
    
}
