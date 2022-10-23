import java.util.Arrays;

// leetcode 2446.
public class DetermineIfTwoEventsHaveConflict {
    public static void main(String[] args) {
        assert(showResults(new String[] {"01:15","02:00"}, new String[] {"02:00","03:00"}) == true); // expect true
        assert(showResults(new String[] {"01:00","02:00"}, new String[] {"01:20","03:00"}) == true); // expect true
        assert(showResults(new String[] {"10:00","11:00"}, new String[] {"14:00","15:00"}) == false); // expect false
    }

    private static boolean showResults(String[] event1, String[] event2) {
        System.out.println("\t----ShowResults----");
        boolean rs = haveConflict(event1, event2);
        System.out.printf("%s %s\n%b\n\n", Arrays.toString(event1), Arrays.toString(event2), rs);
        return rs;
    }

    // Time: O(1), space: O(1)
    public static boolean haveConflict(String[] event1, String[] event2) {
        int startEvent1 = parseStringTimeToIntInMinutes(event1[0]);
        int endEvent1 = parseStringTimeToIntInMinutes(event1[1]);
        int startEvent2 = parseStringTimeToIntInMinutes(event2[0]);
        int endEvent2 = parseStringTimeToIntInMinutes(event2[1]);

        if(startEvent2 > endEvent1) return false;
        if(startEvent1 > endEvent2) return false;
        return true;
    }

    private static int parseStringTimeToIntInMinutes(String time) {
        String hourS = time.substring(0, 2);
        String minuteS = time.substring(3, 5);
        return Integer.parseInt(hourS)*60+Integer.parseInt(minuteS);
    }
}
