package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Elevator extends Thread{
	public JLabel lblElev = new JLabel();
	public String strElev;
	public ImageIcon icnElev2;
	public ArrayList<ImageIcon> listIcnElev = new ArrayList<ImageIcon>();
	//0, 마지막= 엘베 기본, 1 ~ 마지막-1 열림 순서대로
	
	public boolean bMove; //움직이는중=T, 멈춰있음=F
	
	public Elevator(int idx) {
		ImageIcon icnElev;
		Image img, img2;
		
		if(idx == 1) {
			//elev1 이미지
			icnElev = new ImageIcon("elev1.png");
			img = icnElev.getImage();
			img2 = img.getScaledInstance(63, 90, java.awt.Image.SCALE_SMOOTH);
			icnElev2 = new ImageIcon(img2);
			
			strElev = "Elev1";
			lblElev.setIcon(icnElev2);
			
			
		} else if(idx == 2) {
			//elev2 이미지
			icnElev = new ImageIcon("elev2.png");
			img = icnElev.getImage();
			img2 = img.getScaledInstance(63, 90, java.awt.Image.SCALE_SMOOTH);
			icnElev2 = new ImageIcon(img2);
			
			strElev = "Elev2";
			lblElev.setIcon(icnElev2);
			
		} else if(idx == 3) {
			//elev3 이미지
			icnElev = new ImageIcon("elev3.png");
			img = icnElev.getImage();
			img2 = img.getScaledInstance(63, 90, java.awt.Image.SCALE_SMOOTH);
			icnElev2 = new ImageIcon(img2);
			
			strElev = "Elev3";
			lblElev.setIcon(icnElev2);
		}
		
		bMove = false;
		
		this.listIcnElev.add(icnElev2);
		this.IcnList(idx);
		this.listIcnElev.add(icnElev2);		
		
	}
	
	private void IcnList(int idx)
	{
		//listIcnElev
		//elev1_o1 ~ elev1_c4 (열때는 순서대로, 닫을때는 거꾸로)
		ImageIcon icnElev, icnElev2;
		Image img, img2;
		switch(idx) {
		case 1:
			//o1
			icnElev = new ImageIcon("elev1_o1.png");
			img = icnElev.getImage();
			img2 = img.getScaledInstance(63, 90, java.awt.Image.SCALE_SMOOTH);
			icnElev2 = new ImageIcon(img2);
			listIcnElev.add(icnElev2);
			
			//o2
			icnElev = new ImageIcon("elev1_o2.png");
			img = icnElev.getImage();
			img2 = img.getScaledInstance(63, 90, java.awt.Image.SCALE_SMOOTH);
			icnElev2 = new ImageIcon(img2);
			listIcnElev.add(icnElev2);
			
			//o3
			icnElev = new ImageIcon("elev1_o3.png");
			img = icnElev.getImage();
			img2 = img.getScaledInstance(63, 90, java.awt.Image.SCALE_SMOOTH);
			icnElev2 = new ImageIcon(img2);
			listIcnElev.add(icnElev2);
			
			//o4
			icnElev = new ImageIcon("elev1_o4.png");
			img = icnElev.getImage();
			img2 = img.getScaledInstance(63, 90, java.awt.Image.SCALE_SMOOTH);
			icnElev2 = new ImageIcon(img2);
			listIcnElev.add(icnElev2);
			break;
			
		case 2:
			//o1
			icnElev = new ImageIcon("elev2_o1.png");
			img = icnElev.getImage();
			img2 = img.getScaledInstance(63, 90, java.awt.Image.SCALE_SMOOTH);
			icnElev2 = new ImageIcon(img2);
			listIcnElev.add(icnElev2);
			
			//o2
			icnElev = new ImageIcon("elev2_o2.png");
			img = icnElev.getImage();
			img2 = img.getScaledInstance(63, 90, java.awt.Image.SCALE_SMOOTH);
			icnElev2 = new ImageIcon(img2);
			listIcnElev.add(icnElev2);
			
			//o3
			icnElev = new ImageIcon("elev2_o3.png");
			img = icnElev.getImage();
			img2 = img.getScaledInstance(63, 90, java.awt.Image.SCALE_SMOOTH);
			icnElev2 = new ImageIcon(img2);
			listIcnElev.add(icnElev2);
			
			//o4
			icnElev = new ImageIcon("elev2_o4.png");
			img = icnElev.getImage();
			img2 = img.getScaledInstance(63, 90, java.awt.Image.SCALE_SMOOTH);
			icnElev2 = new ImageIcon(img2);
			listIcnElev.add(icnElev2);
			
			break;
		case 3:
			//o1
			icnElev = new ImageIcon("elev3_o1.png");
			img = icnElev.getImage();
			img2 = img.getScaledInstance(63, 90, java.awt.Image.SCALE_SMOOTH);
			icnElev2 = new ImageIcon(img2);
			listIcnElev.add(icnElev2);
			
			//o2
			icnElev = new ImageIcon("elev3_o2.png");
			img = icnElev.getImage();
			img2 = img.getScaledInstance(63, 90, java.awt.Image.SCALE_SMOOTH);
			icnElev2 = new ImageIcon(img2);
			listIcnElev.add(icnElev2);
			
			//o3
			icnElev = new ImageIcon("elev3_o3.png");
			img = icnElev.getImage();
			img2 = img.getScaledInstance(63, 90, java.awt.Image.SCALE_SMOOTH);
			icnElev2 = new ImageIcon(img2);
			listIcnElev.add(icnElev2);
			
			//o4
			icnElev = new ImageIcon("elev3_o4.png");
			img = icnElev.getImage();
			img2 = img.getScaledInstance(63, 90, java.awt.Image.SCALE_SMOOTH);
			icnElev2 = new ImageIcon(img2);
			listIcnElev.add(icnElev2);
			
			break;
		}
	}

	public JLabel GetLbl() {
		return this.lblElev;
	}

	public Elevator GetElev() {
		return this;
	}


	public void ElevMove(int nSflr, int nEFlr) {

		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				int i;
				try{
					for(i=0; i<5; i++) //open
					{
						bMove = true;
						lblElev.setIcon(listIcnElev.get(i));
						
						Thread.sleep(200);
					}for(i=0; i<5; i++) //close
					{
						lblElev.setIcon(listIcnElev.get(4-i));
						Thread.sleep(200);
					}
					lblElev.setText(strElev);	

					//nSflr < nEFlr
					if(nSflr < nEFlr)
						while(true){

							Point p = lblElev.getLocation();
							lblElev.setLocation(p.x, p.y+1);						
							Thread.sleep(100); 
							if(p.y == nEFlr){
								break;
							}
						}

					//nSflr > nEFlr
					else 
						while(true){

							Point p = lblElev.getLocation();
							lblElev.setLocation(p.x, p.y-1);						
							Thread.sleep(100); 
							if(p.y == nEFlr){
								break;
							}
						}

					for(i=0; i<5; i++) //open
					{
						lblElev.setIcon(listIcnElev.get(i));
						Thread.sleep(200);
					}for(i=0; i<5; i++) //close
					{
						lblElev.setIcon(listIcnElev.get(4-i));
						Thread.sleep(200);
					}

					bMove = false;
				}catch(Exception ae){

				}
			}
		});
		t.start();
	}

	public boolean GetIsMove() {
		return this.bMove;
	}
}
