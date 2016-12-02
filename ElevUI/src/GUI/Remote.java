package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class Remote extends Thread{
	public JFrame frm = new JFrame("Remote");
	public JPanel pnl = new JPanel();
	public JButton btn1f = new JButton();
	public JButton btn2f = new JButton(); 
	public JButton btn3f = new JButton(); 
	public JButton btn4f = new JButton(); 
	public JButton btn5f = new JButton(); 
	public JButton btn6f = new JButton(); 
	public int nEndFlr;
	public boolean bChoice;
	public JFrame GetRemote() {
		return this.frm;
	}
	
	public Remote() {
		frm = new JFrame("Remote");
		JLabel lblTitle = new JLabel(new ImageIcon("rmtTitle.png"));
		
		frm.setBounds(800, 100, 300, 300);
		frm.setLayout(new BorderLayout());
	
		//title
		frm.add(lblTitle, "North");
		frm.add(pnl, "Center");
		
		//button
		AddBtn();
			
		bChoice = false;
	}
	
	public void AddBtn() {
		pnl.setLayout(new GridBagLayout());
		pnl.setBackground(Color.white);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = gbc.BOTH;
		gbc.weighty = 1.0; gbc.weightx = 1.0;
		
		//action
		ActionListener actionHandler = new ActionEventHandler();
		
		gbc.gridx = 0; gbc.gridy = 0;
		btn1f.setIcon(new ImageIcon("btnf1.png"));
		btn1f.setBorderPainted(false);
		btn1f.setContentAreaFilled(false);
		btn1f.setFocusPainted(false);
		pnl.add(btn1f, gbc);
		
		
		gbc.gridx = 1; gbc.gridy = 0;
		pnl.add(btn2f, gbc); 
		btn2f.setIcon(new ImageIcon("btnf2.png"));
		btn2f.setBorderPainted(false);
		btn2f.setContentAreaFilled(false);
		btn2f.setFocusPainted(false);
		
		
		gbc.gridx = 2; gbc.gridy = 0;
		pnl.add(btn3f, gbc);
		btn3f.setIcon(new ImageIcon("btnf3.png"));
		btn3f.setBorderPainted(false);
		btn3f.setContentAreaFilled(false);
		btn3f.setFocusPainted(false);
		
		gbc.gridx = 0; gbc.gridy = 1;
		pnl.add(btn4f, gbc); 
		btn4f.setIcon(new ImageIcon("btnf4.png"));
		btn4f.setBorderPainted(false);
		btn4f.setContentAreaFilled(false);
		btn4f.setFocusPainted(false);
		
		gbc.gridx = 1; gbc.gridy = 1;
		pnl.add(btn5f, gbc); 
		btn5f.setIcon(new ImageIcon("btnf5.png"));
		btn5f.setBorderPainted(false);
		btn5f.setContentAreaFilled(false);
		btn5f.setFocusPainted(false);
		
		gbc.gridx = 2; gbc.gridy = 1;
		pnl.add(btn6f, gbc);
		btn6f.setIcon(new ImageIcon("btnf6.png"));
		btn6f.setBorderPainted(false);
		btn6f.setContentAreaFilled(false);
		btn6f.setFocusPainted(false);
		
		btn1f.addActionListener(actionHandler);
		btn2f.addActionListener(actionHandler);
		btn3f.addActionListener(actionHandler);
		btn4f.addActionListener(actionHandler);
		btn5f.addActionListener(actionHandler);
		btn6f.addActionListener(actionHandler);
	}
	
	class ActionEventHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			if(e.getSource() == btn1f) {
				nEndFlr = 1;
			} else if(e.getSource() == btn2f) {
				nEndFlr = 2;
			} else if(e.getSource() == btn3f) {
				nEndFlr = 3;
			} else if(e.getSource() == btn4f) {
				nEndFlr = 4;
			} else if(e.getSource() == btn5f) {
				nEndFlr = 5;
			} else if(e.getSource() == btn6f) {
				nEndFlr = 6;
			}
			
			bChoice = true;
		}
		
	}
	
	public int GetFlr(){
		return this.nEndFlr;
	}
	
	public JFrame GetFrm() {
		return this.frm;
	}
	
}
