package jdbcProjec.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbConnectionTest {

	public static void main(String[] args) throws Exception {
		// 1. Driver load
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("Driever Load Success");
		
		// 2. Connection
		String url = 
				"jdbc:oracle:thin:@Test_medium?TNS_ADMIN=/Users/yujehwan/Downloads/Wallet_Test";
		String user = "ADMIN";
		String password = "DbwpghksIngyoe1146";
		
		Connection conn = DriverManager.getConnection(url, user, password);
		System.out.println("DB Connection Success");
		
		// 3. SQL 문 통로생성(DB와 대화할 수 있는 길)
		Statement st = conn.createStatement();
		System.out.println("SQL load Success");
		
		// 4. SQL 문 실행
		String sql = "SELECT FIRST_NAME ,JOB_ID ,SALARY ,\n"
				+ "DECODE(JOB_ID,'AC_ACCOUNT',SALARY*1.1,'AC_MGR',SALARY*1.2,'AD_ASST',SALARY*1.3,SALARY ) 급여인상정보 \n"
				+ "FROM EMPLOYEES\n";
		ResultSet rs = st.executeQuery(sql);
		System.out.println("SQL Script Success DATA가 메모리로 들어온다.");
		
		while(rs.next()) {
			System.out.println("부서이름 : " + rs.getString("first_name"));
			System.out.println("부서이름 : " + rs.getString("job_id"));
			System.out.println("부서이름 : " + rs.getString("salary"));
			System.out.println("부서번호 : " + rs.getInt("급여인상정보"));
			System.out.println("---------------------------------");
		}
		
		// 5. DB 연결해제
		conn.close();
		System.out.println("DB disconnect Success");
		
	}

}



















