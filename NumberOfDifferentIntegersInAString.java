import java.util.Set;
import java.util.HashSet;

// leetcode 1805.
public class NumberOfDifferentIntegersInAString {
    public static void main(String[] args) {
        showResults("a123bc34d8ef34"); // expect 3
        showResults("leet1234code234"); // expect 2
        showResults("a1b01c001"); // expect 1
        showResults("0a0"); // expect 1
    }

    private static void showResults(String word) {
        System.out.println("\t----ShowResults----");
        System.out.println(word);
        System.out.printf("Results: %d\n\n", numDifferentIntegers(word));
    }

    // Time: O(n), space: O(n)
    public static int numDifferentIntegers(String word) {
        if(word == null || word.length() == 0) return 0;

        char[] cArr = word.toCharArray();
        Set<Integer> set = new HashSet<>();

        int count = 0, currNum = Integer.MIN_VALUE;
        boolean hasZero = false;
        for(int i = 0; i < cArr.length; i++) {
            char c = cArr[i];
            if(Character.isLetter(c)) {
                if(!set.contains(currNum))
                    set.add(currNum);
                currNum = Integer.MIN_VALUE;
            } else currNum = 10*currNum + ((int) c - 48);
        }

        if(currNum != Integer.MIN_VALUE) set.add(currNum);
        System.out.println(set.toString());

        return set.contains(Integer.MIN_VALUE) ? set.size()-1 : set.size();
    }
}
