import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Module {


	// 회원 정보를 저장하기 위한 ArrayList 선언
	private ArrayList<Member> members = new ArrayList<Member>();

	// 회원 정보를 받을 때 사용하는 루틴
	// 이 기능은 내부적으로 사용하는 함수이므로 private 함수로 선언
	// 회원 가입 기능과 회원 로그인 기능 시에 사용할 수 있는 함수
	// 사용자의 이름과 비밀번호 값을 받아서, Member 클래스로 반환해준다.

	private Member inputMember() {
		String id = null, passwd = null;

		try {
			// 키보드 입력을 받기 위한 BufferedReader 생성
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

			// 아이디와 비번을 입력 받음.
			System.out.print("ID : ");
			id = in.readLine();

			System.out.print("PASSWD : ");
			passwd = in.readLine();

			// 원래는 bufferedReader 를 close 해주는 것이 맞으나, System.in 은 한번 닫히면 다시 해당 프로그램에서 오픈이 안되기 때문에 
			// 그냥 오픈 되있는걸 가져다가 쓰고, 프로그램 종료 시에 한번 close 해준다.
		}catch(IOException e) {
			// BufferedReader 로 키보드 입력 받을 때 에러.
			// 현재 예제서는 발생하지는 않지만, 예외 처리는 해두어야 함.
			// 해당 코드는 에러가 어떻게 발생했는지 표시해주는 함수 
			e.printStackTrace();
		}

		// 받은 데이터에 오류가 있는 경우, null 을 리턴해준다.
		// 이 함수를 호출한 곳에서는 null 인 경우에 대한 처리를 해주어야 한다.
		if(id == null ||
				passwd == null ||
				id.isEmpty() ||
				passwd.isEmpty()) { 
			return null;
		}

		// id, passwd 값에 문제가 없으면 Member 클래스를 생성하여 그 객체를 반환해준다.
		return new Member(id, passwd);
	}

	public void register() {
		// 회원 가입 기능이라는 메시지를 출력해준다.
		System.out.println ("- 회원 가입 기능입니다.");

		// 이전에 만든 inputMember 함수를 호출하여, ID, PASSWD 받는 루틴을 사용한다.
		// inputMember 함수는 잘못 입력받았으면 null 값을 리턴 받고, 아니면 정상적인 Member 클래스를 리턴 받는다.
		Member member = inputMember();

		// 입력이 잘못되었으면, 잘못되었다는 메시지를 출력하고, 함수를 종료한다.
		if(member == null) {
			System.out.println("- 회원 가입에 실패했습니다. 올바르지 않은 입력입니다.");
			return;
		}

		// 올바른 입력인 경우, 해당 정보를 확인하고 저장할 수 있는 루틴으로 진입한다.
		// 입력받은멤버가없는경우, 회원 가입 처리를한다.
		// 현재 회원 가입 처리는 회원리스트인 members 변수에 추가하는 것으로 완료한다.
		if(!checkMember(member.getId())) {
			members.add(member);
			System.out.println("- 회원가입처리가 되었습니다.");
		}
	}

	public int login() {
		// 로그인 기능이라는 메시지를 출력한다.
		System.out.println ("- 로그인 기능입니다.");

		// 이전에 만든 inputMember 함수를 호출하여, ID, PASSWD 받는 루틴을 사용한다.
		// inputMember 함수는 잘못 입력받았으면 null 값을 리턴 받고, 아니면 정상적인 Member 클래스를 리턴 받는다.
		Member member = inputMember();

		// 입력 받은 값이 잘못되었으면, 잘못되었다는 메시지를 출력하고, 함수를 종료한다.
		// 해당 부분은 발생할 일은 없지만, 항상 구현하는 버릇을 들이는 것이 중요하다.
		if(member == null) {
			// 로그인 실패로 처리한다.
			// 아래에 선언되어 있으며, 알수없는 이유로 로그인에 실패한 경우에 대한 반환 값 설정 .
			return RESULT_ERR_UNKNOWN;
		}

		// 올바른 입력인 경우, 해당 정보를 확인하여 로그인 처리를 할 수 있도록 한다.
		// 반환 값에 따라 다른 결과를 출력해주도록 이 함수를 호출한 곳에서 처리해준다.
		int result = checkLogin(member);
		return result;
	}

	public void showMembers() {
		System.out.println("- 회원 출력 기능입니다.");
		// * same code
		// for(int i=0; i<members.size(); ++i){
		//	System.out.println("ID : " + members.get(i).getId() + ", PASSWD : " + members.get(i).getPasswd());
		//}

		// for each 문을 이용하여 간단히 표기가 가능하다.
		// 기존 index 방법으로 사용하려면 위의 예제를 참고하세요.
		for(Member member : members) {
			System.out.println("ID : " + member.getId() + ", PASSWD : " + member.getPasswd());
		}
	}

	/**
	 *	checkMember 함수는 id 변수를 받아, 해당 정보가 이 클래스의 회원 정보 저장 변수인 members 에 저장되어 있는지 확인하는 것이 필요하다.
	 *	이미 있는 멤버를 확인하는 것이기 때문에 멤버의 아이디 값을 매개변수로 받아서, members 내에 있는지 체크한다.
	 *	이미 있는 멤버인 경우 true, 없는 멤버이면 false 를 반환하도록 구현한다.
	 */

	private boolean checkMember(String id) {
		// 구현 필요. 현재는 무조건 존재하는 것으로 체크되어 있음.
		for (Member member : members) 
		{
			if (member.getId().equals(id))  // 검색어가 존재함
			{
				System.out.println("이미 존재하는 ID입니다.");
				return true;
			}
		} 
		return false;
	}

	/**
	 *	checkMember 함수는 member 객체를 받아, 해당 정보가 이 클래스의 회원 정보 저장 변수인 members 에 저장되어 있는지 확인하는 것이 필요하다.
	 *	멤버들의 아이디, 비밀번호가 일치하는지 확인하여 맞는 정보가 있는 경우, 로그인 처리를 한다.
	 *	3가지 경우에 따라 반환값을 다르게 한다.
	 *	- ID, PASSWD 가 members 안에 일치하도록 있는 경우, 0(RESULT_OK) 를 반환
	 *	- ID 는 존재하면서, 비밀번호가 틀린 경우, 1(RESULT_ERR_PASSWD) 를 반환
	 *	- ID 도 존재하지 않는 경우, 2(RESULT_ERR_LOGIN) 를 반환
	 */

	// 이 변수는 특정 숫자값을 코드를 안보고도 알 수 있도록 상수화 시켜둔 것으로, 외부에서 사용가능하게 public static 형태로 선언한다.
	public static final int RESULT_OK = 0;
	public static final int RESULT_ERR_PASSWD = 1;
	public static final int RESULT_ERR_LOGIN = 2;
	public static final int RESULT_ERR_UNKNOWN = 3;

	private int checkLogin(Member member) {
		// 구현 필요. 현재는 무조건 RESULT_OK 가 반환되도록 구현되어 있음.
		for (Member member1 : members) 
		{
			if (member1.getId().equals(member.getId()))  
			{	
				// ID부터 검색
				if(member1.getPasswd().equals(member.getPasswd())) 
					// ID가 맞으면 PW도 검색
					return RESULT_OK; //ID와 PW 둘 다 맞으면 반환
				else
					return RESULT_ERR_PASSWD; //ID만 맞으면 반환
			}
		} 
		return RESULT_ERR_LOGIN; // ID와 PW 둘 다 없으면 반환
	}
}

