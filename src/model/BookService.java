package model;

import java.util.List;

import dto.BookDto;

public class BookService {
	BookDAO bookdao = new BookDAO();
	
	public List<BookDto> selectAll() {
		return bookdao.selectAll();
	}
	
	public List<BookDto> selectName(String bookName) {
		return bookdao.selectName(bookName);
	}
	
	public List<BookDto> selectWriter(String writer) {
		return bookdao.selectWriter(writer);
	}
	
	public List<BookDto> selectPublish(String publish) {
		return bookdao.selectPublish(publish);
	}
	
	public void addBook(BookDto book) {
		bookdao.addBook(book);
	}
	
	public void deleteBook(String isbn) {
		bookdao.deleteBook(isbn);
	}
}
