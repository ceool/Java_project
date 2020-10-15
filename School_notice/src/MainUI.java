import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;


// UI 를 설정하기 위해  JFrame 을 상속받는 클래스로 만든다.
public class MainUI extends JFrame {
	
	private BoardTableModel dataModel = new BoardTableModel();
	
	public MainUI() {
		// UI 에 대한 설정들
		
		// 사이즈 설정
		setSize(910, 310);
		// 레이아웃을 절대 좌표로 그릴 수 있도록 하기 위해 레이아웃 설정 제거
		setLayout(null);
		// 타이틀 설정
		setTitle("학교 공지사항");
		
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
						// 버튼 비활성화 
						buttonRefresh.setEnabled(false);
						
						// 웹 데이터 및 데이터 설정하는 함수를 불러온다.
						loadWebData();
						
						// 모든 동작이 끝난 뒤에 버튼을 다시 활성화 시켜줌.
						buttonRefresh.setEnabled(true);
					}
				}.start();
				
			}
		});
		
		// JTable 설정, 생성한 tableModel 과 같이 연결한다.
		
		JTable dataTable = new JTable(dataModel);
		// 크기 설정 및 한개만 선택 가능하도록 설정
		dataTable.setRowHeight(25);
		dataTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// scroll 넣기
		JScrollPane scrollPane = new JScrollPane(dataTable);
		scrollPane.setBounds(20, 60, 860, 200);
				
		// 버튼 및 리스트를 UI 에 넣기
		add(buttonRefresh);
		add(scrollPane);
	}
	
	// UI 띄우는 함수
	public void showUI() {
		setVisible(true);
	}
	
	private void loadWebData() {
		// 공지사항 정보들을 불러온다.
		ArrayList<Board> boardData = Parser.getHallymNotice();
		
		// 불러온 데이터를 model에 넣어준다.
		for(int i=0; i<boardData.size(); ++i)
		{
			dataModel.addData(boardData.get(i));
		}
	}
}
