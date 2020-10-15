import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

// AbstractTableModel �� Ŀ���͸���¡ �ϱ� ���� ��� �޴´�.
public class DataTableModel extends AbstractTableModel {

	// �����͸� ������ ������ arraylist �� ����
	// �� �Ĵ� ������ ���� Data Ŭ������ ���ø����� ����Ѵ�.
	private ArrayList<Data> dataList = new ArrayList<Data>();

	// ��ӹ��� �⺻ �Լ���, �÷� ������ �����Ѵ�.
	// ���� �÷��� 5�����̹Ƿ� 5���� ���� ����
	@Override
	public int getColumnCount() {
		return 5;
	}

	// ��ӹ��� �⺻ �Լ���, ������ ������ �����Ѵ�.
	// ������ ������ ������ �������ش�.
	@Override
	public int getRowCount() { 
		return dataList.size();
	}

	// �� ��Ŀ� � �����͸� ������ ������ �����Ѵ�.
	@Override
	public Object getValueAt(int row, int col) { 
		Data d = dataList.get(row);

		// col �� ��� �� �÷��� �����͸� �ҷ��;� �Ѵ�.
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

	// �÷� �̸��� ����
	private final String columns[] = {"ȸ��", "�ð�", "�˻��", "Ȯ����", "�����"};
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
	public void addData(Data data) {
		// ����Ʈ�� ���� �־���
		dataList.add(data);

		// ui �� �����Ͱ� �߰��Ǿ����� �˸�
		fireTableDataChanged();
	}
	public void clearData() {
		// ����Ʈ ����
		dataList.clear();

		// ui �� �����Ͱ� ����Ǿ����� �˸�.
		fireTableDataChanged();
	}
}
