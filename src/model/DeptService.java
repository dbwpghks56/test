package model;

import java.util.List;

import dto.DeptDto;

// Service : Business logic 작성
// DB 작업은 DAO 호출한다.
public class DeptService {
	DeptDao deptDAO = new DeptDao();
	
	// 1. 모두 조회
	public List<DeptDto> all() {
		
		
		return deptDAO.all();
	}
	
	// 2. 특정 부서만 조회(부서코드로 조회)
	public DeptDto selectById(int deptid) {
		
		return deptDAO.selectById(deptid);
	}
	
	// 3. 특정 지역 코드로 조회
	public List<DeptDto> selectByLocation(int locid) {
		
		return deptDAO.selectByLocation(locid);
	}
	
	// 4. 부서 입력
	public int deptInsert(DeptDto dept) {
		
		return deptDAO.deptInsert(dept);
	}
	
	// 5. 부서 수정
	public int deptUpdate(DeptDto dept) {
		
		return deptDAO.deptUpdate(dept);
	}
	
	// 6. 부서 삭제
	public int deptDelete(int deptid) {
		
		return deptDAO.deptDelete(deptid);
	}
}


















