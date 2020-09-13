import java.util.List;
import java.util.ArrayList;

//leetcode 728.
public class SelfDividingNumbers {
	public static void main(String args[]) {
		int l1 = 1, r1 = 22;
		showResults(l1, r1); //expect {1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22}

		int l2 = 696, r2 = 700;
		showResults(l2, r2);
	}
	
	private static void showResults(int left, int right) {
		System.out.println("\n-----ShowResults-----");
		System.out.printf("left: %d, right: %d\n", left, right);
		
		List<Integer> rs = selfDividingNumbers(left, right);
		System.out.println(rs.toString() + "\n");
	}

	//time: O(n*m), space: O(n); 
	//n is total elements from left to right
	//m is the number of digits for each n value
	public static List<Integer> selfDividingNumbers(int left, int right) {
		List<Integer> rs = new ArrayList<>();

		int numDigits, digit, val, num;
		boolean isValidate = true;
		for(val = left ; val <= right; ++val) { //time: O(n)
			num = val;
			numDigits = (int) Math.log10(num) + 1;
			while(num != 0 && numDigits != 0) { //time: O(m)
				digit = num % 10;
				num /= 10; 
				if(digit == 0 || val % digit != 0) {
					isValidate = false;
					break;
				}
			}
			
			if(isValidate) rs.add(val);
			isValidate = true;
		}

		return rs;
	}
}
