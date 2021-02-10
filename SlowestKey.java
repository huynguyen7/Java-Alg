import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

// leetcode 1629.
public class SlowestKey {
    public static void main(String[] args) {
        int[] releaseTimes1 = {9,29,49,50};
        String keysPressed1 = "cbcd";
        showResults(releaseTimes1, keysPressed1); // expect 'c'

        int[] releaseTimes2 = {12,23,36,46,62};
        String keysPressed2 = "spuda";
        showResults(releaseTimes2, keysPressed2); // expect 'a'

        int[] releaseTimes3 = {23,34,43,59,62,80,83,92,97};
        String keysPressed3 = "qgkzzihfc";
        showResults(releaseTimes3, keysPressed3); // expect 'q'

        int[] releaseTimes4 = {1,2};
        String keysPressed4 = "ba";
        showResults(releaseTimes4, keysPressed4); // expect 'b'
    }

    private static void showResults(int[] releaseTimes, String keysPressed) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(releaseTimes));
        System.out.printf("%s -> %c\n\n", keysPressed, slowestKey(releaseTimes, keysPressed));
    }

    // Time: O(n), space: O(1)
    public static char slowestKey(int[] releaseTimes, String keysPressed) {
        char maxChar = keysPressed.charAt(0);
        int maxTime = releaseTimes[0];

        for(int i = 1; i < releaseTimes.length; ++i) {
            int currTime = releaseTimes[i] - releaseTimes[i - 1];
            
            if(currTime > maxTime) {
                maxTime = currTime;
                maxChar = keysPressed.charAt(i);
            } else if(currTime == maxTime && keysPressed.charAt(i) > maxChar)
                maxChar = keysPressed.charAt(i);
        }

        return maxChar;
    }
}
