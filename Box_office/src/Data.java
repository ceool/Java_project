public class Data {

	private String rank; //��ũ
	private String movieNm; //��ȭ��
	private String openDt; //������
	private	String audiCnt; //������
	private String audiAcc; //����������
	private String salesAcc; //���� �����
	
	// �ش� ������ �޴� �����ڸ� �߰�
	public Data(String rank, String movieNm, String openDt, String audiCnt, String audiAcc, String salesAcc) { 
		this.rank = rank;
		this.movieNm = movieNm;
		this.openDt = openDt;
		this.audiCnt = audiCnt;
		this.audiAcc = audiAcc;
		this.salesAcc = salesAcc;
	}
	
	// private �� �����͵鿡 ������ �� �ְ���.
	public String getrank() {
		return rank;
	}
	public String getmovieNm() {
		return movieNm;
	}
	public String getopenDt() {
		return openDt;
	}
	public String getaudiCnt() {
		return audiCnt;
	}
	public String getaudiAcc() {
		return audiAcc;
	}
	public String getsalesAcc() {
		return salesAcc;
	}
}
