import java.util.PriorityQueue;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class ImplementStackByHeap {
  public static void main(String args[]) {
    int[] nums1 = { 5, 4, 3, 2, 1 };
    showResults(nums1); // expect 1 2 3 4 5

    int[] nums2 = { 4 };
    showResults(nums2); // expect 4
  }

  private static void showResults(int[] nums) {
    System.out.println("----ShowResults----");
    System.out.println(Arrays.toString(nums));

    ImplementStackByHeap stack = new ImplementStackByHeap(nums);
    while (!stack.isEmpty())
      System.out.print(stack.pop());
    System.out.println("\n");
  }

  private PriorityQueue<KeyValue> maxHeap;
  private int keyCounter;

  public ImplementStackByHeap(int[] nums) {
    keyCounter = 0;
    maxHeap = new PriorityQueue<KeyValue>(nums.length, (a, b) -> Integer.compare(b.key, a.key));

    for (int i = 0; i < nums.length; ++i)
      maxHeap.add(new KeyValue(keyCounter++, nums[i]));

  }

  public boolean isEmpty() {
    return keyCounter == 0;
  }

  public int pop() {
    if (isEmpty())
      throw new NoSuchElementException();
    keyCounter--;
    return maxHeap.poll().val;
  }

  public void add(int val) {
    maxHeap.add(new KeyValue(keyCounter++, val));
  }

  static class KeyValue {
    int key;
    int val;

    public KeyValue(int key, int val) {
      this.key = key;
      this.val = val;
    }
  }
}
