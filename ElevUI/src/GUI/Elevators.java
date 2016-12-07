package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.logging.Handler;

public class Elevators extends Thread{
	//엘베 이미지 리스트
	public ArrayList<ImageIcon> icnList = new ArrayList<ImageIcon>();

	//엘베가 움직이고 있는지
	public boolean bMove = false;
	
	//엘베 트랜젝션
	public boolean bTsn = false;
	
	//엘베의 문이 열렸는지
	public boolean bOpen = false;

	//ElevLabel
	JLabel lblElev = new JLabel("");

	//MoveSetting & run
	public int idxRun;

	//floor start, end
	public int nSflr, nEflr; //층으로 받고, listFloorY를 이용해서 바꾼다
	public int nNowflr; //현재 층

	//elev move
	public Point p;//필요할까?

	//각 층의 위치 (엘리베이터 이동 편하게하라고)
	public ArrayList<Integer> listFloorY = new ArrayList<Integer>();

	//사이 간격
	public int nBt = 120;

	public Elevators(int ID) {
		switch(ID){
		case 1:
			icnList.add(new ImageIcon("elev1.png"));
			icnList.add(new ImageIcon("elev1_o1.png"));
			icnList.add(new ImageIcon("elev1_o2.png"));
			icnList.add(new ImageIcon("elev1_o3.png"));
			icnList.add(new ImageIcon("elev1_o4.png"));
			break;
		case 2:
			icnList.add(new ImageIcon("elev2.png"));
			icnList.add(new ImageIcon("elev2_o1.png"));
			icnList.add(new ImageIcon("elev2_o2.png"));
			icnList.add(new ImageIcon("elev2_o3.png"));
			icnList.add(new ImageIcon("elev2_o4.png"));
			break;
		case 3:
			icnList.add(new ImageIcon("elev3.png"));
			icnList.add(new ImageIcon("elev3_o1.png"));
			icnList.add(new ImageIcon("elev3_o2.png"));
			icnList.add(new ImageIcon("elev3_o3.png"));
			icnList.add(new ImageIcon("elev3_o4.png"));
			break;
		}
		
		lblElev.setIcon(icnList.get(0));
		
		//listFloorY 초기화
		for(int i=0; i<6; i++)
			listFloorY.add(610 - nBt*i);
		
		nNowflr = 0;
	}


	//엘리베이터 오브젝트 자체를 리턴한다
	//객체 만들어놓고, run으로 움직이게만 하자
	public Elevators GetElev() {
		return this;
	}


	//엘리베이터 label을 리턴한다
	//엘리베이터를 main frame에 보여준다
	public JLabel GetLblElev() {
		return this.lblElev;
	}


	//run을 하기에 앞서, 매개변수를 전달하는 함수
	//열림 닫김 - 1 2
	public void MoveSetting(int i) {
		idxRun = i;
	}


	//이동 - 3
	public void MoveSetting(int i, int sflr, int eflr) {
		idxRun = i;
		//층 값으로 받았으니, 좌표값으로 바꾸어줘야 한다
		nSflr = sflr;
		nEflr = eflr; //층이니까 y값에 따라 이동하겠지?
		//movesetting에서 run을 호출하면 절대 안된다
		
		nSflr = listFloorY.get(sflr);
		nEflr = listFloorY.get(eflr);
		
	}


	//0: open, 1: close, 2: move
	//MoveSetting을 먼저 해줘야한다
	public void run() {

		int i;
		int time = 5;
		try {
			nNowflr = (610-lblElev.getLocation().y)/nBt + 1;
			System.out.println(">>nNowflr: "+nNowflr);
			switch(idxRun) {
			
			//open
			case 1:
				System.out.println(">>Open elev");
				bOpen = true;
				bMove = false;
				for(i=0; i<time; i++){
					lblElev.setIcon(icnList.get(i));
					Thread.sleep(200);
				}
				lblElev.setIcon(icnList.get(0));
				break;


			//close
			case 2:
				bOpen = false;
				bMove = false;
				System.out.println(">>Close elev");
				for(i=0; i<time; i++){
					lblElev.setIcon(icnList.get(time-1-i));
					Thread.sleep(200);
				}
				lblElev.setIcon(icnList.get(0));
				break;


			//move
			case 3:
				bOpen = false;
				bMove = true;
				System.out.println(">>Move elev");
				//bOpne 위에서 설정해줫으니 안건드려도 ㅇㅋ
				System.out.println(nSflr+", "+nEflr);
				if(nSflr > nEflr) { //고층(10) -> 저층(3)

					while(true) {
						Thread.sleep(200);
						nNowflr = (610-lblElev.getLocation().y)/nBt + 1;
						p = lblElev.getLocation();
						if(p.y > nEflr) //내가 있는 위치가, 도착할 층보다 높다. (내려가야함)
							lblElev.setLocation(p.x, p.y-10);
						else break;
					}

				} else { //저층(3) -> 고층(10)
					while(true) {
						Thread.sleep(200);
						nNowflr = (610-lblElev.getLocation().y)/nBt + 1;
						p = lblElev.getLocation();
						if(nEflr > p.y) //내가 있는 위치가, 도착할 층보다 적다. (올라가야함)
							lblElev.setLocation(p.x, p.y+10);
						else break;;
					}
				}
				break;

			}
		} 
		catch(Exception e) {
			e.printStackTrace();
		}

	}
}
