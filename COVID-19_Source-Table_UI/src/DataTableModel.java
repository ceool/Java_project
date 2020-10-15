import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

// AbstractTableModel 을 커스터마이징 하기 위해 상속 받는다.
public class DataTableModel extends AbstractTableModel {

	// 데이터를 여러개 관리할 arraylist 를 선언
	// 이 후는 이전에 만든 Data 클래스를 템플릿으로 사용한다.
	private ArrayList<Data> dataList = new ArrayList<Data>();

	// 상속받은 기본 함수로, 컬럼 갯수를 지정한다.
	// 현재 컬럼은 5종류이므로 5개로 고정 설정
	@Override
	public int getColumnCount() {
		return 5;
	}

	// 상속받은 기본 함수로, 데이터 개수를 설정한다.
	// 데이터 개수로 개수를 설정해준다.
	@Override
	public int getRowCount() { 
		return dataList.size();
	}

	// 각 행렬에 어떤 데이터를 보여줄 것인지 설정한다.
	@Override
	public Object getValueAt(int row, int col) { 
		Data d = dataList.get(row);

		// col 의 경우 각 컬럼의 데이터를 불러와야 한다.
		if(col == 0)
			return d.getSeq();
		else if(col == 1)
			return d.getCreateDt();
		else if(col == 2)
			return d.getExamCnt();
		else if(col == 3)
			return d.getDecideCnt();
		else
			return d.getDeathCnt();
	}

	// 컬럼 이름을 설정
	private final String columns[] = {"회차", "시간", "검사수", "확진자", "사망자"};
	@Override
	public String getColumnName(int col)
	{ 
		return columns[col];
	}

	// 안의 데이터를 수정 불가능하도록 설정
	@Override
	public boolean isCellEditable(int rowlndex, int columnindex)
	{
		return false;
	}

	// 유저 함수로 데이터를 넣을 수 있는 함수 및 초기화 함수 추가
	public void addData(Data data) {
		// 리스트에 값을 넣어줌
		dataList.add(data);

		// ui 에 데이터가 추가되었음을 알림
		fireTableDataChanged();
	}
	public void clearData() {
		// 리스트 비우기
		dataList.clear();

		// ui 에 데이터가 변경되었음을 알림.
		fireTableDataChanged();
	}
}
