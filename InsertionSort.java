import java.util.Arrays;

public class InsertionSort {
    public static void main(String[] args) {
        Integer[] nums1 = {1};
        showResults(nums1); 

        Integer[] nums2 = {2,1,3};
        showResults(nums2); 

        Integer[] nums3 = {4,1,1,1};
        showResults(nums3); 

        Integer[] nums4 = {1,1,2,4,1,9};
        showResults(nums4); 
    }

    private static void showResults(Comparable[] data) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(data));
        insertionSort(data);
        System.out.println(Arrays.toString(data) + "\n");
    }

    // In-place sort
    // Time: O(n^2), space: O(1)
    public static void insertionSort(Comparable[] data) {
        if(data == null || data.length == 0) return;

        for(int i = 1; i < data.length; ++i) {
            int j = i;
            while(j > 0 && data[j-1].compareTo(data[j]) > 0) {
                swap(data, j-1, j);
                j--;
            }
        }
    }

    private static void swap(Comparable[] data, int i, int j) {
        Comparable tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }
}
