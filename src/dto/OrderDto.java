package dto;

import java.sql.Date;

public class OrderDto {
	private String orderNum;
	private String uids;
	private String isbn;
	private int orderAmount;
	private Date regDate;
	private int totalPrice;
	private String username;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getUids() {
		return uids;
	}
	public void setUids(String uids) {
		this.uids = uids;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public int getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("주문번호 : ").append(orderNum).append(", 아이디 : ").append(uids).append(", 주문자 이름 : ").append(username)
				.append(", 주문 수량 : ").append(orderAmount).append(", 주문일 : ").append(regDate)
				.append(", 전체 가격 : ").append(totalPrice);
		return builder.toString();
	}
	
	
}
