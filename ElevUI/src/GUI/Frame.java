package GUI;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.imageio.ImageIO;


public class Frame extends JFrame {
	
	public static JFrame frm;
	public static JFrame frmRemote;
	public GridBagConstraints gbc;
	
	public static void main(String[] args)
	{
		Frame wdw = new Frame();
		Remote rmt = new Remote(0, 0);
	}
	
	public Frame()
	{
		
		frm = new JFrame("Elevator");
		frm.setBounds(120, 120, 1185, 800);
		frm.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frm.setLayout(new GridBagLayout());
		
		Elevator elev0 = new Elevator(0);
		JLabel lblElev0;
		
		Elevator elev1 = new Elevator(1);
		JLabel lblElev1;
		
		Elevator elev2 = new Elevator(2);
		JLabel lblElev2;
		//elev 3대 몰아놓기
		try {
			frm.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("elevbg.jpg")))));
			lblElev0 = new JLabel(new ImageIcon(ImageIO.read(new File(elev0.GetImg()))));
			lblElev0.setBounds(150, 600, 95, 150);
			//setBounds, graphics로 옮기기
			
			lblElev1 = new JLabel(new ImageIcon(ImageIO.read(new File(elev1.GetImg()))));
			lblElev1.setBounds(545, 600, 95, 150);
			
			lblElev2 = new JLabel(new ImageIcon(ImageIO.read(new File(elev2.GetImg()))));
			lblElev2.setBounds(940, 600, 95, 150);
			
			frm.add(lblElev0);
			frm.add(lblElev1);
			frm.add(lblElev2);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
		frm.setVisible(true);
	}
}
