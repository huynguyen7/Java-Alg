import java.util.Arrays;

// leetcode 1710.
public class MaximumUnitsOnATruck {
	public static void main(String[] args) {
		int[][] boxTypes1 = {
			{1,3},
			{2,2},
			{3,1}
		};
		int truckSize1 = 4;
		showResults(boxTypes1, truckSize1); // expect 8

		int[][] boxTypes2 = {
			{5,10},
			{2,5},
			{4,7},
			{3,9}
		};
		int truckSize2 = 10;
		showResults(boxTypes2, truckSize2); // expect 91
	}

	private static void showResults(int[][] boxTypes, int truckSize) {
		System.out.println("\t----ShowResults----");
		for(int[] row: boxTypes)
			System.out.println(Arrays.toString(row));
		int rs = maximumUnits(boxTypes, truckSize);
		System.out.printf("\nTruck size: %d -> MAX: %d\n\n", truckSize, rs);
	}

	// Time: O(nlogn), space: O(1)
	public static int maximumUnits(int[][] boxTypes, int truckSize) {
		if(boxTypes == null || boxTypes.length == 0) return 0;

		Arrays.sort(boxTypes, (int[] box1, int[] box2) -> {
			return Integer.compare(box2[1], box1[1]);
		});
		
		int maxUnits = 0;
		int currBoxes = 0;
		for(int i = 0; i < boxTypes.length; ++i) {
			if(currBoxes + boxTypes[i][0] > truckSize) {
				int remainBoxes = truckSize - currBoxes;
				maxUnits += remainBoxes * boxTypes[i][1];
				break;
			} else {
				currBoxes += boxTypes[i][0];
				maxUnits += boxTypes[i][0] * boxTypes[i][1];
			}
		}
		
		return maxUnits;
	}
}
