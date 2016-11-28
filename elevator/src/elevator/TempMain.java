package elevator;

import java.util.Scanner;

public class TempMain {
	public static void main(String[] args) {
		Info temp;
		Scanner keyboard = new Scanner(System.in);
		Car car1 = new Car(1, 1, 1, 8);
		Car car2 = new Car(2, 1, 1, 8);
		Car car3 = new Car(3, 1, 1, 8);
		
		car1.start();
		car2.start();
		car3.start();

		while (true) {
			int msg = keyboard.nextInt();
			
			if(msg == 1) {
				car1.action(10);
			} else if(msg == 3) {
				car1.action(30);
			}
		}
	}
}
