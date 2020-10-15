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

// UI �� �����ϱ� ���� JFrame �� ��ӹ޴� Ŭ������ �����.
public class MainUI extends JFrame {

	// ������ ������ ������ URL ��� ����, ���⼭�� ����ϴ� ������ private ������ �Ͽ���.
	private static final String TARGET_URL = "https://finance.naver.com/api/sise/etfItemList.nhn";
	// ������ ȭ�鿡 �ѷ��� list �� ����� �� ������, �ش� �𵨿� �����͸� ������ ����Ʈ�� ����ȴ�.
	private DefaultListModel<String> stockModel;
	public MainUI() {
		// UI �� ���� ������

		// ������ ����
		setSize(450, 300);
		// ���̾ƿ��� ���� ��ǥ�� �׸� �� �ֵ��� �ϱ� ���� ���̾ƿ� ���� ����
		setLayout(null);
		// Ÿ��Ʋ����
		setTitle("�ֽİ� ����ȭ��");

		// ���� ��ư ������ ���α׷� ���� ����
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// ������ ���� �Ұ�
		setResizable(false);

		// ȭ�� ����� ��ġ ����
		setLocationRelativeTo(null);

		// ��ư ��ġ ����
		JButton buttonRefresh = new JButton("1. ����� ���簡��");
		// 20, 20 ��ġ�� 100, 30 �������� ��ư
		buttonRefresh.setBounds(20, 20, 150, 30);
		// ��ư �̺�Ʈ����
		buttonRefresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ���ϴ� ����Ʈ�� ������ �޾ƿ´�.
				String result = HttpModule.requestGet(TARGET_URL);

				// ���� ������ ������� ������, �ش� ����Ʈ�κ��� �����͸� �޾ƿ� ��.
				if(!result.isEmpty()) {
					try {
						// �ϴ� ���⼭�� JSON �������� ���� �����Ƿ� JSON ��ü�� �����͸� �־ ����Ѵ�.
						// ���Ŀ� JSON ������ �޾ƿ����� �Լ��� ���� ���� ����.

						// ���� ���⼭ ������ ���� ���� Json ������ �ƴ� ����ε�, �̷� ��쿡�� Json �����Ͱ� �ƴ� ������ �����͸� ���� ��쿡 
						// ������ �߻��Ѵ�. ����ó���� �Ͽ� �����޽����� ������ش�.
						JSONObject jsonData = new JSONObject(result);

						// ���� json �����Ϳ� ���� ���θ� �Ǵ��ϴ� ���� �����Ƿ�, �̸� ������ ������ �߻����� üũ�� ���� ���ش�.
						// ����Ʈ�� ��� �� success ��� resultcode ���� ������
						if(jsonData.getString("resultCode").equals("success")) {
							// ������ ���, ETF �����͸� �޾ƿ´�, �ش� �����Ͱ� ����Ʈ�� �Ǿ� �����Ƿ� JSONARRAY �� �޾��ش�.
							// ������ ���ذ� �Ȱ��°�� ũ�� ���� ����Ʈ�� ���� URL ���� �ְ� ������ Ȯ���غ���.
							JSONArray stockList = jsonData.getJSONObject("result").getJSONArray("etfItemList");

							// ����Ʈ�� �ֱ� ���� ����Ʈ�� �ʱ�ȭ ���ش�.
							stockModel.clear();

							// ���� �����͸� �ϳ��� �޾ƿ´�.
							for(int i=0; i<stockList.length(); ++i) {
								// ���� �� �������� .
								JSONObject stockData = (JSONObject)stockList.get(i);

								// ����� ����Ʈ ������ ���� �̸� ���� itemname �̹Ƿ� �̸� �̿��Ͽ� ���� ������ ����Ʈ�� �־��ش�.
								String stockName = stockData.getString("itemname");

								
								int nowVal = stockData.getInt("nowVal");
								// ����Ʈ�� stock �� �־��ش�.
								stockModel.addElement(stockName +", ���簡��: " + nowVal);

							}
						} 
						else {
							// �ش� ����Ʈ ��ü�� ������ �߻��� ������ ��� ������ ������ش�.
							JOptionPane.showMessageDialog(null, "����Ʈ ��ü ������ �߻��Ͽ����ϴ�.");
						}
					} catch(Exception e2) {
						// ������� (�α׿�)
						e2.printStackTrace();

						// ���� �޽��� �ڽ� ���
						JOptionPane.showMessageDialog(null, "���ٸ��� ���� ������ �����͸� �޾ƿͼ� ������ �߻��Ͽ����ϴ�"); 
					}
				}
				else {
					// �����̹Ƿ� ������� �޽��� �ڽ��� �����ش�.
					JOptionPane.showMessageDialog(null, "�����͸� �����µ� �����Ͽ����ϴ�.");
				}
			}
		});



		JButton buttonHomeWork = new JButton("2. ���� ����Ʈ �� ����");
		buttonHomeWork.setBounds(190, 20, 230, 30);

		buttonHomeWork.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//���� ����Ʈ
				String result = HttpModule.requestGet("https://api.binance.com/api/v1/ticker/allPrices");

				if(!result.isEmpty()) {
					try {

						JSONArray jsonData = new JSONArray(result);

						stockModel.clear();

						// ���� �����͸� �ϳ��� �޾ƿ´�.
						for(int i=0; i<jsonData.length(); ++i) {
							// ���� �� �������� .
							JSONObject stockData = (JSONObject)jsonData.get(i);
							//���� �̸��� ����
							String symbol = stockData.getString("symbol");
							String price = stockData.getString("price");

							stockModel.addElement(symbol +" ���簡��: " + price);
						}
					} catch(Exception e2) {
						// ������� (�α׿�)
						e2.printStackTrace();

						// ���� �޽��� �ڽ� ���
						JOptionPane.showMessageDialog(null, "���ٸ��� ���� ������ �����͸� �޾ƿͼ� ������ �߻��Ͽ����ϴ�"); 
					}
				}
				else {
					// �����̹Ƿ� ������� �޽��� �ڽ��� �����ش�.
					JOptionPane.showMessageDialog(null, "�����͸� �����µ� �����Ͽ����ϴ�.");
				}
			}
		});
		add(buttonHomeWork);

		/**
		 * 2. JSON ���˿� ������ �ʿ� ���� �׷� ����Ʈ�� �̿��غ���.
		 * JSON �ڵ� ��ó: https://www.coindesk.com/coindesk-api

			JButton buttonHomeWork = new JButton("2. �������� ����(1BTC ������ ����)");
			buttonHomeWork.setBounds(190, 20, 230, 30);

			buttonHomeWork.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					//���� ����
					String result = HttpModule.requestGet("https://api.coindesk.com/v1/bpi/supported-currencies.json");

					if(!result.isEmpty()) {
						try {

							JSONArray jsonData = new JSONArray(result);

							stockModel.clear();

							stockModel.addElement("��Ʈ���� ����");
							// ���� �����͸� �ϳ��� �޾ƿ´�.
							for(int i=0; i<jsonData.length(); ++i) {
								// ���� �� �������� .
								JSONObject stockData = (JSONObject)jsonData.get(i);
								//��Ʈ���� �� ����
								String money = stockData.getString("currency");

								//�� ������ ��ũ�� �־ ��Ʈ������ ��ȭ�� ���� ���� ����Ʈ��  json ����Ʈ���� �޾ƿ�
								String coinObt = HttpModule.requestGet("https://api.coindesk.com/v1/bpi/currentprice/"+money+".json");

								JSONObject coin = new JSONObject(coinObt); // ���� Json �޾ƿ�
								JSONObject coinUSD = new JSONObject(coin.opt("bpi").toString()); // ���� Json bpi�κ�
								JSONObject coinrate = new JSONObject(coinUSD.opt(money).toString());// Json money �κ�
								String coinRate = coinrate.getString("rate"); // ���� ����
								String country = coinrate.getString("description"); //��ȭ�� ����ϴ� ����
								stockModel.addElement(i+1+". " + country + " ������ 1BTC ����(" + money + "): " + coinRate);
								System.out.println(i+1+". " + country + " ������ 1BTC ����(" + money + "): " + coinRate);
							}
						} catch(Exception e2) {
							// ������� (�α׿�)
							e2.printStackTrace();

							// ���� �޽��� �ڽ� ���
							JOptionPane.showMessageDialog(null, "���ٸ��� ���� ������ �����͸� �޾ƿͼ� ������ �߻��Ͽ����ϴ�"); 
						}
					}
					else {
						// �����̹Ƿ� ������� �޽��� �ڽ��� �����ش�.
						JOptionPane.showMessageDialog(null, "�����͸� �����µ� �����Ͽ����ϴ�.");
					}
				}
			});
			add(buttonHomeWork);
		 */

		// ����Ʈ ��ġ ���� �� �ʱ�ȭ, ��ũ�� ����
		JList<String> stockList = new JList<String>(new DefaultListModel<String>());
		JScrollPane scrollPane = new JScrollPane(stockList);
		scrollPane.setBounds(20, 60, 400, 200);

		// �ش� ����Ʈ�� ����� �� ���� �����Ѵ�.
		stockModel = (DefaultListModel<String>)stockList.getModel();

		// ��ư �� ����Ʈ�� UI �� �ֱ�
		add(buttonRefresh);
		add(scrollPane);

	}

	// UI �����Լ�
	public void showUI() {
		setVisible(true);
	}
}
