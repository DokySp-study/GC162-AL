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

	//y ��ǥ
	public int y;

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

		System.out.print("Create human "+strTemp);

		idxKeyChoice ++;
		this.idxUnique = idxKeyChoice;
	}

	//���� Ÿ�� �� ����
	public void SetStartFlr(int n) {

		nSflr = n;
		System.out.println("[h]Set floor "+nSflr);
	}

	//case���� �Լ��� ���εδ°� �? �������̵�?
	//case 0 - move
	public void MoveSetting(int idx, int posX) {
		idxRun = idx;
		nElevPosX = posX;
		System.out.println("[h]Move Setting "+idxRun+", "+nElevPosX);
	}
	
	//case 1 - ���� ����
	public void MoveSetting(int idx, Point _p) {
		idxRun = idx;
		p = _p;
	}

	public void run() {

		Remotes rmt;
		JFrame frmRmt;
		int i;

		switch(idxRun){

		//���������ͱ��� �����̰�, ���ϴ� ���� �����Ѵ�(������) �׸��� �̾ �̵��Ѵ� (�ش� ��ġ����), �׸��� ���ʵڿ� �������
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

				//������
				rmt = new Remotes();
				frmRmt = rmt.GetFrame();
				frmRmt.setVisible(true);
				lblHum.setIcon(icnStop);
				while(rmt.nEflr < 1){
					Thread.sleep(200);
				}

				nEflr = rmt.nEflr - 1;
				frmRmt.dispose();

				//��� ���
				for(i=0; i<3; i++){
					try {
						Thread.sleep(200);
						lblHum.setIcon(icnStop);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				//�̾ ������
				lblHum.setIcon(icnMove);
				while(true)
				{
					Thread.sleep(200);
					lblHum.setLocation(p.x+20, p.y);
					if(nElevPosX > p.x)
						p = lblHum.getLocation();

					else break;
				}

				//���� ��ٸ���
				for(i=0; i<3; i++){
					try {
						Thread.sleep(200);
						lblHum.setIcon(icnStop);
					} catch (InterruptedException e) {
						e.printStackTrace();

					}
				}

				//�������
			}catch(Exception e) {
				e.printStackTrace();
			}
			break;


			//���������Ϳ��� ������
		case 1:
			System.out.println("[h]out, case 1 ("+idxRun+")");
			//p = lblHum.getLocation();
			//lblHum.setLocation(p.x-20, 610-nEflr*nBt);
			
			//lblHum.setLocation(610, 610-nEflr*nBt);
			
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
