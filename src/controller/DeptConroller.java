package controller;

import java.util.*;

import dto.DeptDto;
import model.DeptService;
import view.DeptView;

// controller : 사용자의 요청을받아서 service에 보낸다.
// 서비스가 return한 정보를 사용자에게 응답한다.
public class DeptConroller {
	static Scanner sc = new Scanner(System.in);
	static boolean confirm = true;
	static DeptService deptservice = new DeptService();
	
	public static void main(String[] args) {
		while(confirm) {
			menuDisplay();
			int job = sc.nextInt();
			businessLogic(job);
		}
		
		System.out.println("끝");
	}
	
	private static void businessLogic(int job) {
		switch (job) {
			case 99: confirm = false;
				break;
			case 1: List<DeptDto> deptlist = deptservice.all();
				DeptView.print(deptlist);
				break;
			case 2:
				System.out.print("검색할 부서 번호를 입력하세요 >> ");
				DeptDto dept = deptservice.selectById(sc.nextInt());
				DeptView.print(dept);
				break;
			case 3: 
				System.out.print("검색할 지역 번호를 입력하세요 >> ");
				List<DeptDto> deptl = deptservice.selectByLocation(sc.nextInt());
				DeptView.print(deptl);
				break;
			case 4: {
				System.out.print("입력할 부서번호, 이름, 매니저, 지역 >> ");
				int result = deptservice.deptInsert(new DeptDto(sc.nextInt(), sc.next(), sc.nextInt(), sc.nextInt()));
				DeptView.print(result > 0 ? "insert success" : "insert fail");
				break;}
			case 5: {
				System.out.print("수정할 부서번호, 이름, 매니저, 지역 >> ");
				int result = deptservice.deptUpdate(new DeptDto(sc.nextInt(), sc.next(), sc.nextInt(), sc.nextInt()));
				DeptView.print(result > 0 ? "update success" : "update fail");
				break;}
			case 6: 
				System.out.print("삭제할 부서번호 >> ");
				int result = deptservice.deptDelete(sc.nextInt());
				DeptView.print(result > 0 ? "delete success" : "delete fail");
				break;
			default : break;
		}
	}
	private static void menuDisplay() {
		System.out.println("=-=-=-=-=-=-=-=-=-=-=-=");
		System.out.println("1. 모든 부서 조회");
		System.out.println("2. 특정 부서 조회");
		System.out.println("3. 특정 지역 부서 조회");
		System.out.println("4. 부서 입력");
		System.out.println("5. 부서 수정");
		System.out.println("6. 부서 삭제");
		System.out.println("99. exit");
		System.out.println("=-=-=-=-=-=-=-=-=-=-=-=");
		System.out.print("작업 선택 >> ");
	}

}














