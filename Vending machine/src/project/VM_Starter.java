package project;

abstract class Vending_Machine
{
	protected static int max;  //�� ������� �ִ� ����
	protected static int Money = 0;  //�Էµ� ��
	protected static int Income = 0; //����
	protected static int[][] item = new int[4][2]; // [][0]:���� ����, [][1]:���� ����
	protected static String[] itemName = {"�ݶ�", "���̴�", "�ֽĽ�", "��ī��"};
	
	public abstract void money_output(); //�ܵ� ��ȯ
	public abstract int menu(String num); // �޴� ���� ��, ����� ��
	public abstract String prt(int num); // ����� ���
}


class VM_Starter {
	public static void main(String[] args) {
		new Order(5); //�Ű����� = ���Ǳ⿡ ���� �� �ִ� ����� ������ �ִ� ����
		new Gui();
	}
}

