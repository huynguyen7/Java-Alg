import java.util.List;
import java.util.ArrayList;

// Elements of Programming Interview 8.2.
public class ReverseASingleSublist {
    public static void main(String[] args) {
        ListNode head1 = createLinkedListFromArray(new int[] {11,3,5,7,2});
        int start1 = 2;
        int finish1 = 4;
        showResults(head1, start1, finish1); // expect [11,7,5,3,2]

        ListNode head2 = createLinkedListFromArray(new int[] {1,2,3});
        int start2 = 1;
        int finish2 = 3;
        showResults(head2, start2, finish2); // expect [3,2,1]

        ListNode head3 = createLinkedListFromArray(new int[] {1,2,3});
        int start3 = 2;
        int finish3 = 3;
        showResults(head3, start3, finish3); // expect [1,3,2]

        ListNode head4 = createLinkedListFromArray(new int[] {1,2,3});
        int start4 = 1;
        int finish4 = 2;
        showResults(head4, start4, finish4); // expect [2,1,3]

        ListNode head5 = createLinkedListFromArray(new int[] {0});
        int start5 = 1;
        int finish5 = 1;
        showResults(head5, start5, finish5); // expect [0]

        ListNode head6 = createLinkedListFromArray(new int[] {});
        int start6 = 0;
        int finish6 = 0;
        showResults(head6, start6, finish6); // expect []
    }

    private static void showResults(ListNode head, int start, int finish) {
        System.out.println("\t----ShowResults----");
        System.out.printf("[%d-%d]\n", start, finish);
        System.out.printf("%s\n", head == null ? "null" : head.toString());
        ListNode rs = reverseSublist(head, start, finish);
        System.out.printf("%s\n", rs == null ? "null" : rs.toString());
    }

    /**
     * Time: O(n), space: O(1).
     * Assume that all the inputs are valid (indices are not overbound).
     * @param head linked list's head node.
     * @param start beginning 1-index of sublist (inclusive)
     * @param finish last 1-index of sublist (inclusive)
     * @return  result linked list's head node.
     */
    public static ListNode reverseSublist(ListNode head, int start, int finish) {
        if(head == null || head.next == null || finish-start == 0) return head;

        ListNode prev, curr, next;
        curr = head;
        prev = curr;
        next = curr.next;

        // Look for node at start 1-index.
        int i = 1;
        for(; i < start; ++i) {
            prev = curr;
            curr = curr.next;
        }

        ListNode dummy = new ListNode();
        if(start != 1) dummy.next = head;

        // Memorize the last node before reversing.
        ListNode lastNodeBeforeReversing = prev;
        
        prev = curr;
        next = curr.next;

        // Reverse sublist.
        for(; i < finish; ++i) {
            curr = next;
            next = next.next;
            curr.next = prev;
            prev = curr;
        }

        // Reconnect the sublist
        if(start == 1) {
            dummy.next = curr;
            lastNodeBeforeReversing.next = null;
            head.next = next;
        } else {
            lastNodeBeforeReversing.next.next = next;
            lastNodeBeforeReversing.next = curr;
        }
        
        return dummy.next;
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
