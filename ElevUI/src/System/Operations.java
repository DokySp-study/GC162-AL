package System;

import java.util.Random;

public class Operations extends Thread{
	
	//출발하는 층, 도착할 층
	int nStFlr, nArvFlr;
	
	//적절한 엘리베이터의 인덱스
	int idxElev; 
	
	public Operations() {
		
	}
	
	public int Choice(int stFlr, int arvFlr) {
		nStFlr = stFlr;
		nArvFlr = arvFlr;
		
		/* 
		 * 알고리즘 ...
		 */
		//지금은 랜덤이라 했지만, 어떤 알고리즘을 통해!
		Random ran = new Random();
		idxElev = ran.nextInt(2) + 1;
		
		
		return idxElev;
		
	}
}
