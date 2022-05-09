package day25;

import java.sql.*;

import jdbcProjec.util.DBUtil;

public class DBConnectionTest {

	public static void main(String[] args) {
		// 실행시 DB연결, SQL 실행
		// 1. class load
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		String sql = "select * from employees order by 1";
		
		try {
			conn = DBUtil.getConnection();
			System.out.println("Conn Success");
			
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				System.out.println(rs.getInt(1));
				System.out.println(rs.getString("first_name"));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			
		} finally {
			DBUtil.dbClose(rs, st, conn);
			
		}
		
		// 2. connection
		
		
		// 3. statement 생성(DB와의 대화통로)
		
		
		// 4. SQL 실행
		
		
		// 5. 결과를 받기
		
		
		// 6. 자원해제 (DB 연결 해제)
		
		
		
	}

}
