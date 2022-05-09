package model;

import java.util.List;

import dto.BookDto;
import dto.PutDto;

public class PutService {
	PutDAO putdao = new PutDAO();
	
	public List<PutDto> selectPut() {
		return putdao.selectPut();
	}
	
	public void addPut(BookDto book, int amount, int price) {
		putdao.addPut(book, amount, price);
	}
	
	public List<PutDto> selectIsbn(String isbn) {
		return putdao.selectIsbn(isbn);
	}
	
	public List<PutDto> selectName(String bookName) {
		return putdao.selectName(bookName);
	}
}
