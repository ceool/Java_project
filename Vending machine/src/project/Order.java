package project;

class Order extends Vending_Machine
{
	Order() {}
	
	Order(int t)
	{
		max = t;
		for(int i = 0; i<item.length; i++)
		{
			item[i][0] = t; //�� ������� �ִ� ����
		}
		item[0][1] = 1100;
		item[1][1] = 900;
		item[2][1] = 1300;
		item[3][1] = 1400;
	}

	public void money_output() //��ȯ
	{
		Money = 0;
	}

	public int menu(String num)
	{
		try{
			int tmp = Integer.parseInt(num);
			int i = 0;

			while(i<item.length) //��ǰ ã��
			{
				if((tmp-1)==i) //�ش� ��ǰ ��ȣ�� �����Ѵٸ�
					return item[i][1];
				i++;
			}
			return 0;
		}
		catch(Exception e1) 
		{ //stirng���� ���� ���� ���ڰ� �ƴϸ� 0���� ��ȯ
			return 0;
		}
	}

	public String prt(int num) //��ǰ ��ȣ
	{
		return itemName[num] + "�� ���Խ��ϴ�.";
	}
}