package System;

import java.util.Queue;
import java.util.LinkedList;
import GUI.Window;

public class EQ extends Thread { //EQ == elevator queue


	//�� ���������Ϳ��� �� ť�� �ƿ� ������ (���������� �� ť)
	public Queue<Integer> list = new LinkedList<Integer>();
	public int idx;

	/*
	 * ���� ���⼭, ���ο� ������ �߰��� ������ '� ť���� �ִ°� ������'�� �����ؼ�,
	 * �ش� ť���ٰ� ������ �߰��ϸ� �ǰڴ�
	 */

	public EQ(int i) {
		idx = i;
		//1�̸� elev1 - eq
		//2�̸� elev2 - eq
		//3�̸� elev3 - eq
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



////��� ���鼭
//public void run() {
//	System.out.print("run.. ");
//	int i;
//	//while(true) {
//	System.out.print(". ");
//	//ť�� ���𰡰� ����ִٸ�
//	//���������Ͱ� ���� ��ġ����, �� ��ġ���� �̵��ϰ� �Ѵ�
//	try {
//		if(this.list.size() > 0) {
//			switch(idx){
//			case 1:
//				if(Window.elev1.bTsn == false){
//					System.out.println(">>Q1 size over 1");
//					//tsn
//					Window.elev1.bTsn = true;
//
//					//����
//					Window.elev1.MoveSetting(1);
//					Window.elev1.run();
//					Window.elev1.join();
//
//					//����
//					Window.elev1.MoveSetting(2);
//					Window.elev1.run();
//					Window.elev1.join();
//
//					//������
//					Window.elev1.MoveSetting(3, Window.elev1.nNowflr, Window.q1.list.remove());
//					Window.elev1.run();
//					Window.elev1.join();
//
//					//����
//					Window.elev1.MoveSetting(1);
//					Window.elev1.run();
//					Window.elev1.join();
//
//					//����
//					Window.elev1.MoveSetting(2);
//					Window.elev1.run();
//					Window.elev1.join();
//
//					//�ʹ� ���������̸� �������� �� �����ϱ�, ��� ����
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
//					//����
//					Window.elev2.MoveSetting(1);
//					Window.elev2.run();
//					Window.elev2.join();
//
//					//����
//					Window.elev2.MoveSetting(2);
//					Window.elev2.run();
//					Window.elev2.join();
//
//					//������
//					Window.elev2.MoveSetting(3, Window.elev2.nNowflr, Window.q2.list.remove());
//					Window.elev2.run();
//					Window.elev2.join();
//
//					//����
//					Window.elev2.MoveSetting(1);
//					Window.elev2.run();
//					Window.elev2.join();
//
//					//����
//					Window.elev2.MoveSetting(2);
//					Window.elev2.run();
//					Window.elev2.join();
//
//					//�ʹ� ���������̸� �������� �� �����ϱ�, ��� ����
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
//					//����
//					Window.elev3.MoveSetting(1);
//					Window.elev3.run();
//					Window.elev3.join();
//
//					//����
//					Window.elev3.MoveSetting(2);
//					Window.elev3.run();
//					Window.elev3.join();
//
//					//������
//					Window.elev3.MoveSetting(3, Window.elev3.nNowflr, Window.q3.list.remove());
//					Window.elev3.run();
//					Window.elev3.join();
//
//					//����
//					Window.elev3.MoveSetting(1);
//					Window.elev3.run();
//					Window.elev3.join();
//
//					//����
//					Window.elev3.MoveSetting(2);
//					Window.elev3.run();
//					Window.elev3.join();
//
//					//�ʹ� ���������̸� �������� �� �����ϱ�, ��� ����
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
