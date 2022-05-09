package controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import dto.EmpVo;
import jdbcProjec.util.DateUtil;
import model.EmpService;
import view.EmpView;

public class EmpController {
	static EmpService service = new EmpService();
	public static void main(String[] args) {
		f2();
	}
	
	private static void f10() {

		int result = service.deleteEmp(2);
		
		EmpView.print(result > 0 ?  result+"Success" : "Fail");
	}
	private static void f9() {
		
		EmpVo emp = new EmpVo();
		
		emp.setCommission_pct(0.22);
		emp.setDepartment_id(60);
		emp.setEmail("wpghks55@naver.com");
		emp.setEmployee_id(2);
		emp.setFirst_name("Cham23");
		emp.setHire_date(DateUtil.convertToDate("2022-04-16"));
		emp.setJob_id("IT_PROG");
		emp.setLast_name("Burin");
		emp.setManager_id(100);
		emp.setPhone_number("47489");
		emp.setSalary(2000);
		
		int result = service.updateEmpByDept(emp, 60);
		
		System.out.println(result > 0 ?  result+"Success" : "Fail");
	}
	private static void f8() {
		
		EmpVo emp = new EmpVo();
		
		emp.setCommission_pct(0.12);
		emp.setDepartment_id(60);
		emp.setEmail("wpghks55@naver.com");
		emp.setEmployee_id(2);
		emp.setFirst_name("Cham23");
		emp.setHire_date(DateUtil.convertToDate("2022-04-16"));
		emp.setJob_id("IT_PROG");
		emp.setLast_name("Burin");
		emp.setManager_id(100);
		emp.setPhone_number("47489");
		emp.setSalary(1000);
		
		int result = service.updateEmp(emp, 1);
		
		System.out.println(result > 0 ? "Success" : "Fail");
	}
	private static void f7() {
		
		EmpVo emp = new EmpVo();
		
		emp.setCommission_pct(0.12);
		emp.setDepartment_id(60);
		emp.setEmail("wpghks56@naver.com");
		emp.setEmployee_id(1);
		emp.setFirst_name("Cham");
		emp.setHire_date(DateUtil.convertToDate("2022-04-01"));
		emp.setJob_id("IT_PROG");
		emp.setLast_name("Burin");
		emp.setManager_id(100);
		emp.setPhone_number("4748");
		emp.setSalary(100);
		
		int result = service.insertEmp(emp);
		
		System.out.println(result > 0 ? "Success" : "Fail");
	}

	private static void f6() {
		
		EmpVo emp = service.selectById(101);
		
		System.out.println(emp);
	}
	
	private static void f5() {
		
		List<EmpVo> emplist = service.selecBymulti(60, "IT_PROG", 5000, "2006-01-01");
		
		emplist.forEach(System.out::println);
	}
	
	private static void f4() {
		
		List<EmpVo> emplist = service.selecByJob("IT_PROG");
		
		emplist.forEach(System.out::println);
	}
	
	private static void f3() {
		
		List<EmpVo> emplist = service.selecByMng(103);
		
		emplist.forEach(System.out::println);
	}
	
	private static void f2() {
		
		List<EmpVo> emplist = service.selecByDept(60);
		
		emplist.forEach(System.out::println);
	}

	private static void f1() {
		EmpView.print(service.allselect());
	}

}
