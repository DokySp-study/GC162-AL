package GUI;

import java.util.ArrayList;
import javax.swing.*;

public class Elevator extends JFrame{
	
	int x, y = 600;
	ImageIcon imgElev;
	int ID;
	
	public Elevator(int idxElev)
	{
		ImageIcon imgElev;
		
		if(idxElev == 0) {
			imgElev = new ImageIcon("elev1.png"); //0¹ø elev
			ID = 0;
			x = 150;
		}
		else if(idxElev == 1) {
			imgElev = new ImageIcon("elev2.png"); //1¹ø elev
			ID = 1;
			x = 545;
		}
		else {
			imgElev = new ImageIcon("elev3.png"); //2¹ø elev
			ID = 2;
			x = 940;
		}
	}
	
	public int GetX()
	{
		return this.x;
	}
	
	public int GetY()
	{
		return this.y;
	}
	
	public String GetImg()
	{
		if(this.ID == 0)
			return "elev1.png";
		else if(this.ID == 1)
			return "elev2.png";
		else
			return "elev3.png";
	}
	
	public void SetY(int y)
	{
		this.y = y;
	}
	
	public void MoveHuman(int nSourF, int nDestF)
	{
		int i;
		for(i = nSourF; i < nDestF; i++) {
			this.SetY(i);
		}
	}
}
