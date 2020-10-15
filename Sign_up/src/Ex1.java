import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Ex1 {
	public static void main(String[] args) {

		BufferedReader in = null;
		Module mod = new Module();
		
		try {
			// Ű����κ��� �ޱ� ���� System.in �� �ְ� BufferedReader �� �ʱ�ȭ ��Ŵ.
			in = new BufferedReader(new InputStreamReader(System.in));
			// �޴��� �Է��϶�� �޽����� ����ϱ� ��
			// System.out. print In ��ſ� print �� ����ϴ� ������ �ڵ����� �� �ѱ��� ���� �ʵ��� �ϱ� ����.
			
			while(true)
			{
				System.out.print("�޴� �Է�(1.ȸ������,2.�α���,3.��������Ȯ��(����:x) : ");

				// String ���� �޴� ������ ���� �̿��� ���� ���� ����ó���� �����ϱ� ����
				// Buffer �ι޴¹���� �ֱ���ϳ�, �� �����ϱ� ������.

				String tempMenu = in.readLine();

				if(tempMenu.equals("x"))
				{
					System.out.println("���α׷��� �����մϴ�.");
					in.close(); //���α׷��� ����Ǳ� ������ ���⼭ BufferedReader �� close �Ͽ� �������ش�.
					return;
				}

				int menu = 0;
				
				try {
					menu = Integer.parseInt(tempMenu);
				}catch(NumberFormatException e) {
					// ���� �̿��� ���� ������ ���, ���α׷��� �����ϱ� ���� ��ƾ .
					// ������ ���α׷��� ����ǵ��� ������ �����Ͽ�����, ���� ���� �ÿ��� �� ��ƾ�� �����ؾ� ��.
					System.out.println("���ڷθ� �Է����ּ���. ");
					//System.exit(0);
					continue;
				}

				if(menu == 1) {
					System.out.println("ȸ������ �޴��Դϴ�.");
					mod.register();
				}
				else if(menu == 2) {
					System.out.println("�α��� �޴��Դϴ�.");
					int log = mod.login();
					
					if(log == Module.RESULT_OK)
						System.out.println("�α��� �Ǿ����ϴ�.");
					
					else if(log == Module.RESULT_ERR_PASSWD)
						System.out.println("ID�� ���������� ��й�ȣ�� Ʋ���ϴ�.");
					
					else if(log == Module.RESULT_ERR_LOGIN)
						System.out.println("ID�� PW�� �ٽ� �Է����ּ���.");
					
					else if(log == Module.RESULT_ERR_UNKNOWN)
						System.out.println("ID�� PW�� ��� �Է����ּ���.");
					
					else
						System.out.println("�� �� ���� ������ �߻��߽��ϴ�.");
				}
				else if(menu == 3) {
					System.out.println("ȸ������ ��� �޴��Դϴ�.");
					mod.showMembers();
				}
				else {
					
					System.out.println("�ùٸ��� �ʴ� ��ɾ� �Դϴ�.");
					//System.exit(0);
				}
			}


			
		}catch(Exception e) {
			// �Է� �޴� �߿� ����� ������ close �ϴ� �߿� �������� ��� ���Ⱑ ȣ��ȴ�.
			// ���� ���� ���������� ���Ⱑ ȣ��Ǵ� ��찡 ������, ���α׷� ��, ���� ó�� ��ƾ�� �صδ� ���� �ʿ��ϴ�.
		}
	}
}