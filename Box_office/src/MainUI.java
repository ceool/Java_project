import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.StringReader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

// UI 를 설정하기 위해  JFrame 을 상속받는 클래스로 만든다.
public class MainUI extends JFrame {

	//파일 저장을 위한 선언들
	private String name=null; // 일별 박스오피스 or 주간 박스오피스
	private String code=null; // 불러온 xml 코드를 파일로 저장하기 위한 변수
	private String line=null; // 불러온 xml 코드를 보기편한 gui 형식으로 파일로 저장하기위한 변수
	private String day=null; // 날짜 입력을 사용할 때 사용할 변수
	
	
	// 정보를 뿌려줄 DataTableModel 선언
	private DataTableModel dataModel = new DataTableModel();

	//설명에 사용될 것들 선언
	private JTextField field2;
	private JTextField field3;

	public MainUI() {
		Font font1 = new Font("Dialog",Font.BOLD, 18);

		setSize(600, 430);
		// 레이아웃을 절대 좌표로 그릴 수 있도록 하기 위해 레이아웃 설정 제거
		setLayout(null);
		// 타이틀 설정
		setTitle("박스오피스TOP10");

		// 종료 버튼 누르면 프로그램 종료 설정
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// 사이즈 변경 불가
		setResizable(false);

		// 화면 가운데로 위치 설정
		setLocationRelativeTo(null);

		// 버튼 위치 설정
		JButton button1 = new JButton("일일 박스오피스 TOP10 출력");
		button1.setBounds(20, 20, 200, 50);
		JButton button2 = new JButton("주간 박스오피스 TOP10 출력");
		button2.setBounds(360, 20, 200, 50);
		JButton button3 = new JButton("불러온 코드 원본 저장");
		button3.setBounds(20, 330, 200, 50);
		JButton button4 = new JButton("나열된 영화 정보 저장");
		button4.setBounds(360, 330, 200, 50);

		// 날짜 입력 설정
		JTextField field1 = new JTextField();
		field1.setBounds(230, 40, 120, 30);
		field1.setFont(font1);
		field1.setEditable(true);
		field1.setHorizontalAlignment(JTextField.CENTER);

		JLabel label1= new JLabel("날짜 입력");
		label1.setBounds(250, 20, 80, 20);
		label1.setFont(font1);

		JLabel label2= new JLabel("src 폴더에");
		label2.setBounds(245, 335, 90, 20);
		label2.setFont(font1);
		JLabel label3= new JLabel("저장됩니다.");
		label3.setBounds(240, 355, 100, 20);
		label3.setFont(font1);

		// 설명창1
		field2 = new JTextField();
		field2.setBounds(20, 80, 260, 30);
		field2.setEditable(false);
		field2.setHorizontalAlignment(JTextField.CENTER);
		field2.setText("버튼 사이에 날짜를 먼저 입력하세요.");

		// 설명창2
		field3 = new JTextField();
		field3.setBounds(300, 80, 260, 30);
		field3.setEditable(false);
		field3.setHorizontalAlignment(JTextField.CENTER);
		field3.setText("ex) YYYYMMDD (20200601)");


		// JTable 설정, 생성한 tableModel 과 같이 연결한다.
		JTable dataTable = new JTable(dataModel);
		// 크기 설정 및 한개만 선택 가능하도록 설정
		dataTable.setRowHeight(25);
		dataTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		dataTable.getColumn("랭크").setPreferredWidth(35);
		dataTable.getColumn("영화 이름").setPreferredWidth(200);
		dataTable.getColumn("개봉일").setPreferredWidth(75);
		dataTable.getColumn("관객수").setPreferredWidth(60);
		dataTable.getColumn("누적 관객수").setPreferredWidth(80);
		dataTable.getColumn("누적 매출액").setPreferredWidth(90);

		// scroll 넣기
		JScrollPane scrollPane = new JScrollPane(dataTable);
		scrollPane.setBounds (20, 120, 540, 200);

		// 입력 글자 수 제한
		field1.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke) {
				JTextField src = (JTextField) ke.getSource();
				if(src.getText().length()>=8) 
					ke.consume();
			}
		});

		// 버튼 이벤트 설정
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 인터넷 연결이 필요하기 때문에 연속으로 누르는 것을 방지하기 위해 버튼 활성화를 끔
				button1.setEnabled(false);
				button2.setEnabled(false);
				button3.setEnabled(false);
				button4.setEnabled(false);


				// Thread 로 덮어서, 버튼 멈춘 현상 제거
				// 원래는 handler 와 같은 루틴을 이용하여 main thread 에 message 를 날려 ui 를 refresh 하는 작업을 하는 것이 정석
				// 여기서는 간단히 해결 하기 위해 그냥 전체 영역을 thread 로 덮음. 
				// 안드로이드같은 플랫폼에서는 이렇게 하면 오류가 발생함.
				new Thread() {
					@Override
					public void run() {
						button1.setEnabled(false);
						button2.setEnabled(false);
						button3.setEnabled(false);
						button4.setEnabled(false);

						try 
						{

							dataModel.clearData();

							int date = Integer.parseInt(field1.getText());

							if(field1.getText().length() == 8)
							{
								// 웹 데이터 및 데이터 설정하는 함수를 불러온다.
								loadWebData("Daily", date, "");
							}
							else
							{
								field2.setText("입력 오류!");
								field3.setText("8자리 숫자로 입력해주세요!");
								code = null; // 코드가 있을 수 있고 저장버튼 방지를 위해 null값을 넣음
							}
						}
						catch (NumberFormatException e1) 
						{
							field2.setText("입력 오류!");
							field3.setText("8자리 숫자로만 입력해주세요!!");
							code = null; // 코드가 있을 수 있고 저장버튼 방지를 위해 null값을 넣음
						}


						// 모든 동작이 끝난 뒤에 버튼을 다시 활성화 시켜줌.
						button1.setEnabled(true);
						button2.setEnabled(true);
						button3.setEnabled(true);
						button4.setEnabled(true);
					}
				}.start();
			}
		});

		// 버튼 이벤트 설정
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 인터넷 연결이 필요하기 때문에 연속으로 누르는 것을 방지하기 위해 버튼 활성화를 끔
				button1.setEnabled(false);
				button2.setEnabled(false);
				button3.setEnabled(false);
				button4.setEnabled(false);


				// Thread 로 덮어서, 버튼 멈춘 현상 제거
				// 원래는 handler 와 같은 루틴을 이용하여 main thread 에 message 를 날려 ui 를 refresh 하는 작업을 하는 것이 정석
				// 여기서는 간단히 해결 하기 위해 그냥 전체 영역을 thread 로 덮음. 
				// 안드로이드같은 플랫폼에서는 이렇게 하면 오류가 발생함.
				new Thread() {
					@Override
					public void run() {
						button1.setEnabled(false);
						button2.setEnabled(false);
						button3.setEnabled(false);
						button4.setEnabled(false);
						try 
						{
							dataModel.clearData();

							int date = Integer.parseInt(field1.getText());

							if(field1.getText().length() == 8)
							{
								// 웹 데이터 및 데이터 설정하는 함수를 불러온다.
								loadWebData("Weekly", date, "&weekGb=0");
							}
							else
							{
								field2.setText("입력 오류!");
								field3.setText("8자리 숫자로 입력해주세요!");
								code = null; // 코드가 있을 수 있고 저장버튼 방지를 위해 null값을 넣음
							}
						}
						catch (NumberFormatException e1) 
						{
							field2.setText("입력 오류!");
							field3.setText("8자리 숫자로만 입력해주세요!!");
							code = null; // 코드가 있을 수 있고 저장버튼 방지를 위해 null값을 넣음
						}


						// 모든 동작이 끝난 뒤에 버튼을 다시 활성화 시켜줌.
						button1.setEnabled(true);
						button2.setEnabled(true);
						button3.setEnabled(true);
						button4.setEnabled(true);
					}
				}.start();
			}
		});

		// 버튼 이벤트 설정
		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					BufferedWriter("xml_"+name, day, code);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		// 버튼 이벤트 설정
		button4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					BufferedWriter(name, day, line);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		// 버튼 및 리스트를 UI 에 넣기
		add(button1);
		add(button2);
		add(button3);
		add(button4);
		add(scrollPane);
		add(field1);
		add(label1);
		add(field2);
		add(field3);
		add(label2);
		add(label3);
	}

	// UI 띄우는 함수
	public void showUI() {
		setVisible(true);
	}

	public void BufferedWriter(String name, String day, String file) throws Exception
	{
		int i =JOptionPane.showConfirmDialog(null,"저장했었던 정보가 삭제될 수 있습니다.\r\n저장하시겠습니까?"
				, "경고",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
		
		if(i==0)
		{
			if(code != null)
			{
				//코드 원본 저장
				field2.setText("코드 원본을 저장합니다.");
				FileWriter fw = new FileWriter("src\\" + name + "_" + day + ".txt");
				fw.write(file);
				fw.close();
				field3.setText(name + "_" + day + ".txt");
			}
			else
			{
				field2.setText("코드가 비어있습니다.");
				field3.setText("영화 정보를 먼저 불러와주세요.");
			}
		}
	}

	private void loadWebData(String type, int date, String week) {

		try
		{
			final String API_KEY = "본인 API";

			String url = String.format("http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/search%s"
					+ "BoxOfficeList.xml?key=%s&targetDt=%d%s", type, API_KEY, date, week);

			code = HttpModule.requestGet(url);

			// string으로 받아온 xml 코드를 Document로 변환
			// requestXmlGet()을 사용하면 편하지만, 그러면 해당 원본 코드를 저장하려면 사이트를 2번 접속해야되므로
			// 원본 코드를 받아서 변환하는 방법을 사용함.
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document res = builder.parse(new InputSource(new StringReader(code)));


			NodeList boxofficeType = res.getElementsByTagName("boxofficeType");
			NodeList showRange = res.getElementsByTagName("showRange");
			name = boxofficeType.item(0).getFirstChild().getNodeValue();
			day = showRange.item(0).getFirstChild().getNodeValue();
			field2.setText(name);
			field3.setText(day);


			NodeList ranks = res.getElementsByTagName("rank");
			NodeList movieNms = res.getElementsByTagName("movieNm");
			NodeList openDts = res.getElementsByTagName("openDt");
			NodeList audiCnts = res.getElementsByTagName("audiCnt");
			NodeList audiAccs = res.getElementsByTagName("audiAcc");
			NodeList salesAccs = res.getElementsByTagName("salesAcc");

			if(ranks.item(0)==null)
			{
				field2.setText("해당 날짜의 정보가 없습니다.");
				field3.setText("숫자 8자리를 입력해주세요!!");
				code = null;
			}

			line = "랭크, 영화 이름, 개봉일, 관객수, 누적 관객수, 누적 매출액\r\n";
			
			for(int i=0; i<ranks.getLength(); ++i) {
				// 여기서 Node 는 org.w3c.dom.Node
				Node rank = ranks.item(i);
				Node movieNm = movieNms.item(i);
				Node openDt = openDts.item(i);
				Node audiCnt = audiCnts.item(i);
				Node audiAcc = audiAccs.item(i);
				Node salesAcc = salesAccs.item(i);

				// 각 데이터를 이용하여 Data 클래스를 생성한다.
				Data data = new Data(
						rank.getFirstChild().getNodeValue(),
						movieNm.getFirstChild().getNodeValue(),
						openDt.getFirstChild().getNodeValue(), 
						audiCnt.getFirstChild().getNodeValue(),
						audiAcc.getFirstChild().getNodeValue(),
						salesAcc.getFirstChild().getNodeValue());
				// dataModel 에 넣어준다.
				dataModel.addData(data);

				line = line + String.format("%s	%s, %s, %s명, 누적 %s명, 누적 %s원\r\n",
						rank.getFirstChild().getNodeValue(),
						movieNm.getFirstChild().getNodeValue(),
						openDt.getFirstChild().getNodeValue(), 
						audiCnt.getFirstChild().getNodeValue(),
						audiAcc.getFirstChild().getNodeValue(),
						salesAcc.getFirstChild().getNodeValue());
			}

		}
		catch (Exception e) {
			field2.setText("해당 날짜의 정보가 없습니다.");
			field3.setText("숫자 8자리를 입력해주세요!!");
			code = null;
		}
	}
}
