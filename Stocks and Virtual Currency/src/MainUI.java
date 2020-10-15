import java.awt.ScrollPane;
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

// UI 를 설정하기 위해 JFrame 을 상속받는 클래스로 만든다.
public class MainUI extends JFrame {

	// 정보를 가져올 데이터 URL 상수 설정, 여기서만 사용하는 변수라 private 설정을 하였다.
	private static final String TARGET_URL = "https://finance.naver.com/api/sise/etfItemList.nhn";
	// 정보를 화면에 뿌려줄 list 에 연결된 모델 데이터, 해당 모델에 데이터를 넣으면 리스트에 저장된다.
	private DefaultListModel<String> stockModel;
	public MainUI() {
		// UI 에 대한 설정들

		// 사이즈 설정
		setSize(450, 300);
		// 레이아웃을 절대 좌표로 그릴 수 있도록 하기 위해 레이아웃 설정 제거
		setLayout(null);
		// 타이틀설정
		setTitle("주식과 가상화폐");

		// 종료 버튼 누르면 프로그램 종료 설정
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// 사이즈 변경 불가
		setResizable(false);

		// 화면 가운데로 위치 설정
		setLocationRelativeTo(null);

		// 버튼 위치 설정
		JButton buttonRefresh = new JButton("1. 종목과 현재가격");
		// 20, 20 위치에 100, 30 사이즈의 버튼
		buttonRefresh.setBounds(20, 20, 150, 30);
		// 버튼 이벤트설정
		buttonRefresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 원하는 사이트의 정보를 받아온다.
				String result = HttpModule.requestGet(TARGET_URL);

				// 받은 정보가 비어있지 않으면, 해당 사이트로부터 데이터를 받아온 것.
				if(!result.isEmpty()) {
					try {
						// 일단 여기서는 JSON 데이터인 것이 맞으므로 JSON 객체에 데이터를 넣어서 사용한다.
						// 추후에 JSON 값으로 받아와지는 함수를 따로 만들 예정.

						// 만약 여기서 오류가 나는 경우는 Json 포맷이 아닌 경우인데, 이런 경우에는 Json 데이터가 아닌 곳에서 데이터를 얻어온 경우에 
						// 에러가 발생한다. 예외처리만 하여 에러메시지를 출력해준다.
						JSONObject jsonData = new JSONObject(result);

						// 얻어온 json 데이터에 성공 여부를 판단하는 값이 있으므로, 이를 가지고 오류가 발생한지 체크를 먼저 해준다.
						// 사이트의 결과 상 success 라고 resultcode 값에 있으면
						if(jsonData.getString("resultCode").equals("success")) {
							// 성공한 경우, ETF 데이터를 받아온다, 해당 데이터가 리스트로 되어 있으므로 JSONARRAY 로 받아준다.
							// 구조가 이해가 안가는경우 크롬 같은 사이트에 위의 URL 값을 넣고 구조를 확인해보자.
							JSONArray stockList = jsonData.getJSONObject("result").getJSONArray("etfItemList");

							// 리스트에 넣기 전에 리스트를 초기화 해준다.
							stockModel.clear();

							// 얻어온 데이터를 하나씩 받아온다.
							for(int i=0; i<stockList.length(); ++i) {
								// 단일 값 가져오기 .
								JSONObject stockData = (JSONObject)stockList.get(i);

								// 얻어진 리스트 값에서 종목 이름 값은 itemname 이므로 이를 이용하여 종목 값들을 리스트에 넣어준다.
								String stockName = stockData.getString("itemname");

								
								int nowVal = stockData.getInt("nowVal");
								// 리스트에 stock 을 넣어준다.
								stockModel.addElement(stockName +", 현재가격: " + nowVal);

							}
						} 
						else {
							// 해당 사이트 자체의 오류로 발생한 에러인 경우 오류를 출력해준다.
							JOptionPane.showMessageDialog(null, "사이트 자체 오류가 발생하였습니다.");
						}
					} catch(Exception e2) {
						// 오류출력 (로그용)
						e2.printStackTrace();

						// 오류 메시지 박스 출력
						JOptionPane.showMessageDialog(null, "을바르지 않은 포맷의 데이터를 받아와서 오류가 발생하였습니다"); 
					}
				}
				else {
					// 오류이므로 오류라고 메시지 박스를 보여준다.
					JOptionPane.showMessageDialog(null, "데이터를 얻어오는데 실패하였습니다.");
				}
			}
		});



		JButton buttonHomeWork = new JButton("2. 코인 리스트 및 가격");
		buttonHomeWork.setBounds(190, 20, 230, 30);

		buttonHomeWork.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//코인 리스트
				String result = HttpModule.requestGet("https://api.binance.com/api/v1/ticker/allPrices");

				if(!result.isEmpty()) {
					try {

						JSONArray jsonData = new JSONArray(result);

						stockModel.clear();

						// 얻어온 데이터를 하나씩 받아온다.
						for(int i=0; i<jsonData.length(); ++i) {
							// 단일 값 가져오기 .
							JSONObject stockData = (JSONObject)jsonData.get(i);
							//코인 이름과 가격
							String symbol = stockData.getString("symbol");
							String price = stockData.getString("price");

							stockModel.addElement(symbol +" 현재가격: " + price);
						}
					} catch(Exception e2) {
						// 오류출력 (로그용)
						e2.printStackTrace();

						// 오류 메시지 박스 출력
						JOptionPane.showMessageDialog(null, "을바르지 않은 포맷의 데이터를 받아와서 오류가 발생하였습니다"); 
					}
				}
				else {
					// 오류이므로 오류라고 메시지 박스를 보여준다.
					JOptionPane.showMessageDialog(null, "데이터를 얻어오는데 실패하였습니다.");
				}
			}
		});
		add(buttonHomeWork);

		/**
		 * 2. JSON 포맷에 인증이 필요 없는 그런 사이트를 이용해보자.
		 * JSON 코드 출처: https://www.coindesk.com/coindesk-api

			JButton buttonHomeWork = new JButton("2. 예제변경 과제(1BTC 각국가 가격)");
			buttonHomeWork.setBounds(190, 20, 230, 30);

			buttonHomeWork.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					//코인 가격
					String result = HttpModule.requestGet("https://api.coindesk.com/v1/bpi/supported-currencies.json");

					if(!result.isEmpty()) {
						try {

							JSONArray jsonData = new JSONArray(result);

							stockModel.clear();

							stockModel.addElement("비트코인 가격");
							// 얻어온 데이터를 하나씩 받아온다.
							for(int i=0; i<jsonData.length(); ++i) {
								// 단일 값 가져오기 .
								JSONObject stockData = (JSONObject)jsonData.get(i);
								//비트코인 돈 단위
								String money = stockData.getString("currency");

								//돈 단위를 링크에 넣어서 비트코인의 통화에 따른 가격 리스트를  json 사이트에서 받아옴
								String coinObt = HttpModule.requestGet("https://api.coindesk.com/v1/bpi/currentprice/"+money+".json");

								JSONObject coin = new JSONObject(coinObt); // 코인 Json 받아옴
								JSONObject coinUSD = new JSONObject(coin.opt("bpi").toString()); // 코인 Json bpi부분
								JSONObject coinrate = new JSONObject(coinUSD.opt(money).toString());// Json money 부분
								String coinRate = coinrate.getString("rate"); // 코인 가격
								String country = coinrate.getString("description"); //통화를 사용하는 국가
								stockModel.addElement(i+1+". " + country + " 국가의 1BTC 가격(" + money + "): " + coinRate);
								System.out.println(i+1+". " + country + " 국가의 1BTC 가격(" + money + "): " + coinRate);
							}
						} catch(Exception e2) {
							// 오류출력 (로그용)
							e2.printStackTrace();

							// 오류 메시지 박스 출력
							JOptionPane.showMessageDialog(null, "을바르지 않은 포맷의 데이터를 받아와서 오류가 발생하였습니다"); 
						}
					}
					else {
						// 오류이므로 오류라고 메시지 박스를 보여준다.
						JOptionPane.showMessageDialog(null, "데이터를 얻어오는데 실패하였습니다.");
					}
				}
			});
			add(buttonHomeWork);
		 */

		// 리스트 위치 설정 및 초기화, 스크롤 설정
		JList<String> stockList = new JList<String>(new DefaultListModel<String>());
		JScrollPane scrollPane = new JScrollPane(stockList);
		scrollPane.setBounds(20, 60, 400, 200);

		// 해당 리스트와 연결된 모델 값을 저장한다.
		stockModel = (DefaultListModel<String>)stockList.getModel();

		// 버튼 및 리스트를 UI 에 넣기
		add(buttonRefresh);
		add(scrollPane);

	}

	// UI 띄우는함수
	public void showUI() {
		setVisible(true);
	}
}
