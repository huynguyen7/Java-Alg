import java.util.Stack;

// leetcode 206.
public class ReverseLinkedList {
    public static void main(String[] args) {
        ListNode head1 = createLinkedListFromArray(new int[] {1,2,3,4,5});
        showResults(head1); // expect [5,4,3,2,1]

        ListNode head2 = createLinkedListFromArray(new int[] {1,2});
        showResults(head2); // expect [2,1]

        ListNode head3 = createLinkedListFromArray(new int[] {});
        showResults(head3); // expect []

        ListNode head4 = createLinkedListFromArray(new int[] {1});
        showResults(head4); // expect [1]
    }

    private static void showResults(ListNode head) {
        System.out.println("\t----ShowResults----");
        System.out.printf("%s\n", head == null ? "null" : head.toString());
        ListNode rs = reverseList(head);
        System.out.printf("%s\n", rs == null ? "null" : rs.toString());
    }

    // Time: O(n), space: O(1)
    public static ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode curr = head, prev = head, next = head.next;
        
        while(next != null) {
            curr = next;
            next = next.next;
            curr.next = prev;
            prev = curr;
        }
        head.next = null;
        
        return curr;
    }

    // Time: O(n), space: O(n)
    public static ListNode reverseListII(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode dummy = new ListNode();
        ListNode curr = head;
        Stack<ListNode> stack = new Stack<>();

        while(curr != null) {
            stack.push(curr);
            curr = curr.next;
        }

        curr = stack.pop();
        dummy.next = curr;
        while(!stack.isEmpty()) {
            curr.next = stack.pop();
            curr = curr.next;
        }
        curr.next = null;

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
