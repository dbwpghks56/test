package model;

import java.util.List;

import dto.BoardDto;
import dto.BoardEmpVo;

public class BoardService {
	BoardDAO boarddao = new BoardDAO();
	
	public List<BoardDto> selectAll() {
		return boarddao.selectAll();
	}
	
	public List<BoardDto> selectTitle(String title) {
		return boarddao.selectTitle(title);
	}
	
	public List<BoardDto> selectWriter(String writer) {
		return boarddao.selectWriter(writer);
	}
	
	public int insertBoard(BoardDto board) {
		return boarddao.insertBoard(board);
	}
	
	public int updateBoard(BoardDto board, int bno) {
		return boarddao.updateBoard(board, bno);
	}
	
	public int deleteBoard(int bno) {
		return boarddao.deleteBoard(bno);
	}
	public List<BoardEmpVo> selectName() {
		return boarddao.selectName();
	}
}
