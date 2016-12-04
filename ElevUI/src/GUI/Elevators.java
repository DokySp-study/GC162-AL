package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.logging.Handler;

public class Elevators extends Thread{
	//���� �̹��� ����Ʈ
	public ArrayList<ImageIcon> icnList = new ArrayList<ImageIcon>();
	
	//������ �����̰� �ִ���
	public boolean bMove = false;
	
	//ElevLabel
	JLabel lblElev = new JLabel("");

	//MoveSetting & run
	public int idxRun;

	//floor start, end
	public int nSflr, nEflr; //��ǥ�ι޴´�

	//elev move
	public Point p;//�ʿ��ұ�?


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
	}


	//���������� ������Ʈ ��ü�� �����Ѵ�
	//��ü ��������, run���� �����̰Ը� ����
	public Elevators GetElev() {
		return this;
	}

	//���������� label�� �����Ѵ�
	//���������͸� main frame�� �����ش�
	public JLabel GetLblElev() {
		return this.lblElev;
	}



	//run�� �ϱ⿡ �ռ�, �Ű������� �����ϴ� �Լ�
	//���� �ݱ�
	public void MoveSetting(int i) {
		idxRun = i;
	}
	
	
	//�̵�
	public void MoveSetting(int i, int sflr, int eflr) {
		idxRun = i;
		nSflr = sflr;
		nEflr = eflr; //���̴ϱ� y���� ���� �̵��ϰ���?
		//movesetting���� run�� ȣ���ϸ� ���� �ȵȴ�
	}

	//0: open, 1: close, 2: move
	//MoveSetting�� ���� ������Ѵ�
	public void run() {
		
		int i;
		int time = 5;
		try {
			switch(idxRun) {

			//open
			case 0:
				bMove = true;
				for(i=0; i<time; i++){
					lblElev.setIcon(icnList.get(i));
					Thread.sleep(200);
				}
				lblElev.setIcon(icnList.get(0));
				break;


			//close
			case 1:
				bMove = false;
				for(i=0; i<time; i++){
					lblElev.setIcon(icnList.get(time-1-i));
					Thread.sleep(200);
				}
				lblElev.setIcon(icnList.get(0));
				break;


			//move
			case 2:
				bMove = true;
				System.out.println(nSflr+", "+nEflr);
				if(nSflr > nEflr) { //����(10) -> ����(3)

					while(true) {
						Thread.sleep(200);
						p = lblElev.getLocation();
						if(p.y > nEflr) //���� �ִ� ��ġ��, ������ ������ ����. (����������)
							lblElev.setLocation(p.x, p.y-10);
						else break;
					}

				} else { //����(3) -> ����(10)
					while(true) {
						Thread.sleep(200);
						p = lblElev.getLocation();
						if(nEflr > p.y) //���� �ִ� ��ġ��, ������ ������ ����. (�ö󰡾���)
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
