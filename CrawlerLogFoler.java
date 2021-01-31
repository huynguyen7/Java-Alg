import java.util.Arrays;

// leetcode 844.
public class CrawlerLogFoler {
    public static void main(String[] args) {
        String[] logs1 = {"d1/","d2/","../","d21/","./"};
        showResults(logs1); // expect 2

        String[] logs2 = {"d1/","d2/","./","d3/","../","d31/"};
        showResults(logs2); // expect 3

        String[] logs3 = {"d1/","../","../","../"};
        showResults(logs3); // expect 0

        String[] logs4 = {"./","../","./"};
        showResults(logs4); // expect 0
    }

    private static void showResults(String[] logs) {
        System.out.println("----ShowResults----");
        System.out.println(Arrays.toString(logs));
        int rs = minOperations(logs);
        System.out.printf("Min operations: %d\n\n", rs);
    }

    // Time: O(n), space: O(1)
    public static int minOperations(String[] logs) {
        if(logs == null || logs.length == 0) return 0;

        final String back = "../";
        final String curr = "./";
        int count = 0;
        for(int i = 0; i < logs.length; ++i) {
            if(logs[i].compareTo(back) == 0) {
                if(count > 0) count--;
                else continue;
            }
            else if(logs[i].compareTo(curr) == 0) continue;
            else count++;
        }

        return count >= 0 ? count : 0;
    }
}
