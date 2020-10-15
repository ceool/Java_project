import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;


// UI �� �����ϱ� ����  JFrame �� ��ӹ޴� Ŭ������ �����.
public class MainUI extends JFrame {
	
	private BoardTableModel dataModel = new BoardTableModel();
	
	public MainUI() {
		// UI �� ���� ������
		
		// ������ ����
		setSize(910, 310);
		// ���̾ƿ��� ���� ��ǥ�� �׸� �� �ֵ��� �ϱ� ���� ���̾ƿ� ���� ����
		setLayout(null);
		// Ÿ��Ʋ ����
		setTitle("�б� ��������");
		
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
						// ��ư ��Ȱ��ȭ 
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
		scrollPane.setBounds(20, 60, 860, 200);
				
		// ��ư �� ����Ʈ�� UI �� �ֱ�
		add(buttonRefresh);
		add(scrollPane);
	}
	
	// UI ���� �Լ�
	public void showUI() {
		setVisible(true);
	}
	
	private void loadWebData() {
		// �������� �������� �ҷ��´�.
		ArrayList<Board> boardData = Parser.getHallymNotice();
		
		// �ҷ��� �����͸� model�� �־��ش�.
		for(int i=0; i<boardData.size(); ++i)
		{
			dataModel.addData(boardData.get(i));
		}
	}
}
