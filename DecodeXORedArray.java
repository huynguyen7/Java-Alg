import java.util.Arrays;

public class DecodeXORedArray {
	public static void main(String[] args) {
		int[] encoded1 = {1,2,3};
		int first1 = 1;
		showResults(encoded1, first1); // expect [1,0,2,1]

		int[] encoded2 = {6,2,7,3};
		int first2 = 4;
		showResults(encoded2, first2); // expect [4,2,0,7,4]
	}

	private static void showResults(int[] encoded, int first) {
		System.out.println("\t----ShowResults----");
		System.out.printf("FIRST = %d\n", first);
		System.out.println(Arrays.toString(encoded));
		int[] rs = decode(encoded, first);
		System.out.println(Arrays.toString(rs) + "\n");
	}

	// Time: O(n), space: O(n)
	public static int[] decode(int[] encoded, int first) {
		final int n = encoded.length;
		int[] rs = new int[encoded.length + 1];
		rs[0] = first;

		for(int i = 0; i < n; ++i)
			rs[i + 1] = rs[i] ^ encoded[i];
		
		return rs;
	}
}
