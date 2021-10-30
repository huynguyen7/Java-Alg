public class CountBinarySubstrings {
    public static void main(String[] args) {
        showResults("00110011"); // expect 6
        showResults("10101"); // expect 4
    }

    private static void showResults(String s) {
        System.out.println("\t----ShowResults----");
        System.out.printf("%s\n-> %d\n\n", s, countBinarySubstrings(s));
    }

    // Time: O(n), space: O(n)
    public static int countBinarySubstrings(String s) {
        if(s.length() < 2) return 0;

        final int n = s.length();
        int[] groups = new int[s.length()];
        int count = 0, t = 0;

        groups[0] = 1;
        for(int i = 1 ; i < s.length(); ++i) {
            if(s.charAt(i) != s.charAt(i-1)) groups[++t] = 1;
            else groups[t] += 1;
        }

        for(int i = 1; i < s.length(); ++i)
            count += Math.min(groups[i], groups[i-1]);

        return count;
    }

    // Brute-force approach.
    // Time: O(n^2), space: O(n^2)
    public static int countBinarySubstringsII(String s) {
        if(s.length() < 2) return 0;
        
        final int n = s.length();
        int count = 0;

        for(int i = 0; i < n; ++i) {
            int remainingLength = n-i;
            int length = 2;
            while(length <= remainingLength) {
                String subS = s.substring(i,length+i);
                if(isValid(subS)) {
                    count += 1;
                }
                length += 2;
            }
        }

        return count;
    }

    private static boolean isValid(String s) {
        int low = 0, high = s.length()-1;
        int numZeros = 0, numOnes = 0;

        boolean flag = true;
        if(s.charAt(0) == '0') {
            while(low < high) { 
                if(!flag) break;
                if(s.charAt(low++) == '0') numZeros += 1;
                else return false;
                if(s.charAt(high--) == '1') numOnes += 1;
                else return false;
            }
        } else {
            while(low < high) { 
                if(!flag) break;
                if(s.charAt(low++) == '1') numOnes += 1;
                else return false;
                if(s.charAt(high--) == '0') numZeros += 1;
                else return false;
            }
        }

        return numZeros == numOnes;
    }
}
