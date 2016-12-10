package System;

import java.util.Queue;
import java.util.LinkedList;
import GUI.Window;

public class EQ extends Thread { //EQ == elevator queue


	//각 엘리베이터에서 쓸 큐를 아예 만들자 (엘리베이터 각 큐)
	public Queue<Integer> list = new LinkedList<Integer>();
	public int idx;

	/*
	 * 또한 여기서, 새로운 정보가 추가될 때마다 '어떤 큐에다 넣는게 좋을까'를 연산해서,
	 * 해당 큐에다가 정보를 추가하면 되겠다
	 */

	public EQ(int i) {
		idx = i;
		//1이면 elev1 - eq
		//2이면 elev2 - eq
		//3이면 elev3 - eq
	}


	//}

	public void Add(int i) {
		System.out.println("[Add] "+i);
		this.list.add(i);
		//this.start();
	}

	public int Remove() {
		int n = this.list.remove();
		System.out.println("[Remove] "+n);
		return n;
	}

}



////계속 돌면서
//public void run() {
//	System.out.print("run.. ");
//	int i;
//	//while(true) {
//	System.out.print(". ");
//	//큐에 무언가가 들어있다면
//	//엘리베이터가 현재 위치에서, 그 위치까지 이동하게 한다
//	try {
//		if(this.list.size() > 0) {
//			switch(idx){
//			case 1:
//				if(Window.elev1.bTsn == false){
//					System.out.println(">>Q1 size over 1");
//					//tsn
//					Window.elev1.bTsn = true;
//
//					//열림
//					Window.elev1.MoveSetting(1);
//					Window.elev1.run();
//					Window.elev1.join();
//
//					//닫힘
//					Window.elev1.MoveSetting(2);
//					Window.elev1.run();
//					Window.elev1.join();
//
//					//움직임
//					Window.elev1.MoveSetting(3, Window.elev1.nNowflr, Window.q1.list.remove());
//					Window.elev1.run();
//					Window.elev1.join();
//
//					//열림
//					Window.elev1.MoveSetting(1);
//					Window.elev1.run();
//					Window.elev1.join();
//
//					//닫힘
//					Window.elev1.MoveSetting(2);
//					Window.elev1.run();
//					Window.elev1.join();
//
//					//너무 빨리움직이면 보기어려울 수 있으니까, 잠깐 쉬게
//					for(i=0; i<3; i++)
//						Thread.sleep(100);
//
//					//tsn
//					Window.elev1.bTsn = false;
//
//					break;
//				}
//			case 2:
//				if(Window.elev2.bTsn == false) {
//
//					System.out.println(">>Q2 size over 1");
//					//tsn
//					Window.elev2.bTsn = true;
//
//					//열림
//					Window.elev2.MoveSetting(1);
//					Window.elev2.run();
//					Window.elev2.join();
//
//					//닫힘
//					Window.elev2.MoveSetting(2);
//					Window.elev2.run();
//					Window.elev2.join();
//
//					//움직임
//					Window.elev2.MoveSetting(3, Window.elev2.nNowflr, Window.q2.list.remove());
//					Window.elev2.run();
//					Window.elev2.join();
//
//					//열림
//					Window.elev2.MoveSetting(1);
//					Window.elev2.run();
//					Window.elev2.join();
//
//					//닫힘
//					Window.elev2.MoveSetting(2);
//					Window.elev2.run();
//					Window.elev2.join();
//
//					//너무 빨리움직이면 보기어려울 수 있으니까, 잠깐 쉬게
//					for(i=0; i<3; i++)
//						Thread.sleep(100);
//
//					//tsn
//					Window.elev2.bTsn = false;
//
//					break;
//				}
//			case 3:
//				if(Window.elev3.bTsn == false){
//					//tsn
//					Window.elev3.bTsn = true;
//
//					System.out.println(">>Q3 size over 1");
//					//열림
//					Window.elev3.MoveSetting(1);
//					Window.elev3.run();
//					Window.elev3.join();
//
//					//닫힘
//					Window.elev3.MoveSetting(2);
//					Window.elev3.run();
//					Window.elev3.join();
//
//					//움직임
//					Window.elev3.MoveSetting(3, Window.elev3.nNowflr, Window.q3.list.remove());
//					Window.elev3.run();
//					Window.elev3.join();
//
//					//열림
//					Window.elev3.MoveSetting(1);
//					Window.elev3.run();
//					Window.elev3.join();
//
//					//닫힘
//					Window.elev3.MoveSetting(2);
//					Window.elev3.run();
//					Window.elev3.join();
//
//					//너무 빨리움직이면 보기어려울 수 있으니까, 잠깐 쉬게
//					for(i=0; i<3; i++)
//						Thread.sleep(100);
//
//					//tsn
//					Window.elev3.bTsn = false;
//
//					break;
//				}
//			}
//		}
//	}catch(InterruptedException e) {
//
//	}
//
//}
