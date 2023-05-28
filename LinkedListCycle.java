import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

/**
 * Elements of Programming Interview 8.3.
 * leetcode 141.
 */
public class LinkedListCycle {
    public static void main(String[] args) {
        // Test suite A.
        ListNode headA = createLinkedListFromArray(new int[] {0,1,2,3});
        ListNode nodeA = getListNodeAtIndex(headA, 3);
        nodeA.next = headA;
        assert(showResults(headA) == true);
        
        // Test suite B.
        ListNode headB = createLinkedListFromArray(new int[] {0,1,2,3});
        ListNode nodeB1 = getListNodeAtIndex(headB, 3);
        ListNode nodeB2 = getListNodeAtIndex(headB, 1);
        nodeB1.next = nodeB2;
        assert(showResults(headB) == true);
        
        // Test suite C.
        ListNode headC = createLinkedListFromArray(new int[] {0});
        ListNode nodeC = getListNodeAtIndex(headC, 0);
        nodeC.next = headC;
        assert(showResults(headC) == true);
        
        // Test suite D.
        ListNode headD = createLinkedListFromArray(new int[] {0});
        assert(showResults(headD) == false);
        
        // Test suite E.
        ListNode headE = createLinkedListFromArray(new int[] {0,1,2});
        assert(showResults(headE) == false);
    }

    private static boolean showResults(ListNode head) {
        System.out.println("\t----ShowResults----");
        return hasCycle(head);
    }

    /**
     * n is the total number of nodes;
     * c is the number of nodes within the cycle.
     * Time: O(n+c), space: O(1).
     *
     * @param head linked list's beginning node.
     * @return true if the linked list has cyclicity.
     */
    public static boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) return false;

        ListNode slow = head, fast = head.next;
        while(slow != null && fast != null && fast.next != null) {
            if(slow == fast) return true;
            slow = slow.next;
            fast = fast.next.next;
        }

        return false;
    }

    /**
     * n is the total number of nodes;
     * Time: O(n), space: O(n).
     *
     * @param head linked list's beginning node.
     * @return true if the linked list has cyclicity.
     */
    public static boolean hasCycleII(ListNode head) {
        if(head == null || head.next == null) return false;

        Set<ListNode> seen = new HashSet<>();
        ListNode curr = head;
        while(curr != null) {
            if(seen.contains(curr)) return true;
            seen.add(curr);
            curr = curr.next;
        }

        return false;
    }


    /**
     * Get Linked List node given 0-index.
     */
    private static ListNode getListNodeAtIndex(ListNode head, int i) {
        ListNode curr = head;
        int currI = 0;
        while(curr != null && currI++ < i)
            curr = curr.next;
        return curr;
    }

    private static ListNode createLinkedListFromArray(int[] nums) {
        if(nums == null || nums.length == 0) return null;

        ListNode dummy = new ListNode();
        ListNode curr = new ListNode(nums[0]);
        dummy.next = curr;
        

        for(int i = 1; i < nums.length; ++i) {
            curr.next = new ListNode(nums[i]);
            curr = curr.next;
        }

        return dummy.next;
    }

    private static int[] linkedListToArray(ListNode head) {
        if(head == null) return new int[] {};

        ListNode curr = head;
        List<Integer> holder = new ArrayList<>();
        while(curr != null) {
            holder.add(curr.val);
            curr = curr.next;
        }

        return holder.stream().mapToInt(Integer::intValue).toArray();
    }

    private static boolean compare(ListNode head1, ListNode head2) {
        ListNode curr1 = head1, curr2 = head2;
        while(true) {
            if(curr1 == null && curr2 == null) break;
            if((curr1 == null || curr2 == null) && (curr1 != null || curr2 != null))
                return false;
            if(curr1.val != curr2.val) return false;
            curr1 = curr1.next;
            curr2 = curr2.next;
        }
        return true;
    }

    static class ListNode {
        public int val;
        public ListNode next;

        public ListNode() {
            this.val = 0;
        }

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(ListNode next) {
            this.next = next;
            this.val = 0;
        }

        public ListNode(ListNode next, int val) {
            this.next = next;
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            ListNode curr = this;
            StringBuilder s = new StringBuilder();
            while(curr != null) {
                s.append(String.format("%d->", curr.val));
                curr = curr.next;
            }
            s.append("null");

            return s.toString();
        }
    }
}
