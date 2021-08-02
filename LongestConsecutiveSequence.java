import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

// leetcode 128.
public class LongestConsecutiveSequence {
    public static void main(String[] args) {
        showResults(new int[] {100,4,200,1,3,2}); // expect 4
        showResults(new int[] {0,3,7,2,5,8,4,6,0,1}); // expect 9
        showResults(new int[] {1,2,0,1}); // expect 3
    }

    private static void showResults(int[] nums) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(nums));
        System.out.printf("%d\n\n", longestConsecutive(nums));
    }

    // Time: O(n), space: O(n)
    public static int longestConsecutive(int[] nums) {
        if(nums.length <= 1) return nums.length;

        int maxSeqLen = 1;
        Set<Integer> set = new HashSet<>();

        for(int num: nums)
            set.add(num);

        for(int num: set) {
            if(!set.contains(num-1)) {
                int currNum = num;
                int seqLen = 1;
                
                while(set.contains(currNum+1)) {
                    seqLen++;
                    currNum++;
                }

                maxSeqLen = Math.max(maxSeqLen, seqLen);
            }
        }

        return maxSeqLen;
    }

    // Time: O(nlogn), space: O(1)
    public static int longestConsecutiveII(int[] nums) {
        if(nums.length <= 1) return nums.length;

        Arrays.sort(nums); // time: O(nlogn)
        int maxSeqLen = 1, seqLen = 1;

        for(int i = 1; i < nums.length; ++i) {
            int j = i;
            while(j > 0) {
                if(nums[j]-1 == nums[j-1]) seqLen++;
                else if(nums[j] - nums[j-1] >= 2) break;
                j--;
            }
            maxSeqLen = Math.max(maxSeqLen, seqLen);
            seqLen = 1;
        }
        maxSeqLen = Math.max(maxSeqLen, seqLen);

        return maxSeqLen;
    }
}
