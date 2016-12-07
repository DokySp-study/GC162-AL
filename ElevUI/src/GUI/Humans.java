package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import System.Operations;
import System.EQ;

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

		idxKeyChoice ++;
		this.idxUnique = idxKeyChoice;
	}

	//내가 타는 층 설정
	public void SetStartFlr(int n) {

		nSflr = n;
	}

	//case마다 함수를 따로두는건 어때? 오버라이딩?
	//case 1 - 리모컨까지
	//case 3 - 엘베 내림
	public void MoveSetting(int idx) {
		//idx == 1
		idxRun = idx;
	}

	//case 2 - move
	//case1에서 엘베를 고른 후, posX(엘리베이터 위치)까지 이동하게 한다.
	public void MoveSetting(int idx, int posX) {
		//idx == 2
		idxRun = idx;
		nElevPosX = posX;
	}

	/*
	//case 3 - 엘베 내림
	public void MoveSetting(int idx, Point _p) {
		idxRun = idx;
		p = _p;
	}*/

	public void run() {

		Remotes rmt;
		JFrame frmRmt;
		int i;
		Operations o = new Operations();
		
		switch(idxRun){


		//리모컨 선택 까지만 (엘베까지는 안감)
		case 1:
			try{
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

				//유저가 내릴 층을 받음
				nEflr = rmt.nEflr;
				
				//어떤 엘리베이터를 내려줄지 골랐음
				idxElev = o.Choice(nSflr, nEflr);
				System.out.println(">>탈 엘베: "+idxElev);
				
				frmRmt.dispose();
				
				//해당 index에 맞는 엘리베이터의 큐에 추가해준다
				if(idxElev == 1) { //elev1
					System.out.print("\n>>q1: ");
					Window.q1.Add(nEflr);
					
					
				}else if(idxElev == 2) { //elev2
					System.out.print("\n>>q2: ");
					Window.q2.Add(nEflr);
					
				}else if(idxElev == 3) { //elev3
					System.out.print("\n>>q3: ");
					Window.q3.Add(nEflr);
					
				}
				System.out.println(">>"+nEflr+" 까지 이동");


				//잠시 대기
				for(i=0; i<3; i++){
					try {
						Thread.sleep(200);
						lblHum.setIcon(icnStop);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}



			}catch(Exception e) {
				e.printStackTrace();
			}
			break;


		//리모컨~엘리베이터까지 이동
		//필요한 변수: idx(2), ev의 좌표
		case 2:
			try{
				System.out.print(">>[h.case2]Human"+lblHum.getLocation().x+", Elev: ");
				System.out.println(nElevPosX);
				//이어서 움직임
				lblHum.setIcon(icnMove);
				while(true)
				{
					Thread.sleep(200);
					p = lblHum.getLocation(); //
					lblHum.setLocation(p.x+20, p.y);
					if(nElevPosX > p.x){
						p = lblHum.getLocation();
					}

					else break;
				}

				//조금 기다리고
				for(i=0; i<3; i++){
					Thread.sleep(200);
					lblHum.setIcon(icnStop);

				}
				//사라진다

			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			break;

			
		//엘리베이터에서 내린다
		case 3:
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

	//flr을 입력받으면, 해당 층의 좌표를 알려준다
	public int GetFlrPosY(int flr) { //eflr: end floor
		int height = 610;
		int i;
		for(i=1; i<flr; i++)
			height -= this.nBt;
		System.out.println(">>height: "+height);
		return height;
	}

	public JLabel GetLblHum(){

		return this.lblHum;
	}

}
