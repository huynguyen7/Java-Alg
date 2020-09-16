import java.util.Arrays;

//leetcode 744.
//input char[] is sorted
public class FindSmallestLetterGreaterThanTarget {
  public static void main(String args[]) {
    char[] letters1 = { 'c', 'f', 'j' };
    char target1 = 'a';
    showResults(letters1, target1); // expect 'c'

    char target2 = 'c';
    showResults(letters1, target2); // expect 'f'

    char target3 = 'd';
    showResults(letters1, target3); // expect 'f'

    char target4 = 'g';
    showResults(letters1, target4); // expect 'j'

    char target5 = 'j';
    showResults(letters1, target5); // expect 'c'

    char target6 = 'k';
    showResults(letters1, target6); // expect 'c'
  }

  private static void showResults(char[] letters, char target) {
    System.out.println("----ShowResults----");
    System.out.println(Arrays.toString(letters));
    System.out.printf("%c -> %c\n\n", target, nextGreatestLetter(letters, target));
  }

  // time: O(logn), space: O(1)
  public static char nextGreatestLetter(char[] letters, char target) {
    return find(letters, target);
  }

  private static char find(char[] letters, char target) {
    int lo = 0, hi = letters.length;

    while (lo < hi) {
      int mid = lo + (hi - lo) / 2;
      if (letters[mid] <= target)
        lo = mid + 1;
      else
        hi = mid;
    }

    return letters[lo % letters.length];
  }
}
