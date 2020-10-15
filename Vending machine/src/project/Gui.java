package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.InputMismatchException;

class Gui extends JFrame
{
	protected static JLabel x[] = new JLabel[4]; //����� ������ ���� ��
	private JLabel jlb1, jlb2, bg, retn, cover, out; //�����ݾ�, �����۾� ����, �̹�����
	private JLabel item[] = new JLabel[4]; //����� ��
	private JButton jbtn[] = new JButton[10]; //0-9 ��ư
	private JButton cancel, accept; //���, Ȯ�� ��ư
	private JButton jmoney[] = new JButton[5]; //�� ��ư
	private JTextField field1; //�Է�â
	private JTextField price[] = new JTextField[4]; //����� ������ ����
	private ImageIcon bgicon, retnicon, covericon; // ����� ��� �̹���
	private ImageIcon i[] = new ImageIcon[4]; //����� �̹���
	private ImageIcon outicon[] = new ImageIcon[4];//����� ��� �̹���

	public Gui()
	{
		JFrame f = new JFrame();
		f.setLayout(null); //�����Ӱ� ��ġ ���� ����

		Font font1 = new Font("Dialog",Font.BOLD, 20);
		Font font2 = new Font("����",Font.BOLD, 10);

		//��ҹ�ư, Ȯ�ι�ư ����
		cancel = new JButton("C");
		accept = new JButton("OK");
		
		//���� �Է� ��ư ����
		jmoney[0] = new JButton("100");
		jmoney[1] = new JButton("500");
		jmoney[2] = new JButton("1000");
		jmoney[3] = new JButton("5000");
		jmoney[4] = new JButton("10000");

		//�����ݾ� ǥ�� ��, ���� ��
		jlb1 = new JLabel("");
		jlb2 = new JLabel("");
		jlb1.setBounds(20, 370, 500, 22);
		jlb1.setFont(font1);
		jlb1.setForeground(new Color(0xffffff));
		f.add(jlb1);
		jlb2.setBounds(20, 400, 500, 22);
		jlb2.setFont(new Font("����",Font.PLAIN, 15));
		jlb2.setForeground(new Color(0xff0000));
		f.add(jlb2);

		//���� �Է¹�ư ����
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

		//��ȣ �Է� �ؽ�Ʈ â
		field1 = new JTextField();
		field1.setBounds(205, 320, 170, 30);
		field1.setFont(font1);
		field1.setEditable(false);
		f.add(field1);

		//0-9, ���, Ȯ�� ��ư ����
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


		//���Ǳ� ����ǥ
		price[0] = new JTextField("1100");
		price[1] = new JTextField(" 900");
		price[2] = new JTextField("1300");
		price[3] = new JTextField("1400");

		for(int i=0; i<price.length; i++)
		{
			price[i].setEditable(false);
			price[i].setFont(new Font("����",Font.PLAIN, 17));
			f.add(price[i]);
		}
		price[0].setBounds(35, 139, 40, 20);
		price[1].setBounds(132, 139, 40, 20);
		price[2].setBounds(227, 139, 40, 20);
		price[3].setBounds(323, 139, 40, 20);


		//���Ǳ� ������ ���� �� x
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


		//��ȯ����
		retnicon = new ImageIcon("src\\project\\return.png");
		retn = new JLabel(retnicon);
		retn.setBounds(110, 450, 75, 75);
		retn.addMouseListener(new MyMouseAdapter());
		f.add(retn);

		//���Ǳ� ����� �����°� Ŀ��
		covericon = new ImageIcon("src\\project\\cover.png");
		cover = new JLabel(covericon);
		cover.setBounds(13, 616, 374, 130);
		f.add(cover);

		//����� ��� (����� ����, Ŀ�� ���̷�)
		outicon[0] = new ImageIcon("src\\project\\out_item0.png");
		outicon[1] = new ImageIcon("src\\project\\out_item1.png");
		outicon[2] = new ImageIcon("src\\project\\out_item2.png");
		outicon[3] = new ImageIcon("src\\project\\out_item3.png");
		out = new JLabel();
		out.setBounds(130, 672, 144, 75);
		out.addMouseListener(new MyMouseAdapter());
		f.add(out);

		//���Ǳ� ���
		bgicon = new ImageIcon("src\\project\\back.png");
		bg = new JLabel(bgicon);
		bg.setBounds(-8, -20, 416, 840);
		f.add(bg);

		//���Ǳ� ����� �̹���
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
		f.setTitle("����� ���Ǳ�");
		f.setVisible(true);
	}

	class MyMouseAdapter extends MouseAdapter { 

		public void mouseClicked(MouseEvent e) { 
			if(e.getSource() == retn) //���� �ݾ� ��ȯ
			{
				new Order().money_output();
				jlb1.setText("");
				jlb2.setText("");
			}
			else if(e.getSource() == out) // ����� ������ ���
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
				jlb1.setText("�����ݾ� : " + Vending_Machine.Money);
				jlb2.setText("");
			} else if (e.getSource() == jmoney[1]) { 
				Vending_Machine.Money += 500;
				jlb1.setText("�����ݾ� : " + Vending_Machine.Money);
				jlb2.setText("");
			} else if (e.getSource() == jmoney[2]) { 
				Vending_Machine.Money += 1000;
				jlb1.setText("�����ݾ� : " + Vending_Machine.Money);
				jlb2.setText("");
			} else if (e.getSource() == jmoney[3]) { 
				Vending_Machine.Money += 5000;
				jlb1.setText("�����ݾ� : " + Vending_Machine.Money);
				jlb2.setText("");
			} else if (e.getSource() == jmoney[4]) { 
				Vending_Machine.Money += 10000;
				jlb1.setText("�����ݾ� : " + Vending_Machine.Money);
				jlb2.setText("");
			} 

			else if (e.getSource() == cancel)
				field1.setText("");

			else if (e.getSource() == accept)
			{
				try 
				{ 
					if(field1.getText().equals(Manager.password))
					{ //������ ���
						try {
							new FR().BufferedReader();
							field1.setText("");
							jlb2.setText(""); //���� �۾� ��
						} catch (Exception e1) {}
					}
					else
					{ //��ǥ�� �ν�
						Vending_Machine order = new Order(); //���Ǳ� ����� �ֹ��ڷ� ��ü ����
						int tmp = order.menu(field1.getText());

						if(tmp == 0) //�ش��ȣ ��ǰ ��ȣ�� ������ tmp ���� 0���� ��ȯ
							throw new InputMismatchException();

						if(Vending_Machine.Money >= tmp) //��ǰ���� ������ ���� ������ ������
						{
							int tmp2 = Integer.parseInt(field1.getText())-1; //�迭�� ��ǰ�� ��Ÿ���� ����.

							if(Vending_Machine.item[tmp2][0] == 0) //��ǰ�� ������ ������
							{
								jlb2.setText("�ش� ��ǰ�� �����ϴ�.");
								field1.setText("");
								return;
							}
							//��� ������ �����ϸ�
							Vending_Machine.Money -= tmp;
							Vending_Machine.Income += tmp;
							Vending_Machine.item[tmp2][0]--;
							Manager.itemprice[tmp2][0]++; //�Ǹŵ� ����� ����
							Manager.itemprice[tmp2][1] += tmp; //�Ǹŵ� ����� �� ����
							Thread.sleep(1000); //1�� ���

							//����� �̹��� ���
							out.setIcon(outicon[tmp2]);

							if(Vending_Machine.item[tmp2][0] == 0) //����� ������ ���ٸ�
								x[tmp2].setText("x"); //x ǥ��
							jlb1.setText("�����ݾ� : " + Vending_Machine.Money);
							jlb2.setText(order.prt(tmp2));

							//�ð��� ���Ӱ� ��� ����, ��ó:http://blog.naver.com/highkrs/220378198185
							String time = new SimpleDateFormat("<yyyy-MM-dd,   hh:mm:ss>  ")
									.format(Calendar.getInstance().getTime());
							Manager.source += (time + Vending_Machine.itemName[tmp2]
									+ "�� �ǸŵǾ����ϴ�. (����: " + Vending_Machine.Income + "��)\r\n");
						} 
						else	
						{
							jlb1.setText("�����ݾ� : " + Vending_Machine.Money);
							jlb2.setText("�ܾ��� �����մϴ�.");
						}
						field1.setText(""); // �ؽ�Ʈ �ʵ� ���� ��� ���� �����(catch���� ����)
					}
				}//End of try{}
				catch (InputMismatchException exception) {
					jlb2.setText("��ȣ�� �߸��Ǿ����ϴ�.");
					field1.setText("");
				} 
				catch (InterruptedException e1) { }
			} //End of (e.getSource() == accept)
		} //End of  actionPerformed(ActionEvent e)
	} //End of ActionListener
} //End of Gui