public class Data {
	// �ڷγ� ���� ������ ���� �ʵ���� �����Ѵ�.
	// �⺻ ������ �Ժη� ���� �Ұ����ϵ��� private ������ �Ѵ�.
	private String seq;
	private String createDt;
	private	String examCnt;
	private String decideCnt;
	private String deathCnt;
	
	// �ش� ������ �޴� �����ڸ� �߰�
	public Data(String seq, String createDt, String examCnt, String decideCnt, String deathCnt) { 
		this.seq = seq;
		this.createDt = createDt;
		this.examCnt = examCnt;
		this.decideCnt = decideCnt;
		this.deathCnt = deathCnt;
	}
	
	// private �� �����͵鿡 ������ �� �ִ� getter �� �߰�.
	public String getSeq() {
		return seq;
	}
	public String getCreateDt() {
		return createDt;
	}
	public String getExamCnt() {
		return examCnt;
	}
	public String getDecideCnt() {
		return decideCnt;
	}
	public String getDeathCnt() {
		return deathCnt;
	}
}
