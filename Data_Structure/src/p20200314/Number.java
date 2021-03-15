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
                join() 메소드 실행.
                thread 스레드가 종료될 때까지 main 스레드가 대기한다.
            */
 
            t.join(); // 메인 스레드에서 작업 스레드의 종료를 대기한다.
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
         
        System.out.println("작업 스레드 종료 후... Main 스레드 다시 동작");
    }
}