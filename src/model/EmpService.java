package model;

import java.util.List;

import dto.EmpVo;

public class EmpService {
	EmpDAO empdao = new EmpDAO();
	
	// 1. 모든 직원 조회
	public List<EmpVo> allselect()	{
		return empdao.allselect();
	}
	
	// 2. 조건 조회 (특정부서)
	public List<EmpVo> selecByDept(int deptid) {
		return empdao.selecByDept(deptid);
	}
	
	// 3. 조건 조회 (특정 매니저가 관리하는 직원)
	public List<EmpVo> selecByMng(int managerid) {
		return empdao.selecByMng(managerid);
	}
	
	// 4. 조건 조회 (특정 직업을 가진 사람)
	public List<EmpVo> selecByJob(String jobid) {
		return empdao.selecByJob(jobid);
	}
	
	// 5. 조건 조회 (부서, 직업, 월급 >=, 입사일 >=)
	public List<EmpVo> selecBymulti(int deptid, String jobid, double salary, String hire) {
		return empdao.selecBymulti(deptid, jobid, salary, hire);
	}
	
	public EmpVo selectById(int empid) {
		return empdao.selectById(empid);
	}
	
	// 6. insert
	public int insertEmp(EmpVo ev) {
		return empdao.insertEmp(ev);
	}
	
	// 7. update (특정 직원 1건을 수정)
	public int updateEmp(EmpVo ev, int empid) {
		return empdao.updateEmp(ev, empid);
	}
	
	// 8. update (같은 부서 직원 전부 수정)
	public int updateEmpByDept(EmpVo ev, int deptid) {
		return empdao.updateEmpByDept(ev, deptid);
	}
	
	// 9. delete (특정 직원만)
	public int deleteEmp(int empid) {
		return empdao.deleteEmp(empid);
	}
	
	// 10. delete (같은 조건 직원 전부)
	public int deleteEmpByDept(int deptid) {
		return empdao.deleteEmpByDept(deptid);
	}
}





















