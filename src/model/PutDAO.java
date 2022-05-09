package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dto.BookDto;
import dto.PutDto;
import jdbcProjec.util.DBUtil;

public class PutDAO {
	Connection conn = null;
	PreparedStatement st = null;
	ResultSet rs = null;
	
	public List<PutDto> selectPut() {
		List<PutDto> putlist = new ArrayList<>();
		
		conn = DBUtil.getConnection();
		try {
			st = conn.prepareStatement("select * from put");
			rs = st.executeQuery();
			
			while(rs.next()) {
				putlist.add(makelist(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return putlist;
	}
	
	public List<PutDto> selectIsbn(String isbn) {
		List<PutDto> putlist = new ArrayList<>();
		
		conn = DBUtil.getConnection();
		try {
			st = conn.prepareStatement("select * from put where isbn = ?");
			st.setString(1, isbn);
			rs = st.executeQuery();
			
			while(rs.next()) {
				putlist.add(makelist(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return putlist;
	}
	
	public List<PutDto> selectName(String bookName) {
		List<PutDto> putlist = new ArrayList<>();
		
		conn = DBUtil.getConnection();
		try {
			st = conn.prepareStatement("select * from put where bookname = ?");
			st.setString(1, bookName);
			rs = st.executeQuery();
			
			while(rs.next()) {
				putlist.add(makelist(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return putlist;
	}
	
	public void addPut(BookDto book, int amount, int price) {
		conn = DBUtil.getConnection();
		try {
			st = conn.prepareStatement("insert into put values(seq_puts.nextval,?,?,?,?)");
			st.setString(1, book.getIsbn());
			st.setString(2, book.getBookName());
			st.setInt(3, amount);
			st.setInt(4, price);
			
			if(st.executeUpdate() > 0) {
				System.out.println("입고처리가 완료되었습니다.");
			} else {
				System.out.println("입고처리가 실패하였습니다.");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private PutDto makelist(ResultSet rs) throws SQLException {
		PutDto put = new PutDto();
		
		put.setAmount(rs.getInt("amount"));
		put.setBookName(rs.getString("bookname"));
		put.setIsbn(rs.getString("isbn"));
		put.setPrice(rs.getInt("price"));
		put.setPutNum(rs.getString("putnum"));
		
		return put;
	}
}










