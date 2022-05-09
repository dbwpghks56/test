package controller;

import java.util.Scanner;

import dto.BoardDto;
import model.BoardService;
import view.BoardView;


public class BoardController {
	static BoardService service = new BoardService();
	static Scanner sc = new Scanner(System.in);
	static boolean confirm = true;
	
	public static void main(String[] args) {
		while(confirm) {
			menuDisplay();
			int job = sc.nextInt();
			businessLogic(job);
		}
		
		System.out.println("끝");
	}
	
	private static void menuDisplay() {
		System.out.println("=-=-=-=-=-=-=-=-=-=-=-=");
		System.out.println("1. 모든 게시글 조회");
		System.out.println("2. 제목으로 조회");
		System.out.println("3. 작성자로 조회");
		System.out.println("4. 게시글 입력");
		System.out.println("5. 게시글 수정");
		System.out.println("6. 게시글 삭제");
		System.out.println("99. exit");
		System.out.println("=-=-=-=-=-=-=-=-=-=-=-=");
		System.out.print("작업 선택 >> ");
	}
	
	private static void businessLogic(int job) {
		switch (job) {
			case 99: confirm = false;
				break;
				
			case 1 : f1();
				break;
			case 2 : f2();
			break;
			case 3 : f3();
			break;
			case 4 : f4();
			break;
			case 5 : f5();
			break;
			case 6 : f6();
			break;
			
			default : break;
		}
	}

	private static void f6() {
		System.out.print("삭제할 게시글 번호를 입력하세요 >> ");
		
		int result = service.deleteBoard(sc.nextInt());
		
		BoardView.print(result > 0 ? result + " Success" : "Fail");
	}
	private static void f5() {
		BoardDto board = new BoardDto();
		
		System.out.print("바꿀 게시글의 제목, 내용, 번호를 입력하세요 >> ");
		
		board.setTitle(sc.next());
		board.setContent(sc.next());
		
		int result = service.updateBoard(board, sc.nextInt());
		
		BoardView.print(result > 0 ? result + " Success" : "Fail");
	}
	private static void f4() {
		BoardDto board = new BoardDto();
		
		System.out.print("제목과 내용 작성자를 입력하세요 >> ");
		
		board.setTitle(sc.next());
		board.setContent(sc.next());
		board.setWriter(sc.next());
		
		int result = service.insertBoard(board);
		
		BoardView.print(result > 0 ? result + " Success" : "Fail");
	}
	private static void f3() {
		System.out.print("검색할 작성자의 이름을 입력하세요 >> ");
		
		BoardView.print(service.selectWriter(sc.next()));
	}
	private static void f2() {
		System.out.print("검색할 게시글의 제목을 입력하세요 >> ");
		
		BoardView.print(service.selectTitle(sc.next()));
	}
	private static void f1() {
		BoardView.print2(service.selectName());
	}

}
