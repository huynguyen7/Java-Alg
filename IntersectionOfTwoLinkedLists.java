import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

/**
 * Elements of Programming Interview 8.4.
 * leetcode 160.
 */
public class IntersectionOfTwoLinkedLists {
    public static void main(String[] args) {
        // Test suite 1.
        ListNode head1A = createLinkedListFromArray(new int[] {});
        ListNode head1B = createLinkedListFromArray(new int[] {});
        assert(equals(showResults(1, head1A, head1B), null)); // expect []

        // Test suite 2.
        ListNode head2A = createLinkedListFromArray(new int[] {1,2});
        ListNode head2B = createLinkedListFromArray(new int[] {});
        assert(equals(showResults(2, head2A, head2B), null)); // expect []

        // Test suite 3.
        ListNode head3A = createLinkedListFromArray(new int[] {});
        ListNode head3B = createLinkedListFromArray(new int[] {1,2});
        assert(equals(showResults(3, head3A, head3B), null)); // expect []

        // Test suite 4.
        ListNode head4A = createLinkedListFromArray(new int[] {1,2});
        ListNode head4B = createLinkedListFromArray(new int[] {3,4,5,6});
        ListNode node4A = getListNodeAtIndex(head4A, 1);
        ListNode node4B = getListNodeAtIndex(head4B, 2);
        node4A.next = node4B;
        assert(equals(showResults(4, head4A, head4B),
                    createLinkedListFromArray(new int[] {5,6}))); // expect [5,6]

        // Test suite 5.
        ListNode head5A = createLinkedListFromArray(new int[] {1,2,3,4});
        ListNode head5B = createLinkedListFromArray(new int[] {5,6});
        ListNode node5A = getListNodeAtIndex(head5A, 3);
        ListNode node5B = getListNodeAtIndex(head5B, 0);
        node5A.next = node5B;
        assert(equals(showResults(5, head5A, head5B),
                    createLinkedListFromArray(new int[] {5,6}))); // expect [5,6]

        // Test suite 6.
        ListNode head6A = createLinkedListFromArray(new int[] {5,6});
        ListNode head6B = createLinkedListFromArray(new int[] {1,2,3,4});
        ListNode node6A = getListNodeAtIndex(head6A, 0);
        ListNode node6B = getListNodeAtIndex(head6B, 3);
        node6B.next = node6A;
        assert(equals(showResults(6, head6A, head6B),
                    createLinkedListFromArray(new int[] {5,6}))); // expect [5,6]

        // Test suite 7.
        ListNode head7A = createLinkedListFromArray(new int[] {3});
        ListNode head7B = createLinkedListFromArray(new int[] {1,2});
        ListNode node7A = getListNodeAtIndex(head7A, 0);
        ListNode node7B = getListNodeAtIndex(head7B, 1);
        node7B.next = node7A;
        assert(equals(showResults(7, head7A, head7B),
                    createLinkedListFromArray(new int[] {3}))); // expect [3]

        // Test suite 8.
        ListNode head8A = createLinkedListFromArray(new int[] {1,2});
        ListNode head8B = createLinkedListFromArray(new int[] {3});
        ListNode node8A = getListNodeAtIndex(head8A, 1);
        ListNode node8B = getListNodeAtIndex(head8B, 0);
        node8A.next = node8B;
        assert(equals(showResults(8, head8A, head8B),
                    createLinkedListFromArray(new int[] {3}))); // expect [3]

        // Test suite 9.
        ListNode head9A = createLinkedListFromArray(new int[] {1});
        ListNode head9B = createLinkedListFromArray(new int[] {3});
        ListNode node9A = getListNodeAtIndex(head9A, 0);
        ListNode node9B = getListNodeAtIndex(head9B, 0);
        node9A.next = node9B;
        assert(equals(showResults(9, head9A, head9B),
                    createLinkedListFromArray(new int[] {3}))); // expect [3]

        // Test suite 10.
        ListNode head10A = createLinkedListFromArray(new int[] {1});
        ListNode head10B = createLinkedListFromArray(new int[] {3});
        ListNode node10A = getListNodeAtIndex(head10A, 0);
        ListNode node10B = getListNodeAtIndex(head10B, 0);
        node10B.next = node10A;
        assert(equals(showResults(10, head10A, head10B),
                    createLinkedListFromArray(new int[] {1}))); // expect [1]
    }

    private static ListNode showResults(int suite, ListNode headA, ListNode headB) {
        System.out.printf("\t----Test-Suite-%d----\n", suite);
        System.out.printf("%s\n%s\n",
                headA == null ? "null" : headA.toString(),
                headB == null ? "null" : headB.toString());
        ListNode rs = getIntersectionNode(headA, headB);
        System.out.printf("%s\n\n",
                 rs == null ? "null" : rs.toString());
        return rs;
    }

    /**
     * n is the number of nodes in linked list A;
     * m is the number of nodes in linked list B.
     *
     * Time: O(n+m), space: O(1).
     * @return found beginning node of the intersection, else null.
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int n = 0, m = 0;

        ListNode curr1 = headA, curr2 = headB;
        for(; curr1 != null; curr1 = curr1.next)
            n += 1;
        for(; curr2 != null; curr2 = curr2.next)
            m += 1;

        ListNode longerList = n >= m ? headA : headB;
        ListNode shorterList = n < m ? headA : headB;

        curr1 = longerList;
        curr2 = shorterList;

        int diff = Math.abs(n-m);
        for(int i = 0; i < diff; ++i)
            curr1 = curr1.next;

        while(curr1 != curr2) {
            curr1 = curr1.next;
            curr2 = curr2.next;
        }

        return curr1;
    }

    /**
     * n is the number of nodes in linked list A;
     * m is the number of nodes in linked list B.
     *
     * Time: O(n+m), space: O(n+m).
     * @return found beginning node of the intersection, else null.
     */
    public static ListNode getIntersectionNodeII(ListNode headA, ListNode headB) {
        ListNode curr1 = headA, curr2 = headB;

        Set<ListNode> seen = new HashSet<>();
        while(curr1 != null || curr2 != null) {
            if(curr1 != null) {
                if(seen.contains(curr1)) return curr1;
                seen.add(curr1);
                curr1 = curr1.next;
            }
            if(curr2 != null) {
                if(seen.contains(curr2)) return curr2;
                seen.add(curr2);
                curr2 = curr2.next;
            }
        }

        return null;
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

    private static boolean equals(ListNode head1, ListNode head2) {
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
