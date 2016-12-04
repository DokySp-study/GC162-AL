package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Window extends Thread{

	//메인 프레임
	public JFrame frmMain = new JFrame("Elevator");

	//창 아래에 배치할 버튼 목록(사람을 몇층에 놓을까)
	public JButton btnf1, btnf2, btnf3, btnf4, btnf5, btnf6;

	//엘리베이터
	public Elevators elev1 = new Elevators(1);
	public Elevators elev2 = new Elevators(2);
	public Elevators elev3 = new Elevators(3);

	//각 층의 위치 (엘리베이터 이동 편하게하라고)
	public ArrayList<Integer> listFloorY = new ArrayList<Integer>();

	//사이 간격
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

		//listFloorY 초기화
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
					try {
						Humans h = new Humans();
						Elevators ev;
						
						
						if(e.getSource() == btnf1) {

							ev = elev1;

							h.SetSflr(1);
							h.GetLblHum().setBounds(80, h.y, 63, 90);

							frmMain.add(h.GetLblHum(), "Center");

							//사람이 이동
							h.MoveSetting(0, ev.GetLblElev().getLocation().x);
							h.run();
							h.join();
							h.GetLblHum().setVisible(false);

							//엘베 문 열림
							ev.MoveSetting(0);
							ev.run();
							ev.join();

							//엘베 문 닫힘
							ev.MoveSetting(1);
							ev.run();
							ev.join();

							//엘베 올라감 (listFloorY 사용)
							ev.MoveSetting(2, listFloorY.get(0), listFloorY.get(h.nEflr));
							ev.run();
							ev.join();

							//엘베 문 열림
							ev.MoveSetting(0);
							ev.run();
							ev.join();


							//엘베 문 닫힘
							ev.MoveSetting(1);
							ev.run();
							ev.join();

							//사람 내림
							h.MoveSetting(1, ev.GetLblElev().getLocation());
							h.run();
							h.GetLblHum().setVisible(true);
							h.GetLblHum().setBounds(ev.GetLblElev().getLocation().x-100, listFloorY.get(h.nEflr)+10, 63, 90);
							frmMain.add(h.GetLblHum(), "Center");




						} else if(e.getSource() == btnf2) {
							ev = elev3;

							h.SetSflr(2);
							h.GetLblHum().setBounds(80, h.y, 63, 90);

							frmMain.add(h.GetLblHum(), "Center");

							//사람이 이동
							h.MoveSetting(0, ev.GetLblElev().getLocation().x);
							h.run();
							h.join();
							h.GetLblHum().setVisible(false);

							//엘베 문 열림
							ev.MoveSetting(0);
							ev.run();
							ev.join();

							//엘베 문 닫힘
							ev.MoveSetting(1);
							ev.run();
							ev.join();

							//엘베 올라감 (listFloorY 사용)
							ev.MoveSetting(2, listFloorY.get(0), listFloorY.get(h.nEflr));
							ev.run();
							ev.join();

							//엘베 문 열림
							ev.MoveSetting(0);
							ev.run();
							ev.join();


							//엘베 문 닫힘
							ev.MoveSetting(1);
							ev.run();
							ev.join();

							//사람 내림
							h.MoveSetting(1, ev.GetLblElev().getLocation());
							h.run();
							h.GetLblHum().setVisible(true);
							h.GetLblHum().setBounds(ev.GetLblElev().getLocation().x-100, listFloorY.get(h.nEflr)+10, 63, 90);
							frmMain.add(h.GetLblHum(), "Center");


						} else if(e.getSource() == btnf3) {
							ev = elev1;

							h.SetSflr(3);
							h.GetLblHum().setBounds(80, h.y, 63, 90);

							frmMain.add(h.GetLblHum(), "Center");

							//사람이 이동
							h.MoveSetting(0, ev.GetLblElev().getLocation().x);
							h.run();
							h.join();
							h.GetLblHum().setVisible(false);

							//엘베 문 열림
							ev.MoveSetting(0);
							ev.run();
							ev.join();

							//엘베 문 닫힘
							ev.MoveSetting(1);
							ev.run();
							ev.join();

							//엘베 올라감 (listFloorY 사용)
							ev.MoveSetting(2, listFloorY.get(0), listFloorY.get(h.nEflr));
							ev.run();
							ev.join();

							//엘베 문 열림
							ev.MoveSetting(0);
							ev.run();
							ev.join();


							//엘베 문 닫힘
							ev.MoveSetting(1);
							ev.run();
							ev.join();

							//사람 내림
							h.MoveSetting(1, ev.GetLblElev().getLocation());
							h.run();
							h.GetLblHum().setVisible(true);
							h.GetLblHum().setBounds(ev.GetLblElev().getLocation().x-100, listFloorY.get(h.nEflr)+10, 63, 90);
							frmMain.add(h.GetLblHum(), "Center");


						} else if(e.getSource() == btnf4) {
							ev = elev1;

							h.SetSflr(4);
							h.GetLblHum().setBounds(80, h.y, 63, 90);

							frmMain.add(h.GetLblHum(), "Center");

							//사람이 이동
							h.MoveSetting(0, ev.GetLblElev().getLocation().x);
							h.run();
							h.join();
							h.GetLblHum().setVisible(false);

							//엘베 문 열림
							ev.MoveSetting(0);
							ev.run();
							ev.join();

							//엘베 문 닫힘
							ev.MoveSetting(1);
							ev.run();
							ev.join();

							//엘베 올라감 (listFloorY 사용)
							ev.MoveSetting(2, listFloorY.get(0), listFloorY.get(h.nEflr));
							ev.run();
							ev.join();

							//엘베 문 열림
							ev.MoveSetting(0);
							ev.run();
							ev.join();


							//엘베 문 닫힘
							ev.MoveSetting(1);
							ev.run();
							ev.join();

							//사람 내림
							h.MoveSetting(1, ev.GetLblElev().getLocation());
							h.run();
							h.GetLblHum().setVisible(true);
							h.GetLblHum().setBounds(ev.GetLblElev().getLocation().x-100, listFloorY.get(h.nEflr)+10, 63, 90);
							frmMain.add(h.GetLblHum(), "Center");

						} else if(e.getSource() == btnf5) {
							ev = elev2;

							h.SetSflr(5);
							h.GetLblHum().setBounds(80, h.y, 63, 90);

							frmMain.add(h.GetLblHum(), "Center");

							//사람이 이동
							h.MoveSetting(0, ev.GetLblElev().getLocation().x);
							h.run();
							h.join();
							h.GetLblHum().setVisible(false);

							//엘베 문 열림
							ev.MoveSetting(0);
							ev.run();
							ev.join();

							//엘베 문 닫힘
							ev.MoveSetting(1);
							ev.run();
							ev.join();

							//엘베 올라감 (listFloorY 사용)
							ev.MoveSetting(2, listFloorY.get(0), listFloorY.get(h.nEflr));
							ev.run();
							ev.join();

							//엘베 문 열림
							ev.MoveSetting(0);
							ev.run();
							ev.join();


							//엘베 문 닫힘
							ev.MoveSetting(1);
							ev.run();
							ev.join();

							//사람 내림
							h.MoveSetting(1, ev.GetLblElev().getLocation());
							h.run();
							h.GetLblHum().setVisible(true);
							h.GetLblHum().setBounds(ev.GetLblElev().getLocation().x-100, listFloorY.get(h.nEflr)+10, 63, 90);
							frmMain.add(h.GetLblHum(), "Center");


						} else if(e.getSource() == btnf6) {
							ev = elev1;

							h.SetSflr(6);
							h.GetLblHum().setBounds(80, h.y, 63, 90);

							frmMain.add(h.GetLblHum(), "Center");

							//사람이 이동
							h.MoveSetting(0, ev.GetLblElev().getLocation().x);
							h.run();
							h.join();
							h.GetLblHum().setVisible(false);

							//엘베 문 열림
							ev.MoveSetting(0);
							ev.run();
							ev.join();

							//엘베 문 닫힘
							ev.MoveSetting(1);
							ev.run();
							ev.join();

							//엘베 올라감 (listFloorY 사용)
							ev.MoveSetting(2, listFloorY.get(0), listFloorY.get(h.nEflr));
							ev.run();
							ev.join();

							//엘베 문 열림
							ev.MoveSetting(0);
							ev.run();
							ev.join();


							//엘베 문 닫힘
							ev.MoveSetting(1);
							ev.run();
							ev.join();

							//사람 내림
							h.MoveSetting(1, ev.GetLblElev().getLocation());
							h.run();
							h.GetLblHum().setVisible(true);
							h.GetLblHum().setBounds(ev.GetLblElev().getLocation().x-100, listFloorY.get(h.nEflr)+10, 63, 90);
							frmMain.add(h.GetLblHum(), "Center");

						}
						frmMain.add(h.GetLblHum());
					}catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			});
			t.start();
		}

	}

}
