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
	
	//���� Ʈ������
	public boolean bTsn = false;
	
	//������ ���� ���ȴ���
	public boolean bOpen = false;

	//ElevLabel
	JLabel lblElev = new JLabel("");

	//MoveSetting & run
	public int idxRun;

	//floor start, end
	public int nSflr, nEflr; //������ �ް�, listFloorY�� �̿��ؼ� �ٲ۴�
	public int nNowflr; //���� ��

	//elev move
	public Point p;//�ʿ��ұ�?

	//�� ���� ��ġ (���������� �̵� ���ϰ��϶��)
	public ArrayList<Integer> listFloorY = new ArrayList<Integer>();

	//���� ����
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
		
		//listFloorY �ʱ�ȭ
		for(int i=0; i<6; i++)
			listFloorY.add(610 - nBt*i);
		
		nNowflr = 0;
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
	//���� �ݱ� - 1 2
	public void MoveSetting(int i) {
		idxRun = i;
	}


	//�̵� - 3
	public void MoveSetting(int i, int sflr, int eflr) {
		idxRun = i;
		//�� ������ �޾�����, ��ǥ������ �ٲپ���� �Ѵ�
		nSflr = sflr;
		nEflr = eflr; //���̴ϱ� y���� ���� �̵��ϰ���?
		//movesetting���� run�� ȣ���ϸ� ���� �ȵȴ�
		
		nSflr = listFloorY.get(sflr);
		nEflr = listFloorY.get(eflr);
		
	}


	//0: open, 1: close, 2: move
	//MoveSetting�� ���� ������Ѵ�
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
				//bOpne ������ �����آZ���� �Ȱǵ���� ����
				System.out.println(nSflr+", "+nEflr);
				if(nSflr > nEflr) { //����(10) -> ����(3)

					while(true) {
						Thread.sleep(200);
						nNowflr = (610-lblElev.getLocation().y)/nBt + 1;
						p = lblElev.getLocation();
						if(p.y > nEflr) //���� �ִ� ��ġ��, ������ ������ ����. (����������)
							lblElev.setLocation(p.x, p.y-10);
						else break;
					}

				} else { //����(3) -> ����(10)
					while(true) {
						Thread.sleep(200);
						nNowflr = (610-lblElev.getLocation().y)/nBt + 1;
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
