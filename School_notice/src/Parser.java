import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Parser {
	// 한림대학교 공지사항 정보를 불러올 수 있는 함수.
	// 불러온 데이터는 여러 개이기 때문에 결과 값은 Board 정보를 가지는 ArrayList 로 반환한다.
	// 에러가 발생하는 경우어 arrayList 가 빈 값이 return 됨 .
	public static ArrayList<Board> getHallymNotice(){
		// 결과값 초기화, 에러가 발생하는 경우, 빈 arraylist가 리턴됨.

		ArrayList<Board> result = new ArrayList<Board>();
		try {
			// html 정보를 가져온다. jsoup 라이브러리 이용.
			// import org.jsoup.Jsoup;
			// import org.jsoup.nodes.Document;
			Document doc = Jsoup.connect("https://www.hallym.ac.kr/hallym_univ/sub05/cP3/sCP1.html").get();
			// 공지사항항목 table 들을 찾도록 함.
			// li html tag 를 찾고, 그 중에서 tbl-row class 값을 가지는 값들을 전부 찾아서 가져온다.
			// import org.jsoup.select.Elements;
			Elements notices = doc.select("li.tbl-row");
			// 전체 table 정보를둘러본다.
			for(int i=0; i<notices.size(); ++i) {
				// import org.jsoup.nodes.Element; 필요
				Element notice = notices.get(i);
				
				// 각 항목에 대해 접근, 내부에 span 태그의 1번째에 접근하여 text 얻어오기 .
				String id = notice.select("span").get(0).text();
				
				// title은 a 태그에서 얻어오고 url 값은 a 태그의 href 항목을 얻어온다.
				String title = notice.select("a").get(0).text();
				String url = notice.select("a").get(0).attr("href");
				
				// writer 는 span 태그의 8번째 항목을 선택해서 표시.
				String writer = notice.select("span").get(7).text();
				
				// datetime 은 span 태그의 14번째 항목을 선택해서 표시.
	            // 등록일의 경우 첨부파일 여부에 따라 항목 번째가 바뀌므로, class 명으로 찾아서 안의 span 을 다시 찾는 방식을 사용
	            // 등록일의 class 명이 col col-5 tc 이므로 아래와 같이 선택.
	            // 해당 클래스명에 해당 하는 항목은 1개 이므로 0 으로 접근 후에 안에서 span 을 찾고, 그 중 내용 값으로 2번째 항목을 선택
	            // 자기 자신 span 까지 포함이기 때문에 2번째 항목을 선택하도록 한다.
	            String datetime = notice.select("span.col.col-5.tc").get(0).select("span").get(2).text();
	            
				// 결과 값에 저장한다.
				result.add(new Board(id, title, writer, datetime, url));
			}
		} catch (IOException e) {
			// 인터넷 상태 이상 등의 오류가 발생할 수 있어, try catch 필수.
			e.printStackTrace();
		}
		return result;
	}
}
