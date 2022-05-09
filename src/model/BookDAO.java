package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dto.BookDto;
import jdbcProjec.util.DBUtil;

public class BookDAO {
	Connection conn = null;
	PreparedStatement st = null;
	ResultSet rs = null;
	
	public List<BookDto> selectAll() {
		List<BookDto> bookList = new ArrayList<>();
		conn = DBUtil.getConnection();
		try {
			st = conn.prepareStatement("select * from book");
			rs = st.executeQuery();
			
			while(rs.next()) {
				bookList.add(makeBook(rs));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return bookList;
	}
	
	public List<BookDto> selectName(String bookName) {
		List<BookDto> books = new ArrayList<>();
		conn = DBUtil.getConnection();
		try {
			st = conn.prepareStatement("select * from book where bookName = ?");
			st.setString(1, bookName);
			rs = st.executeQuery();
			
			while(rs.next()) {
				books.add(makeBook(rs));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return books;
	}
	
	public List<BookDto> selectWriter(String writer) {
		List<BookDto> books = new ArrayList<>();
		conn = DBUtil.getConnection();
		try {
			st = conn.prepareStatement("select * from book where writer = ?");
			st.setString(1, writer);
			rs = st.executeQuery();
			
			while(rs.next()) {
				books.add(makeBook(rs));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return books;
	}
	
	public List<BookDto> selectPublish(String publish) {
		List<BookDto> books = new ArrayList<>();
		conn = DBUtil.getConnection();
		try {
			st = conn.prepareStatement("select * from book where publish = ?");
			st.setString(1, publish);
			rs = st.executeQuery();
			
			while(rs.next()) {
				books.add(makeBook(rs));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return books;
	}
	
	public void addBook(BookDto book) {
		conn = DBUtil.getConnection();
		try {
			st = conn.prepareStatement("insert into book values(?,?,?,?,?,?,?)");
			st.setString(1, book.getIsbn());
			st.setString(2, book.getBookName());
			st.setString(3, book.getWriter());
			st.setDate(4, book.getRegdate());
			st.setString(5, book.getPublish());
			st.setInt(6, book.getRemain());
			st.setInt(7, book.getPrice());
			
			if(st.executeUpdate() > 0) {
				System.out.println("책 추가에 성공하였습니다.");
			} else {
				System.out.println("책 추가에 실패하였습니다.");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteBook(String isbn) {
		conn = DBUtil.getConnection();
		try {
			st = conn.prepareStatement("delete from book where isbn = ?");
			st.setString(1, isbn);
			
			if(st.executeUpdate() > 0) {
				System.out.println("도서 삭제에 성공하였습니다.");
			} else {
				System.out.println("도서 삭제에 실패하였습니다.");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private BookDto makeBook(ResultSet rs) throws SQLException {
		BookDto book = new BookDto();
		
		book.setBookName(rs.getString("bookname"));
		book.setIsbn(rs.getString("isbn"));
		book.setPrice(rs.getInt("price"));
		book.setPublish(rs.getString("publish"));
		book.setRegdate(rs.getDate("regdate"));
		book.setRemain(rs.getInt("remain"));
		book.setWriter(rs.getString("writer"));
		
		return book;
	}
}

















