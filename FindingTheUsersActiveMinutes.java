import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

// leetcode 1817.
public class FindingTheUsersActiveMinutes {
    public static void main(String[] args) {
        int[][] logs1 = {{0,5},{1,2},{0,2},{0,5},{1,3}};
        int k1 = 5;
        showResults(logs1, k1); // expect [0,2,0,0,0]

        int[][] logs2 = {{1,1},{2,2},{2,3}};
        int k2 = 4;
        showResults(logs2, k2); // expect [1,1,0,0]
    }

    private static void showResults(int[][] logs, int k) {
        System.out.println("\t----ShowResults----");
        for(int[] log: logs)
            System.out.printf("%s ", Arrays.toString(log));
        System.out.printf("\nk = %d\nOutput = %s\n\n", k, Arrays.toString(findingUsersActiveMinutes(logs, k)));
    }

    // Time: O(n), space: O(n)
    public static int[] findingUsersActiveMinutes(int[][] logs, int k) {
        int[] answer = new int[k];
        Map<Integer, Set<Integer>> map = new HashMap<>();

        for(int i = 0; i < logs.length; ++i) {
            int id = logs[i][0];
            int minute = logs[i][1];
            if(map.get(id) == null) map.put(id, new HashSet<>());
            map.get(id).add(minute);
        }

        for(Map.Entry<Integer,Set<Integer>> pair: map.entrySet()) {
            int id = pair.getKey();
            int uam = pair.getValue().size();
            answer[uam-1]++;
        }

        return answer;
    }
}
