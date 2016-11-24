package GUI;


import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class Remote extends JFrame{
	
	JFrame frmRemote;
	GridBagConstraints gbc;
	Insets iss;
	ArrayList<JButton> btnFList;

	
	public Remote(int idxHum, int idxStdFloor)
	{
		Font fNanum = new Font("³ª´®¹Ù¸¥°íµñ UltraLight", Font.PLAIN, 15);
		Font fDot = new Font("Power Pixel-7", Font.BOLD, 20);
		iss = new Insets(20, 30, 40, 10);
		gbc = new GridBagConstraints();
		gbc.fill = gbc.VERTICAL;
		gbc.insets = iss;
		btnFList = new ArrayList<JButton>();
		
		gbc.weightx = 0.5;
		frmRemote = new JFrame("Remote");
		frmRemote.setBounds(1480, 250, 400, 400);
		frmRemote.setLayout(new GridBagLayout());
		
		JPanel pnl1 = new JPanel();
		gbc.gridx = 0; gbc.gridy = 0;
		gbc.weighty = 0.1;
		
		frmRemote.add(pnl1, gbc);

		JLabel lblTitle = new JLabel("Remote Controller");
		lblTitle.setFont(fDot);
		pnl1.add(lblTitle);
		
		JPanel pnl2 = new JPanel();
		gbc.gridx = 0; gbc.gridy = 1;
		gbc.weighty = 0.9;
		frmRemote.add(pnl2, gbc);
		pnl2.setLayout(new GridLayout(2, 4, 10, 10));	
		
		JButton btnFlr1 = new JButton(new ImageIcon("btnf1.png"));
		btnFlr1.setBorderPainted(false); btnFlr1.setContentAreaFilled(false);
		btnFlr1.setFocusPainted(false); btnFlr1.setOpaque(false);
		
		JButton btnFlr2 = new JButton(new ImageIcon("btnf2.png")); 
		btnFlr2.setBorderPainted(false); btnFlr2.setContentAreaFilled(false);
		btnFlr2.setFocusPainted(false); btnFlr2.setOpaque(false);
		
		JButton btnFlr3 = new JButton(new ImageIcon("btnf3.png")); 
		btnFlr3.setBorderPainted(false); btnFlr3.setContentAreaFilled(false);
		btnFlr3.setFocusPainted(false); btnFlr3.setOpaque(false);
		
		JButton btnFlr4 = new JButton(new ImageIcon("btnf4.png")); 
		btnFlr4.setBorderPainted(false); btnFlr4.setContentAreaFilled(false);
		btnFlr4.setFocusPainted(false); btnFlr4.setOpaque(false);
		
		JButton btnFlr5 = new JButton(new ImageIcon("btnf5.png")); 
		btnFlr5.setBorderPainted(false); btnFlr5.setContentAreaFilled(false);
		btnFlr5.setFocusPainted(false); btnFlr5.setOpaque(false);
		
		JButton btnFlr6 = new JButton(new ImageIcon("btnf6.png")); 
		btnFlr6.setBorderPainted(false); btnFlr6.setContentAreaFilled(false);
		btnFlr6.setFocusPainted(false); btnFlr6.setOpaque(false);
		
		JButton btnFlr7 = new JButton(new ImageIcon("btnf7.png")); 
		btnFlr7.setBorderPainted(false); btnFlr7.setContentAreaFilled(false);
		btnFlr7.setFocusPainted(false); btnFlr7.setOpaque(false);
		
		JButton btnFlr8 = new JButton(new ImageIcon("btnf8.png")); 
		btnFlr8.setBorderPainted(false); btnFlr8.setContentAreaFilled(false);
		btnFlr8.setFocusPainted(false); btnFlr8.setOpaque(false);
		
		btnFList.add(btnFlr1); btnFList.add(btnFlr2); btnFList.add(btnFlr3); btnFList.add(btnFlr4); 
		btnFList.add(btnFlr5); btnFList.add(btnFlr6); btnFList.add(btnFlr7); btnFList.add(btnFlr8);
		
		pnl2.add(btnFlr1); pnl2.add(btnFlr2); pnl2.add(btnFlr3); pnl2.add(btnFlr4);
		pnl2.add(btnFlr5); pnl2.add(btnFlr6); pnl2.add(btnFlr7); pnl2.add(btnFlr8);
		
		frmRemote.getContentPane().setBackground(Color.WHITE);
		pnl1.setBackground(Color.WHITE);
		pnl2.setBackground(Color.WHITE);
		
		frmRemote.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frmRemote.setVisible(true);
	}
	
}

