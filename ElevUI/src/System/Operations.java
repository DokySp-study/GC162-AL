package System;

import java.util.Random;

public class Operations extends Thread{
	
	//����ϴ� ��, ������ ��
	int nStFlr, nArvFlr;
	
	//������ ������������ �ε���
	int idxElev; 
	
	public Operations() {
		
	}
	
	public int Choice(int stFlr, int arvFlr) {
		nStFlr = stFlr;
		nArvFlr = arvFlr;
		
		/* 
		 * �˰��� ...
		 */
		//������ �����̶� ������, � �˰����� ����!
		Random ran = new Random();
		idxElev = ran.nextInt(2) + 1;
		
		
		return idxElev;
		
	}
}
