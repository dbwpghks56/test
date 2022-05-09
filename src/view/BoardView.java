package view;

import java.util.List;

import dto.BoardDto;
import dto.BoardEmpVo;
import dto.EmpVo;

public class BoardView {
	public static void print(List<BoardDto> boardlist) {
		boardlist.forEach(System.out::println);
	}
	
	public static void print2(List<BoardEmpVo> boardlist) {
		boardlist.forEach(System.out::println);
	}
	
	public static void print(BoardDto board) {
		System.out.println(board);
	}
	
	public static void print(String message) {
		System.out.println(message);
	}
}
