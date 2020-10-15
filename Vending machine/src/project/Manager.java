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
	protected static String password = "1234"; //������ ��� ��й�ȣ
	protected static String source=""; //���� ����� String ����
	protected static int[][] itemprice = {{0,0},{0,0},{0,0},{0,0}}; //���� �Ǹŵ� ����� ������ ����
	private JLabel jlb[] = new JLabel[4];
	private JLabel in, jlb1, jlb2, jlb3; //����, ���� ���ϳ��� ���� ��¹�, itemprice��¹�
	private JButton btn[] = new JButton[4]; //����� �߰� ��ư
	private JButton btn1, btn2; // �Ǹų��� ���� ���� ��ư, ������ �н����� ���� ��ư
	private JTextArea textArea; //�Ǹ� ����

	Manager()
	{
		JFrame m = new JFrame();
		m.setLayout(new FlowLayout());

		btn2 = new JButton("������ ��й�ȣ ����"); 

		//����� �߰� ��ư, ���� ����
		btn[0] = new JButton("�߰��ϱ�"); 
		btn[1] = new JButton("�߰��ϱ�");
		btn[2] = new JButton("�߰��ϱ�");
		btn[3] = new JButton("�߰��ϱ�");
		jlb[0] = new JLabel("�� �ݶ� : " + Vending_Machine.item[0][0] + "�� ����");
		jlb[1] = new JLabel("�����̴� : " + Vending_Machine.item[1][0] + "�� ����");
		jlb[2] = new JLabel(" �ֽĽ� : " + Vending_Machine.item[2][0] + "�� ����");
		jlb[3] = new JLabel("����ī�� : " + Vending_Machine.item[3][0] + "�� ����");


		in = new JLabel("��ü ���� : " + Vending_Machine.Income + "��");
		btn1 = new JButton("�Ǹų��� ���Ϸ� �����ϱ�");
		jlb1 = new JLabel("��ü ���԰� �Ʒ� �Ǹ� log�� ���� ����˴ϴ�.");


		jlb2 = new JLabel("�ǸŰ���(����) : ");
		jlb3 = new JLabel("�ǸŰ���(����) : ��");
		for(int i = 0; i<itemprice.length; i++)
		{
			if(i%2 != 0)
				jlb2.setText(jlb2.getText() + Vending_Machine.itemName[i] + " : " + itemprice[i][0]
						+ "��(" + itemprice[i][1] + "��)    ");
			else
				jlb3.setText(jlb3.getText() + Vending_Machine.itemName[i] + " : " + itemprice[i][0]
						+ "��(" + itemprice[i][1] + "��)    ");
		}

		textArea = new JTextArea(22,35); //�Ǹų��� ���
		textArea.append(source);
		JScrollPane scrollPane = new JScrollPane(textArea);

		for(int i =0; i<btn.length; i++)
		{
			jlb[i].setFont(new Font("����",Font.PLAIN, 12));
			btn[i].setFont(new Font("����",Font.BOLD, 12));
			m.add(jlb[i]);
			m.add(btn[i]);

			btn[i].addActionListener(new MyListener());
		}
		in.setFont(new Font("����",Font.BOLD, 30));
		btn1.setFont(new Font("����",Font.BOLD, 22));
		m.add(in, CENTER_ALIGNMENT);
		m.add(scrollPane);
		textArea.setEditable(false); //�Ǹų��� ���� ����

		m.add(btn1, CENTER_ALIGNMENT);
		btn1.addActionListener(new MyListener());
		btn2.addActionListener(new MyListener());
		m.add(jlb1);
		m.add(jlb2);
		m.add(jlb3);
		m.add(btn2);

		jlb1.setFont(new Font("����",Font.BOLD, 15));
		jlb2.setFont(new Font("����",Font.PLAIN, 12));
		jlb3.setFont(new Font("����",Font.PLAIN, 12));
		m.setBounds(300, 50, 416, 700);
		m.setTitle("������ ���");
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
				jlb[0].setText("�� �ݶ� : " + Vending_Machine.item[0][0] + "�� ����");
			} else if(e.getSource() == btn[1]) {
				if(Vending_Machine.item[1][0]==0)
					Gui.x[1].setText("");
				if(Vending_Machine.item[1][0]<Vending_Machine.max)
					Vending_Machine.item[1][0]++;
				jlb[1].setText("�����̴� : " + Vending_Machine.item[1][0] + "�� ����");
			} else if(e.getSource() == btn[2]) {
				if(Vending_Machine.item[2][0]==0)
					Gui.x[2].setText("");
				if(Vending_Machine.item[2][0]<Vending_Machine.max)
					Vending_Machine.item[2][0]++;
				jlb[2].setText(" �ֽĽ� : " + Vending_Machine.item[2][0] + "�� ����");
			} else if(e.getSource() == btn[3]) {
				if(Vending_Machine.item[3][0]==0)
					Gui.x[3].setText("");
				if(Vending_Machine.item[3][0]<Vending_Machine.max)
					Vending_Machine.item[3][0]++;
				jlb[3].setText("����ī�� : " + Vending_Machine.item[3][0] + "�� ����");
			}
		}

		private void ChangePassword() { //������ ��� ��й�ȣ ����
			int tmp = 0; //�������� Ȯ���ϱ� ���� �ӽð�
			String msg;

			while(true)
			{
				try {
					//JOptionPane ��ó : http://wcwtmt.blog.me/10176504678
					msg = JOptionPane.showInputDialog("������ ��й�ȣ�� �Է��ϼ���.(���ڸ� ����)");

					if(msg != null)
					{
						tmp = Integer.parseInt(msg); //�������� catch
						Manager.password = msg;
						JOptionPane.showMessageDialog(null, "'" + Manager.password + "' �� ����Ǿ����ϴ�.", 
								"�н����� ����", JOptionPane.INFORMATION_MESSAGE);
					}
					break;
				}
				catch (NumberFormatException exception) {
					JOptionPane.showMessageDialog(null, "���ڷ� �ٽ� �Է����ּ���.", "�н����� ����", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
	}

	public void BufferedWriter() throws Exception
	{
		int i =JOptionPane.showConfirmDialog(null,"�����߾��� ������ ������ �� �ֽ��ϴ�.\r\n�����Ͻðڽ��ϱ�?"
				, "���",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);

		if(i==0)
		{
			//�Ǹ� log �߰�(���� ����, ����, ����)
			source += jlb2.getText() +  "\r\n" + jlb3.getText() + "\r\n" ;
			source += in.getText() + "\r\n";
			source += "------------------------------------"
					+ "------------------------------------------------------------\r\n";

			FileWriter fw = new FileWriter("src\\project\\data.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(source);   // ���ڿ��� ���Ͽ� ���
			bw.close();

			jlb1.setText("�������Ǹ� ���� ���� �Ϸ�!������");
		}
	}
}

class FR implements writer
{
	private static int count=0; //���� �Ǹų��� �ߺ� ���� ����.
	private int num;

	public void BufferedReader() throws Exception
	{
		if(count == 0)
		{
			//���� �޼��� â, ��ó:http://wcwtmt.blog.me/10176504678
			num = JOptionPane.showConfirmDialog(null,"�Ǹ� ������ ���� ������ ���� �ҷ��ñ��?", "������ ���",
					JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);

			if(num == 0)
			{
				String s;
				ArrayList<String> array = new ArrayList<String>();

				FileReader fr = new FileReader("src\\project\\data.txt");
				BufferedReader br = new BufferedReader(fr); 

				while((s = br.readLine()) != null) // ������ ������ �� ���ξ� �б� 
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