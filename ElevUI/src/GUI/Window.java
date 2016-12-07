package GUI;

import javax.swing.*;

import System.EQ;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Window extends Thread{

	//���� ������
	public JFrame frmMain = new JFrame("Elevator");

	//â �Ʒ��� ��ġ�� ��ư ���(����� ������ ������)
	public JButton btnf1, btnf2, btnf3, btnf4, btnf5, btnf6;

	//����������
	public static Elevators elev1 = new Elevators(1);
	public static Elevators elev2 = new Elevators(2);
	public static Elevators elev3 = new Elevators(3);

	//ť
	public static EQ q1 = new EQ(1);
	public static EQ q2 = new EQ(2);
	public static EQ q3 = new EQ(3);

	//�� ���� ��ġ (���������� �̵� ���ϰ��϶��)
	public ArrayList<Integer> listFloorY = new ArrayList<Integer>();

	//���� ����
	public int nBt = 120;

	public static void main(String[] args) {
		Window main = new Window();
	}

	public Window() {
		//gb
		JLabel gb = new JLabel(""); //gb

		//background img
		frmMain.setContentPane(new JLabel(new ImageIcon("elevbg.png")));

		frmMain.setBounds(50, 50, 1000, 800);
		frmMain.setLayout(new BorderLayout());

		JPanel pnl = new JPanel();

		//listFloorY �ʱ�ȭ
		for(int i=0; i<6; i++)
			listFloorY.add(610 - nBt*i);

		//add elev
		elev1.GetLblElev().setBounds(675, 610, 63, 90);
		elev2.GetLblElev().setBounds(785, 610, 63, 90);
		elev3.GetLblElev().setBounds(895, 610, 63, 90);

		frmMain.add(elev1.GetLblElev(), "Center");
		frmMain.add(elev2.GetLblElev(), "Center");
		frmMain.add(elev3.GetLblElev(), "Center");
		frmMain.add(gb, "Center");

		//button init
		AddBtn();

		//button pnl setting
		frmMain.add(pnl, "South");
		pnl.setLayout(new GridLayout(1, 6, 0, 0));

		//in pnl add button
		pnl.add(btnf1); pnl.add(btnf2); pnl.add(btnf3); pnl.add(btnf4); pnl.add(btnf5); pnl.add(btnf6);
		pnl.setBackground(Color.white);
		pnl.setOpaque(true);

		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMain.setVisible(true);

		q1.start();
		q2.start();
		q3.start();

	}

	public void AddBtn() {
		ActionListener actionHandler = new ActionEventHandler();

		btnf1 = new JButton();
		btnf2 = new JButton();
		btnf3 = new JButton();
		btnf4 = new JButton();
		btnf5 = new JButton();
		btnf6 = new JButton();

		/* button bg */
		//btn1
		btnf1.setIcon(new ImageIcon("1f.png"));
		btnf1.setBorderPainted(false);
		btnf1.setContentAreaFilled(false);
		btnf1.setFocusPainted(false);

		//btn2
		btnf2.setIcon(new ImageIcon("2f.png"));
		btnf2.setBorderPainted(false);
		btnf2.setContentAreaFilled(false);
		btnf2.setFocusPainted(false);

		//btn3
		btnf3.setIcon(new ImageIcon("3f.png"));
		btnf3.setBorderPainted(false);
		btnf3.setContentAreaFilled(false);
		btnf3.setFocusPainted(false);

		//btn4
		btnf4.setIcon(new ImageIcon("4f.png"));
		btnf4.setBorderPainted(false);
		btnf4.setContentAreaFilled(false);
		btnf4.setFocusPainted(false);

		//btn5
		btnf5.setIcon(new ImageIcon("5f.png"));
		btnf5.setBorderPainted(false);
		btnf5.setContentAreaFilled(false);
		btnf5.setFocusPainted(false);

		//btn6
		btnf6.setIcon(new ImageIcon("6f.png"));
		btnf6.setBorderPainted(false);
		btnf6.setContentAreaFilled(false);
		btnf6.setFocusPainted(false);

		//button setting(action)
		btnf1.addActionListener(actionHandler);
		btnf2.addActionListener(actionHandler);
		btnf3.addActionListener(actionHandler);
		btnf4.addActionListener(actionHandler);
		btnf5.addActionListener(actionHandler);
		btnf6.addActionListener(actionHandler);
	}

	class ActionEventHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Thread t = new Thread(new Runnable() {
				@Override

				public void run() {
					// TODO Auto-generated method stub
					//�ϴ� ����� ��ü�� �����Ѵ�
					Humans h = new Humans();
					Elevators ev;
					int PosY;

					try {
						if(e.getSource() == btnf1) {

							//��� ����&��ġ ����
							PosY = h.GetFlrPosY(1);
							h.GetLblHum().setBounds(80, PosY, 63, 90);
							frmMain.add(h.GetLblHum(), "Center");
							h.SetStartFlr(0);

							
							//������-�� �� ����
							h.MoveSetting(1);
							h.run();
							h.join();

							
							//���� �߰�
							if(h.idxElev == 1) {
								ev = elev1;
							}else if(h.idxElev == 2) {
								ev = elev2;
							}else {
								ev = elev3;
							}
							
							System.out.println("AAA");
							//���� �ձ��� �̵�
							while(true) {
								if(ev.nNowflr == h.nSflr) {
									h.MoveSetting(2, ev.GetLblElev().getLocation().x);
									h.run();
									h.join();
									break;
								}
								
							}

							
							
							System.out.println("BBB");
							while(true) {
								if(ev.GetLblElev().getLocation().y == PosY) {
									System.out.println(">>���� �ձ��� �̵�");
									h.MoveSetting(2, ev.GetLblElev().getLocation().x);
									h.run();
									h.join();
									break;
								}
							}

							
							//���� ź��
							System.out.println("CCC");
							while(true) {
								if(ev.bOpen && ev.bTsn) {
									//transaction�� ������ 
									//������ ������!
									System.out.println(">>���� ź��");
									h.GetLblHum().setVisible(false);
									break;
								}
							}

							//��ٸ����� �ʴ´�
							//�ٷ� ������

							//������ �������� ���߸�
							while(!ev.bMove) {
								Thread.sleep(100);
								if(!ev.bMove && ev.bOpen) {
									System.out.println(">>��ⳡ");
									break;
								}
							}
							 
							//������������ ���̰� ���� �������� ���ٸ�
							
							while(true) {
								System.out.print(". ");
								if(ev.nNowflr == h.nEflr && ev.bOpen) {
									//���� ������
									System.out.println(">>������������ ���̰� ���� �������� �����Ƿ� ������");
									h.MoveSetting(3);
									h.run();
									h.join();
									h.GetLblHum().setVisible(true);
									h.GetLblHum().setBounds(ev.GetLblElev().getLocation().x-100, listFloorY.get(h.nEflr)+10, 63, 90);
									frmMain.add(h.GetLblHum(), "Center");
									break;
								}
							}



						} else if(e.getSource() == btnf2) {

						} else if(e.getSource() == btnf3) {

						} else if(e.getSource() == btnf4) {

						} else if(e.getSource() == btnf5) {

						} else if(e.getSource() == btnf6) {

						} 
					}catch(InterruptedException e) {
						e.printStackTrace();
					}

				}
			});
			t.start();
		}

	}

}
