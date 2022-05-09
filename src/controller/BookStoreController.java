package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dto.BookDto;
import dto.OrderDto;
import dto.UserDto;
import jdbcProjec.util.DateUtil;
import model.BookService;
import model.OrderService;
import model.PutService;
import model.UserService;


public class BookStoreController {
	
	static BookService bookservice = new BookService();
	static UserService userservice = new UserService();
	static OrderService orderservice = new OrderService();
	static PutService putservice = new PutService();
	static Scanner sc = new Scanner(System.in);
	static String uid = "";
	static boolean confirm = true;
	static boolean login = false;
	
	public static void main(String[] args) {
		while(confirm) {
			menuDisplay();
		}
		System.out.println("끝");
	}

	private static void businessLogic(int job) {
		switch(job) {
		case 99: 
			confirm = false;
			break;
		case 1:
			singUp();
			break;
		case 2:
			logIn();
			break;
		default:
			break;
		}
		
	}
	
	private static void businessLogicLogin(int job) {
		switch(job) {
		case 99: 
			confirm = false;
			break;
		case 1:
			searchBook();
			break;
		case 2:
			orderBook();
			break;
		case 3:
			UserDto user = new UserDto();
			System.out.println("회원 아이디 : " + uid);
			user.setUids(uid);
			System.out.print("수정할 이름 : ");
			user.setUserName(sc.nextLine());
			
			System.out.print("수정할 비밀번호 : ");
			user.setPassword(sc.nextLine());
			
			System.out.print("수정할 이메일 : ");
			user.setEmail(sc.nextLine());
			
			System.out.print("수정할 번호 : ");
			user.setPhone(sc.nextLine());
			
			userservice.updateUser(user, uid);
			break;
		case 4:
			System.out.println("=-=-= 계정 확인 =-=-=");
			System.out.print("아이디 : ");
			String uid2 = sc.nextLine(); 
			
			System.out.print("비밀번호 : ");
			String password2 = sc.nextLine();
			
			userservice.deleteUser(uid2, password2);
			
		case 5:
			uid = "";
			login = false;
			break;
		default:
			break;
		}
		
	}
	
	private static void orderBook() {
		System.out.println("=-=-=-=-=-=-=");
		System.out.println("1. 도서 주문하기");
		System.out.println("2. 주문내역 검색");
		System.out.println("3. 주문 취소");
		System.out.println("4. 뒤로가기");
		System.out.println("=-=-=-=-=-=-=");
		System.out.print("작업 선택 >> ");

		orderBookLogic(Integer.parseInt(sc.nextLine()));
	}

	private static void orderBookLogic(int job) {
		List<OrderDto> orderlist = new ArrayList<>();
		List<BookDto> booklist = new ArrayList<>();
		
		switch(job) {
		case 1:
			System.out.print("주문할 책 제목을 입력하세요 >> ");
			booklist = bookservice.selectName(sc.nextLine());
			viewList(booklist);
			
			System.out.print("주문할 책 번호를 입력하세요 >> ");
			int index = Integer.parseInt(sc.nextLine()) - 1;
			
			System.out.print("주문할 책 수량을 입력하세요 >> ");
			int amount = Integer.parseInt(sc.nextLine());
			
			if(booklist.get(index).getRemain() < amount && amount > 0) {
				System.out.println("재고보다 주문 수량이 많습니다.");
			} 
			if(amount > 0) {
				orderservice.addOrder(booklist, index, uid, amount);
			} else {
				System.out.println("0권이하는 주문할 수 없습니다.");
			}
				
			break;
		
		case 2:
			orderlist = orderservice.selectOrder(uid);
			viewList(orderlist);
			break;
		
		case 3:
			orderlist = orderservice.selectOrder(uid);
			viewList(orderlist);
			System.out.print("주문 취소할 주문번호를 입력하세요 >> ");
			orderservice.deleteOrder(orderlist.get(Integer.parseInt(sc.nextLine()) -1).getOrderNum(), uid);
			break;
			
		case 4:
			return;
			
		default:
			break;
		}
		
		
	}
	
	private static void searchBook() {
		System.out.println("=-=-=-=-=-=-=");
		System.out.println("1. 전체 도서 검색");
		System.out.println("2. 제목으로 검색");
		System.out.println("3. 작가로 검색");
		System.out.println("4. 출판사로 검색");
		System.out.println("5. 뒤로가기");
		System.out.println("=-=-=-=-=-=-=");
		System.out.print("작업 선택 >> ");

		saerchBookLogic(Integer.parseInt(sc.nextLine()));
		
	}
	
	private static void saerchBookLogic(int job) {
		List<BookDto> booklist = new ArrayList<>();
		
		switch(job) {
		case 1: 
			booklist = bookservice.selectAll();
			booklist.forEach(System.out::println);
			break;
			
		case 2:
			System.out.print("검색할 책 제목을 입력하세요 >> ");
			booklist = bookservice.selectName(sc.nextLine());
			booklist.forEach(System.out::println);
			break;
			
		case 3:
			System.out.print("검색할 작가를 입력하세요 >> ");
			booklist = bookservice.selectWriter(sc.nextLine());
			booklist.forEach(System.out::println);
			break;
			
		case 4:
			System.out.print("검색할 출판사를 입력하세요 >> ");
			booklist = bookservice.selectPublish(sc.nextLine());
			booklist.forEach(System.out::println);
			break;
			
		case 5:
			return;
			
		default:
			break;
		}
		
	}
	
	private static void menuDisplay() {
		if(!login) {
			System.out.println("=-=-=-=-=-=-=");
			System.out.println("1. 회원가입");
			System.out.println("2. 로그인");
			System.out.println("99. exit");
			System.out.println("=-=-=-=-=-=-=");
			System.out.print("작업 선택 >> ");

			businessLogic(Integer.parseInt(sc.nextLine()));
		} else {
			menuDisplay2();
		}
		
	}

	private static void logIn() {
		String id = "";
		String password = "";
		
		System.out.println("=-=-= 로그인 =-=-=");
		System.out.print("아이디 : ");
		id = sc.nextLine();
		
		System.out.print("비밀번호 : ");
		password = sc.nextLine();
		
		uid = userservice.getUser(id, password);
		
		if(uid.equals(id)) {
			login = true;
		} else {
			login = false;
		}
		
	}

	private static void singUp() {
		UserDto user = new UserDto();
		
		System.out.println("=-=-= 회원가입 =-=-=");
		System.out.print("아이디 : ");
		user.setUids(sc.nextLine());
		
		System.out.print("비밀번호 : ");
		user.setPassword(sc.nextLine());

		System.out.print("회원이름 : ");
		user.setUserName(sc.nextLine());

		System.out.print("이메일 : ");
		user.setEmail(sc.nextLine());

		System.out.print("핸드폰 번호 : ");
		user.setPhone(sc.nextLine());

		userservice.AddUser(user);
		
	}
	
	private static void menuDisplay2() {
		if(uid.equals("Admin")) {
			System.out.println("=-=-=-=-=-=-=-=");
			System.out.println("1. 새 도서 추가");
			System.out.println("2. 기존 도서 입고");
			System.out.println("3. 도서 삭제");
			System.out.println("4. 회원 검색");
			System.out.println("5. 회원 삭제");
			System.out.println("6. 로그아웃");
			System.out.println("=-=-=-=-=-=-=-=");
			System.out.print("작업 선택 >> ");
			
			businessLogicAdmin(Integer.parseInt(sc.nextLine()));
		} else {
			System.out.println("=-=-=-=-=-=-=-=");
			System.out.println("1. 도서 검색");
			System.out.println("2. 도서 주문하기");
			System.out.println("3. 회원 정보 수정");
			System.out.println("4. 회원 탈퇴");
			System.out.println("5. 로그아웃");
			System.out.println("=-=-=-=-=-=-=-=");
			System.out.print("작업 선택 >> ");

			businessLogicLogin(Integer.parseInt(sc.nextLine()));
		}
		
	}

	private static void businessLogicAdmin(int job) {
		BookDto book = new BookDto();
		List<BookDto> booklist = new ArrayList<>();
		
		switch(job) {
		case 1: 
			System.out.print("ISBN 코드를 입력하세요 >> ");
			book.setIsbn(sc.nextLine());
			
			System.out.print("이름을 입력하세요 >> ");
			book.setBookName(sc.nextLine());
			
			System.out.print("작가를 입력하세요 >> ");
			book.setWriter(sc.nextLine());
			
			System.out.print("출판일을 입력하세요 >> ");
			book.setRegdate(DateUtil.convertToDate(sc.nextLine()));
			
			System.out.print("출판사를 입력하세요 >> ");
			book.setPublish(sc.nextLine());
			
			System.out.print("재고를 입력하세요 >> ");
			book.setRemain(Integer.parseInt(sc.nextLine()));
			
			System.out.print("가격을 입력하세요 >> ");
			book.setPrice(Integer.parseInt(sc.nextLine()));
			
			bookservice.addBook(book);
			break;
			
		case 2:{
			System.out.print("입고할 책 제목을 입력하세요 >> ");
			booklist = bookservice.selectName(sc.nextLine());
			viewList(booklist);
			
			System.out.print("입고할 책 번호를 입력하세요 >> ");
			int index = Integer.parseInt(sc.nextLine()) - 1;
			
			System.out.print("입고할 책 수량을 입력하세요 >> ");
			int amount = Integer.parseInt(sc.nextLine());
			
			System.out.print("입고할 책의 가격을 입력하세요 >> ");
			int price = Integer.parseInt(sc.nextLine());
			
			BookDto book2 = booklist.get(index);
			
			putservice.addPut(book2, amount, price);
			break;}
			
		case 3:{
			System.out.print("제거할 책 제목을 입력하세요 >> ");
			booklist = bookservice.selectName(sc.nextLine());
			viewList(booklist);
			
			System.out.print("제거할 책 번호를 입력하세요 >> ");
			int index = Integer.parseInt(sc.nextLine()) -1;
			
			BookDto book2 = booklist.get(index);
			
			bookservice.deleteBook(book2.getIsbn());
			break;}
		case 4:
			List<UserDto> userlist = new ArrayList<>();
			userlist = userservice.selectAll();
			userlist.forEach(System.out::println);
			break;
		case 5:
			System.out.print("삭제할 회원의 아이디를 입력하세요 >> ");
			userservice.deleteUser(sc.nextLine());
			break;
		case 6:
			login = false;
			break;
		}
	}
	
	public static void viewList(List<?> list) {
		for(int i = 0; i < list.size(); i++) {
			System.out.println((i+1) + " : "+ list.get(i));
		}
	}

}
















