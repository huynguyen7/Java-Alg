import java.util.Arrays;

public class BinaryInsertionSort {
    public static void main(String[] args) {
        Integer[] nums1 = {9,4};
        showResults(nums1);

        Integer[] nums2 = {9,5,1,1,1};
        showResults(nums2);

        Integer[] nums3 = {1,1,1};
        showResults(nums3);

        Integer[] nums4 = {1,2,3};
        showResults(nums4);
    }

    private static void showResults(Comparable[] data) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(data));
        binaryInsertionSort(data);
        System.out.println(Arrays.toString(data));
        System.out.println(isSorted(data));
    }

    private static int binarySearch(Comparable[] data, Comparable target, int from, int to) {
        int low = from, high = to;
        int mid = 0;
        while(low <= high) {
            mid = (low+high)/2;
            int compare = target.compareTo(data[mid]);
            if(compare == 0) return mid;
            else if(compare > 0) low = mid+1;
            else high = mid-1;
        }

        return mid;
    }

    public static void binaryInsertionSort(Comparable[] data) {
        if(data == null || data.length <= 1) return;
        
        for(int i = 1; i < data.length; ++i) {
            Comparable val = data[i];
            int idx = binarySearch(data, val, 0, i);
            for(int j = i-1; j >= idx; --j)
                data[j+1] = data[j];
            data[idx] = val;
        }
    }


    private static boolean isSorted(Comparable[] data) {
        for(int i = 1; i < data.length; ++i) {
            if(data[i].compareTo(data[i-1]) < 0) return false;
        }
        return true;
    }
}
