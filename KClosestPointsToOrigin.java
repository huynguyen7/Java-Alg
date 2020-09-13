import java.util.PriorityQueue;
import java.util.Arrays;
import java.util.Iterator;

public class KClosestPointsToOrigin {
	public static void main(String args[]) {
		int[][] points1 = {
			{1,3},
			{-2,2}
		};
		int k1 = 1;
		showResults(points1, k1); //expect {{-2,2}}

		int[][] points2 = {
			{3,3},
			{5,-1},
			{-2,4}
		};
		int k2 = 2;
		showResults(points2, k2); //expect {{-2,4},{3,3}}
		
		int[][] points3 = {
			{1,1},
			{3,3},
			{4,4},
			{2,2},
			{5,5}
		};
		int k3 = 2;
		showResults(points3, k3); //expect {{1,1},{2,2}}
	}

	private static void showResults(int[][] points, int k) {
		System.out.println("----ShowResults----");
		System.out.printf("k = %d\n", k);
		for(int[] a: points)
			System.out.println(Arrays.toString(a));
		System.out.println("\nOUTPUT");
		int[][] rs = kClosest(points, k);
		for(int[] a: rs)
			System.out.println(Arrays.toString(a));
		System.out.println();
	}

	//time: O(nlogk), space: O(k)
	public static int[][] kClosest(int[][] points, int k) {
		if(points.length == 0 || points == null) return new int[0][0];
		
		int[][] rs = new int[k][2];
		PriorityQueue<int[]> maxHeap = new PriorityQueue<>( //using maxHeap since we need to filter out all the largest distance
			(p1, p2) -> getDistWithoutSqrt(p2) - getDistWithoutSqrt(p1)
		);
		
		for(int i = 0; i < points.length; ++i) {
			maxHeap.add(points[i]);
			if(maxHeap.size() == k + 1) maxHeap.remove(); //filter
		}
		
		for(int i = 0; i < k; ++i)
			rs[i] = maxHeap.poll();

		return rs;
	}

	private static int getDistWithoutSqrt(int[] point) {
		return point[0] * point[0] + point[1] * point[1];
	}
}
