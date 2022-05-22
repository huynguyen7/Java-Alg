import java.util.*;

// leetcode 2244.
public class MinimumRoundsToCompleteAllTasks {
    public static void main(String[] args) {
        assert(showResults(new int[] {2,2,3,3,2,4,4,4,4,4}) == 4); // expect 4
        assert(showResults(new int[] {2,3,3}) == -1); // expect -1
        assert(showResults(new int[] {5,5,5,5}) == 2); // expect 2
    }

    private static int showResults(int[] tasks) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(tasks));
        int rs = minimumRounds(tasks);
        System.out.printf("%d\n\n", rs);
        return rs;
    }

    // n is the number of tasks, k is the number of unique task levels.
    // Time: O(nlogk), space: O(n)
    public static int minimumRounds(int[] tasks) {
        final int n = tasks.length;
        int numRounds = 0;

        PriorityQueue<Counter> minHeap = new PriorityQueue<>((a,b) -> Integer.compare(a.val, b.val));
        Map<Integer, Integer> counts = new HashMap<>();
        for(int i = 0; i < n; ++i)
            counts.put(tasks[i], counts.getOrDefault(tasks[i],0)+1);

        for(Map.Entry<Integer, Integer> entry: counts.entrySet())
            minHeap.add(new Counter(entry.getKey(), entry.getValue()));

        while(!minHeap.isEmpty()) {
            Counter c = minHeap.poll();
            while(c.count > 0) {
                if(c.count == 1) return -1;
                else if(c.count > 3) {
                    if(c.count % 3 == 0)
                        c.count -= 3;
                    else c.count -= 2;
                } else if(c.count == 2 || c.count == 3) c.count = 0;
                numRounds += 1;
            }
        }

        return numRounds;
    }

    private static class Counter {
        int val;
        int count;

        private Counter() {}

        public Counter(int val, int count) {
            this.val = val;
            this.count = count;
        }
    }
}
