import java.util.*;

/**
 * Elements of Programming Interview 8.8.
 * leetcode 83.
 */
public class RemoveDuplicatesFromSortedListI {
    public static void main(String args[]) {
        //all input should be sorted arrs.
        int[] nums1 = {1,1,2,2,3,3,3,3,4,4};
        showResults(nums1); // expect [1 2 3 4]
        
        int[] nums2 = {1,2,2,3,4,4};
        showResults(nums2); // expect [1 2 3 4]

        int[] nums3 = {1};
        showResults(nums3); // expect [1]

        int[] nums4 = {};
        showResults(nums4); // expect []
    }

    private static void showResults(int[] nums) {
        System.out.println("----ShowResults----");
        System.out.println("INPUT");
        ListNode l = createLinkedList(nums);
        printLinkedList(l);

        System.out.println("\nOUTPUT");
        deleteDuplicates(l);
        printLinkedList(l);
        System.out.println();
    }

    // Time: O(n), space: O(1); n is the length of linked list.
    public static ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode curr = head;
        while(curr != null) {
            ListNode next = curr.next;
            while(next != null && next.val == curr.val)
                next = next.next;
            if(next != curr.next)
                curr.next = next;
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

    private static ListNode addTwoLinkedList(ListNode headA, ListNode headB) {
        if(headB == null) return headA;
        ListNode dummy = new ListNode();
        dummy.next = headA;
        
        ListNode curr;
        for(curr = headA; curr.next != null; curr = curr.next);
        curr.next = headB;

        return dummy.next;
    }

    private static void printLinkedList(ListNode head) {
        if(head == null) {
            System.out.println("null");
            return;
        }
        for(ListNode curr = head; curr != null; curr = curr.next)
            System.out.printf("%d ", curr.val);
        System.out.println();
    }

    private static ListNode createLinkedList(int[] nums) {
        if(nums == null || nums.length == 0) return null;
        ListNode curr = new ListNode();
        ListNode dummy = curr;
        for(int i = 0; i < nums.length; ++i) {
            curr.next = new ListNode(nums[i]);
            curr = curr.next;
        }

        return dummy.next;
    }

    static class ListNode {
        int val;
        ListNode next;
        
        ListNode() {}
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
