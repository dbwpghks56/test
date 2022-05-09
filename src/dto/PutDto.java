package dto;

public class PutDto {
	private String putNum;
	private String isbn;
	private String bookName;
	private int amount;
	private int price;
	
	public String getPutNum() {
		return putNum;
	}
	public void setPutNum(String putNum) {
		this.putNum = putNum;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("입고 번호 : ").append(putNum).append(", ISBN : ").append(isbn).append(", 도서 명 : ")
				.append(bookName).append(", 수량 : ").append(amount).append(", 가격 : ").append(price);
		return builder.toString();
	}
	
	
}
