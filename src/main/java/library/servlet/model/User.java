package library.servlet.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;

public class User {

	private String id;
	private String nickname;
	private String email;
	private String password;
	private String phoneNum;
	private LocalDateTime createdDate;
	
	private User(String id, String email, String password, String phoneNum) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.phoneNum = phoneNum;
		this.createdDate = LocalDateTime.now();
	}
	
	public User() {
		
	}
	
	public User(ResultSet rs) throws SQLException {
		int id = rs.getInt("userId");
		String nickname = rs.getString("nickname");
		String email = rs.getString("email");
		String password = rs.getString("pwd");
		
		this.id = String.valueOf(id);
		this.nickname = nickname;
		this.email = email;
		this.password = password;
	}
	
	public static User create(String id, String email, String password, String phoneNum) {
		return new User(id, email, password, phoneNum);
	}
	
	public static User update(String email, String phoneNum) {
		return new User(null, email, null, phoneNum); 
	}
	
	public boolean isSameId(final String id) { // 아이디 중복 확인
		return this.id.equals(id);
	}
		
	public void updateUser(User updateInfo) {
		setEmail(updateInfo.getEmail());
		setPhoneNum(updateInfo.getPhoneNum());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
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

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nickname=" + nickname + ", email=" + email + ", password=" + password
				+ ", phoneNum=" + phoneNum + ", createdDate=" + createdDate + "]";
	}

	

}



