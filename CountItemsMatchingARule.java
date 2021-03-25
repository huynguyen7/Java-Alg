import java.util.List;
import java.util.ArrayList;

// leetcode 1773.
public class CountItemsMatchingARule {
    public static void main(String[] args) {
        String[][] items1 = {
            {"phone","blue","pixel"},
            {"computer","silver","lenovo"},
            {"phone","gold","iphone"}
        };
        showResults(items1, "color", "silver"); // expect 1

        String[][] items2 = {
            {"phone","blue","pixel"},
            {"computer","silver","phone"},
            {"phone","gold","iphone"} 
        };
        showResults(items2, "type", "phone"); // expect 2
    }

    private static void showResults(String[][] items, String ruleKey, String ruleValue) {
        System.out.println("\t----ShowResults----");
        List<List<String>> input = new ArrayList<>();
        List<String> tmp;
        for(String[] item: items) {
            tmp = new ArrayList<>();
            for(String type: item)
                tmp.add(type);
            input.add(tmp);
            System.out.println(tmp.toString());
        }

        int rs = countMatches(input, ruleKey, ruleValue);
        System.out.printf("RS: %d\n\n", rs);
    }

    // Time: O(n), space: O(1)
    public static int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        final String type = "type";
        final String color = "color";
        final String name = "name";

        int count = 0;
        for(List<String> item: items) {
            if(ruleKey.compareTo(type) == 0 && ruleValue.compareTo(item.get(0)) == 0) count++;
            else if(ruleKey.compareTo(color) == 0 && ruleValue.compareTo(item.get(1)) == 0) count++;
            else if(ruleKey.compareTo(name) == 0 && ruleValue.compareTo(item.get(2)) == 0) count++;
        }

        return count;
    }
}
