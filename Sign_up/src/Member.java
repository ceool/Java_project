public class Member {


	// ȸ�� ����
	private String id;
	private String passwd;

	// Ŭ���� ���� ��, ���̵�� ��й�ȣ ���� �޵��� ����
	public Member(String id, String passwd) { 
		this.id = id;
		this.passwd = passwd;
	}

	// private �� ���� ������ ���� getter, setting
	public String getId() {
		return id;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
}