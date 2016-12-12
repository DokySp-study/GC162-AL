package System;

import java.util.ArrayList;

import GUI.Window;

public class Core extends Thread {
	
	public Core(){
		
	}
	
//	# 올라갈 때 
//	1. 올라오는 것 중에 큐 사이즈 가장 작은 것 
//	2. 올라오는 것이 없으면 큐 사이즈 0인거 우선 배정 
//	3. 큐 사이즈가 0인게 없으면 uq, dq합이 가장 작언거 배정 
	
	
	
	
	
	public int getElevNum(int startLev, int targetLev){
			
		int[] uqSize = new int[3];
		int[] dqSize = new int[3];
		
		ArrayList<Integer> uqList = new ArrayList<Integer>();
		ArrayList<Integer> dqList = new ArrayList<Integer>();
		
		boolean isGoingUp = false;
		int min = 100000;
		int targetElev = 0;
			
		uqSize[0] = Window.elev1.upQueue.size();
		dqSize[0] = Window.elev1.downQueue.size();
		uqSize[1] = Window.elev2.upQueue.size();
		dqSize[1] = Window.elev2.downQueue.size();
		uqSize[2] = Window.elev3.upQueue.size();
		dqSize[2] = Window.elev3.downQueue.size();
		
		int[] tq = new int[3];
		
		
		if(startLev > targetLev){
			//Going Down
			//System.out.println("Going Down");
			printQueue(0);
			
			if(Window.elev1.currLevel > startLev)
				dqList.add(0);
			if(Window.elev2.currLevel > startLev)
				dqList.add(1);
			if(Window.elev3.currLevel > startLev)
				dqList.add(2);
			
			System.out.println("1: " + Window.elev1.currLevel);
			System.out.println("2: " + Window.elev2.currLevel);
			System.out.println("3: " + Window.elev3.currLevel);
			System.out.println("size: " + dqList.size());
			
			
			if(dqList.size() != 0){ //Case 1
				//System.out.println("Case 1");
				for(int i = 0; i < dqList.size(); i++){
					if(min > dqSize[dqList.get(i)]){
						min = dqSize[i];
						targetElev = dqList.get(i);;
					}
				}
				targetElev++;
//				System.out.println("startLev" + startLev);
//				System.out.println("targetLev" + targetLev);
				setElevQueue(targetElev, false, startLev, targetLev);
				
				printQueue(1);
				return targetElev;
				
			}
			
			else{ //Case 2
				if(Window.elev1.isMooving == false){
					setElevQueue(1, true, startLev, startLev);
					setElevQueue(1, false, targetLev, targetLev);
					printQueue(2);
					return 1;
				}
				else if(Window.elev2.isMooving == false){
					setElevQueue(2, true, startLev, startLev);
					setElevQueue(2, false, targetLev, targetLev);
					return 2;
				}
				else if(Window.elev3.isMooving == false){
					setElevQueue(3, true, startLev, startLev);
					setElevQueue(3, false, targetLev, targetLev);
					return 3;
				}
				else{ //Case 3
					tq[0] = uqSize[0] + dqSize[0];
					tq[1] = uqSize[1] + dqSize[1];
					tq[2] = uqSize[2] + dqSize[2];
					
					min = tq[0];
					for(int i = 1; i < 3; i++){
						if(min > tq[i]){
							min = tq[i];
							targetElev = i;
						}
					}
					targetElev++;
					setElevQueue(targetElev, false, startLev, targetLev);
					printQueue(3);
					return targetElev;
					
				}
				
				
			}
			
			
			
			
		}
		else if(startLev < targetLev){
			//Going Up
			//System.out.println("Going Up");
			printQueue(0);
			
			
			if(Window.elev1.currLevel < startLev)
				uqList.add(0);
			if(Window.elev2.currLevel < startLev)
				uqList.add(1);
			if(Window.elev3.currLevel < startLev)
				uqList.add(2);
			
			
			if(uqList.size() != 0){
				//System.out.println("min: " + min);
				for(int i = 0; i < uqList.size(); i++){
					//System.out.println("min: " + uqSize[uqList.get(i)] + "/" + uqList.get(i));
					if(min > uqSize[uqList.get(i)]){
						min = uqSize[i];
						targetElev = uqList.get(i);
					}
				}
				//System.out.println("min: " + min);
				targetElev++;
				//System.out.println("target: " + targetElev);
				//System.out.println(targetElev);
				
				setElevQueue(targetElev, true, startLev, targetLev);
				printQueue(1);
				return targetElev;
				
			}
			
			else{ //Case 2
				
				if(Window.elev1.isMooving == false){
					if(uqList.indexOf(0) != -1){ //나보다 밑에 있을 때
						setElevQueue(1, false, startLev, startLev);
						setElevQueue(1, true, targetLev, targetLev);
						printQueue(2);
						return 1;
					}
					else{ //같은 층일 
						setElevQueue(1, true, startLev, startLev);
						setElevQueue(1, false, startLev, startLev);
						setElevQueue(1, true, targetLev, targetLev);
						printQueue(2);
						return 1;
					}
				}
				else if(Window.elev2.isMooving == false){
					if(uqList.indexOf(1) != -1){ //나보다 밑에 있을 때
						setElevQueue(2, false, startLev, startLev);
						setElevQueue(2, true, targetLev, targetLev);
						return 2;
					}
					else{ //같은 층일 
						setElevQueue(2, true, startLev, startLev);
						setElevQueue(2, false, startLev, startLev);
						setElevQueue(2, true, targetLev, targetLev);
						return 2;
					}
				}
				else if(Window.elev3.isMooving == false){
					if(uqList.indexOf(2) != -1){ //나보다 밑에 있을 때
						setElevQueue(3, false, startLev, startLev);
						setElevQueue(3, true, targetLev, targetLev);
						return 3;
					}
					else{ //같은 층일 
						setElevQueue(3, true, startLev, startLev);
						setElevQueue(3, false, startLev, startLev);
						setElevQueue(3, true, targetLev, targetLev);
						return 3;
					}
				}
				else{ //Case 3
					tq[0] = uqSize[0] + dqSize[0];
					tq[1] = uqSize[1] + dqSize[1];
					tq[2] = uqSize[2] + dqSize[2];
					
					min = tq[0];
					for(int i = 1; i < 3; i++){
						if(min > tq[i]){
							min = tq[i];
							targetElev = i;
						}
					}
					targetElev++;
					setElevQueue(targetElev, true, startLev, targetLev);
					printQueue(3);
					return targetElev;
					
				}
				
				
			}
			
			
		}
		else{
			printQueue(-1);
			return 0;
		}
		
		
		
	}
	
	
	
	public void printQueue(int input){
		System.out.println("=========elev1=========");
		System.out.println("=========case"+input+"=========");
		System.out.println("=========Hum"+Window.elev1.HumStack.size()+"=========");
		System.out.println("Up queue");
		for(Integer a: Window.elev1.upQueue)
			System.out.print(a + "/");
		System.out.println();
		System.out.println("Down queue");
		for(Integer a: Window.elev1.downQueue)
			System.out.print(a + "/");
		System.out.println("\n=======================");
	}
	
	
	
	
	private void setElevQueue(int elevNum, boolean isUp, int elevStop1, int elevStop2){
		
		if(isUp){
			if(elevNum == 1){
				Window.elev1.insertUpQueue(elevStop1);
				if(elevStop1 != elevStop2)
					Window.elev1.insertUpQueue(elevStop2);
			}
			else if(elevNum == 2){
				Window.elev2.insertUpQueue(elevStop1);
				if(elevStop1 != elevStop2)
					Window.elev2.insertUpQueue(elevStop2);
			}
			else if(elevNum == 3){
				Window.elev3.insertUpQueue(elevStop1);
				if(elevStop1 != elevStop2)
					Window.elev3.insertUpQueue(elevStop2);
			}
		}
		else{
			if(elevNum == 1){
				Window.elev1.insertDownQueue(elevStop1);
				if(elevStop1 != elevStop2)
					Window.elev1.insertDownQueue(elevStop2);
			}
			else if(elevNum == 2){
				Window.elev2.insertDownQueue(elevStop1);
				if(elevStop1 != elevStop2)
					Window.elev2.insertDownQueue(elevStop2);
			}
			else if(elevNum == 3){
				Window.elev3.insertDownQueue(elevStop1);
				if(elevStop1 != elevStop2)
					Window.elev3.insertDownQueue(elevStop2);
			}
			
		}
		
	}
	
	
}
