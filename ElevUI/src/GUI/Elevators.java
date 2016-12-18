package GUI;

import javax.swing.*;
import java.awt.*;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Elevators {
	
	private final int LEV_INT = 119;
	private final int LEV_FIRST = 610;
	private final int LEV_LAST = 15;
	
	public ArrayList<Integer> upQueue = new ArrayList<Integer>();
	public ArrayList<Integer> downQueue = new ArrayList<Integer>();
	
	public ArrayList<Humans> HumStack = new ArrayList<Humans>();
	
	public boolean isDoorOpen;
	public boolean isMooving;
	
	public int currLevel;
	private int weight;
	private boolean isGoingUp;
	
	//ElevLabel
	JLabel lblElev = new JLabel();
	
	public Elevators(){
		lblElev.setIcon(new ImageIcon("elev3_o0.png"));
		isGoingUp = true;
		isDoorOpen = false;
		currLevel = 1;
		
	}
	


	public JLabel GetLblElev() {
		return this.lblElev;
	}
	
	public void runCheckThd(){
		new thdCheckList().start();
	}
	public void runMoveThd(){
		new thdMoveElev().start();
	}
	
	
	
	
	class thdMoveElev extends Thread {
		
		public void run(){
			
			

			
			try{
				while(true){
					
					
					//올라갈 때
					if(isGoingUp){
						int getNextLev;
						System.out.print("");
						
						if(HumStack.size() != 0 && isMooving == false){
							for(int i = 0; i < HumStack.size(); i++){
									if(HumStack.get(i).targetLev > currLevel){
										insertUpQueue(HumStack.get(i).targetLev);
										isMooving = true;
									}
							}
						}
						
						
						
						if(upQueue.size() == 0 && downQueue.size() != 0){
							isMooving = true;
							isGoingUp = false;
						}
						else if(upQueue.size() != 0 && downQueue.size() == 0){
							isMooving = true;
							isGoingUp = true;
						}
						
						
						
						if(upQueue.size() != 0){
							
							
							
							
							while(true){
								
								getNextLev = (int) upQueue.get(0);
								
								if(getNextLev == currLevel){
									
									upQueue.remove((Integer)currLevel);
									
									
									openDoor();
									//System.out.println("door open");
									
									//People get in or out
									for(int i = 0; i < HumStack.size(); i++){
										if(HumStack.get(i).targetLev == currLevel){
											HumStack.get(i).thdLeaveStart(lblElev.getX(), currLevel);
											HumStack.remove(i);
										}
									}
									
									Thread.sleep(1500);

									
									closeDoor();
									//System.out.println("door close");
									
									//Weight
									if(HumStack.size() >= 15)
										currLevel = -1;
									
									break;
									
								}
								
								
								isMooving = true;
								Point p = lblElev.getLocation();
								
								if(isTouchMax(p.y)){
									isGoingUp = false;
									break;
								}
								
								for(int i = 0; i <= LEV_INT; i++){
									lblElev.setLocation(p.x, p.y - i);
										Thread.sleep(5);
								}
								calcCurrLev();
							
							}
							
							isMooving = false;
							
						}
						
						
					}
					
					//내려갈 때 
					else{
						int getNextLev;
						System.out.print("");
						
						if(HumStack.size() != 0 && isMooving == false){
							for(int i = 0; i < HumStack.size(); i++){
									if(HumStack.get(i).targetLev < currLevel){
										insertDownQueue(HumStack.get(i).targetLev);
										isMooving = true;
									}
							}
						}
						
						
						
						if(upQueue.size() == 0 && downQueue.size() != 0){
							isMooving = true;
							isGoingUp = false;
						}
						else if(upQueue.size() != 0 && downQueue.size() == 0){
							isMooving = true;
							isGoingUp = true;
						}
						
						
						
						if(downQueue.size() != 0){
							
							
							
							while(true){
								
								getNextLev = (int) downQueue.get(0);
								
								if(getNextLev == currLevel){
									
									downQueue.remove((Integer)currLevel);
									
									
									openDoor();
									//System.out.println("door open");
									
									//People get in or out
									for(int i = 0; i < HumStack.size(); i++){
										if(HumStack.get(i).targetLev == currLevel){
											System.out.println("target lev:"+ HumStack.get(i).targetLev);
											HumStack.get(i).thdLeaveStart(lblElev.getX(), currLevel);
											HumStack.remove(i);
										}
									}
									
									Thread.sleep(1500);
									
									closeDoor();
									//System.out.println("door close");
									
									//Weight
									if(HumStack.size() >= 15)
										currLevel = -1;
									
									
									break;
								}
								
								isMooving = true;
								
								Point p = lblElev.getLocation();
								
								if(isTouchMin(p.y)){
									isGoingUp = true;
									break;
								}
								
								for(int i = 0; i <= LEV_INT; i++){
									lblElev.setLocation(p.x, p.y + i);
										Thread.sleep(5);
								}
								calcCurrLev();
							
							}
							
							isMooving = false;
							
						}
						
					}
					
				}
				  
			} 
			catch (InterruptedException e) {
				
			}
			
		}
		
		
		private boolean isTouchMax(int yLoc){
			
			if(yLoc == LEV_LAST)
				return true;
			return false;
			
		}
		
		private boolean isTouchMin(int yLoc){
			
			if(yLoc == LEV_FIRST)
				return true;
			return false;
			
		}
		

		
		
		
		
		
		
		
		
		
		private void openDoor(){
			for(int i = 1; i <= 4; i++){
				lblElev.setIcon(new ImageIcon("elev3_o"+i+".png"));
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			isDoorOpen = true;
			
		}
		
		
		private void closeDoor(){
			for(int i = 4; i >= 0; i--){
				lblElev.setIcon(new ImageIcon("elev3_o"+i+".png"));
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			isDoorOpen = false;
			
		}

	}
	
	
	
	
	public void insertUpQueue(int input){
		
		if(upQueue.indexOf(input) != -1)
			return;

		
		upQueue.add(input);
		Collections.sort(upQueue);
		
//		for(Integer a: upQueue)
//			System.out.println(a);
//		System.out.println();
			
	}
	
	
	
	public void insertDownQueue(int input){
		
		if(downQueue.indexOf(input) != -1)
			return;
		
		
		//System.out.println("queue");
		downQueue.add(input);
		Collections.sort(downQueue); /// 내림차순이 아님!!
		Collections.reverse(downQueue);
//		for(Integer a: downQueue)
//			System.out.print(a + "/");
//		System.out.println();
			
	}
	
	
	
	
	class thdCheckList extends Thread {
		
		public void run(){
			while(true){
				System.out.println("Up queue");
				for(int i = 0; i < upQueue.size(); i++){
					upQueue.get(i);
				}
				
				System.out.println("Down queue");
				for(int i = 0; i < downQueue.size(); i++){
					downQueue.get(i);
				}
				
			}
		}
		
	}
	
	
	class thdCheckWeight extends Thread {
		
		public void run(){
			while(true){
				
				weight = 0;
				System.out.println(weight);
				
			}
		}
		
	}
	
	
	
	
	
	
	
	private void calcCurrLev(){
		Point p = lblElev.getLocation();
		int yLoc = LEV_FIRST - p.y;
		yLoc /= LEV_INT;
		currLevel = yLoc + 1;
		//System.out.println(currLevel);
	}
	
}



////엘베 이미지 리스트
//public ArrayList<ImageIcon> icnList = new ArrayList<ImageIcon>();
//
////엘베가 움직이고 있는지
//public boolean bMove = false;
//
////엘베 트랜젝션
//public boolean bTsn = false;
//
////엘베의 문이 열렸는지
//public boolean bOpen = false;
//
////ElevLabel
//JLabel lblElev = new JLabel("");
//
////MoveSetting & run
//public int idxRun;
//
////floor start, end
//public int nSflr, nEflr; //층으로 받고, listFloorY를 이용해서 바꾼다
//public int nNowflr; //현재 층
//
////elev move
//public Point p;//필요할까?
//
////각 층의 위치 (엘리베이터 이동 편하게하라고)
//public ArrayList<Integer> listFloorY = new ArrayList<Integer>();
//
////사이 간격
//public int nBt = 120;
//
//public Elevators(int ID) {
//	switch(ID){
//	case 1:
//		icnList.add(new ImageIcon("elev1.png"));
//		icnList.add(new ImageIcon("elev1_o1.png"));
//		icnList.add(new ImageIcon("elev1_o2.png"));
//		icnList.add(new ImageIcon("elev1_o3.png"));
//		icnList.add(new ImageIcon("elev1_o4.png"));
//		break;
//	case 2:
//		icnList.add(new ImageIcon("elev2.png"));
//		icnList.add(new ImageIcon("elev2_o1.png"));
//		icnList.add(new ImageIcon("elev2_o2.png"));
//		icnList.add(new ImageIcon("elev2_o3.png"));
//		icnList.add(new ImageIcon("elev2_o4.png"));
//		break;
//	case 3:
//		icnList.add(new ImageIcon("elev3.png"));
//		icnList.add(new ImageIcon("elev3_o1.png"));
//		icnList.add(new ImageIcon("elev3_o2.png"));
//		icnList.add(new ImageIcon("elev3_o3.png"));
//		icnList.add(new ImageIcon("elev3_o4.png"));
//		break;
//	}
//	
//	lblElev.setIcon(icnList.get(0));
//	
//	//listFloorY 초기화
//	for(int i=0; i<6; i++)
//		listFloorY.add(610 - nBt*i);
//	
//	nNowflr = 0;
//}
//
//
////엘리베이터 오브젝트 자체를 리턴한다
////객체 만들어놓고, run으로 움직이게만 하자
//public Elevators GetElev() {
//	return this;
//}
//
//
////엘리베이터 label을 리턴한다
////엘리베이터를 main frame에 보여준다
//public JLabel GetLblElev() {
//	return this.lblElev;
//}
//
//
////run을 하기에 앞서, 매개변수를 전달하는 함수
////열림 닫김 - 1 2
//public void MoveSetting(int i) {
//	idxRun = i;
//}
//
//
////이동 - 3
//public void MoveSetting(int i, int sflr, int eflr) {
//	idxRun = i;
//	//층 값으로 받았으니, 좌표값으로 바꾸어줘야 한다
//	nSflr = sflr;
//	nEflr = eflr; //층이니까 y값에 따라 이동하겠지?
//	//movesetting에서 run을 호출하면 절대 안된다
//	
//	nSflr = listFloorY.get(sflr);
//	nEflr = listFloorY.get(eflr);
//	
//}
//
//
////0: open, 1: close, 2: move
////MoveSetting을 먼저 해줘야한다
//public void run() {
//
//	int i;
//	int time = 5;
//	try {
//		nNowflr = (610-lblElev.getLocation().y)/nBt + 1;
//		System.out.println(">>nNowflr: "+nNowflr);
//		switch(idxRun) {
//		
//		//open
//		case 1:
//			System.out.println(">>Open elev");
//			bOpen = true;
//			bMove = false;
//			for(i=0; i<time; i++){
//				lblElev.setIcon(icnList.get(i));
//				Thread.sleep(200);
//			}
//			lblElev.setIcon(icnList.get(0));
//			break;
//
//
//		//close
//		case 2:
//			bOpen = false;
//			bMove = false;
//			System.out.println(">>Close elev");
//			for(i=0; i<time; i++){
//				lblElev.setIcon(icnList.get(time-1-i));
//				Thread.sleep(200);
//			}
//			lblElev.setIcon(icnList.get(0));
//			break;
//
//
//		//move
//		case 3:
//			bOpen = false;
//			bMove = true;
//			System.out.println(">>Move elev");
//			//bOpne 위에서 설정해�Z으니 안건드려도 ㅇㅋ
//			System.out.println(nSflr+", "+nEflr);
//			if(nSflr > nEflr) { //고층(10) -> 저층(3)
//
//				while(true) {
//					Thread.sleep(200);
//					nNowflr = (610-lblElev.getLocation().y)/nBt + 1;
//					p = lblElev.getLocation();
//					if(p.y > nEflr) //내가 있는 위치가, 도착할 층보다 높다. (내려가야함)
//						lblElev.setLocation(p.x, p.y-10);
//					else break;
//				}
//
//			} else { //저층(3) -> 고층(10)
//				while(true) {
//					Thread.sleep(200);
//					nNowflr = (610-lblElev.getLocation().y)/nBt + 1;
//					p = lblElev.getLocation();
//					if(nEflr > p.y) //내가 있는 위치가, 도착할 층보다 적다. (올라가야함)
//						lblElev.setLocation(p.x, p.y+10);
//					else break;;
//				}
//			}
//			break;
//
//		}
//	} 
//	catch(Exception e) {
//		e.printStackTrace();
//	}
//
//}

