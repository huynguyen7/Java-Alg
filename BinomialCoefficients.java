import java.util.Arrays;

// element-prog 17.4
public class BinomialCoefficients {
	public static void main(String[] args) {
		int n1 = 3;
		int k1 = 2;
		showResults(n1, k1); // expect 3

		int n2 = 10;
		int k2 = 3;
		showResults(n2, k2); // expect 120

		int n3 = 1;
		int k3 = 1;
		showResults(n3, k3); // expect 1
	}

	private static void showResults(int n, int k) {
		System.out.println("----ShowResults----");
		int rs = computeBinomialCoefficient(n, k);
		System.out.printf("n = %d, k = %d\nResult = %d\n\n",
						n, k, rs);
	}
	
	// Binomial Coefficient of (n,k) = (n!) / (k! * (n-k)!)
	// Formula: Coefficient of (n,k) = (n - 1, k) + (n - 1, k - 1)
	
	// Constraints:
	// n >= k

	// DP approach.
	// top-down DP.
	// Time: O(), space: O()
	public static int computeBinomialCoefficient(int n, int k) {
		return dfs(n, k, new int[n + 1][k + 1]);
	}

	private static int dfs(int n, int k, int[][] nChooseK) {
		if(k == 0 || n == k) return 1;
		
		if(nChooseK[n][k] == 0) { // not yet explore
			int notChooseK = dfs(n - 1, k, nChooseK);
			int chooseK = dfs(n - 1, k - 1, nChooseK);
			nChooseK[n][k] = notChooseK + chooseK;
		}

		return nChooseK[n][k];
	}
}
