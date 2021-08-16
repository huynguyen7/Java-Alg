import java.util.*;

// leetcode 457.
public class CircularArrayLoop {
    public static void main(String[] args) {
        showResults(new int[] {2,-1,1,2,2}); // expect true
        showResults(new int[] {-1,2}); // expect false
        showResults(new int[] {-2,1,-1,-2,-2}); // expect false
        showResults(new int[] {-1,-2,-3,-4,-5}); // expect false
        showResults(new int[] {2,2,2,2,2,4,7}); // expect false
        showResults(new int[] {1,1,2}); // expect true
        showResults(new int[] {2,-1,2,-1,3}); // expect true
    }

    private static void showResults(int[] nums) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(nums));
        System.out.printf("%b\n\n", circularArrayLoop(nums));
    }

    // Time: O(n), space: O(n)
    public static boolean circularArrayLoop(int[] nums) {
        if(nums == null || nums.length <= 1) return false;

        final int n = nums.length;
        Map<Integer, Integer> seq = new HashMap<>();

        int j, lastIndex;
        int index = 0;

        for(int i = 0; i < n; ++i) {
            index = i;
            lastIndex = index;
            j = 0;
            boolean flag = nums[index] > 0;
            boolean check = true;
            while(j++ < n) {
                if((nums[index] > 0) != flag) {
                    check = false;
                    break;
                }
                lastIndex = index;
                index = getNextIndex(n, nums[lastIndex], lastIndex);
                seq.put(lastIndex, index);
                if(seq.containsKey(index))
                    break;
            }
            
            if(!check) {
                seq.clear();
                continue;
            }
            //System.out.println(seq.toString());
            if(seq.size() > 1 && seq.get(lastIndex) == i) return true;
            seq.clear();
        }

        return false;
    }

    private static int getNextIndex(int arrLength, int currValue, int currIndex) {
        
        int i = (currIndex+currValue)%arrLength;
        i = i < 0 ? arrLength+i : i;
        return i;

        //int i = currValue+currIndex;
        //if(i >= arrLength) return 0;
        //else if(i < 0) return arrLength-1;
        //return i;
    }
}
