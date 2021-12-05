import java.util.*;

// leetcode 1331.
public class RankTransformOfAnArray {
    public static void main(String[] args) {
        showResults(new int[] {40,10,20,30}); // expect [4,1,2,3]
        showResults(new int[] {100,100,100}); // expect [1,1,1]
        showResults(new int[] {37,12,28,9,100,56,80,5,12}); // expect [5,3,4,2,8,6,7,1,3]
    }

    private static void showResults(int[] arr) {
        System.out.println("\t----ShowResults----");
        System.out.printf("%s\n%s\n\n", Arrays.toString(arr), Arrays.toString(arrayRankTransform(arr)));
    }

    // Time: O(n), space: O(n)
    public static int[] arrayRankTransform(int[] arr) {
        final int n = arr.length;
        int[] rs = new int[n];

        if(n == 0) return rs;

        Map<Integer, Deque<Integer>> mapIndices = new HashMap<>();

        for(int i = 0; i < n; ++i) {
            int num = arr[i];
            if(!mapIndices.containsKey(num)) {
                Deque<Integer> holder = new LinkedList<>();
                mapIndices.put(num, holder);
            }

            mapIndices.get(num).addLast(i);
        }

        Arrays.sort(arr); // time: O(nlogn)

        int rank = 1;
        int lastNum = arr[0];

        for(int num : arr) {
            if(num > lastNum)
                rank++;

            int index = mapIndices.get(num).removeFirst();
            rs[index] = rank;

            lastNum = num;
        }

        return rs;
    }
}
