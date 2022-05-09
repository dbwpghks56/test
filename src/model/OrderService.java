package model;

import java.util.List;

import dto.BookDto;
import dto.OrderDto;

public class OrderService {
	OrderDAO orderdao = new OrderDAO();
	
	public List<OrderDto> selectOrder(String uid) {
		return orderdao.selectOrder(uid);
	}
	
	public void addOrder(List<BookDto> booklist, int index, String uid, int amount) {
		orderdao.addOrder(booklist, index, uid, amount);
	}
	
	public void deleteOrder(String orderNum, String uid) {
		orderdao.deleteOrder(orderNum, uid);
	}
}
