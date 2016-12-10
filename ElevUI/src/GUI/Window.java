package GUI;

import javax.swing.*;

import System.EQ;
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
	public static Elevators elev1 = new Elevators();
	public static Elevators elev2 = new Elevators();
	public static Elevators elev3 = new Elevators();

	//큐
	public static EQ q1 = new EQ(1);
	public static EQ q2 = new EQ(2);
	public static EQ q3 = new EQ(3);
	
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

		frmMain.setBounds(50, 50, 1000, 780);
		frmMain.setLayout(new BorderLayout());

		JPanel pnl = new JPanel();

		//listFloorY 초기화
		for(int i=0; i<6; i++)
			listFloorY.add(610 - nBt*i);

		//add elev
		elev1.GetLblElev().setBounds(685, 610, 63, 90);
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
		//pnl.setOpaque(true);

		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMain.setVisible(true);
		
		
		elev1.runMoveThd();
		elev1.runCheckThd();
		
		
		while(true){
		
		try {
			
			elev1.upQueue.addLast(1);
			System.out.println("push 1");
			Thread.sleep(3000);
			
			elev1.upQueue.addLast(2);
			System.out.println("push 2");
			Thread.sleep(3000);
			
			elev1.upQueue.addLast(4);
			System.out.println("push 4");
			Thread.sleep(3000);
			
			elev1.upQueue.addLast(6);
			System.out.println("push 6");
			Thread.sleep(3000);
			
			elev1.downQueue.addLast(4);
			System.out.println("push 4");
			Thread.sleep(3000);
			
			elev1.downQueue.addLast(2);
			System.out.println("push 2");
			Thread.sleep(3000);
			
			elev1.downQueue.addLast(1);
			System.out.println("push 1");
			Thread.sleep(3000);
			
			
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		}

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
		btnf1.setActionCommand("1");

		//btn2
		btnf2.setIcon(new ImageIcon("2f.png"));
		btnf2.setBorderPainted(false);
		btnf2.setContentAreaFilled(false);
		btnf2.setFocusPainted(false);
		btnf2.setActionCommand("2");

		//btn3
		btnf3.setIcon(new ImageIcon("3f.png"));
		btnf3.setBorderPainted(false);
		btnf3.setContentAreaFilled(false);
		btnf3.setFocusPainted(false);
		btnf3.setActionCommand("3");

		//btn4
		btnf4.setIcon(new ImageIcon("4f.png"));
		btnf4.setBorderPainted(false);
		btnf4.setContentAreaFilled(false);
		btnf4.setFocusPainted(false);
		btnf4.setActionCommand("4");

		//btn5
		btnf5.setIcon(new ImageIcon("5f.png"));
		btnf5.setBorderPainted(false);
		btnf5.setContentAreaFilled(false);
		btnf5.setFocusPainted(false);
		btnf5.setActionCommand("5");

		//btn6
		btnf6.setIcon(new ImageIcon("6f.png"));
		btnf6.setBorderPainted(false);
		btnf6.setContentAreaFilled(false);
		btnf6.setFocusPainted(false);
		btnf6.setActionCommand("6");

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
			System.out.println(e.getActionCommand());
			int levNum = Integer.parseInt(e.getActionCommand());
//			JLabel hum = new JLabel();
//			int i = 0;
//			ImageIcon humCov = new ImageIcon("hum"+i+"_0.png");
//			hum.setIcon(humCov);
//			hum.setBounds(100, 100, 100, 100);
			
			JLabel tmpHum = new Humans(levNum).GetLblHum();
			frmMain.add(tmpHum, "Center");
			frmMain.repaint();
		}
		
	}

}
