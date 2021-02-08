import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

// leetcode 884.
public class UncommonWordsFromTwoSentences {
    public static void main(String[] args) {
        String A1 = "this apple is sweet";
        String B1 = "this apple is sour";
        showResults(A1, B1); // expect ["sweet", "sour"]

        String A2 = "apple apple";
        String B2 = "banana";
        showResults(A2, B2); // expect ["banana"]
    }

    private static void showResults(String A, String B) {
        System.out.println("\t----ShowResults----");
        System.out.println("A: " + A);
        System.out.println("B: " + B);
        String[] rs = uncommonFromSentences(A, B);
        System.out.printf("%s\n\n", Arrays.toString(rs));
    }

    // Time: O(n), space: O(n)
    public static String[] uncommonFromSentences(String A, String B) {
        if(A.length() == 0 && B.length() == 0) return new String[0];

        String[] words1 = A.split(" ");
        String[] words2 = B.split(" ");
        Map<String, Integer> map = new HashMap<>();

        for(int i = 0; i < words1.length; ++i)
            map.put(words1[i], map.getOrDefault(words1[i], 0) + 1);

        for(int i = 0; i < words2.length; ++i)
            map.put(words2[i], map.getOrDefault(words2[i], 0) + 1);

        List<String> rs = new ArrayList<>();
        for(Map.Entry<String, Integer> entry: map.entrySet()) {
            if(entry.getValue() == 1)
                rs.add(entry.getKey());
        }
        
        String[] ans = new String[rs.size()];
        for(int i = 0; i < rs.size(); ++i)
            ans[i] = rs.get(i);

        return ans;
    }
}
