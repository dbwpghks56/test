package dto;

import java.sql.Date;

public class BookDto {
	private String isbn;
	private String bookName;
	private String writer;
	private Date regdate;
	private String publish;
	private int remain;
	private int price;
	
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
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getPublish() {
		return publish;
	}
	public void setPublish(String publish) {
		this.publish = publish;
	}
	public int getRemain() {
		return remain;
	}
	public void setRemain(int remain) {
		this.remain = remain;
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
		builder.append("도서 명 : ").append(bookName).append(", 작가 : ")
				.append(writer).append(", 발매일 : ").append(regdate).append(", 출판사 : ").append(publish)
				.append(", 재고 : ").append(remain).append(", 가격 : ").append(price);
		return builder.toString();
	}
	
	
}
