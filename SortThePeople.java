import java.util.Arrays;

// leetcode 2418.
public class SortThePeople {
    public static void main(String[] args) {
        assertStringArrays(showResults(new String[] {"Mary","John","Emma"}, new int[] {180,165,170}), new String[] {"Mary","Emma","John"}); // expect ["Mary","Emma","John"]
        assertStringArrays(showResults(new String[] {"Alice","Bob","Bob"}, new int[] {155,185,150}), new String[] {"Bob","Alice","Bob"}); // expect ["Bob","Alice","Bob"]
    }

    private static String[] showResults(String[] names, int[] heights) {
        System.out.println("\t----ShowResults----");
        String[] rs = sortPeople(names, heights);
        System.out.printf("%s\n%s\n%s\n\n", Arrays.toString(heights), Arrays.toString(names), Arrays.toString(rs));
        return rs;
    }

    // Time: O(nlogn), space: O(n)
    public static String[] sortPeople(String[] names, int[] heights) {
        final int n = names.length;
        People[] people = new People[n];
        for(int i = 0; i < n; ++i) {
            people[i] = new People(names[i], heights[i]);
        }

        Arrays.sort(people, (a, b) -> Integer.compare(b.height, a.height));

        for(int i = 0; i < n; ++i)
            names[i] = people[i].name;

        return names;
    }

    private static boolean assertStringArrays(String[] a, String[] b) {
        assert(a.length == b.length);
        for(int i = 0; i < a.length; ++i) {
            if(a[i].compareTo(b[i]) != 0) return false;
        }

        return true;
    }

    static class People {
        String name;
        int height;
        People(String name, int height) {
            this.name = name;
            this.height = height;
        }
    }
}
