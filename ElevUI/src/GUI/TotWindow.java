package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TotWindow{
	public JFrame frm;
	public JPanel pnlBtnFlr = new JPanel();
	
	public static Elevator elev1 = new Elevator(1);
	public static Elevator elev2 = new Elevator(2);
	public static Elevator elev3 = new Elevator(3);
	public static JLabel lblGb = new JLabel("");
	
	public static JButton btn1f, btn2f, btn3f, btn4f, btn5f, btn6f;
	public static JButton btnElev1, btnElev2, btnElev3;
	
	public static void main(String[] args) {
		TotWindow tw = new TotWindow();
	}

	public TotWindow()
	{
		frm = new JFrame("Elevator");
		frm.setBounds(50, 50, 1000, 800);
		frm.setContentPane(new JLabel(new ImageIcon("elevbg.png")));

		frm.setLayout(new BorderLayout());
		//frm.getContentPane().setBackground(Color.CYAN);
		
		//elev
		elev1.GetLbl().setBounds(675, 610, 63, 90);
		elev2.GetLbl().setBounds(785, 610, 63, 90);
		elev3.GetLbl().setBounds(895, 610, 63, 90);

		frm.add(elev1.GetLbl(), "Center");
		frm.add(elev2.GetLbl(), "Center");
		frm.add(elev3.GetLbl(), "Center");
		frm.add(lblGb, "Center");
		
		
		//btnPnl
		frm.add(pnlBtnFlr, "South");
		pnlBtnFlr.setLayout(new GridLayout(1, 6, 10, 10));

		
		//btn
		AddBtn();
		AddElevBtn();
		
		
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setVisible(true);
	}

	public void AddBtn()
	{
		ActionListener actionHandler = new ActionEventHandler();

		btn1f = new JButton();
		btn1f.addActionListener(actionHandler);
		btn1f.setIcon(new ImageIcon("1f.png"));
		btn1f.setBorderPainted(false);
		btn1f.setContentAreaFilled(false);
		btn1f.setFocusPainted(false);
		pnlBtnFlr.add(btn1f);

		btn2f = new JButton();
		btn2f.addActionListener(actionHandler);
		btn2f.setIcon(new ImageIcon("2f.png"));
		btn2f.setBorderPainted(false);
		btn2f.setContentAreaFilled(false);
		btn2f.setFocusPainted(false);
		pnlBtnFlr.add(btn2f);

		btn3f = new JButton();
		btn3f.addActionListener(actionHandler);
		btn3f.setIcon(new ImageIcon("3f.png"));
		btn3f.setBorderPainted(false);
		btn3f.setContentAreaFilled(false);
		btn3f.setFocusPainted(false);
		pnlBtnFlr.add(btn3f);

		btn4f = new JButton();
		btn4f.addActionListener(actionHandler);
		btn4f.setIcon(new ImageIcon("4f.png"));
		btn4f.setBorderPainted(false);
		btn4f.setContentAreaFilled(false);
		btn4f.setFocusPainted(false);
		pnlBtnFlr.add(btn4f);

		btn5f = new JButton();
		btn5f.addActionListener(actionHandler);
		btn5f.setIcon(new ImageIcon("5f.png"));
		btn5f.setBorderPainted(false);
		btn5f.setContentAreaFilled(false);
		btn5f.setFocusPainted(false);
		pnlBtnFlr.add(btn5f);

		btn6f = new JButton();
		btn6f.addActionListener(actionHandler);
		btn6f.setIcon(new ImageIcon("6f.png"));
		btn6f.setBorderPainted(false);
		btn6f.setContentAreaFilled(false);
		btn6f.setFocusPainted(false);
		pnlBtnFlr.add(btn6f);

	}

	public void AddElevBtn() {
		ActionListener actionHandler = new ActionEventHandler();
		
		btnElev1 = new JButton();
		btnElev2 = new JButton();
		btnElev3 = new JButton();
		
		btnElev1.setIcon(new ImageIcon("e1.png"));
		btnElev1.setBorderPainted(false);
		btnElev1.setContentAreaFilled(false);
		btnElev1.setFocusPainted(false);
		btnElev1.addActionListener(actionHandler);
		pnlBtnFlr.add(btnElev1); 
		
		btnElev2.setIcon(new ImageIcon("e2.png"));
		btnElev2.setBorderPainted(false);
		btnElev2.setContentAreaFilled(false);
		btnElev2.setFocusPainted(false);
		btnElev2.addActionListener(actionHandler);
		pnlBtnFlr.add(btnElev2); 
		
		btnElev3.setIcon(new ImageIcon("e3.png"));
		btnElev3.setBorderPainted(false);
		btnElev3.setContentAreaFilled(false);
		btnElev3.setFocusPainted(false);
		btnElev3.addActionListener(actionHandler);
		pnlBtnFlr.add(btnElev3);
	}
	
	class ActionEventHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			
			if(e.getSource() == btn1f){
				Human h = new Human();
				h.SetStFlr(1);
				
				
				h.GetLbl().setBounds(80, h.y, 63, 90);
				h.GetLblArv().setBounds(80, h.y-30, 63, 90);
				
				h.SetStopX(elev1.GetLbl().getLocation().x);
				h.HumMove();
				
				frm.add(h.GetLbl());
				frm.add(h.GetLblArv());
				frm.repaint();
				
			} else if(e.getSource() == btn2f){
				Human h = new Human();
				h.SetStFlr(2);
				
				h.GetLbl().setBounds(80, h.y, 63, 90);
				h.SetStopX(elev2.GetLbl().getLocation().x);
				h.HumMove();
				frm.add(h.GetLbl());
				frm.repaint();
				
			} else if(e.getSource() == btn3f) {
				Human h = new Human();
				h.SetStFlr(3);
				
				h.GetLbl().setBounds(80, h.y, 63, 90);
				h.SetStopX(elev3.GetLbl().getLocation().x);
				h.HumMove();
				frm.add(h.GetLbl());
				frm.repaint();
				
			} else if(e.getSource() == btn4f) {
				Human h = new Human();
				h.SetStopX(elev3.GetLbl().getLocation().x);
				h.SetStFlr(4);
				
				h.GetLbl().setBounds(80, h.y, 93, 90);
				h.HumMove();
				frm.add(h.GetLbl());
				frm.repaint();
				
			} else if(e.getSource() == btn5f) {
				Human h = new Human();
				h.SetStFlr(5);
				
				h.GetLbl().setBounds(80, h.y, 63, 90);
				h.SetStopX(elev1.GetLbl().getLocation().x);
				h.HumMove();
				frm.add(h.GetLbl());
				frm.repaint();
				
			} else if(e.getSource() == btn6f) {
				Human h = new Human();
				h.SetStFlr(6);
				
				h.GetLbl().setBounds(80, h.y, 63, 90);
				h.SetStopX(elev2.GetLbl().getLocation().x);
				h.HumMove();
				frm.add(h.GetLbl());
				frm.repaint();
			}
			if(e.getSource() == btnElev1) {
				elev1.ElevMove(elev1.lblElev.getLocation().x, 20);
				btnElev1.setEnabled(false);
				
			} else if(e.getSource() == btnElev2) {
				elev2.ElevMove(elev2.lblElev.getLocation().x, 700);
				btnElev2.setEnabled(false);
				
			} else if(e.getSource() == btnElev3) {
				elev3.ElevMove(elev3.lblElev.getLocation().x, 130);
				btnElev3.setEnabled(false);
			} 
			

		}
	}
}
