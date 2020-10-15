package project;

abstract class Vending_Machine
{
	protected static int max;  //각 음료수의 최대 개수
	protected static int Money = 0;  //입력된 돈
	protected static int Income = 0; //수입
	protected static int[][] item = new int[4][2]; // [][0]:음료 개수, [][1]:음료 가격
	protected static String[] itemName = {"콜라", "사이다", "핫식스", "포카리"};
	
	public abstract void money_output(); //잔돈 반환
	public abstract int menu(String num); // 메뉴 선택 시, 경우의 수
	public abstract String prt(int num); // 음료수 출력
}


class VM_Starter {
	public static void main(String[] args) {
		new Order(5); //매개변수 = 자판기에 넣을 수 있는 음료수 각각의 최대 개수
		new Gui();
	}
}

