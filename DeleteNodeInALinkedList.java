import java.util.List;
import java.util.ArrayList;

/**
 * Elements of Programming Interview 8.5.
 * leetcode 237.
 */
public class DeleteNodeInALinkedList {
    public static void main(String[] args) {
        // Test suite A.
        ListNode head1a = createLinkedListFromArray(new int[] {4,5,1,9});
        ListNode node1a = getListNodeAtIndex(head1a, 1);
        showResults(head1a, node1a);
        assert(equals(head1a,
                    createLinkedListFromArray(new int[] {4,1,9}))); // expect [4,1,9]

        // Test suite B.
        ListNode head1b = createLinkedListFromArray(new int[] {4,5});
        ListNode node1b = getListNodeAtIndex(head1b, 0);
        showResults(head1b, node1b);
        assert(equals(head1b,
                    createLinkedListFromArray(new int[] {5}))); // expect [5]
    }

    private static void showResults(ListNode head, ListNode nodeToDelete) {
        System.out.println("\t----ShowResults----");
        System.out.printf("%s\n", head != null ? head.toString() : "null");
        deleteNode(nodeToDelete);
        System.out.printf("%s\n\n", head != null ? head.toString() : "null");
    }

    /*
     * Constraint: The node to delete cannot be the last one in the linked list.
     * Time: O(1), space: O(1)
     */
    public static void deleteNode(ListNode nodeToDelete) {
        ListNode nextNode = nodeToDelete.next;
        nodeToDelete.val = nextNode.val;
        nodeToDelete.next = nextNode.next;
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
