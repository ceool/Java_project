package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

interface reader
{
	public void BufferedWriter() throws Exception;
}
interface writer
{
	public static int count=0;
	public void BufferedReader() throws Exception;
}

class Manager extends JFrame implements reader
{
	protected static String password = "1234"; //관리자 모드 비밀번호
	protected static String source=""; //파일 입출력 String 구문
	protected static int[][] itemprice = {{0,0},{0,0},{0,0},{0,0}}; //각각 판매된 음료수 종류와 수입
	private JLabel jlb[] = new JLabel[4];
	private JLabel in, jlb1, jlb2, jlb3; //수입, 이전 파일내역 저장 출력문, itemprice출력문
	private JButton btn[] = new JButton[4]; //음료수 추가 버튼
	private JButton btn1, btn2; // 판매내역 파일 저장 버튼, 관리자 패스워드 변경 버튼
	private JTextArea textArea; //판매 내역

	Manager()
	{
		JFrame m = new JFrame();
		m.setLayout(new FlowLayout());

		btn2 = new JButton("관리자 비밀번호 변경"); 

		//음료수 추가 버튼, 내용 생성
		btn[0] = new JButton("추가하기"); 
		btn[1] = new JButton("추가하기");
		btn[2] = new JButton("추가하기");
		btn[3] = new JButton("추가하기");
		jlb[0] = new JLabel("　 콜라 : " + Vending_Machine.item[0][0] + "개 남음");
		jlb[1] = new JLabel("　사이다 : " + Vending_Machine.item[1][0] + "개 남음");
		jlb[2] = new JLabel(" 핫식스 : " + Vending_Machine.item[2][0] + "개 남음");
		jlb[3] = new JLabel("　포카리 : " + Vending_Machine.item[3][0] + "개 남음");


		in = new JLabel("전체 수입 : " + Vending_Machine.Income + "원");
		btn1 = new JButton("판매내역 파일로 저장하기");
		jlb1 = new JLabel("전체 수입과 아래 판매 log가 같이 저장됩니다.");


		jlb2 = new JLabel("판매개수(수입) : ");
		jlb3 = new JLabel("판매개수(수입) : 　");
		for(int i = 0; i<itemprice.length; i++)
		{
			if(i%2 != 0)
				jlb2.setText(jlb2.getText() + Vending_Machine.itemName[i] + " : " + itemprice[i][0]
						+ "개(" + itemprice[i][1] + "원)    ");
			else
				jlb3.setText(jlb3.getText() + Vending_Machine.itemName[i] + " : " + itemprice[i][0]
						+ "개(" + itemprice[i][1] + "원)    ");
		}

		textArea = new JTextArea(22,35); //판매내역 목록
		textArea.append(source);
		JScrollPane scrollPane = new JScrollPane(textArea);

		for(int i =0; i<btn.length; i++)
		{
			jlb[i].setFont(new Font("굴림",Font.PLAIN, 12));
			btn[i].setFont(new Font("굴림",Font.BOLD, 12));
			m.add(jlb[i]);
			m.add(btn[i]);

			btn[i].addActionListener(new MyListener());
		}
		in.setFont(new Font("굴림",Font.BOLD, 30));
		btn1.setFont(new Font("굴림",Font.BOLD, 22));
		m.add(in, CENTER_ALIGNMENT);
		m.add(scrollPane);
		textArea.setEditable(false); //판매내역 편집 금지

		m.add(btn1, CENTER_ALIGNMENT);
		btn1.addActionListener(new MyListener());
		btn2.addActionListener(new MyListener());
		m.add(jlb1);
		m.add(jlb2);
		m.add(jlb3);
		m.add(btn2);

		jlb1.setFont(new Font("굴림",Font.BOLD, 15));
		jlb2.setFont(new Font("굴림",Font.PLAIN, 12));
		jlb3.setFont(new Font("굴림",Font.PLAIN, 12));
		m.setBounds(300, 50, 416, 700);
		m.setTitle("관리자 모드");
		m.setVisible(true);
	}

	class MyListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == btn1)
			{
				try 
				{
					BufferedWriter();
				} 
				catch (Exception e1) {e1.printStackTrace();}
			} else if(e.getSource() == btn2) {
				ChangePassword();

			} else if(e.getSource() == btn[0]) {
				if(Vending_Machine.item[0][0]==0)
					Gui.x[0].setText("");
				if(Vending_Machine.item[0][0]<Vending_Machine.max)
					Vending_Machine.item[0][0]++;
				jlb[0].setText("　 콜라 : " + Vending_Machine.item[0][0] + "개 남음");
			} else if(e.getSource() == btn[1]) {
				if(Vending_Machine.item[1][0]==0)
					Gui.x[1].setText("");
				if(Vending_Machine.item[1][0]<Vending_Machine.max)
					Vending_Machine.item[1][0]++;
				jlb[1].setText("　사이다 : " + Vending_Machine.item[1][0] + "개 남음");
			} else if(e.getSource() == btn[2]) {
				if(Vending_Machine.item[2][0]==0)
					Gui.x[2].setText("");
				if(Vending_Machine.item[2][0]<Vending_Machine.max)
					Vending_Machine.item[2][0]++;
				jlb[2].setText(" 핫식스 : " + Vending_Machine.item[2][0] + "개 남음");
			} else if(e.getSource() == btn[3]) {
				if(Vending_Machine.item[3][0]==0)
					Gui.x[3].setText("");
				if(Vending_Machine.item[3][0]<Vending_Machine.max)
					Vending_Machine.item[3][0]++;
				jlb[3].setText("　포카리 : " + Vending_Machine.item[3][0] + "개 남음");
			}
		}

		private void ChangePassword() { //관리자 모드 비밀번호 변경
			int tmp = 0; //숫자인지 확인하기 위한 임시값
			String msg;

			while(true)
			{
				try {
					//JOptionPane 출처 : http://wcwtmt.blog.me/10176504678
					msg = JOptionPane.showInputDialog("변경할 비밀번호를 입력하세요.(숫자만 가능)");

					if(msg != null)
					{
						tmp = Integer.parseInt(msg); //숫자인지 catch
						Manager.password = msg;
						JOptionPane.showMessageDialog(null, "'" + Manager.password + "' 로 변경되었습니다.", 
								"패스워드 변경", JOptionPane.INFORMATION_MESSAGE);
					}
					break;
				}
				catch (NumberFormatException exception) {
					JOptionPane.showMessageDialog(null, "숫자로 다시 입력해주세요.", "패스워드 변경", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
	}

	public void BufferedWriter() throws Exception
	{
		int i =JOptionPane.showConfirmDialog(null,"저장했었던 정보가 삭제될 수 있습니다.\r\n저장하시겠습니까?"
				, "경고",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);

		if(i==0)
		{
			//판매 log 추가(음료 종류, 개수, 수입)
			source += jlb2.getText() +  "\r\n" + jlb3.getText() + "\r\n" ;
			source += in.getText() + "\r\n";
			source += "------------------------------------"
					+ "------------------------------------------------------------\r\n";

			FileWriter fw = new FileWriter("src\\project\\data.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(source);   // 문자열을 파일에 출력
			bw.close();

			jlb1.setText("　　　판매 내역 저장 완료!　　　");
		}
	}
}

class FR implements writer
{
	private static int count=0; //이전 판매내역 중복 생성 방지.
	private int num;

	public void BufferedReader() throws Exception
	{
		if(count == 0)
		{
			//스윙 메세지 창, 출처:http://wcwtmt.blog.me/10176504678
			num = JOptionPane.showConfirmDialog(null,"판매 내역을 이전 내역과 같이 불러올까요?", "관리자 모드",
					JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);

			if(num == 0)
			{
				String s;
				ArrayList<String> array = new ArrayList<String>();

				FileReader fr = new FileReader("src\\project\\data.txt");
				BufferedReader br = new BufferedReader(fr); 

				while((s = br.readLine()) != null) // 파일의 끝까지 한 라인씩 읽기 
					array.add(s+"\r\n");
				br.close(); s="";

				Iterator<String> it = array.iterator();
				while(it.hasNext())
					s+=it.next();

				Manager.source = s + Manager.source;
				count++;
			}
		}
		new Manager();
	}
}