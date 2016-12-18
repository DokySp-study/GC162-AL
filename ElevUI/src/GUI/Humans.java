package GUI;

import javax.swing.*;

import System.Core;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;



public class Humans extends Thread{
	//Human label
	JLabel lblHum;
	int humSkinNo;
	boolean isMoving;
	private int startLev;
	public int targetLev;
	
	long start;
	long end;
	
	private final int LEV_INT = 119;
	private final int LEV_FIRST = 610;
	private final int LEV_LAST = 25;
	
	

	public Humans(int getLev) {
		
		startLev = getLev;
		
		targetLev = 0;
		lblHum = new JLabel("<html><h1><b>&nbsp;&nbsp;</b></h1></html>");
		lblHum.setForeground(Color.WHITE);
		lblHum.setHorizontalTextPosition(JLabel.LEFT);
		lblHum.setVerticalTextPosition(JLabel.TOP);
		
		isMoving = false;
		
		Random ran = new Random();
		humSkinNo = ran.nextInt(5);
		ImageIcon humCov = new ImageIcon("hum"+humSkinNo+"_0.png");
		
		lblHum.setBounds(60, convLevToY(getLev), 100, 100);
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
				int elevNum = 0;
				isMoving = true;
				new thdAni().start();
				
					
				
				
				
				
					for(int i = 60; i < 480; i++){
						lblHum.setLocation(i, p.y);
						Thread.sleep(5);
					}
					
					if(Window.autoMode){
						isMoving = false;
						Random ran = new Random();
						
						
						while(true){
							targetLev = ran.nextInt(6) + 1;
							if(targetLev != startLev)
								break;
						}
						
						
					}
					else{
						isMoving = false;
						Remotes a = new Remotes();
						while(targetLev == 0){
							targetLev = a.getLevFromPad();
							System.out.print("");
						}
					}
					
					start = System.currentTimeMillis();
					
					Window.floorHumStack[startLev-1].add(getHumans());
					
					
					lblHum.setText("<html><h1><b>"+targetLev+"</b></h1></html>");
					
					
					
					elevNum = Window.coreSystem.getElevNum(startLev, targetLev);
					//System.out.println(elevNum);
					
					//elevNum = 1;
					int destElev = 0;
					
					
					
					
					
					if(elevNum == 1){
						//if(1번 엘베 도착 하고 문 열리면 )
						while(!(Window.elev1.isDoorOpen && Window.elev1.currLevel == startLev))
							System.out.print("");
							
						Window.elev1.HumStack.add(getHumans());
						destElev = Window.ELEV_FIR - 10;
					}
					else if(elevNum == 2){
						while(!(Window.elev2.isDoorOpen && Window.elev2.currLevel == startLev))
							System.out.print("");
						Window.elev2.HumStack.add(getHumans());
						destElev = Window.ELEV_SEC - 10;
					}
					else if(elevNum == 3){
						while(!(Window.elev3.isDoorOpen && Window.elev3.currLevel == startLev))
							System.out.print("");
						Window.elev3.HumStack.add(getHumans());
						destElev = Window.ELEV_TRD - 10;
					}
					else{
						for(int i = 479; i <= 1000; i++){
							lblHum.setLocation(i, p.y);
							Thread.sleep(5);
						}
						return;
					}
					
					isMoving = true;
					new thdAni().start();
					
					
					for(int i = 479; i <= destElev; i++){
						lblHum.setLocation(i, p.y);
						Thread.sleep(5);
					}
					
					lblHum.setVisible(false);
					
					
				
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
			finally{
				lblHum.setVisible(false);
			}
			
		}
	}
	
	
	public void thdLeaveStart(int inCurrElev, int inCurrLev){
		new thdLeave(inCurrElev, inCurrLev).start();
	}
	
	
	public class thdLeave extends Thread {
		
		private int currElev;
		private int currLev;
		
		public thdLeave(int inCurrElev, int inCurrLev){
			currElev = inCurrElev;
			currLev = inCurrLev;
		}
		
		public void run(){
			//after notify...
			end = System.currentTimeMillis();
			System.out.println("<time>" + (end-start) + "</time>");
			Window.totalMsec += (end-start);
			Window.totalUser++;
			System.out.println("<tot_time>" + Window.totalMsec + "</tot_time>");
			System.out.println("<tot_proc>" + Window.totalUser + "</tot_proc>");
			
			lblHum.setVisible(true);
			new thdAni().start();
			
			
			for(int i = currElev; i < 1000; i++){
				lblHum.setLocation(i, convLevToY(currLev));
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
			
		}
		
	}
	
	
	
	
	class thdAni extends Thread {
		public void run() {
			while(true){
				if(isMoving){
					try{
						lblHum.setIcon(new ImageIcon("hum"+humSkinNo+"_1.png"));
						Thread.sleep(300);
						lblHum.setIcon(new ImageIcon("hum"+humSkinNo+"_0.png"));
						Thread.sleep(300);
					}
					catch(InterruptedException e){
						e.printStackTrace();
					}
				}
				else{
					lblHum.setIcon(new ImageIcon("hum"+humSkinNo+"_0.png"));
					break;
				}
				
			}
		}
	}
	
	public Humans getHumans(){
		return this;
	}
	
	
	private int convLevToY(int Lev){
		return ((6 - Lev) * LEV_INT) + LEV_LAST;
	}
	
}
