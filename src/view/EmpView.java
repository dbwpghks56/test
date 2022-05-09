package view;

import java.util.List;

import dto.EmpVo;

public class EmpView {
	public static void print(List<EmpVo> emplist) {
		emplist.forEach(System.out::println);
	}
	
	public static void print(EmpVo emp) {
		System.out.println(emp);
	}
	
	public static void print(String message) {
		System.out.println(message);
	}
}
