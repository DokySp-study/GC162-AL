package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Humans extends Thread{
	//Human label
	JLabel lblHum = new JLabel("");
	String strTemp;

	//Human Image
	ImageIcon icnStop;
	ImageIcon icnMove;

	//MoveSetting & run
	public int idxRun;

	//층 선택할 위치. 중간에 한번 멈춰야 할 위치
	public int nRemotePosX = 500; 

	//내가 탈 엘리베이터의 위치
	public int nElevPosX;

	//내가 타고 있는 엘리베이터의 index
	public int idxElev;

	//내가 탄 층, 내가 내릴 층
	public int nSflr, nEflr;  //좌표

	//사이 간격
	int nBt = 120;

	//y 좌표
	public int y;

	//모든 human이 가지는 unique한 키
	static public int idxKeyChoice = 0; //얘는 만들어주는 애고(모든 오브젝트가 공유하니까 얘를 사용하면 안됨!)
	public int idxUnique; //얘가 idxKeyChoice를 로컬로 저장한다. 그러니까 휴먼의 실제 유니크키는 이거!


	//lblHum위치
	public Point p;

	public Humans() {

		Random ran = new Random();
		int i = ran.nextInt(5);
		switch(i)
		{
		case 0:
			icnStop = new ImageIcon("hum1.png");
			icnMove = new ImageIcon("hum1_move.gif");
			break;
		case 2:
			icnStop = new ImageIcon("hum2.png");
			icnMove = new ImageIcon("hum2_move.gif");
			break;
		case 1:
			icnStop = new ImageIcon("hum3.png");
			icnMove = new ImageIcon("hum3_move.gif");
			break;
		case 3:
			icnStop = new ImageIcon("hum4.png");
			icnMove = new ImageIcon("hum4_move.gif");
			break;
		case 4:
			icnStop = new ImageIcon("hum5.png");
			icnMove = new ImageIcon("hum5_move.gif");
			break;

		}

		lblHum.setIcon(icnMove);

		System.out.print("Create human "+strTemp);

		idxKeyChoice ++;
		this.idxUnique = idxKeyChoice;
	}

	//내가 타는 층 설정
	public void SetStartFlr(int n) {

		nSflr = n;
		System.out.println("[h]Set floor "+nSflr);
	}

	//case마다 함수를 따로두는건 어때? 오버라이딩?
	//case 0 - move
	public void MoveSetting(int idx, int posX) {
		idxRun = idx;
		nElevPosX = posX;
		System.out.println("[h]Move Setting "+idxRun+", "+nElevPosX);
	}
	
	//case 1 - 엘베 내림
	public void MoveSetting(int idx, Point _p) {
		idxRun = idx;
		p = _p;
	}

	public void run() {

		Remotes rmt;
		JFrame frmRmt;
		int i;

		switch(idxRun){

		//엘리베이터까지 움직이고, 원하는 층을 선택한다(리모컨) 그리고 이어서 이동한다 (해당 위치까지), 그리고 몇초뒤에 사라진다
		case 0:
			try{
				System.out.println("[h]move, case 0 ("+idxRun+")");
				p = lblHum.getLocation();
				while(true)
				{
					Thread.sleep(200);
					lblHum.setLocation(p.x+20, p.y);
					if(nRemotePosX > p.x)
						p = lblHum.getLocation();

					else break;
				}

				//리모컨
				rmt = new Remotes();
				frmRmt = rmt.GetFrame();
				frmRmt.setVisible(true);
				lblHum.setIcon(icnStop);
				while(rmt.nEflr < 1){
					Thread.sleep(200);
				}

				nEflr = rmt.nEflr - 1;
				frmRmt.dispose();

				//잠시 대기
				for(i=0; i<3; i++){
					try {
						Thread.sleep(200);
						lblHum.setIcon(icnStop);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				//이어서 움직임
				lblHum.setIcon(icnMove);
				while(true)
				{
					Thread.sleep(200);
					lblHum.setLocation(p.x+20, p.y);
					if(nElevPosX > p.x)
						p = lblHum.getLocation();

					else break;
				}

				//조금 기다리고
				for(i=0; i<3; i++){
					try {
						Thread.sleep(200);
						lblHum.setIcon(icnStop);
					} catch (InterruptedException e) {
						e.printStackTrace();

					}
				}

				//사라진다
			}catch(Exception e) {
				e.printStackTrace();
			}
			break;


			//엘리베이터에서 내린다
		case 1:
			System.out.println("[h]out, case 1 ("+idxRun+")");
			//p = lblHum.getLocation();
			//lblHum.setLocation(p.x-20, 610-nEflr*nBt);
			
			//lblHum.setLocation(610, 610-nEflr*nBt);
			
			//곧 사라질꺼
			lblHum.setBackground(Color.WHITE);

			//조금 기다리고
			for(i=0; i<3; i++){
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();

				}
			}

			//사라진다
			lblHum.setVisible(false);
			break;

		}
	}

	public void SetSflr(int eflr) { //eflr: end floor
		this.nEflr = eflr;
		this.y = 620;
		int i;
		for(i=1; i<nEflr; i++)
			this.y -= this.nBt;
		System.out.println(this.y);
	}

	public JLabel GetLblHum(){

		return this.lblHum;
	}

}
