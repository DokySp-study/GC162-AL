package GUI;

import javax.swing.*;

import System.Core;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Window extends Thread{
	
	public static boolean autoMode = true;
	public static int frequency = 1000;
	public static int totalMsec = 0;
	public static int totalUser = 0;
	
	
	//메인 프레임
	public JFrame frmMain = new JFrame("Elevator");

	//창 아래에 배치할 버튼 목록(사람을 몇층에 놓을까)
	public JButton btnf1, btnf2, btnf3, btnf4, btnf5, btnf6;

	//엘리베이터
	public static Elevators elev1 = new Elevators();
	public static Elevators elev2 = new Elevators();
	public static Elevators elev3 = new Elevators();
	
	public static final int ELEV_FIR = 566;
	public static final int ELEV_SEC = 678;
	public static final int ELEV_TRD = 788;
	
	public static Core coreSystem = new Core();
	
	
	public static ArrayList<Humans>[] floorHumStack = new ArrayList[6];
	
	
	
	
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
		ImageIcon tmpIcn = new ImageIcon("elevbg.jpg");
		Image tmpImg = tmpIcn.getImage();
		tmpImg.getScaledInstance(1000, 780, Image.SCALE_FAST);
		tmpIcn = new ImageIcon(tmpImg);
		
		frmMain.setContentPane(new JLabel(tmpIcn));
		
		frmMain.setBounds(50, 50, 1000, 780);
		frmMain.setLayout(new BorderLayout());
		
		for(int i = 0; i < floorHumStack.length; i++)
			floorHumStack[i] = new ArrayList<Humans>();
		
		JPanel pnl = new JPanel();

		
		
		
		//listFloorY 초기화
		for(int i=0; i<6; i++)
			listFloorY.add(610 - nBt*i);

		//add elev
		elev1.GetLblElev().setBounds(ELEV_FIR, 610, 63, 90);
		elev2.GetLblElev().setBounds(ELEV_SEC, 610, 63, 90);
		elev3.GetLblElev().setBounds(ELEV_TRD, 610, 63, 90);

		frmMain.getContentPane().add(elev1.GetLblElev(), "Center");
		frmMain.getContentPane().add(elev2.GetLblElev(), "Center");
		frmMain.getContentPane().add(elev3.GetLblElev(), "Center");
		frmMain.getContentPane().add(gb, "Center");
		
		
		
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
		
		elev2.runMoveThd();
		elev3.runMoveThd();
		//elev1.runCheckThd();
		
		
		while(autoMode){
			
			try {
				Random rand = new Random();
				Thread.sleep(frequency);
				//makePerson(rand.nextInt(6)+1);
				makePerson(1);
				
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
			//System.out.println(e.getActionCommand());
			int levNum = Integer.parseInt(e.getActionCommand());
			makePerson(levNum);
		}
		
	}
	
	
	
	private void makePerson(int levNum){

		JLabel hum = new JLabel();
		ImageIcon humCov = new ImageIcon();
		hum.setIcon(humCov);
		hum.setBounds(100, 100, 100, 100);
		
		JLabel tmpHum = new Humans(levNum).GetLblHum();
		frmMain.getContentPane().add(tmpHum, "Center");
		frmMain.getContentPane().add(hum, "Center");
		frmMain.repaint();
		
	}

}
