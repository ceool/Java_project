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

// UI �� �����ϱ� ����  JFrame �� ��ӹ޴� Ŭ������ �����.
public class MainUI extends JFrame {

	// ������ ������ ������ URL ��� ����, ���⼭�� ����ϴ� ������ private ������ �Ͽ���.
	private static final String TARGET_URL = "https://finance.naver.com/api/sise/etfItemList.nhn";

	// ������ ȭ�鿡 �ѷ��� list �� ����� �� ������, �ش� �𵨿� �����͸� ������ ����Ʈ�� ����ȴ�.
	private DefaultListModel<String> dataModel;

	public MainUI() {
		// UI �� ���� ������

		// ������ ����
		setSize(460, 300);
		// ���̾ƿ��� ���� ��ǥ�� �׸� �� �ֵ��� �ϱ� ���� ���̾ƿ� ���� ����
		setLayout(null);
		// Ÿ��Ʋ ����
		setTitle("�ڷγ� ����");

		// ���� ��ư ������ ���α׷� ���� ����
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// ������ ���� �Ұ�
		setResizable(false);

		// ȭ�� ����� ��ġ ����
		setLocationRelativeTo(null);

		// ��ư ��ġ ����
		JButton buttonRefresh = new JButton("Refresh");
		// 20, 20 ��ġ�� 100, 50 �������� ��ư
		buttonRefresh.setBounds(20, 20, 100, 30);
		// ��ư �̺�Ʈ ����
		buttonRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ���ͳ� ������ �ʿ��ϱ� ������ �������� ������ ���� �����ϱ� ���� ��ư Ȱ��ȭ�� ��
				buttonRefresh.setEnabled(false);
				
				// Thread �� ���, ��ư ���� ���� ����
				// ������ handler �� ���� ��ƾ�� �̿��Ͽ� main thread �� message �� ���� ui �� refresh �ϴ� �۾��� �ϴ� ���� ����
				// ���⼭�� ������ �ذ� �ϱ� ���� �׳� ��ü ������ thread �� ����.
				// �ȵ���̵尰�� �÷��������� �̷��� �ϸ� ������ �߻���.
				new Thread() {
					@Override
					public void run() {
						// ���� ������ ����
						dataModel.clear();
						// �ڷγ� �ּҸ� ��û�Ѵ�.
						final String API_KEY = "���� API"; 
						// �������� �ݵ�� �Է��� url parameter�� �˻��Ⱓ�� ���� �� ����.
						final String startDate = "20200310";
						final String endDate = "20200509";

						// ������ page ������ �ѹ��� ������ ������ �����ؼ� ����ϰ�, �ʿ�� ���� ����.
						String url = String.format("http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson"
								+ "?serviceKey=%s&pageNo=l&numOfRows=10&startCreateDt=%s&endCreateDt=%s", API_KEY, startDate, endDate);
						// HttpModule �� �ۼ����Լ��� xml �����͸�������.
						// ���⼭ ������� Document Ŭ������ import �� org.w3c.dom.Docunient ������.
						// Document �� ���� ���Ǵ� Ŭ���� ���̶� �̻��Ѱ� import �ϴ� �Ǽ��� ���� �� ������, �׷� ��� ������ �������� ������ �ٽ� �߰� �ʿ�.
						Document res = HttpModule.requestXmlGet(url);

						// ���� ������� opt ���� xml ������ ������ � key ���� ���� item �� �������� ������ �ʿ�.
						// ���⼭�� item Ű ���� �������� ��� ������ ���� �� ������, ������ item key ���� �ش��ϴ� �͵��� �޾ƿ��� ������ ��.
						// ���⼭ Node �� org.w3c.dom.NodeList
						// ���� �ڷγ� �����Ϳ� ���� item ���� Ű �߿� ����� Ű �� ����Ʈ�� �޴´�.
						// clearCnt, createDt, deathCnt, decideCnt, examCnt, seq

						// �� Ű ���� ���� ����Ʈ�� �޾ƿ´�.
						// �� Ű ���� ���� ���� �ش� API ������ � ������ ���� ������ ����.
						NodeList accExamCnts = res.getElementsByTagName("accExamCnt");
						NodeList seqs = res.getElementsByTagName("seq");
						NodeList createDts = res.getElementsByTagName("createDt");
						NodeList examCnts = res.getElementsByTagName("examCnt");
						NodeList decideCnts = res.getElementsByTagName("decideCnt");
						NodeList deathCnts = res.getElementsByTagName("deathCnt");

						// head �κ��� �־��ֱ� ���� �κ�
						// ������ space ���� �ؾ� ���� .
						dataModel.addElement(" ����                         �ð�                         �˻��          Ȯ���ڼ�        ����ڼ�");
 
						// �� Ű�� ���� ��� ���̴� ������ �ƹ��ų� length �� ����ϰ� ���� nodevalue �� ����Ѵ�.
						// ��� �ٸ� Ű�� ���̰� �ٸ���, �̸� �����ؼ� �ٸ� api���� �Ȱ��� ����� �Ұ��� �� ����.
						for(int i=0; i<accExamCnts.getLength(); ++i) {
							// ���⼭ Node �� org.w3c.doni.Node
							Node seq = seqs.item(i);
							Node createDt = createDts.item(i);
							Node examCnt = examCnts.item(i);
							Node decideCnt = decideCnts.item(i);
							Node deathCnt = deathCnts.item(i);

							// �� list �� �ش��ϴ� �� �������� ù��° ������ ���ϴ� �������� �����Ѵ�.
							// ��¥ ���� �ڿ� ms ������ �����Ѵ�.
							String line = String.format(" %s            %s         %s             %s             %s",
									seq.getFirstChild().getNodeValue(),
									createDt.getFirstChild().getNodeValue().substring(0, createDt.getFirstChild().getNodeValue().length()-3),
									examCnt.getFirstChild().getNodeValue(), 
									decideCnt.getFirstChild().getNodeValue(),
									deathCnt.getFirstChild().getNodeValue());

							// list �� �־��ش�.
							dataModel.addElement(line);
							// �ּ����� delay �� �ִ� ���� �ʿ�, main ui �� sync �� �ȸ¾Ƽ� ������ ���ϴ� ��찡 ����.
							// ���� ������ζ�� main thread ���� UI �� �ǵ鿩 �ִ� ���� ����������, ���������Ƿ� �����ϰ� �ذ�.
							try {
								Thread.sleep(1);
							}catch(InterruptedException e) {
							}
						}
						// ��� ������ ���� �ڿ� ��ư�� �ٽ� Ȱ��ȭ ������.
						buttonRefresh.setEnabled(true);
					}
				}.start();
			}
		});



		// ����Ʈ ��ġ ���� �� �ʱ�ȭ, ��ũ�� ����
		JList<String> dataList = new JList<String>(new DefaultListModel<String>());
		JScrollPane scrollPane = new JScrollPane(dataList);
		scrollPane.setBounds(20, 60, 410, 200);

		// �ش� ����Ʈ�� ����� �� ���� �����Ѵ�.
		dataModel = (DefaultListModel<String>)dataList.getModel();

		// ��ư �� ����Ʈ�� UI �� �ֱ�
		add(buttonRefresh);
		add(scrollPane);
	}

	// UI ���� �Լ�
	public void showUI() {
		setVisible(true);
	}
}
