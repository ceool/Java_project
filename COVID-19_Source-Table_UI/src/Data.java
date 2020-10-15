public class Data {
	// 코로나 감염 정보에 대한 필드들을 정의한다.
	// 기본 값들을 함부로 변경 불가능하도록 private 선언을 한다.
	private String seq;
	private String createDt;
	private	String examCnt;
	private String decideCnt;
	private String deathCnt;
	
	// 해당 값들을 받는 생성자를 추가
	public Data(String seq, String createDt, String examCnt, String decideCnt, String deathCnt) { 
		this.seq = seq;
		this.createDt = createDt;
		this.examCnt = examCnt;
		this.decideCnt = decideCnt;
		this.deathCnt = deathCnt;
	}
	
	// private 된 데이터들에 접근할 수 있는 getter 만 추가.
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
