import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

// UI 를 설정하기 위해  JFrame 을 상속받는 클래스로 만든다.
public class MainUI extends JFrame {

	// 정보를 가져올 데이터 URL 상수 설정, 여기서만 사용하는 변수라 private 설정을 하였다.
	private static final String TARGET_URL = "https://finance.naver.com/api/sise/etfItemList.nhn";

	// 정보를 화면에 뿌려줄 list 에 연결된 모델 데이터, 해당 모델에 데이터를 넣으면 리스트에 저장된다.
	private DefaultListModel<String> dataModel;

	public MainUI() {
		// UI 에 대한 설정들

		// 사이즈 설정
		setSize(460, 300);
		// 레이아웃을 절대 좌표로 그릴 수 있도록 하기 위해 레이아웃 설정 제거
		setLayout(null);
		// 타이틀 설정
		setTitle("코로나 정보");

		// 종료 버튼 누르면 프로그램 종료 설정
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// 사이즈 변경 불가
		setResizable(false);

		// 화면 가운데로 위치 설정
		setLocationRelativeTo(null);

		// 버튼 위치 설정
		JButton buttonRefresh = new JButton("Refresh");
		// 20, 20 위치에 100, 50 사이즈의 버튼
		buttonRefresh.setBounds(20, 20, 100, 30);
		// 버튼 이벤트 설정
		buttonRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 인터넷 연결이 필요하기 때문에 연속으로 누르는 것을 방지하기 위해 버튼 활성화를 끔
				buttonRefresh.setEnabled(false);
				
				// Thread 로 덮어서, 버튼 멈춘 현상 제거
				// 원래는 handler 와 같은 루틴을 이용하여 main thread 에 message 를 날려 ui 를 refresh 하는 작업을 하는 것이 정석
				// 여기서는 간단히 해결 하기 위해 그냥 전체 영역을 thread 로 덮음.
				// 안드로이드같은 플랫폼에서는 이렇게 하면 오류가 발생함.
				new Thread() {
					@Override
					public void run() {
						// 이전 데이터 제거
						dataModel.clear();
						// 코로나 주소를 요청한다.
						final String API_KEY = "본인 API"; 
						// 예제에서 반드시 입력할 url parameter로 검색기간을 정할 수 있음.
						final String startDate = "20200310";
						final String endDate = "20200509";

						// 나머지 page 정보나 한번에 보여줄 정보는 고정해서 사용하고, 필요시 변경 가능.
						String url = String.format("http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson"
								+ "?serviceKey=%s&pageNo=l&numOfRows=10&startCreateDt=%s&endCreateDt=%s", API_KEY, startDate, endDate);
						// HttpModule 에 작성한함수로 xml 데이터를얻어오기.
						// 여기서 얻어지는 Document 클래스는 import 시 org.w3c.dom.Docunient 여야함.
						// Document 는 많이 사용되는 클래스 명이라 이상한걸 import 하는 실수가 있을 수 있으니, 그럴 경우 위에서 수동으로 제거후 다시 추가 필요.
						Document res = HttpModule.requestXmlGet(url);

						// 얻어온 결과에서 opt 내에 xml 포맷이 있으니 어떤 key 값에 대한 item 을 가져올지 설정이 필요.
						// 여기서는 item 키 값을 가져오면 결과 값들을 받을 수 있으니, 간단히 item key 값에 해당하는 것들을 받아오는 것으로 함.
						// 여기서 Node 는 org.w3c.dom.NodeList
						// 현재 코로나 데이터에 보면 item 내에 키 중에 사용할 키 값 리스트를 받는다.
						// clearCnt, createDt, deathCnt, decideCnt, examCnt, seq

						// 각 키 값에 대한 리스트를 받아온다.
						// 각 키 값에 대한 값은 해당 API 문서에 어떤 값인지 적혀 있으니 참조.
						NodeList accExamCnts = res.getElementsByTagName("accExamCnt");
						NodeList seqs = res.getElementsByTagName("seq");
						NodeList createDts = res.getElementsByTagName("createDt");
						NodeList examCnts = res.getElementsByTagName("examCnt");
						NodeList decideCnts = res.getElementsByTagName("decideCnt");
						NodeList deathCnts = res.getElementsByTagName("deathCnt");

						// head 부분을 넣어주기 위한 부분
						// 구분은 space 으로 해야 보임 .
						dataModel.addElement(" 순서                         시간                         검사수          확진자수        사망자수");
 
						// 각 키에 대한 결과 길이는 같으니 아무거나 length 로 사용하고 각각 nodevalue 를 출력한다.
						// 몇몇 다른 키는 길이가 다르니, 이를 참고해서 다른 api에는 똑같이 사용이 불가할 수 있음.
						for(int i=0; i<accExamCnts.getLength(); ++i) {
							// 여기서 Node 는 org.w3c.doni.Node
							Node seq = seqs.item(i);
							Node createDt = createDts.item(i);
							Node examCnt = examCnts.item(i);
							Node decideCnt = decideCnts.item(i);
							Node deathCnt = deathCnts.item(i);

							// 각 list 에 해당하는 각 아이템의 첫번째 값들을 원하는 포맷으로 저장한다.
							// 날짜 값은 뒤에 ms 단위는 제거한다.
							String line = String.format(" %s            %s         %s             %s             %s",
									seq.getFirstChild().getNodeValue(),
									createDt.getFirstChild().getNodeValue().substring(0, createDt.getFirstChild().getNodeValue().length()-3),
									examCnt.getFirstChild().getNodeValue(), 
									decideCnt.getFirstChild().getNodeValue(),
									deathCnt.getFirstChild().getNodeValue());

							// list 에 넣어준다.
							dataModel.addElement(line);
							// 최소한의 delay 를 주는 것이 필요, main ui 와 sync 가 안맞아서 동작을 안하는 경우가 있음.
							// 원래 정석대로라면 main thread 에서 UI 를 건들여 주는 것이 정석이지만, 복잡해지므로 간단하게 해결.
							try {
								Thread.sleep(1);
							}catch(InterruptedException e) {
							}
						}
						// 모든 동작이 끝난 뒤에 버튼을 다시 활성화 시켜줌.
						buttonRefresh.setEnabled(true);
					}
				}.start();
			}
		});



		// 리스트 위치 설정 및 초기화, 스크롤 설정
		JList<String> dataList = new JList<String>(new DefaultListModel<String>());
		JScrollPane scrollPane = new JScrollPane(dataList);
		scrollPane.setBounds(20, 60, 410, 200);

		// 해당 리스트와 연결된 모델 값을 저장한다.
		dataModel = (DefaultListModel<String>)dataList.getModel();

		// 버튼 및 리스트를 UI 에 넣기
		add(buttonRefresh);
		add(scrollPane);
	}

	// UI 띄우는 함수
	public void showUI() {
		setVisible(true);
	}
}
