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

// UI �� �����ϱ� ����  JFrame �� ��ӹ޴� Ŭ������ �����.
public class MainUI extends JFrame {

	//���� ������ ���� �����
	private String name=null; // �Ϻ� �ڽ����ǽ� or �ְ� �ڽ����ǽ�
	private String code=null; // �ҷ��� xml �ڵ带 ���Ϸ� �����ϱ� ���� ����
	private String line=null; // �ҷ��� xml �ڵ带 �������� gui �������� ���Ϸ� �����ϱ����� ����
	private String day=null; // ��¥ �Է��� ����� �� ����� ����
	
	
	// ������ �ѷ��� DataTableModel ����
	private DataTableModel dataModel = new DataTableModel();

	//���� ���� �͵� ����
	private JTextField field2;
	private JTextField field3;

	public MainUI() {
		Font font1 = new Font("Dialog",Font.BOLD, 18);

		setSize(600, 430);
		// ���̾ƿ��� ���� ��ǥ�� �׸� �� �ֵ��� �ϱ� ���� ���̾ƿ� ���� ����
		setLayout(null);
		// Ÿ��Ʋ ����
		setTitle("�ڽ����ǽ�TOP10");

		// ���� ��ư ������ ���α׷� ���� ����
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// ������ ���� �Ұ�
		setResizable(false);

		// ȭ�� ����� ��ġ ����
		setLocationRelativeTo(null);

		// ��ư ��ġ ����
		JButton button1 = new JButton("���� �ڽ����ǽ� TOP10 ���");
		button1.setBounds(20, 20, 200, 50);
		JButton button2 = new JButton("�ְ� �ڽ����ǽ� TOP10 ���");
		button2.setBounds(360, 20, 200, 50);
		JButton button3 = new JButton("�ҷ��� �ڵ� ���� ����");
		button3.setBounds(20, 330, 200, 50);
		JButton button4 = new JButton("������ ��ȭ ���� ����");
		button4.setBounds(360, 330, 200, 50);

		// ��¥ �Է� ����
		JTextField field1 = new JTextField();
		field1.setBounds(230, 40, 120, 30);
		field1.setFont(font1);
		field1.setEditable(true);
		field1.setHorizontalAlignment(JTextField.CENTER);

		JLabel label1= new JLabel("��¥ �Է�");
		label1.setBounds(250, 20, 80, 20);
		label1.setFont(font1);

		JLabel label2= new JLabel("src ������");
		label2.setBounds(245, 335, 90, 20);
		label2.setFont(font1);
		JLabel label3= new JLabel("����˴ϴ�.");
		label3.setBounds(240, 355, 100, 20);
		label3.setFont(font1);

		// ����â1
		field2 = new JTextField();
		field2.setBounds(20, 80, 260, 30);
		field2.setEditable(false);
		field2.setHorizontalAlignment(JTextField.CENTER);
		field2.setText("��ư ���̿� ��¥�� ���� �Է��ϼ���.");

		// ����â2
		field3 = new JTextField();
		field3.setBounds(300, 80, 260, 30);
		field3.setEditable(false);
		field3.setHorizontalAlignment(JTextField.CENTER);
		field3.setText("ex) YYYYMMDD (20200601)");


		// JTable ����, ������ tableModel �� ���� �����Ѵ�.
		JTable dataTable = new JTable(dataModel);
		// ũ�� ���� �� �Ѱ��� ���� �����ϵ��� ����
		dataTable.setRowHeight(25);
		dataTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		dataTable.getColumn("��ũ").setPreferredWidth(35);
		dataTable.getColumn("��ȭ �̸�").setPreferredWidth(200);
		dataTable.getColumn("������").setPreferredWidth(75);
		dataTable.getColumn("������").setPreferredWidth(60);
		dataTable.getColumn("���� ������").setPreferredWidth(80);
		dataTable.getColumn("���� �����").setPreferredWidth(90);

		// scroll �ֱ�
		JScrollPane scrollPane = new JScrollPane(dataTable);
		scrollPane.setBounds (20, 120, 540, 200);

		// �Է� ���� �� ����
		field1.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke) {
				JTextField src = (JTextField) ke.getSource();
				if(src.getText().length()>=8) 
					ke.consume();
			}
		});

		// ��ư �̺�Ʈ ����
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ���ͳ� ������ �ʿ��ϱ� ������ �������� ������ ���� �����ϱ� ���� ��ư Ȱ��ȭ�� ��
				button1.setEnabled(false);
				button2.setEnabled(false);
				button3.setEnabled(false);
				button4.setEnabled(false);


				// Thread �� ���, ��ư ���� ���� ����
				// ������ handler �� ���� ��ƾ�� �̿��Ͽ� main thread �� message �� ���� ui �� refresh �ϴ� �۾��� �ϴ� ���� ����
				// ���⼭�� ������ �ذ� �ϱ� ���� �׳� ��ü ������ thread �� ����. 
				// �ȵ���̵尰�� �÷��������� �̷��� �ϸ� ������ �߻���.
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
								// �� ������ �� ������ �����ϴ� �Լ��� �ҷ��´�.
								loadWebData("Daily", date, "");
							}
							else
							{
								field2.setText("�Է� ����!");
								field3.setText("8�ڸ� ���ڷ� �Է����ּ���!");
								code = null; // �ڵ尡 ���� �� �ְ� �����ư ������ ���� null���� ����
							}
						}
						catch (NumberFormatException e1) 
						{
							field2.setText("�Է� ����!");
							field3.setText("8�ڸ� ���ڷθ� �Է����ּ���!!");
							code = null; // �ڵ尡 ���� �� �ְ� �����ư ������ ���� null���� ����
						}


						// ��� ������ ���� �ڿ� ��ư�� �ٽ� Ȱ��ȭ ������.
						button1.setEnabled(true);
						button2.setEnabled(true);
						button3.setEnabled(true);
						button4.setEnabled(true);
					}
				}.start();
			}
		});

		// ��ư �̺�Ʈ ����
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ���ͳ� ������ �ʿ��ϱ� ������ �������� ������ ���� �����ϱ� ���� ��ư Ȱ��ȭ�� ��
				button1.setEnabled(false);
				button2.setEnabled(false);
				button3.setEnabled(false);
				button4.setEnabled(false);


				// Thread �� ���, ��ư ���� ���� ����
				// ������ handler �� ���� ��ƾ�� �̿��Ͽ� main thread �� message �� ���� ui �� refresh �ϴ� �۾��� �ϴ� ���� ����
				// ���⼭�� ������ �ذ� �ϱ� ���� �׳� ��ü ������ thread �� ����. 
				// �ȵ���̵尰�� �÷��������� �̷��� �ϸ� ������ �߻���.
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
								// �� ������ �� ������ �����ϴ� �Լ��� �ҷ��´�.
								loadWebData("Weekly", date, "&weekGb=0");
							}
							else
							{
								field2.setText("�Է� ����!");
								field3.setText("8�ڸ� ���ڷ� �Է����ּ���!");
								code = null; // �ڵ尡 ���� �� �ְ� �����ư ������ ���� null���� ����
							}
						}
						catch (NumberFormatException e1) 
						{
							field2.setText("�Է� ����!");
							field3.setText("8�ڸ� ���ڷθ� �Է����ּ���!!");
							code = null; // �ڵ尡 ���� �� �ְ� �����ư ������ ���� null���� ����
						}


						// ��� ������ ���� �ڿ� ��ư�� �ٽ� Ȱ��ȭ ������.
						button1.setEnabled(true);
						button2.setEnabled(true);
						button3.setEnabled(true);
						button4.setEnabled(true);
					}
				}.start();
			}
		});

		// ��ư �̺�Ʈ ����
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

		// ��ư �̺�Ʈ ����
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
		// ��ư �� ����Ʈ�� UI �� �ֱ�
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

	// UI ���� �Լ�
	public void showUI() {
		setVisible(true);
	}

	public void BufferedWriter(String name, String day, String file) throws Exception
	{
		int i =JOptionPane.showConfirmDialog(null,"�����߾��� ������ ������ �� �ֽ��ϴ�.\r\n�����Ͻðڽ��ϱ�?"
				, "���",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
		
		if(i==0)
		{
			if(code != null)
			{
				//�ڵ� ���� ����
				field2.setText("�ڵ� ������ �����մϴ�.");
				FileWriter fw = new FileWriter("src\\" + name + "_" + day + ".txt");
				fw.write(file);
				fw.close();
				field3.setText(name + "_" + day + ".txt");
			}
			else
			{
				field2.setText("�ڵ尡 ����ֽ��ϴ�.");
				field3.setText("��ȭ ������ ���� �ҷ����ּ���.");
			}
		}
	}

	private void loadWebData(String type, int date, String week) {

		try
		{
			final String API_KEY = "���� API";

			String url = String.format("http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/search%s"
					+ "BoxOfficeList.xml?key=%s&targetDt=%d%s", type, API_KEY, date, week);

			code = HttpModule.requestGet(url);

			// string���� �޾ƿ� xml �ڵ带 Document�� ��ȯ
			// requestXmlGet()�� ����ϸ� ��������, �׷��� �ش� ���� �ڵ带 �����Ϸ��� ����Ʈ�� 2�� �����ؾߵǹǷ�
			// ���� �ڵ带 �޾Ƽ� ��ȯ�ϴ� ����� �����.
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
				field2.setText("�ش� ��¥�� ������ �����ϴ�.");
				field3.setText("���� 8�ڸ��� �Է����ּ���!!");
				code = null;
			}

			line = "��ũ, ��ȭ �̸�, ������, ������, ���� ������, ���� �����\r\n";
			
			for(int i=0; i<ranks.getLength(); ++i) {
				// ���⼭ Node �� org.w3c.dom.Node
				Node rank = ranks.item(i);
				Node movieNm = movieNms.item(i);
				Node openDt = openDts.item(i);
				Node audiCnt = audiCnts.item(i);
				Node audiAcc = audiAccs.item(i);
				Node salesAcc = salesAccs.item(i);

				// �� �����͸� �̿��Ͽ� Data Ŭ������ �����Ѵ�.
				Data data = new Data(
						rank.getFirstChild().getNodeValue(),
						movieNm.getFirstChild().getNodeValue(),
						openDt.getFirstChild().getNodeValue(), 
						audiCnt.getFirstChild().getNodeValue(),
						audiAcc.getFirstChild().getNodeValue(),
						salesAcc.getFirstChild().getNodeValue());
				// dataModel �� �־��ش�.
				dataModel.addData(data);

				line = line + String.format("%s	%s, %s, %s��, ���� %s��, ���� %s��\r\n",
						rank.getFirstChild().getNodeValue(),
						movieNm.getFirstChild().getNodeValue(),
						openDt.getFirstChild().getNodeValue(), 
						audiCnt.getFirstChild().getNodeValue(),
						audiAcc.getFirstChild().getNodeValue(),
						salesAcc.getFirstChild().getNodeValue());
			}

		}
		catch (Exception e) {
			field2.setText("�ش� ��¥�� ������ �����ϴ�.");
			field3.setText("���� 8�ڸ��� �Է����ּ���!!");
			code = null;
		}
	}
}
