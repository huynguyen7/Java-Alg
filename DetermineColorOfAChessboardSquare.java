// leetcode 1813.
public class DetermineColorOfAChessboardSquare {
    public static void main(String[] args) {
        showResults("a1"); // expect false
        showResults("h3"); // expect true
        showResults("c7"); // expect false
    }

    private static void showResults(String coordinates) {
        System.out.println("\t----ShowResults----");
        System.out.printf("%s -> %b\n\n", coordinates, squareIsWhite(coordinates));
    }

    // Time: O(1), space: O(1)
    public static boolean squareIsWhite(String coordinates) {
        if(coordinates == null || coordinates.length() != 2) return false;

        int col = (int) coordinates.charAt(0)-97;
        int row = (int) coordinates.charAt(1)-48;

        if(col % 2 == 0) {
            if(row % 2 == 0) return true;
            else return false;
        } else {
            if(row % 2 == 0) return false;
            else return true;
        }
    }
}
