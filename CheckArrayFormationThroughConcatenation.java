import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

// leetcode 1640.
public class CheckArrayFormationThroughConcatenation {
    public static void main(String[] args) {
        int[] arr1 = {85};
        int[][] pieces1 = {{85}};
        showResults(arr1, pieces1); // expect true
        
        int[] arr2 = {15,88};
        int[][] pieces2 = {{88},{15}};
        showResults(arr2, pieces2); // expect true

        int[] arr3 = {49,18,16};
        int[][] pieces3 = {{16,18,49}};
        showResults(arr3, pieces3); // expect false

        int[] arr4 = {91,4,64,78};
        int[][] pieces4 = {{78},{4,64},{91}};
        showResults(arr4, pieces4); // expect true

        int[] arr5 = {1,3,5,7};
        int[][] pieces5 = {{2,4,6,8}};
        showResults(arr5, pieces5); // expect false
    }

    private static void showResults(int[] arr, int[][] pieces) {
        System.out.println("\t----ShowResults----");
        System.out.printf("Arr:\t%s\n", Arrays.toString(arr));
        System.out.print("Pieces:\t");
        for(int[] row: pieces)
            System.out.printf("%s ", Arrays.toString(row));

        boolean rs = canFormArray(arr, pieces);
        System.out.printf("\nRESULTS: %b\n\n", rs);
    }

    // Time: O(n), space: O(n)
    public static boolean canFormArray(int[] arr, int[][] pieces) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < arr.length; ++i)
            map.put(arr[i], i);

        for(int[] piece: pieces) {
            int index = map.getOrDefault(piece[0], -1);
            if(index == -1) return false;
            else {
                for(int i = 1; i < piece.length; ++i) {
                    if(!map.containsKey(piece[i])) return false;
                    else if(!(map.get(piece[i]) == index + 1)) return false;
                    else index++;
                }
            }
        }
        
        return true;
    }
}
