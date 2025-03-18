package music;

public class User {
	
	private String username;
	private String salt;
	private String securePassword;
	
	public User(String username, String salt, String securePassword) {
		this.username = username;
		this.salt = salt;
		this.securePassword = securePassword;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getSalt() {
		return this.salt;
	}
	
	public String getSecurePassword() {
		return this.securePassword;
	}

}