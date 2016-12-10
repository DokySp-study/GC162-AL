package GUI;

import javax.swing.*;

import java.awt.Point;
import java.util.Random;



public class Humans extends Thread{
	//Human label
	JLabel lblHum = new JLabel("");
	int humSkinNo;
	boolean isMoving;
	
	private final int LEV_INT = 119;
	private final int LEV_FIRST = 610;
	private final int LEV_LAST = 25;
	
	

	public Humans(int getLev) {
		
		isMoving = false;
		
		Random ran = new Random();
		humSkinNo = ran.nextInt(5);
		ImageIcon humCov = new ImageIcon("hum"+humSkinNo+"_0.png");
		
		lblHum.setBounds(65, convLevToY(getLev), 100, 100);
		lblHum.setIcon(humCov);
		new thdLive().start();
		
	}
	
	public JLabel GetLblHum() {
		return this.lblHum;
	}
	
	
	class thdLive extends Thread {
		Point p;
		public void run() {
			
			try{
				p = lblHum.getLocation();
				Thread.sleep(500);
				
				isMoving = true;
				new thdAni().start();
				
				
				while(true){
					
					for(int i = 65; i < 400; i++){
						//lblHum.setLocation(i, lblHum.getY());
						lblHum.setLocation(p.x+i, p.y);
						Thread.sleep(30);
						System.out.println(3);
					}
				}
				
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
			
			
		}
	}
	
	
	class thdAni extends Thread {
		public void run() {
			while(true){
				if(isMoving){
					try{
						lblHum.setIcon(new ImageIcon("hum"+humSkinNo+"_1.png"));
						Thread.sleep(500);
						lblHum.setIcon(new ImageIcon("hum"+humSkinNo+"_0.png"));
						Thread.sleep(500);
					}
					catch(InterruptedException e){
						e.printStackTrace();
					}
				}
				else{
					lblHum.setIcon(new ImageIcon("hum"+humSkinNo+"_0.png"));
				}
				
			}
		}
	}
	
	
	
	
	private int convLevToY(int Lev){
		return ((6 - Lev) * LEV_INT) + LEV_LAST;
	}
	
}
