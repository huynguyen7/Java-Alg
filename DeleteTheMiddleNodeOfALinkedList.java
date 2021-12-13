// leetcode 2095.
public class DeleteTheMiddleNodeOfALinkedList {
    public static void main(String[] args) {
        showResults(new int[] {1,3,4,7,1,2,6}); // expect [1,3,4,1,2,6]
        showResults(new int[] {1,2,3,4}); // expect [1,2,4]
        showResults(new int[] {2,1}); // expect [1]
        showResults(new int[] {1}); // expect []
    }

    private static void showResults(int[] nums) {
        System.out.println("\t----ShowResults----");
        ListNode head = arrToSLL(nums);
        printSLL(head);
        ListNode rs = deleteMiddle(head);
        printSLL(rs);
        System.out.println();
    }

    // Time: O(n), space: O(1)
    public static ListNode deleteMiddle(ListNode head) {
        ListNode one = head;
        ListNode two = head;
        ListNode prevOne = new ListNode();
        ListNode dummy = prevOne;
        prevOne.next = one;

        while(two != null && two.next != null) {
            prevOne = one;
            one = one.next;
            two = two.next.next;
        }

        // One is middle node.
        prevOne.next = one.next;

        return dummy.next;
    }

    private static void printSLL(ListNode head) {
        ListNode curr = head;
        while(curr != null) {
            System.out.printf("%d->", curr.val);
            curr = curr.next;
        }
        System.out.printf("NULL\n");
    }

    // Convert int array to singly linked list.
    private static ListNode arrToSLL(int[] nums) {
        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        
        for(int num: nums) {
            curr.next = new ListNode(num);
            curr = curr.next;
        }

        return dummy.next;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
