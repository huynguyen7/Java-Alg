//leetcode 258.
public class AddDigits {
	public static void main(String args[]) {
		int num = Integer.parseInt(args[0]);
		System.out.printf("%d -> %d\n", num, addDigits(num));
	}

	//time: O(), space: O()
	public static int addDigits(int num) {
		int rs = 0;
		while(numDigits(num) != 1)
			num = sumDigits(num);
		
		return num;
	}

	private static int sumDigits(int num) {
		int sum = 0;
		while(num != 0) {
			sum += num % 10;
			num /= 10;
		}

		return sum;
	}

	private static int numDigits(int num) {
		if(num == 0) return 1;
		return (int) (Math.log10(num) + 1);
	}
}
