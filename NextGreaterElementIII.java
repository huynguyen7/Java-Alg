public class NextGreaterElementIII {
    public static void main(String[] args) {
        showResults(12); // expect 21
        showResults(21); // expect -1
        showResults(123); // expect 132
        showResults(5122); // expect 5212
        showResults(7213); // expect 7123
    }

    private static void showResults(int n) {
        System.out.println("\t----ShowResults----");
        System.out.printf("%d -> %d\n\n", n, nextGreaterElement(n));
    }

    // Time: O(n), space: O(n)
    public static int nextGreaterElement(int n) {
        char[] digits = String.valueOf(n).toCharArray();
        int i = digits.length-1;

        int index = -1;
        while(i >= 1) { // Find smaller digit from the right.
            if(digits[i] > digits[i-1]) {
                index = i-1;
                break;
            }
            i--;
        }
        if(i == 0) return -1;

        int j = digits.length-1;
        while(j >= 0) {
            if(digits[index] < digits[j]) {
                // Swap digit at index and j.
                swap(digits, index, j);
                break;
            }
            j--;
        }

        long rs = Long.parseLong(new String(reverse(digits, index+1)));

        return rs > Integer.MAX_VALUE ? -1 : (int) rs;
    }

    // Time: O(n), space: O(1)
    private static char[] reverse(char[] charArray, int i) {
        int j = charArray.length-1;
        while(i < j)
            swap(charArray, i++, j--);
        return charArray;
    }

    // Time: O(1), space: O(1)
    private static void swap(char[] charArray, int i, int j) {
        char tmp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = tmp;
    }
}
