package project;

class Order extends Vending_Machine
{
	Order() {}
	
	Order(int t)
	{
		max = t;
		for(int i = 0; i<item.length; i++)
		{
			item[i][0] = t; //각 음료수의 최대 개수
		}
		item[0][1] = 1100;
		item[1][1] = 900;
		item[2][1] = 1300;
		item[3][1] = 1400;
	}

	public void money_output() //반환
	{
		Money = 0;
	}

	public int menu(String num)
	{
		try{
			int tmp = Integer.parseInt(num);
			int i = 0;

			while(i<item.length) //상품 찾기
			{
				if((tmp-1)==i) //해당 상품 번호가 존재한다면
					return item[i][1];
				i++;
			}
			return 0;
		}
		catch(Exception e1) 
		{ //stirng으로 들어온 값이 숫자가 아니면 0으로 반환
			return 0;
		}
	}

	public String prt(int num) //상품 번호
	{
		return itemName[num] + "가 나왔습니다.";
	}
}