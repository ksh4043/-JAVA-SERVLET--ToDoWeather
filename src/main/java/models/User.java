package models;

public class User {
	private String email;
	private String password;
	private String nickname;
	
	public User(String email, String password, String nickname) {
		this.email= email;
		this.password = password;
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

}
