//leetcode 371.
public class SumOfTwoIntegers {
	public static void main(String args[]) {
		int a = Integer.parseInt(args[0]);
		int b = Integer.parseInt(args[1]);

		int sum = getSum(a, b);
		System.out.printf("%d + %d = %d\n", a, b, sum);
	}

	public static int getSum(int a, int b) {
		int carry = -1;
		while(carry != 0) {
			carry = a & b;
			a = a ^ b;
			b = carry << 1;
		}

		return a;
	}
}
