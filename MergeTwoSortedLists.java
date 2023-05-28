/**
 * Elements of Programming Interview 8.1.
 * leetcode 21.
 */
public class MergeTwoSortedLists {
    public static void main(String[] args) {
        ListNode node1a = createLinkedListFromArray(new int[] {1,2,4});
        ListNode node2a = createLinkedListFromArray(new int[] {1,3,4});
        showResults(node1a, node2a); // expect [1,2,3,4,4]

        ListNode node1b = createLinkedListFromArray(new int[] {});
        ListNode node2b = createLinkedListFromArray(new int[] {});
        showResults(node1b, node2b); // expect []

        ListNode node1c = createLinkedListFromArray(new int[] {0});
        ListNode node2c = createLinkedListFromArray(new int[] {});
        showResults(node1c, node2c); // expect [0]

        ListNode node1d = createLinkedListFromArray(new int[] {});
        ListNode node2d = createLinkedListFromArray(new int[] {0});
        showResults(node1d, node2d); // expect [0]

        ListNode node1e = createLinkedListFromArray(new int[] {1,2});
        ListNode node2e = createLinkedListFromArray(new int[] {0});
        showResults(node1e, node2e); // expect [0]
    }

    private static void showResults(ListNode list1, ListNode list2) {
        System.out.println("\t----ShowResults----");
        System.out.printf("%s\n%s\n",
                list1 != null ? list1.toString() : "null",
                list2 != null ? list2.toString() : "null");
        ListNode rs = mergeTwoLists(list1, list2);
        System.out.printf("%s\n\n",
                rs != null ? rs.toString() : "null");
    }

    // n is list1's length, m is list2's length.
    // Time: O(n+m), space: O(1)
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode();
        ListNode tmp;
        ListNode curr1 = list1, curr2 = list2;
        
        if(curr1 == null) return curr2;
        else if(curr2 == null) return curr1;

        if(curr1.val <= curr2.val) {
            tmp = curr1;
            curr1 = curr1.next;
        } else {
            tmp = curr2;
            curr2 = curr2.next;
        }
        dummy.next = tmp;
        
        while(curr1 != null && curr2 != null) {
            if(curr1.val <= curr2.val){
                tmp.next = curr1;
                curr1 = curr1.next;
            } else {
                tmp.next = curr2;
                curr2 = curr2.next;
            }
            tmp = tmp.next;
        }
        
        // Append the rest of nodes from the curr pointer that is not null.
        tmp.next = curr1 != null ? curr1 : curr2;

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
