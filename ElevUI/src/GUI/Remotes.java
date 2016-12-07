package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import System.EQ;

public class Remotes{

	JFrame frm = new JFrame("Remote Controller");
	JButton btn1, btn2, btn3, btn4, btn5, btn6;

	//내릴 층
	public int nEflr = 0;


	//생성자
	public Remotes() {
		/* Init */
		//Frame
		frm.setBounds(1100, 50, 300, 300);
		frm.setLayout(new BorderLayout());
		
		

		//JLabel title
		JLabel title = new JLabel(new ImageIcon("rmtTitle.png"));

		//Btn
		btn1 = new JButton();
		btn2 = new JButton();
		btn3 = new JButton();
		btn4 = new JButton();
		btn5 = new JButton();
		btn6 = new JButton();

		//Pnl
		JPanel pnl = new JPanel();
		pnl.setLayout(new GridLayout(2, 3, 0, 0));
		pnl.setBackground(Color.white);
		
		//Actionlistner init
		ActionEventHandler actionHandler = new ActionEventHandler();

		
		
		/* Setting */
		//in pnl add
		pnl.add(btn1); pnl.add(btn2); pnl.add(btn3); 
		pnl.add(btn4); pnl.add(btn5); pnl.add(btn6); 


		//btn setting
		BtnSetting();
		btn1.addActionListener(actionHandler);
		btn2.addActionListener(actionHandler);
		btn3.addActionListener(actionHandler);
		btn4.addActionListener(actionHandler);
		btn5.addActionListener(actionHandler);
		btn6.addActionListener(actionHandler);



		//in frm add
		frm.add(title, "North");
		frm.add(pnl, "Center");

	}


	//btn 추가 설정
	public void BtnSetting()
	{
		//btn1
		btn1.setIcon(new ImageIcon("btnf1.png"));
		btn1.setBorderPainted(false);
		btn1.setContentAreaFilled(false);
		btn1.setFocusPainted(false);

		//btn2
		btn2.setIcon(new ImageIcon("btnf2.png"));
		btn2.setBorderPainted(false);
		btn2.setContentAreaFilled(false);
		btn2.setFocusPainted(false);

		//btn3
		btn3.setIcon(new ImageIcon("btnf3.png"));
		btn3.setBorderPainted(false);
		btn3.setContentAreaFilled(false);
		btn3.setFocusPainted(false);

		//btn4
		btn4.setIcon(new ImageIcon("btnf4.png"));
		btn4.setBorderPainted(false);
		btn4.setContentAreaFilled(false);
		btn4.setFocusPainted(false);

		//btn5
		btn5.setIcon(new ImageIcon("btnf5.png"));
		btn5.setBorderPainted(false);
		btn5.setContentAreaFilled(false);
		btn5.setFocusPainted(false);

		//btn6
		btn6.setIcon(new ImageIcon("btnf6.png"));
		btn6.setBorderPainted(false);
		btn6.setContentAreaFilled(false);
		btn6.setFocusPainted(false);
	}

	//리모콘도 스레드로 구현해야 하나..?
	//아직까지 문제는 없었는데 살짝 걱정이된다!
	public JFrame GetFrame()
	{
		return this.frm;
		//dispose는 human에서 할까? 층도 얻어야 하니까?
	}


	//그냥 변수를 바로 받았다. 안쓰긴하지만 혹시모르니까 일단은 냅두자
	//내가 몇층에서 내리는지를 리턴한다. human에서 받는다
	public int GetEndFlr()
	{
		return this.nEflr;
	}

	class ActionEventHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == btn1) {
				nEflr = 1;
			}else if(e.getSource() == btn2) {
				nEflr = 2;
			}else if(e.getSource() == btn3) {
				nEflr = 3;
			}else if(e.getSource() == btn4) {
				nEflr = 4;
			}else if(e.getSource() == btn5) {
				nEflr = 5;
			}else if(e.getSource() == btn6) {
				nEflr = 6;
			}
			System.out.println(nEflr);
		}

	}
}

