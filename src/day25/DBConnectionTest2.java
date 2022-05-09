package day25;

import java.sql.*;

import jdbcProjec.util.DBUtil;

public class DBConnectionTest2 {

	public static void main(String[] args) {
		f1();
	}

	private static void f1() {
		String sql = "select employee_id, first_name, salary, hire_date "
				+ "from employees "
				+ "where department_id = 60 "
				+ "order by 1";
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		conn = DBUtil.getConnection();
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()) { // rx.next() ...row로 이동.
//				System.out.println(rs.getInt(1));
//				System.out.println(rs.getString(2));
//				System.out.println(rs.getInt(3));
//				System.out.println(rs.getDate(4));
				
				int empno = rs.getInt(1);
				String name = rs.getString(2);
				double sal = rs.getInt(3);
				Date hdate = rs.getDate(4);
				
				System.out.printf("%d %s %8.2f %s \n", empno, name, sal, hdate);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
	}

}











