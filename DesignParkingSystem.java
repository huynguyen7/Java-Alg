// leetcode 1603.
public class DesignParkingSystem {
	public static void main(String[] args) {
		DesignParkingSystem parkingSystem = new DesignParkingSystem(1, 1, 0);
		System.out.println(parkingSystem.addCar(1)); // return true because there is 1 available slot for a big car
		System.out.println(parkingSystem.addCar(2)); // return true because there is 1 available slot for a medium car
		System.out.println(parkingSystem.addCar(3)); // return false because there is no available slot for a small car
		System.out.println(parkingSystem.addCar(1)); // return false because there is no available slot for a big car. It is already occupied.
	}

	private int big;
	private int medium;
	private int small;
	private int currBig;
	private int currMedium;
	private int currSmall;

	public DesignParkingSystem(int big, int medium, int small) {
		this.big = big;
		this.medium = medium;
		this.small = small;
	}

	public boolean addCar(int carType) {
		if(carType == 1) {
			if(currBig == big) return false;
			currBig++;
		} else if(carType == 2) {
			if(currMedium == medium) return false;
			currMedium++;
		} else { // carType == 3
			if(currSmall == small) return false;
			currSmall++;
		}
		
		return true;
	}
}

// Constraints:
// - big = 1, medium = 2, small = 3
