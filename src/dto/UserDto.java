package dto;

public class UserDto {
	private String uids;
	private String userName;
	private String password;
	private String email;
	private String phone;
	
	public String getUids() {
		return uids;
	}
	public void setUids(String uids) {
		this.uids = uids;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("아이디 : ").append(uids).append(", 이름 : ").append(userName)
				.append(", 이메일 : ").append(email).append(", 전화번호 : ").append(phone);
		return builder.toString();
	}
	
	
}
