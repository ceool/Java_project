import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Module {


	// ȸ�� ������ �����ϱ� ���� ArrayList ����
	private ArrayList<Member> members = new ArrayList<Member>();

	// ȸ�� ������ ���� �� ����ϴ� ��ƾ
	// �� ����� ���������� ����ϴ� �Լ��̹Ƿ� private �Լ��� ����
	// ȸ�� ���� ��ɰ� ȸ�� �α��� ��� �ÿ� ����� �� �ִ� �Լ�
	// ������� �̸��� ��й�ȣ ���� �޾Ƽ�, Member Ŭ������ ��ȯ���ش�.

	private Member inputMember() {
		String id = null, passwd = null;

		try {
			// Ű���� �Է��� �ޱ� ���� BufferedReader ����
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

			// ���̵�� ����� �Է� ����.
			System.out.print("ID : ");
			id = in.readLine();

			System.out.print("PASSWD : ");
			passwd = in.readLine();

			// ������ bufferedReader �� close ���ִ� ���� ������, System.in �� �ѹ� ������ �ٽ� �ش� ���α׷����� ������ �ȵǱ� ������ 
			// �׳� ���� ���ִ°� �����ٰ� ����, ���α׷� ���� �ÿ� �ѹ� close ���ش�.
		}catch(IOException e) {
			// BufferedReader �� Ű���� �Է� ���� �� ����.
			// ���� �������� �߻������� ������, ���� ó���� �صξ�� ��.
			// �ش� �ڵ�� ������ ��� �߻��ߴ��� ǥ�����ִ� �Լ� 
			e.printStackTrace();
		}

		// ���� �����Ϳ� ������ �ִ� ���, null �� �������ش�.
		// �� �Լ��� ȣ���� �������� null �� ��쿡 ���� ó���� ���־�� �Ѵ�.
		if(id == null ||
				passwd == null ||
				id.isEmpty() ||
				passwd.isEmpty()) { 
			return null;
		}

		// id, passwd ���� ������ ������ Member Ŭ������ �����Ͽ� �� ��ü�� ��ȯ���ش�.
		return new Member(id, passwd);
	}

	public void register() {
		// ȸ�� ���� ����̶�� �޽����� ������ش�.
		System.out.println ("- ȸ�� ���� ����Դϴ�.");

		// ������ ���� inputMember �Լ��� ȣ���Ͽ�, ID, PASSWD �޴� ��ƾ�� ����Ѵ�.
		// inputMember �Լ��� �߸� �Է¹޾����� null ���� ���� �ް�, �ƴϸ� �������� Member Ŭ������ ���� �޴´�.
		Member member = inputMember();

		// �Է��� �߸��Ǿ�����, �߸��Ǿ��ٴ� �޽����� ����ϰ�, �Լ��� �����Ѵ�.
		if(member == null) {
			System.out.println("- ȸ�� ���Կ� �����߽��ϴ�. �ùٸ��� ���� �Է��Դϴ�.");
			return;
		}

		// �ùٸ� �Է��� ���, �ش� ������ Ȯ���ϰ� ������ �� �ִ� ��ƾ���� �����Ѵ�.
		// �Է¹�����������°��, ȸ�� ���� ó�����Ѵ�.
		// ���� ȸ�� ���� ó���� ȸ������Ʈ�� members ������ �߰��ϴ� ������ �Ϸ��Ѵ�.
		if(!checkMember(member.getId())) {
			members.add(member);
			System.out.println("- ȸ������ó���� �Ǿ����ϴ�.");
		}
	}

	public int login() {
		// �α��� ����̶�� �޽����� ����Ѵ�.
		System.out.println ("- �α��� ����Դϴ�.");

		// ������ ���� inputMember �Լ��� ȣ���Ͽ�, ID, PASSWD �޴� ��ƾ�� ����Ѵ�.
		// inputMember �Լ��� �߸� �Է¹޾����� null ���� ���� �ް�, �ƴϸ� �������� Member Ŭ������ ���� �޴´�.
		Member member = inputMember();

		// �Է� ���� ���� �߸��Ǿ�����, �߸��Ǿ��ٴ� �޽����� ����ϰ�, �Լ��� �����Ѵ�.
		// �ش� �κ��� �߻��� ���� ������, �׻� �����ϴ� ������ ���̴� ���� �߿��ϴ�.
		if(member == null) {
			// �α��� ���з� ó���Ѵ�.
			// �Ʒ��� ����Ǿ� ������, �˼����� ������ �α��ο� ������ ��쿡 ���� ��ȯ �� ���� .
			return RESULT_ERR_UNKNOWN;
		}

		// �ùٸ� �Է��� ���, �ش� ������ Ȯ���Ͽ� �α��� ó���� �� �� �ֵ��� �Ѵ�.
		// ��ȯ ���� ���� �ٸ� ����� ������ֵ��� �� �Լ��� ȣ���� ������ ó�����ش�.
		int result = checkLogin(member);
		return result;
	}

	public void showMembers() {
		System.out.println("- ȸ�� ��� ����Դϴ�.");
		// * same code
		// for(int i=0; i<members.size(); ++i){
		//	System.out.println("ID : " + members.get(i).getId() + ", PASSWD : " + members.get(i).getPasswd());
		//}

		// for each ���� �̿��Ͽ� ������ ǥ�Ⱑ �����ϴ�.
		// ���� index ������� ����Ϸ��� ���� ������ �����ϼ���.
		for(Member member : members) {
			System.out.println("ID : " + member.getId() + ", PASSWD : " + member.getPasswd());
		}
	}

	/**
	 *	checkMember �Լ��� id ������ �޾�, �ش� ������ �� Ŭ������ ȸ�� ���� ���� ������ members �� ����Ǿ� �ִ��� Ȯ���ϴ� ���� �ʿ��ϴ�.
	 *	�̹� �ִ� ����� Ȯ���ϴ� ���̱� ������ ����� ���̵� ���� �Ű������� �޾Ƽ�, members ���� �ִ��� üũ�Ѵ�.
	 *	�̹� �ִ� ����� ��� true, ���� ����̸� false �� ��ȯ�ϵ��� �����Ѵ�.
	 */

	private boolean checkMember(String id) {
		// ���� �ʿ�. ����� ������ �����ϴ� ������ üũ�Ǿ� ����.
		for (Member member : members) 
		{
			if (member.getId().equals(id))  // �˻�� ������
			{
				System.out.println("�̹� �����ϴ� ID�Դϴ�.");
				return true;
			}
		} 
		return false;
	}

	/**
	 *	checkMember �Լ��� member ��ü�� �޾�, �ش� ������ �� Ŭ������ ȸ�� ���� ���� ������ members �� ����Ǿ� �ִ��� Ȯ���ϴ� ���� �ʿ��ϴ�.
	 *	������� ���̵�, ��й�ȣ�� ��ġ�ϴ��� Ȯ���Ͽ� �´� ������ �ִ� ���, �α��� ó���� �Ѵ�.
	 *	3���� ��쿡 ���� ��ȯ���� �ٸ��� �Ѵ�.
	 *	- ID, PASSWD �� members �ȿ� ��ġ�ϵ��� �ִ� ���, 0(RESULT_OK) �� ��ȯ
	 *	- ID �� �����ϸ鼭, ��й�ȣ�� Ʋ�� ���, 1(RESULT_ERR_PASSWD) �� ��ȯ
	 *	- ID �� �������� �ʴ� ���, 2(RESULT_ERR_LOGIN) �� ��ȯ
	 */

	// �� ������ Ư�� ���ڰ��� �ڵ带 �Ⱥ��� �� �� �ֵ��� ���ȭ ���ѵ� ������, �ܺο��� ��밡���ϰ� public static ���·� �����Ѵ�.
	public static final int RESULT_OK = 0;
	public static final int RESULT_ERR_PASSWD = 1;
	public static final int RESULT_ERR_LOGIN = 2;
	public static final int RESULT_ERR_UNKNOWN = 3;

	private int checkLogin(Member member) {
		// ���� �ʿ�. ����� ������ RESULT_OK �� ��ȯ�ǵ��� �����Ǿ� ����.
		for (Member member1 : members) 
		{
			if (member1.getId().equals(member.getId()))  
			{	
				// ID���� �˻�
				if(member1.getPasswd().equals(member.getPasswd())) 
					// ID�� ������ PW�� �˻�
					return RESULT_OK; //ID�� PW �� �� ������ ��ȯ
				else
					return RESULT_ERR_PASSWD; //ID�� ������ ��ȯ
			}
		} 
		return RESULT_ERR_LOGIN; // ID�� PW �� �� ������ ��ȯ
	}
}

