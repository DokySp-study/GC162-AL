package GUI;

import java.awt.*;
import javax.swing.*;

import java.util.ArrayList;
import java.util.Random;

public class Human extends Thread{

	Random rnd = new Random();
	public int nStFlr;
	public JLabel lblHum = new JLabel();
	public ImageIcon icnHumMov = new ImageIcon();
	public ImageIcon icnHumStop = new ImageIcon();
	public int y;
	public int nRemoteX = 150;
	public int nStopX; //����� ���ٰ� ���� ��ġ(���������͸� ��� Ÿ�Ŀ� ���� �޶�����)
	public int nBtw = 120; //���� ����
	public int IDElev; //���������� ���̵�
	public boolean bWalk; //T: ������, F:����
	public boolean bRmt; //T: ��������, F:�����Ȱ�
	public JLabel lblArv = new JLabel(); //������ �� ��Ÿ��
	public int nArv;
	
	public Human()
	{
		int nRan = rnd.nextInt(5);
		
		if(nRan==0){
			icnHumMov = new ImageIcon("hum1_move.gif");
			icnHumStop = new ImageIcon("hum1.png");
			lblHum.setIcon(icnHumMov);
		}
		else if(nRan==1){
			icnHumMov = new ImageIcon("hum2_move.gif");
			icnHumStop = new ImageIcon("hum2.png");
			lblHum.setIcon(icnHumMov);
		}
		else if(nRan==2){
			icnHumMov = new ImageIcon("hum3_move.gif");
			icnHumStop = new ImageIcon("hum3.png");
			lblHum.setIcon(icnHumMov);
		}
		else if(nRan==3){
			icnHumMov = new ImageIcon("hum4_move.gif");
			icnHumStop = new ImageIcon("hum4.png");
			lblHum.setIcon(icnHumMov);
		}
		else {
			icnHumMov = new ImageIcon("hum5_move.gif");
			icnHumStop = new ImageIcon("hum5.png");
			lblHum.setIcon(icnHumMov);
		}
		
		bWalk = false;
	}
	
	public void SetElevID(int ID) {
		this.IDElev = ID;
	}

	public Human GetHum(){
		return this;
	}

	public JLabel GetLblArv(){
		return this.lblArv;
	}
	
	public JLabel GetLbl() {
		return this.lblHum;
	}

	public void SetBMove(boolean b) {
		this.bWalk = b;
	}

	public void SetStopX(int nX) {
		this.nStopX = nX;
	}


	public void HumMove() {

		this.SetBMove(true);

		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				Remote rmt = null;
				JFrame frm = new JFrame();
				try{
					while(true){
						Point p = lblHum.getLocation();
						lblHum.setLocation(p.x+10, p.y);
						
						Thread.sleep(300); 

						if(p.x > nRemoteX){ //�� ����
							lblHum.setIcon(icnHumStop);
							rmt = new Remote();
							frm = rmt.GetFrm();
							frm.setVisible(true);
							SetBMove(false);
							break;
						}
					}
					while(rmt.bChoice == false){
						
						Thread.sleep(200);
						
					}
					nArv = rmt.GetFlr();
					frm.dispose();
					HumMoveRemain();
					lblHum.setIcon(icnHumMov);
					
				}catch(Exception ae){

				}
			}
		});

		t.start();
		SetBMove(true);
	}


	public void HumMoveRemain() {
		
		this.SetBMove(true);
		lblArv.setText(nArv+"��");
		
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try{
					Point p = lblHum.getLocation();
					Point p2 = lblArv.getLocation();
					
					while(true){
						if(bWalk == true) {
							p = lblHum.getLocation();
							lblHum.setLocation(p.x+10, p.y);		
							
							p2 = lblArv.getLocation();
							lblArv.setLocation(p.x+10, p.y);
							
							Thread.sleep(200); 
							
							if(p.x > nStopX){ //���� ������ �� ����
								lblHum.setIcon(icnHumStop);
								break;
							}
						}
					}
				}catch(Exception ae){

				}
			}
		});
		t.start();
	}

	public void SetStFlr(int nFlr) {
		this.nStFlr = nFlr;
		this.y = 620;
		int i;
		for(i=1; i<nFlr; i++)
			this.y -= this.nBtw;
	}
}
