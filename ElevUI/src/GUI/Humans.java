package GUI;

import javax.swing.*;

import java.awt.Point;
import java.util.Random;



public class Humans extends Thread{
	//Human label
	JLabel lblHum = new JLabel("");
	
	private final int LEV_INT = 119;
	private final int LEV_FIRST = 610;
	private final int LEV_LAST = 25;
	
	

	public Humans(int getLev) {
		
		Random ran = new Random();
		int i = ran.nextInt(5);
		ImageIcon humCov = new ImageIcon("hum"+i+"_0.png");
		
		lblHum.setBounds(50, convLevToY(getLev), 100, 100);
		lblHum.setIcon(humCov);
		lblHum.setVisible(true);
		new thdLive().start();
		
	}
	
	public JLabel GetLblHum() {
		return this.lblHum;
	}
	
	
	class thdLive extends Thread {
		public void run() {
			while(true){
				System.out.println("I'm running!");
			}
		}
	}
	
	
	class thdLive extends Thread {
		public void run() {
			while(true){
				System.out.println("I'm running!");
			}
		}
	}
	
	
	
	
	private int convLevToY(int Lev){
		return ((6 - Lev) * LEV_INT) + LEV_LAST;
	}
	
}
