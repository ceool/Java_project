import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Ex1 {
	public static void main(String[] args) {

		BufferedReader in = null;
		Module mod = new Module();
		
		try {
			// 키보드로부터 받기 위해 System.in 을 넣고 BufferedReader 를 초기화 시킴.
			in = new BufferedReader(new InputStreamReader(System.in));
			// 메뉴를 입력하라는 메시지를 출력하기 용
			// System.out. print In 대신에 print 를 사용하는 이유는 자동으로 줄 넘김이 되지 않도록 하기 위함.
			
			while(true)
			{
				System.out.print("메뉴 입력(1.회원가입,2.로그인,3.가입정보확인(종료:x) : ");

				// String 으로 받는 이유는 숫자 이외의 값에 대한 예외처리에 용이하기 때문
				// Buffer 로받는방법이 있기는하나, 더 구현하기 불편함.

				String tempMenu = in.readLine();

				if(tempMenu.equals("x"))
				{
					System.out.println("프로그램을 종료합니다.");
					in.close(); //프로그램이 종료되기 때문에 여기서 BufferedReader 를 close 하여 종료해준다.
					return;
				}

				int menu = 0;
				
				try {
					menu = Integer.parseInt(tempMenu);
				}catch(NumberFormatException e) {
					// 숫자 이외의 값이 나왔을 경우, 프로그램을 종료하기 위한 루틴 .
					// 지금은 프로그램이 종료되도록 예제를 구현하였지만, 숙제 제출 시에는 이 루틴을 수정해야 함.
					System.out.println("숫자로만 입력해주세요. ");
					//System.exit(0);
					continue;
				}

				if(menu == 1) {
					System.out.println("회원가입 메뉴입니다.");
					mod.register();
				}
				else if(menu == 2) {
					System.out.println("로그인 메뉴입니다.");
					int log = mod.login();
					
					if(log == Module.RESULT_OK)
						System.out.println("로그인 되었습니다.");
					
					else if(log == Module.RESULT_ERR_PASSWD)
						System.out.println("ID는 존재하지만 비밀번호가 틀립니다.");
					
					else if(log == Module.RESULT_ERR_LOGIN)
						System.out.println("ID와 PW를 다시 입력해주세요.");
					
					else if(log == Module.RESULT_ERR_UNKNOWN)
						System.out.println("ID와 PW를 모두 입력해주세요.");
					
					else
						System.out.println("알 수 없는 오류가 발생했습니다.");
				}
				else if(menu == 3) {
					System.out.println("회원정보 출력 메뉴입니다.");
					mod.showMembers();
				}
				else {
					
					System.out.println("올바르지 않는 명령어 입니다.");
					//System.exit(0);
				}
			}


			
		}catch(Exception e) {
			// 입력 받는 중에 생기는 오류나 close 하는 중에 오류나는 경우 여기가 호출된다.
			// 물론 현재 예제에서는 여기가 호출되는 경우가 없지만, 프로그램 상, 예외 처리 루틴을 해두는 것이 필요하다.
		}
	}
}