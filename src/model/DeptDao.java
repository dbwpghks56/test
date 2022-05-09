package model;

import java.util.*;
import java.sql.*;
import dto.DeptDto;
import jdbcProjec.util.DBUtil;

// DAO : DB에 접근하는 Business Logic을 작성한다.
public class DeptDao {
	
	// 1. 모두 조회
	public List<DeptDto> all() {
		List<DeptDto> deptlist = new ArrayList<>();
		Connection conn = DBUtil.getConnection();
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from departments order by 1";
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()) {	
				deptlist.add(makeDept(rs));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		return deptlist;
	}
	
	private DeptDto makeDept(ResultSet rs) throws SQLException {
		int deptid = rs.getInt(1);
		String deptname = rs.getString(2);
		int mid = rs.getInt(3);
		int loc = rs.getInt(4);
		return new DeptDto(deptid, deptname, mid, loc);
	}

	// 2. 특정 부서만 조회(부서코드로 조회)
	public DeptDto selectById(int deptid) {
		DeptDto dept = null;
		Connection conn = DBUtil.getConnection();
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from departments where department_id = " 
					+ deptid + 
					" order by 1";
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()) {	
				dept = makeDept(rs);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		return dept;
	}
	
	// 3. 특정 지역 코드로 조회
	public List<DeptDto> selectByLocation(int locid) {
		List<DeptDto> deptlist = new ArrayList<>();
		Connection conn = DBUtil.getConnection();
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from departments where location_id = " + locid + " order by 1";
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()) {	
				deptlist.add(makeDept(rs));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		return deptlist;
	}
	
	// 4. 부서 입력
	public int deptInsert(DeptDto dept) {
		Connection conn = DBUtil.getConnection();
		Statement st = null;
		int ret = 0;
		String sql = "insert into departments values("
				+ dept.getDepartment_id() + ", '"
				+ dept.getDepartment_name() + "', "
				+ dept.getManager_id() + ", "
				+ dept.getLocation_id() + ") ";
		
		try {
			st = conn.createStatement();
			ret = st.executeUpdate(sql);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		return ret;
	}
	
	// 5. 부서 수정
	public int deptUpdate(DeptDto dept) {
		Connection conn = DBUtil.getConnection();
		Statement st = null;
		int ret = 0;
		String sql = "update departments set department_name = '"
				+ dept.getDepartment_name() + "', manager_id = "
				+ dept.getManager_id() + ", location_id = "
				+ dept.getLocation_id() + " where department_id = "
				+ dept.getDepartment_id();
		
		try {
			st = conn.createStatement();
			ret = st.executeUpdate(sql);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		return ret;
	}
	
	// 6. 부서 삭제
	public int deptDelete(int deptid) {
		Connection conn = DBUtil.getConnection();
		Statement st = null;
		int ret = 0;
		String sql = "delete from departments where department_id = "
				+ deptid;
		
		try {
			st = conn.createStatement();
			ret = st.executeUpdate(sql);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		return ret;
	}
}
















