package view;

import java.util.List;

import dto.DeptDto;

// View : 사용자에게 결과를 출력
// Web : jsp로 작성될 예정이다.
public class DeptView {
	// 1. 여러건의 dept정보를 출력
	public static void print(List<DeptDto> depts) {
		System.out.println("=-=-=-=-부서 여러건=-=-=-=-=");
		
		for(DeptDto dept : depts) {
//			System.out.println("부서번호 : " + dept.getDepartment_id());
//			System.out.println("부서이름 : " + dept.getDepartment_name());
//			System.out.println("메니저 사번 : " + dept.getManager_id());
//			System.out.println("지역번호 : " + dept.getLocation_id());
			System.out.println(dept);
		}
	}
	
	// 2. 한건의 dept정보를 출력
	public static void print(DeptDto dept) {
		if(dept == null) {
			print("조회된 데이터가 없습니다.");
			return;
		}
		System.out.println("=-=-=-=-부서 한건=-=-=-=-=");
		System.out.println("부서번호 : " + dept.getDepartment_id());
		System.out.println("부서이름 : " + dept.getDepartment_name());
		System.out.println("메니저 사번 : " + dept.getManager_id());
		System.out.println("지역번호 : " + dept.getLocation_id());
	}
	
	// 3. 단순한 문자열을 출력
	public static void print(String message) {
		System.out.println("=-=-=-=-=-=-알림-=-=-=-=-=-=");
		System.out.println(message);
	}
	
}










