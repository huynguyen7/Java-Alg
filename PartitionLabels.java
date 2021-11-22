import java.util.*;

public class PartitionLabels {
    public static void main(String[] args) {
        showResults("ab"); // expect [1,1]
        showResults("abab"); // expect [4]
        showResults("aabb"); // expect [2,2]
        showResults("ababcbacadefegdehijhklij"); // expect [9,7,8]
        showResults("eccbbbbdec"); // expect [10]
    }

    private static void showResults(String s) {
        System.out.println("\t----ShowResults----");
        System.out.printf("%s\n%s\n\n", s, partitionLabels(s).toString());
    }

    private static final int alphabetSize = 26;

    // Time: O(n), space: O(n)
    public static List<Integer> partitionLabels(String s) {
        final int n = s.length();
        List<Integer> rs = new ArrayList<>();
        int[] lastOccuredIndex = new int[alphabetSize];
        
        // Default index is -1, which means that char did not appear.
        for(int i = 0; i < alphabetSize; ++i)
            lastOccuredIndex[i] = -1;

        // Find the last occured index for each character.
        for(int i = 0; i < n; ++i) {
            int c = s.charAt(i)-0x61;
            lastOccuredIndex[c] = i;
        }

        int j = 0, lastPartitionedIndex = 0;
        for(int i = 0; i < n; ++i) {
            int c = s.charAt(i)-0x61;
            j = Math.max(j, lastOccuredIndex[c]);

            if(i == j) {
                rs.add(i-lastPartitionedIndex+1);
                lastPartitionedIndex = i+1;
            }
        }

        return rs;
    }
}
