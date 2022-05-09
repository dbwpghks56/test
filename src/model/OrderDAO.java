package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dto.BookDto;
import dto.OrderDto;
import jdbcProjec.util.DBUtil;

public class OrderDAO {
	Connection conn = null;
	PreparedStatement st = null;
	ResultSet rs = null;
	
	public List<OrderDto> selectOrder(String uid) {
		List<OrderDto> orderList = new ArrayList<>();
		conn = DBUtil.getConnection();
		
		try {
			st = conn.prepareStatement("select o.*, u.username username , o.orderamount * b.price totalprice from orders o, book b, users u"
					+ " where o.uids = ? and u.uids = ? and "
					+ "o.isbn = b.isbn order by ordernum");
			st.setString(1, uid);
			st.setString(2, uid);
			
			rs = st.executeQuery();
			
			while(rs.next()) {
				orderList.add(makeList(rs));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return orderList;
	}
	
	public void addOrder(List<BookDto> booklist, int index, String uid, int amount) {
		conn = DBUtil.getConnection();
		try {
			st = conn.prepareStatement("insert into orders values(seq_orders.nextval,?,?,?,sysdate)");
			st.setString(1, uid);
			st.setString(2, booklist.get(index).getIsbn());
			st.setInt(3, amount);
			
			if(st.executeUpdate() > 0)	{
				System.out.println("주문이 완료되었습니다.");
			} else {
				System.out.println("주문에 실패하였습니다.");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
	}
	
	public void deleteOrder(String orderNum, String uid) {
		conn = DBUtil.getConnection();
		try {
			st = conn.prepareStatement("delete from orders where orderNum = ? and uids = ?");
			st.setString(1, orderNum);
			st.setString(2, uid);
			
			if(st.executeUpdate() > 0) {
				System.out.println("주문취소에 성공하였습니다.");
			} else {
				System.out.println("주문취소에 실패하였습니다.");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
	}

	private OrderDto makeList(ResultSet rs) throws SQLException {
		OrderDto order = new OrderDto();
		
		order.setIsbn(rs.getString("isbn"));
		order.setOrderAmount(rs.getInt("orderamount"));
		order.setOrderNum(rs.getString("ordernum"));
		order.setRegDate(rs.getDate("regdate"));
		order.setUids(rs.getString("uids"));
		order.setTotalPrice(rs.getInt("totalprice"));
		order.setUsername(rs.getString("username"));
		
		return order;
	}
}










