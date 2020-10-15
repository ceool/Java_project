import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

// UI �� �����ϱ� ����  JFrame �� ��ӹ޴� Ŭ������ �����.
public class MainUI extends JFrame {

	// ������ �ѷ��� DataTableModel ����
	private DataTableModel dataModel = new DataTableModel();

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
						// [PPT ���� �����ϴ� �κ�] ��ư ��Ȱ��ȭ, �� �Լ� �ҷ����� �κ�, ��ư Ȱ��ȭ
				
										buttonRefresh.setEnabled(false);
										// �� ������ �� ������ �����ϴ� �Լ��� �ҷ��´�.
										loadWebData();
										// ��� ������ ���� �ڿ� ��ư�� �ٽ� Ȱ��ȭ ������.
										buttonRefresh.setEnabled(true);
					}
				}.start();
			}
		});

		// JTable ����, ������ tableModel �� ���� �����Ѵ�.
		JTable dataTable = new JTable(dataModel);
		// ũ�� ���� �� �Ѱ��� ���� �����ϵ��� ����
		dataTable.setRowHeight(25);
		dataTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// scroll �ֱ�
		JScrollPane scrollPane = new JScrollPane(dataTable);
		scrollPane.setBounds (20, 60, 410, 200);

		// ��ư �� ����Ʈ�� UI �� �ֱ�
		add(buttonRefresh);
		add(scrollPane);
	}

	// UI ���� �Լ�
	public void showUI() {
		setVisible(true);
	}

	// ������ �ڷγ� �����͸� �ҷ����� �Լ�, ���� ���� �����Ѵ�.
	private void loadWebData() {
		// �ڷγ� api �ּҸ� ��û�Ѵ�.
		// ���� api�� ���
		final String API_KEY = "0%2BUirk94OGKR5kIwDVIefKzRGUm%2B5Xu%2Bw523JAVD4BMh1h9VFPU8AkXDjHSXoBCxoq9aoislKFCQxIUlhznUFg%3D%3D";
		// �ݵ�� �Է��� url parameter �� �˻� �Ⱓ�� ���� �� ����.
		final String startDate = "20200310";
		final String endDate = "20200509";
		// ������ page ������ �ѹ��� ������ ������ �����ؼ� ����ϰ�, �ʿ�� ���� ����.
		String url = String.format("http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson"
				+ "?serviceKey=%s&pageNo=l&numOfRows=10&startCreateDt=%s&endCreateDt=%s", API_KEY, startDate, endDate);
		// HttpModule �� �ۼ����Լ��� xml �����͸�������.
		// ���⼭ ������� Document Ŭ������ import �� org.w3c.dom.Docunient ������.
		// Document �� ���� ���Ǵ� Ŭ���� ���̶� �̻��Ѱ� import �ϴ� �Ǽ��� ���� �� ������, �׷� ��� ������ �������� ������ �ٽ� �߰� �ʿ�.
		Document res = HttpModule.requestXmlGet(url);
		System.out.println(url);
		// ���� ������� opt ���� xml ������ ������ � key ���� ���� item �� �������� ������ �ʿ�.
		// ���⼭�� item Ű ���� �������� ��� ������ ���� �� ������, ������ item key ���� �ش��ϴ� �͵��� �޾ƿ��� ������ ��.
		// �ٸ� API ������ �̷� �κ��� �� �ٸ���, json ���� �� ��쿡�� key ���� �� �ٸ��� ������ ������ �ʿ�.
		// ���⼭ Node �� org.w3c.dom.NodeList
		
		// ���� �ڷγ� �����Ϳ� ���� item ���� Ű �߿� ����� Ű �� ����Ʈ�� �޴´�.
		// clearCnt, createDt, deathCnt, decideCnt, examCnt, seq

		// �� Ű ���� ���� ����Ʈ�� �޾ƿ´�.
		// �� Ű ���� ���� ���� �ش� API ������ � ������ ���� ������ ����.
		NodeList seqs = res.getElementsByTagName("seq");
		NodeList createDts = res.getElementsByTagName("createDt");
		NodeList examCnts = res.getElementsByTagName("examCnt");
		NodeList decideCnts = res.getElementsByTagName("decideCnt");
		NodeList deathCnts = res.getElementsByTagName("deathCnt");

		// �� Ű�� ���� ��� ���̴� ������ �ƹ��ų� length�� ����ϰ� ���� nodevalue �� ����Ѵ�.
		// ��� �ٸ� Ű�� ���̰� �ٸ���, �̸� �����ؼ� �ٸ� #1 ���� �Ȱ��� ����� �Ұ��� �� ����.
		for(int i=0; i<seqs.getLength(); ++i) {
			// ���⼭ Node �� org.w3c.dom.Node
			Node seq = seqs.item(i);
			Node createDt = createDts.item(i);
			Node examCnt = examCnts.item(i);
			Node decideCnt = decideCnts.item(i);
			Node deathCnt = deathCnts.item(i);
			// �� �����͸� �̿��Ͽ� Data Ŭ������ �����Ѵ�.
			Data data = new Data(seq.getFirstChild().getNodeValue(),
					createDt.getFirstChild().getNodeValue().substring(0, createDt.getFirstChild().getNodeValue().length()-3),
					examCnt.getFirstChild().getNodeValue(), 
					decideCnt.getFirstChild().getNodeValue(),
					deathCnt.getFirstChild().getNodeValue());
			// dataModel �� �־��ش�.
			dataModel.addData(data);
		}
	}
}
