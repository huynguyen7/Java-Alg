import java.util.*;

// leetcode 2103.
public class RingsAndRods {
    public static void main(String[] args) {
        assert(showResults("B0B6G0R6R0R6G9") == 1); // expect 
        assert(showResults("B0R0G0R9R0B0G0") == 1); // expect 
        assert(showResults("G4") == 0); // expect 
    }

    private static int showResults(String rings) {
        System.out.println("\t----ShowResults----");
        int rs = countPoints(rings);
        System.out.printf("%s\n%d\n\n", rings, rs);
        return rs;
    }

    private static int rgbCharToInt(char c) {
        return c == 'R' ? 0 :
               c == 'G' ? 1 :
                          2 ;
    }

    // Time: O(n), space: O(1)
    public static int countPoints(String rings) {
        boolean[] R = new boolean[10];
        boolean[] G = new boolean[10];
        boolean[] B = new boolean[10];

        int count = 0;

        for(int i = 0; i < rings.length(); i += 2) {
            int color = rgbCharToInt(rings.charAt(i));
            int rodIndex = rings.charAt(i+1) - 0x30;

            if(color == 0) R[rodIndex] = true;
            else if(color == 1) G[rodIndex] = true;
            else B[rodIndex] = true;
        }

        for(int i = 0; i < 10; ++i) {
            boolean haveAllThreeColors = R[i] && G[i] && B[i];
            if(haveAllThreeColors) count++;
        }

        return count;
    }
}
