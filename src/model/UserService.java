package model;

import java.util.List;

import dto.UserDto;

public class UserService {
	UserDAO userdao = new UserDAO();
	
	public void AddUser(UserDto user) {
		userdao.AddUser(user);
	}
	
	public String getUser(String uid, String password) {
		return userdao.getUser(uid, password);
	}
	
	public List<UserDto> selectAll() {
		return userdao.selectAll();
	}
	
	public void deleteUser(String uid, String password) {
		userdao.deleteUser(uid, password);
	}
	
	public void deleteUser(String uid) {
		userdao.deleteUser(uid);
	}
	
	public void updateUser(UserDto user, String uid) {
		userdao.updateUser(user, uid);
	}
}
