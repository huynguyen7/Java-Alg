import java.util.Arrays;

// leetcode 725.
public class SplitLinkedListInParts {
    public static void main(String[] args) {
        showResults(new int[] {1,2,3}, 5); // expect [[1],[2],[3],[],[]]
        showResults(new int[] {1,2,3,4,5,6,7,8,9,10}, 3); // expect [[1,2,3,4],[5,6,7],[8,9,10]]
    }

    private static void showResults(int[] vals, int k) {
        System.out.println("\t----ShowResults----");
        ListNode head = createLinkedListFromArray(vals);
        printLinkedList(head);
        System.out.printf("k = %d\n", k);

        ListNode[] rs = splitListToParts(head, k);
        for(ListNode l: rs)
            printLinkedList(l);
        System.out.println();
    }

    // Time: O(n), space: O(n)
    public static ListNode[] splitListToParts(ListNode head, int k) {
        ListNode[] rs = new ListNode[k];

        // Get Linked List's length.
        int n = 0;
        ListNode curr = head;
        while(curr != null) {
            n++;
            curr = curr.next;
        }

        // Calculating sub-list size appropriately.
        final int m = n/k; // List size.
        int numOddLists = n%k;
        
        curr = head;
        for(int i = 0; i < k; ++i) {
            ListNode currHead = curr, prev = curr;
            int count = 0;
            while(count < m) {
                prev = curr;
                curr = curr.next;
                count++;
            }
            if(numOddLists != 0 && curr != null) {
                numOddLists--;
                prev = curr;
                curr = curr.next;
            }

            if(prev != null) prev.next = null;

            // Append result.
            rs[i] = currHead;
        }
    
        return rs;
    }

    private static ListNode createLinkedListFromArray(int[] nums) {
        ListNode head = new ListNode();
        ListNode curr = head;
        for(int i = 0; i < nums.length; ++i) {
            curr.next = new ListNode(nums[i]);
            curr = curr.next;
        }

        return head.next;
    }

    private static void printLinkedList(ListNode head) {
        ListNode dummy = head;
        while(dummy != null) {
            System.out.printf("%d->", dummy.val);
            dummy = dummy.next;
        }
        System.out.print("NULL\n");
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }

        @Override
        public String toString() {
            return String.format("%d", val);
        }
    }
}
