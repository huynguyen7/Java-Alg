import java.util.Deque;
import java.util.LinkedList;

// leetcode 2130.
public class MaximumTwinSumOfALinkedList {
    public static void main(String[] args) {
        assert(showResults(new int[] {5,4,2,1}) == 6); // expect 6
        assert(showResults(new int[] {4,2,2,3}) == 7); // expect 7
        assert(showResults(new int[] {1,100000}) == 100001); // expect 100001
    }

    private static int showResults(int[] nums) {
        System.out.println("\t----ShowResults----");
        ListNode head = createLinkedList(nums);
        printLinkedList(head);
        int rs = pairSum(head);
        System.out.printf("%d\n\n", rs);
        return rs;
    }

    // Time: O(n), space: O(n)
    public static int pairSum(ListNode head) {
        int maxSum = 0; // Resulting output.
        int n = 0; // List size.
        Deque<ListNode> deque = new LinkedList<>(); // 2 sided queue.

        ListNode curr = head;
        while(curr != null) { // Add nodes to deque.
            n += 1;
            deque.offerLast(curr);
            curr = curr.next;
        }

        n = n/2;
        while(n-- != 0) {
            ListNode node1 = deque.pollFirst();
            ListNode node2 = deque.pollLast();
            int sum = node1.val + node2.val;
            maxSum = Math.max(maxSum, sum);
        }

        return maxSum;
    }

    private static void printLinkedList(ListNode head) {
        ListNode curr = head;
        while(curr != null) {
            System.out.printf("%d->", curr.val);
            curr = curr.next;
        }
        System.out.printf("NULL\n");
    }

    private static ListNode createLinkedList(int[] nums) {
        ListNode dummy = new ListNode();
        ListNode curr = dummy;

        for(int num: nums) {
            curr.next = new ListNode(num);
            curr = curr.next;
        }

        return dummy.next;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
