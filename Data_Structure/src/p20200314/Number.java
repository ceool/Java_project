package p20200314;


class Num implements Runnable
{
	String Snum;

	private int sleepTime;
	private int num;
	private int count;


	public Num(String name, int i, int j)
	{
		Snum = name + ": ";
		sleepTime = 100;
		num=i;
		count = j;
	}

	public void cal()
	{
		while(num < count)
		{
			Snum = Snum + Integer.toString(num) + " ";
			num++;
		}
	}

	public void run()
	{
		try
		{
			Thread.sleep(sleepTime);
			cal();
		}
		catch(InterruptedException e) {}
		System.out.println(Snum);
	}
}
public class Number {
	public static void main(String[] args) {

		Thread t = null;
		int n = 1;
		
		for(int i = 1; i < 400; i+=100) 
		{
			t = new Thread(new Num("t" + Integer.toString(n++), i, i+100));
			t.start();
		}
		
        try {
            /*
                join() �޼ҵ� ����.
                thread �����尡 ����� ������ main �����尡 ����Ѵ�.
            */
 
            t.join(); // ���� �����忡�� �۾� �������� ���Ḧ ����Ѵ�.
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
         
        System.out.println("�۾� ������ ���� ��... Main ������ �ٽ� ����");
    }
}