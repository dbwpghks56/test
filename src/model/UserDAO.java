package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dto.UserDto;
import jdbcProjec.util.DBUtil;

public class UserDAO {
	Connection conn = null;
	PreparedStatement st = null;
	ResultSet rs = null;
	
	public void AddUser(UserDto user) {
		conn = DBUtil.getConnection();
		try {
			st = conn.prepareStatement("insert into users values(?,?,?,?,?)");
			st.setString(1, user.getUids());
			st.setString(2, user.getUserName());
			st.setString(3, user.getPassword());
			st.setString(4, user.getEmail());
			st.setString(5, user.getPhone());
			
			if(st.executeUpdate() > 0) {
				System.out.println(user.getUserName() + "회원의 아이디가 만들어졌습니다.");
			} else {
				System.out.println("회원가입에 실패하였습니다.");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
	}
	
	public String getUser(String uid, String password) {
		conn = DBUtil.getConnection();
		String uids = null;
		try {
			st = conn.prepareStatement("select uids from users where uids = ? and password = ?");
			st.setString(1, uid);
			st.setString(2, password);
			rs = st.executeQuery();
			
			if(rs.next()) {
				uids = rs.getString("uids");
				System.out.println("로그인에 성공하였습니다.");
			}
			
			else {
				uids = "아이디 혹은 패스워드가 틀렸습니다.";
				System.out.println(uids);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return uids;
	}
	
	public List<UserDto> selectAll() {
		List<UserDto> userList = new ArrayList<>();
		conn = DBUtil.getConnection();
		try {
			st = conn.prepareStatement("select * from users");
			rs = st.executeQuery();
			
			while(rs.next()) {
				userList.add(makeList(rs));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return userList;
	}
	
	public void deleteUser(String uid, String password) {
		conn = DBUtil.getConnection();

		try {
			st = conn.prepareStatement("delete from users where uids = ? and password = ?");
			st.setString(1, uid);
			st.setString(2, password);
			rs = st.executeQuery();
			
			if(rs.next()) {
				System.out.println("회원 탈퇴에 성공하였습니다.");
			}
			
			else {
				System.out.println("회원 탈퇴에 실패하였습니다.");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
	}
	
	public void deleteUser(String uid) {
		conn = DBUtil.getConnection();

		try {
			st = conn.prepareStatement("delete from users where uids = ?");
			st.setString(1, uid);
			rs = st.executeQuery();
			
			if(rs.next()) {
				System.out.println("회원 삭제에 성공하였습니다.");
			}
			
			else {
				System.out.println("회원 삭제에 실패하였습니다.");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
	}
	
	public void updateUser(UserDto user, String uid) {
		conn = DBUtil.getConnection();
		try {
			st = conn.prepareStatement("update users set username = ?, password = ?, email = ?, phone = ? where uids = ?");
			st.setString(1, user.getUserName());
			st.setString(2, user.getPassword());
			st.setString(3, user.getEmail());
			st.setString(4, user.getPhone());
			st.setString(5, uid);
			
			if(st.executeUpdate() > 0) {
				System.out.println("회원 정보가 바뀌었습니다.");
			} else {
				System.out.println("회원 정보 수정에 실패하였습니다.");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
	}

	private UserDto makeList(ResultSet rs) throws SQLException {
		UserDto user = new UserDto();
		
		user.setUids(rs.getString("uids"));
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getString("password"));
		user.setPhone(rs.getString("phone"));
		user.setUserName(rs.getString("username"));
		
		return user;
	}
}

















