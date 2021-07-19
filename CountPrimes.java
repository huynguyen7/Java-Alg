// leetcode 204.
public class CountPrimes {
    public static void main(String[] args) {
        showResults(10); // expect 4
        showResults(0); // expect 0
        showResults(1); // expect 0
    }

    private static void showResults(int n) {
        System.out.println("\t----ShowResults----");
        System.out.printf("%d -> %d\n\n", n, countPrimes(n));
    }

    // Time: O(n*loglogn), space: O(n)
    public static int countPrimes(int n) {
        if(n <= 1) return 0;

        int[] buffer = new int[n+1];
        int i,j;

        int sqrt_n = (int) Math.sqrt(n);
        for(i = 2; i <= sqrt_n; ++i) {
            if(buffer[i] == 0) {
                for(j = i*i; j < n; j += i)
                    buffer[j] = 1;
            }
        }

        int count = 0;
        for(i = 2; i < n; ++i)
            count += buffer[i] == 1 ? 0 : 1;

        return count;
    }
}
