import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;

// leetcode 1496.
public class PathCrossing {
    public static void main(String args[]) {
        String s1 = "NES";
        showResults(s1); // expect false

        String s2 = "NESWW";
        showResults(s2); // expect true
    }

    private static void showResults(String s) {
        System.out.println("----ShowResults----");
        System.out.printf("%s -> %b\n\n", s, isPathCrossing(s));
    }

    // time: O(n), space: O(n)
    public static boolean isPathCrossing(String s) {
        Set<List<Integer>> set = new HashSet<>();
        ArrayList<Integer> at = new ArrayList<>();
        at.add(0);
        at.add(0);
        set.add(at);

        for (char c : s.toCharArray()) {
            if (c == 'N')
                at.set(0, at.get(0) + 1);
            else if (c == 'S')
                at.set(0, at.get(0) - 1);
            else if (c == 'E')
                at.set(1, at.get(1) + 1);
            else
                at.set(1, at.get(1) - 1);

            if (!set.contains(at))
                set.add(at);
            else
                return true;
        }

        return false;
    }
}
