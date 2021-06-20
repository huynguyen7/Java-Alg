import java.util.Arrays;

/**
 * Timsort implementation (For academia purpose only!).
 * Source: https://en.wikipedia.org/wiki/Timsort
 */

public class TimSort {
    public static void main(String[] args) {
        Integer[] nums1 = {4,1,1,1};
        showResults(nums1);

        Integer[] nums2 = {
            1,2,3,1,1,1,3,4,
            1,3,4,1,2,4,1,2,
            3,2,1,2,3,2,2,1,
            1,1,1,1,3,91,193,
            100,4,45,23,5,2123,12,
            10,108,128,4,42,22,431,4};
        showResults(nums2);

        Integer[] nums3 = {1,4,1,2,9,1};
        showResults(nums3);

        Integer[] nums4 = {1,2,3,4};
        showResults(nums4);
    }

    private static void showResults(Comparable[] data) {
        System.out.println("\t----ShowResults----");
        System.out.println(Arrays.toString(data));
        timSort(data);
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

    private static void binaryInsertionSort(Comparable[] data, int from, int to) {
        if(data == null || data.length <= 1) return;

        for(int i = from; i <= to; ++i) {
            Comparable val = data[i];
            int idx = binarySearch(data, val, from, i);
            for(int j = i-1; j > idx; --j)
                data[j+1] = data[j];
            data[idx] = val;
        }
    }

    private static void merge(Comparable[] data, int left, int mid, int right) {
        final int m = mid;
        final int n = right;
        int i = left, j = mid+1, k = 0;

        Comparable[] rs = new Comparable[right-left+1];

        while(i <= m || j <= n) {
            if(i > m) rs[k++] = data[j++];
            else if(j > n) rs[k++] = data[i++];
            else if(data[i].compareTo(data[j]) >= 0) rs[k++] = data[j++];
            else rs[k++] = data[i++];
        }

        for(i = left; i <= right; ++i)
            data[i] = rs[i-left];
    }

    private static final int RUN = 32;
    
    // Time: O(n), space: O(n)
    public static void timSort(Comparable[] data) {
        if(data == null || data.length <= 1) return;

        final int n = data.length;

        for(int i = 0; i < n; i+=RUN)
            binaryInsertionSort(data, i, Math.min(i+RUN-1, n-1));

        for(int size = RUN; size < n; size*=2) {
            for(int left = 0; left < n; left+=2*size) {
                int mid = left+size-1;
                int right = Math.min(left+2*size-1, n-1);
                if(mid < right)
                    merge(data, left, mid, right); // space: O(n)
            }
        }
    }

    private static void swap(Comparable[] data, int i, int j) {
        Comparable tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    private static boolean isSorted(Comparable[] data, int from, int to) {
        for(int i = from+1; i <= to; ++i) {
            if(data[i].compareTo(data[i-1]) < 0)
                return false;
        }

        return true;
    }

    private static boolean isSorted(Comparable[] data) {
        for(int i = 1; i < data.length; ++i) {
            if(data[i].compareTo(data[i-1]) < 0)
                return false;
        }

        return true;
    }
}
