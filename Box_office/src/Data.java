public class Data {

	private String rank; //랭크
	private String movieNm; //영화명
	private String openDt; //개봉일
	private	String audiCnt; //관객수
	private String audiAcc; //누적관객수
	private String salesAcc; //누적 매출액
	
	// 해당 값들을 받는 생성자를 추가
	public Data(String rank, String movieNm, String openDt, String audiCnt, String audiAcc, String salesAcc) { 
		this.rank = rank;
		this.movieNm = movieNm;
		this.openDt = openDt;
		this.audiCnt = audiCnt;
		this.audiAcc = audiAcc;
		this.salesAcc = salesAcc;
	}
	
	// private 된 데이터들에 접근할 수 있게함.
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
