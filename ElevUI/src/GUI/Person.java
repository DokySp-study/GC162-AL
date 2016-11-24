package GUI;

import java.awt.*;
import java.util.ArrayList;
import javax.imageio.*;
import javax.swing.*;


public class Person extends JFrame{

	public int idxAbsHum = 0;
	public float fWeight = 50;
	public int idxFloor;
	ImageIcon hum;
	
	
	//버튼을 누르면 사람을 추가함
	//idxStdFloor: hum이 elev 요청한 층
	public Person(int idxStdFloor)
	{
		
		System.out.println("Add people on "+idxStdFloor);

		idxAbsHum = idxAbsHum + 1;
		
		if(idxAbsHum % 7 == 0)
			hum = new ImageIcon("hum1.png");
		
		else if(idxAbsHum % 5 == 0)
			hum = new ImageIcon("hum2.png");
		
		else if(idxAbsHum % 4 == 0)
			hum = new ImageIcon("hum3.png");
		
		else if(idxAbsHum % 3 == 0)
			hum = new ImageIcon("hum4.png");
		
		else 
			hum = new ImageIcon("hum5.png");
		
		idxFloor = idxStdFloor;
		
		Remote rmt = new Remote(idxAbsHum, 0);

	}

}
