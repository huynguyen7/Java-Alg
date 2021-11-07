import java.util.Arrays;

// leetcode 16.
public class ThreeSumClosest {
    public static void main(String[] args) {
        showResults(new int[] {-1,2,1,-4}, 1); // expect 2
        showResults(new int[] {0,0,0}, 1); // expect 0
    }

    private static void showResults(int[] nums, int target) {
        System.out.println("\t----ShowResults----");
        System.out.printf("%s\nTarget: %d\n-> %d\n\n", Arrays.toString(nums), target, threeSumClosest(nums, target));
    }

    // Time: O(nlogn + n^2), space: O(1)
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums); // time: O(nlogn)
        int ans = nums[0]+nums[1]+nums[2];
        int minDiff = Math.abs(ans-target);

        final int n = nums.length;

        for(int i = 0; i < n; ++i) {
            int j = i+1, k = n-1;
            while(j < k) {
                int sum = nums[i]+nums[j]+nums[k];
                int diff = Math.abs(sum-target);

                if(sum > target) k--;
                else if(sum < target) j++;
                else return sum; // Found exact math.

                if(diff < minDiff) {
                    minDiff = diff;
                    ans = sum;
                }
            }
        }

        return ans;
    }

    // Brute-force approach.
    // Time: O(n^3), space: O(1)
    public static int threeSumClosestII(int[] nums, int target) {
        int minDiff = Integer.MAX_VALUE;
        int ans = target;
        
        for(int i = 0; i < nums.length; ++i) {
            for(int j = 0; j < nums.length; ++j) {
                for(int k = 0; k < nums.length; ++k) {
                    if(i == j || j == k || i == k) continue;

                    int sum = (nums[i]+nums[j]+nums[k]); 
                    int diff = Math.abs(sum-target);
                    if(diff < minDiff) {
                        minDiff = diff;
                        ans = sum;
                    }
                }
            }
        }

        return ans;
    }
}
