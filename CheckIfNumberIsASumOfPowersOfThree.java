// leetcode 1780.
public class CheckIfNumberIsASumOfPowersOfThree {
    public static void main(String[] args) {
        showResults(12); // expect true
        showResults(91); // expect true
        showResults(21); // expect false
    }

    private static void showResults(int n) {
        System.out.println("\t----ShowResults----");
        System.out.printf("%d -> %b\n\n", n, checkPowersOfThree(n));
    }

    // Time: O(), space: O()
    public static boolean checkPowersOfThree(int n) {
        if(n <= 0) return false;
        return dfs(n, 0, 0);
    }

    private static boolean dfs(int n, int currSum, int currBase) {
        if(currSum > n) return false;
        else if(currSum == n) return true;

        for(int base = currBase; base < 15; ++base) {
            if(dfs(n, currSum+(int) Math.pow(3,base), base+1))
                return true;
        }

        return false;
    }
}
