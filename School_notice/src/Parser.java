import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Parser {
	// �Ѹ����б� �������� ������ �ҷ��� �� �ִ� �Լ�.
	// �ҷ��� �����ʹ� ���� ���̱� ������ ��� ���� Board ������ ������ ArrayList �� ��ȯ�Ѵ�.
	// ������ �߻��ϴ� ���� arrayList �� �� ���� return �� .
	public static ArrayList<Board> getHallymNotice(){
		// ����� �ʱ�ȭ, ������ �߻��ϴ� ���, �� arraylist�� ���ϵ�.

		ArrayList<Board> result = new ArrayList<Board>();
		try {
			// html ������ �����´�. jsoup ���̺귯�� �̿�.
			// import org.jsoup.Jsoup;
			// import org.jsoup.nodes.Document;
			Document doc = Jsoup.connect("https://www.hallym.ac.kr/hallym_univ/sub05/cP3/sCP1.html").get();
			// ���������׸� table ���� ã���� ��.
			// li html tag �� ã��, �� �߿��� tbl-row class ���� ������ ������ ���� ã�Ƽ� �����´�.
			// import org.jsoup.select.Elements;
			Elements notices = doc.select("li.tbl-row");
			// ��ü table �������ѷ�����.
			for(int i=0; i<notices.size(); ++i) {
				// import org.jsoup.nodes.Element; �ʿ�
				Element notice = notices.get(i);
				
				// �� �׸� ���� ����, ���ο� span �±��� 1��°�� �����Ͽ� text ������ .
				String id = notice.select("span").get(0).text();
				
				// title�� a �±׿��� ������ url ���� a �±��� href �׸��� ���´�.
				String title = notice.select("a").get(0).text();
				String url = notice.select("a").get(0).attr("href");
				
				// writer �� span �±��� 8��° �׸��� �����ؼ� ǥ��.
				String writer = notice.select("span").get(7).text();
				
				// datetime �� span �±��� 14��° �׸��� �����ؼ� ǥ��.
	            // ������� ��� ÷������ ���ο� ���� �׸� ��°�� �ٲ�Ƿ�, class ������ ã�Ƽ� ���� span �� �ٽ� ã�� ����� ���
	            // ������� class ���� col col-5 tc �̹Ƿ� �Ʒ��� ���� ����.
	            // �ش� Ŭ������ �ش� �ϴ� �׸��� 1�� �̹Ƿ� 0 ���� ���� �Ŀ� �ȿ��� span �� ã��, �� �� ���� ������ 2��° �׸��� ����
	            // �ڱ� �ڽ� span ���� �����̱� ������ 2��° �׸��� �����ϵ��� �Ѵ�.
	            String datetime = notice.select("span.col.col-5.tc").get(0).select("span").get(2).text();
	            
				// ��� ���� �����Ѵ�.
				result.add(new Board(id, title, writer, datetime, url));
			}
		} catch (IOException e) {
			// ���ͳ� ���� �̻� ���� ������ �߻��� �� �־�, try catch �ʼ�.
			e.printStackTrace();
		}
		return result;
	}
}
