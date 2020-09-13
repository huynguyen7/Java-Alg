import java.util.Arrays;
import java.util.PriorityQueue; //min heap
import java.util.Collections;

//leetcode 1046.
public class LastStoneWeight {
	public static void main(String args[]) {
		int[] stones1 = {2,7,4,1,8,1};
		showResults(stones1); //expect 1
	}

	private static void showResults(int[] stones) {
		System.out.println("----ShowResults----");
		System.out.println(Arrays.toString(stones));

		int rs = lastStoneWeight(stones);
		System.out.printf("Last stone weight: %d\n", rs);
	}

	//time: O(nlogn), space: O(n)
	public static int lastStoneWeight(int[] stones) {
		PriorityQueue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());
		for(int i = 0; i < stones.length; ++i)
			heap.add(stones[i]);
		
		Integer x = null, y = null;
		while(!heap.isEmpty()) {
			x = heap.poll();
			y = heap.poll();
			
			System.out.println("x: " + x + " y: " + y);
			
			if(x != null && y != null)
				if(Math.abs(x - y) != 0)
					heap.add(Math.abs(x - y));
		}
		
		if(y == null) return x;
		return 0;
	}
}
