package elevator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Car extends Thread {
	Info info = new Info();

	public Car(int id, double speed, int maxWeight, int buildingStories) {
		info.setInfo(id, speed, maxWeight, buildingStories);
	}

	public void run() {
		while (true) {
			if (info.destination.isEmpty() && info.currentFloor != info.buildingStories / 2) {
				System.out.println("the car is empty");
				info.destination.add(info.buildingStories / 2);
			} else if (!info.destination.isEmpty()) {
				info.currentFloor = info.destination.remove(0);
				System.out.println("the car moves");
				System.out.println(info.currentFloor);
			} else if(info.destination.isEmpty()) {
				System.out.println("sdalkfhqieuhfdx");
			}
		}
	}
 
	

	public void action(int destination) {
		info.destination.add(destination);
		System.out.println("inserttion complete");
		if(!info.destination.isEmpty()) {
			System.out.println("array is not empty");
		}
		/*
		 * Collections.sort(info.destination, new Comparator<Integer>() { public
		 * int compare(Integer a, Integer b) { } });
		 */
	}

	public Info getInfo() {
		return info;
	}
}

class Info {
	int id = 0;
	int currentFloor = 0;
	ArrayList<Integer> destination = new ArrayList<Integer>();

	double speed = 0;
	int currentWeight = 0;
	int maxWeight = 0;
	int buildingStories = 0;

	public void setInfo(int id, double speed, int maxWeight, int buildingStories) {
		this.id = id;
		this.speed = speed;
		this.maxWeight = maxWeight;
		this.buildingStories = buildingStories;
	}
}
