import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

// AbstractTableModel�� Ŀ���͸���¡ �ϱ� ���� ��� �޴´�.
public class BoardTableModel extends AbstractTableModel {

	// �����͸� ������ ������ arraylist�� ����
	// �� arraylist�� ������ ���� Board Ŭ������ ���ø����� ����Ѵ�.
	private ArrayList<Board> boardList = new ArrayList<Board>();

	// ��ӹ��� �⺻ �Լ���, �÷� ������ �����Ѵ�.
	// ���� �÷��� 4������ �Ѵ�. url�� ȭ�鿡 ǥ�ô� ���� ��.

	@Override
	public int getColumnCount() { 
		return 4;
	}

	// ��ӹ��� �⺻ �Լ���, ������ �����������Ѵ�.
	// ������ ������ ������ �������ش�.

	@Override
	public int getRowCount() { 
		return boardList.size();
	}

	// �� ��Ŀ� � �����͸� ������ ������ �����Ѵ�.
	@Override
	public Object getValueAt(int row, int col) { 
		Board board = boardList.get(row);
		
		// col �� ��� �� �÷��� �����͸� �ҷ��;� �Ѵ�.
		if(col == 0)
			return board.getld();
		else if(col == 1)
			return board.getTitle();
		else if(col == 2)
			return board.getWriter();
		else
			return board.getDatetime();
	}
	// �÷� �̸��� ����
	private final String columns[] = {"�۹�ȣ", "����", "�ۼ���", "�۾� ��¥"};
	
	@Override
	public String getColumnName(int col)
	{
		return columns[col];
	}
	
	// ���� �����͸� ���� �Ұ����ϵ��� ����
	@Override
	public boolean isCellEditable(int rowlndex, int columnindex)
	{
		return false;
	}
	
	
	// ���� �Լ��� �����͸� ���� �� �ִ� �Լ� �� �ʱ�ȭ �Լ� �߰�
	public void addData(Board data) {
		// ����Ʈ�� ���� �־���
		boardList.add(data);
		
		// ui �� �����Ͱ� �߰��Ǿ����� �˸�
		fireTableDataChanged();
	}
	
	public void clearData() {
		// ����Ʈ ����
		boardList.clear();
		
		// ui �� �����Ͱ� ����Ǿ����� �˸�.
		fireTableDataChanged();
	}
}