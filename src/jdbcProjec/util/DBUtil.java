package jdbcProjec.util;

import java.sql.*;

public class DBUtil {
	public static Connection getConnection() {
		Connection conn = null;
		
		String url = "jdbc:oracle:thin:@Test_medium?TNS_ADMIN=/Users/yujehwan/Downloads/Wallet_Test"
				, userid = "ADMIN", password = "DbwpghksIngyoe1146";
		
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, userid, password);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		
		return conn;
	}
	
	public static void dbClose(ResultSet rs, Statement st, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
