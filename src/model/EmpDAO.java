package model;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import dto.EmpVo;
import jdbcProjec.util.DBUtil;

// CRUD 작업 하는 곳
public class EmpDAO {
	static final String SQL_SELECT_ALL = "select * from employees order by 1";
	static final String SQL_SELECT_DEPT = "select * from employees where department_id = ? order by 1"; // ? : 바인딩 변수
	static final String SQL_SELECT_MGR = "select * from employees where manager_id = ? order by 1";
	static final String SQL_SELECT_JOB = "select * from employees where job_id = ? order by 1";
	static final String SQL_SELECT_EMP = "select * from employees where employee_id = ? order by 1";
	static final String SQL_INSERT = "insert into employees values(?,?,?,?,?,?,?,?,?,?,?)";
	static final String SQL_UPDATE = "update employees set employee_id = "
			+ "?, first_name = "
			+ "?"
			+ ", last_name = "
			+ "?"
			+ ", email = "
			+ "?"
			+ ", phone_number = "
			+ "?"
			+ ", hire_date = "
			+ "?"
			+ ", job_id = "
			+ "?"
			+ ", salary = "
			+ "?"
			+ ", commission_pct = "
			+ "?"
			+ ", manager_id = "
			+ "?"
			+ ", department_id = "
			+ "?"
			+ " where employee_id = "
			+ "?";
	static final String SQL_UPDATE_MUL = "update employees set salary =?, commission_pct = ? where department_id = ?";
	static final String SQL_DELETE = "delete from employees where employee_id = ?";
	static final String SQL_SELECT_MUL = "select * from employees where department_id = ? and job_id = ? "
			+ "and salary >= ? and "
			+ "hire_date >= ?"
			+ "order by 1";
	Connection conn;
	Statement st; // 바인딩 변수를 지원하지 않는다.
	PreparedStatement pst; // 바인딩 변수 ? 를 지원한다.
	ResultSet rs;
	int result;
	
	// 1. 모든 직원 조회
	public List<EmpVo> allselect()	{
		List<EmpVo> emplist = new ArrayList<>();
		conn = DBUtil.getConnection();
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(SQL_SELECT_ALL);
			
			while(rs.next()) {
				emplist.add(makeEmp(rs));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return emplist;
	}
	
	private EmpVo makeEmp(ResultSet rs) throws SQLException {
		EmpVo emp = new EmpVo();
		
		emp.setCommission_pct(rs.getDouble("commission_pct"));
		emp.setDepartment_id(rs.getInt("department_id"));
		emp.setEmail(rs.getString("email"));
		emp.setEmployee_id(rs.getInt("employee_id"));
		emp.setFirst_name(rs.getString("first_name"));
		emp.setHire_date(rs.getDate("hire_date"));
		emp.setJob_id(rs.getString("job_id"));
		emp.setLast_name(rs.getString("last_name"));
		emp.setManager_id(rs.getInt("manager_id"));
		emp.setPhone_number(rs.getString("phone_number"));
		emp.setSalary(rs.getDouble("salary"));
		
		return emp;
	}

	// 2. 조건 조회 (특정부서)
	public List<EmpVo> selecByDept(int deptid) {
		List<EmpVo> emplist = new ArrayList<>();
		conn = DBUtil.getConnection();
		
		try {
			pst = conn.prepareStatement(SQL_SELECT_DEPT);
			pst.setInt(1, deptid); // 1번쨰 물음표에 뒤의 변수 값을 넣어라.
			rs = pst.executeQuery();
			
			while(rs.next()) {
				emplist.add(makeEmp(rs));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return emplist;
	}
	
	// 3. 조건 조회 (특정 매니저가 관리하는 직원)
	public List<EmpVo> selecByMng(int managerid) {
		List<EmpVo> emplist = new ArrayList<>();
		conn = DBUtil.getConnection();
		
		try {
			pst = conn.prepareStatement(SQL_SELECT_MGR);
			pst.setInt(1, managerid); // 1번쨰 물음표에 뒤의 변수 값을 넣어라.
			rs = pst.executeQuery();
			
			while(rs.next()) {
				emplist.add(makeEmp(rs));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return emplist;
	}
	
	// 4. 조건 조회 (특정 직업을 가진 사람)
	public List<EmpVo> selecByJob(String jobid) {
		List<EmpVo> emplist = new ArrayList<>();
		conn = DBUtil.getConnection();
		
		try {
			pst = conn.prepareStatement(SQL_SELECT_JOB);
			pst.setString(1, jobid); // 1번쨰 물음표에 뒤의 변수 값을 넣어라.
			rs = pst.executeQuery();
			
			while(rs.next()) {
				emplist.add(makeEmp(rs));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return emplist;
	}
	
	// 5. 조건 조회 (부서, 직업, 월급 >=, 입사일 >=)
	public List<EmpVo> selecBymulti(int deptid, String jobid, double salary, String hire) {
		List<EmpVo> emplist = new ArrayList<>();
		conn = DBUtil.getConnection();
		
		try {
			pst = conn.prepareStatement(SQL_SELECT_MUL);
			pst.setInt(1, deptid); // 1번쨰 물음표에 뒤의 변수 값을 넣어라.
			pst.setString(2, jobid);
			pst.setDouble(3, salary); 
			pst.setString(4, hire);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				emplist.add(makeEmp(rs));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return emplist;
	}
	
	public EmpVo selectById(int empid) {
		EmpVo emp = null;
		conn = DBUtil.getConnection();
		
		try {
			pst = conn.prepareStatement(SQL_SELECT_EMP);
			pst.setInt(1, empid); // 1번쨰 물음표에 뒤의 변수 값을 넣어라.
			rs = pst.executeQuery();
			
			while(rs.next()) {
				emp = makeEmp(rs);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return emp;
	}
	
	// 6. insert
	public int insertEmp(EmpVo ev) {
		conn = DBUtil.getConnection();
		
		try {
			pst = conn.prepareStatement(SQL_INSERT);
			pst.setInt(1, ev.getEmployee_id()); // 1번쨰 물음표에 뒤의 변수 값을 넣어라.
			pst.setString(2, ev.getFirst_name());
			pst.setString(3, ev.getLast_name()); 
			pst.setString(4, ev.getEmail());
			pst.setString(5, ev.getPhone_number());
			pst.setDate(6, ev.getHire_date());
			pst.setString(7, ev.getJob_id());
			pst.setDouble(8, ev.getSalary());
			pst.setDouble(9, ev.getCommission_pct());
			pst.setInt(10, ev.getManager_id());
			pst.setInt(11, ev.getDepartment_id());
			
			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return result;
	}
	
	// 7. update (특정 직원 1건을 수정)
	public int updateEmp(EmpVo ev, int empid) {
		conn = DBUtil.getConnection();
		
		try {
			pst = conn.prepareStatement(SQL_UPDATE);
			pst.setInt(1, ev.getEmployee_id()); // 1번쨰 물음표에 뒤의 변수 값을 넣어라.
			pst.setString(2, ev.getFirst_name());
			pst.setString(3, ev.getLast_name()); 
			pst.setString(4, ev.getEmail());
			pst.setString(5, ev.getPhone_number());
			pst.setDate(6, ev.getHire_date());
			pst.setString(7, ev.getJob_id());
			pst.setDouble(8, ev.getSalary());
			pst.setDouble(9, ev.getCommission_pct());
			pst.setInt(10, ev.getManager_id());
			pst.setInt(11, ev.getDepartment_id());
			pst.setInt(12, empid);
			
			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		
		return result;

	}
	
	// 8. update (같은 부서 직원 전부 수정)
	public int updateEmpByDept(EmpVo ev, int deptid) {
		conn = DBUtil.getConnection();
		
		try {
			pst = conn.prepareStatement(SQL_UPDATE_MUL);
			pst.setDouble(1, ev.getSalary());
			pst.setDouble(2, ev.getCommission_pct());
			pst.setInt(3, deptid);
			
			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		
		return result;
	}
	
	// 9. delete (특정 직원만)
	public int deleteEmp(int empid) {
		conn = DBUtil.getConnection();
		
		try {
			pst = conn.prepareStatement(SQL_DELETE);
			pst.setInt(1, empid);
			
			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		
		return result;
	}
	
	// 10. delete (같은 조건 직원 전부)
	public int deleteEmpByDept(int deptid) {
		
		
		return result;
	}
	
}














