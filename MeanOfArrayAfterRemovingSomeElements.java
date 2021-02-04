import java.util.Arrays;

// leetcode 1619.
public class MeanOfArrayAfterRemovingSomeElements {
    public static void main(String[] args) {
        int[] arr1 = {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3};
        showResults(arr1); // expect 2.0

        int[] arr2 = {6,2,7,5,1,2,0,3,10,2,5,0,5,5,0,8,7,6,8,0};
        showResults(arr2); // expect 4

        int[] arr3 = {6,0,7,0,7,5,7,8,3,4,0,7,8,1,6,8,1,1,2,4,8,1,9,5,4,3,8,5,10,8,6,6,1,0,6,10,8,2,3,4};
        showResults(arr3); // expect 4.77778

        int[] arr4 = {9,7,8,7,7,8,4,4,6,8,8,7,6,8,8,9,2,6,0,0,1,10,8,6,3,3,5,1,10,9,0,7,10,0,10,4,1,10,6,9,3,6,0,0,2,7,0,6,7,2,9,7,7,3,0,1,6,1,10,3};
        showResults(arr4); // expect 5.27778

        int[] arr5 = {4,8,4,10,0,7,1,3,7,8,8,3,4,1,6,2,1,1,8,0,9,8,0,3,9,10,3,10,1,10,7,3,2,1,4,9,10,7,6,4,0,8,5,1,2,1,6,2,5,0,7,10,9,10,3,7,10,5,8,5,7,6,7,6,10,9,5,10,5,5,7,2,10,7,7,8,2,0,1,1};
        showResults(arr5); // expect 5.29167
    }

    private static void showResults(int[] arr) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(arr));
        double rs = trimMean(arr);
        System.out.printf("Results: %.5f\n\n", rs);
    }

    // Time: O(n), space: O(1)
    public static double trimMean(int[] arr) {
        if(arr == null || arr.length == 0) return 0;
        Arrays.sort(arr);

        int n = arr.length;
        final int start = n * 5/100;
        int end = n * 95/100;
        if(end > 0) end--;
        int m = end - start + 1;

        double sum = 0;
        for(int i = start; i <= end; ++i)
            sum += arr[i];

        return sum != 0 ? sum / m : 0;
    }
}
