package GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.Position;

public class ShowFloor extends Thread {
	public int nFlr;
	public JLabel lblShowFlr = new JLabel("");
	public Position pStd;
	public Position pMe;
	
	public ShowFloor(int n, Position std) {
		nFlr = n;
		lblShowFlr.setText(nFlr+"Ãþ");
		lblShowFlr.setOpaque(true);
		lblShowFlr.setBackground(Color.white);
		pStd = std;
	}
	
	public JLabel GetLbl() {
		return this.lblShowFlr;
	}
	
	public void run() {
		while(true) {
			
		}
	}
}
