public class Member {


	// 회원 정보
	private String id;
	private String passwd;

	// 클래스 생성 시, 아이디와 비밀번호 값을 받도록 설정
	public Member(String id, String passwd) { 
		this.id = id;
		this.passwd = passwd;
	}

	// private 에 대한 접근을 위한 getter, setting
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