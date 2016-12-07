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

	//�� ������ ��ġ. �߰��� �ѹ� ����� �� ��ġ
	public int nRemotePosX = 500; 

	//���� Ż ������������ ��ġ
	public int nElevPosX;

	//���� Ÿ�� �ִ� ������������ index
	public int idxElev;

	//���� ź ��, ���� ���� ��
	public int nSflr, nEflr;  //��ǥ

	//���� ����
	int nBt = 120;


	//��� human�� ������ unique�� Ű
	static public int idxKeyChoice = 0; //��� ������ִ� �ְ�(��� ������Ʈ�� �����ϴϱ� �긦 ����ϸ� �ȵ�!)
	public int idxUnique; //�갡 idxKeyChoice�� ���÷� �����Ѵ�. �׷��ϱ� �޸��� ���� ����ũŰ�� �̰�!


	//lblHum��ġ
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

	//���� Ÿ�� �� ����
	public void SetStartFlr(int n) {

		nSflr = n;
	}

	//case���� �Լ��� ���εδ°� �? �������̵�?
	//case 1 - ����������
	//case 3 - ���� ����
	public void MoveSetting(int idx) {
		//idx == 1
		idxRun = idx;
	}

	//case 2 - move
	//case1���� ������ �� ��, posX(���������� ��ġ)���� �̵��ϰ� �Ѵ�.
	public void MoveSetting(int idx, int posX) {
		//idx == 2
		idxRun = idx;
		nElevPosX = posX;
	}

	/*
	//case 3 - ���� ����
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


		//������ ���� ������ (���������� �Ȱ�)
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

				//������
				rmt = new Remotes();
				frmRmt = rmt.GetFrame();
				frmRmt.setVisible(true);
				lblHum.setIcon(icnStop);
				while(rmt.nEflr < 1){
					Thread.sleep(200);
				}

				//������ ���� ���� ����
				nEflr = rmt.nEflr;
				
				//� ���������͸� �������� �����
				idxElev = o.Choice(nSflr, nEflr);
				System.out.println(">>Ż ����: "+idxElev);
				
				frmRmt.dispose();
				
				//�ش� index�� �´� ������������ ť�� �߰����ش�
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
				System.out.println(">>"+nEflr+" ���� �̵�");


				//��� ���
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


		//������~���������ͱ��� �̵�
		//�ʿ��� ����: idx(2), ev�� ��ǥ
		case 2:
			try{
				System.out.print(">>[h.case2]Human"+lblHum.getLocation().x+", Elev: ");
				System.out.println(nElevPosX);
				//�̾ ������
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

				//���� ��ٸ���
				for(i=0; i<3; i++){
					Thread.sleep(200);
					lblHum.setIcon(icnStop);

				}
				//�������

			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			break;

			
		//���������Ϳ��� ������
		case 3:
			//�� �������
			lblHum.setBackground(Color.WHITE);

			//���� ��ٸ���
			for(i=0; i<3; i++){
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();

				}
			}

			//�������
			lblHum.setVisible(false);
			break;

		}
	}

	//flr�� �Է¹�����, �ش� ���� ��ǥ�� �˷��ش�
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
