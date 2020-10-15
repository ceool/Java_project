import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

// AbstractTableModel �� Ŀ���͸���¡ �ϱ� ���� ��� �޴´�.
public class DataTableModel extends AbstractTableModel {

	// �����͸� ������ ������ arraylist �� ����
	private ArrayList<Data> dataList = new ArrayList<Data>();

	// ��ӹ��� �⺻ �Լ���, �÷� ������ �����Ѵ�.
	@Override
	public int getColumnCount() {
		return 6;
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
			return d.getrank();
		else if(col == 1)
			return d.getmovieNm();
		else if(col == 2)
			return d.getopenDt();
		else if(col == 3)
			return d.getaudiCnt();
		else if(col == 4)
			return d.getaudiAcc();
		else
			return d.getsalesAcc();
	}

	// �÷� �̸��� ����
	private final String columns[] = {"��ũ", "��ȭ �̸�", "������", "������", "���� ������", "���� �����"};
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
