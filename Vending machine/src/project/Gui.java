package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.InputMismatchException;

class Gui extends JFrame
{
	protected static JLabel x[] = new JLabel[4]; //음료수 수량이 없을 때
	private JLabel jlb1, jlb2, bg, retn, cover, out; //남은금액, 붉은글씨 설명, 이미지들
	private JLabel item[] = new JLabel[4]; //음료수 라벨
	private JButton jbtn[] = new JButton[10]; //0-9 버튼
	private JButton cancel, accept; //취소, 확인 버튼
	private JButton jmoney[] = new JButton[5]; //돈 버튼
	private JTextField field1; //입력창
	private JTextField price[] = new JTextField[4]; //음료수 각각의 가격
	private ImageIcon bgicon, retnicon, covericon; // 음료수 기능 이미지
	private ImageIcon i[] = new ImageIcon[4]; //음료수 이미지
	private ImageIcon outicon[] = new ImageIcon[4];//음료수 출력 이미지

	public Gui()
	{
		JFrame f = new JFrame();
		f.setLayout(null); //자유롭게 위치 설정 가능

		Font font1 = new Font("Dialog",Font.BOLD, 20);
		Font font2 = new Font("굴림",Font.BOLD, 10);

		//취소버튼, 확인버튼 생성
		cancel = new JButton("C");
		accept = new JButton("OK");
		
		//현금 입력 버튼 새성
		jmoney[0] = new JButton("100");
		jmoney[1] = new JButton("500");
		jmoney[2] = new JButton("1000");
		jmoney[3] = new JButton("5000");
		jmoney[4] = new JButton("10000");

		//남은금액 표시 라벨, 설명 라벨
		jlb1 = new JLabel("");
		jlb2 = new JLabel("");
		jlb1.setBounds(20, 370, 500, 22);
		jlb1.setFont(font1);
		jlb1.setForeground(new Color(0xffffff));
		f.add(jlb1);
		jlb2.setBounds(20, 400, 500, 22);
		jlb2.setFont(new Font("굴림",Font.PLAIN, 15));
		jlb2.setForeground(new Color(0xff0000));
		f.add(jlb2);

		//현금 입력버튼 설정
		for(int i = 0; i<jmoney.length; i++)
		{
			if(i<2)
				jmoney[i].setBounds(15+65*i, 255, 55,40);
			else if(i<4)
				jmoney[i].setBounds(145 + 80*(i-2), 255, 70,40);
			else
				jmoney[i].setBounds(305, 255, 75,40);

			f.add(jmoney[i]);
			jmoney[i].addActionListener(new MyListener());
		}

		//번호 입력 텍스트 창
		field1 = new JTextField();
		field1.setBounds(205, 320, 170, 30);
		field1.setFont(font1);
		field1.setEditable(false);
		f.add(field1);

		//0-9, 취소, 확인 버튼 설정
		for(int i = 0; i<jbtn.length; i++)
		{    
			jbtn[i] = new JButton("" + i);
			if(i==0)
				jbtn[i].setLocation(2*60+145, 540);
			else if(i<=3)
				jbtn[i].setLocation(i*60+145, 360);
			else if(i<=6)
				jbtn[i].setLocation((i-3)*60+145, 420);
			else
				jbtn[i].setLocation((i-6)*60+145, 480);
			f.add(jbtn[i]);
			jbtn[i].setSize(50,50);
			jbtn[i].setFont(font1);
			jbtn[i].addActionListener(new MyListener());
		}
		cancel.setBounds(60+145, 540, 50,50);
		f.add(cancel);
		cancel.setFont(font2);
		cancel.addActionListener(new MyListener());

		accept.setBounds(3*60+145, 540, 50,50);
		f.add(accept);
		accept.setFont(font2);
		accept.addActionListener(new MyListener());


		//자판기 가격표
		price[0] = new JTextField("1100");
		price[1] = new JTextField(" 900");
		price[2] = new JTextField("1300");
		price[3] = new JTextField("1400");

		for(int i=0; i<price.length; i++)
		{
			price[i].setEditable(false);
			price[i].setFont(new Font("굴림",Font.PLAIN, 17));
			f.add(price[i]);
		}
		price[0].setBounds(35, 139, 40, 20);
		price[1].setBounds(132, 139, 40, 20);
		price[2].setBounds(227, 139, 40, 20);
		price[3].setBounds(323, 139, 40, 20);


		//자판기 수량이 없을 때 x
		x[0] = new JLabel("");
		x[1] = new JLabel("");
		x[2] = new JLabel("");
		x[3] = new JLabel("");

		for(int i=0; i<x.length; i++)
		{
			f.add(x[i]);
			x[i].setForeground(new Color(0xff0000));
		}
		x[0].setBounds(65, 184, 500, 22);
		x[1].setBounds(162, 184, 500, 22);
		x[2].setBounds(257, 184, 500, 22);
		x[3].setBounds(353, 184, 500, 22);


		//반환레버
		retnicon = new ImageIcon("src\\project\\return.png");
		retn = new JLabel(retnicon);
		retn.setBounds(110, 450, 75, 75);
		retn.addMouseListener(new MyMouseAdapter());
		f.add(retn);

		//자판기 음료수 나오는곳 커버
		covericon = new ImageIcon("src\\project\\cover.png");
		cover = new JLabel(covericon);
		cover.setBounds(13, 616, 374, 130);
		f.add(cover);

		//음료수 출력 (음료수 배경과, 커버 사이로)
		outicon[0] = new ImageIcon("src\\project\\out_item0.png");
		outicon[1] = new ImageIcon("src\\project\\out_item1.png");
		outicon[2] = new ImageIcon("src\\project\\out_item2.png");
		outicon[3] = new ImageIcon("src\\project\\out_item3.png");
		out = new JLabel();
		out.setBounds(130, 672, 144, 75);
		out.addMouseListener(new MyMouseAdapter());
		f.add(out);

		//자판기 배경
		bgicon = new ImageIcon("src\\project\\back.png");
		bg = new JLabel(bgicon);
		bg.setBounds(-8, -20, 416, 840);
		f.add(bg);

		//자판기 음료수 이미지
		i[0] = new ImageIcon("src\\project\\item0.png");
		i[1] = new ImageIcon("src\\project\\item1.png");
		i[2] = new ImageIcon("src\\project\\item2.png");
		i[3] = new ImageIcon("src\\project\\item3.png");
		item[0] = new JLabel(i[0]);
		item[0].setBounds(20, 15, 75, 144);
		f.add(item[0]);
		item[1] = new JLabel(i[1]);
		item[1].setBounds(115, 15, 75, 144);
		f.add(item[1]);
		item[2] = new JLabel(i[2]);
		item[2].setBounds(210, 15, 75, 144);
		f.add(item[2]);
		item[3] = new JLabel(i[3]);
		item[3].setBounds(305, 15, 75, 144);
		f.add(item[3]);

		f.setBounds(300, 50, 416, 840);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("음료수 자판기");
		f.setVisible(true);
	}

	class MyMouseAdapter extends MouseAdapter { 

		public void mouseClicked(MouseEvent e) { 
			if(e.getSource() == retn) //남은 금액 반환
			{
				new Order().money_output();
				jlb1.setText("");
				jlb2.setText("");
			}
			else if(e.getSource() == out) // 음료수 꺼내는 기능
			{
				out.setIcon(null);
				jlb2.setText("");
				if(Vending_Machine.Money==0)
					jlb1.setText("");
			}
		}
	}// End of MouseAdapter

	class MyListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == jbtn[1])
				field1.setText(field1.getText() + "1");
			else if (e.getSource() == jbtn[2])
				field1.setText(field1.getText() + "2");
			else if (e.getSource() == jbtn[3])
				field1.setText(field1.getText() + "3");
			else if (e.getSource() == jbtn[4])
				field1.setText(field1.getText() + "4");
			else if (e.getSource() == jbtn[5])
				field1.setText(field1.getText() + "5");
			else if (e.getSource() == jbtn[6])
				field1.setText(field1.getText() + "6");
			else if (e.getSource() == jbtn[7])
				field1.setText(field1.getText() + "7");
			else if (e.getSource() == jbtn[8])
				field1.setText(field1.getText() + "8");
			else if (e.getSource() == jbtn[9])
				field1.setText(field1.getText() + "9");
			else if (e.getSource() == jbtn[0])
				field1.setText(field1.getText() + "0");

			else if (e.getSource() == jmoney[0]) { 
				Vending_Machine.Money += 100;
				jlb1.setText("남은금액 : " + Vending_Machine.Money);
				jlb2.setText("");
			} else if (e.getSource() == jmoney[1]) { 
				Vending_Machine.Money += 500;
				jlb1.setText("남은금액 : " + Vending_Machine.Money);
				jlb2.setText("");
			} else if (e.getSource() == jmoney[2]) { 
				Vending_Machine.Money += 1000;
				jlb1.setText("남은금액 : " + Vending_Machine.Money);
				jlb2.setText("");
			} else if (e.getSource() == jmoney[3]) { 
				Vending_Machine.Money += 5000;
				jlb1.setText("남은금액 : " + Vending_Machine.Money);
				jlb2.setText("");
			} else if (e.getSource() == jmoney[4]) { 
				Vending_Machine.Money += 10000;
				jlb1.setText("남은금액 : " + Vending_Machine.Money);
				jlb2.setText("");
			} 

			else if (e.getSource() == cancel)
				field1.setText("");

			else if (e.getSource() == accept)
			{
				try 
				{ 
					if(field1.getText().equals(Manager.password))
					{ //관리자 모드
						try {
							new FR().BufferedReader();
							field1.setText("");
							jlb2.setText(""); //붉은 글씨 라벨
						} catch (Exception e1) {}
					}
					else
					{ //음표수 인식
						Vending_Machine order = new Order(); //자판기 기능을 주문자로 객체 생성
						int tmp = order.menu(field1.getText());

						if(tmp == 0) //해당번호 상품 번호가 없으면 tmp 값을 0으로 반환
							throw new InputMismatchException();

						if(Vending_Machine.Money >= tmp) //상품보다 투입한 돈의 가격이 높으면
						{
							int tmp2 = Integer.parseInt(field1.getText())-1; //배열로 상품을 나타내기 위함.

							if(Vending_Machine.item[tmp2][0] == 0) //상품의 개수가 없으면
							{
								jlb2.setText("해당 상품이 없습니다.");
								field1.setText("");
								return;
							}
							//모든 조건을 만족하면
							Vending_Machine.Money -= tmp;
							Vending_Machine.Income += tmp;
							Vending_Machine.item[tmp2][0]--;
							Manager.itemprice[tmp2][0]++; //판매된 음료수 개수
							Manager.itemprice[tmp2][1] += tmp; //판매된 음료수 총 가격
							Thread.sleep(1000); //1초 대기

							//음료수 이미지 출력
							out.setIcon(outicon[tmp2]);

							if(Vending_Machine.item[tmp2][0] == 0) //음료수 개수가 없다면
								x[tmp2].setText("x"); //x 표시
							jlb1.setText("남은금액 : " + Vending_Machine.Money);
							jlb2.setText(order.prt(tmp2));

							//시간을 새롭게 계속 갱신, 출처:http://blog.naver.com/highkrs/220378198185
							String time = new SimpleDateFormat("<yyyy-MM-dd,   hh:mm:ss>  ")
									.format(Calendar.getInstance().getTime());
							Manager.source += (time + Vending_Machine.itemName[tmp2]
									+ "가 판매되었습니다. (수입: " + Vending_Machine.Income + "원)\r\n");
						} 
						else	
						{
							jlb1.setText("남은금액 : " + Vending_Machine.Money);
							jlb2.setText("잔액이 부족합니다.");
						}
						field1.setText(""); // 텍스트 필드 값은 어떠한 경우라도 비워짐(catch에도 있음)
					}
				}//End of try{}
				catch (InputMismatchException exception) {
					jlb2.setText("번호가 잘못되었습니다.");
					field1.setText("");
				} 
				catch (InterruptedException e1) { }
			} //End of (e.getSource() == accept)
		} //End of  actionPerformed(ActionEvent e)
	} //End of ActionListener
} //End of Gui