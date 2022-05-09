package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dto.BoardDto;
import dto.BoardEmpVo;
import jdbcProjec.util.DBUtil;

public class BoardDAO {
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	int result = 0;
	
	static final String SQL_BOARD_EMP = "select b.bno, b.title, b.content ,e.first_name || e.last_name fullname from tbl_board b join"
			+ " employees e on(b.writer = e.email)";
	
	public List<BoardDto> selectAll() {
		List<BoardDto> boardlist = new ArrayList<>();
		
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement("select * from tbl_board");
			rs = pst.executeQuery();
			
			while(rs.next()) {
				boardlist.add(makeBoard(rs));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		
		return boardlist;
	}
	
	public List<BoardEmpVo> selectName() {
		List<BoardEmpVo> boardlist = new ArrayList<>();
		
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_BOARD_EMP);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				boardlist.add(makeBoardEmp(rs));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		
		return boardlist;
	}
	
	private BoardEmpVo makeBoardEmp(ResultSet rs2) throws SQLException {
		BoardEmpVo bev = new BoardEmpVo(rs2.getInt(1), rs2.getString(2), rs2.getString(3), rs2.getString(4));
		
		
		return bev;
	}

	private BoardDto makeBoard(ResultSet rs2) throws SQLException {
		BoardDto board = new BoardDto();
		
		board.setBno(rs2.getInt("bno"));
		board.setContent(rs2.getString("content"));
		board.setRegDate(rs2.getDate("regdate"));
		board.setTitle(rs2.getString("title"));
		board.setUpdateDate(rs2.getDate("updatedate"));
		board.setWriter(rs2.getString("writer"));
		
		return board;
	}

	public List<BoardDto> selectTitle(String title) {
		List<BoardDto> boardlist = new ArrayList<>();
		
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement("select * from tbl_board where title = ?");
			pst.setString(1, title);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				boardlist.add(makeBoard(rs));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		
		
		return boardlist;
	}
	
	public List<BoardDto> selectWriter(String writer) {
		List<BoardDto> boardlist = new ArrayList<>();
		
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement("select * from tbl_board where writer = ?");
			pst.setString(1, writer);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				boardlist.add(makeBoard(rs));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		
		
		return boardlist;
	}
	
	public int insertBoard(BoardDto board) {
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement("insert into tbl_board values(seq_bno.nextval, ?, ?, ?, sysdate, sysdate)");
			pst.setString(1, board.getTitle());
			pst.setString(2, board.getContent());
			pst.setString(3, board.getWriter());
			
			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		
		
		return result;
	}
	
	public int updateBoard(BoardDto board, int bno) {
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement("update tbl_board set title = ?, content = ?, updatedate = sysdate where bno = ?");
			pst.setString(1, board.getTitle());
			pst.setString(2, board.getContent());
			pst.setInt(3, bno);
			
			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		
		
		return result;
	}
	
	public int deleteBoard(int bno) {
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement("delete from tbl_board where bno = ?");
			pst.setInt(1, bno);
			
			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		
		
		return result;
	}
}



















